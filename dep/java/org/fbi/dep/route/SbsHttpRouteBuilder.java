package org.fbi.dep.route;

import com.thoughtworks.xstream.converters.ConversionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.netty.http.NettyChannelBufferStreamCache;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.fbi.dep.component.jms.JmsBytesClient;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.helper.MD5Helper;
import org.fbi.dep.management.TxnChecker;
import org.fbi.dep.management.TxnUseridChecker;
import org.fbi.dep.model.CheckResult;
import org.fbi.dep.model.base.ToaXmlHttpErr;
import org.fbi.dep.transform.AbstractTiaBytesTransform;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.dep.txn.AbstractTxnProcessor;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by XIANGYANG on 2015-5-23.
 * httpЭ��
 */

public class SbsHttpRouteBuilder extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(SbsHttpRouteBuilder.class);
    private static String SERVER_IP = PropertyManager.getProperty("dep.localhost.ip");
    private String server_port;

    public SbsHttpRouteBuilder(String port) {
        this.server_port = port;
    }

    @Override
    public void configure() throws Exception {
        from("netty-http:http://" + SERVER_IP + ":" + server_port + "/depService?matchOnUriPrefix=false").
            process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                String rtnmac = null;
                                String txnCode = null;
                                String userId = null;
                                String reqsn = null;
                                String version = null;
                                String txnDate = null;
                                String txnTime = null;
                                String userKey = null;
                                String rtnXml = null;
                                NettyChannelBufferStreamCache cf = null;
                                try {
                                    cf = (NettyChannelBufferStreamCache) exchange.getIn().getBody();
                                    StringBuffer sb = new StringBuffer();
                                    int l;
                                    byte[] tmp = new byte[2048];
                                    while ((l = cf.read(tmp)) != -1) {
                                        sb.append(new String(tmp, 0, l));
                                    }
                                    byte[] bytes = sb.toString().getBytes();
                                    logger.info("��SbsHttpRouteBuilder ���յ����ġ�" + sb.toString());

                                    //�ͻ��������ĸ�ʽΪ(Encrypt-MD5:32�ֽ�MD5ֵ+�س�����+XML������)
                                    //1.����mac
                                    int macLength = 32;
                                    byte[] macBytes = new byte[macLength];
                                    System.arraycopy(bytes, 12, macBytes, 0, macBytes.length);
                                    String mac = new String(macBytes, "GBK");

                                    //2.����XML������info����
                                    byte[] bodyBytes = new byte[bytes.length - macLength - 13];
                                    System.arraycopy(bytes, macLength + 13, bodyBytes, 0, bodyBytes.length);
                                    String xmlMsgData = new String(bodyBytes, "GBK");

                                    SAXReader saxReader = new SAXReader();
                                    InputStream inputStream = new ByteArrayInputStream(bodyBytes);
                                    Document document = saxReader.read(inputStream);
                                    Element root = document.getRootElement();
                                    for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                                        Element info = (Element) i.next();
                                        if ("info".equals(info.getName())){
                                            for (Iterator j = info.elementIterator(); j.hasNext(); ) {
                                                Element node = (Element) j.next();
                                                if ("txncode".equals(node.getName())){
                                                    txnCode = node.getText();
                                                }
                                                if ("userid".equals(node.getName())){
                                                    userId = node.getText();
                                                }
                                                if ("reqsn".equals(node.getName())){
                                                    reqsn = node.getText();
                                                }
                                                if ("version".equals(node.getName())){
                                                    version = node.getText();
                                                }
                                                if ("txndate".equals(node.getName())){
                                                    txnDate = node.getText();
                                                }
                                                if ("txntime".equals(node.getName())){
                                                    txnTime = node.getText();
                                                }
                                            }
                                            break;
                                        }
                                    }

                                    //3.MD5У��
                                    // MAC(32λ����Message Data���� +  USER_ID + USER_KEY��Ϊ���ݲ�������ASC�ַ���ʾ��16����MD5ֵ������USER_KEY�ɲ���˾���ÿ���û������·���
                                    userKey = PropertyManager.getProperty("wsys.userkey." + userId);
                                    String md5 = MD5Helper.getMD5String(userId + userKey + xmlMsgData);
                                    // ��֤ʧ�� ������֤ʧ����Ϣ
                                    if (!md5.equalsIgnoreCase(mac)) {
                                        logger.info("�û���ʶ��" + userId + " " + userKey + " ����ʱ��:" + txnDate + " " + txnTime);
                                        logger.info("MACУ��ʧ��[�����]MD5:" + md5 + "[�ͻ���]MAC:" + mac);
                                        throw new RuntimeException(TxnRtnCode.MSG_VERIFY_MAC_ILLEGAL.toRtnMsg());
                                    } else {
                                        logger.info("MACУ��ɹ�,�û���ʶ��" + userId + " " + userKey + " ����ʱ��:" + txnDate + " " + txnTime);
                                    }

                                    // δͨ��բ��ʱ���׳��쳣������ʱ����CheckResult
                                    CheckResult checkResult = new CheckResult(userId, txnCode);
                                    // �û�����Ȩ��բ��
                                    new TxnUseridChecker().check(userId, txnCode, checkResult);
                                    // ҵ������բ��
                                    String checkerClass = PropertyManager.getProperty("check." + userId + "." + txnCode);
                                    if (!StringUtils.isEmpty(checkerClass)) {
                                        logger.info(txnCode + "��������բ�ڣ�" + checkerClass);
                                        TxnChecker checker = (TxnChecker) Class.forName(checkerClass).newInstance();
                                        checker.check(userId, txnCode, xmlMsgData, checkResult);
                                    }
                                    // ���⽻�����⴦��
                                    Class txnClass = null;
                                    try {
                                        txnClass = Class.forName("org.fbi.dep.txn.Txn" + txnCode + "Processor");
                                    } catch (ClassNotFoundException e) {
                                        txnClass = null;
                                    }
                                    if (txnClass != null) {
                                        AbstractTxnProcessor processor = (AbstractTxnProcessor) txnClass.newInstance();
                                        rtnXml = processor.process(userId, xmlMsgData);
                                    } else {
                                        // ֱ��SBS
                                        AbstractTiaBytesTransform bytesTransform = (AbstractTiaBytesTransform) Class.forName("org.fbi.dep.transform.TiaXml" + txnCode + "Transform").newInstance();
                                        byte[] sbsReqMsg = bytesTransform.run(xmlMsgData, userId);
                                        // SBS
                                        byte[] sbsResBytes = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", txnCode, userId,
                                                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsg);
                                        // ����ת��Ϊdep-sbs-xml����
                                        AbstractToaBytesTransform toaTransform = (AbstractToaBytesTransform) Class.forName("org.fbi.dep.transform.ToaXml" + txnCode + "Transform").newInstance();
                                        rtnXml = toaTransform.run(sbsResBytes);
                                    }
                                    rtnmac = MD5Helper.getMD5String(userId + userKey + rtnXml);
                                    logger.info("��SbsHttpRouteBuilder ���ͱ��ġ�" + new String((rtnmac + "\n" + rtnXml).getBytes("GBK")));
                                    exchange.getOut().setBody((rtnmac + "\n" + rtnXml).getBytes("GBK"));
                                } catch (Exception e) {
                                    //  �����쳣��Ϣ
                                    ToaXmlHttpErr errXmlHttp = new ToaXmlHttpErr();
                                    errXmlHttp.getInfo().setTxncode(txnCode);
                                    errXmlHttp.getInfo().setUserid(userId);
                                    errXmlHttp.getInfo().setReqsn(reqsn);
                                    errXmlHttp.getInfo().setVersion(version);
                                    errXmlHttp.getInfo().setTxndate(txnDate);
                                    errXmlHttp.getInfo().setTxntime(txnTime);

                                    if (txnCode == null|| ConversionException.class.equals(e.getClass())) {
                                        logger.error("���Ľ���ʧ��", e);
                                        errXmlHttp.getInfo().setRtncode(TxnRtnCode.MSG_ANALYSIS_ILLEGAL.getCode());
                                        errXmlHttp.getInfo().setRtnmsg(TxnRtnCode.MSG_ANALYSIS_ILLEGAL.getTitle());
                                        errXmlHttp.getBody().setRtncode(TxnRtnCode.MSG_ANALYSIS_ILLEGAL.getCode());
                                        errXmlHttp.getBody().setRtnmsg(TxnRtnCode.MSG_ANALYSIS_ILLEGAL.getTitle());
                                    } else {
                                        String exmsg = e.getMessage();
                                        logger.error("�����쳣", e);
                                        if (exmsg == null) {
                                            exmsg = TxnRtnCode.SERVER_EXCEPTION.getCode() + "|" + TxnRtnCode.SERVER_EXCEPTION.getTitle();
                                        } else if (!exmsg.contains("|")) {
                                            exmsg = TxnRtnCode.SERVER_EXCEPTION.getCode() + "|" + exmsg;
                                        }
                                        String errmsg[] = exmsg.split("\\|");
                                        SBSFormCode msgFormCode = SBSFormCode.valueOfAlias(errmsg[0]);
                                        if (msgFormCode != null) {
                                            errmsg[1] = msgFormCode.getTitle();
                                        }
                                        errXmlHttp.getInfo().setRtncode(errmsg[0]);
                                        errXmlHttp.getInfo().setRtnmsg(errmsg[1]);
                                        errXmlHttp.getBody().setRtncode(errmsg[0]);
                                        errXmlHttp.getBody().setRtnmsg(errmsg[1]);
                                    }
                                    rtnXml = errXmlHttp.toString();
                                    rtnmac = MD5Helper.getMD5String(rtnXml + userId + userKey);
                                    logger.info("��SbsHttpRouteBuilder ���ͱ��ġ�" + new String((rtnmac + "\n" + rtnXml).getBytes("GBK")));
                                    exchange.getOut().setBody((rtnmac + "\n" + rtnXml).getBytes("GBK"));
                                }finally {
                                    cf.close();
                                }
                            }
                        }
            );
    }
}

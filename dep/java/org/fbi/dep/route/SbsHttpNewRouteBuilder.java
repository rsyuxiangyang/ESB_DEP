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
import org.fbi.dep.enums.TxnBusiRtnCode;
import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.helper.MD5Helper;
import org.fbi.dep.management.TxnChecker;
import org.fbi.dep.management.TxnUseridChecker;
import org.fbi.dep.model.CheckResult;
import org.fbi.dep.transform.AbstractTiaBytesTransform;
import org.fbi.dep.transform.AbstractTiaToToa;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.dep.txn.AbstractTxnProcessor;
import org.fbi.dep.util.PropertyManager;
import org.fbi.dep.util.StringPad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by XIANGYANG on 2015-5-23.
 * httpЭ��
 */

public class SbsHttpNewRouteBuilder extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(SbsHttpNewRouteBuilder.class);
    private static String SERVER_IP = PropertyManager.getProperty("dep.localhost.ip");
    private String server_port;

    public SbsHttpNewRouteBuilder(String port) {
        this.server_port = port;
    }

    @Override
    public void configure() throws Exception {
        from("netty-http:http://" + SERVER_IP + ":" + server_port + "/depService?matchOnUriPrefix=false").
            process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                NettyChannelBufferStreamCache cf = null;
                                cf = (NettyChannelBufferStreamCache) exchange.getIn().getBody();
                                StringBuffer sb = new StringBuffer();
                                int l;
                                byte[] tmp = new byte[2048];
                                while ((l = cf.read(tmp)) != -1) {
                                    sb.append(new String(tmp, 0, l));
                                }
                                byte[] bytesTemp = sb.toString().getBytes();
                                byte[] lengthBytes = new byte[8];
                                byte[] bytes = new byte[bytesTemp.length-8];
                                System.arraycopy(bytesTemp, 0, lengthBytes, 0, lengthBytes.length);
                                System.arraycopy(bytesTemp, 8, bytes, 0, bytes.length);
                                logger.info("��SbsHttpNewRouteBuilder ���ձ��ĳ��ȡ�" + new String(lengthBytes));
                                logger.info("��SbsHttpNewRouteBuilder ���յ����ġ�" + new String(bytes));

                                String rtnMsgHeader = null;
                                String rtnMsgData = null;
                                String txnCode = null;
                                String txnDate = null;
                                String userid = null;
                                String userkey = null;
                                try {
                                    // ��������ͷ
                                    int headerLength = 94;
                                    byte[] headerBytes = new byte[headerLength];
                                    System.arraycopy(bytes, 0, headerBytes, 0, headerBytes.length);
                                    String msgHeader = new String(headerBytes, "GBK");
                                    rtnMsgHeader = msgHeader.substring(0, 38);

                                    // ������
                                    byte[] bodyBytes = new byte[bytes.length - headerLength];
                                    System.arraycopy(bytes, headerLength, bodyBytes, 0, bodyBytes.length);
                                    String msgData = new String(bodyBytes, "GBK");
                                    rtnMsgData = msgData;
                                    // ��Χϵͳ�û�ID�������롢�������ڡ�mac
                                    userid = msgHeader.substring(4, 14);
                                    userkey = PropertyManager.getProperty("wsys.userkey." + userid.trim());
                                    txnCode = msgHeader.substring(14, 24).trim();
                                    txnDate = msgHeader.substring(24, 32);
                                    String txnTime = msgHeader.substring(32, 38);
                                    String mac = new String(headerBytes, 62, 32);
                                    // MD5У��
                                    // MAC	32	�ԣ�Message Data���� + TXN_DATE + USER_ID + USER_KEY��Ϊ���ݲ�������ASC�ַ���ʾ��16����MD5ֵ������USER_KEY�ɲ���˾���ÿ���û������·���
                                    String md5 = MD5Helper.getMD5String(msgData + txnDate + userid + userkey);
                                    // ��֤ʧ�� ������֤ʧ����Ϣ
                                    if (!md5.equalsIgnoreCase(mac)) {
                                        logger.info("�û���ʶ��" + userid + " " + userkey + " ����ʱ��:" + txnDate + " " + txnTime);
                                        logger.info("MACУ��ʧ��[�����]MD5:" + md5 + "[�ͻ���]MAC:" + mac);
                                        throw new RuntimeException(TxnRtnCode.MSG_VERIFY_MAC_ILLEGAL.toRtnMsg());
                                    } else {
                                        logger.info("MACУ��ɹ�,�û���ʶ��" + userid + " " + userkey + " ����ʱ��:" + txnDate + " " + txnTime);
                                    }

                                    // δͨ��բ��ʱ���׳��쳣������ʱ����CheckResult
                                    String checkerClass = PropertyManager.getProperty("check." + userid + "." + txnCode);
                                    CheckResult checkResult = new CheckResult(userid, txnCode);
                                    // �û�����Ȩ��բ��
                                    new TxnUseridChecker().check(userid.trim(), txnCode, checkResult);
                                    // ҵ������բ��
                                    if (!StringUtils.isEmpty(checkerClass)) {
                                        logger.info(txnCode + "��������բ�ڣ�" + checkerClass);
                                        TxnChecker checker = (TxnChecker) Class.forName(checkerClass).newInstance();
                                        checker.check(userid, txnCode, msgData, checkResult);
                                    }
                                    String rtnXml = "";
                                    // ���⽻�����⴦��
                                    Class txnClass = null;
                                    try {
                                        txnClass = Class.forName("org.fbi.dep.txn.Txn" + txnCode + "Processor");
                                    } catch (ClassNotFoundException e) {
                                        txnClass = null;
                                    }
                                    if (txnClass != null) {
                                        AbstractTxnProcessor processor = (AbstractTxnProcessor) txnClass.newInstance();
                                        rtnXml = processor.process(userid, msgData);
                                    } else {
                                        // ֱ��SBS
                                        AbstractTiaBytesTransform bytesTransform = (AbstractTiaBytesTransform) Class.forName("org.fbi.dep.transform.TiaXml" + txnCode + "Transform").newInstance();
                                        byte[] sbsReqMsg = bytesTransform.run(msgData, userid.trim());
                                        // SBS
                                        byte[] sbsResBytes = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", txnCode, userid,
                                                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsg);
                                        // ����ת��Ϊdep-sbs-xml����
                                        AbstractToaBytesTransform toaTransform = (AbstractToaBytesTransform) Class.forName("org.fbi.dep.transform.http.sbs.ToaXmlHttp" + txnCode + "Transform").newInstance();
                                        rtnXml = toaTransform.run(sbsResBytes);
                                    }
                                    String rtnmac = MD5Helper.getMD5String(rtnXml + txnDate + userid + userkey);
                                    rtnMsgHeader = rtnMsgHeader + TxnRtnCode.TXN_PROCESSED.getCode()
                                            + StringPad.rightPad4ChineseToByteLength(TxnRtnCode.TXN_PROCESSED.getTitle(), 20, " ")
                                            + rtnmac;

                                    byte[] msgbuf = (rtnMsgHeader + rtnXml).getBytes();
                                    byte[] msglen = (StringPad.rightPad4ChineseToByteLength("" + (msgbuf.length + 8), 8, " ")).getBytes();
                                    byte[] bytesResData = new byte[msgbuf.length + 8];
                                    System.arraycopy(msglen, 0, bytesResData, 0, msglen.length);
                                    System.arraycopy(msgbuf, 0, bytesResData, msglen.length, msgbuf.length);
                                    logger.info("��SbsHttpNewRouteBuilder ���ͱ��ġ�" + new String(bytesResData));
                                    exchange.getOut().setBody(bytesResData);
                                } catch (Exception e) {
                                    //  �����쳣��Ϣ
                                    if (txnCode == null) {
                                        logger.error("���Ľ���ʧ�ܣ��޷�������������.", e);
                                        return;
                                    } else {
                                        String exmsg = e.getMessage();
                                        logger.error("�����쳣", e);
                                        String rtnmsg="";
                                        AbstractTiaToToa tiaToToa = (AbstractTiaToToa) Class.forName("org.fbi.dep.transform.Tia" + txnCode + "ToToa").newInstance();
                                        if (exmsg == null) {
                                            exmsg = TxnRtnCode.SERVER_EXCEPTION.getCode() + "|" + TxnRtnCode.SERVER_EXCEPTION.getTitle();
                                            rtnmsg = tiaToToa.run(rtnMsgData, TxnBusiRtnCode.TXN_DONTKNOW.getCode(),TxnBusiRtnCode.TXN_DONTKNOW.getTitle());
                                        } else if (!exmsg.contains("|")) {
                                            exmsg = TxnRtnCode.SERVER_EXCEPTION.getCode() + "|" + exmsg;
                                            rtnmsg = tiaToToa.run(rtnMsgData, TxnBusiRtnCode.TXN_DONTKNOW.getCode(),exmsg);
                                        }else{
                                            String errmsg[] = exmsg.split("\\|");
                                            SBSFormCode msgFormCode = SBSFormCode.valueOfAlias(errmsg[0]);
                                            if (msgFormCode != null) {
                                                errmsg[1] = msgFormCode.getTitle();
                                            }
                                            rtnmsg = tiaToToa.run(rtnMsgData, errmsg[0], errmsg[1]);
                                        }

                                        String errmsg[] = exmsg.split("\\|");
                                        SBSFormCode msgFormCode = SBSFormCode.valueOfAlias(errmsg[0]);
                                        if (msgFormCode != null) {
                                            errmsg[1] = msgFormCode.getTitle();
                                        }

                                        String rtnmac = MD5Helper.getMD5String(rtnmsg + txnDate + userid + userkey);

                                        rtnMsgHeader = rtnMsgHeader + errmsg[0]
                                                + StringPad.rightPad4ChineseToByteLength(errmsg[1], 20, " ")
                                                + rtnmac;

                                        byte[] msgbuf = (rtnMsgHeader + rtnmsg).getBytes();
                                        byte[] msglen = (StringPad.rightPad4ChineseToByteLength("" + (msgbuf.length + 8), 8, " ")).getBytes();
                                        byte[] bytesResData = new byte[msgbuf.length + 8];
                                        System.arraycopy(msglen, 0, bytesResData, 0, msglen.length);
                                        System.arraycopy(msgbuf, 0, bytesResData, msglen.length, msgbuf.length);
                                        logger.info("��SbsHttpNewRouteBuilder ���ͱ��ġ�" + new String(bytesResData));
                                        exchange.getOut().setBody(bytesResData);
                                    }
                                }
                            }
                    }
            );
    }
}

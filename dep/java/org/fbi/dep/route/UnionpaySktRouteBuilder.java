package org.fbi.dep.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.lang.StringUtils;
import org.fbi.dep.component.jms.JmsBytesClient;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.helper.MD5Helper;
import org.fbi.dep.management.TxnChecker;
import org.fbi.dep.management.TxnUseridChecker;
import org.fbi.dep.model.CheckResult;
import org.fbi.dep.transform.AbstractTiaBytesTransform;
import org.fbi.dep.transform.AbstractTiaToToa;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.dep.util.PropertyManager;
import org.fbi.dep.util.StringPad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unionpay
 */
public class UnionpaySktRouteBuilder extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(UnionpaySktRouteBuilder.class);
    private static String SERVER_IP = PropertyManager.getProperty("dep.localhost.ip");
    private String server_port;

    public UnionpaySktRouteBuilder(String port) {
        this.server_port = port;
    }

    @Override
    public void configure() throws Exception {
        from("netty:tcp://" + SERVER_IP + ":" + server_port + "?sync=true&disconnect=true" +
                "&encoding=GBK&decoder=#skt-decoder&encoder=#skt-encoder")
                .routeId("������֧��ͨ��")
                .process(new Processor() {
                             public void process(Exchange exchange) throws Exception {
                                 byte[] bytes = (byte[]) exchange.getIn().getBody();
                                 logger.info("���յ����ģ�" + new String(bytes));
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
                                     userid = msgHeader.substring(4, 14).trim();
                                     userkey = PropertyManager.getProperty("wsys.userkey." + userid);
                                     txnCode = msgHeader.substring(14, 24).trim();
                                     txnDate = msgHeader.substring(24, 32).trim();
                                     String txnTime = msgHeader.substring(32, 38).trim();
                                     logger.info("�û���ʶ��" + userid + " " + userkey + " ����ʱ��:" + txnDate + " " + txnTime);
                                     String mac = new String(headerBytes, 62, 32);
                                     // MD5У��
                                     // MAC	32	�ԣ�Message Data���� + TXN_DATE + USER_ID + USER_KEY��Ϊ���ݲ�������ASC�ַ���ʾ��16����MD5ֵ������USER_KEY�ɲ���˾���ÿ���û������·���
                                     String md5 = MD5Helper.getMD5String(msgData + txnDate + userid + userkey);
                                     // ��֤ʧ�� ������֤ʧ����Ϣ
                                     if (!md5.equalsIgnoreCase(mac)) {
                                         logger.info("MACУ��ʧ��[�����]MD5:" + md5 + "[�ͻ���]MAC:" + mac);
                                         throw new RuntimeException(TxnRtnCode.MSG_VERIFY_MAC_ILLEGAL.toRtnMsg());
                                     } else {
                                         logger.info("MACУ��ɹ�");
                                     }

                                     // δͨ��բ��ʱ���׳��쳣������ʱ����CheckResult
                                     String checkerClass = PropertyManager.getProperty("check." + userid + "." + txnCode);
                                     CheckResult checkResult = new CheckResult(userid, txnCode);
                                     // �û�����Ȩ��բ��
                                     new TxnUseridChecker().check(userid, txnCode, checkResult);
                                     // ҵ������բ��
                                     if (!StringUtils.isEmpty(checkerClass)) {
                                         logger.info(txnCode + "��������բ�ڣ�" + checkerClass);
                                         TxnChecker checker = (TxnChecker) Class.forName(checkerClass).newInstance();
                                         checker.check(userid, txnCode, msgData, checkResult);
                                     }
                                     AbstractTiaBytesTransform bytesTransform = (AbstractTiaBytesTransform) Class.forName("org.fbi.dep.transform.TiaXml" + txnCode + "Transform").newInstance();
                                     byte[] upayReqMsg = bytesTransform.run(msgData, userid);
                                     // unionpay
                                     byte[] upayResBytes = new JmsBytesClient().sendRecivMsg("100", "fcdep", "fcdep", txnCode, userid,
                                             "queue.dep.core.fcdep.unionpay", "queue.dep.core.unionpay.fcdep", upayReqMsg);
                                     // ����ת��Ϊdep-sbs-xml����
                                     AbstractToaBytesTransform toaTransform = (AbstractToaBytesTransform) Class.forName("org.fbi.dep.transform.ToaXml" + txnCode + "Transform").newInstance();
                                     String rtnXml = toaTransform.run(upayResBytes);
                                     String rtnmac = MD5Helper.getMD5String(rtnXml + txnDate + userid + userkey);
                                     rtnMsgHeader = rtnMsgHeader + TxnRtnCode.TXN_PROCESSED.getCode()
                                             + StringPad.rightPad4ChineseToByteLength(TxnRtnCode.TXN_PROCESSED.getTitle(), 20, " ")
                                             + rtnmac;
                                     exchange.getOut().setBody((rtnMsgHeader + rtnXml).getBytes());
                                 } catch (Exception e) {
                                     //  �����쳣��Ϣ
                                     if (txnCode == null) {
                                         logger.error("���Ľ���ʧ�ܣ��޷�������������.", e);
                                         return;
                                     } else {
                                         String exmsg = e.getMessage();
                                         logger.error("�����쳣", e);
                                         AbstractTiaToToa tiaToToa = (AbstractTiaToToa) Class.forName("org.fbi.dep.transform.Tia" + txnCode + "ToToa").newInstance();
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
                                         String rtnmsg = tiaToToa.run(rtnMsgData, errmsg[0], errmsg[1]);
                                         String rtnmac = MD5Helper.getMD5String(rtnmsg + txnDate + userid + userkey);
                                         rtnMsgHeader = rtnMsgHeader + errmsg[0]
                                                 + StringPad.rightPad4ChineseToByteLength(errmsg[1], 20, " ")
                                                 + rtnmac;
                                         exchange.getOut().setBody((rtnMsgHeader + rtnmsg).getBytes());
                                     }
                                 }
                             }
                         }
                );
    }
}

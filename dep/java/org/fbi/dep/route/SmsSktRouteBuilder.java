package org.fbi.dep.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.lang.StringUtils;
import org.fbi.dep.component.jms.JmsBytesClient;
import org.fbi.dep.component.jms.JmsTopicBytesClient;
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
import org.fbi.endpoint.sms.SmsTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����ƽ̨
 */
public class SmsSktRouteBuilder extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(SmsSktRouteBuilder.class);
    private static String SERVER_IP = PropertyManager.getProperty("dep.localhost.ip");
    private String server_port;

    public SmsSktRouteBuilder(String port) {
        this.server_port = port;
    }

    @Override
    public void configure() throws Exception {
        from("netty:tcp://" + SERVER_IP + ":" + server_port + "?sync=true&disconnect=true" +
                "&encoding=GBK&decoder=#skt-decoder&encoder=#skt-encoder")
                .routeId("SMS֪ͨͨ��")
                .process(new Processor() {
                             public void process(Exchange exchange) throws Exception {
                                 byte[] bytes = (byte[]) exchange.getIn().getBody();
                                 logger.info("���յ����ģ�" + new String(bytes));
                                 String bizCode = "0000";
                                 try {
                                     // ��������ͷ
                                     byte[] headerBytes = new byte[4];
                                     System.arraycopy(bytes, 0, headerBytes, 0, headerBytes.length);
                                     bizCode = new String(headerBytes, "GBK");

                                     // ������
                                     byte[] bodyBytes = new byte[bytes.length - 4];
                                     System.arraycopy(bytes, 4, bodyBytes, 0, bodyBytes.length);
                                     String msgData = new String(bodyBytes, "GBK");

                                     // TODO ��ȡ�ļ�������ҵ��Ŷ�ȡ�ֻ����룬��msgData���͵��ֻ���
                                     // 0-���� 1-�ʼ� 2-΢��
                                     //0010�� SBSר�� �󱸷����  �����а���SMS��������
                                     //0011���������а����ֻ��ź���Ϣ����
                                     //0020��SBSר��  SBS�������
                                     if (bizCode.startsWith("0")) {
                                         // ���������ֻ���
                                         if (bizCode.equals("0011")) {
                                             int index = msgData.indexOf("|");
                                             String phones = msgData.substring(0, index);
                                             String msgContent = msgData.substring(index + 1);
                                             if (StringUtils.isEmpty(phones)) {
                                                 exchange.getOut().setBody("0000".getBytes());
                                             } else {
                                                 String[] phoneNums = phones.split(",");
                                                 for (String num : phoneNums) {
                                                     if (!StringUtils.isEmpty(num) && num.length() == 11) {
                                                         SmsTool.sendMessage(num, msgContent);
                                                     } else {
                                                         logger.info("������ֻ��ţ�" + num);
                                                     }
                                                 }
                                             }
                                         } else if (bizCode.equals("0010")) { // ������û���ֻ��ţ���Ҫ��ȡ�ֻ����б��ļ�
                                             String[] phoneNums = SmsTool.getPhoneNumberList(bizCode);
                                             logger.info("����ҵ�����ࣺ" + bizCode + phoneNums);
                                             if (phoneNums == null) {
                                                 exchange.getOut().setBody("0000".getBytes());
                                             }
                                             for (String num : phoneNums) {
                                                 SmsTool.sendMessage(num, msgData);
                                             }
                                         }else if (bizCode.equals("0020")) { //SBSר��  SBS�������
                                             new JmsTopicBytesClient().sendTopicMsg("topic.sbs.status", msgData);
                                         }else if (bizCode.equals("0030")) { //SBSר��  SBS֪ͨ
                                             new JmsTopicBytesClient().sendTopicMsg("topic.sbs.notice", msgData);
                                         }else{
                                             logger.error("���׺Ų�֧��" + bizCode);
                                             exchange.getOut().setBody("0000".getBytes());
                                         }
                                     }
                                     exchange.getOut().setBody(bizCode.getBytes());
                                 } catch (Exception e) {
                                     logger.error("��Ϣ�����쳣", e);
                                     exchange.getOut().setBody("0000".getBytes());
                                 }
                             }
                         }
                );
    }
}

package org.fbi.dep.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.fbi.endpoint.sbs.CtgManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SBS���״���
 */
public class Core900Processor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(Core900Processor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        Message inMessage = exchange.getIn();
        String msgID = inMessage.getHeader("JMSCorrelationID").toString();
        logger.info(msgID + "  --SBS���ı��Ĵ���--");

        byte[] bytesDatagram = (byte[]) inMessage.getBody();
        logger.info("��SBS���ͱ��ģ� " + new String(bytesDatagram).trim());
        long startM = System.currentTimeMillis();

        CtgManager ctgManager = new CtgManager();
        byte[] rtnBytesDatagram = ctgManager.processTxn(bytesDatagram);
        logger.info("��SBS����Ӧ���ģ� " + new String(rtnBytesDatagram).trim());
        long endM = System.currentTimeMillis();
        logger.info(msgID + " --SBS���״����ʱ--��" + (endM - startM) + " mm.");
        exchange.getOut().setHeader("JMSCorrelationID", msgID);
        exchange.getOut().setHeader("JMSX_APPID", inMessage.getHeader("JMSX_APPID"));
        exchange.getOut().setHeader("JMSX_CHANNELID", inMessage.getHeader("JMSX_CHANNELID"));
        exchange.getOut().setHeader("JMSX_SRCMSGFLAG", inMessage.getHeader("JMSX_SRCMSGFLAG"));
        // hanjianlong �˴���Ҫ���TX_CODE���׺ţ�����ִ������
        exchange.getOut().setHeader("REQ_TXN_CODE", inMessage.getHeader("REQ_TXN_CODE"));
        exchange.getOut().setBody(rtnBytesDatagram);
    }
}

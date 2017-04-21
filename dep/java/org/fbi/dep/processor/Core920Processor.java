package org.fbi.dep.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.fbi.endpoint.mbp.MbpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MBP���״���
 */
public class Core920Processor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(Core920Processor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        
        logger.info("------MBP ���ı��Ĵ���------");
        Message inMessage = exchange.getIn();
        byte[] msg = (byte[]) inMessage.getBody();
        logger.info("[MBP] ���������ݣ� " + msg);

        byte[] rtnMsg = new MbpClient().onSend(msg);

        exchange.getOut().setHeader("JMSCorrelationID", inMessage.getHeader("JMSCorrelationID"));
        exchange.getOut().setHeader("JMSX_APPID", inMessage.getHeader("JMSX_APPID"));
        exchange.getOut().setHeader("JMSX_CHANNELID", inMessage.getHeader("JMSX_CHANNELID"));
        exchange.getOut().setHeader("JMSX_SRCMSGFLAG", inMessage.getHeader("JMSX_SRCMSGFLAG"));
        exchange.getOut().setBody(rtnMsg);
    }
}

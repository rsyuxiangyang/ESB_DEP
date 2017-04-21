package org.fbi.dep.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.*;
import org.fbi.dep.transform.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by XIANGYANG on 2015-10-21.
 * “Ì÷ß∏∂
 */

public class TIA140Processor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(TIA140Processor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Object msgBody = exchange.getIn().getBody();
        String datagram = null;
        String bizId= (String) exchange.getIn().getHeader("JMSX_BIZID");
        // TODO ADD
        if (msgBody instanceof TIA1401001) {
            TIA1401001 tia1401001 = (TIA1401001) msgBody;
            exchange.getOut().setHeader("REQ_TXN_CODE", "1401001");
            datagram = new TIA1401001Transform().transform(tia1401001);
        }else if (msgBody instanceof TIA1403001) {
            TIA1403001 tia1403001 = (TIA1403001) msgBody;
            exchange.getOut().setHeader("REQ_TXN_CODE", "1403001");
            datagram = new TIA1403001Transform().transform(tia1403001);
        } else if (msgBody instanceof TIA1409001) {
            TIA1409001 tia1409001 = (TIA1409001) msgBody;
            exchange.getOut().setHeader("REQ_TXN_CODE", "1409001");
            datagram = new TIA1409001Transform().transform(tia1409001);
        } else {
            throw new RuntimeException("TxnCode not supported!");
        }
        exchange.getOut().setBody(datagram);

        String correlationID = exchange.getIn().getHeader("JMSCorrelationID", String.class);
        if (StringUtils.isEmpty(correlationID)) {
            exchange.getOut().setHeader("JMSCorrelationID", exchange.getIn().getMessageId());
            logger.info("TIA JMSCorrelationID : " + exchange.getIn().getMessageId());
        } else {
            exchange.getOut().setHeader("JMSCorrelationID", correlationID);
        }

        exchange.getOut().setHeader("JMSX_APPID", exchange.getIn().getHeader("JMSX_APPID"));
        exchange.getOut().setHeader("JMSX_BIZID", exchange.getIn().getHeader("JMSX_BIZID"));
        exchange.getOut().setHeader("JMSX_CHANNELID", exchange.getIn().getHeader("JMSX_CHANNELID"));
        exchange.getOut().setHeader("JMSX_SRCMSGFLAG", exchange.getIn().getHeader("JMSX_SRCMSGFLAG"));
    }
}

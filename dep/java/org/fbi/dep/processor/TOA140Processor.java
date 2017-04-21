package org.fbi.dep.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.fbi.dep.model.base.TOA;
import org.fbi.dep.transform.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by XIANGYANG on 2015-10-21.
 * “Ì÷ß∏∂
 */

public class TOA140Processor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(TOA140Processor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Message inMessage = exchange.getIn();
        exchange.getOut().setHeader("JMSCorrelationID", inMessage.getHeader("JMSCorrelationID"));

        exchange.getOut().setHeader("JMSX_APPID", inMessage.getHeader("JMSX_APPID"));
        exchange.getOut().setHeader("JMSX_SRCMSGFLAG", inMessage.getHeader("JMSX_SRCMSGFLAG"));
        String datagram = (String) inMessage.getBody();

        String txnCode = (String) inMessage.getHeader("JMSX_TXCODE");
        TOA toa = null;
        String reqTxnCode = null;
        switch (Integer.parseInt(txnCode)) {
            case 1401001:
                toa = new TOA1401001Transform().transform(datagram, txnCode);
                break;
            case 1403001:
                toa = new TOA1403001Transform().transform(datagram, txnCode);
                break;
            case 1409001:
                toa = new TOA1409001Transform().transform(datagram, txnCode);
                break;
            default:
                break;
        }
        exchange.getOut().setBody(toa);
    }
}

package org.fbi.dep.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.fbi.dep.helper.BestpayCryptHelper;
import org.fbi.dep.helper.DepHttpClient;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.bestpay.com.bestpay.rsa.RsaUtil;
import org.fbi.endpoint.bestpay.com.bestpay.service.CallFasClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付
 */

public class Core140Processor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(Core140Processor.class);
    private static boolean DEP_IS_RUNNING_DEBUG = !"0".equals(PropertyManager.getProperty("dep.running.debug"));

    @Override
    public void process(Exchange exchange) throws Exception {
        Message inMessage = exchange.getIn();

        String appID = (String) inMessage.getHeader("JMSX_APPID");
        String bizID = (String) inMessage.getHeader("JMSX_BIZID");
        String txnCode = (String) inMessage.getHeader("REQ_TXN_CODE");

        String datagram = inMessage.getBody(String.class);
        logger.info("翼支付业务处理报文：" + datagram);

        String platCodeStr="";
        String url="";
        if (DEP_IS_RUNNING_DEBUG) {
            url = PropertyManager.getProperty("test_bestpay_server_url_"+txnCode);
            platCodeStr=PropertyManager.getProperty("test_bestpay_platCode_" + bizID);
        } else {
            url = PropertyManager.getProperty("product_bestpay_server_url_"+txnCode);
            platCodeStr=PropertyManager.getProperty("product_bestpay_platCode_" + bizID);
        }
        String signJsonStr= BestpayCryptHelper.signMsgBest(datagram);
        String certStr= RsaUtil.getPublicKey();
        String reqJsonStr= "{"+
                           "\"platformCode\":\""+ platCodeStr +"\","+
                           "\"cert\":\""+ certStr +"\","+
                           "\"sign\":\""+ signJsonStr+"\","+
                           "\"data\":"+datagram+
                           "}";
        logger.info("发送翼支付报文："+reqJsonStr);
//        String rtnDatagram =new DepHttpClient().doPost(url, reqJsonStr, PropertyManager.getProperty("bestpay_http_charset_name"));
        String rtnDatagram =new CallFasClient().doPost(reqJsonStr,url);
        logger.info("接收翼支付报文："+rtnDatagram);
        exchange.getOut().setHeader("JMSCorrelationID", inMessage.getHeader("JMSCorrelationID"));
        exchange.getOut().setHeader("JMSX_APPID", appID);
        exchange.getOut().setHeader("JMSX_TXCODE", txnCode);
        exchange.getOut().setHeader("JMSX_CHANNELID", inMessage.getHeader("JMSX_CHANNELID"));
        exchange.getOut().setHeader("REQ_TXN_CODE", inMessage.getHeader("REQ_TXN_CODE"));
        exchange.getOut().setHeader("JMSX_SRCMSGFLAG", inMessage.getHeader("JMSX_SRCMSGFLAG"));
        exchange.getOut().setBody(rtnDatagram);
    }

    public static String getSubstrBetweenStrs(String fromStr, String startStr, String endStr) {
        int length = startStr.length();
        int start = fromStr.indexOf(startStr) + length;
        int end = fromStr.indexOf(endStr);
        return fromStr.substring(start, end);
    }
}

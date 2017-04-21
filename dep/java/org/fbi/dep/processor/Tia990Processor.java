package org.fbi.dep.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.fbi.dep.enums.EnuTaTxCode;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.transform.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ̩�������ʽ���
 * User: hanjianlong
 * Date: 2015-07-16
 */
public class Tia990Processor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(Tia990Processor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        
        logger.info("------���������� �����Ĵ���------");
        Message inMessage = exchange.getIn();
        String correlationID = inMessage.getHeader("JMSCorrelationID", String.class);
        if (StringUtils.isEmpty(correlationID)) {
            exchange.getOut().setHeader("JMSCorrelationID", inMessage.getMessageId());
            logger.info("AppRouteBuilder JMSCorrelationID : " + inMessage.getMessageId());
        } else {
            exchange.getOut().setHeader("JMSCorrelationID", correlationID);
        }
        exchange.getOut().setHeader("JMSX_APPID", inMessage.getHeader("JMSX_APPID"));
        exchange.getOut().setHeader("JMSX_CHANNELID", inMessage.getHeader("JMSX_CHANNELID"));
        exchange.getOut().setHeader("JMSX_SRCMSGFLAG", inMessage.getHeader("JMSX_SRCMSGFLAG"));

        String  msg = inMessage.getBody(String.class);
        logger.info("[RFM] ���������ݣ� " + msg.trim());
        String strMsgContent="";
        String strTxCode;
        TIA tiaTemp=(TIA) inMessage.getBody();
        strTxCode=tiaTemp.getHeader().TX_CODE;
        // �������
        if(EnuTaTxCode.TRADE_1001.getCode().equals(strTxCode)) {
            Tia9901001Transform tia9901001TransformTemp = new Tia9901001Transform();
            strMsgContent = tia9901001TransformTemp.transform(tiaTemp);
        // ������
        }else if(EnuTaTxCode.TRADE_1002.getCode().equals(strTxCode)){
            Tia9901002Transform tia9901002TransformTemp = new Tia9901002Transform();
            strMsgContent = tia9901002TransformTemp.transform(tiaTemp);
        // ������֤
        }else if(EnuTaTxCode.TRADE_2001.getCode().equals(strTxCode)){
            Tia9902001Transform tia9902001TransformTemp = new Tia9902001Transform();
            strMsgContent = tia9902001TransformTemp.transform(tiaTemp);
        // �������
        }else if(EnuTaTxCode.TRADE_2002.getCode().equals(strTxCode)){
            Tia9902002Transform tia9902002TransformTemp = new Tia9902002Transform();
            strMsgContent = tia9902002TransformTemp.transform(tiaTemp);
        // �������
        }else if(EnuTaTxCode.TRADE_2011.getCode().equals(strTxCode)){
            Tia9902011Transform tia9902011TransformTemp = new Tia9902011Transform();
            strMsgContent = tia9902011TransformTemp.transform(tiaTemp);
        // ������֤
        }else if(EnuTaTxCode.TRADE_2101.getCode().equals(strTxCode)){
            Tia9902101Transform tia9902101TransformTemp = new Tia9902101Transform();
            strMsgContent = tia9902101TransformTemp.transform(tiaTemp);
        // ��������
        }else if(EnuTaTxCode.TRADE_2102.getCode().equals(strTxCode)){
            Tia9902102Transform tia9902102TransformTemp = new Tia9902102Transform();
            strMsgContent = tia9902102TransformTemp.transform(tiaTemp);
        // ��������
        }else if(EnuTaTxCode.TRADE_2111.getCode().equals(strTxCode)){
            Tia9902111Transform tia9902111TransformTemp = new Tia9902111Transform();
            strMsgContent = tia9902111TransformTemp.transform(tiaTemp);
        // ������֤
        }else if(EnuTaTxCode.TRADE_2201.getCode().equals(strTxCode)){
            Tia9902201Transform tia9902201TransformTemp = new Tia9902201Transform();
            strMsgContent = tia9902201TransformTemp.transform(tiaTemp);
        // ��������
        }else if(EnuTaTxCode.TRADE_2202.getCode().equals(strTxCode)){
            Tia9902202Transform tia9902202TransformTemp = new Tia9902202Transform();
            strMsgContent = tia9902202TransformTemp.transform(tiaTemp);
        // ��������
        }else if(EnuTaTxCode.TRADE_2211.getCode().equals(strTxCode)){
            Tia9902211Transform tia9902211TransformTemp = new Tia9902211Transform();
            strMsgContent = tia9902211TransformTemp.transform(tiaTemp);
        // ���˽����ѯ
        }else if(EnuTaTxCode.TRADE_2501.getCode().equals(strTxCode)){
            Tia9902501Transform tia9902501TransformTemp = new Tia9902501Transform();
            strMsgContent = tia9902501TransformTemp.transform(tiaTemp);
        }
        exchange.getOut().setBody(strMsgContent);
    }
}

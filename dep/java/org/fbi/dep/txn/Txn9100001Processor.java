package org.fbi.dep.txn;

import com.thoughtworks.xstream.converters.ConversionException;
import org.fbi.dep.component.jms.JmsObjMsgClient;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.txn.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by hanjianlong on 2015-9-8.
 * FEB�������֪ͨ
 */

public class Txn9100001Processor extends AbstractTxnProcessor  {
    private static Logger logger = LoggerFactory.getLogger(Txn9100001Processor.class);

    public String process(String userid, String msgData) throws ClassNotFoundException,ConversionException, InstantiationException, IllegalAccessException, IOException {
        logger.error("����FEB�˷��͹����ı��ģ�"+msgData);
        TiaXml9100001 tia = (TiaXml9100001)( new TiaXml9100001().getTia(msgData));
        logger.error("ͨ�����Ķ���ת����RFMϵͳ");
        try {
            Object toa = new JmsObjMsgClient().sendRecivMsg("91001", tia.INFO.getTXNCODE(),"febdep",
                    "queue.dep.in.fcdep.object", "queue.dep.out.fcdep.object", tia);
            ToaXml9100001 toaXml9100001 = (ToaXml9100001) toa;
            return toaXml9100001.toString();
        } catch (Exception e) {
            logger.error("����FEB�˷��͹����Ķ���ִ�������쳣.", e);
            throw new RuntimeException(e);
        }
    }
}

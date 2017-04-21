package org.fbi.dep.txn;

import com.thoughtworks.xstream.converters.ConversionException;
import org.fbi.dep.component.jms.JmsObjMsgClient;
import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.base.ToaXmlHttpErr;
import org.fbi.dep.model.txn.TiaXml9101401;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhanrui on 2016-03-16.
 * 翼支付-签约
 */

public class Txn9101401Processor extends AbstractTxnProcessor {
    private static Logger logger = LoggerFactory.getLogger(Txn9101401Processor.class);

    public String process(String userid, String msgData) throws ClassNotFoundException, ConversionException, InstantiationException, IllegalAccessException, IOException {
        TiaXml9101401 tia = (TiaXml9101401) (new TiaXml9101401().getTia(msgData));

        // fip
        Object toa = null;
        try {
            toa = new JmsObjMsgClient().sendRecivMsg("910", "9101401", "fcdep",
                    "queue.dep.in.fcdep.object", "queue.dep.out.fcdep.object", tia);
        } catch (Exception e) {
            logger.error("FIP交易异常.", e);
            ToaXmlHttpErr toaXmlHttpErr = new ToaXmlHttpErr();
            copyTiaInfoToToa(tia, toaXmlHttpErr);
            toaXmlHttpErr.getInfo().setRtncode(TxnRtnCode.TXN_PROCESSED.getCode());
            toaXmlHttpErr.getInfo().setRtnmsg(TxnRtnCode.TXN_PROCESSED.getTitle());
            toaXmlHttpErr.getBody().setRtncode(TxnStatus.TXN_QRY_PEND.getCode());
            toaXmlHttpErr.getBody().setRtnmsg(TxnStatus.TXN_QRY_PEND.getTitle() + e.getMessage());
            toa = toaXmlHttpErr;
        }
        return toa.toString();
    }
}

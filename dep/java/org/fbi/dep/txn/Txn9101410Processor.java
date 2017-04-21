package org.fbi.dep.txn;

import com.thoughtworks.xstream.converters.ConversionException;
import org.fbi.dep.component.jms.JmsObjMsgClient;
import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.base.ToaXmlHttpErr;
import org.fbi.dep.model.txn.TiaXml9101104;
import org.fbi.dep.model.txn.TiaXml9101410;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付-实时代收入账
 */

public class Txn9101410Processor extends AbstractTxnProcessor  {
    private static Logger logger = LoggerFactory.getLogger(Txn9101410Processor.class);

    public String process(String userid, String msgData) throws ClassNotFoundException,ConversionException, InstantiationException, IllegalAccessException, IOException {
        TiaXml9101410 tia = (TiaXml9101410) (new TiaXml9101410().getTia(msgData));

        // fip
        Object toa = null;
        try {
            toa = new JmsObjMsgClient().sendRecivMsg("910", "9101410", "fcdep",
                    "queue.dep.in.fcdep.object", "queue.dep.out.fcdep.object", tia);
        } catch (Exception e) {
            logger.error("FIP交易异常.", e);
            ToaXmlHttpErr toaXmlHttpErr=new ToaXmlHttpErr();
            copyTiaInfoToToa(tia,toaXmlHttpErr);
            toaXmlHttpErr.getInfo().setRtncode(TxnRtnCode.TXN_PROCESSED.getCode());
            toaXmlHttpErr.getInfo().setRtncode(TxnRtnCode.TXN_PROCESSED.getTitle());
            toaXmlHttpErr.getBody().setRtncode(TxnStatus.TXN_QRY_PEND.getCode());
            toaXmlHttpErr.getBody().setRtnmsg(TxnStatus.TXN_QRY_PEND.getTitle());
            toa=toaXmlHttpErr;
        }

        return toa.toString();
    }
}

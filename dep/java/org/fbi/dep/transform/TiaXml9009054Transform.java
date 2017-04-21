package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.TiaXml9009054;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 9009054-查询总分账户余额
 * 对应SBS-8858交易
 */

public class TiaXml9009054Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009054Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009054 tia = (TiaXml9009054) (new TiaXml9009054().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxn8858For9009054(tia, termID);
        return bytes;
    }
}

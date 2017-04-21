package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.TiaXml9009050;
import org.fbi.dep.model.txn.TiaXml9009051;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hanjianlong on 2015-12-10.
 * SBS 9009050 -> SBS-8118½»Ò×
 */

public class TiaXml9009050Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009050Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009050 tia = (TiaXml9009050) (new TiaXml9009050().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxn8118For9009050(tia, termID);
        return bytes;
    }
}

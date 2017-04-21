package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.sbs.TiaXml9009503;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ֪ͨ���-����ͬʱ�Զ������˺Ž���9009503
 * (��ӦSBS-a27a����)
 */
public class TiaXml9009503Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009503Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009503 tia = (TiaXml9009503) (new TiaXml9009503().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxna27aFor9009503(tia, termID);
        return bytes;
    }
}

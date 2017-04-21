package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.TiaXml9009055;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 9009055-��ѯ�ܷ��˻��ϻ��²���ϸ
 * ��ӦSBS-8859����
 */

public class TiaXml9009055Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009055Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009055 tia = (TiaXml9009055) (new TiaXml9009055().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxn8859For9009055(tia, termID);
        return bytes;
    }
}

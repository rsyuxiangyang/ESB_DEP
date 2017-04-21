package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.sbs.TiaXml9009505;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ֪ͨ���-����֪ͨ9009505
 * (��ӦSBS-a121����)
 * �ý������ڳ���������������֪ͨ��Ƭ
 */
public class TiaXml9009505Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009505Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009505 tia = (TiaXml9009505) (new TiaXml9009505().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxna121For9009505(tia, termID);
        return bytes;
    }
}

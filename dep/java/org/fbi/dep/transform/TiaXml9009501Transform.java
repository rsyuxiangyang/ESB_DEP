package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.sbs.TiaXml9009501;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �����ʺſͻ�����-�˻���ͨ�����Ǽ�9009501
 * (��ӦSBS-8126����)�˻���ͨ�����Ǽ�������
 */
public class TiaXml9009501Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009501Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009501 tia = (TiaXml9009501) (new TiaXml9009501().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxn8126For9009501(tia, termID);
        return bytes;
    }
}

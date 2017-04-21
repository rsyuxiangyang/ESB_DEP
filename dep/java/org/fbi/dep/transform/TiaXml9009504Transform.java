package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.sbs.TiaXml9009501;
import org.fbi.dep.model.txn.sbs.TiaXml9009504;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ֪ͨ���-���֪ͨ9009504
 * (��ӦSBS-a111����)
 * ��������������֪ͨ�����ȡ����ǰ�����֪ͨ���ͻ�����֪ͨʱӦ��д֪ͨ��
 */
public class TiaXml9009504Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009504Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009504 tia = (TiaXml9009504) (new TiaXml9009504().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxna111For9009504(tia, termID);
        return bytes;
    }
}

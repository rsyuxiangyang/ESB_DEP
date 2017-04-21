package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.sbs.TiaXml9009507;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����֤����ѯ-֪ͨ������֪ͨ9009507
 * (��ӦSBS-a113����)
 * ��������������֪ͨ�����ȡ����ǰ�����֪ͨ���ͻ�����֪ͨʱӦ��д֪ͨ��
 */
public class TiaXml9009507Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009507Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009507 tia = (TiaXml9009507) (new TiaXml9009507().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxna113For9009507(tia, termID);
        return bytes;
    }
}

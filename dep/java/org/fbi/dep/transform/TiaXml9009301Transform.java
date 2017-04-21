package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.TiaXml9009060;
import org.fbi.dep.model.txn.TiaXml9009301;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
  �������˿ͻ���Ϣ ��ӦSBS-8011����
 */
public class TiaXml9009301Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009301Transform.class);

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009301 tia = (TiaXml9009301) (new TiaXml9009301().getTia(xml));
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] bytes = SbsTxnDataTransform.convertToTxn8011(tia, termID);
        return bytes;
    }
}

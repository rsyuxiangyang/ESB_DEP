package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.TiaXml9009201;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
SBS 9009201 -> aa41
�˽������ڶ���֧��ϵͳ���������Ѻ��㡣

����˾��������ʱ���ո���־Ϊ��F����������Ϊ��
�裺���ӻ㻮��֧�� 801090106006053001
         ��������������� 801090106001031001

����˾��������ʱ���ո���־Ϊ��S����������Ϊ��
�裺����������� 801090106001031001
         �������ӻ㻮��֧�� 801090106006053001

 */
public class TiaXml9009201Transform extends AbstractTiaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(TiaXml9009201Transform.class);
    // SBS���ӻ㻮��֧���˻�-�����˻�
    private static final String SBS_ACT_ELEC_EXCHANGE_EXPOSE = PropertyManager.getProperty("sbs.eeeact");
    // SBS �洢���������˻�-ͬҵ�˻�
    private static final String SBS_ACT_DEPOSIT_CENT_BANK = PropertyManager.getProperty("sbs.dcbact");

    @Override
    byte[] transform(String xml, String userid) {
        TiaXml9009201 tia = (TiaXml9009201) (new TiaXml9009201().getTia(xml));
        byte[] bytes = null;
        String termID = PropertyManager.getProperty("sbs.termid." + userid);
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        if ("F".equalsIgnoreCase(tia.BODY.SFFLAG.trim())) {
            bytes = SbsTxnDataTransform.convertToTxnAa41(tia.INFO.REQ_SN,
                    SBS_ACT_ELEC_EXCHANGE_EXPOSE, SBS_ACT_DEPOSIT_CENT_BANK,
                    tia.BODY.TXNAMT, termID, tia.BODY.REMARK);
        } else if ("S".equalsIgnoreCase(tia.BODY.SFFLAG.trim())) {
            bytes = SbsTxnDataTransform.convertToTxnAa41(tia.INFO.REQ_SN,
                    SBS_ACT_DEPOSIT_CENT_BANK, SBS_ACT_ELEC_EXCHANGE_EXPOSE,
                    tia.BODY.TXNAMT, termID, tia.BODY.REMARK);
        } else {
            throw new RuntimeException("����֧�������������ո���־��:" + tia.BODY.SFFLAG);
        }
        return bytes;
    }
}

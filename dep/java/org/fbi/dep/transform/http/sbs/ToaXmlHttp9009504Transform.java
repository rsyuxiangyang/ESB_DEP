package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.sbs.ToaXml9009504;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.re.T220;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * ֪ͨ���-���֪ͨ9009504
 * (��ӦSBS-a111����)
 * ��������������֪ͨ�����ȡ����ǰ�����֪ͨ���ͻ�����֪ͨʱӦ��д֪ͨ��
 */

public class ToaXmlHttp9009504Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009504Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009504 toa = new ToaXml9009504();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            DecimalFormat df = new DecimalFormat("#000000000000000.00");
            toa.getINFO().setRET_MSG(TxnStatus.TXN_SUCCESS.getTitle());
            SOFForm form = response.getSofForms().get(0);
            T220 t220 = (T220) form.getFormBody();
            toa.getBODY().setTELLER(t220.getTELLER()); // ��Ա����
            toa.getBODY().setTXNDAT(t220.getTXNDAT()); // ��������
            toa.getBODY().setACTTY(t220.getACTTY());   // �˻����
            toa.getBODY().setIPTAC(t220.getIPTAC());   // �ʺ�
            toa.getBODY().setADVDAT(t220.getADVDAT()); // ֪ͨ����
            toa.getBODY().setACTNAM(t220.getACTNAM()); // ����
            toa.getBODY().setINTCUR(t220.getINTCUR()); // �ұ�
            toa.getBODY().setTXNAMT(df.format(t220.getTXNAMT())); // ֪ͨ���
            toa.getBODY().setADVNUM(t220.getADVNUM()); // ֪ͨ����
            toa.getBODY().setREMARK(t220.getREMARK()); // ��ע
        } else {
            try {
                toa.getINFO().setRET_MSG(new String(SBSFormCode.valueOfAlias(response.getFormCodes().get(0)).getTitle().getBytes(), "GBK"));
                if (StringUtils.isEmpty(toa.getINFO().getRET_MSG())) {
                    toa.getINFO().setRET_MSG(TxnStatus.TXN_FAILED.getTitle());
                }
            }catch (NullPointerException e) {
                toa.getINFO().setRET_MSG(TxnStatus.TXN_FAILED.getTitle());
            } catch (UnsupportedEncodingException e) {
                logger.error("�ַ�������", e);
            }
        }
        toa.getINFO().setRET_CODE(formCode);
        return toa.toString();
    }
}

package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.sbs.ToaXml9009507;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.re.T555;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * ����֤����ѯ-֪ͨ������֪ͨ9009507
 * (��ӦSBS-a113����)
 * ��������������֪ͨ�����ȡ����ǰ�����֪ͨ���ͻ�����֪ͨʱӦ��д֪ͨ��
 */

public class ToaXmlHttp9009507Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009507Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009507 toa = new ToaXml9009507();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.getINFO().setRET_MSG(TxnStatus.TXN_SUCCESS.getTitle());
            SOFForm form = response.getSofForms().get(0);
            T555 t555 = (T555) form.getFormBody();
            toa.getBODY().setTOTCNT(t555.getFormBodyHeader().getTOTCNT());
            toa.getBODY().setCURCNT(t555.getFormBodyHeader().getCURCNT());
            toa.getBODY().setDETAILS(new ArrayList<ToaXml9009507.Body.BodyDetail>());
            for (T555.Bean bean : t555.getBeanList()) {
                ToaXml9009507.Body.BodyDetail detail = new ToaXml9009507.Body.BodyDetail();
                detail.setBOKNUM(bean.getBOKNUM());
                detail.setACTNUM(bean.getACTNUM());
                detail.setADVNAM(bean.getADVNAM());
                detail.setADVDAT(bean.getADVDAT().replace("-", ""));
                detail.setADVAMT(bean.getADVAMT());
                detail.setDCDDAT(bean.getDCDDAT().replace("-", ""));
                detail.setADVFLG(bean.getADVFLG());
                detail.setCNCDAT(bean.getCNCDAT().replace("-", ""));
                detail.setACTNAM(bean.getACTNAM());
                toa.getBODY().getDETAILS().add(detail);
            }
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

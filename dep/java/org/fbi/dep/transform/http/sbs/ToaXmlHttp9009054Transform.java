package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.model.txn.ToaXml9009054;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.ac.T125;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * 9009054-查询总分账户余额
 * 对应SBS-8858交易
 */

public class ToaXmlHttp9009054Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009054Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009054 toa = new ToaXml9009054();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.INFO.RET_MSG = TxnStatus.TXN_SUCCESS.getTitle();
            SOFForm form = response.getSofForms().get(0);
            T125 t125 = (T125) form.getFormBody();
            toa.BODY.TOTCNT = t125.getFormBodyHeader().getTOTCNT();
            toa.BODY.CURCNT = t125.getFormBodyHeader().getCURCNT();
            toa.BODY.DETAILS = new ArrayList<ToaXml9009054.Body.BodyDetail>();
            for (T125.Bean bean : t125.getBeanList()) {
                ToaXml9009054.Body.BodyDetail detail = new ToaXml9009054.Body.BodyDetail();
                detail.ACTNUM = bean.getACTNUM();
                detail.ACTNAM = bean.getACTNAM();
                detail.BOKBAL = bean.getBOKBAL();
                detail.LIMBAL = bean.getLIMBAL();
                detail.TXNAMT = bean.getTXNAMT();
                toa.BODY.DETAILS.add(detail);
            }
        } else {
            try {
                toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(response.getFormCodes().get(0)).getTitle().getBytes(), "GBK");
                if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
                    toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
                }
            } catch (NullPointerException e) {
                toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
            } catch (UnsupportedEncodingException e) {
                logger.error("字符集错误", e);
            }
        }
        toa.INFO.RET_CODE = formCode;
        return toa.toString();
    }
}

package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.model.txn.ToaXml9009055;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.ac.T126;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * 9009055-查询总分账户上划下拨明细
 * 对应SBS-8859交易
 */

public class ToaXmlHttp9009055Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009055Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009055 toa = new ToaXml9009055();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.INFO.RET_MSG = TxnStatus.TXN_SUCCESS.getTitle();
            SOFForm form = response.getSofForms().get(0);
            T126 t126 = (T126) form.getFormBody();
            toa.BODY.TOTCNT = t126.getFormBodyHeader().getTOTCNT();
            toa.BODY.CURCNT = t126.getFormBodyHeader().getCURCNT();
            toa.BODY.DETAILS = new ArrayList<ToaXml9009055.Body.BodyDetail>();
            for (T126.Bean bean : t126.getBeanList()) {
                ToaXml9009055.Body.BodyDetail detail = new ToaXml9009055.Body.BodyDetail();
                detail.ACTNUM = bean.getACTNUM();
                detail.TXNAMT = bean.getTXNAMT();
                detail.TXNTYP = bean.getTXNTYP();
                detail.OTHACT = bean.getOTHACT();
                detail.TLRNUM = bean.getTLRNUM();
                detail.VCHSET = bean.getVCHSET();
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

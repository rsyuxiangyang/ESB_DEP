package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.sbs.ToaXml9009501;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 根据帐号客户名称-账户开通网银登记9009501
 * (对应SBS-8126交易)
 */

public class ToaXmlHttp9009501Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009501Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009501 toa = new ToaXml9009501();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.getINFO().setRET_MSG(TxnStatus.TXN_SUCCESS.getTitle());
        } else {
            try {
                toa.getINFO().setRET_MSG(new String(SBSFormCode.valueOfAlias(response.getFormCodes().get(0)).getTitle().getBytes(), "GBK"));
                if (StringUtils.isEmpty(toa.getINFO().getRET_MSG())) {
                    toa.getINFO().setRET_MSG(TxnStatus.TXN_FAILED.getTitle());
                }
            }catch (NullPointerException e) {
                toa.getINFO().setRET_MSG(TxnStatus.TXN_FAILED.getTitle());
            } catch (UnsupportedEncodingException e) {
                logger.error("字符集错误", e);
            }
        }
        toa.getINFO().setRET_CODE(formCode);
        return toa.toString();
    }
}

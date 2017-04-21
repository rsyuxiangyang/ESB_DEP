package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.sbs.ToaXml9009503;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.re.T104;
import org.fbi.endpoint.sbs.model.form.re.T999;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * 通知存款-存入同时自动建立账号交易9009503
 * (对应SBS-a27a交易)
 */

public class ToaXmlHttp9009503Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009503Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009503 toa = new ToaXml9009503();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.getINFO().setRET_MSG(TxnStatus.TXN_SUCCESS.getTitle());
            SOFForm form = response.getSofForms().get(0);
            String strSbsRtnCodeTmp=response.getFormCodes().get(0);
            if ("T999".equalsIgnoreCase(strSbsRtnCodeTmp)) {
                T999 t999 = (T999) form.getFormBody();
                copyFormBodyToToa(t999, toa);
            } else {
                T104 t104 = (T104) form.getFormBody();
                DecimalFormat df = new DecimalFormat("#0000000000000.00");
                DecimalFormat df2 = new DecimalFormat("#000.000000");
                toa.getBODY().setCUSIDT(t104.getCUSIDT()); // 客户号
                toa.getBODY().setOURREF(t104.getOURREF()); // 摘要
                toa.getBODY().setTXNDAT(t104.getTXNDAT()); // 交易日期
                toa.getBODY().setORGNAM(t104.getORGNAM()); // 机构名
                toa.getBODY().setACTNAM(t104.getACTNAM()); // 户名
                toa.getBODY().setBOKNUM(t104.getBOKNUM()); // 帐户号
                toa.getBODY().setVALDAT(t104.getVALDAT()); // 开户日
                toa.getBODY().setEXPDAT(t104.getEXPDAT()); // 到期日
                toa.getBODY().setINTCUR(t104.getINTCUR()); // 币别
                toa.getBODY().setTXNAMT(df.format(Double.valueOf(t104.getTXNAMT())/100)); // 金额
                toa.getBODY().setDPTTYP(t104.getDPTTYP()); // 存款种类
                toa.getBODY().setDPTPRD(t104.getDPTPRD()); // 存期
                toa.getBODY().setINTRAT(df2.format(Double.valueOf(t104.getINTRAT())/1000000)); // 利率
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
                logger.error("字符集错误", e);
            }
        }
        toa.getINFO().setRET_CODE(formCode);
        return toa.toString();
    }
}

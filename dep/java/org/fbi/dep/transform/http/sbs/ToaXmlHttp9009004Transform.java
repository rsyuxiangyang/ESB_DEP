package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.ToaXml9009004;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.re.T531;
import org.fbi.endpoint.sbs.model.form.re.T999;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-4-2
 * Time: ����11:04
 * To change this template use File | Settings | File Templates.
 */
/*
sbs-��ת����תaa4b
 */
public class ToaXmlHttp9009004Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009004Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode =  SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormCodes().get(0));
        // bean -> txn bean
        ToaXml9009004 toa = new ToaXml9009004();

        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.INFO.RET_MSG = TxnStatus.TXN_SUCCESS.getTitle();
            SOFForm form = response.getSofForms().get(0);
            String strSbsRtnCodeTmp=response.getFormCodes().get(0);
            if("T531".equalsIgnoreCase(strSbsRtnCodeTmp)) {
                T531 t531 = (T531) form.getFormBody();
                copyFormBodyToToa(t531, toa);
                toa.INFO.REQ_SN = t531.getSECNUM();
            }
            else if ("T999".equalsIgnoreCase(strSbsRtnCodeTmp)) {
                T999 t999 = (T999) form.getFormBody();
                copyFormBodyToToa(t999, toa);
            }
        }else {
            try {
                toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(response.getFormCodes().get(0)).getTitle().getBytes(), "GBK");
                if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
                    toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
                }
            }catch (NullPointerException e) {
                toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
            } catch (UnsupportedEncodingException e) {
                logger.error("�ַ�������", e);
            }
        }
        toa.INFO.RET_CODE = formCode;
        return toa.toString();
    }
}

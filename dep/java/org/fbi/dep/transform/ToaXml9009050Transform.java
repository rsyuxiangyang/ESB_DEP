package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.model.txn.ToaXml9009050;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.ac.T108;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hanjianlong on 2015-12-10.
 * SBS 9009050 -> 8118 根据账号查询账户信息余额
 */

public class ToaXml9009050Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXml9009050Transform.class);

    public String transform(byte[] bytes) {
        FebResponse response = new FebResponse();
        response.init(bytes);
        String formCode = response.getFormCodes().get(0);
        // bean -> txn bean
        ToaXml9009050 toa = new ToaXml9009050();
        toa.INFO.RET_CODE = formCode;
        if ("T108".equalsIgnoreCase(formCode)) {
            toa.INFO.RET_MSG = "交易成功";
            SOFForm form = response.getSofForms().get(0);
            T108 t108 = (T108) form.getFormBody();
            toa.BODY.ACTNUM=t108.ACTNUM;
            toa.BODY.ACTNAM=t108.ACTNAM;
            toa.BODY.BOKBAL=t108.BOKBAL;
            toa.BODY.DDRAMT=t108.DDRAMT;
            toa.BODY.DCRAMT=t108.DCRAMT;
            toa.BODY.DDRCNT=t108.DDRCNT;
            toa.BODY.DCRCNT=t108.DCRCNT;
        } else {
            toa.INFO.RET_MSG = SBSFormCode.valueOfAlias(formCode).getTitle();
            if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
                toa.INFO.RET_MSG = "交易失败";
            }
        }
        return toa.toString();
    }
}

package org.fbi.dep.transform.http.sbs;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.ToaXml9009101;
import org.fbi.dep.transform.AbstractToaBytesTransform;
import org.fbi.endpoint.sbs.core.SBSResponse4SingleRecord;
import org.fbi.endpoint.sbs.txn.Tn080SOFDataDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
sbs-n080 ͬҵ����
 */
public class ToaXmlHttp9009101Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXmlHttp9009101Transform.class);

    public String transform(byte[] bytes) {
        SBSResponse4SingleRecord response = new SBSResponse4SingleRecord();
        Tn080SOFDataDetail sofDataDetail = new Tn080SOFDataDetail();
        response.setSofDataDetail(sofDataDetail);
        response.init(bytes);
        logger.info("[sbs]������:" + response.getFormcode() +
                " Form��Ϣ��" + response.getForminfo() +
                " sbs��ˮ��:" + sofDataDetail.getSEQNUM() +
                " ��Χϵͳ��ˮ��:" + sofDataDetail.getSECNUM());
        String formCode = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", response.getFormcode());
        // bean -> txn bean
        ToaXml9009101 toa = new ToaXml9009101();
        toa.INFO.REQ_SN = (StringUtils.isEmpty(sofDataDetail.getSECNUM()) ? "" : sofDataDetail.getSECNUM().trim());

        toa.BODY.TXNAMT = sofDataDetail.getTXNAMT();
        if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(formCode)) {
            toa.INFO.RET_MSG = TxnStatus.TXN_SUCCESS.getTitle();
        } else {
            try {
                if (!StringUtils.isEmpty(response.getForminfo())) {
                    toa.INFO.RET_MSG = response.getForminfo().trim();
                } else {
                    toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(response.getFormcode()).getTitle().getBytes(), "GBK");
                }
            } catch (NullPointerException e) {
                toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
            } catch (UnsupportedEncodingException e) {
                logger.error("�ַ�������", e);
            }
        }
        toa.INFO.RET_CODE = formCode;
        return toa.toString();
    }
}

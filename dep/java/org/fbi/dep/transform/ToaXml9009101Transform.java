package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.model.txn.ToaXml9009001;
import org.fbi.dep.model.txn.ToaXml9009101;
import org.fbi.endpoint.sbs.core.SBSResponse4SingleRecord;
import org.fbi.endpoint.sbs.txn.Tn080SOFDataDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
SBS-n080 ͬҵ����
 */
public class ToaXml9009101Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXml9009101Transform.class);

    public String transform(byte[] bytes) {

        SBSResponse4SingleRecord response = new SBSResponse4SingleRecord();
        Tn080SOFDataDetail sofDataDetail = new Tn080SOFDataDetail();
        response.setSofDataDetail(sofDataDetail);
        response.init(bytes);
        logger.info("[sbs]������:" + response.getFormcode() +
                " Form��Ϣ��" + response.getForminfo() +
                " sbs��ˮ��:" + sofDataDetail.getSEQNUM() +
                " ��Χϵͳ��ˮ��:" + sofDataDetail.getSECNUM());
        // bean -> txn bean
        ToaXml9009101 toa = new ToaXml9009101();
        toa.INFO.REQ_SN = (StringUtils.isEmpty(sofDataDetail.getSECNUM()) ? "" : sofDataDetail.getSECNUM().trim());
        toa.INFO.RET_CODE = response.getFormcode();
        toa.BODY.TXNAMT = sofDataDetail.getTXNAMT();
        if ("T531".equalsIgnoreCase(response.getFormcode())) {
            toa.INFO.RET_MSG = "���׳ɹ�";
        } else {
            try {
                if (!StringUtils.isEmpty(response.getForminfo())) {
                    toa.INFO.RET_MSG = response.getForminfo().trim();
                } else {
                    toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(response.getFormcode()).getTitle().getBytes(), "GBK");
                }
            } catch (NullPointerException e) {
                if (response.getFormcode().startsWith("T") || response.getFormcode().startsWith("t")) {
                    toa.INFO.RET_MSG = "���׳ɹ�";
                } else
                    toa.INFO.RET_MSG = SBSFormCode.OTHER.getTitle();
            } catch (UnsupportedEncodingException e) {
                logger.error("�ַ�������", e);
            }
        }
       /* if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
            if (response.getFormcode().startsWith("T") || response.getFormcode().startsWith("t")) {
                toa.INFO.RET_MSG = "���׳ɹ�";
            } else
                toa.INFO.RET_MSG = SBSFormCode.OTHER.getTitle();
        }*/
        return toa.toString();
    }
}

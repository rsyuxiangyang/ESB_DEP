package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.model.txn.ToaXml9009001;
import org.fbi.dep.model.txn.ToaXml9009201;
import org.fbi.endpoint.sbs.core.SBSResponse4SingleRecord;
import org.fbi.endpoint.sbs.txn.Taa41SOFDataDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * ����֧������������
 */
/*
SBS-��תaa41
 */
public class ToaXml9009201Transform extends AbstractToaBytesTransform {
    private static Logger logger = LoggerFactory.getLogger(ToaXml9009201Transform.class);

    public String transform(byte[] bytes) {

        SBSResponse4SingleRecord response = new SBSResponse4SingleRecord();
        Taa41SOFDataDetail sofDataDetail = new Taa41SOFDataDetail();
        response.setSofDataDetail(sofDataDetail);
        response.init(bytes);
        logger.info("[sbs]������:" + response.getFormcode() +
                " Form��Ϣ��" + response.getForminfo() +
                " sbs��ˮ��:" + sofDataDetail.getSEQNUM() +
                " ��Χϵͳ��ˮ��:" + sofDataDetail.getSECNUM());
        // bean -> txn bean
        ToaXml9009201 toa = new ToaXml9009201();
        toa.INFO.REQ_SN = (StringUtils.isEmpty(sofDataDetail.getSECNUM()) ? "" : sofDataDetail.getSECNUM().trim());
        toa.INFO.RET_CODE = response.getFormcode();
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

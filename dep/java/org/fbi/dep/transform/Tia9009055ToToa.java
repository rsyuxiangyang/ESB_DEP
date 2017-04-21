package org.fbi.dep.transform;

import org.fbi.dep.model.txn.ToaXml9009055;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 9009055-��ѯ�ܷ��˻��ϻ��²���ϸ
 * ��ӦSBS-8859����
 */
/*
��tia����toa
 */
public class Tia9009055ToToa extends AbstractTiaToToa {
    private static Logger logger = LoggerFactory.getLogger(Tia9009055ToToa.class);

    public String transform(String xml, String rtnCode, String errMsg) {
        ToaXml9009055 toa = new ToaXml9009055();
        toa.INFO.TXN_CODE = "9009055";
        toa.INFO.REQ_SN = getSubstrBetweenStrs(xml, "<REQ_SN>", "</REQ_SN>");
        toa.INFO.RET_CODE = rtnCode;
        toa.INFO.RET_MSG = errMsg;
        return toa.toString();
    }
}

package org.fbi.dep.transform;

import org.fbi.dep.model.txn.ToaXml9009052;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by XIANGYANG on 2015-5-26.
 */
/*
��tia����toa
 */
public class Tia9009052ToToa extends AbstractTiaToToa {
    private static Logger logger = LoggerFactory.getLogger(Tia9009052ToToa.class);

    public String transform(String xml, String rtnCode, String errMsg) {
        ToaXml9009052 toa = new ToaXml9009052();
        toa.INFO.TXN_CODE = "9009052";
        toa.INFO.REQ_SN = getSubstrBetweenStrs(xml, "<REQ_SN>", "</REQ_SN>");
        toa.INFO.RET_CODE = rtnCode;
        toa.INFO.RET_MSG = errMsg;
        return toa.toString();
    }
}

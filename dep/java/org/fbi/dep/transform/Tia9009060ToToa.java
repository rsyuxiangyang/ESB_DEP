package org.fbi.dep.transform;

import org.fbi.dep.model.txn.ToaXml9009060;
import org.fbi.dep.model.txn.ToaXml9009201;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-4-2
 * Time: ����11:04
 * To change this template use File | Settings | File Templates.
 */
/*
��tia����toa
 */
public class Tia9009060ToToa extends AbstractTiaToToa {
    private static Logger logger = LoggerFactory.getLogger(Tia9009060ToToa.class);

    public String transform(String xml, String rtnCode, String errMsg) {
        ToaXml9009060 toa = new ToaXml9009060();
        toa.INFO.TXN_CODE = "9009060";
        toa.INFO.REQ_SN = getSubstrBetweenStrs(xml, "<REQ_SN>", "</REQ_SN>");
        toa.INFO.RET_CODE = rtnCode;
        toa.INFO.RET_MSG = errMsg;
        return toa.toString();
    }
}

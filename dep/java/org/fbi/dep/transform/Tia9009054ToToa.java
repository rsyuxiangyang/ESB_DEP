package org.fbi.dep.transform;

import org.fbi.dep.model.txn.ToaXml9009054;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 9009054-查询总分账户余额
 * 对应SBS-8858交易
 */
/*
由tia生成toa
 */
public class Tia9009054ToToa extends AbstractTiaToToa {
    private static Logger logger = LoggerFactory.getLogger(Tia9009054ToToa.class);

    public String transform(String xml, String rtnCode, String errMsg) {
        ToaXml9009054 toa = new ToaXml9009054();
        toa.INFO.TXN_CODE = "9009054";
        toa.INFO.REQ_SN = getSubstrBetweenStrs(xml, "<REQ_SN>", "</REQ_SN>");
        toa.INFO.RET_CODE = rtnCode;
        toa.INFO.RET_MSG = errMsg;
        return toa.toString();
    }
}

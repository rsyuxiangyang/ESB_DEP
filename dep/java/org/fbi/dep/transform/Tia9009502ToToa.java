package org.fbi.dep.transform;

import org.fbi.dep.model.txn.sbs.ToaXml9009502;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��tia����toa
 */
public class Tia9009502ToToa extends AbstractTiaToToa {
    private static Logger logger = LoggerFactory.getLogger(Tia9009502ToToa.class);

    public String transform(String xml, String rtnCode, String errMsg) {
        ToaXml9009502 toa = new ToaXml9009502();
        toa.getINFO().setTXN_CODE("9009502");
        toa.getINFO().setREQ_SN(getSubstrBetweenStrs(xml, "<REQ_SN>", "</REQ_SN>"));
        toa.getINFO().setRET_CODE(rtnCode);
        toa.getINFO().setRET_MSG(errMsg);
        return toa.toString();
    }
}

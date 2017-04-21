package org.fbi.dep.management;

import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.model.ActList;
import org.fbi.dep.model.CheckResult;
import org.fbi.dep.model.txn.TiaXml9009054;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 9009054 8858查询总分账户余额-总公司账号白名单检查
 */
public class Txn9009054Checker extends TxnActChecker implements TxnChecker {

    private static Logger logger = LoggerFactory.getLogger(Txn9009054Checker.class);

    public void check(String userid, String txnCode, String reqMsg, CheckResult res) {
        if (res == null) res = new CheckResult(userid, txnCode);
        TiaXml9009054 tia = (TiaXml9009054) (new TiaXml9009054().getTia(reqMsg));
        ActList.Act actno = null;
        List<ActList.Act> acts = getActnoWhitelist("/ActList9009054.xml");
        for (ActList.Act record : acts) {
            if (record.actno.equals(tia.BODY.ACTNUM)) {
                actno = record;
                break;
            }
        }
        if (actno == null) {
            res.setResultCode(TxnRtnCode.TXN_CHECK_ERR.getCode());
            res.setResultMsg(TxnRtnCode.TXN_CHECK_ERR.getTitle());
            logger.info("校验未通过：[ACTNUM]" + tia.BODY.ACTNUM);
            throw new RuntimeException(TxnRtnCode.TXN_CHECK_ERR.getCode() + "|总公司账号校验未通过");
        }
    }

}

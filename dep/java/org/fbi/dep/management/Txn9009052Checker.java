package org.fbi.dep.management;

import org.fbi.dep.enums.TxnRtnCode;
import org.fbi.dep.model.ActList;
import org.fbi.dep.model.CheckResult;
import org.fbi.dep.model.txn.TiaXml9009052;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 9009052 8854查询账户分户账明细-总公司账号白名单检查
 */
public class Txn9009052Checker extends TxnActChecker implements TxnChecker {

    private static Logger logger = LoggerFactory.getLogger(Txn9009052Checker.class);

    public void check(String userid, String txnCode, String reqMsg, CheckResult res) {
        if (res == null) res = new CheckResult(userid, txnCode);
        TiaXml9009052 tia = (TiaXml9009052) (new TiaXml9009052().getTia(reqMsg));
        ActList.Act actno = null;
        List<ActList.Act> acts = getActnoWhitelist("/ActList9009054.xml");
        for (ActList.Act record : acts) {
            if (record.actno.equals(tia.BODY.ACTNO)) {
                actno = record;
                break;
            }
        }
        if (actno == null) {
            res.setResultCode(TxnRtnCode.TXN_CHECK_ERR.getCode());
            res.setResultMsg(TxnRtnCode.TXN_CHECK_ERR.getTitle());
            logger.info("校验未通过：[ACTNO]" + tia.BODY.ACTNO);
            throw new RuntimeException(TxnRtnCode.TXN_CHECK_ERR.getCode() + "|总公司账号校验未通过");
        }
    }

}

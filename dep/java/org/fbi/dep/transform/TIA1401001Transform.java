package org.fbi.dep.transform;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.TIA1401001;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasBankAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasEbkAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1401001Tia;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class TIA1401001Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        TIA1401001 tia1401001 = (TIA1401001) tia;
        String bizID=tia1401001.header.BIZ_ID;

        T1401001Tia t1401001Tia = new T1401001Tia();
        t1401001Tia.setReqIp(LOCAL_IP);
//        t1401001Tia.setPlatCode(PropertyManager.getProperty(preDebugFlag+"_bestpay_platCode_" + bizID));
        t1401001Tia.setReqSeq(tia1401001.header.REQ_SN);
        t1401001Tia.setReqTime(tia1401001.body.reqTime);
//        t1401001Tia.setCustCode(PropertyManager.getProperty(preDebugFlag+"_bestpay_custCode_" + bizID));
        t1401001Tia.setExtOrderSeq(tia1401001.body.extOrderSeq);
        t1401001Tia.setCurrencyCode("RMB");
        t1401001Tia.setAmount(tia1401001.body.amount);
        if (DEP_IS_RUNNING_DEBUG) {
            t1401001Tia.setPlatCode(PropertyManager.getProperty("test_bestpay_platCode_" + bizID));
            t1401001Tia.setCustCode(PropertyManager.getProperty("test_bestpay_custCode_" + bizID));
        } else {
            t1401001Tia.setPlatCode(PropertyManager.getProperty("product_bestpay_platCode_" + bizID));
            t1401001Tia.setCustCode(PropertyManager.getProperty("product_bestpay_custCode_" + bizID));
        }

        t1401001Tia.setSignId(tia1401001.body.signId);
        t1401001Tia.setAccountCode(tia1401001.body.accountcode);

/*
        FasBankAccount fasBankAccount=new FasBankAccount();
        fasBankAccount.setBankCode(tia1401001.body.bankCode);
        fasBankAccount.setCardType(tia1401001.body.cardType);
        fasBankAccount.setAccountCode(tia1401001.body.accountcode);
        fasBankAccount.setBankCardName(tia1401001.body.bankcardname);
        fasBankAccount.setCertNo(tia1401001.body.certno);
        fasBankAccount.setCertType(tia1401001.body.certtype);
        fasBankAccount.setAreaCode(tia1401001.body.areacode);
        fasBankAccount.setPerEntFlag("1");   //对公对私标识  0：对公 1：对私

        t1401001Tia.setPayerAccount(fasBankAccount);
*/

        FasEbkAccount fasEbkAccount=new FasEbkAccount();
        if (DEP_IS_RUNNING_DEBUG) {
            fasEbkAccount.setAccountCode(PropertyManager.getProperty("test_bestpay_accountCode_" + bizID));
            fasEbkAccount.setAccountName(PropertyManager.getProperty("test_bestpay_accountName_" + bizID));
        } else {
            fasEbkAccount.setAccountCode(PropertyManager.getProperty("product_bestpay_accountCode_" + bizID));
            fasEbkAccount.setAccountName(PropertyManager.getProperty("product_bestpay_accountName_" + bizID));
        }

        t1401001Tia.setPayeeAccount(fasEbkAccount);

        return ObjectJsonUtil.objectToJson(t1401001Tia);
    }
}

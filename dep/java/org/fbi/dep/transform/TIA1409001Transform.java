package org.fbi.dep.transform;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.TIA1409001;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasBankAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasEbkAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1409001Tia;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanrui on 2016-3-16.
 * 翼支付 签约
 */

public class TIA1409001Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        TIA1409001 tia1409001 = (TIA1409001) tia;
        String bizID=tia1409001.header.BIZ_ID;

        T1409001Tia t1409001Tia = new T1409001Tia();
        t1409001Tia.setReqIp(LOCAL_IP);
        t1409001Tia.setReqSeq(tia1409001.header.REQ_SN);
        t1409001Tia.setReqTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        t1409001Tia.setExtOrderSeq(tia1409001.header.REQ_SN);//TODO
        t1409001Tia.setCurrencyCode("RMB");
        if (DEP_IS_RUNNING_DEBUG) {
            t1409001Tia.setPlatCode(PropertyManager.getProperty("test_bestpay_platCode_" + bizID));
            t1409001Tia.setCustCode(PropertyManager.getProperty("test_bestpay_custCode_" + bizID));
        } else {
            t1409001Tia.setPlatCode(PropertyManager.getProperty("product_bestpay_platCode_" + bizID));
            t1409001Tia.setCustCode(PropertyManager.getProperty("product_bestpay_custCode_" + bizID));
        }


        FasBankAccount fasBankAccount=new FasBankAccount();
        fasBankAccount.setBankCode(tia1409001.body.bankCode);
        fasBankAccount.setCardType(tia1409001.body.cardType);
        fasBankAccount.setAccountCode(tia1409001.body.accountCode);
        fasBankAccount.setBankCardName(tia1409001.body.bankCardName);
        fasBankAccount.setCertNo(tia1409001.body.certNo);
        fasBankAccount.setCertType(tia1409001.body.certType);
        fasBankAccount.setAreaCode(tia1409001.body.areaCode);
        fasBankAccount.setPerEntFlag(tia1409001.body.perEntFlag);   //对公对私标识  0：对公 1：对私

        t1409001Tia.setBankAccount(fasBankAccount);

        FasEbkAccount fasEbkAccount=new FasEbkAccount();
        if (DEP_IS_RUNNING_DEBUG) {
            fasEbkAccount.setAccountCode(PropertyManager.getProperty("test_bestpay_accountCode_" + bizID));
            fasEbkAccount.setAccountName(PropertyManager.getProperty("test_bestpay_accountName_" + bizID));
        } else {
            fasEbkAccount.setAccountCode(PropertyManager.getProperty("product_bestpay_accountCode_" + bizID));
            fasEbkAccount.setAccountName(PropertyManager.getProperty("product_bestpay_accountName_" + bizID));
        }


        //
        t1409001Tia.setValidateType("01");
        t1409001Tia.setNetWorkNature(tia1409001.body.netWorkNature);
        t1409001Tia.setUserFullName(tia1409001.body.userFullName);
        t1409001Tia.setEbkType(tia1409001.body.ebkType);
        t1409001Tia.setPayeeName(tia1409001.body.payeeName);
        t1409001Tia.setNetWorkAreaCode(tia1409001.body.netWorkAreaCode);
        t1409001Tia.setArpType(tia1409001.body.arpType);

        return ObjectJsonUtil.objectToJson(t1409001Tia);
    }
}

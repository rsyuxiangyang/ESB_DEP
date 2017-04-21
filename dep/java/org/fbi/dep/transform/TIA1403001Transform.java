package org.fbi.dep.transform;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.TIA1401001;
import org.fbi.dep.model.txn.TIA1403001;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasBankAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasEbkAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1401001Tia;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1403001Tia;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class TIA1403001Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {

        TIA1403001 tia1403001 = (TIA1403001) tia;
        String bizID=tia1403001.header.BIZ_ID;

        T1403001Tia t1403001Tia = new T1403001Tia();
        t1403001Tia.setReqSeq(tia1403001.header.REQ_SN);
        if (DEP_IS_RUNNING_DEBUG) {
            t1403001Tia.setCustCode(PropertyManager.getProperty("test_bestpay_custCode_" + bizID));
            t1403001Tia.setPlatCode(PropertyManager.getProperty("test_bestpay_platCode_" + bizID));
        } else {
            t1403001Tia.setCustCode(PropertyManager.getProperty("product_bestpay_custCode_" + bizID));
            t1403001Tia.setPlatCode(PropertyManager.getProperty("product_bestpay_platCode_" + bizID));
        }
        t1403001Tia.setReqIp(LOCAL_IP);
        t1403001Tia.setOriginalSeq(tia1403001.body.originalSeq);

        return ObjectJsonUtil.objectToJson(t1403001Tia);
    }
}

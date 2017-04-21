package org.fbi.dep.transform;

import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.TOA1401001;
import org.fbi.dep.model.txn.TOA1403001;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1401001Toa;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1403001Toa;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class TOA1403001Transform extends AbstractToaTransform {
    @Override
    public TOA1403001 transform(String datagram, String txCode) {

        TOA1403001 toa1403001 = null;
        switch (Integer.parseInt(txCode)) {
            case 1403001:
                toa1403001 = get1403001RtnBean(datagram);
                break;
            default:
                break;
        }
        return toa1403001;
    }

    private TOA1403001 get1403001RtnBean(String message) {

        T1403001Toa t1403001Toa = (T1403001Toa) ObjectJsonUtil.jsonToObject(message,T1403001Toa.class);

        TOA1403001 toa1403001 = new TOA1403001();

        if ("000000".equals(t1403001Toa.data.code)){// 交易成功
            toa1403001.body.code=TxnStatus.TXN_SUCCESS.getCode();
            toa1403001.body.msg=TxnStatus.TXN_SUCCESS.getTitle();
        }else if ("011007".equals(t1403001Toa.data.code)||"040022".equals(t1403001Toa.data.code)){// 交易不明
            toa1403001.body.code=TxnStatus.TXN_QRY_PEND.getCode();
            toa1403001.body.msg=TxnStatus.TXN_QRY_PEND.getTitle();
        }else{// 交易失败
            toa1403001.body.code=TxnStatus.TXN_FAILED.getCode();
            toa1403001.body.msg="[" + t1403001Toa.data.code + "]" + t1403001Toa.data.msg;
        }
        return toa1403001;
    }
}

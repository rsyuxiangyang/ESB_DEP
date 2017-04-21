package org.fbi.dep.transform;

import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.TOA1409001;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1409001Toa;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

/**
 * Created by zhanrui on 2016-03-17.
 * 翼支付 签约
 */

public class TOA1409001Transform extends AbstractToaTransform {
    @Override
    public TOA1409001 transform(String datagram, String txCode) {

        TOA1409001 toa1409001 = null;
        switch (Integer.parseInt(txCode)) {
            case 1409001:
                toa1409001 = get1409001RtnBean(datagram);
                break;
            default:
                break;
        }
        return toa1409001;
    }

    private TOA1409001 get1409001RtnBean(String message) {

        T1409001Toa toaFromBestpay = (T1409001Toa) ObjectJsonUtil.jsonToObject(message,T1409001Toa.class);

        TOA1409001 toaToFip = new TOA1409001();

        if ("000000".equals(toaFromBestpay.data.code)){// 交易成功
            toaToFip.body.code=TxnStatus.TXN_SUCCESS.getCode();
            toaToFip.body.msg=TxnStatus.TXN_SUCCESS.getTitle();
            toaToFip.body.signId = toaFromBestpay.getData().getResult().signId;
            toaToFip.body.pgwSeq = toaFromBestpay.getData().getResult().pgwSeq;
        }else if ("011007".equals(toaFromBestpay.data.code)||"040022".equals(toaFromBestpay.data.code)){// 交易不明
            toaToFip.body.code=TxnStatus.TXN_QRY_PEND.getCode();
            toaToFip.body.msg=TxnStatus.TXN_QRY_PEND.getTitle();
        }else{// 交易失败
            toaToFip.body.code=TxnStatus.TXN_FAILED.getCode();
            toaToFip.body.msg="[" + toaFromBestpay.data.code + "]" + toaFromBestpay.data.msg;
        }
        return toaToFip;
    }
}

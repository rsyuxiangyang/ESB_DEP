package org.fbi.dep.transform;

import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.TOA1401001;
import org.fbi.endpoint.bestpay.com.bestpay.txn.domain.T1401001Toa;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class TOA1401001Transform extends AbstractToaTransform {
    @Override
    public TOA1401001 transform(String datagram, String txCode) {

        TOA1401001 toa1401001 = null;
        switch (Integer.parseInt(txCode)) {
            case 1401001:
                toa1401001 = get1401001RtnBean(datagram);
                break;
            default:
                break;
        }
        return toa1401001;
    }

    private TOA1401001 get1401001RtnBean(String message) {

        T1401001Toa toaFromBestpay = (T1401001Toa) ObjectJsonUtil.jsonToObject(message, T1401001Toa.class);

        TOA1401001 toaToFip = new TOA1401001();

        if ("000000".equals(toaFromBestpay.data.code)) {// 交易成功
            toaToFip.body.code = TxnStatus.TXN_SUCCESS.getCode();
            toaToFip.body.msg = TxnStatus.TXN_SUCCESS.getTitle();
            toaToFip.body.fasRdRespResult = toaFromBestpay.data.result.result;
            toaToFip.body.fasRdRespReserveDomain1 = toaFromBestpay.data.result.reserverDomain1;
            toaToFip.body.fasRdRespReserveDomain2 = toaFromBestpay.data.result.reserverDomain2;
        } else if ("011007".equals(toaFromBestpay.data.code) || "040022".equals(toaFromBestpay.data.code)) {// 交易不明
            toaToFip.body.code = TxnStatus.TXN_QRY_PEND.getCode();
            toaToFip.body.msg = TxnStatus.TXN_QRY_PEND.getTitle();
        } else {// 交易失败
            toaToFip.body.code = TxnStatus.TXN_FAILED.getCode();
            toaToFip.body.msg = "[" + toaFromBestpay.data.code + "]" + toaFromBestpay.data.msg;
        }
        return toaToFip;
    }
}

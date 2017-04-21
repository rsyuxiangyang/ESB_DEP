package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付-实时代扣入账
 */

public class TOA1401001 extends TOA implements Serializable {
    public Header header = new Header();
    public Body body = new Body();

    @Override
    public TOAHeader getHeader() {
        return header;
    }

    @Override
    public TOABody getBody() {
        return body;
    }

    //====================================================================
    public static class Header extends TOAHeader {
    }

    public static class Body extends TOABody {
        public String code;
        public String msg;
        public String fasRdRespResult;   //交易结果 成功时不为空 返回交易流水号
        public String fasRdRespReserveDomain1;   //预留域1 请求参数有值时返回
        public String fasRdRespReserveDomain2;   //预留域2 请求参数有值时返回
    }
}

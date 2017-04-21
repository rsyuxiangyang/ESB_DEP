package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;

/**
 * Created by zhanrui on 2016-3-16.
 * 与翼支付端接口
 * 翼支付-签约
 */

public class TOA1409001 extends TOA implements Serializable {
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
        public String signId;   //签约流水(签约Id)
        public String pgwSeq;   //签约扣一分钱 pgw返回的流水号
    }
}

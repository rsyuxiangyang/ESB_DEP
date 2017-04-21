package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付-交易综合查询
 */

public class TIA1403001 extends TIA {
    public  Header header = new Header();
    public  Body body = new Body();


    @Override
    public TIAHeader getHeader() {
        return  header;
    }

    @Override
    public TIABody getBody() {
        return  body;
    }

    //====================================================================
    public  static class Header extends TIAHeader {
    }

    public static class Body extends TIABody {
        public String originalSeq;            // 原请求流水号
    }
}

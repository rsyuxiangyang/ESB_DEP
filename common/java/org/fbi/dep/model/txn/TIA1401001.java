package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付-实时代扣入账
 */

public class TIA1401001 extends TIA {
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
        public String reqTime;            // 请求时间
        public String extOrderSeq;        // 外部系统订单号
        public String amount;             //交易金额
        public String bankCode;           //银行编码
        public String cardType;           //银行卡类型
        public String accountcode;        //银行卡号
        public String bankcardname;       //银行卡户名
        public String certno;             //证件号码
        public String certtype;           //证件类型
        public String areacode;           //银行区域码
        public String signId;            //签约ID
    }
}

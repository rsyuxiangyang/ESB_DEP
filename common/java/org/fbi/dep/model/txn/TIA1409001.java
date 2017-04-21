package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * Created by zhanrui
 * on 2016-3-16.
 * DEP与翼支付端接口
 * 翼支付-签约接口
 */

public class TIA1409001 extends TIA {
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
        public String bankCode;            //银行编码
        public String cardType;            //银行卡卡类型  1 :借记卡卡 2:信用卡(贷记卡) 4:存折 8:公司账户
        public String accountCode;         //银行卡号
        public String bankCardName;        //银行卡户名
        public String certNo;              //证件号码
        public String certType;            //证件类型
        public String openBankAddress;    //开户支行地址
        public String mobile;             //联系方式:移动号码
        public String areaCode;           //银行区域码
        public String perEntFlag;         //对公对私标识
        public String netWorkNature;
        public String userFullName;
        public String ebkType;
        public String payeeName;
        public String netWorkAreaCode;
        public String arpType;
    }
}

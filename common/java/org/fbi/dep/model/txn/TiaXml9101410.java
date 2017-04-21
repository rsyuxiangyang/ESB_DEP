package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlHttpInfo;

import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付-实时代收入账-请求报文
 */

@XStreamAlias("root")
public class TiaXml9101410 extends TiaXml {
    public TiaXmlHttpInfo info;
    public Body body;

    public TiaXmlHttpInfo getInfo() {
        return info;
    }

    public void setInfo(TiaXmlHttpInfo info) {
        this.info = info;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body implements Serializable {
        public String iouno;        //借据号
        public String poano;        //期次号
        public String custid;       //客户号
        public String province;     //省份
        public String city;         //城市
        public String amount;       //交易金额
        public String bankcode;     //银行编码
        public String cardtype;     //银行卡类型
        public String accountcode;  //银行卡号
        public String bankcardname; //银行卡户名
        public String certno;       //证件号码
        public String certtype;     //证件类型
        public String areacode;     //银行区域码
        public String signId;       //签约ID

        public String getIouno() {
            return iouno;
        }

        public void setIouno(String iouno) {
            this.iouno = iouno;
        }

        public String getPoano() {
            return poano;
        }

        public void setPoano(String poano) {
            this.poano = poano;
        }

        public String getCustid() {
            return custid;
        }

        public void setCustid(String custid) {
            this.custid = custid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBankcode() {
            return bankcode;
        }

        public void setBankcode(String bankcode) {
            this.bankcode = bankcode;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getAccountcode() {
            return accountcode;
        }

        public void setAccountcode(String accountcode) {
            this.accountcode = accountcode;
        }

        public String getBankcardname() {
            return bankcardname;
        }

        public void setBankcardname(String bankcardname) {
            this.bankcardname = bankcardname;
        }

        public String getCertno() {
            return certno;
        }

        public void setCertno(String certno) {
            this.certno = certno;
        }

        public String getCerttype() {
            return certtype;
        }

        public void setCerttype(String certtype) {
            this.certtype = certtype;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getSignId() {
            return signId;
        }

        public void setSignId(String signId) {
            this.signId = signId;
        }
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9101410.class);
        return (TiaXml9101410) xs.fromXML(xml);
    }
}

package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlHttpInfo;

import java.io.Serializable;

/**
 * Created by zhanrui on 2016-03-16.
 * 翼支付-签约处理-请求报文
 * DEP对外服务接口
 */

@XStreamAlias("root")
public class TiaXml9101401 extends TiaXml {
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

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getAccountCode() {
            return accountCode;
        }

        public void setAccountCode(String accountCode) {
            this.accountCode = accountCode;
        }

        public String getBankCardName() {
            return bankCardName;
        }

        public void setBankCardName(String bankCardName) {
            this.bankCardName = bankCardName;
        }

        public String getCertNo() {
            return certNo;
        }

        public void setCertNo(String certNo) {
            this.certNo = certNo;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public String getOpenBankAddress() {
            return openBankAddress;
        }

        public void setOpenBankAddress(String openBankAddress) {
            this.openBankAddress = openBankAddress;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getPerEntFlag() {
            return perEntFlag;
        }

        public void setPerEntFlag(String perEntFlag) {
            this.perEntFlag = perEntFlag;
        }

        public String getNetWorkNature() {
            return netWorkNature;
        }

        public void setNetWorkNature(String netWorkNature) {
            this.netWorkNature = netWorkNature;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public String getEbkType() {
            return ebkType;
        }

        public void setEbkType(String ebkType) {
            this.ebkType = ebkType;
        }

        public String getPayeeName() {
            return payeeName;
        }

        public void setPayeeName(String payeeName) {
            this.payeeName = payeeName;
        }

        public String getNetWorkAreaCode() {
            return netWorkAreaCode;
        }

        public void setNetWorkAreaCode(String netWorkAreaCode) {
            this.netWorkAreaCode = netWorkAreaCode;
        }

        public String getArpType() {
            return arpType;
        }

        public void setArpType(String arpType) {
            this.arpType = arpType;
        }
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9101401.class);
        return (TiaXml9101401) xs.fromXML(xml);
    }
}

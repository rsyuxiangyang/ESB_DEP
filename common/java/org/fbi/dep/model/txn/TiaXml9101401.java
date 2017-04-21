package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlHttpInfo;

import java.io.Serializable;

/**
 * Created by zhanrui on 2016-03-16.
 * ��֧��-ǩԼ����-������
 * DEP�������ӿ�
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
        public String bankCode;            //���б���
        public String cardType;            //���п�������  1 :��ǿ��� 2:���ÿ�(���ǿ�) 4:���� 8:��˾�˻�
        public String accountCode;         //���п���
        public String bankCardName;        //���п�����
        public String certNo;              //֤������
        public String certType;            //֤������
        public String openBankAddress;    //����֧�е�ַ
        public String mobile;             //��ϵ��ʽ:�ƶ�����
        public String areaCode;           //����������
        public String perEntFlag;         //�Թ���˽��ʶ

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

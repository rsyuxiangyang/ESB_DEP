package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;
import org.fbi.dep.model.base.ToaXmlHttpInfo;

import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-7-1.
 */

@XStreamAlias("root")
public class ToaXml910012001 extends ToaXml {
    public Info info = new Info();
    public Body Body = new Body();

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ToaXml910012001.Body getBody() {
        return Body;
    }

    public void setBody(ToaXml910012001.Body body) {
        Body = body;
    }

    public static class Info extends ToaXmlHttpInfo {
        public String bankbranchid;       //������
        public String bankoperid;         //��Ա���

        public String getBankbranchid() {
            return bankbranchid;
        }

        public void setBankbranchid(String bankbranchid) {
            this.bankbranchid = bankbranchid;
        }

        public String getBankoperid() {
            return bankoperid;
        }

        public void setBankoperid(String bankoperid) {
            this.bankoperid = bankoperid;
        }
    }

    public static class Body implements Serializable {
        public String rtncode;        //ҵ�����-���ش���
        public String rtnmsg;         //ҵ�����-������Ϣ
        public String accounttype;    //�ʻ���� 0����ܻ�
        public String tradeamt;       //������
        public String accountcode;    //���ר���˺�
        public String accountname;    //���ר������
        public String fdcserial;      //Ԥ���ʽ���ƽ̨��ˮ

        public String getRtncode() {
            return rtncode;
        }

        public void setRtncode(String rtncode) {
            this.rtncode = rtncode;
        }

        public String getRtnmsg() {
            return rtnmsg;
        }

        public void setRtnmsg(String rtnmsg) {
            this.rtnmsg = rtnmsg;
        }

        public String getAccounttype() {
            return accounttype;
        }

        public void setAccounttype(String accounttype) {
            this.accounttype = accounttype;
        }

        public String getTradeamt() {
            return tradeamt;
        }

        public void setTradeamt(String tradeamt) {
            this.tradeamt = tradeamt;
        }

        public String getAccountcode() {
            return accountcode;
        }

        public void setAccountcode(String accountcode) {
            this.accountcode = accountcode;
        }

        public String getAccountname() {
            return accountname;
        }

        public void setAccountname(String accountname) {
            this.accountname = accountname;
        }

        public String getFdcserial() {
            return fdcserial;
        }

        public void setFdcserial(String fdcserial) {
            this.fdcserial = fdcserial;
        }
    }

    @Override
    public String toString() {
        this.info.txncode = "2001";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml910012001.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml910012001 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml910012001.class);
        return (ToaXml910012001) xs.fromXML(xml);
    }
}

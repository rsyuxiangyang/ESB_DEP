package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;
import org.fbi.dep.model.base.ToaXmlInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 9009054-查询总分账户余额
 * 对应SBS-8858交易
 */

@XStreamAlias("ROOT")
public class ToaXml9009054 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String TOTCNT;       // 总记录数
        public String CURCNT;       // 本包内记录数
        public List<BodyDetail> DETAILS;

        @XStreamAlias("DETAIL")
        public static class BodyDetail implements Serializable {
            public String ACTNUM; // 账号
            public String ACTNAM; // 户名
            public String BOKBAL; // 余额
            public String LIMBAL; // 最低保留余额
            public String TXNAMT; // 归集金额

            public String getACTNUM() {
                return ACTNUM;
            }

            public void setACTNUM(String ACTNUM) {
                this.ACTNUM = ACTNUM;
            }

            public String getACTNAM() {
                return ACTNAM;
            }

            public void setACTNAM(String ACTNAM) {
                this.ACTNAM = ACTNAM;
            }

            public String getBOKBAL() {
                return BOKBAL;
            }

            public void setBOKBAL(String BOKBAL) {
                this.BOKBAL = BOKBAL;
            }

            public String getLIMBAL() {
                return LIMBAL;
            }

            public void setLIMBAL(String LIMBAL) {
                this.LIMBAL = LIMBAL;
            }

            public String getTXNAMT() {
                return TXNAMT;
            }

            public void setTXNAMT(String TXNAMT) {
                this.TXNAMT = TXNAMT;
            }
        }
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009054";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009054.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009054 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009054.class);
        return (ToaXml9009054) xs.fromXML(xml);
    }
}

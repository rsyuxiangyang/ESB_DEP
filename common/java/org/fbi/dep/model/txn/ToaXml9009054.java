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
 * 9009054-��ѯ�ܷ��˻����
 * ��ӦSBS-8858����
 */

@XStreamAlias("ROOT")
public class ToaXml9009054 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String TOTCNT;       // �ܼ�¼��
        public String CURCNT;       // �����ڼ�¼��
        public List<BodyDetail> DETAILS;

        @XStreamAlias("DETAIL")
        public static class BodyDetail implements Serializable {
            public String ACTNUM; // �˺�
            public String ACTNAM; // ����
            public String BOKBAL; // ���
            public String LIMBAL; // ��ͱ������
            public String TXNAMT; // �鼯���

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

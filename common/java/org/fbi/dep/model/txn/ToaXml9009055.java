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
 * 9009055-��ѯ�ܷ��˻��ϻ��²���ϸ
 * ��ӦSBS-8859����
 */

@XStreamAlias("ROOT")
public class ToaXml9009055 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String TOTCNT;       // �ܼ�¼��
        public String CURCNT;       // �����ڼ�¼��
        public List<BodyDetail> DETAILS;

        @XStreamAlias("DETAIL")
        public static class BodyDetail implements Serializable {
            public String ACTNUM; // ��������
            public String TXNAMT; // ���׽��
            public String TXNTYP; // �������
            public String OTHACT; // �Է��˺�
            public String TLRNUM; // ����Ա
            public String VCHSET; // ��Ʊ��

            public String getACTNUM() {
                return ACTNUM;
            }

            public void setACTNUM(String ACTNUM) {
                this.ACTNUM = ACTNUM;
            }

            public String getTXNAMT() {
                return TXNAMT;
            }

            public void setTXNAMT(String TXNAMT) {
                this.TXNAMT = TXNAMT;
            }

            public String getTXNTYP() {
                return TXNTYP;
            }

            public void setTXNTYP(String TXNTYP) {
                this.TXNTYP = TXNTYP;
            }

            public String getOTHACT() {
                return OTHACT;
            }

            public void setOTHACT(String OTHACT) {
                this.OTHACT = OTHACT;
            }

            public String getTLRNUM() {
                return TLRNUM;
            }

            public void setTLRNUM(String TLRNUM) {
                this.TLRNUM = TLRNUM;
            }

            public String getVCHSET() {
                return VCHSET;
            }

            public void setVCHSET(String VCHSET) {
                this.VCHSET = VCHSET;
            }
        }
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009055";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009055.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009055 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009055.class);
        return (ToaXml9009055) xs.fromXML(xml);
    }
}

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
 * ���ݿͻ�֤����Ϣ��ѯ�˻��б�-���ر����к��пͻ���
 */

@XStreamAlias("ROOT")
public class ToaXml9009061 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String TOTCNT;       // �ܼ�¼��
        public String CURCNT;       // �����ڼ�¼��
        public List<BodyDetail> DETAILS;

        @XStreamAlias("DETAIL")
        public static class BodyDetail implements Serializable {

            public String CUSIDT = "";       // �ͻ���
            public String CUSNAM = "";       // ����
            public String CUSACT = "";       // �˺�
            public String ACTTYP = "";       // �˻�����
            public String APCODE = "";       // ������


            public String getCUSACT() {
                return CUSACT;
            }

            public void setCUSACT(String CUSACT) {
                this.CUSACT = CUSACT;
            }

            public String getACTTYP() {
                return ACTTYP;
            }

            public void setACTTYP(String ACTTYP) {
                this.ACTTYP = ACTTYP;
            }

            public String getCUSIDT() {
                return CUSIDT;
            }

            public void setCUSIDT(String CUSIDT) {
                this.CUSIDT = CUSIDT;
            }

            public String getCUSNAM() {
                return CUSNAM;
            }

            public void setCUSNAM(String CUSNAM) {
                this.CUSNAM = CUSNAM;
            }

            public String getAPCODE() {
                return APCODE;
            }

            public void setAPCODE(String APCODE) {
                this.APCODE = APCODE;
            }
        }
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009061";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009061.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009061 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009061.class);
        return (ToaXml9009061) xs.fromXML(xml);
    }
}

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
 * ���ݿͻ�֤����Ϣ��ѯ�˻��б�
 */

@XStreamAlias("ROOT")
public class ToaXml9009060 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String CUSNAM;       // ����
        public String TOTCNT;       // �ܼ�¼��
        public String CURCNT;       // �����ڼ�¼��
        public List<BodyDetail> DETAILS;

        @XStreamAlias("DETAIL")
        public static class BodyDetail implements Serializable {
            public String CUSACT = "";       // �˺�
            public String ACTTYP = "";       // �˻�����
            public String BOKBAL = "";       // ���
            public String AVABAL = "";       // �������
            public String OPNDAT = "";       // ������
            public String VALDAT = "";       // ��Ϣ��
            public String DPTPRD = "";       // ��������
            public String EXPDAT = "";       // ������
            public String INTRAT = "";       // ����
            public String ACTSTS = "";       // ״̬  1-������0-����

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

            public String getBOKBAL() {
                return BOKBAL;
            }

            public void setBOKBAL(String BOKBAL) {
                this.BOKBAL = BOKBAL;
            }

            public String getAVABAL() {
                return AVABAL;
            }

            public void setAVABAL(String AVABAL) {
                this.AVABAL = AVABAL;
            }

            public String getOPNDAT() {
                return OPNDAT;
            }

            public void setOPNDAT(String OPNDAT) {
                this.OPNDAT = OPNDAT;
            }

            public String getVALDAT() {
                return VALDAT;
            }

            public void setVALDAT(String VALDAT) {
                this.VALDAT = VALDAT;
            }

            public String getDPTPRD() {
                return DPTPRD;
            }

            public void setDPTPRD(String DPTPRD) {
                this.DPTPRD = DPTPRD;
            }

            public String getEXPDAT() {
                return EXPDAT;
            }

            public void setEXPDAT(String EXPDAT) {
                this.EXPDAT = EXPDAT;
            }

            public String getINTRAT() {
                return INTRAT;
            }

            public void setINTRAT(String INTRAT) {
                this.INTRAT = INTRAT;
            }

            public String getACTSTS() {
                return ACTSTS;
            }

            public void setACTSTS(String ACTSTS) {
                this.ACTSTS = ACTSTS;
            }
        }
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009060";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009060.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009060 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009060.class);
        return (ToaXml9009060) xs.fromXML(xml);
    }
}

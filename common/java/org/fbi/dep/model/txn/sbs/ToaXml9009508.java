package org.fbi.dep.model.txn.sbs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;

import java.io.Serializable;

/**
 * ����֤����ѯ-�����Ϣ9009508
 * (��ӦSBS-����)
 */

@XStreamAlias("ROOT")
public class ToaXml9009508 extends ToaXml {
    private Info INFO = new Info();
    private Body BODY = new Body();

    public Info getINFO() {
        return INFO;
    }

    public void setINFO(Info INFO) {
        this.INFO = INFO;
    }

    public Body getBODY() {
        return BODY;
    }

    public void setBODY(Body BODY) {
        this.BODY = BODY;
    }

    public static class Info implements Serializable {
        private String TXN_CODE; // ���״��루�̶�ֵ��9009508��
        private String REQ_SN;   // ������ˮ��
        private String RET_CODE; // ���ش��루0000�����׳ɹ���1000������ʧ�ܣ�2000�����׽��������
        private String RET_MSG;  // ������Ϣ

        public String getTXN_CODE() {
            return TXN_CODE;
        }

        public void setTXN_CODE(String TXN_CODE) {
            this.TXN_CODE = TXN_CODE;
        }

        public String getREQ_SN() {
            return REQ_SN;
        }

        public void setREQ_SN(String REQ_SN) {
            this.REQ_SN = REQ_SN;
        }

        public String getRET_CODE() {
            return RET_CODE;
        }

        public void setRET_CODE(String RET_CODE) {
            this.RET_CODE = RET_CODE;
        }

        public String getRET_MSG() {
            return RET_MSG;
        }

        public void setRET_MSG(String RET_MSG) {
            this.RET_MSG = RET_MSG;
        }
    }

    public static class Body implements Serializable{
        private String BOKNUM; // �˻���
        private String ACTNUM; // �˺�
        private String ACTNAM; // ����
        private String CURBAL; // ��##############9.99��
        private String OPNDAT; // �����գ��̶����ȣ�yyyyMMdd��
        private String VALDAT; // ��Ϣ�գ��̶����ȣ�yyyyMMdd��
        private String EXPDAT; // �����գ��̶����ȣ�yyyyMMdd��
        private String DPTPRD; // ����
        private String ATOFLG; // �Զ�ת���־
        private String INTCDE; // ������
        private String INTRAT; // ���ʣ�##9.999999��
        private String DPTTYP; // �������
        private String VCHTYP; // ƾ֤����
        private String VCHNUM; // ƾ֤����
        private String PASTYP; // ֤������
        private String PASSNO; // ֤������
        private String DRAMDE; // ֧ȡ��ʽ
        private String ACTSTS; // ״̬��1-������0-���壩

        public String getBOKNUM() {
            return BOKNUM;
        }

        public void setBOKNUM(String BOKNUM) {
            this.BOKNUM = BOKNUM;
        }

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

        public String getCURBAL() {
            return CURBAL;
        }

        public void setCURBAL(String CURBAL) {
            this.CURBAL = CURBAL;
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

        public String getEXPDAT() {
            return EXPDAT;
        }

        public void setEXPDAT(String EXPDAT) {
            this.EXPDAT = EXPDAT;
        }

        public String getDPTPRD() {
            return DPTPRD;
        }

        public void setDPTPRD(String DPTPRD) {
            this.DPTPRD = DPTPRD;
        }

        public String getATOFLG() {
            return ATOFLG;
        }

        public void setATOFLG(String ATOFLG) {
            this.ATOFLG = ATOFLG;
        }

        public String getINTCDE() {
            return INTCDE;
        }

        public void setINTCDE(String INTCDE) {
            this.INTCDE = INTCDE;
        }

        public String getINTRAT() {
            return INTRAT;
        }

        public void setINTRAT(String INTRAT) {
            this.INTRAT = INTRAT;
        }

        public String getDPTTYP() {
            return DPTTYP;
        }

        public void setDPTTYP(String DPTTYP) {
            this.DPTTYP = DPTTYP;
        }

        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getVCHNUM() {
            return VCHNUM;
        }

        public void setVCHNUM(String VCHNUM) {
            this.VCHNUM = VCHNUM;
        }

        public String getPASTYP() {
            return PASTYP;
        }

        public void setPASTYP(String PASTYP) {
            this.PASTYP = PASTYP;
        }

        public String getPASSNO() {
            return PASSNO;
        }

        public void setPASSNO(String PASSNO) {
            this.PASSNO = PASSNO;
        }

        public String getDRAMDE() {
            return DRAMDE;
        }

        public void setDRAMDE(String DRAMDE) {
            this.DRAMDE = DRAMDE;
        }

        public String getACTSTS() {
            return ACTSTS;
        }

        public void setACTSTS(String ACTSTS) {
            this.ACTSTS = ACTSTS;
        }
    }

    @Override
    public String toString() {
        this.INFO.setTXN_CODE("9009508");
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009508.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009508 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009508.class);
        return (ToaXml9009508) xs.fromXML(xml);
    }
}

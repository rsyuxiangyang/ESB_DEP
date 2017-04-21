package org.fbi.dep.model.txn.sbs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;

import java.io.Serializable;

/**
 * ֪ͨ���-����ͬʱ�Զ������˺Ž���9009503
 * (��ӦSBS-����)
 */

@XStreamAlias("ROOT")
public class TiaXml9009503 extends TiaXml {
    private Info INFO;
    private Body BODY;

    public Body getBODY() {
        return BODY;
    }

    public void setBODY(Body BODY) {
        this.BODY = BODY;
    }

    public Info getINFO() {
        return INFO;
    }

    public void setINFO(Info INFO) {
        this.INFO = INFO;
    }

    public static class Info implements Serializable {
        private String TXN_CODE; // ���״��루�̶�ֵ��9009503��
        private String REQ_SN;   // ������ˮ��

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
    }

    public static class Body implements Serializable{
        private String CUSIDT; // �ͻ���
        private String ACTNM2; // ����
        private String PASTYP; // ֤������
        private String PASSNO; // ֤����
        private String CORADD; // ��ַ
        private String TELNUM; // �绰
        private String TXNAMT; // ���׽�##############9.99��
        private String VALDAT; // ��Ϣ����
        private String ACTTYP; // �˻����ͣ�00.����ң�08.��ң�
        private String DPTTYP; // �������
        private String DPTPRD; // ����
        private String ATOFLG; // �Զ�ת���־
        private String IPTAC1; // ת���˺�

        public String getCUSIDT() {
            return CUSIDT;
        }

        public void setCUSIDT(String CUSIDT) {
            this.CUSIDT = CUSIDT;
        }

        public String getACTNM2() {
            return ACTNM2;
        }

        public void setACTNM2(String ACTNM2) {
            this.ACTNM2 = ACTNM2;
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

        public String getCORADD() {
            return CORADD;
        }

        public void setCORADD(String CORADD) {
            this.CORADD = CORADD;
        }

        public String getTELNUM() {
            return TELNUM;
        }

        public void setTELNUM(String TELNUM) {
            this.TELNUM = TELNUM;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getACTTYP() {
            return ACTTYP;
        }

        public void setACTTYP(String ACTTYP) {
            this.ACTTYP = ACTTYP;
        }

        public String getDPTTYP() {
            return DPTTYP;
        }

        public void setDPTTYP(String DPTTYP) {
            this.DPTTYP = DPTTYP;
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

        public String getIPTAC1() {
            return IPTAC1;
        }

        public void setIPTAC1(String IPTAC1) {
            this.IPTAC1 = IPTAC1;
        }
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009503.class);
        return (TiaXml9009503) xs.fromXML(xml);
    }
}

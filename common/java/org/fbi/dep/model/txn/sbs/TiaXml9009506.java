package org.fbi.dep.model.txn.sbs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;

import java.io.Serializable;

/**
 * ֪ͨ���-֧ȡ����9009506
 * (��ӦSBS-a13a����)
 */

@XStreamAlias("ROOT")
public class TiaXml9009506 extends TiaXml {
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
        private String TXN_CODE; // ���״��루�̶�ֵ��9009506��
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
        private String ACTTY1; // �˺�����
        private String IPTAC1; // �˻���
        private String DRAMD1; // ȡ�ʽ
        private String CUSPW1; // �ͻ�����
        private String TXNDAT; // �������ڣ��̶�����yyyyMMdd��
        private String ADVNUM; // ֪ͨ���ţ���֪ͨ���ż�֪ͨ��û��֪ͨ���ż�û��֪ͨ��
        private String TXNAMT; // ���׽��
        private String ACTTY2; // ת���ʺ����ͣ�01.��λ���ڼ��ڲ��˺ţ�
        private String IPTAC2; // ת���ʺ�
        private String PASTYP; // ֤������
        private String PASSNO; // ֤������
        private String REMARK; // ժҪ
        private String ANACDE; // ����ͳ����
        private String MAGFL1; // ˢ���������ʺű�־
        private String MAGFL2; // �����ֶ�
        private String BOKNUM; // ��Χϵͳ��ˮ��

        public String getACTTY1() {
            return ACTTY1;
        }

        public void setACTTY1(String ACTTY1) {
            this.ACTTY1 = ACTTY1;
        }

        public String getIPTAC1() {
            return IPTAC1;
        }

        public void setIPTAC1(String IPTAC1) {
            this.IPTAC1 = IPTAC1;
        }

        public String getDRAMD1() {
            return DRAMD1;
        }

        public void setDRAMD1(String DRAMD1) {
            this.DRAMD1 = DRAMD1;
        }

        public String getCUSPW1() {
            return CUSPW1;
        }

        public void setCUSPW1(String CUSPW1) {
            this.CUSPW1 = CUSPW1;
        }

        public String getTXNDAT() {
            return TXNDAT;
        }

        public void setTXNDAT(String TXNDAT) {
            this.TXNDAT = TXNDAT;
        }

        public String getADVNUM() {
            return ADVNUM;
        }

        public void setADVNUM(String ADVNUM) {
            this.ADVNUM = ADVNUM;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getACTTY2() {
            return ACTTY2;
        }

        public void setACTTY2(String ACTTY2) {
            this.ACTTY2 = ACTTY2;
        }

        public String getIPTAC2() {
            return IPTAC2;
        }

        public void setIPTAC2(String IPTAC2) {
            this.IPTAC2 = IPTAC2;
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

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getANACDE() {
            return ANACDE;
        }

        public void setANACDE(String ANACDE) {
            this.ANACDE = ANACDE;
        }

        public String getMAGFL1() {
            return MAGFL1;
        }

        public void setMAGFL1(String MAGFL1) {
            this.MAGFL1 = MAGFL1;
        }

        public String getMAGFL2() {
            return MAGFL2;
        }

        public void setMAGFL2(String MAGFL2) {
            this.MAGFL2 = MAGFL2;
        }

        public String getBOKNUM() {
            return BOKNUM;
        }

        public void setBOKNUM(String BOKNUM) {
            this.BOKNUM = BOKNUM;
        }
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009506.class);
        return (TiaXml9009506) xs.fromXML(xml);
    }
}

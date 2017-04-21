package org.fbi.dep.model.txn.sbs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;

import java.io.Serializable;

/**
 * ֪ͨ���-����֪ͨ9009505
 * (��ӦSBS-a121����)
 * �ý������ڳ���������������֪ͨ��Ƭ
 */

@XStreamAlias("ROOT")
public class TiaXml9009505 extends TiaXml {
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
        private String TXN_CODE; // ���״��루�̶�ֵ��9009505��
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
        private String ACTTY2; // �˻����
        private String IPTAC2; // �˻���
        private String DRAMD2; // ȡ�ʽ
        private String CUSPW2; // �ͻ�����
        private String ADVNUM; // ֪ͨ����
        private String TXNDAT; // �������ڣ��̶�����yyyyMMdd��
        private String PASTYP; // ֤������
        private String PASSNO; // ֤����
        private String REMARK; // ��ע����ַ��
        private String MAGFL2; // �˺����뷽ʽ��0.���룬1.ˢ������

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

        public String getDRAMD2() {
            return DRAMD2;
        }

        public void setDRAMD2(String DRAMD2) {
            this.DRAMD2 = DRAMD2;
        }

        public String getCUSPW2() {
            return CUSPW2;
        }

        public void setCUSPW2(String CUSPW2) {
            this.CUSPW2 = CUSPW2;
        }

        public String getADVNUM() {
            return ADVNUM;
        }

        public void setADVNUM(String ADVNUM) {
            this.ADVNUM = ADVNUM;
        }

        public String getTXNDAT() {
            return TXNDAT;
        }

        public void setTXNDAT(String TXNDAT) {
            this.TXNDAT = TXNDAT;
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

        public String getMAGFL2() {
            return MAGFL2;
        }

        public void setMAGFL2(String MAGFL2) {
            this.MAGFL2 = MAGFL2;
        }
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009505.class);
        return (TiaXml9009505) xs.fromXML(xml);
    }
}

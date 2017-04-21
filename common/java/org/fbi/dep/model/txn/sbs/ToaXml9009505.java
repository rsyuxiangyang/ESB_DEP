package org.fbi.dep.model.txn.sbs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ֪ͨ���-����֪ͨ9009505
 * (��ӦSBS-a121����)
 * �ý������ڳ���������������֪ͨ��Ƭ
 */

@XStreamAlias("ROOT")
public class ToaXml9009505 extends ToaXml {
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
        private String TXN_CODE; // ���״��루�̶�ֵ��9009505��
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
        private String TELLER; // ��Ա����
        private String TXNDAT; // �������ڣ��̶�����yyyyMMdd��
        private String ACTTY;  // �˻����
        private String IPTAC;  // �ʺ�
        private String ADVDAT; // ֪ͨ���ڣ��̶�����yyyyMMdd��
        private String ACTNAM; // ����
        private String INTCUR; // �ұ�
        private String TXNAMT; // ֪ͨ���
        private String ADVNUM; // ֪ͨ����
        private String REMARK; // ��ע�����֪ͨ��

        public String getTELLER() {
            return TELLER;
        }

        public void setTELLER(String TELLER) {
            this.TELLER = TELLER;
        }

        public String getTXNDAT() {
            return TXNDAT;
        }

        public void setTXNDAT(String TXNDAT) {
            this.TXNDAT = TXNDAT;
        }

        public String getACTTY() {
            return ACTTY;
        }

        public void setACTTY(String ACTTY) {
            this.ACTTY = ACTTY;
        }

        public String getIPTAC() {
            return IPTAC;
        }

        public void setIPTAC(String IPTAC) {
            this.IPTAC = IPTAC;
        }

        public String getADVDAT() {
            return ADVDAT;
        }

        public void setADVDAT(String ADVDAT) {
            this.ADVDAT = ADVDAT;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }

        public String getINTCUR() {
            return INTCUR;
        }

        public void setINTCUR(String INTCUR) {
            this.INTCUR = INTCUR;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getADVNUM() {
            return ADVNUM;
        }

        public void setADVNUM(String ADVNUM) {
            this.ADVNUM = ADVNUM;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }
    }

    @Override
    public String toString() {
        this.INFO.setTXN_CODE("9009505");
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009505.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009505 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009505.class);
        return (ToaXml9009505) xs.fromXML(xml);
    }
}

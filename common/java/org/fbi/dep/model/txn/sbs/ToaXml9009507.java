package org.fbi.dep.model.txn.sbs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;

import java.io.Serializable;
import java.util.List;

/**
 * ����֤����ѯ-֪ͨ������֪ͨ9009507
 * (��ӦSBS-a113����)
 * ��������������֪ͨ�����ȡ����ǰ�����֪ͨ���ͻ�����֪ͨʱӦ��д֪ͨ��
 */

@XStreamAlias("ROOT")
public class ToaXml9009507 extends ToaXml {
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
        private String TXN_CODE; // ���״��루�̶�ֵ��9009507��
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
        private String TOTCNT; // �ܼ�¼��
        private String CURCNT; // �����ڼ�¼��

        private List<BodyDetail> DETAILS;

        public String getTOTCNT() {
            return TOTCNT;
        }

        public void setTOTCNT(String TOTCNT) {
            this.TOTCNT = TOTCNT;
        }

        public String getCURCNT() {
            return CURCNT;
        }

        public void setCURCNT(String CURCNT) {
            this.CURCNT = CURCNT;
        }

        public List<BodyDetail> getDETAILS() {
            return DETAILS;
        }

        public void setDETAILS(List<BodyDetail> DETAILS) {
            this.DETAILS = DETAILS;
        }

        @XStreamAlias("DETAIL")
        public static class BodyDetail implements Serializable {
            private String BOKNUM; // �˻���
            private String ACTNUM; // �˺�
            private String ADVNAM; // ֪ͨ����
            private String ADVDAT; // ֪ͨ����
            private String ADVAMT; // ֪ͨ���
            private String DCDDAT; // Э�������
            private String ADVFLG; // ֪ͨ״̬
            private String CNCDAT; // ����֪ͨ����
            private String ACTNAM; // ����

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

            public String getADVNAM() {
                return ADVNAM;
            }

            public void setADVNAM(String ADVNAM) {
                this.ADVNAM = ADVNAM;
            }

            public String getADVDAT() {
                return ADVDAT;
            }

            public void setADVDAT(String ADVDAT) {
                this.ADVDAT = ADVDAT;
            }

            public String getADVAMT() {
                return ADVAMT;
            }

            public void setADVAMT(String ADVAMT) {
                this.ADVAMT = ADVAMT;
            }

            public String getDCDDAT() {
                return DCDDAT;
            }

            public void setDCDDAT(String DCDDAT) {
                this.DCDDAT = DCDDAT;
            }

            public String getADVFLG() {
                return ADVFLG;
            }

            public void setADVFLG(String ADVFLG) {
                this.ADVFLG = ADVFLG;
            }

            public String getCNCDAT() {
                return CNCDAT;
            }

            public void setCNCDAT(String CNCDAT) {
                this.CNCDAT = CNCDAT;
            }

            public String getACTNAM() {
                return ACTNAM;
            }

            public void setACTNAM(String ACTNAM) {
                this.ACTNAM = ACTNAM;
            }
        }
    }

    @Override
    public String toString() {
        this.INFO.setTXN_CODE("9009507");
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009507.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009507 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009507.class);
        return (ToaXml9009507) xs.fromXML(xml);
    }
}

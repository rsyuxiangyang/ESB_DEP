package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanjianlong on 2015-8-10.
 * ��ѯ���˻����Ӧ����(����SBS  ���׺�8119��
 */

public class Toa900012701 extends TOA {
    public Header header = new Header();
    public Body body = new Body();

    @Override
    public TOAHeader getHeader() {
        return header;
    }

    @Override
    public TOABody getBody() {
        return body;
    }

    //====================================================================
    public static class Header extends TOAHeader {
    }

    public static class Body extends TOABody {
        public String TOTCNT;   //�ܼ�¼��
        public String CURCNT;   //�����ڼ�¼��
        public List<BodyDetail> DETAILS;
    }

    public static class BodyDetail implements Serializable {
        public String ACTNUM;          // �ʺ�
        public String ACTNAM;          // �ʻ���
        public String BOKBAL;          // �ʻ����
        public String AVABAL;          // ��Ч���
        public String FRZSTS;          // ����״̬
        public String ACTSTS;          // �ʻ�״̬
        public String RECSTS;          // ��¼״̬

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

        public String getAVABAL() {
            return AVABAL;
        }

        public void setAVABAL(String AVABAL) {
            this.AVABAL = AVABAL;
        }

        public String getFRZSTS() {
            return FRZSTS;
        }

        public void setFRZSTS(String FRZSTS) {
            this.FRZSTS = FRZSTS;
        }

        public String getACTSTS() {
            return ACTSTS;
        }

        public void setACTSTS(String ACTSTS) {
            this.ACTSTS = ACTSTS;
        }

        public String getRECSTS() {
            return RECSTS;
        }

        public void setRECSTS(String RECSTS) {
            this.RECSTS = RECSTS;
        }
    }
}

package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanjianlong on 2015-8-10.
 * �˻����ս�����ϸ��ѯ
 */
public class Toa900012602 extends TOA {
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
        /*01	��Χϵͳ��ˮ��
          02	���׽��
        */
        public String FLOFLG;   //������ʶ
        public String TOTCNT;   //�ܼ�¼��
        public String CURCNT;   //�����ڼ�¼��
        public List<BodyDetail> DETAILS;

        public static class BodyDetail implements Serializable {
            public String FBTIDX;          // ��Χϵͳ��ˮ��
            public String ACTNUM;          // �����˺�
            public String TXNAMT;          // ���׽������ʱ������
            public String INTAMT;          // ��Ϣ���
            public String FEEAMT;          // �����ѽ��
            public String BENACT;          // �Է��˺�
            public String ERYTIM;          // ����ʱ��

            public String getFBTIDX() {
                return FBTIDX;
            }

            public void setFBTIDX(String FBTIDX) {
                this.FBTIDX = FBTIDX;
            }

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

            public String getINTAMT() {
                return INTAMT;
            }

            public void setINTAMT(String INTAMT) {
                this.INTAMT = INTAMT;
            }

            public String getFEEAMT() {
                return FEEAMT;
            }

            public void setFEEAMT(String FEEAMT) {
                this.FEEAMT = FEEAMT;
            }

            public String getBENACT() {
                return BENACT;
            }

            public void setBENACT(String BENACT) {
                this.BENACT = BENACT;
            }

            public String getERYTIM() {
                return ERYTIM;
            }

            public void setERYTIM(String ERYTIM) {
                this.ERYTIM = ERYTIM;
            }
        }
    }
}

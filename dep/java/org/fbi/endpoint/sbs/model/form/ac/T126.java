package org.fbi.endpoint.sbs.model.form.ac;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 8859 ��ѯ�ܷ��˻��ϻ��²���ϸ
 */

public class T126 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(offset, buffer);
        int index = offset + 12;
        int beanLength = 54;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1};
            fieldLengths = new int[]{6, 6};
        }

        private String TOTCNT;   //�ܼ�¼��
        private String CURCNT;   //�����ڼ�¼��


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
    }


    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{10, 17, 1, 18, 4, 4};
        }

        private String ACTNUM;          // ��������
        private String TXNAMT;          // ���׽��
        private String TXNTYP;          // �������
        private String OTHACT;          // �Է��˺�
        private String TLRNUM;          // ����Ա
        private String VCHSET;          // ��Ʊ��

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

        public String getTXNTYP() {
            return TXNTYP;
        }

        public void setTXNTYP(String TXNTYP) {
            this.TXNTYP = TXNTYP;
        }

        public String getOTHACT() {
            return OTHACT;
        }

        public void setOTHACT(String OTHACT) {
            this.OTHACT = OTHACT;
        }

        public String getTLRNUM() {
            return TLRNUM;
        }

        public void setTLRNUM(String TLRNUM) {
            this.TLRNUM = TLRNUM;
        }

        public String getVCHSET() {
            return VCHSET;
        }

        public void setVCHSET(String VCHSET) {
            this.VCHSET = VCHSET;
        }
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }
}

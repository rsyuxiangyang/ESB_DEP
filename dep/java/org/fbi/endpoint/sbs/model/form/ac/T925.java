package org.fbi.endpoint.sbs.model.form.ac;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 *  8855 ���ݿͻ�֤����Ϣ��ѯ�˻��б�
 */
public class T925 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(offset,buffer);
        int index = offset + 72;
        int beanLength = 96;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1};
            fieldLengths = new int[]{60, 6, 6};
        }

        private String CUSNAM;   // ����
        private String TOTCNT;   //�ܼ�¼��
        private String CURCNT;   //�����ڼ�¼��


        public String getCUSNAM() {
            return CUSNAM;
        }

        public void setCUSNAM(String CUSNAM) {
            this.CUSNAM = CUSNAM;
        }

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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 1, 17, 17, 10, 10, 2, 10, 10, 1};
        }

        private String CUSACT;          // �˺�
        private String ACTTYP;          // �˻�����
        private String BOKBAL;          // ���
        private String AVABAL;          // �������
        private String OPNDAT;          // ������
        private String VALDAT;          // ��Ϣ��
        private String DPTPRD;          // ��������
        private String EXPDAT;          // ������
        private String INTRAT;          // ����
        private String ACTSTS;          // ״̬

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

    public List<Bean> getBeanList() {
        return beanList;
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }
}

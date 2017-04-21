package org.fbi.endpoint.sbs.model.form.re;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 *  a113 ֪ͨ���֪ͨ��Ϣ��ѯ
 */
public class T555 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(offset,buffer);
        int index = offset + 12;
        int beanLength = 171;
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
            fieldTypes = new int[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1 };
            fieldLengths = new int[]{ 18, 18, 16, 10, 16, 10, 1, 10, 72 };
        }

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

    public List<Bean> getBeanList() {
        return beanList;
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }
}

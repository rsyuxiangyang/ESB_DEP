package org.fbi.endpoint.sbs.model.form.ac;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 8119 ��ѯ���˻����Ӧ����
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-8-11
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class T106 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(offset,buffer);
        int index = offset + 12;
        int beanLength = 131;
        int i=0;
        int b=0;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
            b=++i;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{22, 72, 17, 17, 1, 1, 1};
        }

        private String ACTNUM;          // �ʺ�
        private String ACTNAM;          // �ʻ���
        private String BOKBAL;          // �ʻ����
        private String AVABAL;          // ��Ч���
        private String FRZSTS;          // ����״̬
        private String ACTSTS;          // �ʻ�״̬
        private String RECSTS;          // ��¼״̬

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

    public List<Bean> getBeanList() {
        return beanList;
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }
}

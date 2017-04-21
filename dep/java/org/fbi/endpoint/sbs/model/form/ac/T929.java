package org.fbi.endpoint.sbs.model.form.ac;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 8874 �ռ���ϸ����Ӧ����
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-8-11
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class T929 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(offset,buffer);
        int index = offset + 13;
        int beanLength = 129;
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
            fieldTypes = new int[]{1, 1, 1};
            fieldLengths = new int[]{1, 6, 6};
        }

        private String FLOFLG;   //������ʶ
        private String TOTCNT;   //�ܼ�¼��
        private String CURCNT;   //�����ڼ�¼��

        public String getFLOFLG() {
            return FLOFLG;
        }

        public void setFLOFLG(String FLOFLG) {
            this.FLOFLG = FLOFLG;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 22, 16, 16, 16, 35, 6};
        }

        private String FBTIDX;          // ��Χϵͳ��ˮ��
        private String ACTNUM;          // �����˺�
        private String TXNAMT;          // ���׽������ʱ������
        private String INTAMT;          // ��Ϣ���
        private String FEEAMT;          // �����ѽ��
        private String BENACT;          // �Է��˺�
        private String ERYTIM;          // ����ʱ��

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

    public List<Bean> getBeanList() {
        return beanList;
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }
}

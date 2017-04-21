package org.fbi.endpoint.sbs.model.form.ac;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 8859 查询总分账户上划下拨明细
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

        private String TOTCNT;   //总记录数
        private String CURCNT;   //本包内记录数


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

        private String ACTNUM;          // 交易日期
        private String TXNAMT;          // 交易金额
        private String TXNTYP;          // 交易类别
        private String OTHACT;          // 对方账号
        private String TLRNUM;          // 操作员
        private String VCHSET;          // 传票号

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

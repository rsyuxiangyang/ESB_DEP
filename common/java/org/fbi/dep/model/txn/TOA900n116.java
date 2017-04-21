package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * SBS: ����֧����������ϸ��ѯ
 * User: zhanrui
 * Date: 2012-02-14
 */
public class TOA900n116 extends TOA implements Serializable {
    public  Header header = new Header();
    public  Body body = new Body();

    @Override
    public TOAHeader getHeader() {
        return  header;
    }

    @Override
    public TOABody getBody() {
        return  body;
    }

    //====================================================================
    public  static class Header extends TOAHeader {
    }

    public static class Body extends TOABody {
        public String TOTCNT;          //�ܼ�¼��
        public String CURCNT;          //�����ڼ�¼��
        public List<BodyDetail> RET_DETAILS = new ArrayList<BodyDetail>();

        public static class BodyDetail implements Serializable{
            public String FBTIDX;      //��ˮ��
            public String CUSACT;      //�����˺�
            public String RETNAM;      //�����
            public String BENACT;      //�տ��˺�
            public String BENNAM;      //�տ��
            public String BENBNK;      //�տ�����
            public BigDecimal TXNAMT;  //���׽��
            public String RETAUX;      //��;
            public String CTLRSN;      //���ԭ��
            public String TLRNAM;      //��˹�Ա��
            public String TERMNM;      //��������
        }
    }
}

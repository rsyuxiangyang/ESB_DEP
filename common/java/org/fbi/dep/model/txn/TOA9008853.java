package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * SBS: �˻�������ϸ��ѯ
 * User: zhanrui
 * Date: 2012-08-08
 */
public class TOA9008853 extends TOA implements Serializable {
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
        public String FLOFLG;          //�������ı�ʶ X(1)  0-�޺�������1-�к�����
        public String TOTCNT;          //�ܼ�¼�� X(6)   ��6λǰ��0
        public String CURCNT;          //�����ڼ�¼�� X(6)   ��6λǰ��0
        public List<BodyDetail> RET_DETAILS = new ArrayList<BodyDetail>();

        //��Щ��¼����ظ�ѭ��150�Ρ�
        //���������ʶΪ0���򱾰������150����¼�����������ʶΪ1���򱾰��϶�Ϊ150����¼
        public static class BodyDetail implements Serializable{
            public String CUSIDT;      //�ͻ���   X(7)
            public String APCODE;      //������   X(4)
            public String CURCDE;      //���Һ�   X(3)
            public String STMDAT;      //��������  X(8) YYYYMMDD
            public String TLRNUM;      //���׹�Ա  X(4)
            public String VCHSET;      //��Ʊ�׺�  X(4)   ��4λǰ��0
            public String SETSEQ;      //��Ʊ����˳��� X(2) ��2λǰ��0
            public String TXNAMT;      //���׽��     X(21)   �Ҷ��룬�󲹿ո� -Z,ZZZ,ZZZ,ZZZ,ZZ9.99
            public String LASBAL;      //���׺����   X(21)   �Ҷ��룬�󲹿ո� -Z,ZZZ,ZZZ,ZZZ,ZZ9.99
            public String FURINF;      //ժҪ   X(32)
        }
    }
}

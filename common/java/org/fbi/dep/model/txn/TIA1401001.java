package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * Created by XIANGYANG on 2015-10-21.
 * ��֧��-ʵʱ��������
 */

public class TIA1401001 extends TIA {
    public  Header header = new Header();
    public  Body body = new Body();


    @Override
    public TIAHeader getHeader() {
        return  header;
    }

    @Override
    public TIABody getBody() {
        return  body;
    }

    //====================================================================
    public  static class Header extends TIAHeader {
    }

    public static class Body extends TIABody {
        public String reqTime;            // ����ʱ��
        public String extOrderSeq;        // �ⲿϵͳ������
        public String amount;             //���׽��
        public String bankCode;           //���б���
        public String cardType;           //���п�����
        public String accountcode;        //���п���
        public String bankcardname;       //���п�����
        public String certno;             //֤������
        public String certtype;           //֤������
        public String areacode;           //����������
        public String signId;            //ǩԼID
    }
}

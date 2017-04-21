package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ�������˵�SBS
 * User: hanjianlong
 * Date: 2015-07-16
 */

public class Tia900010002 extends TIA implements Serializable {
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
        /*
          01    ��Χϵͳ��ˮ��
          02    �����˺�
          03	��������
          04	����ʱ��
          05    �տ��˺�
          06	���׽��*/
        public String OUT_ACC_ID;    // �����˺�
        public String TX_DATE;         // ��������
        public String TX_TIME;         // ����ʱ��
        public String IN_ACC_ID;       // �տ��˺�
        public String TX_AMT;          // ���׽��
        public String BANK_BRANCH_ID; // ���׽��
    }
}

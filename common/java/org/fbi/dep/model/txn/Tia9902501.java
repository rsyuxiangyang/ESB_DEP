package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ����˽����ѯ
 * User: hanjianlong
 * Date: 2015-07-16
 */

public class Tia9902501 extends TIA implements Serializable {
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
        /*01	���״���	    4	2102
          02	������д���	2
          03	���д���	    6
          04	ҵ����	    14 ��������� ����ҵ���� �˻�ҵ����
          05	��ѯ����	    30
          06	��ѯ��Ա	    30
          07	����	        1 1_�������*/
        public String SPVSN_BANK_ID;     // ������д���	  2
        public String CITY_ID;            // ���д���	      6
        public String BRANCH_ID;          // �����         30
        public String INITIATOR;          // ����         1   1_�������
    }
}

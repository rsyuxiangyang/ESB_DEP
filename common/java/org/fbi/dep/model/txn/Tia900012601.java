package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ���SBS��ѯ���ռ�����Ϣ
 * User: hanjianlong
 * Date: 2015-07-16
 */

public class Tia900012601 extends TIA implements Serializable {
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
          04	����ʱ��*/
        public String TX_DATE; // ��������
    }
}

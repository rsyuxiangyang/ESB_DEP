package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ��������
 * User: hanjianlong
 * Date: 2015-07-16
 */

public class Tia9902002 extends TIA implements Serializable {
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
        /*01	���״���	    4	2002
          02	������д���	2
          03	���д���	    6
          04	����������	14
          05	������	    20	2001���׽�����֤���ص�ʵ�ɽ�2003�����޷�����ɹ�Ա¼�롣
          06	����˺�	    30	������֤�������
          07	���㷽ʽ	    2	01_ �ֽ� 02_ ת�� 03_ ֧Ʊ
          08	֧Ʊ����	    30
          09	���м�����ˮ	30
          10	��������	    10	��ϵͳ���ڼ���
          11	��������	    30
          12	������Ա	    30
          13	����	        1	1_�������*/
        public String SPVSN_BANK_ID;     // ������д���	  2
        public String CITY_ID;            // ���д���	      6
        public String BRANCH_ID;          // �����         30
        public String INITIATOR;          // ����         1   1_�������
        public String TX_AMT;             // 05	������	    20	2001���׽�����֤���ص�ʵ�ɽ�2003�����޷�����ɹ�Ա¼�롣
        public String SPVSN_ACC_ID;      // 06	����˺�	    30	������֤�������
        public String STL_TYPE;           // 07   ���㷽ʽ	    2	01_ �ֽ� 02_ ת�� 03_ ֧Ʊ
        public String CHECK_ID;           // 08   ֧Ʊ����	    30
        public String TX_DATE;            // 09   ����           10  ��ϵͳ���ڼ���
    }
}

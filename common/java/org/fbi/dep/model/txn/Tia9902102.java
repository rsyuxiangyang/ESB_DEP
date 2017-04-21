package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ���������
 * User: hanjianlong
 * Date: 2015-07-16
 */

public class Tia9902102 extends TIA implements Serializable {
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
          04	����ҵ����	14
          05	��������	    32	MD5
          06	����˺�	    30	������֤�������
          07	�տλ�˺�	30	������֤�������
          08	�����ʽ�	    20	������֤�������
          09	���㷽ʽ	    2	01_ �ֽ� 02_ ת�� 03_ ֧Ʊ
          10	֧Ʊ����	    30
          11	���м�����ˮ	30
          12	��������	    10	��ϵͳ���ڼ���
          13	��������	    30
          14	������Ա	    30
          15	����	        1	1_�������*/
        public String SPVSN_BANK_ID;     // ������д���	  2
        public String CITY_ID;            // ���д���	      6
        public String BRANCH_ID;          // �����         30
        public String INITIATOR;          // ����         1   1_�������
        public String SPVSN_ACC_ID;      // 06	����˺�	    30	������֤�������
        public String GERL_ACC_ID;       // 07	�տλ�˺�	30	������֤�������
        public String TX_AMT;             // 08	�����ʽ�	    20	������֤�������
        public String STL_TYPE;           // 09   ���㷽ʽ	    2	01_ �ֽ� 02_ ת�� 03_ ֧Ʊ
        public String CHECK_ID;           // 10   ֧Ʊ����	    30
        public String TX_DATE;            // ����           10  ��ϵͳ���ڼ���
    }
}

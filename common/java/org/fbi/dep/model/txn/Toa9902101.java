package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ�������֤
 * User: hanjianlong
 * Date: 2015-07-16
 */
public class Toa9902101 extends TOA implements Serializable {
    public Header header = new Header();
    public Body body = new Body();

    @Override
    public TOAHeader getHeader() {
        return header;
    }

    @Override
    public TOABody getBody() {
        return body;
    }

    //====================================================================
    public static class Header extends TOAHeader {
    }

    public static class Body extends TOABody {
        /*01	���	                4   0000��ʾ�ɹ�
          02	����˺�                30
          03    ����˻�����            150
          04	�������	            20  �Է�Ϊ��λ
          05	�տ�����	            90
          06	�տλ�˺�	        30
          07	�տλ����	        150
          08	��Ŀ����	            128
          09	������ҵ����	        255
          10    Ԥ���ʽ���ƽ̨��ˮ    16
        */
        public String SPVSN_ACC_ID;             // 02	����˺�        30
        public String SPVSN_ACC_NAME;           // 03	����˻�����    150
        public String TX_AMT;             // 04   �������	    20  �Է�Ϊ��λ
        public String GERL_BANK;          // 05   �տ�����       90
        public String GERL_ACC_ID;       // 06	�տλ�˺�	30
        public String GERL_ACC_NAME;     // 07   �տλ����	150
        public String PROG_NAME;         // 08   ��Ŀ����        128
        public String COMP_NAME;         // 09   ������ҵ����    255
    }
}

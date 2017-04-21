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
public class Toa9902201 extends TOA implements Serializable {
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
          05	ҵ������	            80
          06	֤������    	        30
          07	֤������                255
          08    Ԥ���ʽ���ƽ̨��ˮ    16
        */
        public String SPVSN_ACC_ID;           // 02	����˺�     30
        public String SPVSN_ACC_NAME;        // 03	����˻����� 150
        public String TX_AMT;          // 04   �������	 20  �Է�Ϊ��λ
        public String OWNER_NAME;     // 05   ҵ������     80
        public String CTFIC_TYPE;     // 06   ֤������	 30
        public String CTFIC_ID;       // 07   ֤������ 	 255
    }
}

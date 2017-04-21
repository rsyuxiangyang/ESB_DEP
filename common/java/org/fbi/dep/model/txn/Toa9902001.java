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
public class Toa9902001 extends TOA implements Serializable {
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
          02	�ʻ����	            1   0����ܻ���
          03	������	            20  �Է�Ϊ��λ
          04	���ר���˺�            30
          05    ���ר������            150
          06    Ԥ���ʽ���ƽ̨��ˮ    16
        */
        public String SPVSN_ACC_TYPE;   // �ʻ����       1  0��Ԥ�ۼ�ܻ�
        public String TX_AMT;             // ������	      20  �Է�Ϊ��λ
        public String SPVSN_ACC_ID;      // ���ר���˺�   30
        public String SPVSN_ACC_NAME;    // ���ר������   150
    }
}

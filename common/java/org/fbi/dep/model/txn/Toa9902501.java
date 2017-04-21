package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ����˽����ѯ
 * User: hanjianlong
 * Date: 2015-07-16
 */
public class Toa9902501 extends TOA implements Serializable {
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
          02	���˽��	            1   0_�ɹ� 1_ʧ��
          03	Ԥ���ʽ���ƽ̨������ˮ16  ҵ�������ˮ
          04	������м�����ˮ	    30  ҵ�������ˮ
          05	Ԥ���ʽ���ƽ̨��ˮ	16
        */
        public String SPVSN_ACT_RSTL;          // 02  ���˽��	                 1   0_�ɹ� 1_ʧ��
        public String FDC_ACT_SN;        // 03  Ԥ���ʽ���ƽ̨������ˮ  16  ҵ�������ˮ
        public String FDC_BANK_ACT_SN;  // 04  ������м�����ˮ	         30  ҵ�������ˮ
    }
}

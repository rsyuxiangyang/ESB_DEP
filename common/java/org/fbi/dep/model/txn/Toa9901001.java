package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ��������
 * User: hanjianlong
 * Date: 2015-07-16
 */
public class Toa9901001 extends TOA implements Serializable {
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
          02	Ԥ���ʽ���ƽ̨��ˮ	16
          03	Ԥ��������	            255
          04	Ԥ����Ŀ��ַ            128
          05    Ԥ����Ŀ����            128
        */
        public String PRE_SALE_PER_NAME;             // Ԥ��������	            255
        public String PRE_SALE_PRO_ADDR;             // Ԥ����Ŀ��ַ            128
        public String PRE_SALE_PRO_NAME;             // Ԥ����Ŀ����            128
    }
}

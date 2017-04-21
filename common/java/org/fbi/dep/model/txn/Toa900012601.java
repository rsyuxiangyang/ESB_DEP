package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TOA;
import org.fbi.dep.model.base.TOABody;
import org.fbi.dep.model.base.TOAHeader;

import java.io.Serializable;

/**
 * ̩�������ʽ��ܣ����ն�����Ϣȡ��
 * User: hanjianlong
 * Date: 2015-07-16
 */
public class Toa900012601 extends TOA implements Serializable {
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
        public String DRCNT;   //�跽�ܱ���
        public String DRAMT;   //�跽�ܽ��
        public String CRCNT;   //�����ֶ�
        public String CRAMT;   //�����ֶ�
    }
}

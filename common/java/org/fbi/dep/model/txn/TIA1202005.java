package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ���������������ѯ���׽��
 * User: xiaobo
 */

public class TIA1202005 extends TIA implements Serializable {
    public Header header = new Header();
    public Body body = new Body();

    @Override
    public TIAHeader getHeader() {
        return header;
    }

    @Override
    public TIABody getBody() {
        return body;
    }

    //====================================================================
    public static class Header extends TIAHeader {
    }

    public static class Body extends TIABody {
        public String MERCHANT_ID;                  // �̻�����
        public String QUERY_SN;                  // Ҫ��ѯ�Ľ�����ˮ�� *
        public String STATUS;                     // ״̬  ����״̬����, 0�ɹ�,1ʧ��, 2ȫ��,3��Ʊ *
        public String TYPE;                     // ��ѯ����
        public String START_DAY;                     // ��ʼ��
        public String END_DAY;                     // ������

    }

}

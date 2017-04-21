package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * ������ʵʱ��ѯ���׽��
 * User: zhanrui
 * Date: 2012-01-31
 */

public class TIA1003001 extends TIA implements Serializable {
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
        public String QUERY_SN;                  // Ҫ��ѯ�Ľ�����ˮ��
        public String REMARK;                   // ��ע
    }

}

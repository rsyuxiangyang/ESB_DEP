package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * Created by XIANGYANG on 2015-9-9.
 * �ܷ��˻�����֪ͨ��7614��
 */

public class TIA4007614 extends TIA {
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

    public static class Header extends TIAHeader {

    }

    public static class Body extends TIABody {
        public String bankSerial;      //������ˮ��
        public String tradeDate;       //���н�������
        public String payAccount;      //����˺�
        public String payAccountName;  //�������
        public String apAmount;        //���׽��
        public String toCompanyCode;   //�տ˾���
        public String recCompanyName;  //�տ�������
        public String recAccount;      //���⻧��Ӧ�ڲ��˺�
        public String voucherFlag;     //�Ƿ񹤳���־
        public String purpose;         //��ʽ
        public String dcFlag;          //�����־
    }
}

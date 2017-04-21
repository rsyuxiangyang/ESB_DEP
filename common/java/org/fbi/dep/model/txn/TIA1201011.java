package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * ͨ����ʵʱ���� ����
 * User: wanglichao
 * Date: 2015-06-25
 */

public class TIA1201011 extends TIA {
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
        public String BUSINESS_CODE;            // ҵ�����
        public String MERCHANT_ID;              // �̻�����
        public String SUBMIT_TIME;               // �ύʱ��
        public String E_USER_CODE;              // �û����

        public String VALIDATE;                 // ��Ч��
        public String CVV2;                     // ���ÿ�CVV2
        public String BANK_CODE;                // ���д���
        public String ACCOUNT_TYPE;              // �˺�����
        public String ACCOUNT_NO;                 // �˺�
        public String ACCOUNT_NAME;              //�˺���
        public String ACCOUNT_PROP;              // �˺�����
        public String AMOUNT;                    // ���
        public String CURRENCY;                    // ��������
        public String ID_TYPE;                    // ����֤������
        public String ID;                    // ֤����
        public String SETTACCT;                    // �����׽��㻧
        public String TEL;                    // �ֻ���/С��ͨ
        public String CUST_USERID;                    // �Զ����û���
        public String SETTGROUPFLAG;                    // ���������־
        public String SUMMARY;                    // ���׸���
        public String REMARK;                    // ��ע
    }
}

package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

/**
 * Created by zhanrui
 * on 2016-3-16.
 * DEP����֧���˽ӿ�
 * ��֧��-ǩԼ�ӿ�
 */

public class TIA1409001 extends TIA {
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
        public String bankCode;            //���б���
        public String cardType;            //���п�������  1 :��ǿ��� 2:���ÿ�(���ǿ�) 4:���� 8:��˾�˻�
        public String accountCode;         //���п���
        public String bankCardName;        //���п�����
        public String certNo;              //֤������
        public String certType;            //֤������
        public String openBankAddress;    //����֧�е�ַ
        public String mobile;             //��ϵ��ʽ:�ƶ�����
        public String areaCode;           //����������
        public String perEntFlag;         //�Թ���˽��ʶ
        public String netWorkNature;
        public String userFullName;
        public String ebkType;
        public String payeeName;
        public String netWorkAreaCode;
        public String arpType;
    }
}

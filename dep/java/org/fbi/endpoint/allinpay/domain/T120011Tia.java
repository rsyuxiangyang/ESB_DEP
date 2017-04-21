package org.fbi.endpoint.allinpay.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.endpoint.allinpay.core.ABaseTiaHeader;

/**
 * Created by Lichao.W At 2015/6/25 22:27
 * wanglichao@163.com
 * ͨ��ʵʱ����
 */
@XStreamAlias("AIPG")
public class T120011Tia {
    public static class TiaHeader extends ABaseTiaHeader {
    }

    public static class Body {
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

    public TiaHeader INFO;
    public Body TRANS;

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("__", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(T120011Tia.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + xs.toXML(this);
    }

    public static void main(String[] argv) {
        T120011Tia tia = new T120011Tia();
        tia.INFO = new TiaHeader();
        tia.INFO.TRX_CODE = "100011";
        tia.INFO.VERSION = "03";
        tia.INFO.DATA_TYPE = "2";
        tia.INFO.LEVEL = "5";
        tia.INFO.USER_NAME = "20060400000044502";
        tia.INFO.USER_PASS = "`12qwe";
        tia.INFO.REQ_SN = "" + System.currentTimeMillis();

        tia.TRANS = new Body();
        tia.TRANS.BUSINESS_CODE = "10600";
        tia.TRANS.MERCHANT_ID = "200581000000519";
        System.out.println(tia);
    }
}

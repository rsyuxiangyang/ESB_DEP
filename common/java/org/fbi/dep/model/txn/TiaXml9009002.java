package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 *  ���ʶ���֧�� SBS-MBP-����
 */

@XStreamAlias("ROOT")
public class TiaXml9009002 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {

        public String ORGIDT;     // ���׻���
        public String ORDDAT;     // ί������
        public String FBTACT;     // �ͻ���
        public String ORDTYP;     // ��������
        public String TXNCUR;     // ���׻���
        public String TXNAMT;	  // ���׽��
        public String RMTTYP;	  // �������
        public String CUSTYP;	  // ����ʻ�����
        public String CUSACT;	  // ����ʺ�
        public String FEETYP;	  // �Ƿ����
        public String FEEACT;	  // �����ʻ�
        public String BENACT;	  // �տ����ʺ�
        public String BENNAM;	  // �տ�������
        public String BENBNK;	  // �տ�������
        public String AGENBK;	  // ����������
        public String PBKACT;	  // �����˺�
        public String RETNAM;	  // ���������
        public String RETAUX;	  // �����;
        public String CHQTYP;	  // ֧Ʊ����
        public String CHQNUM;	  // �տ����к�
        public String CHQPWD;	  // ֧Ʊ����
        public String FUNCDE;	  // ������
        public String ADVNUM;	  // FS��ˮ��
        public String REQNUM;	  // ������ˮ��

    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009002.class);
        return (TiaXml9009002) xs.fromXML(xml);
    }
}

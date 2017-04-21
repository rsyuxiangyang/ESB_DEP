package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * SBSͬҵ����֪ͨ
 */

@XStreamAlias("ROOT")
public class TiaXml9009101 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {

        public String BANKSN;        // ������ˮ��	C(16)	����16λ��������16λ	�ɿ�
        public String BNKDAT;        // BNKDAT	ί������	C(8)	YYYYMMDD ���н�������	�ǿ�
        public String BNKTIM;        // BNKTIM	ί��ʱ��	C(6)	hhmmss   ���н���ʱ��	�ɿ�

        public String PBKNUM;        // �������к�	C(12)		�ɿ�
        public String PBKNAM;        // ����������	C(80)		�ɿ�
        public String PAYACT;        // ����ʺ�	C(40)		�ɿ�
        public String PAYNAM;        // �������	C(150)		�ɿ�

        public String IBKACT;        // ͬҵ�˺�	C(22)		�ǿ�
        public String IBKNUM;        // ͬҵ���к�	C(12)		�ǿ�
        public String IBKNAM;        // ͬҵ������	C(80)		�ɿ�
        public String RETAUX;        // ��;	C(150)		�ɿ�

        public String TXNAMT;        // ���׽��	S9(13).99	S��ʾ�����ţ�ռһλ	�ǿ�
        public String DCTYPE;        // �����־	C(1)	D-�裬C-��     �ǿ�

        public String RECACT;        // �տ�ʺ�	C(35)		�ǿ�
        public String RECNAM;        // �տ����	C(150)		�ɿ�

    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009101.class);
        return (TiaXml9009101) xs.fromXML(xml);
    }
}

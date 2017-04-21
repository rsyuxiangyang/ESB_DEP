package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * ���ݿͻ�֤����Ϣ��ѯ�˻��б�
 */

@XStreamAlias("ROOT")
public class TiaXml9009061 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String CUSKID; // �ͻ����	C(1)	1-��λ�ͻ� 2-���˿ͻ� 3-�ڲ��ͻ� 4-����ͬҵ�ͻ�
        public String PASTYP; // ֤�����	C(1)	1-Ӫҵִ�� 2-�Ǽ�֤ 3-���ܲ���֤���ļ� 4-���֤ 5-����֤ 6-����
        public String PASSNO; // ֤����
        public String ACTTYP; // �ʻ�����
        public String BEGNUM; // ��ʼ����
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009061.class);
        return (TiaXml9009061) xs.fromXML(xml);
    }
}

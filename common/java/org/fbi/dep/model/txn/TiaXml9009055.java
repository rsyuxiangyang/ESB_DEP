package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * 9009055-��ѯ�ܷ��˻��ϻ��²���ϸ
 * ��ӦSBS-8859����
 */

@XStreamAlias("ROOT")
public class TiaXml9009055 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String ACTNUM; // �˺�
        public String BEGDAT; // ��ѯ����
        public String ENDDAT; // ��ѯֹ��
        public String BEGNUM; // ��ʼ����
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009055.class);
        return (TiaXml9009055) xs.fromXML(xml);
    }
}

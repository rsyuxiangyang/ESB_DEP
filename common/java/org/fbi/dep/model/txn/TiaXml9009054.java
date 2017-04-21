package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * 9009054-��ѯ�ܷ��˻����
 * ��ӦSBS-8858����
 */

@XStreamAlias("ROOT")
public class TiaXml9009054 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String ACTNUM; // �ܹ�˾�˺�
        public String BEGNUM; // ��ʼ����
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009054.class);
        return (TiaXml9009054) xs.fromXML(xml);
    }
}
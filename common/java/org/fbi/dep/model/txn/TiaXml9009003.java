package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 *  ���ʶ���֧�������ѯ SBS-MBP-����
 */

@XStreamAlias("ROOT")
public class TiaXml9009003 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {

        public String FBTIDX;     // ��Χϵͳ������
        public String REQNUM;     // �������к�
        public String ORDDAT;     // ��������
        public String CHQNUM;     // �տ����к�

    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009003.class);
        return (TiaXml9009003) xs.fromXML(xml);
    }
}

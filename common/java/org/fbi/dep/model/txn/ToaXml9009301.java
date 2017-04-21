package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;
import org.fbi.dep.model.base.ToaXmlInfo;

import java.io.Serializable;
import java.util.List;

/**
 * �������˿ͻ���Ϣ ��ӦSBS-8011����
 */

@XStreamAlias("ROOT")
public class ToaXml9009301 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String ORGIDT;       // ���ź�
        public String DEPNUM;       // ������
        public String CUSIDT;       // �ͻ���
        public String CUSNAM;       // �ͻ�����
        public String OPNDAT;       // ��������
        public String CLSDAT;       // ע������
        public String AMDTLR;       // ����Ա
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009301";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009301.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009301 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009301.class);
        return (ToaXml9009301) xs.fromXML(xml);
    }
}

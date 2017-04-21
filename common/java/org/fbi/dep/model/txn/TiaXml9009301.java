package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * �������˿ͻ���Ϣ ��ӦSBS-8011����
 */

@XStreamAlias("ROOT")
public class TiaXml9009301 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {

        public String CUSNAM;  // �ͻ�����
        public String CORADD;  // ��ַ
        public String ZIPCDE;  // �ʱ�
        public String TELNUM;  // �绰
        public String TELEXN;  // ����
        public String PASTYP;  // ֤������
        public String PASSNO;  // ֤����

    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009301.class);
        return (TiaXml9009301) xs.fromXML(xml);
    }
}

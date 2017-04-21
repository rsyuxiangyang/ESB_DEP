package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-5-21.
 * �˻����ս�����ϸ��ѯ
 */

@XStreamAlias("ROOT")
public class TiaXml9009051 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String ACTNO;       //�˻��˺�	  C(22) �����˺�(8010��ͷ)
        public String START_NUM;       //��ʼ����     C(6)
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009051.class);
        return (TiaXml9009051) xs.fromXML(xml);
    }
}

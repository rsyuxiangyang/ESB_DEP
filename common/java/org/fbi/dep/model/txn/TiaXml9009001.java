package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlHeader;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * SBSת��
 */

@XStreamAlias("ROOT")
public class TiaXml9009001 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String OUT_ACTNO;
        public String OUT_ACTNAME;
        public String IN_ACTNO;
        public String IN_ACTNAME;
        public String TXN_AMT;
        public String REMARK;
        public String RESERVE;
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009001.class);
        return (TiaXml9009001) xs.fromXML(xml);
    }
}

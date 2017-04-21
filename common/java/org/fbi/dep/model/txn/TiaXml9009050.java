package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * Created by hanjianlong on 2015-12-10.
 * 账户余额查询
 */

@XStreamAlias("ROOT")
public class TiaXml9009050 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String ACTNUM;       //账户账号	  C(18) 完整账号(8010开头)
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009050.class);
        return (TiaXml9009050) xs.fromXML(xml);
    }
}

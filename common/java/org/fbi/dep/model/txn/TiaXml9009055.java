package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * 9009055-查询总分账户上划下拨明细
 * 对应SBS-8859交易
 */

@XStreamAlias("ROOT")
public class TiaXml9009055 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String ACTNUM; // 账号
        public String BEGDAT; // 查询起日
        public String ENDDAT; // 查询止日
        public String BEGNUM; // 起始笔数
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009055.class);
        return (TiaXml9009055) xs.fromXML(xml);
    }
}

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
 * Created by hanjianlong on 2015-12-10.
 * 账户余额查询
 */

@XStreamAlias("ROOT")
public class ToaXml9009050 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String ACTNUM;       // 账号
        public String ACTNAM;       // 帐户名
        public String BOKBAL;       // 帐户余额
        public String DDRAMT;       // 本日借方发生额
        public String DCRAMT;       // 本日贷方发生额
        public String DDRCNT;       // 本日借方发生数
        public String DCRCNT;       // 本日贷方发生数
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009050";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009050.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009050 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009050.class);
        return (ToaXml9009050) xs.fromXML(xml);
    }
}

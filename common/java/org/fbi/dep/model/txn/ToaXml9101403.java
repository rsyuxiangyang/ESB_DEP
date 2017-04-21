package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.dep.model.base.ToaXml;
import org.fbi.dep.model.base.ToaXmlHttpInfo;

import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付-交易综合查询-响应报文
 */

@XStreamAlias("root")
public class ToaXml9101403 extends ToaXml {
    public ToaXmlHttpInfo info = new ToaXmlHttpInfo();
    public Body Body = new Body();

    public ToaXml9101403.Body getBody() {
        return Body;
    }

    public void setBody(ToaXml9101403.Body body) {
        Body = body;
    }

    public ToaXmlHttpInfo getInfo() {
        return info;
    }

    public void setInfo(ToaXmlHttpInfo info) {
        this.info = info;
    }

    public static class Body implements Serializable {
        public String rtncode;    //业务层面-返回代码
        public String rtnmsg;     //业务层面-返回信息

        public String getRtncode() {
            return rtncode;
        }

        public void setRtncode(String rtncode) {
            this.rtncode = rtncode;
        }

        public String getRtnmsg() {
            return rtnmsg;
        }

        public void setRtnmsg(String rtnmsg) {
            this.rtnmsg = rtnmsg;
        }

    }


    @Override
    public String toString() {
        this.info.txncode = "9101403";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9101403.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9101403 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9101403.class);
        return (ToaXml9101403) xs.fromXML(xml);
    }
}


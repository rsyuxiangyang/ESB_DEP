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
 * Created by XIANGYANG on 2015-6-30.
 * �������̲�ѯ-��Ӧ����
 */

@XStreamAlias("root")
public class ToaXml9101102 extends ToaXml {
    public ToaXmlHttpInfo info = new ToaXmlHttpInfo();
    public Body Body = new Body();

    public ToaXml9101102.Body getBody() {
        return Body;
    }

    public void setBody(ToaXml9101102.Body body) {
        Body = body;
    }

    public ToaXmlHttpInfo getInfo() {
        return info;
    }

    public void setInfo(ToaXmlHttpInfo info) {
        this.info = info;
    }

    public static class Body implements Serializable {
        public String rtncode;     //ҵ�����-���ش���
        public String rtnmsg;      //ҵ�����-������Ϣ
        public String flowcode;    //������Ӧ��
        public String flowmsg;     //������Ӧ��Ϣ

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

        public String getFlowcode() {
            return flowcode;
        }

        public void setFlowcode(String flowcode) {
            this.flowcode = flowcode;
        }

        public String getFlowmsg() {
            return flowmsg;
        }

        public void setFlowmsg(String flowmsg) {
            this.flowmsg = flowmsg;
        }
    }

    @Override
    public String toString() {
        this.info.txncode = "9101102";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9101102.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9101102 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9101102.class);
        return (ToaXml9101102) xs.fromXML(xml);
    }
}


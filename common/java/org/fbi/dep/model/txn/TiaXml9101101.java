package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlHttpInfo;

import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-6-30.
 * 还款流程启动-请求报文
 */

@XStreamAlias("root")
public class TiaXml9101101 extends TiaXml {
    public TiaXmlHttpInfo info;
    public Body body;

    public TiaXmlHttpInfo getInfo() {
        return info;
    }

    public void setInfo(TiaXmlHttpInfo info) {
        this.info = info;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body implements Serializable {
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9101101.class);
        return (TiaXml9101101) xs.fromXML(xml);
    }
}

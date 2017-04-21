package org.fbi.dep.helper.sbsHelper;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ∑µªÿ¬Î∂‘’’
 */
@XStreamAlias("Content")
public class RtnIdContrastTbl implements Serializable {

    @XStreamImplicit
    public List<Type> TypeList = new ArrayList<Type>();

    @XStreamAlias("type")
    public static class Type implements Serializable {
        public String RtnId;
        public String RtnRemark;
        public String Recvs;
    }

    public RtnIdContrastTbl toBean(String xml) {

        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(RtnIdContrastTbl.class);
        return (RtnIdContrastTbl) xs.fromXML(xml);
    }

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(RtnIdContrastTbl.class);
        return xs.toXML(this);
    }

}

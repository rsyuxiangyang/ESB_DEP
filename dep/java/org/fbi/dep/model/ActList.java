package org.fbi.dep.model;

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
 * �������˺�
 */
@XStreamAlias("List")
public class ActList implements Serializable {

    @XStreamImplicit
    public List<Act> Acts = new ArrayList<Act>();

    @XStreamAlias("Act")
    public static class Act implements Serializable {
        public String actno;
        public String userid;
        public String txnNo;
        public String inOut;
    }

    public ActList toBean(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ActList.class);
        return (ActList) xs.fromXML(xml);
    }

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ActList.class);
        return xs.toXML(this);
    }

    public static void main(String[] args) {
        ActList testAct = new ActList();
        Act act1 = new Act();
        act1.actno="12121";
        testAct.Acts.add(act1);
        Act act2 = new Act();
        act1.actno="12121";
        testAct.Acts.add(act2);
        System.out.println(testAct.toString());

        String str = "<List>    <Act>        <actno>801000026103021001</actno>        <userid>WSYS01</userid>        <txnNo>9009001</txnNo>        <inOut>0</inOut>    </Act>    <Act>        <actno>801000026103021001</actno>        <userid>WSYS01</userid>        <txnNo>9009001</txnNo>        <inOut>0</inOut>    </Act></List>";
        ActList actlist = new ActList().toBean(str);
        for(Act act : actlist.Acts) {
            System.out.println(act.actno);
        }
    }
}

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

/**
 * 2.2	���ʶ���֧�������ѯ 9009003
 */

@XStreamAlias("ROOT")
public class ToaXml9009003 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {


        public String TLRNUM;	// ��Ա��
        public String VCHSET;	// ��Ʊ�׺�
        public String SETSEQ;	// ��Ʊ����˳���

        public String TXNSEQ;   // ������ˮ��
        public String FBTIDX;	// ��Χϵͳ��ˮ��
        /*
        1-��������,2-�����,3-���оܾ�,4-����,5-���,6-������ϸʧ��,9-����
         */
        public String PRCSTS;	// ����״̬
        public String RTNMSG;	// ���з�����Ϣ


    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009003";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009003.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009003 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009003.class);
        return (ToaXml9009003) xs.fromXML(xml);
    }
}

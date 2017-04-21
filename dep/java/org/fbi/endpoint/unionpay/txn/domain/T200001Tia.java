package org.fbi.endpoint.unionpay.txn.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.endpoint.unionpay.core.BaseTiaHeader;

/**
 * Created by IntelliJ IDEA.
 * User: zxb
 */
@XStreamAlias("GZELINK")
public class T200001Tia {
    public static class TiaHeader extends BaseTiaHeader {
    }

    public static class Body {
        public BodyHeader QUERY_TRANS = new BodyHeader();

        public static class BodyHeader {
            public String QUERY_SN = "";
            public String QUERY_REMARK = "";
        }
    }

    public TiaHeader INFO;
    public Body BODY;

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(T200001Tia.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static void main(String[] argv) {
        T200001Tia tia = new T200001Tia();
        tia.INFO = new TiaHeader();
        tia.INFO.TRX_CODE = "200001";
        tia.INFO.REQ_SN = "" + System.currentTimeMillis();

        tia.BODY = new Body();
        tia.INFO.VERSION = "03";
        tia.BODY.QUERY_TRANS = new Body.BodyHeader();
        tia.BODY.QUERY_TRANS.QUERY_REMARK = "mark";
        tia.BODY.QUERY_TRANS.QUERY_SN = "111";
        System.out.println(tia);
    }
}

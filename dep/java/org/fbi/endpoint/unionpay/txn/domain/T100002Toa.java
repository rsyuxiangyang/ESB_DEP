package org.fbi.endpoint.unionpay.txn.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.endpoint.unionpay.core.BaseToaHeader;

import java.util.List;

/**
 * zhangxiaobo 2013-03-28
 */
@XStreamAlias("GZELINK")
public class T100002Toa {
    public static class ToaHeader extends BaseToaHeader {
    }

    public static class Body {
        public List<BodyDetail> RET_DETAILS;

        @XStreamAlias("RET_DETAIL")
        public static class BodyDetail {
            public String SN = "";
            public String RET_CODE = "";
            public String ERR_MSG = "";
        }
    }

    public ToaHeader INFO;
    public Body BODY = new Body();

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(T100002Toa.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static T100002Toa getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(T100002Toa.class);
        return (T100002Toa) xs.fromXML(xml);
    }

    public static void main(String[] argv) {
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><GZELINK>\n" +
                "  <INFO>\n" +
                "    <TRX_CODE>100002</TRX_CODE>\n" +
                "    <VERSION>04</VERSION>\n" +
                "    <DATA_TYPE>2</DATA_TYPE>\n" +
                "    <REQ_SN>1510956041000</REQ_SN>\n" +
                "    <RET_CODE>0000</RET_CODE>\n" +
                "    <ERR_MSG></ERR_MSG>\n" +
                "    <SIGNED_MSG>9a15fa1a2c98b32743852d67668f308e59e75dbcd80509a9b5fc48724ca1c447a8608a0d3288a32a73f1750649d9e6502d5f7754cf2e695c2e795884378a193c06c5744f2af1d2151fd62b42d0abc8daa0d5e53d5ffa51997ce2c6c8e5c7c3671859e545e5367955d8e3bd64930f4719469fd1b502a8b45aeed66d5d93002867</SIGNED_MSG>\n" +
                "  </INFO>\n" +
                "  <BODY>\n" +
                "    <RET_DETAILS>\n" +
                "      <RET_DETAIL>\n" +
                "        <SN>0001</SN>\n" +
                "        <RET_CODE>0000</RET_CODE>\n" +
                "        <ERR_MSG>支付成功</ERR_MSG>\n" +
                "      </RET_DETAIL>\n" +
                "      <RET_DETAIL>\n" +
                "        <SN>0002</SN>\n" +
                "        <RET_CODE>0000</RET_CODE>\n" +
                "        <ERR_MSG>支付成功</ERR_MSG>\n" +
                "      </RET_DETAIL>\n" +
                "    </RET_DETAILS>\n" +
                "  </BODY>\n" +
                "</GZELINK>";

        T100002Toa toa = new T100002Toa();

        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(T100002Toa.class);
        toa = (T100002Toa) xs.fromXML(xml);

        System.out.println(toa);
    }
}

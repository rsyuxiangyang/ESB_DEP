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
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-25
 * Time: ����9:27
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("GZELINK")
public class T200001Toa {
    public static class ToaHeader extends BaseToaHeader {
    }

    public static class Body {
        public BodyHeader QUERY_TRANS;

        public static class BodyHeader {
            public String QUERY_SN = "";
            public String QUERY_REMARK = "";
        }

        public List<BodyDetail> RET_DETAILS;

        @XStreamAlias("RET_DETAIL")
        public static class BodyDetail {
            public String SN = "";
            public String ACCOUNT = "";
            public String ACCOUNT_NAME = "";
            public String AMOUNT = "";
            public String CUST_USERID = "";
            public String REMARK = "";
            public String RET_CODE = "";
            public String ERR_MSG = "";
        }
    }

    public ToaHeader INFO;
    public Body BODY;

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(T200001Toa.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static T200001Toa getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(T200001Toa.class);
        return (T200001Toa) xs.fromXML(xml);
    }

    public static void main(String[] argv) {
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><GZELINK>\n" +
                "  <INFO>\n" +
                "    <TRX_CODE>100004</TRX_CODE>\n" +
                "    <VERSION>04</VERSION>\n" +
                "    <DATA_TYPE>2</DATA_TYPE>\n" +
                "    <REQ_SN>1510956041000</REQ_SN>\n" +
                "    <RET_CODE>0000</RET_CODE>\n" +
                "    <ERR_MSG></ERR_MSG>\n" +
                "    <SIGNED_MSG>9a15fa1a2c98b32743852d67668f308e59e75dbcd80509a9b5fc48724ca1c447a8608a0d3288a32a73f1750649d9e6502d5f7754cf2e695c2e795884378a193c06c5744f2af1d2151fd62b42d0abc8daa0d5e53d5ffa51997ce2c6c8e5c7c3671859e545e5367955d8e3bd64930f4719469fd1b502a8b45aeed66d5d93002867</SIGNED_MSG>\n" +
                "  </INFO>\n" +
                "  <BODY>\n" +
                "<QUERY_TRANS>\n" +
                "<QUERY_SN>2009041611084101</QUERY_SN>\n" +
                "<QUERY_REMARK>��ѯ��ע</QUERY_REMARK>\n" +
                "</QUERY_TRANS>\n" +
                "    <RET_DETAILS>\n" +
                "      <RET_DETAIL>\n" +
                "        <SN>0001</SN>\n" +
                "        <RET_CODE>0000</RET_CODE>\n" +
                "        <ERR_MSG>֧���ɹ�</ERR_MSG>\n" +
                "        <ACCOUNT>60138270140042110021</ACCOUNT>\n" +
                "        <ACCOUNT_NAME>����</ACCOUNT_NAME>\n" +
                "        <AMOUNT>10000</AMOUNT>\n" +
                "        <CUST_USERID>666666</CUST_USERID>\n" +
                "        <REMARK>ȡů��</REMARK>\n" +
                "      </RET_DETAIL>\n" +
                "    </RET_DETAILS>\n" +
                "  </BODY>\n" +
                "</GZELINK>";

        T200001Toa toa = new T200001Toa();

        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(T200001Toa.class);
        toa = (T200001Toa) xs.fromXML(xml);
        System.out.println(toa.BODY.QUERY_TRANS.QUERY_SN);
        System.out.println(toa);
    }
}

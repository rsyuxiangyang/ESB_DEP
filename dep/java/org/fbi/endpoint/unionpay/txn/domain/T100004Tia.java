package org.fbi.endpoint.unionpay.txn.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.endpoint.unionpay.core.BaseTiaHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-25
 * Time: 上午9:27
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("GZELINK")
public class T100004Tia {
    public static class TiaHeader extends BaseTiaHeader {
    }

    public static class Body {
        public BodyHeader TRANS_SUM;
        public List<BodyDetail> TRANS_DETAILS;

        public static class BodyHeader {
            public String BUSINESS_CODE = "";
            public String MERCHANT_ID = "";
            public String SUBMIT_TIME = "";
            public String TOTAL_ITEM = "1";
            public String TOTAL_SUM = "0";
        }

        @XStreamAlias("TRANS_DETAIL")
        public static class BodyDetail {
            public String SN = "001";
            public String BANK_CODE = "";
            public String ACCOUNT_NO = "";
            public String ACCOUNT_NAME = "";
            public String PROVINCE = "";
            public String CITY = "";
            public String BANK_NAME = "";
            public String ACCOUNT_PROP = "0";   //0私人，1公司。不填时，默认为私人0。
            public String AMOUNT = "";   //整数，单位分
            public String CURRENCY = "";
            public String PROTOCOL = "";
            public String PROTOCOL_USERID = "";
            public String ID_TYPE = "";
            public String ID = "";
            public String TEL = "";
            public String RECKON_ACCOUNT = "";
            public String CUST_USERID = "";

            public String REMARK = "";
            public String RESERVE1 = "";
            public String RESERVE2 = "";
        }
    }

    public TiaHeader INFO;
    public Body BODY;

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(T100004Tia.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static void main(String[] argv) {
        T100004Tia tia = new T100004Tia();
        tia.INFO = new TiaHeader();
        tia.INFO.TRX_CODE = "100004";
        tia.INFO.REQ_SN = "" + System.currentTimeMillis();

        tia.BODY = new Body();
        tia.BODY.TRANS_SUM = new Body.BodyHeader();

        tia.BODY.TRANS_DETAILS = new ArrayList<Body.BodyDetail>();

        Body.BodyDetail record = new Body.BodyDetail();
        tia.BODY.TRANS_DETAILS.add(record);

        System.out.println(tia);
    }
}

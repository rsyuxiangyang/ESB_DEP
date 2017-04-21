package org.fbi.endpoint.allinpay.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.fbi.endpoint.allinpay.core.ABaseTiaHeader;

/**
 * Created by Lichao.W At 2015/6/25 22:27
 * wanglichao@163.com
 * ͨ�������ѯ ����
 */
@XStreamAlias("AIPG")
public class T120004Tia {
    public static class TiaHeader extends ABaseTiaHeader {
    }

    @XStreamAlias("QTRANSREQ")
    public static class Body {
        public String QUERY_SN;                  // Ҫ��ѯ�Ľ�����ˮ�� *
        public String MERCHANT_ID;                  // �̻�����
        public String STATUS;                     // ״̬  ����״̬����, 0�ɹ�,1ʧ��, 2ȫ��,3��Ʊ *
        public String TYPE;                     // ��ѯ����
        public String START_DAY;                     // ��ʼ��
        public String END_DAY;                     // ������
    }

    public TiaHeader INFO;
    public Body QTRANSREQ;

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("__", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(T120004Tia.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + xs.toXML(this);
    }

    public static void main(String[] argv) {
        T120004Tia tia = new T120004Tia();
        tia.INFO = new TiaHeader();
        tia.INFO.TRX_CODE = "200004";
        tia.INFO.REQ_SN = "" + System.currentTimeMillis();

        tia.QTRANSREQ = new Body();
        tia.INFO.VERSION = "03";
        tia.QTRANSREQ.QUERY_SN = "200604000000445-1435219798915";
        tia.QTRANSREQ.MERCHANT_ID = "200604000000445";
        tia.QTRANSREQ.STATUS = "1";
        tia.QTRANSREQ.TYPE = "1";
        System.out.println(tia);
    }
}

package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlInfo;

import java.io.Serializable;

/**
 * SBS����֧������������
 */

@XStreamAlias("ROOT")
public class TiaXml9009201 extends TiaXml {
    public TiaXmlInfo INFO;
    public Body BODY;

    public static class Body implements Serializable {
        public String SFFLAG;            // �ո���־	C(1)	S:��    F:��	�ǿ�
        public String TXNAMT;            // ���	C(3��16)	��ʽ: ####9.99 ��:1234.56��0.00���16λ��	�ǿ�

        public String REMARK;
        public String RESERVE;
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9009201.class);
        return (TiaXml9009201) xs.fromXML(xml);
    }
}

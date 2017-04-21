package org.fbi.dep.model.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.TiaXmlHttpInfo;
import java.io.Serializable;

/**
 * Created by XIANGYANG on 2015-6-2.
 * ��ѯ���е�����ϸ-������
 */

@XStreamAlias("root")
public class TiaXml9109003 extends TiaXml {
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
        public String pagenum;        //����ҳ��
        public String pagesize;       //ÿҳ��¼��
        public String startdate;      //������ʼ����
        public String enddate;        //���׽�������
        public String acctid;         //�����˻�
        public String reserve;        //������

        public String getPagenum() {
            return pagenum;
        }

        public void setPagenum(String pagenum) {
            this.pagenum = pagenum;
        }

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getAcctid() {
            return acctid;
        }

        public void setAcctid(String acctid) {
            this.acctid = acctid;
        }

        public String getReserve() {
            return reserve;
        }

        public void setReserve(String reserve) {
            this.reserve = reserve;
        }
    }

    @Override
    public TiaXml getTia(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(TiaXml9109003.class);
        return (TiaXml9109003) xs.fromXML(xml);
    }
}

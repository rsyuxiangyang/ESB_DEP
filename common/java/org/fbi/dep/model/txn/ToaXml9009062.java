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
 * Created by XIANGYANG on 2015-5-11.
 * �����˺Ų�ѯ�˻���Ϣ
 */

@XStreamAlias("ROOT")
public class ToaXml9009062 extends ToaXml {
    public ToaXmlInfo INFO = new ToaXmlInfo();
    public Body BODY = new Body();

    public static class Body implements Serializable {
        public String ORGIDT;  //������
        public String ACTNUM;  //�˻���
        public String ACTNAM;  //�˻���
        public String BOKBAL;  //�˻����
        public String AVABAL;  //��Ч���
        public String DIFBAL;  //�跽����Ϣ���
        public String CIFBAL;  //��������Ϣ���
        public String MAVBAL;  //��ƽ�����
        public String YAVBAL;  //��ƽ�����
        public String DDRAMT;  //���ս跽������
        public String DCRAMT;  //���մ���������
        public String DDRCNT;  //���ս跽������
        public String DCRCNT;  //���մ���������
        public String MDRAMT;  //���ۼƽ跽������
        public String MCRAMT;  //���ۼƴ���������
        public String MDRCNT;  //���ۼƽ跽������
        public String MCRCNT;  //���ۼƴ���������
        public String YDRAMT;  //���ۼƽ跽������
        public String YCRAMT;  //���ۼƴ���������
        public String YDRCNT;  //���ۼƽ跽������
        public String YCRCNT;  //���ۼƴ���������
        public String DINRAT;  //�跽���ʴ���
        public String CINRAT;  //�������ʴ���
        public String DRATSF;  //�跽�̶�������������
        public String CRATSF;  //�����̶�������������
        public String DACINT;  //Ӧ����Ϣ
        public String CACINT;  //Ӧ����Ϣ
        public String DAMINT;  //��̯��Ӧ����Ϣ
        public String CAMINT;  //��̯��Ӧ����Ϣ
        public String INTFLG;  //��Ϣ��־
        public String INTCYC;  //��Ϣ����
        public String INTTRA;  //תϢ�ʻ�
        public String LINTDT;  //ĩ�μ�Ϣ����
        public String LINDTH;  //ĩ��ת������ʷ����
        public String STMCYC;  //���ʵ�����������
        public String STMCDT;  //���ʵ�������������
        public String STMFMT;  //���ʵ���ҳ��ʽ
        public String STMSHT;  //���ʵ����ʷ���
        public String STMDEP;  //���˵��ַ�����
        public String STMADD;  //���˵��ʼĵ�ַ
        public String STMZIP;  //��������
        public String LEGCYC;  //�ֻ��˳�������
        public String LEGCDT;  //�ֻ��ʳ�����������
        public String LEGFMT;  //�ֻ�����ҳ��ʽ
        public String LEGSHT;  //�ֻ��ʳ��ʷ���
        public String LEGDEP;  //�ֻ��˷ַ�����
        public String LEGADD;  //�ֻ����ʼĵ�ַ
        public String LEGZIP;  //�ʱ�
        public String ACTTYP;  //�ʻ�����
        public String ACTGLC;  //������
        public String ACTPLC;  //������
        public String DEPNUM;  //���ź�
        public String LENTDT;  //ĩ�μ�������
        public String DRACCM;  //����Ϣ�ν跽����
        public String CRACCM;  //����Ϣ�δ�������
        public String CQEFLG;  //֧Ʊ���۱�־
        public String BALLIM;  //Э���ʻ������
        public String OVELIM;  //͸֧�޶�
        public String OVEEXP;  //��ȵ�����
        public String OPNDAT;  //��������
        public String CLSDAT;  //��������
        public String REGSTS;  //��ʧ״̬
        public String FRZSTS;  //����״̬
        public String ACTSTS;  //�ʻ�״̬
        public String AMDTLR;  //�޸Ĺ�Ա
        public String UPDDAT;  //����޸�����
        public String RECSTS;  //��¼״̬

        public String getORGIDT() {
            return ORGIDT;
        }

        public void setORGIDT(String ORGIDT) {
            this.ORGIDT = ORGIDT;
        }

        public String getACTNUM() {
            return ACTNUM;
        }

        public void setACTNUM(String ACTNUM) {
            this.ACTNUM = ACTNUM;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }

        public String getBOKBAL() {
            return BOKBAL;
        }

        public void setBOKBAL(String BOKBAL) {
            this.BOKBAL = BOKBAL;
        }

        public String getAVABAL() {
            return AVABAL;
        }

        public void setAVABAL(String AVABAL) {
            this.AVABAL = AVABAL;
        }

        public String getDIFBAL() {
            return DIFBAL;
        }

        public void setDIFBAL(String DIFBAL) {
            this.DIFBAL = DIFBAL;
        }

        public String getCIFBAL() {
            return CIFBAL;
        }

        public void setCIFBAL(String CIFBAL) {
            this.CIFBAL = CIFBAL;
        }

        public String getMAVBAL() {
            return MAVBAL;
        }

        public void setMAVBAL(String MAVBAL) {
            this.MAVBAL = MAVBAL;
        }

        public String getYAVBAL() {
            return YAVBAL;
        }

        public void setYAVBAL(String YAVBAL) {
            this.YAVBAL = YAVBAL;
        }

        public String getDDRAMT() {
            return DDRAMT;
        }

        public void setDDRAMT(String DDRAMT) {
            this.DDRAMT = DDRAMT;
        }

        public String getDCRAMT() {
            return DCRAMT;
        }

        public void setDCRAMT(String DCRAMT) {
            this.DCRAMT = DCRAMT;
        }

        public String getDDRCNT() {
            return DDRCNT;
        }

        public void setDDRCNT(String DDRCNT) {
            this.DDRCNT = DDRCNT;
        }

        public String getDCRCNT() {
            return DCRCNT;
        }

        public void setDCRCNT(String DCRCNT) {
            this.DCRCNT = DCRCNT;
        }

        public String getMDRAMT() {
            return MDRAMT;
        }

        public void setMDRAMT(String MDRAMT) {
            this.MDRAMT = MDRAMT;
        }

        public String getMCRAMT() {
            return MCRAMT;
        }

        public void setMCRAMT(String MCRAMT) {
            this.MCRAMT = MCRAMT;
        }

        public String getMDRCNT() {
            return MDRCNT;
        }

        public void setMDRCNT(String MDRCNT) {
            this.MDRCNT = MDRCNT;
        }

        public String getMCRCNT() {
            return MCRCNT;
        }

        public void setMCRCNT(String MCRCNT) {
            this.MCRCNT = MCRCNT;
        }

        public String getYDRAMT() {
            return YDRAMT;
        }

        public void setYDRAMT(String YDRAMT) {
            this.YDRAMT = YDRAMT;
        }

        public String getYCRAMT() {
            return YCRAMT;
        }

        public void setYCRAMT(String YCRAMT) {
            this.YCRAMT = YCRAMT;
        }

        public String getYDRCNT() {
            return YDRCNT;
        }

        public void setYDRCNT(String YDRCNT) {
            this.YDRCNT = YDRCNT;
        }

        public String getYCRCNT() {
            return YCRCNT;
        }

        public void setYCRCNT(String YCRCNT) {
            this.YCRCNT = YCRCNT;
        }

        public String getDINRAT() {
            return DINRAT;
        }

        public void setDINRAT(String DINRAT) {
            this.DINRAT = DINRAT;
        }

        public String getCINRAT() {
            return CINRAT;
        }

        public void setCINRAT(String CINRAT) {
            this.CINRAT = CINRAT;
        }

        public String getDRATSF() {
            return DRATSF;
        }

        public void setDRATSF(String DRATSF) {
            this.DRATSF = DRATSF;
        }

        public String getCRATSF() {
            return CRATSF;
        }

        public void setCRATSF(String CRATSF) {
            this.CRATSF = CRATSF;
        }

        public String getDACINT() {
            return DACINT;
        }

        public void setDACINT(String DACINT) {
            this.DACINT = DACINT;
        }

        public String getCACINT() {
            return CACINT;
        }

        public void setCACINT(String CACINT) {
            this.CACINT = CACINT;
        }

        public String getDAMINT() {
            return DAMINT;
        }

        public void setDAMINT(String DAMINT) {
            this.DAMINT = DAMINT;
        }

        public String getCAMINT() {
            return CAMINT;
        }

        public void setCAMINT(String CAMINT) {
            this.CAMINT = CAMINT;
        }

        public String getINTFLG() {
            return INTFLG;
        }

        public void setINTFLG(String INTFLG) {
            this.INTFLG = INTFLG;
        }

        public String getINTCYC() {
            return INTCYC;
        }

        public void setINTCYC(String INTCYC) {
            this.INTCYC = INTCYC;
        }

        public String getINTTRA() {
            return INTTRA;
        }

        public void setINTTRA(String INTTRA) {
            this.INTTRA = INTTRA;
        }

        public String getLINTDT() {
            return LINTDT;
        }

        public void setLINTDT(String LINTDT) {
            this.LINTDT = LINTDT;
        }

        public String getLINDTH() {
            return LINDTH;
        }

        public void setLINDTH(String LINDTH) {
            this.LINDTH = LINDTH;
        }

        public String getSTMCYC() {
            return STMCYC;
        }

        public void setSTMCYC(String STMCYC) {
            this.STMCYC = STMCYC;
        }

        public String getSTMCDT() {
            return STMCDT;
        }

        public void setSTMCDT(String STMCDT) {
            this.STMCDT = STMCDT;
        }

        public String getSTMFMT() {
            return STMFMT;
        }

        public void setSTMFMT(String STMFMT) {
            this.STMFMT = STMFMT;
        }

        public String getSTMSHT() {
            return STMSHT;
        }

        public void setSTMSHT(String STMSHT) {
            this.STMSHT = STMSHT;
        }

        public String getSTMDEP() {
            return STMDEP;
        }

        public void setSTMDEP(String STMDEP) {
            this.STMDEP = STMDEP;
        }

        public String getSTMADD() {
            return STMADD;
        }

        public void setSTMADD(String STMADD) {
            this.STMADD = STMADD;
        }

        public String getSTMZIP() {
            return STMZIP;
        }

        public void setSTMZIP(String STMZIP) {
            this.STMZIP = STMZIP;
        }

        public String getLEGCYC() {
            return LEGCYC;
        }

        public void setLEGCYC(String LEGCYC) {
            this.LEGCYC = LEGCYC;
        }

        public String getLEGCDT() {
            return LEGCDT;
        }

        public void setLEGCDT(String LEGCDT) {
            this.LEGCDT = LEGCDT;
        }

        public String getLEGFMT() {
            return LEGFMT;
        }

        public void setLEGFMT(String LEGFMT) {
            this.LEGFMT = LEGFMT;
        }

        public String getLEGSHT() {
            return LEGSHT;
        }

        public void setLEGSHT(String LEGSHT) {
            this.LEGSHT = LEGSHT;
        }

        public String getLEGDEP() {
            return LEGDEP;
        }

        public void setLEGDEP(String LEGDEP) {
            this.LEGDEP = LEGDEP;
        }

        public String getLEGADD() {
            return LEGADD;
        }

        public void setLEGADD(String LEGADD) {
            this.LEGADD = LEGADD;
        }

        public String getLEGZIP() {
            return LEGZIP;
        }

        public void setLEGZIP(String LEGZIP) {
            this.LEGZIP = LEGZIP;
        }

        public String getACTTYP() {
            return ACTTYP;
        }

        public void setACTTYP(String ACTTYP) {
            this.ACTTYP = ACTTYP;
        }

        public String getACTGLC() {
            return ACTGLC;
        }

        public void setACTGLC(String ACTGLC) {
            this.ACTGLC = ACTGLC;
        }

        public String getACTPLC() {
            return ACTPLC;
        }

        public void setACTPLC(String ACTPLC) {
            this.ACTPLC = ACTPLC;
        }

        public String getDEPNUM() {
            return DEPNUM;
        }

        public void setDEPNUM(String DEPNUM) {
            this.DEPNUM = DEPNUM;
        }

        public String getLENTDT() {
            return LENTDT;
        }

        public void setLENTDT(String LENTDT) {
            this.LENTDT = LENTDT;
        }

        public String getDRACCM() {
            return DRACCM;
        }

        public void setDRACCM(String DRACCM) {
            this.DRACCM = DRACCM;
        }

        public String getCRACCM() {
            return CRACCM;
        }

        public void setCRACCM(String CRACCM) {
            this.CRACCM = CRACCM;
        }

        public String getCQEFLG() {
            return CQEFLG;
        }

        public void setCQEFLG(String CQEFLG) {
            this.CQEFLG = CQEFLG;
        }

        public String getBALLIM() {
            return BALLIM;
        }

        public void setBALLIM(String BALLIM) {
            this.BALLIM = BALLIM;
        }

        public String getOVELIM() {
            return OVELIM;
        }

        public void setOVELIM(String OVELIM) {
            this.OVELIM = OVELIM;
        }

        public String getOVEEXP() {
            return OVEEXP;
        }

        public void setOVEEXP(String OVEEXP) {
            this.OVEEXP = OVEEXP;
        }

        public String getOPNDAT() {
            return OPNDAT;
        }

        public void setOPNDAT(String OPNDAT) {
            this.OPNDAT = OPNDAT;
        }

        public String getCLSDAT() {
            return CLSDAT;
        }

        public void setCLSDAT(String CLSDAT) {
            this.CLSDAT = CLSDAT;
        }

        public String getREGSTS() {
            return REGSTS;
        }

        public void setREGSTS(String REGSTS) {
            this.REGSTS = REGSTS;
        }

        public String getFRZSTS() {
            return FRZSTS;
        }

        public void setFRZSTS(String FRZSTS) {
            this.FRZSTS = FRZSTS;
        }

        public String getACTSTS() {
            return ACTSTS;
        }

        public void setACTSTS(String ACTSTS) {
            this.ACTSTS = ACTSTS;
        }

        public String getAMDTLR() {
            return AMDTLR;
        }

        public void setAMDTLR(String AMDTLR) {
            this.AMDTLR = AMDTLR;
        }

        public String getUPDDAT() {
            return UPDDAT;
        }

        public void setUPDDAT(String UPDDAT) {
            this.UPDDAT = UPDDAT;
        }

        public String getRECSTS() {
            return RECSTS;
        }

        public void setRECSTS(String RECSTS) {
            this.RECSTS = RECSTS;
        }
    }

    @Override
    public String toString() {
        this.INFO.TXN_CODE = "9009062";
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(ToaXml9009062.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    public static ToaXml9009062 getToa(String xml) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(ToaXml9009062.class);
        return (ToaXml9009062) xs.fromXML(xml);
    }
}

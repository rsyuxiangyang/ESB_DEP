package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.txn.*;
import org.fbi.dep.model.txn.sbs.*;
import org.fbi.dep.util.StringPad;
import org.fbi.endpoint.eai.transCreditInfoFromSBStoJDE.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-7-25
 * Time: ����4:38
 * To change this template use File | Settings | File Templates.
 */
public class SbsTxnDataTransform {

    private static Logger logger = LoggerFactory.getLogger(SbsTxnDataTransform.class);

    public static byte[] convertToTxnN022(TiaXml9009003 tia, String termId) {

        List<String> tiaList = new ArrayList<String>();
        tiaList.add(tia.BODY.FBTIDX);
        tiaList.add(tia.BODY.REQNUM);
        tiaList.add(tia.BODY.ORDDAT);
        tiaList.add(tia.BODY.CHQNUM);
        return convert("n022", termId, tiaList);
    }

    public static byte[] convertToTxnN02b(TiaXml9009002 tia, String termId) {
        List<String> tiaList = new ArrayList<String>();
        tiaList.add(tia.INFO.REQ_SN);
        try {
            Field[] fields = tia.BODY.getClass().getFields();
            Object obj = null;
            for (Field field : fields) {
//                logger.info("Field:" + field.getName());
                obj = field.get(tia.BODY);
                if (obj != null) {
                    tiaList.add(obj.toString());
                } else {
                    tiaList.add("");
                }
            }
        } catch (Exception e) {
            logger.error("SBS������װ�쳣", e);
            throw new RuntimeException("SBS������װ�쳣");
        }
        return convert("n02b", termId, tiaList);
    }

    public static byte[] convertToTxnN020(TiaXml9009002 tia, String termId) {
        List<String> tiaList = new ArrayList<String>();
        tiaList.add(tia.INFO.REQ_SN);
        try {
            Field[] fields = tia.BODY.getClass().getFields();
            Object obj = null;
            for (Field field : fields) {
//                logger.info("Field:" + field.getName());
                obj = field.get(tia.BODY);
                if (obj != null) {
                    tiaList.add(obj.toString());
                } else {
                    tiaList.add("");
                }
            }
        } catch (Exception e) {
            logger.error("SBS������װ�쳣", e);
            throw new RuntimeException("SBS������װ�쳣");
        }
        return convert("n020", termId, tiaList);
    }

    public static byte[] convertToTxnAa41(String sn, String outActno, String inActno, String amt, String termId, String remark) {
        if (StringUtils.isEmpty(remark)) remark = "�ʽ𽻻�ƽ̨9009001";
        List<String> tiaList = assembleTaa41Param(sn, outActno, inActno, new BigDecimal(amt), remark, "", "");
        return convert("aa41", termId, tiaList);
    }

    public static byte[] convertToTxnAa41(String sn, String outActno, String inActno, String amt, String termId, String remark,
                                          String outActnam, String inActnam) {
        if (StringUtils.isEmpty(remark)) remark = "�ʽ𽻻�ƽ̨9009001";
        List<String> tiaList = assembleTaa41Param(sn, outActno, inActno, new BigDecimal(amt), remark, outActnam, inActnam);
        return convert("aa41", termId, tiaList);
    }

    public static byte[] convertToTxnAa4b(String sn, String outActno, String outActName, String inActno,
                                          String inActName, String amt,
                                          String inActno2, String inActName2, String amt2, String termId, String remark) {
        if (StringUtils.isEmpty(remark)) remark = "�ʽ𽻻�ƽ̨9009004";
        List<String> tiaList = assembleTaa4bParam(sn, outActno, outActName, inActno, inActName, amt, inActno2, inActName2, amt2, remark);
        return convert("aa4b", termId, tiaList);
    }

    public static byte[] convertToTxnN080(TiaXml9009101 tia, String termId) {
        return convert("n080", termId, assembleTn080Param(tia));
    }

    public static byte[] convertToTxn8855(TiaXml9009060 tia, String termId) {
        return convert("8855", termId, assembleT8855Param(tia));
    }

    public static byte[] convertToTxn8856(TiaXml9009061 tia, String termId) {
        return convert("8856", termId, assembleT8856Param(tia));
    }

    public static byte[] convertToTxn8118(TiaXml9009062 tia, String termId) {
        return convert("8118", termId, assembleT8118Param(tia));
    }

    public static byte[] convertToTxn8118For9009050(TiaXml9009050 tia, String termId) {
        return convert("8118", termId, assembleT8118ParamFor9009050(tia));
    }

    public static byte[] convertToTxn8854For9009051(TiaXml9009051 tia, String termId) {
        return convert("8854", termId, assembleT8854ParamFor9009051(tia));
    }

    public static byte[] convertToTxn8126For9009501(TiaXml9009501 tia, String termId) {
        return convert("8126", termId, assembleT8126ParamFor9009501(tia));
    }

    public static byte[] convertToTxn8014For9009502(TiaXml9009502 tia, String termId) {
        return convert("8014", termId, assembleT8014ParamFor9009502(tia));
    }

    public static byte[] convertToTxna27aFor9009503(TiaXml9009503 tia, String termId) {
        return convert("a27a", termId, assembleTa27aParamFor9009503(tia));
    }

    public static byte[] convertToTxna111For9009504(TiaXml9009504 tia, String termId) {
        return convert("a111", termId, assembleTa111ParamFor9009504(tia));
    }

    public static byte[] convertToTxna121For9009505(TiaXml9009505 tia, String termId) {
        return convert("a121", termId, assembleTa121ParamFor9009505(tia));
    }

    public static byte[] convertToTxna13aFor9009506(TiaXml9009506 tia, String termId) {
        return convert("a13a", termId, assembleTa13aParamFor9009506(tia));
    }

    public static byte[] convertToTxna113For9009507(TiaXml9009507 tia, String termId) {
        return convert("a113", termId, assembleTa113ParamFor9009507(tia));
    }

    public static byte[] convertToTxn8854For9009052(TiaXml9009052 tia, String termId) {
        return convert("8854", termId, assembleT8854ParamFor9009052(tia));
    }

    public static byte[] convertToTxn8858For9009054(TiaXml9009054 tia, String termId) {
        return convert("8858", termId, assembleT8858ParamFor9009054(tia));
    }

    public static byte[] convertToTxn8859For9009055(TiaXml9009055 tia, String termId) {
        return convert("8859", termId, assembleT8859ParamFor9009055(tia));
    }

    public static byte[] convertToTxn8011(TiaXml9009301 tia, String termId) {
        return convert("8011", termId, assembleT8011Param(tia));
    }

    public static byte[] convertToTxn8124(TiaXml9009401 tia, String termId) {
        return convert("8124", termId, assembleT8124Param(tia));
    }

    public static byte[] convertToTxn8872(Tia900012601 tia, String termId) {
        return convert("8872", termId, assembleT8872Param(tia));
    }

    public static byte[] convertToTxn8874(Tia900012602 tia, String termId) {
        return convert("8874", termId, assembleT8874Param(tia));
    }

    public static byte[] convertToTxn8119(Tia900012701 tia, String termId) {
        return convert("8119", termId, assembleT8119Param(tia));
    }

    private static List<String> assembleT8119Param(Tia900012701 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.body.TOTCNT);
        String strTemp="";
        for (Tia900012701.BodyDetail bdUnit: tia.body.DETAILS){
            strTemp+=bdUnit.ACTNM+"    ";
        }
        paramList.add(strTemp);
        return paramList;
    }

    private static List<String> assembleT8872Param(Tia900012601 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.body.TX_DATE);
        return paramList;
    }

    private static List<String> assembleT8874Param(Tia900012602 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.body.TX_DATE);
        paramList.add(tia.body.BEGNUM);
        return paramList;
    }

    private static List<String> assembleT8124Param(TiaXml9009401 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.BODY.CUSIDT);
        paramList.add(tia.BODY.APCODE);
        paramList.add(tia.BODY.CURCDE);
        return paramList;
    }

    private static List<String> assembleT8011Param(TiaXml9009301 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.BODY.CUSNAM);
        paramList.add(tia.BODY.CORADD);
        paramList.add(tia.BODY.ZIPCDE);
        paramList.add(tia.BODY.TELNUM);
        paramList.add(tia.BODY.TELEXN);
        paramList.add(tia.BODY.PASTYP);
        paramList.add(tia.BODY.PASSNO);
        return paramList;
    }

    private static List<String> assembleT8855Param(TiaXml9009060 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.BODY.CUSKID);
        paramList.add(tia.BODY.PASTYP);
        paramList.add(tia.BODY.PASSNO);
        paramList.add(tia.BODY.ACTTYP);
        paramList.add(tia.BODY.BEGNUM);
        return paramList;
    }

    private static List<String> assembleT8856Param(TiaXml9009061 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.BODY.CUSKID);
        paramList.add(tia.BODY.PASTYP);
        paramList.add(tia.BODY.PASSNO);
        paramList.add(tia.BODY.ACTTYP);
        paramList.add(tia.BODY.BEGNUM);
        return paramList;
    }

    private static List<String> assembleT8118Param(TiaXml9009062 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add("111111");
        paramList.add("010"); //��Ա������
        paramList.add("60");  //��Ա���ź�
        paramList.add("010"); //�ʻ�������
        paramList.add(tia.BODY.ACTNUM);
        return paramList;
    }

    private static List<String> assembleT8118ParamFor9009050(TiaXml9009050 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add("111111");
        paramList.add("010"); //��Ա������
        paramList.add("60");  //��Ա���ź�
        paramList.add("010"); //�ʻ�������
        paramList.add(tia.BODY.ACTNUM);
        return paramList;
    }

    private static List<String> assembleT8854ParamFor9009051(TiaXml9009051 tia) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        List<String> paramList = new ArrayList<String>();
        paramList.add("111111");
        paramList.add("010"); //��Ա������
        paramList.add("60");  //��Ա���ź�
        paramList.add("010"); //�ʻ�������
        paramList.add(tia.BODY.ACTNO);
        paramList.add(sdf.format(new Date()));
        paramList.add(sdf.format(new Date()));
        paramList.add(StringPad.leftPad4ChineseToByteLength(tia.BODY.START_NUM,6,"0"));
        return paramList;
    }

    private static List<String> assembleT8126ParamFor9009501(TiaXml9009501 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.getBODY().getACTNUM()); // �ʺ�
        paramList.add(tia.getBODY().getCUSNAM()); // �ͻ���
        paramList.add(tia.getBODY().getPAYTYP()); // ��ͨ������־��0-�رգ�1-��ͨ��
        return paramList;
    }

    private static List<String> assembleT8014ParamFor9009502(TiaXml9009502 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.getBODY().getPASTYP()); // ֤������
        paramList.add(tia.getBODY().getPASSNO()); // ֤������
        paramList.add(tia.getBODY().getINTNET()); // ��ͨ������־��0-�رգ�1-��ͨ��
        return paramList;
    }

    private static List<String> assembleTa27aParamFor9009503(TiaXml9009503 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add("8");                       // ϵͳ��ʶ��8-��λ���ڴ�
        paramList.add(tia.getBODY().getCUSIDT()); // �ͻ���
        paramList.add("0");                       // ȡ�ʽ
        paramList.add(StringUtils.rightPad("", 6, " ")); // �ͻ�����
        paramList.add(tia.getBODY().getACTNM2()); // ����
        paramList.add(tia.getBODY().getPASTYP()); // ֤������
        paramList.add(tia.getBODY().getPASSNO()); // ֤����
        paramList.add(tia.getBODY().getCORADD()); // ��ַ
        paramList.add(tia.getBODY().getTELNUM()); // �绰
        paramList.add(tia.getBODY().getTXNAMT()); // ���׽��
        paramList.add(tia.getBODY().getVALDAT()); // ��Ϣ����
        paramList.add(tia.getBODY().getACTTYP()); // �˻�����
        paramList.add(tia.getBODY().getDPTTYP()); // �������
        paramList.add(tia.getBODY().getDPTPRD()); // ����
        paramList.add("00");                     // ȡϢ����
        paramList.add(tia.getBODY().getATOFLG()); // �Զ�ת���־
        paramList.add("00");                     // ƾ֤����
        paramList.add(StringUtils.rightPad("", 13, " ")); // ƾ֤��
        paramList.add("01");                     // ת���˺�����
        paramList.add(tia.getBODY().getIPTAC1()); // ת���˺�
        paramList.add("0");                       // ת���ʺ�ȡ�ʽ
        paramList.add(StringUtils.rightPad("", 6, " ")); // �ͻ�����
        paramList.add(" ");                      // ֧Ʊ����
        paramList.add(StringUtils.rightPad("", 10, " ")); // ֧Ʊ��
        paramList.add(StringUtils.rightPad("", 12, " ")); // ֧Ʊ����
        paramList.add(StringUtils.rightPad("", 8, " ")); // ǩ����
        paramList.add(StringUtils.rightPad("", 30, " ")); // ժҪ
        paramList.add("");                     // תϢ�˻�������
        paramList.add(""); // תϢ�˻�
        paramList.add("0"); // Э������
        paramList.add("0"); // �˺����뷽ʽ
        paramList.add(StringUtils.rightPad(tia.getINFO().getREQ_SN(), 18, " ")); // ��Χϵͳ��ˮ��
        return paramList;
    }

    private static List<String> assembleTa111ParamFor9009504(TiaXml9009504 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.getBODY().getACTTY2()); // �˻����
        paramList.add(tia.getBODY().getIPTAC2()); // �˻���
        paramList.add(tia.getBODY().getDRAMD2()); // ȡ�ʽ
        paramList.add(tia.getBODY().getCUSPW2()); // �ͻ�����
        paramList.add(tia.getBODY().getADVNUM()); // ֪ͨ����
        paramList.add(tia.getBODY().getTXNDAT()); // ��������
        paramList.add(tia.getBODY().getADVAMT()); // ֧ȡ���
        paramList.add(tia.getBODY().getADVDAT()); // ֧ȡ����
        paramList.add(tia.getBODY().getPASTYP()); // ֤������
        paramList.add(tia.getBODY().getPASSNO()); // ֤����
        paramList.add(tia.getBODY().getREMARK()); // ��ע����ַ��
        paramList.add(tia.getBODY().getMAGFL2()); // �˺����뷽ʽ
        return paramList;
    }

    private static List<String> assembleTa121ParamFor9009505(TiaXml9009505 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.getBODY().getACTTY2()); // �˻����
        paramList.add(tia.getBODY().getIPTAC2()); // �˻���
        paramList.add(tia.getBODY().getDRAMD2()); // ȡ�ʽ
        paramList.add(tia.getBODY().getCUSPW2()); // �ͻ�����
        paramList.add(tia.getBODY().getADVNUM()); // ֪ͨ����
        paramList.add(tia.getBODY().getTXNDAT()); // ��������
        paramList.add(tia.getBODY().getPASTYP()); // ֤������
        paramList.add(tia.getBODY().getPASSNO()); // ֤����
        paramList.add(tia.getBODY().getREMARK()); // ��ע����ַ��
        paramList.add(tia.getBODY().getMAGFL2()); // �˺����뷽ʽ
        return paramList;
    }

    private static List<String> assembleTa13aParamFor9009506(TiaXml9009506 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.getBODY().getACTTY1()); // �˺�����
        paramList.add(tia.getBODY().getIPTAC1()); // �˻���
        paramList.add(tia.getBODY().getDRAMD1()); // ȡ�ʽ
        paramList.add(tia.getBODY().getCUSPW1()); // �ͻ�����
        paramList.add(tia.getBODY().getTXNDAT()); // ��������
        paramList.add(tia.getBODY().getADVNUM()); // ֪ͨ����
        paramList.add(tia.getBODY().getTXNAMT()); // ���׽��
        paramList.add(tia.getBODY().getACTTY2()); // ת���ʺ�����
        paramList.add(tia.getBODY().getIPTAC2()); // ת���ʺ�
        paramList.add(tia.getBODY().getPASTYP()); // ֤������
        paramList.add(tia.getBODY().getPASSNO()); // ֤������
        paramList.add(tia.getBODY().getREMARK()); // ժҪ
        paramList.add(tia.getBODY().getANACDE()); // ����ͳ����
        paramList.add(tia.getBODY().getMAGFL1()); // ˢ���������ʺű�־
        paramList.add(tia.getBODY().getMAGFL2()); // �����ֶ�
        paramList.add(tia.getBODY().getBOKNUM()); // ��Χϵͳ��ˮ��
        return paramList;
    }

    private static List<String> assembleTa113ParamFor9009507(TiaXml9009507 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(getStrIgnoreNull(tia.getBODY().getBOKNUM())); // �˻���
        paramList.add(getStrIgnoreNull(tia.getBODY().getADVFLG())); // ֪ͨ״̬��1-֪ͨ,2-ȡ��,3-������
        paramList.add(getStrIgnoreNull(tia.getBODY().getVALDAT())); // ֪ͨ����
        paramList.add(getStrIgnoreNull(tia.getBODY().getSGNDAT())); // Э��ȡ����
        paramList.add(getStrIgnoreNull(tia.getBODY().getCUSPW1())); // ��ʼ���
        paramList.add(getStrIgnoreNull(tia.getBODY().getPASTYP())); // ֤������
        paramList.add(getStrIgnoreNull(tia.getBODY().getPASSNO())); // ֤����
        return paramList;
    }

    public static String getStrIgnoreNull(String strValue){
        return strValue==null?"":strValue;
    }

    private static List<String> assembleT8854ParamFor9009052(TiaXml9009052 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add("111111");
        paramList.add("010"); //��Ա������
        paramList.add("60");  //��Ա���ź�
        paramList.add("010"); //�ʻ�������
        paramList.add(tia.BODY.ACTNO);
        paramList.add(tia.BODY.START_DATE);
        paramList.add(tia.BODY.END_DATE);
        paramList.add(StringPad.leftPad4ChineseToByteLength(tia.BODY.START_NUM,6,"0"));
        return paramList;
    }

    private static List<String> assembleT8858ParamFor9009054(TiaXml9009054 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.BODY.ACTNUM);
        paramList.add(StringPad.leftPad4ChineseToByteLength(tia.BODY.BEGNUM,6,"0"));
        return paramList;
    }

    private static List<String> assembleT8859ParamFor9009055(TiaXml9009055 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(tia.BODY.ACTNUM);
        paramList.add(tia.BODY.BEGDAT);
        paramList.add(tia.BODY.ENDDAT);
        paramList.add(StringPad.leftPad4ChineseToByteLength(tia.BODY.BEGNUM,6,"0"));
        return paramList;
    }

    private static List<String> assembleTn080Param(TiaXml9009101 tia) {
        List<String> paramList = new ArrayList<String>();
        paramList.add(StringUtils.rightPad(tia.BODY.BANKSN, 18, ' '));
        paramList.add(StringUtils.rightPad(tia.INFO.REQ_SN, 16, ' '));
        paramList.add(tia.BODY.BNKDAT);
        paramList.add(tia.BODY.BNKTIM);
        paramList.add(tia.BODY.PBKNUM);
        paramList.add(tia.BODY.PBKNAM);
        paramList.add(tia.BODY.PAYACT);
        paramList.add(tia.BODY.PAYNAM);
        paramList.add(tia.BODY.TXNAMT);
        paramList.add(tia.BODY.DCTYPE);
        paramList.add(tia.BODY.RECACT);
        paramList.add(tia.BODY.RECNAM);
        paramList.add("907452000016");
        paramList.add("�������Ų����������ι�˾");
        paramList.add(tia.BODY.IBKACT);
        paramList.add(tia.BODY.IBKNUM);
        paramList.add(tia.BODY.IBKNAM);
        paramList.add(tia.BODY.RETAUX);
        return paramList;
    }

    private static List<String> assembleTaa41Param(String sn, String fromAcct, String toAcct, BigDecimal txnAmt, String remark,
                                                   String outActnam, String inActnam) {

        // ת���˻�
        String outAct = StringUtils.rightPad(fromAcct, 22, ' ');
        // ת���˻�
        String inAct = StringUtils.rightPad(toAcct, 22, ' ');
        if(StringUtils.isEmpty(outActnam)) {
            outActnam = StringUtils.leftPad("", 72, ' ');
        } else {
            outActnam = outActnam + StringUtils.leftPad("", 72-outActnam.getBytes().length, ' ');
        }

        if(StringUtils.isEmpty(inActnam)) {
            inActnam = StringUtils.leftPad("", 72, ' ');
        } else {
            inActnam = inActnam + StringUtils.leftPad("", 72-inActnam.getBytes().length, ' ');
        }

        DecimalFormat df = new DecimalFormat("#############0.00");
        List<String> txnparamList = new ArrayList<String>();
        String txndate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        //ת���ʻ�����
        txnparamList.add("01");
        //ת���ʻ�
        txnparamList.add(outAct);
        //ȡ�ʽ
        txnparamList.add("3");
        //ת���ʻ�����
        txnparamList.add(outActnam);
        //ȡ������
        txnparamList.add(StringUtils.leftPad("", 6, ' '));
        //֤������
        txnparamList.add("N");
        //��Χϵͳ��ˮ��
        txnparamList.add(StringUtils.rightPad(sn, 18, ' '));      //������ˮ��
        //֧Ʊ����
        txnparamList.add(" ");
        //֧Ʊ��
        txnparamList.add(StringUtils.leftPad("", 10, ' '));
        //֧Ʊ����
        txnparamList.add(StringUtils.leftPad("", 12, ' '));
        //ǩ������
        txnparamList.add(StringUtils.leftPad("", 8, ' '));
        //���۱�ʶ
        txnparamList.add("3");
        //�����ֶ�
        txnparamList.add(StringUtils.leftPad("", 8, ' '));
        //�����ֶ�
        txnparamList.add(StringUtils.leftPad("", 4, ' '));

        //���׽��
        String amt = df.format(txnAmt);
        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));   //���

        //ת���ʻ�����
        txnparamList.add("01");
        //ת���ʻ� (�̻��ʺ�)
        String account = StringUtils.rightPad(inAct, 22, ' ');
        txnparamList.add(account);

        //ת���ʻ�����
        txnparamList.add(inActnam);
        //���۱�ʶ
        txnparamList.add(" ");
        //��������
        txnparamList.add(txndate);
        //ժҪ
        txnparamList.add(StringPad.rightPad4ChineseToByteLength(remark, 25, " "));
        //��Ʒ��
        txnparamList.add(StringUtils.leftPad("", 4, ' '));
        //MAGFL1
        txnparamList.add(" ");
        //MAGFL2
        txnparamList.add(" ");

        return txnparamList;
    }

    private static List<String> assembleTaa4bParam(String sn, String fromAcct, String outActName, String toAcct,
                                                   String inActName, String txnAmt,
                                                   String toAcct2, String inActName2,
                                                   String txnAmt2, String remark) {
//        DecimalFormat df = new DecimalFormat("#############0.00");
        List<String> txnparamList = new ArrayList<String>();
        String txndate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        //ת���ʻ�����
        txnparamList.add("01");
        //ת���ʻ�
        txnparamList.add(fromAcct);
        //ȡ�ʽ
        txnparamList.add("3");
        //ת���ʻ�����
        txnparamList.add(outActName);
        //ȡ������
        txnparamList.add(StringUtils.leftPad("", 6, ' '));
        //֤������
        txnparamList.add("N");
        //��Χϵͳ��ˮ��
        txnparamList.add(StringUtils.rightPad(sn, 18, ' '));      //������ˮ��
        //֧Ʊ����
        txnparamList.add(" ");
        //֧Ʊ��
        txnparamList.add("");
        //֧Ʊ����
        txnparamList.add("");
        //ǩ������
        txnparamList.add("");
        //���۱�ʶ
        txnparamList.add("3");
        //�����ֶ�
        txnparamList.add("");
        //�����ֶ�
        txnparamList.add("");

        //���׽��
//        String amt = df.format(txnAmt);
//        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));   //���

        txnparamList.add(txnAmt);

        //ת���ʻ�����
        txnparamList.add("01");
        //ת���ʻ� (�̻��ʺ�)
        txnparamList.add(toAcct);

        //ת���ʻ�����
        txnparamList.add(inActName);
        //���۱�ʶ
        txnparamList.add(" ");
        //��������
        txnparamList.add(txndate);
        //ժҪ
        txnparamList.add(remark);
        //��Ʒ��
        txnparamList.add("N201");
        //MAGFL1
        txnparamList.add(" ");
        //MAGFL2
        txnparamList.add(" ");
        // ��ת���ʻ�
        txnparamList.add(toAcct2);
        // ��ת����
//        txnparamList.add(df.format(txnAmt2));
        txnparamList.add(txnAmt2);

        // ��ת���˻��� 20150416
        logger.info("INACTNAME2:" + inActName2);
        txnparamList.add(inActName2);

        return txnparamList;
    }

    private static byte[] convert(String txnCode, String termID, List<String> tiaList) {
//        String header = "TPEI" + txnCode + "  010       " + termID + "MT01                       ";
        // 2015-06-02 ��Ȩ��Ա����Ϊ9999
        String header = "TPEI" + txnCode + "  010       " + termID + "MT019999                   ";
        byte[] tiaBuf = new byte[32000];
        byte[] bytes = header.getBytes();
        System.arraycopy(bytes, 0, tiaBuf, 0, bytes.length);
        try {
            setBufferValues(tiaList, tiaBuf);
        } catch (UnsupportedEncodingException e) {
            logger.error("Unsupported  Encoding", e);
        }
        return tiaBuf;
    }

    private static void setBufferValues(List list, byte[] bb) throws UnsupportedEncodingException {
        int start = 51;
        for (int i = 1; i <= list.size(); i++) {
            String value = list.get(i - 1).toString();
            setVarData(start, value, bb);
            start = start + value.getBytes("GBK").length + 2;
        }
    }

    private static void setVarData(int pos, String data, byte[] aa) throws UnsupportedEncodingException {
        short len = (short) data.getBytes("GBK").length;

        byte[] slen = new byte[2];
        slen[0] = (byte) (len >> 8);
        slen[1] = (byte) (len);
        System.arraycopy(slen, 0, aa, pos, 2);
        System.arraycopy(data.getBytes(), 0, aa, pos + 2, len);
    }

}

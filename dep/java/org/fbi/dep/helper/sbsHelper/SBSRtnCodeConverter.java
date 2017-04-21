package org.fbi.dep.helper.sbsHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SBSRtnCodeConverter {

    private static final Logger logger = LoggerFactory.getLogger(SBSRtnCodeConverter.class);
    public static void main(String[] args) {
        String code = GetRtnCodeFromSBSRtnCode("InnerSBS", "T543");
        System.out.println(code);
    }

    /**
     * @param strRecvRtnCodePara ��SBS���صķ�����
     * @return ����DEP��װ��ķ�����
     */
    public static String GetRtnCodeFromSBSRtnCode(String xmlNamePara, String strRecvRtnCodePara) {
        // xml�����ļ��в����ڵķ����룬�̶�����Ϊ2000������ԭ��
        String strDepRtnCodeTmp = "2000";

        if(strRecvRtnCodePara.length() != 4 ){
            return strDepRtnCodeTmp;
        }

        String xmlName="";
        if("InnerSBS".equals(xmlNamePara)) {
            xmlName = "/RtnIdInnerTradeContrastTbl.xml";
        }else if("OuterSBS".equals(xmlNamePara)) {
            xmlName = "/RtnIdOuterTradeContrastTbl.xml";
        }else {
            throw new RuntimeException("��������");
        }
        // ��T������W������WB02��ͷ�ķ����룬�̶�����ΪT___����W___
        if(!strRecvRtnCodePara.equals("WB02") && (strRecvRtnCodePara.startsWith("T") || strRecvRtnCodePara.startsWith("W"))){
            strRecvRtnCodePara = strRecvRtnCodePara.substring(0,1) + "___";
        }

        // ��ȡxml�����ļ�������ȡ�÷��������ļ��еĶ�Ӧֵ
        String xmlFile = readFile(xmlName);
        List<RtnIdContrastTbl.Type> typeList = new RtnIdContrastTbl().toBean(xmlFile).TypeList;
        for (RtnIdContrastTbl.Type type : typeList) {
            String [] stringArrTmp= type.Recvs.split("\\,");  //ע��ָ�������Ҫת���...
            List<String> stringListTmp= Arrays.asList(stringArrTmp);

            if(stringListTmp.contains(strRecvRtnCodePara)) {
                strDepRtnCodeTmp = type.RtnId;
                return strDepRtnCodeTmp;
            }
        }

        return strDepRtnCodeTmp;
    }

    private static String readFile(String fileName) {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(SBSRtnCodeConverter.class.getResourceAsStream(fileName)));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                buffer.append(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return buffer.toString();
    }
}


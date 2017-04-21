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
     * @param strRecvRtnCodePara 从SBS返回的返回码
     * @return 经过DEP包装后的返回码
     */
    public static String GetRtnCodeFromSBSRtnCode(String xmlNamePara, String strRecvRtnCodePara) {
        // xml配置文件中不存在的返回码，固定设置为2000（不明原因）
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
            throw new RuntimeException("参数错误！");
        }
        // 以T或者是W但不是WB02开头的返回码，固定设置为T___或者W___
        if(!strRecvRtnCodePara.equals("WB02") && (strRecvRtnCodePara.startsWith("T") || strRecvRtnCodePara.startsWith("W"))){
            strRecvRtnCodePara = strRecvRtnCodePara.substring(0,1) + "___";
        }

        // 读取xml配置文件并遍历取得返回码在文件中的对应值
        String xmlFile = readFile(xmlName);
        List<RtnIdContrastTbl.Type> typeList = new RtnIdContrastTbl().toBean(xmlFile).TypeList;
        for (RtnIdContrastTbl.Type type : typeList) {
            String [] stringArrTmp= type.Recvs.split("\\,");  //注意分隔符是需要转译滴...
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


package org.fbi.endpoint.chinapaysh;

import chinapay.Base64;
import chinapay.PrivateKey;
import chinapay.SecureLink;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.chinapaysh.util.DigestMD5;
import org.fbi.endpoint.chinapaysh.util.MsgUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �Ϻ������ӽ��ܸ�����
 */
public class ChinapayShTxnHandler {

    private Log logger = LogFactory.getLog(this.getClass());

    public static final String BATCH_FILE_PATH = PropertyManager.getProperty("chinapaysh_path_batchfile");

    public static final String BATCH_MER_ID = PropertyManager.getProperty("chinapaysh_haierfc_batch_merid");
    public static final String BATCH_MER_KEY_PATH = PropertyManager.getProperty("chinapaysh_crypt_path_batch_merprk");
    public static final String BATCH_PUB_KEY_PATH = PropertyManager.getProperty("chinapaysh_crypt_path_batch_pgpubk");
    public static final String BATCH_FILE_QUERY_URL = PropertyManager.getProperty("chinapaysh_server_http_batch_query_url");
    public static final String BATCH_FILE_UPLOAD_URL = PropertyManager.getProperty("chinapaysh_server_http_batch_cut_url");
    public static final String BATCH_FILE_DOWNLOAD_URL = PropertyManager.getProperty("chinapaysh_server_http_batch_download_url");

    public static final String SINGLE_MER_ID = PropertyManager.getProperty("chinapaysh_haierfc_single_merid");
    public static final String SINGLE_KEY_MER_ID = PropertyManager.getProperty("chinapaysh_haierfc_single_key_merid");
    public static final String SINGLE_MER_KEY_PATH = PropertyManager.getProperty("chinapaysh_crypt_path_single_merprk");
    public static final String SINGLE_PUB_KEY_PATH = PropertyManager.getProperty("chinapaysh_crypt_path_single_pgpubk");
    public static final String SINGLE_QUERY_URL = PropertyManager.getProperty("chinapaysh_server_http_single_query_url");
    public static final String SINGLE_CUT_URL = PropertyManager.getProperty("chinapaysh_server_http_single_cut_url");

    // �ϴ������ļ�
    public Map<String, String> uploadBatchFile(String fileName, String fileContent) throws Exception {

        // ���ɲ���
        List<NameValuePair> nvps = genBatchCutCryptParams(fileName, fileContent);
        // �������󣬻�ȡ��Ӧ����
        String responseBody = new ChinapayShHttpClient(BATCH_FILE_UPLOAD_URL).doPost("UTF-8", nvps);
        // ���Ӧ��������
        // ���յ���ChinaPayӦ�𴫻ص���ν�����ǩ
        attestBatchTxnMsg(responseBody);
        return getResponseValues(responseBody);
    }

    // ���ػ����ļ�
    public Map<String, String> downloadNoticeFile(String orFileName, String fileName) throws Exception {

        // ���ɲ���
        List<NameValuePair> nvps = genDownloadCryptParams(orFileName, fileName);
        // �������󣬻�ȡ��Ӧ����
        String responseBody = new ChinapayShHttpClient(BATCH_FILE_DOWNLOAD_URL).doPost("UTF-8", nvps);
        // ���Ӧ��������
        Map<String, String> resValues = getResponseValues(responseBody);
        String params[] = responseBody.split("&");
        if (params.length == 5) {
            String encodeFileData = params[3].substring(params[3].indexOf("=") + 1);
            String fileData = new String(MsgUtil.decodeInflate(encodeFileData.getBytes()), "UTF-8");
            boolean chkFlag = DigestMD5.MD5Verify(fileData.getBytes("UTF-8"), resValues.get("chkValue"), BATCH_PUB_KEY_PATH);
            if (!chkFlag) {
                logger.error("���ر���ǩ�����ݲ�ƥ�䣡[responsebody]---" + responseBody);
            }
            resValues.put("fileData", fileData);
        }
        return resValues;
    }

    // ��ѯ�����ļ��ϴ�������
    public Map<String, String> qryBatchFileStatus(String fileName) throws Exception {

        String signMsg = BATCH_MER_ID + fileName;
        // ���ɲ���
        List<NameValuePair> nvps = genBatchQryCryptParams(fileName, signMsg);
        // �������󣬻�ȡ��Ӧ����
        String responseBody = new ChinapayShHttpClient(BATCH_FILE_QUERY_URL).doPost("UTF-8", nvps);
        // ���Ӧ��������
        // ���յ���ChinaPayӦ�𴫻ص���ν�����ǩ
        attestBatchTxnMsg(responseBody);
        return getResponseValues(responseBody);
    }

    // ���ʴ���
    public Map<String, String> singleCut(String signMsg, List<NameValuePair> nvps) throws Exception {

        nvps.add(new BasicNameValuePair("chkValue", signToChkValue(signMsg)));
        String responseBody = new ChinapayShHttpClient(SINGLE_CUT_URL).doPost("GBK", nvps);
        if (attestSingleTxnMsg(responseBody)) {
            return getResponseValues(responseBody);
        }
        return null;
    }

    // ���ʲ�ѯ
    public Map<String, String> singleQry(String signMsg, List<NameValuePair> nvps) throws Exception {

        nvps.add(new BasicNameValuePair("chkValue", signToChkValue(signMsg)));
        String responseBody = new ChinapayShHttpClient(SINGLE_QUERY_URL).doPost("GBK", nvps);
        attestSingleTxnMsg(responseBody);
        return getResponseValues(responseBody);
    }

    // ���ʴ���ǩ��
    private String signToChkValue(String signMsg) {
        String base64Msg = new String(Base64.encode(signMsg.toString().getBytes()));
        PrivateKey key = new PrivateKey();
        boolean keyFlag = key.buildKey(SINGLE_MER_ID, 0, ChinapayShTxnHandler.SINGLE_MER_KEY_PATH);
        if (!keyFlag) {
            logger.error("����˽Կ����ʧ�ܣ�������ǩ��");
            throw new RuntimeException("����˽Կ����ʧ�ܣ�������ǩ��");
        }
        SecureLink secureLink = new SecureLink(key);
        String chkValue = secureLink.Sign(base64Msg);
        return chkValue;
    }

    // ��ȡ�ļ���Base64���롢ѹ��
    private String readBatchFileToBase64(String fileName) throws Exception {
        File file = new File(BATCH_FILE_PATH + fileName);
        byte[] fileContentBase64Bytes = MsgUtil.getBytes(file);
        return new String(fileContentBase64Bytes, "UTF-8");
    }

    // ���ɻ����ļ����ؽ���HTTP������
    private List<NameValuePair> genDownloadCryptParams(String orFileName, String fileName) throws Exception {
        String signMsg = BATCH_MER_ID + orFileName + fileName;
        // ����Ҫ�ϴ����ֶ�ǩ��
        String chkValue = DigestMD5.MD5Sign(BATCH_MER_ID, signMsg.getBytes("UTF-8"), BATCH_MER_KEY_PATH);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("merId", BATCH_MER_ID));
        nvps.add(new BasicNameValuePair("fileName", fileName));
        nvps.add(new BasicNameValuePair("orFileName", orFileName));
        nvps.add(new BasicNameValuePair("chkValue", chkValue));
        return nvps;
    }

    // ���������ϴ��ļ�����HTTP������ signMsgΪ��ǩ�ֶΣ�һ��Ϊ�ļ�����
    private List<NameValuePair> genBatchCutCryptParams(String fileName, String fileContent) throws Exception {
        // ����Ҫ�ϴ����ֶ�ǩ��
        String chkValue = DigestMD5.MD5Sign(BATCH_MER_ID, fileName, fileContent.getBytes("UTF-8"), BATCH_MER_KEY_PATH);
        String fileContentBase64 = readBatchFileToBase64(fileName);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("merId", BATCH_MER_ID));
        nvps.add(new BasicNameValuePair("fileName", fileName));
        nvps.add(new BasicNameValuePair("fileContent", fileContentBase64));
        nvps.add(new BasicNameValuePair("chkValue", chkValue));
        return nvps;
    }

    // ����������ѯHTTP������ signMsgΪ��ǩ�ֶ�
    private List<NameValuePair> genBatchQryCryptParams(String fileName, String signMsg) throws Exception {
        // ����Ҫ�ϴ����ֶ�ǩ��
        String chkValue = DigestMD5.MD5Sign(BATCH_MER_ID, signMsg.getBytes("UTF-8"), BATCH_MER_KEY_PATH);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("merId", BATCH_MER_ID));
        nvps.add(new BasicNameValuePair("fileName", fileName));
        nvps.add(new BasicNameValuePair("chkValue", chkValue));
        return nvps;
    }


    // ����������Ӧ������ǩ
    private boolean attestBatchTxnMsg(String responseBody) throws Exception {
        int mingIndex = responseBody.lastIndexOf("=");
        String mingParam = responseBody.substring(0, mingIndex + 1);
        String resChkValue = responseBody.substring(mingIndex + 1);
        boolean chkFlag = DigestMD5.MD5Verify(mingParam.getBytes("UTF-8"), resChkValue, BATCH_PUB_KEY_PATH);
        if (!chkFlag) {
            logger.error("���ر���ǩ�����ݲ�ƥ�䣡[responsebody]---" + responseBody);
            throw new RuntimeException("���ر���ǩ�����ݲ�ƥ�䣡");
        } else return true;
    }

    // ���ʽ�����Ӧ������ǩ
    private boolean attestSingleTxnMsg(String responseBody) throws Exception {
        int mingIndex = responseBody.lastIndexOf("=");
        String mingParam = responseBody.substring(0, mingIndex + 1);
        String resChkValue = responseBody.substring(mingIndex + 1);

        chinapay.PrivateKey key = new chinapay.PrivateKey();
        boolean buildFlag = key.buildKey(SINGLE_KEY_MER_ID, 0, SINGLE_PUB_KEY_PATH);
        if (buildFlag == false) {
            logger.error("build key error!");
            return false;
        }
        chinapay.SecureLink secureLink = new chinapay.SecureLink(key);
        boolean chkFlag = secureLink.verifyAuthToken(new String(Base64.encode(mingParam.getBytes())), resChkValue);
        if (!chkFlag) {
            logger.error("���ʴ��۷��ر���ǩ�����ݲ�ƥ�䣡[responsebody]" + responseBody);
            throw new RuntimeException("���ʴ��۷��ر���ǩ�����ݲ�ƥ�䣡");
        } else return true;
    }

    // ������Ӧ����
    private Map<String, String> getResponseValues(String responseBody) {
        String[] pairs = responseBody.split("&");
        Map<String, String> resPairs = new HashMap<String, String>();
        for (String str : pairs) {
            String[] aPair = str.split("=");
            resPairs.put(aPair[0].trim().replace("\r", "").replace("\n", ""),
                    aPair[1].trim().replace("\r", "").replace("\n", ""));
        }
        return resPairs;
    }

}

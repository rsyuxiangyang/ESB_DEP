package org.fbi.endpoint.sms;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.fbi.dep.util.PropertyManager;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * ���Ͷ��ŵĹ�����
 */
public class SmsTool {
    //�������ļ��л�ȡ���Ͷ��ŵ�����.
    private static String OPERID = PropertyManager.getProperty("SMS_OPERID");
    private static String OPERPASS = PropertyManager.getProperty("SMS_OPERPASS");
    private static String SMS_SEND_URL = PropertyManager.getProperty("SMS_SEND_URL");
    //ʧ���ظ�����
    private final static int REPEAT_NUM = Integer.parseInt(PropertyManager.getProperty("SMS_REPEAT_NUM").trim());

    protected final static Log logger = LogFactory.getLog(SmsTool.class);
    protected final static String PREFIX = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n<CoreSMS><OperID>" + OPERID
            + "</OperID><OperPass>" + OPERPASS + "</OperPass><Action>Submit</Action>"
            + "<Category>0</Category><Body><SendTime></SendTime><AppendID></AppendID>";
    protected final static String SUFFIX = "</Body></CoreSMS>";

    public static String[] getPhoneNumberList(String bizCode) {
        String smsFile = readFile("/SmsPhoneNumberList.xml");
        List<PhoneNumberList.Sms> smsList = new PhoneNumberList().toBean(smsFile).SmsList;
        for (PhoneNumberList.Sms sms : smsList) {
            if (bizCode.equals(sms.bizCode)) {
                return sms.phoneNumber.split(",");
            }
        }
        return null;
    }

    private static String readFile(String fileName) {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(SmsTool.class.getResourceAsStream(fileName)));
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

    private static String buildMessage(String cellphone, String message) {
        StringBuilder builder = new StringBuilder(PREFIX);
        builder.append("<Message>");
        builder.append("<DesMobile>").append(cellphone).append("</DesMobile>");
        builder.append("<Content>").append(message).append("</Content>");
        builder.append("</Message>");
        builder.append(SUFFIX);
        return builder.toString();
    }

    private static boolean executeSend(String msg) throws HttpException, IOException {
        if (logger.isInfoEnabled()) {
            logger.info("����������ƽ̨����:" + msg);
        }

        HttpClient httpclient = new DefaultHttpClient();
        String content = "";
        try {
            HttpPost httppost = new HttpPost(SMS_SEND_URL);

            //����ʱ
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000 * 5);
            //��ȡ��ʱ
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000 * 10);

            StringEntity xmlSE = new StringEntity(msg, "GBK");
            httppost.setEntity(xmlSE);

            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
                logger.error("����ƽ̨ͨѶ��Ӧ:" + statusCode);
                return false;
            }

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                content = EntityUtils.toString(entity);
            } else {
                logger.error("����ƽ̨ͨѶ��Ӧ��������Ϊ��");
                return false;
            }
        } finally {
            httpclient.getConnectionManager().shutdown();
        }


        return verifyResponseResult(content);
    }

    //У�����ƽ̨��Ӧ���ĵ���Ӧ��
    private static boolean verifyResponseResult(String content) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new ByteArrayInputStream(content.getBytes()));
            logger.info("����ƽ̨ͨѶ��Ӧ����:" + document.asXML());
            Element root = document.getRootElement();
            String[] values = new String[3];
            Boolean found = false;
            getResultCode(root, values, found);
            String resultCode = "";
            resultCode = values[1];
            if ("0".equals(resultCode)) {
                return true;
            } else {
                logger.error("����ƽ̨ͨѶ��Ӧ����:" + resultCode);
                return false;
            }
        } catch (DocumentException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }


    private static void getResultCode(Element element, String[] results, Boolean found) {
        if (!found) {
            for (Iterator i = element.elementIterator(); i.hasNext() && !found; ) {//��ý����б�
                Element node = (Element) i.next();
                if ("Code".equals(node.getName())) {
                    results[0] = node.getName();
                    results[1] = node.getTextTrim();
                    found = true;
                } else {
                    getResultCode(node, results, found);
                }
            }
        }
    }

    //��˳����ָ���Ķ�Ѷ���ݵ�ָ���ֻ�������, ����: ���Ͷ�Ѷ(message) �� �ֻ�(cellphone)
    public static boolean sendMessage(String cellphone, String message) {

        try {
            if (StringUtils.isEmpty(cellphone)) {
                return true;
            }
            boolean result = executeSend(buildMessage(cellphone, message));
            //FIXME �ر�,����ʧ���Զ��ظ�, ����ط�����
            for (int i = 0; i < REPEAT_NUM && !result; i++) {
                result = executeSend(buildMessage(cellphone, message));
            }
            return result;
        } catch (HttpException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            sendMessage("13905320231", "��������ƽ̨���Զ���");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

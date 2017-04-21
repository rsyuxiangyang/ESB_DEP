import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.fbi.dep.helper.MD5Helper;
import org.fbi.dep.util.StringPad;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * ֪ͨ���-����֪ͨ9009505
 * (��ӦSBS-a121����)
 * �ý������ڳ���������������֪ͨ��Ƭ
 */
public class HttpSbs9009505 {
    private Log logger = LogFactory.getLog(this.getClass());

    private String serverUrl;
    private HttpClient httpclient = null;
    private HttpPost httppost = null;
    public static void main(String[] args) {
        try {
            String xmlmsg = "<?xml version=\"1.0\" encoding=\"GB2312\"?>" +
                    "<ROOT>" +
                        "<INFO>" +
                            "<TXN_CODE>9009505</TXN_CODE>" +         // ���״���
                            "<REQ_SN>WY2015102809191749</REQ_SN>" + // ������ˮ��
                        "</INFO>" +
                        "<BODY>" +
                            "<ACTTY2>07</ACTTY2>" +                      // �˻����RETFIX������
                            "<IPTAC2>010600012700001898</IPTAC2>" +    // �˻��ţ�RETFIX������
                            "<DRAMD2>3</DRAMD2>" +                       // ȡ�ʽ��RETFIX��DRAMDE��0 - ������Ҫ��1 - ƾ����    ��2 - ƾ֤��    ��3 - ƾӡ��    ��4 - ǿ��֧ȡ��
                            "<CUSPW2>111111</CUSPW2>" +                 // �ͻ����루RETFIX������
                            "<ADVNUM>000022918       </ADVNUM>" +      // ֪ͨ���ţ�RETADV��ADVNUM����
                            "<TXNDAT>20151028</TXNDAT>" +               // �������ڣ�RETFIX������
                            "<PASTYP>1</PASTYP>" +                       // ֤�����ࣨRETFIX������
                            "<PASSNO>3702121801690     </PASSNO>" +   // ֤���ţ�RETFIX������
                            "<REMARK>" +                                  // ��ע����ַR ETFIX������
                                StringPad.rightPad4ChineseToByteLength("���Ա�ע", 30, " ") +
                            "</REMARK>" +
                            "<MAGFL2>1</MAGFL2>" +                      // �˺����뷽ʽ��RETFIX������
                        "</BODY>" +
                    "</ROOT>";

            String header = "1.0"          // ͨѶЭ��汾�ţ�Ĭ��Ϊ1.0
                + "0"                      // ������ѹ��ģʽ��0:��ѹ���� 1��zipѹ����Ĭ�ϣ�0
                + "EBKAPP001 "            // ��Χϵͳ�û�ID
                + "9009505   "            // ���׺ţ�Ӧ�뱨�����еĽ��׺�һ��
                + "20151028"              // ��������
                + "155101"                // ����ʱ��
                + "0000"                   // ϵͳ����״̬��Ӧ��
                + "12345678901234567890";// ϵͳ����״̬��Ӧ��Ϣ

            String mac = xmlmsg            // Message Data����
                    + "20151028"         // ��������
                    + "EBKAPP001 "        // ��Χϵͳ�û�ID
                    + "EBK20151009001"; // USER_KEY�ɲ���˾���ÿ���û������·�

            String reqmsg = header +
                    MD5Helper.getMD5String(mac) +
                    xmlmsg;

            int length = reqmsg.getBytes().length + 8;
            System.out.println("�����ؿͻ��ˡ����ͱ����ܳ��ȣ�" + length);
            String message = appendStrToLength(String.valueOf(length), " ", 8) + reqmsg;
            System.out.println("���ͱ��ģ�" + message);
            Mock9100001 client = new Mock9100001();
            //String serverUrl = "http://localhost:62090/depService";
            String serverUrl = "http://10.143.20.153:62090/depService";
            String rtnDatagram = client.doPost(serverUrl, message, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String appendStrToLength(String srcStr, String appendStr, int length) {
        int appendLength = length - srcStr.getBytes().length;
        StringBuilder strBuilder = new StringBuilder(srcStr);
        for (int i = 1; i <= appendLength; i++) {
            strBuilder.append(appendStr);
        }
        return strBuilder.toString();
    }

    private void init(String serverUrl) {

        this.serverUrl = serverUrl;
        try {
            httpclient = new DefaultHttpClient();
            //����ʱ
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000 * 2);
            //��ȡ��ʱ
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000 * 5);
            httppost = new HttpPost(serverUrl);
            httppost.getURI();
        } catch (Exception e) {
            logger.error("��ʼ��http���ش���!URL: " + serverUrl, e);
            httpclient.getConnectionManager().shutdown();
            throw new RuntimeException(e);
        }
    }

    public String doPost(String serverUrl, String datagram, String charsetName) {
        String responseBody = null;
        String errmsg = "";
        try {
            init(serverUrl);
            StringEntity xmlSE = new StringEntity(datagram, charsetName);
            httppost.setEntity(xmlSE);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
//            responseBody = httpclient.execute(httppost, responseHandler);
            HttpResponse httpResponse = httpclient.execute(httppost);
//            String charset = EntityUtils.getContentCharSet(httpResponse.getEntity());
            // �����ַ������Է�����
            responseBody = EntityUtils.toString(httpResponse.getEntity(), charsetName);
        } catch (UnsupportedEncodingException e) {
            errmsg = "Http�ӿ�ͨѶ���ĸ�ʽת������!";
            logger.error(errmsg, e);
            throw new RuntimeException(errmsg, e);
        } catch (IOException e) {
            errmsg = "��Http�ӿ�ͨѶ���Ӵ���!";
            logger.error(errmsg, e);
            throw new RuntimeException(errmsg, e);
        } catch (Exception e) {
            errmsg = "Http�ӿڵ�ͨѶ����!";
            logger.error(errmsg, e);
            throw new RuntimeException(errmsg, e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        if (StringUtils.isEmpty(responseBody)) {
            throw new RuntimeException("ͨѶ���ܳ��ִ��󣬷��ر���Ϊ�գ�");
        } else {
            logger.info("HttpClient���ձ������ݣ�\n" + responseBody);
        }
        return responseBody;
    }


    private void doGet() {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);//���ӳ�ʱʱ������
        HttpGet httpget = new HttpGet("http://localhost:62003/nettyHttp");
        try {
            HttpResponse response = null;
            response = httpClient.execute(httpget);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                StringBuffer sb=new StringBuffer();
                InputStream instream = entity.getContent();
                int l;
                byte[] tmp = new byte[2048];
                while ((l = instream.read(tmp)) != -1) {
                    sb.append(new String(tmp, 0, l));
                }
                System.out.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
    }
}

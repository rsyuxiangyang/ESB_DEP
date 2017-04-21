import helper.StringPad;
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

import java.io.*;
import java.net.Socket;

/**
 * ���ݿͻ�֤����Ϣ��ѯ-�˻��б�9009060
 * (��ӦSBS-8855����)
 * ����֤������ѯ�ͻ����������˻������Ϣ������û���˻����Ͳ鲻���˻���Ϣ
 */
public class HttpSbs9009060 {
    private Log logger = LogFactory.getLog(this.getClass());

    private String serverUrl;
    private HttpClient httpclient = null;
    private HttpPost httppost = null;
    public static void main(String[] args) {
        try {
            String xmlmsg = "<?xml version=\"1.0\" encoding=\"GB2312\"?>" +
                    "<ROOT>" +
                        "<INFO>" +
                            "<TXN_CODE>9009060</TXN_CODE>" +         // ���״���
                            "<REQ_SN>WY2015112408161441</REQ_SN>" + // ������ˮ��
                        "</INFO>" +
                        "<BODY>" +
                            "<CUSKID>1</CUSKID>" +  // �ͻ����
                            "<PASTYP>1</PASTYP>" +  // ֤�����
                            "<PASSNO>3702001806517     </PASSNO>" +  // ֤����
                            "<ACTTYP>2</ACTTYP>" +  // �ʻ����ͣ�0-ȫ��,1-���ڴ��,2-����,3-���ڴ�
                            "<BEGNUM>000001</BEGNUM>" +  // ��ʼ����
                        "</BODY>" +
                    "</ROOT>";

            String header = "1.0"          // ͨѶЭ��汾�ţ�Ĭ��Ϊ1.0
                + "0"                      // ������ѹ��ģʽ��0:��ѹ���� 1��zipѹ����Ĭ�ϣ�0
                + "EBKAPP001 "            // ��Χϵͳ�û�ID
                + "9009060   "            // ���׺ţ�Ӧ�뱨�����еĽ��׺�һ��
                + "20151124"              // ��������
                + "081111"                // ����ʱ��
                + "0000"                   // ϵͳ����״̬��Ӧ��
                + "12345678901234567890";// ϵͳ����״̬��Ӧ��Ϣ

            String mac = xmlmsg            // Message Data����
                    + "20151124"         // ��������
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

    public static class SbsSktXml9009050 {
        public static void main(String[] args) {
            try {
                String xmlmsg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                        "<ROOT>" +
                        "<INFO>" +
                        "<TXN_CODE>9009001</TXN_CODE>" +
                        "<REQ_SN>QDJS000092321</REQ_SN>" +
                        "</INFO>" +
                        "<BODY>" +
                        "<OUT_ACTNO>SN9010101010</OUT_ACTNO>" +
                        "<IN_ACTNO>20140711</IN_ACTNO>" +
                        "<TXN_AMT>105</TXN_AMT>" +
                        "<REMARK>����</REMARK>" +
                        "<RESERVE>��ת</RESERVE>" +
                        "</BODY>" +
                        "</ROOT>";
                String mac = helper.MD5Helper.getMD5String(xmlmsg + "20140711QDJSAPP001QDJS20140106001");
                String reqmsg = "1.00QDJSAPP0019009001   201407111450050000" +
                        StringPad.rightPad4ChineseToByteLength("ϵͳ��Ӧ״̬��Ϣ", 20, " ")
                        + mac + xmlmsg;
                int length = reqmsg.getBytes().length + 8;
                System.out.println("�����ؿͻ��ˡ����ͱ����ܳ��ȣ�" + length);
                String message = appendStrToLength(String.valueOf(length), " ", 8) + reqmsg;
                System.out.println("���ͱ��ģ�" + message);
    //            Socket socket = new Socket("10.143.20.15", 62006);
                Socket socket = new Socket("127.0.0.1", 62006);
                socket.setSoTimeout(10000);
                OutputStream os = socket.getOutputStream();
                os.write(message.getBytes());
                os.flush();

                InputStream is = socket.getInputStream();
                byte[] bytes = readBytesFromInputStream(is);
                System.out.println("���ر��ģ�" + new String(bytes));
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static byte[] readBytesFromInputStream(InputStream is) throws IOException {
            if (is != null) {
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] byteBuffer = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                while ((len = bis.read(byteBuffer)) != -1) {
                    baos.write(byteBuffer, 0, len);
                }
                baos.flush();
                bis.close();
                is.close();
                return baos.toByteArray();
            } else
                return null;
        }

        public static String appendStrToLength(String srcStr, String appendStr, int length) {
            int appendLength = length - srcStr.getBytes().length;
            StringBuilder strBuilder = new StringBuilder(srcStr);
            for (int i = 1; i <= appendLength; i++) {
                strBuilder.append(appendStr);
            }
            return strBuilder.toString();
        }
    }
}

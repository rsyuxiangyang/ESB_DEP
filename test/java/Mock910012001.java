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

import java.io.*;

/**
 * Created by hanjianlong on 2015-7-3.
 */
public class Mock910012001 {
    private Log logger = LogFactory.getLog(this.getClass());

    private String serverUrl;
    private org.apache.http.client.HttpClient httpclient = null;
    private HttpPost httppost = null;
    public static void main(String[] args) {
        try {
            String xmlmsg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<root>" +
                    "<info>" +
                    "<txncode>910012001</txncode>" +
                    "<userid>HFNBAPP001</userid>" +
                    "<reqsn>HFNB115011500000207</reqsn>" +
                    "<version>1.0</version>" +
                    "<txndate>20150609</txndate>" +
                    "<txntime>142626</txntime>" +
                    "<bankbranchid>142626</bankbranchid>" +
                    "<bankoperid>142626</bankoperid>" +
                    "</info>" +
                    "<body>" +
                    "<originid>12345678901234</originid>" +
                    "</body>" +
                    "</root>";
            // crypt-MD5:e9baa4c4d7787f4c752a0b05122d15e
            String mac = helper.MD5Helper.getMD5String("HFNBAPP001HFNB20150623001" + xmlmsg);
            String reqmsg = "Encrypt-MD5:" + mac + "\n" + xmlmsg;
            Mock910012001 client = new Mock910012001();
            String serverUrl = "http://localhost:62080/depService";
            String rtnDatagram = client.doPost(serverUrl, reqmsg, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

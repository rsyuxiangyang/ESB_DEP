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

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhanrui on 2016/3/16.
 * 翼支付 签约 
 */
public class Mock9101401 {
    private Log logger = LogFactory.getLog(this.getClass());

    private String serverUrl;
    private org.apache.http.client.HttpClient httpclient = null;
    private HttpPost httppost = null;

    public static void main(String[] args) {
        try {
/*
            String xmlmsg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<root>" +
                    "<info>" +
                    "<txncode>9101401</txncode>" +
                    "<userid>HCCBAPP001</userid>" +
                    "<reqsn>HCCB115011500000305</reqsn>" +
                    "<version>1.0</version>" +
                    "<txndate>20160317</txndate>" +
                    "<txntime>111213</txntime>" +
                    "</info>" +
                    "<body>" +
                    "<bankCode>866500</bankCode>" +
                    "<cardType>1</cardType>" +
                    "<accountCode>6227002394040750328</accountCode>" +
                    "<bankCardName>zhang</bankCardName>" +
                    "<certNo>370225196905290030</certNo>" +
                    "<certType>00</certType>" +
                    "<areaCode>370000</areaCode>" +
                    "<perEntFlag>1</perEntFlag>" +

                    "<netWorkNature>小额贷款</netWorkNature>" +
                    "<userFullName>小贷</userFullName>" +
                    "<ebkType>100</ebkType>" +
                    "<payeeName>小贷</payeeName>" +
                    "<netWorkAreaCode>110000</netWorkAreaCode>" +
                    "<arpType>01</arpType>" +

                    "</body>" +
                    "</root>";
*/

            String xmlmsg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<root>" +
                    "<info>" +
                    "<txncode>9101401</txncode>" +
                    "<userid>HCCBAPP001</userid>" +
                    "<reqsn>HCCB20160412000012</reqsn>" +
                    "<version>1.0</version>" +
                    "<txndate>20160412</txndate>" +
                    "<txntime>181801</txntime>" +
                    "</info>" +
                    "<body>" +
//                    "<bankCode>866500</bankCode>" +
                    "<bankCode>866300</bankCode>" +
                    "<cardType>1</cardType>" +
                    "<accountCode>6217007200036040676</accountCode>" +
//                    "<bankCardName>王浩磊</bankCardName>" +
                    "<bankCardName>张慧文</bankCardName>" +
//                    "<certNo>37068219840305313X</certNo>" +
                    "<certNo>360728198310220010</certNo>" +
                    "<certType>00</certType>" +
                    "<areaCode>370000</areaCode>" +
                    "<perEntFlag>1</perEntFlag>" +
                    "<netWorkNature>小额贷款</netWorkNature>" +
                    "<userFullName>海尔小贷</userFullName>" +
                    "<ebkType>100</ebkType>" +
                    "<payeeName>海尔小贷</payeeName>" +
                    "<netWorkAreaCode>110000</netWorkAreaCode>" +
                    "<arpType>01</arpType>" +
                    "</body>" +
                    "</root>";



            // crypt-MD5:e9baa4c4d7787f4c752a0b05122d15e
            String mac = helper.MD5Helper.getMD5String("HCCBAPP001HCCB20150630001" + xmlmsg);
            String reqmsg = "Encrypt-MD5:" + mac + "\n" + xmlmsg;
            Mock9101401 client = new Mock9101401();
            String serverUrl = "http://localhost:62080/depService";
//            String rtnDatagram = client.doPost(serverUrl, reqmsg, "GBK");
            int count = 0;
            while (true) {
                String rtnDatagram = client.doPost(serverUrl, reqmsg, "GBK");
                System.out.println("第" + (++count) + "次");
                if (count == 1) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(String serverUrl) {

        this.serverUrl = serverUrl;
        try {
            httpclient = new DefaultHttpClient();
            //请求超时
//            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000 * 2);
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000 * 2);
            //读取超时
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000 * 5);
            httppost = new HttpPost(serverUrl);
            httppost.getURI();
        } catch (Exception e) {
            logger.error("初始化http网关错误!URL: " + serverUrl, e);
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
            // 设置字符集，以防乱码
            responseBody = EntityUtils.toString(httpResponse.getEntity(), charsetName);
        } catch (UnsupportedEncodingException e) {
            errmsg = "Http接口通讯报文格式转换错误!";
            logger.error(errmsg, e);
            throw new RuntimeException(errmsg, e);
        } catch (IOException e) {
            errmsg = "与Http接口通讯连接错误!";
            logger.error(errmsg, e);
            throw new RuntimeException(errmsg, e);
        } catch (Exception e) {
            errmsg = "Http接口的通讯错误!";
            logger.error(errmsg, e);
            throw new RuntimeException(errmsg, e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        if (StringUtils.isEmpty(responseBody)) {
            throw new RuntimeException("通讯可能出现错误，返回报文为空！");
        } else {
            logger.info("HttpClient接收报文内容：\n" + responseBody);
        }
        return responseBody;
    }


    private void doGet() {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);//连接超时时间设置
        HttpGet httpget = new HttpGet("http://localhost:62003/nettyHttp");
        try {
            HttpResponse response = null;
            response = httpClient.execute(httpget);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                StringBuffer sb = new StringBuffer();
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
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}

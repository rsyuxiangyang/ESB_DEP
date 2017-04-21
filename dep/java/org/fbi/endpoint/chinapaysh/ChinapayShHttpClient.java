package org.fbi.endpoint.chinapaysh;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * HTTP�ͻ���
 */
public class ChinapayShHttpClient {

    private Log logger = LogFactory.getLog(this.getClass());

    private String serverUrl;
    private org.apache.http.client.HttpClient httpclient = null;
    private HttpPost httppost = null;
    private int connTimeOut = 40 * 1000;
    private int soTimeOut = 120 * 1000;

    public ChinapayShHttpClient(String serverUrl) {
        this.serverUrl = serverUrl;
        try {
            httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connTimeOut);
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeOut);
            httppost = new HttpPost(serverUrl);
            httppost.getURI();
        } catch (Exception e) {
            logger.error("��ʼ��http���ش���!URL: " + serverUrl, e);
            httpclient.getConnectionManager().shutdown();
            throw new RuntimeException(e);
        }
    }

    public String doPost(String charsetName, List<NameValuePair> nvps) {
        String responseBody = null;
        String errmsg = "";
        try {
            httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charsetName);
            httppost.setHeader("Connection", "close");
            httppost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse httpResponse = httpclient.execute(httppost);
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
            logger.info("HttpClient���ձ������ݣ�" + responseBody);
        }
        return responseBody;
    }

}

package org.fbi.endpoint.bestpay.com.bestpay.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fbi.endpoint.bestpay.com.bestpay.constant.BestpayConstants;
import org.fbi.endpoint.bestpay.com.bestpay.response.CertResponse;
import org.fbi.endpoint.bestpay.com.bestpay.rsa.RsaCipher;
import org.fbi.endpoint.bestpay.com.bestpay.util.ObjectJsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 调用翼支付前置接口
 */
public class CallFasClient {
    private Log logger = LogFactory.getLog(this.getClass());

    public String doPost(String message, String urlStr){
        /*String message = request.getParameter("message");//请求参数
        String urlStr = request.getParameter("url");//请求url*/
        URLConnection conn = null;
        String responseMessage=null;
        try {
//            String tempUrl=new String(urlStr.getBytes("utf-8"),"utf-8");
            logger.info("翼支付链接：" + urlStr);
            URL url = new URL(urlStr);
            conn = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;
            httpUrlConnection.setRequestMethod("POST");
            byte[] data = message.getBytes("utf-8");
            String strData=new String(data,"utf-8");
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setInstanceFollowRedirects(true);
            httpUrlConnection.setInstanceFollowRedirects(false);
            httpUrlConnection.addRequestProperty("Content-Type", "application/json;charset=utf-8");
//            httpUrlConnection.addRequestProperty("Content-Type", "text/xml;charset=utf-8");
            httpUrlConnection.addRequestProperty("Content-Length", String.valueOf(data.length));
            OutputStream out = httpUrlConnection.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream(), "utf-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuilder stringBuffer = new StringBuilder(255);
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            responseMessage = stringBuffer.toString();
//            CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseMessage, CertResponse.class);
//            String plainText = ObjectJsonUtil.objToJson(certResponse.getData());
//            boolean check = RsaCipher.verify(plainText, BestpayConstants.BESTPAY_CERT, certResponse.getSign());
//            if (!check) {
//                responseMessage = "报文被窜改";
//            }
            String  strTeset="dsd";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((conn != null) && conn instanceof HttpURLConnection)
                ((HttpURLConnection) conn).disconnect();
        }
        return responseMessage;
    }
}
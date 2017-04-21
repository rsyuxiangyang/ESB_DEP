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
 * 根据客户证件信息查询-账户列表9009060
 * (对应SBS-8855交易)
 * 根据证件，查询客户所开立的账户相关信息（有余额）没有账户，就查不到账户信息
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
                            "<TXN_CODE>9009060</TXN_CODE>" +         // 交易代码
                            "<REQ_SN>WY2015112408161441</REQ_SN>" + // 交易流水号
                        "</INFO>" +
                        "<BODY>" +
                            "<CUSKID>1</CUSKID>" +  // 客户类别
                            "<PASTYP>1</PASTYP>" +  // 证件类别
                            "<PASSNO>3702001806517     </PASSNO>" +  // 证件号
                            "<ACTTYP>2</ACTTYP>" +  // 帐户类型（0-全部,1-定期存款,2-贷款,3-活期存款）
                            "<BEGNUM>000001</BEGNUM>" +  // 起始笔数
                        "</BODY>" +
                    "</ROOT>";

            String header = "1.0"          // 通讯协议版本号，默认为1.0
                + "0"                      // 报文体压缩模式。0:不压缩， 1：zip压缩，默认：0
                + "EBKAPP001 "            // 外围系统用户ID
                + "9009060   "            // 交易号，应与报文体中的交易号一致
                + "20151124"              // 交易日期
                + "081111"                // 交易时间
                + "0000"                   // 系统处理状态响应码
                + "12345678901234567890";// 系统处理状态响应信息

            String mac = xmlmsg            // Message Data部分
                    + "20151124"         // 交易日期
                    + "EBKAPP001 "        // 外围系统用户ID
                    + "EBK20151009001"; // USER_KEY由财务公司针对每个用户单独下发

            String reqmsg = header +
                    MD5Helper.getMD5String(mac) +
                    xmlmsg;

            int length = reqmsg.getBytes().length + 8;
            System.out.println("【本地客户端】发送报文总长度：" + length);
            String message = appendStrToLength(String.valueOf(length), " ", 8) + reqmsg;
            System.out.println("发送报文：" + message);
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
            //请求超时
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000 * 2);
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
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);//连接超时时间设置
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
                        "<REMARK>建行</REMARK>" +
                        "<RESERVE>内转</RESERVE>" +
                        "</BODY>" +
                        "</ROOT>";
                String mac = helper.MD5Helper.getMD5String(xmlmsg + "20140711QDJSAPP001QDJS20140106001");
                String reqmsg = "1.00QDJSAPP0019009001   201407111450050000" +
                        StringPad.rightPad4ChineseToByteLength("系统响应状态信息", 20, " ")
                        + mac + xmlmsg;
                int length = reqmsg.getBytes().length + 8;
                System.out.println("【本地客户端】发送报文总长度：" + length);
                String message = appendStrToLength(String.valueOf(length), " ", 8) + reqmsg;
                System.out.println("发送报文：" + message);
    //            Socket socket = new Socket("10.143.20.15", 62006);
                Socket socket = new Socket("127.0.0.1", 62006);
                socket.setSoTimeout(10000);
                OutputStream os = socket.getOutputStream();
                os.write(message.getBytes());
                os.flush();

                InputStream is = socket.getInputStream();
                byte[] bytes = readBytesFromInputStream(is);
                System.out.println("返回报文：" + new String(bytes));
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

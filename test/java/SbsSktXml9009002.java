import org.fbi.dep.helper.MD5Helper;
import org.fbi.dep.util.StringPad;

import java.io.*;
import java.net.Socket;

public class SbsSktXml9009002 {
    public static void main(String[] args) {
        try {
            String xmlmsg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<ROOT>" +
                    "<INFO>" +
                    "<TXN_CODE>9009002</TXN_CODE>" +
                    "<REQ_SN>CSF115011500000207</REQ_SN>" +
                    "</INFO>" +
                    "<BODY>" +
                    "<ORGIDT>010</ORGIDT>" +
                    "<ORDDAT>20150124</ORDDAT>" +
                    "<FBTACT>0374530</FBTACT>" +
                    "<ORDTYP>CTY</ORDTYP>" +
                    "<TXNCUR>001</TXNCUR>" +
                    "<TXNAMT>1.05</TXNAMT>" +
                    "<RMTTYP>T</RMTTYP>" +
                    "<CUSTYP>01</CUSTYP>" +
                    "<CUSACT>801000001412012001</CUSACT>" +
                    "<FEETYP>1</FEETYP>" +
                    "<FEEACT></FEEACT>" +
                    "<BENACT>801000000902012001</BENACT>" +
                    "<BENNAM></BENNAM>" +
                    "<BENBNK>�������Ų����������ι�˾</BENBNK>" +
                    "<AGENBK></AGENBK>" +
                    "<PBKACT></PBKACT>" +
                    "<RETNAM></RETNAM>" +
                    "<RETAUX></RETAUX>" +
                    "<CHQTYP></CHQTYP>" +
                    "<CHQNUM>8700</CHQNUM>" +
                    "<CHQPWD></CHQPWD>" +
                    "<FUNCDE></FUNCDE>" +
                    "<ADVNUM></ADVNUM>" +
                    "<REQNUM>C115011500000207</REQNUM>" +
                    "</BODY>" +
                    "</ROOT>";
            String mac = MD5Helper.getMD5String(xmlmsg + "20140711CFSAPP001CFS20141219001");
            String reqmsg = "1.00CFSAPP001 9009002   201407111450050000" +
                    StringPad.rightPad4ChineseToByteLength("ϵͳ��Ӧ״̬��Ϣ", 20, " ")
                    + mac + xmlmsg;
            int length = reqmsg.getBytes().length + 8;
            System.out.println("�����ؿͻ��ˡ����ͱ����ܳ��ȣ�" + length);
            String message = appendStrToLength(String.valueOf(length), " ", 8) + reqmsg;
            System.out.println("���ͱ��ģ�" + message);
//            Socket socket = new Socket("10.143.20.15", 62006);
            Socket socket = new Socket("127.0.0.1", 62006);
            socket.setSoTimeout(100000);
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

package org.fbi.endpoint.ccbvips;

import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-1-11
 * Time: ����10:47
 * To change this template use File | Settings | File Templates.
 */
public class VipsSocketClient {

    private static Logger logger = LoggerFactory.getLogger(VipsSocketClient.class);

    public static final String HOSTIP = PropertyManager.getProperty("CCBVIPS_HOSTIP");
    public static final int HOSTPORT = PropertyManager.getIntProperty("CCBVIPS_HOSTPORT");
    public static final int TIMEOUT = PropertyManager.getIntProperty("CCBVIPS_TIMEOUT");

    public String processTxn(String datagram) throws Exception {
        long startTime = System.currentTimeMillis();
        Socket socket = new Socket(HOSTIP, HOSTPORT);
        socket.setKeepAlive(true);
        socket.setSoTimeout(TIMEOUT);
        OutputStream os = socket.getOutputStream();
        os.write(datagram.getBytes("GB2312"));
        os.flush();
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] byteBuffer = new byte[49];
        bis.read(byteBuffer);
        String xmlLengthStr = new String(byteBuffer, 40, 9);
        logger.info("�������ĳ���: " + xmlLengthStr);
        int xmlLength = 62 + Integer.parseInt(xmlLengthStr.substring(0, xmlLengthStr.indexOf('\0')));
        byte[] xmlBytes = new byte[xmlLength];
        bis.read(xmlBytes);
        String resDatagram = new String(byteBuffer) + new String(xmlBytes);
        is.close();
        bis.close();
        socket.close();
        long endTime = System.currentTimeMillis();
        logger.info("��ʱ��" + (endTime - startTime));
        return resDatagram;
    }
}

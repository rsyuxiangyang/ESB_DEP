package org.fbi.endpoint.sbs;

import com.ibm.ctg.client.ECIRequest;
import com.ibm.ctg.client.JavaGateway;
import org.apache.commons.lang.StringUtils;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.sbs.core.SBSRequest;
import org.fbi.endpoint.sbs.core.SBSResponse4MultiRecord;
import org.fbi.endpoint.sbs.core.SBSResponse4SingleRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 2009-5-10
 * Time: 20:58:47
 * To change this template use File | Settings | File Templates.
 */
public class CtgManager {

    private static Logger logger = LoggerFactory.getLogger(CtgManager.class);
    private JavaGateway javaGatewayObject;

    private boolean bDataConv = true;
    private String strDataConv = "ASCII";

    private String strProgram = "SCLMPC";
    private String strChosenServer = "haier";

    private String strUrl = PropertyManager.getProperty("SBS_HOSTIP");
    private int iPort = Integer.parseInt(PropertyManager.getProperty("SBS_HOSTPORT"));

    private int iCommareaSize = 32000;

    private static int TXNCODE_OFFSET = 21;
    private static int TXNCODE_LENGTH = 4;

    private static String CICS_USERID = "CICSUSER";
    private static String CICS_PWD = "";


    /**
         *   �������װ�ͨ�ô���
         *   20120209  zxb
         * @param tiaBytes  �����ģ�GBK��
         * @return ��Ӧ����
         */
        public byte[] processTxn(byte[] tiaBytes)  {
            ECIRequest eciRequestObject = null;
            try {
                byte[] abytCommarea = new byte[iCommareaSize];
    
                javaGatewayObject = new JavaGateway(strUrl, iPort);
    
                eciRequestObject = ECIRequest.listSystems(20);
                flowRequest(eciRequestObject);
    
                if (eciRequestObject.SystemList.isEmpty()) {
                    System.out.println("No CICS servers have been defined.");
                    if (javaGatewayObject.isOpen()) {
                        javaGatewayObject.close();
                    }
                }
                //���
                byte[] headBytes = tiaBytes;
                System.arraycopy(headBytes, 0, abytCommarea, 0, headBytes.length); //�����ͷ
    
                //���Ͱ�
                eciRequestObject = new ECIRequest(ECIRequest.ECI_SYNC, //ECI call type
                        strChosenServer, //CICS server
                        "1", //CICS userid
                        "1", //CICS password
                        strProgram, //CICS program to be run
                        null, //CICS transid to be run
                        abytCommarea, //Byte array containing the
                        // COMMAREA
                        iCommareaSize, //COMMAREA length
                        ECIRequest.ECI_NO_EXTEND, //ECI extend mode
                        0);                       //ECI LUW token
    
    
                //��ȡ���ر���
                flowRequest(eciRequestObject);
    
                logger.info("��CtgManager�����ذ�����:" + format16(abytCommarea).substring(0, 200));
                return abytCommarea;
            } catch (Exception e) {
                logger.error("��SBSͨѶ�������⣺", e);
                throw new RuntimeException(e.getMessage());
            } finally {
                if (javaGatewayObject != null) {
                    if (javaGatewayObject.isOpen()) {
                        try {
                            javaGatewayObject.close();
                        } catch (IOException e) {
                            logger.error("��SBSͨѶ�������⣺", e);
                        }
                    }
                }
            }
        }
    
    /**
     *   �������װ�ͨ�ô���
     *   20120209  zxb
     * @param tia  �����ģ�GBK��
     * @return ��Ӧ����
     */
    public String processTxn(String tia)  {
        return new String(processTxn(tia.getBytes()));
    }

    /*
     �����Ŵ��ۿ�ɹ������ʴ���: a541
     �����ۿ�ɹ������ʴ���: a542
     �����Ŵ�ϵͳ�ſ��aa54
      */
    public byte[] processAccount(List list, String txncode) throws IOException {

        ECIRequest eciRequestObject = null;
        String buff = "";

        try {
            byte[] abytCommarea = new byte[iCommareaSize];

            javaGatewayObject = new JavaGateway(strUrl, iPort);

            eciRequestObject = ECIRequest.listSystems(20);
            flowRequest(eciRequestObject);
            int no = 1001;

            if (eciRequestObject.SystemList.isEmpty() == true) {
                System.out.println("No CICS servers have been defined.");
                if (javaGatewayObject.isOpen() == true) {
                    javaGatewayObject.close();
                }
            }
            //���
            buff = "TPEI" + txncode + "  010       MT01MT01"; //��ͷ���ݣ�xxxx���ף�010���㣬MPC1�նˣ�MPC1��Ա����ͷ����51���ַ�
            System.arraycopy(getBytes(buff), 0, abytCommarea, 0, buff.length()); //�����ͷ

            no = no + 1;

            //�������
            setBufferValues(list, abytCommarea);

//            System.out.println("���Ͱ�����:\n" + new String(abytCommarea));
            logger.info("���Ͱ�����:\n" + new String(abytCommarea));

            //���Ͱ�
            eciRequestObject = new ECIRequest(ECIRequest.ECI_SYNC, //ECI call type
                    strChosenServer, //CICS server
                    "1", //CICS userid
                    "1", //CICS password
                    strProgram, //CICS program to be run
                    null, //CICS transid to be run
                    abytCommarea, //Byte array containing the
                    // COMMAREA
                    iCommareaSize, //COMMAREA length
                    ECIRequest.ECI_NO_EXTEND, //ECI extend mode
                    0);                       //ECI LUW token


            //��ȡ���ر���

            String rtnStr = new String(abytCommarea);

            if (flowRequest(eciRequestObject) == true) {
                //��sof
//                System.out.println("����ֵ11Ϊ\n" + rtnStr);
                logger.info("����ֵ11Ϊ\n" + rtnStr);
            }
//            System.out.println("����ֵ22Ϊ\n" + rtnStr);
            logger.info("����ֵ22Ϊ\n" + rtnStr);

            if (javaGatewayObject.isOpen() == true) {
                javaGatewayObject.close();
            }

            return abytCommarea;

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("SBSϵͳ������ͨ�����ӳ�ʱ��");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * �����ѯ���׶�����ص����
     * ע�⣺������е���ʼ������λӦ�������һ����
     */
    public void processMultiResponsePkg(byte[] tiaBytes, SBSRequest request, SBSResponse4MultiRecord response) {
        ECIRequest eciRequestObject = null;
        javaGatewayObject = null;

        try {
            javaGatewayObject = new JavaGateway(strUrl, iPort);

            eciRequestObject = ECIRequest.listSystems(20);
            flowRequest(eciRequestObject);

            if (eciRequestObject.SystemList.isEmpty()) {
                System.out.println("No CICS servers have been defined.");
                if (javaGatewayObject.isOpen()) {
                    javaGatewayObject.close();
                }
                throw new Exception("δ���� CICS ����������ȷ�ϣ�");
            }

            boolean isEnd = false;
            //�ѽ��յ���ϸ��¼����
            int receivedCount = 0;
            //�ѽ��յı��ĸ���
            int receivedPkgCount = 0;
            //ÿ��������ʼ����
            int beginNumber = 1;
            //ͨѶ��ʱ
            long txTotalTime = 0;

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
            String sendTime;

            while (!isEnd) {
                String requestBuffer = "";

                byte[] abytCommarea = new byte[iCommareaSize];

                //��ͷ���ݣ�xxxx���ף�010���㣬MPC1�նˣ�MPC1��Ա����ͷ����51���ַ�
                requestBuffer = "TPEI" + request.getTxncode() + "  010       MT01MT01";
                //�����ͷ
                System.arraycopy(getBytes(requestBuffer), 0, abytCommarea, 0, requestBuffer.length());

                List paramList = new ArrayList();
                for (String s : request.getParamList()) {
                    paramList.add(s);
                }

                //����������е���ʼ����
                paramList.add(StringUtils.leftPad(String.valueOf(beginNumber), 6, '0'));

                //�������
                setBufferValues(paramList, abytCommarea);

                sendTime = sdf.format(new Date());
                logger.info("���ͱ���: " + sendTime + format16(truncBuffer(abytCommarea)));

                long starttime = System.currentTimeMillis();
                //���Ͱ�
                eciRequestObject = new ECIRequest(ECIRequest.ECI_SYNC, //ECI call type
                        strChosenServer, //CICS server
                        CICS_USERID, //CICS userid
                        CICS_PWD, //CICS password
                        strProgram, //CICS program to be run
                        null, //CICS transid to be run
                        abytCommarea, //Byte array containing the
                        // COMMAREA
                        iCommareaSize, //COMMAREA length
                        ECIRequest.ECI_NO_EXTEND, //ECI extend mode
                        0);                       //ECI LUW token

                //��ȡ���ر���
                if (flowRequest(eciRequestObject)) {
                    logger.info("���ձ���(����ʱ��:" + sendTime + "): " + format16(truncBuffer(abytCommarea)));
                }

                long endtime = System.currentTimeMillis();

                receivedPkgCount++;

                response.init(abytCommarea);
                String formcode = response.getFormcode();

                logger.info("===���ձ�����ţ�" + StringUtils.leftPad(String.valueOf(receivedPkgCount), 6, '0') + "   ����FORMCODE:" + formcode + "   ����ͨѶ��ʱ:" + (endtime - starttime) + "ms.");

                txTotalTime = txTotalTime + (endtime - starttime);

                if (formcode.substring(0, 1).equals("T")) {
                    int totcnt = Integer.parseInt(response.getSofDataHeader().getTotcnt());
                    int curcnt = Integer.parseInt(response.getSofDataHeader().getCurcnt());
                    receivedCount += curcnt;
                    beginNumber = receivedCount + 1;
                    isEnd = receivedCount >= totcnt;
                } else {
                    isEnd = true;
                }
            }
            logger.info("����ͨѶ���ĸ���:" + receivedPkgCount + "   ͨѶ�ܺ�ʱ:" + txTotalTime / 1000 + "��   ƽ��ÿ����ʱ:" + txTotalTime / receivedPkgCount + "ms.");
        } catch (Exception e) {
            logger.error("��SBSͨѶ�������⣺", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (javaGatewayObject != null) {
                if (javaGatewayObject.isOpen()) {
                    try {
                        javaGatewayObject.close();
                    } catch (IOException e) {
                        logger.error("��SBSͨѶ�������⣺", e);
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * ��������ѯ�����
     *
     * @throws Exception
     */
    public void processSingleResponsePkg(SBSRequest request, SBSResponse4SingleRecord response) {
        ECIRequest eciRequestObject = null;
        javaGatewayObject = null;

        try {
            javaGatewayObject = new JavaGateway(strUrl, iPort);

            eciRequestObject = ECIRequest.listSystems(20);
            flowRequest(eciRequestObject);

            if (eciRequestObject.SystemList.isEmpty()) {
                System.out.println("No CICS servers have been defined.");
                if (javaGatewayObject.isOpen()) {
                    javaGatewayObject.close();
                }
                throw new Exception("δ���� CICS ����������ȷ�ϣ�");
            }

            //ͨѶ��ʱ
            long txTotalTime = 0;
            String requestBuffer = "";

            byte[] abytCommarea = new byte[iCommareaSize];

            //��ͷ���ݣ�xxxx���ף�010���㣬MPC1�նˣ�MPC1��Ա����ͷ����51���ַ�
            requestBuffer = "TPEI" + request.getTxncode() + "  010       MT01MT01";
            //�����ͷ
            System.arraycopy(getBytes(requestBuffer), 0, abytCommarea, 0, requestBuffer.length());

            List paramList = new ArrayList();
            for (String s : request.getParamList()) {
                paramList.add(s);
            }

            //�������
            setBufferValues(paramList, abytCommarea);

            String sendTime = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
            logger.info("����" + request.getTxncode() + " ���ͱ���: " + sendTime + format16(truncBuffer(abytCommarea)));

            long starttime = System.currentTimeMillis();
            //���Ͱ�
            eciRequestObject = new ECIRequest(ECIRequest.ECI_SYNC, //ECI call type
                    strChosenServer, //CICS server
                    CICS_USERID, //CICS userid
                    CICS_PWD, //CICS password
                    strProgram, //CICS program to be run
                    null, //CICS transid to be run
                    abytCommarea, //Byte array containing the
                    // COMMAREA
                    iCommareaSize, //COMMAREA length
                    ECIRequest.ECI_NO_EXTEND, //ECI extend mode
                    0);                       //ECI LUW token

            //��ȡ���ر���
            if (flowRequest(eciRequestObject)) {
                logger.info("����" + request.getTxncode() + " ���ձ���(����ʱ��:" + sendTime + "): " + format16(truncBuffer(abytCommarea)));
            }

            long endtime = System.currentTimeMillis();

            response.init(abytCommarea);
            String formcode = response.getFormcode();

            logger.info("===����FORMCODE:" + formcode + "   ����ͨѶ��ʱ:" + (endtime - starttime) + "ms.");

        } catch (Exception e) {
            logger.error("��SBSͨѶ�������⣺", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (javaGatewayObject != null) {
                if (javaGatewayObject.isOpen()) {
                    try {
                        javaGatewayObject.close();
                    } catch (IOException e) {
                        logger.error("��SBSͨѶ�������⣺", e);
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }
    }

    //===================================================================================================
    private byte[] getBytes(String source) throws UnsupportedEncodingException {
        if (bDataConv) {
            return source.getBytes(strDataConv);
        } else {
            return source.getBytes();
        }
    }

    private void setBufferValues(List list, byte[] bb) throws UnsupportedEncodingException {
        int start = 51;
        for (int i = 1; i <= list.size(); i++) {
            String value = list.get(i - 1).toString();
            setVarData(start, value, bb);
            start = start + value.getBytes("GBK").length + 2;
        }
    }

    private void setVarData(int pos, String data, byte[] aa) throws UnsupportedEncodingException {
        short len = (short) data.getBytes("GBK").length;

        byte[] slen = new byte[2];
        slen[0] = (byte) (len >> 8);
        slen[1] = (byte) (len);
        System.arraycopy(slen, 0, aa, pos, 2);
        System.arraycopy(data.getBytes(), 0, aa, pos + 2, len);
    }


    private boolean flowRequest(ECIRequest requestObject) throws Exception {
        int iRc = javaGatewayObject.flow(requestObject);
        String msg = null;
        switch (requestObject.getCicsRc()) {
            case ECIRequest.ECI_NO_ERROR:
                if (iRc == 0) {
                    return true;
                } else {
                    if (javaGatewayObject.isOpen() == true) {
                        javaGatewayObject.close();
                    }
                    throw new Exception("SBS Gateway ���ִ���("
                            + requestObject.getRcString()
                            + "), �����ԭ�����·�����");
                }
            case ECIRequest.ECI_ERR_SECURITY_ERROR:
                msg = "SBS CICS: �û������������";
                break;
            case ECIRequest.ECI_ERR_TRANSACTION_ABEND:
                msg = "SBS CICS : û��Ȩ�����д˱�CICS����";
                break;
            default:
                msg = "SBS CICS : ���ִ��������ԭ��" + requestObject.getCicsRcString();
        }
        logger.info("ECI returned: " + requestObject.getCicsRcString());
        logger.info("Abend code was " + requestObject.Abend_Code + " ");
        if (javaGatewayObject.isOpen() == true) {
            javaGatewayObject.close();
        }
        throw new Exception(msg);
    }

    /**
     * 16���Ƹ�ʽ�����
     *
     * @param buffer
     * @return
     */
    private String format16(byte[] buffer) {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        int n = 0;
        byte[] lineBuffer = new byte[16];
        for (byte b : buffer) {
            if (n % 16 == 0) {
                result.append(String.format("%05x: ", n));
                lineBuffer = new byte[16];
            }
            result.append(String.format("%02x ", b));
            lineBuffer[n % 16] = b;
            n++;
            if (n % 16 == 0) {
                result.append(new String(lineBuffer));
                result.append("\n");
            }

            //TODO
            if (n >= 1024) {
                result.append("���Ĺ����ѽض�...");
                break;
            }

        }
        for (int k = 0; k < (16 - n % 16); k++) {
            result.append("   ");
        }
        result.append(new String(lineBuffer));
        result.append("\n");
        return result.toString();
    }

    /**
     * @param buffer
     * @return
     */
    private byte[] truncBuffer(byte[] buffer) {
        int count = 0;
        for (int i = 0; i < iCommareaSize; i++) {
            if (buffer[iCommareaSize - 1 - i] == 0x00) {
                count++;
            } else {
                break;
            }
        }
        byte[] outBuffer = new byte[iCommareaSize - count];
        System.arraycopy(buffer, 0, outBuffer, 0, outBuffer.length);
        return outBuffer;
    }

}

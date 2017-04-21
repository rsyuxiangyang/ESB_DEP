import org.fbi.endpoint.chinapaysh.ChinapayShTxnHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2014-06-03.
 */
public class ChinapayBatchClientTest {

    static final String BATCH_MER_ID = ChinapayShTxnHandler.BATCH_MER_ID;
    static final String BATCH_FILE_PATH = ChinapayShTxnHandler.BATCH_FILE_PATH;

    public static void main(String[] args) {

//        qry("808080211388500_20140702_151155_Q.txt");
      /*  try {
            uploadBatchFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        downloadNoticeFile();
    }

    // �����ļ�
    public static void uploadBatchFile() throws IOException {

        Date dt = new Date();
        String MerSeqId = new SimpleDateFormat("HHmmss").format(dt); // ���κ�
        String TransDate = new SimpleDateFormat("yyyyMMdd").format(dt); // ��������

        int n = 3; // ������������
        // �ļ��ĵ�һ�У��������̻��ţ����κţ��ܱ������ܽ������á�|���ָ�
        String fileContent = BATCH_MER_ID + "|" + MerSeqId + "|" + n + "|300";
        System.out.println("�ļ�ͷ��" + fileContent);

		/*
         * һ�д���һ�ʽ��׼�¼����ÿһ�н��׼�¼�У���������ĸ���á�|������
		 * �̻����ڣ���ˮ��[������]�������д��ţ����۱�־������/�ۺţ��ֿ���������֤�����ͣ�֤�����룬����;��˽����
		 */
        for (int i = 1; i <= n; i++) {
            String OrdId = TransDate + MerSeqId + "0" + i;  // ������
            fileContent += "\r\n"
                    + TransDate
                    + "|"
                    + OrdId
                    + "|0103|0|6228401111111111111|����|01|310104200005053209|100|�ۿ�|���οۿ����";

        }
        System.out.println("�ļ����ݣ�");
        System.out.println(fileContent);
        // �ļ������淶��MERID_YYYYMMDD_XXXXXX_Q.TXT
        String fileName = BATCH_MER_ID + "_" + TransDate + "_" + MerSeqId + "_Q.txt";
        System.out.println("filePathName=[" + BATCH_FILE_PATH + fileName + "]");
        // д���ļ�
        File file = new File(BATCH_FILE_PATH + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(fileContent.getBytes("UTF-8"));
        fos.flush();
        fos.close();
        try {
            Map<String, String> response = new ChinapayShTxnHandler().uploadBatchFile(fileName, fileContent);
            readMap(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // �����ļ�����
    public static void downloadNoticeFile() {
        // ԭʼ�ļ���
        String orFileName = "808080211388500_20140702_151155_Q.txt";
        // �����ļ���
        String fileName = "808080211388500_20140702_151155_P.txt";

        try {
            Map<String, String> response = new ChinapayShTxnHandler().downloadNoticeFile(orFileName, fileName);
            readMap(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // �����ļ���ѯ�ӿ�
    public static void qry(String fileName) {

        try {
            Map<String, String> response = new ChinapayShTxnHandler().qryBatchFileStatus(fileName);
            readMap(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readMap(Map<String, String> response) {
        for(Map.Entry<String, String> entry : response.entrySet()) {
            System.out.println(entry.getKey() + "  "  + entry.getValue());
        }
     }
}

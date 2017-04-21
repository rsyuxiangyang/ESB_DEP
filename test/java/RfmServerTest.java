import org.apache.commons.lang.StringUtils;
import org.fbi.dep.util.ToolUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lichao.W At 2015/7/24 8:54
 * wanglichao@163.com
 */
public class RfmServerTest {
    public static void main(String args[]) throws IOException {
        int port = 9876;
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            //ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������
            new Thread(new Task(socket)).start();
        }
    }

    /**
     * ��������Socket�����
     */
    static class Task implements Runnable {

        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                handleSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * �õ�һ���ַ����ĳ���,��ʾ�ĳ���,һ�����ֻ��պ��ĳ���Ϊ2,Ӣ���ַ�����Ϊ1
         * @param  s ��Ҫ�õ����ȵ��ַ���
         * @return int �õ����ַ�������
         */
        public int length(String s) {
            if (s == null)
                return 0;
            char[] c = s.toCharArray();
            int len = 0;
            for (int i = 0; i < c.length; i++) {
                len++;
                if (!isLetter(c[i])) {
                    len++;
                }
            }
            return len;
        }

        public boolean isLetter(char c) {
            int k = 0x80;
            return c / k == 0 ? true : false;
        }

        /**
         * ���ͻ���Socket����ͨ��
         * @throws Exception
         */
        private void handleSocket() throws Exception {
            Reader reader = new InputStreamReader(socket.getInputStream());
            char chars[] = new char[64];
            int len;
            StringBuilder sb = new StringBuilder();
            String temp;
            int index;
            if ((len=reader.read(chars)) != -1) {
                temp = new String(chars, 0, len);
                sb.append(temp);
            }
            String strSBstrSB=sb.toString().replace("|", "").substring(6, 10);
            String rtnmsg;
            if("1001".equals(strSBstrSB)){
                rtnmsg = "0005360000|1000000000000009|"+
                        ToolUtil.rightPad("prePerName", 255, " ")+"|" +
                        ToolUtil.rightPad("preProAddr", 128, " ")+"|" +
                        ToolUtil.rightPad("preProName", 128, " ")+"|";
//                rtnmsg = "0000662002|�����������󣬲��ɽ������                                |";
            }else if("1002".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
//                String msg = "����˺Ż�δ���볷�����";
//                rtnmsg = "0000642003|" + SBSRtnCodeConverter.rightPad(msg, 58, ' ') + "|";
            }else if("2001".equals(strSBstrSB)){
                String accountname = "�ൺ�����յ��������ܹ�˾ְ������Э��";
                rtnmsg = "0002270000|0|"+
                        ToolUtil.rightPad("1023", 20, " ")+"|" +
                        ToolUtil.rightPad("801000016502013", 30, " ")+"|" +
                        ToolUtil.rightPad(accountname, 150+accountname.length()-length(accountname), " ")+"|"+
                        "1440560429911369|";
            }else if("2002".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
            }else if("2011".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
            }else if("2101".equals(strSBstrSB)){
                rtnmsg = "0008830000|" +
                        ToolUtil.rightPad("801000006412011001",30," ") +"|"+
                        ToolUtil.rightPad("�ൺ�����յ��������޹�˾", 150, " ")+"|" +
                        ToolUtil.rightPad("20000", 20, " ")+"|" +
                        ToolUtil.rightPad("shoukuanyinhang", 90, " ")+"|" +
                        ToolUtil.rightPad("801000017502011001",30," ") +"|"+
                        ToolUtil.rightPad("��������������Ʒ���޹�˾", 150, " ")+"|" +
                        ToolUtil.rightPad("xiangmumingcheng", 128, " ")+"|" +
                        ToolUtil.rightPad("kaifaqiyemingcheng", 255, " ")+"|" +
                        "1000000000000009|";
                /*rtnmsg = "000873|0000|jianguanzhanghao12345678900014|"+
                        SBSRtnCodeConverter.rightPad("jianguanzhanghao12345678900014����", 150, " ")+"|" +
                        SBSRtnCodeConverter.rightPad("20800001", 20, " ")+"|" +
                        SBSRtnCodeConverter.rightPad("shoukuanyinhang", 90, " ")+"|" +
                        "shoukuanzhanghao12345678900014|" +
                        SBSRtnCodeConverter.rightPad("shoukuanzhanghao12345678900014����", 150, " ")+"|" +
                        SBSRtnCodeConverter.rightPad("��Ŀ����", 128, " ")+"|" +
                        SBSRtnCodeConverter.rightPad("������ҵ����", 255, " ")+"|" +
                        "1000000000000009";*/
            }else if("2102".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
            }else if("2111".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
            }else if("2201".equals(strSBstrSB)){
                rtnmsg = "0005930000|"+
                        ToolUtil.rightPad("801000017702012001",30," ") +"|"+
                        ToolUtil.rightPad("�������Ŵ���������ҵ���޹�˾", 150, " ")+"|" +
                        ToolUtil.rightPad("10000", 20, " ")+"|" +
                        ToolUtil.rightPad("yezhuxingming", 80, " ")+"|" +
                        ToolUtil.rightPad("zhengjianleixing", 30, " ")+"|" +
                        ToolUtil.rightPad("kaifaqiyemingcheng", 255, " ")+"|" +
                        "1000000000000009|";
            }else if("2202".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
            }else if("2211".equals(strSBstrSB)){
                rtnmsg = "0000220000|1000000000000009|";
            }else if("2501".equals(strSBstrSB)){
                rtnmsg = "0000720000|0|pingtaijizhang12|jianguanyinhangjizhangliushui1|1000000000000009|";
            }else{
                rtnmsg = "1234567890111213141516171819202122232425262728293031323334353637383940414243454647484950";
            }
            System.out.println("���ر��ģ�" + rtnmsg);
            Writer writer = new OutputStreamWriter(socket.getOutputStream(),"GB2312");
            writer.write(rtnmsg);
            writer.flush();
            writer.close();
            reader.close();
            socket.close();
        }

    }
}

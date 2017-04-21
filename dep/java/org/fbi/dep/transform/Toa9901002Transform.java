package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9901002;

/**
 * ̩�������ʽ���ϵͳ�������ܷ���
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9901002Transform extends AbstractToaTransform {

    @Override
    public Toa9901002 transform(String tiaStrDatagram, String txCode) {
        Toa9901002 Toa9901002 = convertStrToBean(tiaStrDatagram);
        return Toa9901002;
    }

    private Toa9901002 convertStrToBean(String strPara) {
        Toa9901002 Toa9901002Para =new Toa9901002();
        /*��ȷ���أ�
          01	���	                4   0000��ʾ�ɹ�
          02	Ԥ���ʽ���ƽ̨��ˮ	16
        */
        /*���󷵻أ�
          01    ���ؽ��                4   0000��ʾ�ɹ�
          02    ����ԭ������	        60
        */
        String[] sourceStrArray = strPara.split("\\|");
        /* 1������á�.����Ϊ�ָ��Ļ�������������д����String.split("\\."),����������ȷ�ķָ�����������String.split(".");
           2������á�|����Ϊ�ָ��Ļ�������������д����String.split("\\|"),����������ȷ�ķָ�����������String.split("|");
             ��.���͡�|������ת���ַ�������ü�"\\";
           3�������һ���ַ������ж���ָ����������á�|����Ϊ���ַ������磺��a=1 and b =2 or c=3��,���������ָ�������������String.split("and|or");
        */
        String strRtnCode=sourceStrArray[0];
        Toa9901002Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            Toa9901002Para.header.REQ_SN=sourceStrArray[1];
        }else{
            Toa9901002Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return Toa9901002Para;
    }
}

package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9902102;

/**
 * ̩�������ʽ���ϵͳ���������˷���
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9902102Transform extends AbstractToaTransform {

    @Override
    public Toa9902102 transform(String tiaStrDatagram, String txCode) {
        Toa9902102 toa9902102 = convertStrToBean(tiaStrDatagram);
        return toa9902102;
    }

    private Toa9902102 convertStrToBean(String strPara) {
        Toa9902102 toa9902102Para=new Toa9902102();
        /*��ȷ���أ�
          01    ���	                4   0000��ʾ�ɹ�
          02    Ԥ���ʽ���ƽ̨��ˮ	16
        */
        /*���󷵻أ�
          01    ���ؽ��                4   0000��ʾ�ɹ�
          02    ����ԭ������	        60
        */
        String[] sourceStrArray = strPara.split("\\|");
        String strRtnCode=sourceStrArray[0];
        toa9902102Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            toa9902102Para.header.REQ_SN = sourceStrArray[1];
        }else{
            toa9902102Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return toa9902102Para;
    }
}

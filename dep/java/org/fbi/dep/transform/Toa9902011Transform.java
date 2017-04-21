package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9902011;

/**
 * ̩�������ʽ���ϵͳ�������������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9902011Transform extends AbstractToaTransform {

    @Override
    public Toa9902011 transform(String tiaStrDatagram, String txCode) {
        Toa9902011 toa9902011 = convertStrToBean(tiaStrDatagram);
        return toa9902011;
    }

    private Toa9902011 convertStrToBean(String strPara) {
        Toa9902011 toa9902011Para=new Toa9902011();
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
        toa9902011Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            toa9902011Para.header.REQ_SN = sourceStrArray[1];
        }else{
            toa9902011Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return toa9902011Para;
    }
}

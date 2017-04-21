package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9902201;

/**
 * ̩�������ʽ���ϵͳ��������֤����
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9902201Transform extends AbstractToaTransform {

    @Override
    public Toa9902201 transform(String tiaStrDatagram, String txCode) {
        Toa9902201 toa9902201 = convertStrToBean(tiaStrDatagram);
        return toa9902201;
    }

    private Toa9902201 convertStrToBean(String strPara) {
        Toa9902201 toa9902201Para=new Toa9902201();
        /*��ȷ���أ�
          01	���	                4   0000��ʾ�ɹ�
          02	����˺�                30
          03    ����˻�����            150
          04	�������	            20  �Է�Ϊ��λ
          05	ҵ������	            80
          06	֤������    	        30
          07	֤������                255
          08    Ԥ���ʽ���ƽ̨��ˮ    16
        */
        /*���󷵻أ�
          01    ���ؽ��                4   0000��ʾ�ɹ�
          02    ����ԭ������	        60
        */
        String[] sourceStrArray = strPara.split("\\|");
        String strRtnCode=sourceStrArray[0];
        toa9902201Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            toa9902201Para.body.SPVSN_ACC_ID = sourceStrArray[1];
            toa9902201Para.body.SPVSN_ACC_NAME = sourceStrArray[2];
            toa9902201Para.body.TX_AMT = sourceStrArray[3];
            toa9902201Para.body.OWNER_NAME = sourceStrArray[4];
            toa9902201Para.body.CTFIC_TYPE = sourceStrArray[5];
            toa9902201Para.body.CTFIC_ID = sourceStrArray[6];
            toa9902201Para.header.REQ_SN = sourceStrArray[7];
        }else{
            toa9902201Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return toa9902201Para;
    }
}

package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9902101;

/**
 * ̩�������ʽ���ϵͳ��������֤����
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9902101Transform extends AbstractToaTransform {

    @Override
    public Toa9902101 transform(String tiaStrDatagram, String txCode) {
        Toa9902101 toa9902101 = convertStrToBean(tiaStrDatagram);
        return toa9902101;
    }

    private Toa9902101 convertStrToBean(String strPara) {
        Toa9902101 toa9902101Para=new Toa9902101();
        /*��ȷ���أ�
          01	���	                4   0000��ʾ�ɹ�
          02	����˺�                30
          03    ����˻�����            150
          04	�������	            20  �Է�Ϊ��λ
          05	�տ�����	            90
          06	�տλ�˺�	        30
          07	�տλ����	        150
          08	��Ŀ����	            128
          09	������ҵ����	        255
          10    Ԥ���ʽ���ƽ̨��ˮ    16
        */
        /*���󷵻أ�
          01    ���ؽ��                4   0000��ʾ�ɹ�
          02    ����ԭ������	        60
        */
        String[] sourceStrArray = strPara.split("\\|");
        String strRtnCode=sourceStrArray[0];
        toa9902101Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            toa9902101Para.body.SPVSN_ACC_ID = sourceStrArray[1];
            toa9902101Para.body.SPVSN_ACC_NAME = sourceStrArray[2];
            toa9902101Para.body.TX_AMT = sourceStrArray[3];
            toa9902101Para.body.GERL_BANK = sourceStrArray[4];
            toa9902101Para.body.GERL_ACC_ID = sourceStrArray[5];
            toa9902101Para.body.GERL_ACC_NAME = sourceStrArray[6];
            toa9902101Para.body.PROG_NAME = sourceStrArray[7];
            toa9902101Para.body.COMP_NAME = sourceStrArray[8];
            toa9902101Para.header.REQ_SN = sourceStrArray[9];
        }else{
            toa9902101Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return toa9902101Para;
    }
}

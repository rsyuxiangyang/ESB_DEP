package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9902501;

/**
 * ̩�������ʽ���ϵͳ�����˽����ѯ����
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9902501Transform extends AbstractToaTransform {

    @Override
    public Toa9902501 transform(String tiaStrDatagram, String txCode) {
        Toa9902501 toa9902501 = convertStrToBean(tiaStrDatagram);
        return toa9902501;
    }

    private Toa9902501 convertStrToBean(String strPara) {
        Toa9902501 toa9902501Para=new Toa9902501();
        /*��ȷ���أ�
          01	���	                4   0000��ʾ�ɹ�
          02	���˽��	            1   0_�ɹ� 1_ʧ��
          03	Ԥ���ʽ���ƽ̨������ˮ16  ҵ�������ˮ
          04	������м�����ˮ	    30  ҵ�������ˮ
          05	Ԥ���ʽ���ƽ̨��ˮ	16
        */
        /*���󷵻أ�
          01    ���ؽ��                4   0000��ʾ�ɹ�
          02    ����ԭ������	        60
        */
        String[] sourceStrArray = strPara.split("\\|");
        String strRtnCode=sourceStrArray[0];
        toa9902501Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            toa9902501Para.body.SPVSN_ACT_RSTL = sourceStrArray[1];
            toa9902501Para.body.FDC_ACT_SN = sourceStrArray[2];
            toa9902501Para.body.FDC_BANK_ACT_SN = sourceStrArray[3];
            toa9902501Para.header.REQ_SN = sourceStrArray[4];
        }else{
            toa9902501Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return toa9902501Para;
    }
}

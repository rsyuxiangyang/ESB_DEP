package org.fbi.dep.transform;

import org.fbi.dep.model.txn.Toa9901001;

/**
 * ̩�������ʽ���ϵͳ��������ܷ���
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-6-30
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class Toa9901001Transform extends AbstractToaTransform {

    @Override
    public Toa9901001 transform(String tiaStrDatagram, String txCode) {
        Toa9901001 toa9901001 = convertStrToBean(tiaStrDatagram);
        return toa9901001;
    }

    private Toa9901001 convertStrToBean(String strPara) {
        Toa9901001 toa9901001Para=new Toa9901001();
        /*01	���	                4   0000��ʾ�ɹ�
          02	Ԥ���ʽ���ƽ̨��ˮ	16
          03	Ԥ��������	            255
          04	Ԥ����Ŀ��ַ            128
          05    Ԥ����Ŀ����            128
        */
        String[] sourceStrArray = strPara.split("\\|");
        String strRtnCode=sourceStrArray[0];
        toa9901001Para.header.RETURN_CODE = strRtnCode;
        if("0000".equals(strRtnCode)) {
            toa9901001Para.header.REQ_SN = sourceStrArray[1];
            toa9901001Para.body.PRE_SALE_PER_NAME = sourceStrArray[2];
            toa9901001Para.body.PRE_SALE_PRO_ADDR = sourceStrArray[3];
            toa9901001Para.body.PRE_SALE_PRO_NAME = sourceStrArray[4];
        }else{
            toa9901001Para.header.RETURN_MSG = sourceStrArray[1];
        }
        return toa9901001Para;
    }
}

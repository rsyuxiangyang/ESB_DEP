package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9901001;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ���������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9901001Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9901001 tia9901001 = (Tia9901001) tia;
        return convertBeanToStr(tia9901001);
    }

    private String convertBeanToStr(Tia9901001 tia9901001Para) {
        /*01	���״���	    4	1001
          02	������д���	2
          03	���д���	    6
          04	���������    14
          05    �ʻ����        1  0��Ԥ�ۼ�ܻ�
          06    ���ר���˺�    30
          07    ���ר������    150
          08	��ˮ��    	    30
          09	����	        10	��ϵͳ���ڼ���
          10	�����	        30
          11	��Ա��	        30
          12	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9901001Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.SPVSN_BANK_ID, 2, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.SPVSN_ACC_TYPE,  1, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.SPVSN_ACC_ID,    30, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.SPVSN_ACC_NAME,  150, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.TX_DATE,   10, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9901001Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength = ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

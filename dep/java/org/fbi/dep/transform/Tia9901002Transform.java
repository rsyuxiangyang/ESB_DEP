package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9901002;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ���������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9901002Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9901002 tia9901002 = (Tia9901002) tia;
        return convertBeanToStr(tia9901002);
    }

    private String convertBeanToStr(Tia9901002 tia9901002Para) {
          /*01	���״���	    4	1002
          02	������д���	2
          03	���д���	    6
          04	��ֹ֤�����    14
          05    ���ר���˺�    30
          06    ���ר������    150
          07	��ˮ��    	    30
          08	����	        10	��ϵͳ���ڼ���
          09	�����	        30
          10	��Ա��	        30
          11	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9901002Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.SPVSN_ACC_ID,    30, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.SPVSN_ACC_NAME,  150, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.TX_DATE,   10, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9901002Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

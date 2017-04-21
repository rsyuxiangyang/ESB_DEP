package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902202;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ����������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902202Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902202 tia9902202 = (Tia9902202) tia;
        return convertBeanToStr(tia9902202);
    }

    private String convertBeanToStr(Tia9902202 tia9902202Para) {
        /*01	���״���	    4	2202
          02	������д���	2
          03	���д���	    6
          04	����ҵ����	14
          05	��������	    32	MD5
          06	����˺�	    30
          07	�����ʽ�	    20
          08	���м�����ˮ	30
          09	��������	    10	��ϵͳ���ڼ���
          10	��������	    30
          11	������Ա	    30
          12	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902202Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.header.PASSWORD,32, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.SPVSN_ACC_ID,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.TX_AMT,  20, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.TX_DATE, 10, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902202Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

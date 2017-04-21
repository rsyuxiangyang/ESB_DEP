package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902111;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ����������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902111Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902111 tia9902111 = (Tia9902111) tia;
        return convertBeanToStr(tia9902111);
    }

    private String convertBeanToStr(Tia9902111 tia9902111Para) {
         /*01	���״���	    4	2111
          02	������д���	2
          03	���д���	    6
          04	����ҵ����	14
          05	���г�����ˮ	30
          06	��������	    10	��ϵͳ���ڼ���
          07	��������	    30
          08	������Ա	    30
          09	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902111Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.header.REQ_SN, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.body.TX_DATE,   10, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902111Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

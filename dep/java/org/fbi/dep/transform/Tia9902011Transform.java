package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902011;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ���������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902011Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902011 tia9902011 = (Tia9902011) tia;
        return convertBeanToStr(tia9902011);
    }

    private String convertBeanToStr(Tia9902011 tia9902011Para) {
         /*01	���״���	    4	2011
          02	������д���	2
          03	���д���	    6
          04	����������	14
          05	���г�����ˮ	30
          06	��������	    10	��ϵͳ���ڼ���
          07	��������	    30
          08	������Ա	    30
          09	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902011Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.header.BIZ_ID, 14, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.body.TX_DATE,   10, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902011Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}
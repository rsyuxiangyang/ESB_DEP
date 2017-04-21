package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902201;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ��������֤
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902201Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902201 tia9902201 = (Tia9902201) tia;
        return convertBeanToStr(tia9902201);
    }

    private String convertBeanToStr(Tia9902201 tia9902201Para) {
        /*01	���״���	    4	2201
          02	������д���	2
          03	���д���	    6
          04	����ҵ����	14
          05	֧������	    32	MD5
          06	��֤��ˮ    	30
          07	��֤����	    10	��ϵͳ���ڼ���
          08	��֤����	    30
          09	��֤��Ա	    30
          10	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902201Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.header.PASSWORD,  32, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.body.TX_DATE,   10, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902201Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

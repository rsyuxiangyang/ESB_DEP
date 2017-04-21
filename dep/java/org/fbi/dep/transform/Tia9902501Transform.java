package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902501;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ���������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902501Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902501 tia9902501 = (Tia9902501) tia;
        return convertBeanToStr(tia9902501);
    }

    private String convertBeanToStr(Tia9902501 tia9902501Para) {
        /*01	���״���	    4	2501
          02	������д���	2
          03	���д���	    6
          04	ҵ����	    14 ��������� ����ҵ���� �˻�ҵ����
          05	��ѯ����	    30
          06	��ѯ��Ա	    30
          07	����	        1 1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902501Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902501Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902501Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902501Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9902501Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902501Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902501Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

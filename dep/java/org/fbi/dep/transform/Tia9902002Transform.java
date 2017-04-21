package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902002;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ���������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902002Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902002 tia9902002 = (Tia9902002) tia;
        return convertBeanToStr(tia9902002);
    }

    private String convertBeanToStr(Tia9902002 tia9902002Para) {
        /*01	���״���	    4	2002
          02	������д���	2
          03	���д���	    6
          04	����������	14
          05	������	    20	2001���׽�����֤���ص�ʵ�ɽ�2003�����޷�����ɹ�Ա¼�롣
          06	����˺�	    30	������֤�������
          07	���㷽ʽ	    2	01_ �ֽ� 02_ ת�� 03_ ֧Ʊ
          08	֧Ʊ����	    30
          09	���м�����ˮ	30
          10	��������	    10	��ϵͳ���ڼ���
          11	��������	    30
          12	������Ա	    30
          13	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902002Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.TX_AMT,  20, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.SPVSN_ACC_ID,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.STL_TYPE,  2, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.CHECK_ID,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.TX_DATE, 10, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902002Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

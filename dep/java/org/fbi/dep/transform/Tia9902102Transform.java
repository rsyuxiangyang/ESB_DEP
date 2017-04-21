package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902102;
import org.fbi.dep.util.ToolUtil;

/**
 * ̩�����������ʽ���ϵͳ����������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 2015-07-24
 * Time: 9:11
 * To change this template use File | Settings | File Templates.
 */
public class Tia9902102Transform extends AbstractTiaTransform {

    @Override
    public String transform(TIA tia) {
        Tia9902102 tia9902102 = (Tia9902102) tia;
        return convertBeanToStr(tia9902102);
    }

    private String convertBeanToStr(Tia9902102 tia9902102Para) {
        /*01	���״���	    4	2102
          02	������д���	2
          03	���д���	    6
          04	����ҵ����	14
          05	��������	    32	MD5
          06	����˺�	    30	������֤�������
          07	�տλ�˺�	30	������֤�������
          08	�����ʽ�	    20	������֤�������
          09	���㷽ʽ	    2	01_ �ֽ� 02_ ת�� 03_ ֧Ʊ
          10	֧Ʊ����	    30
          11	���м�����ˮ	30
          12	��������	    10	��ϵͳ���ڼ���
          13	��������	    30
          14	������Ա	    30
          15	����	        1	1_�������*/
        String strRtn=
                ToolUtil.rightPad(tia9902102Para.header.TX_CODE, 4, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.SPVSN_BANK_ID,   2, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.CITY_ID,   6, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.header.BIZ_ID,  14, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.header.PASSWORD,32, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.SPVSN_ACC_ID,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.GERL_ACC_ID,30, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.TX_AMT, 20, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.STL_TYPE,  2, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.CHECK_ID,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.header.REQ_SN,  30, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.TX_DATE,   10, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.BRANCH_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.header.USER_ID, 30, ' ')+"|"+
                ToolUtil.rightPad(tia9902102Para.body.INITIATOR, 1, ' ')+"|";
        Integer intStrRtnLength=ToolUtil.length(strRtn);
        strRtn= StringUtils.leftPad(intStrRtnLength.toString(),6, '0')+strRtn;
        return strRtn;
    }
}

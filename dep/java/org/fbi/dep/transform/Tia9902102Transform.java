package org.fbi.dep.transform;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.txn.Tia9902102;
import org.fbi.dep.util.ToolUtil;

/**
 * 泰安房产中心资金监管系统—划拨记账
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
        /*01	交易代码	    4	2102
          02	监管银行代码	2
          03	城市代码	    6
          04	划拨业务编号	14
          05	划拨密码	    32	MD5
          06	监管账号	    30	划拨验证的输出项
          07	收款单位账号	30	划拨验证的输出项
          08	划拨资金	    20	划拨验证的输出项
          09	结算方式	    2	01_ 现金 02_ 转账 03_ 支票
          10	支票号码	    30
          11	银行记账流水	30
          12	记账日期	    10	送系统日期即可
          13	记账网点	    30
          14	记账人员	    30
          15	发起方	        1	1_监管银行*/
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

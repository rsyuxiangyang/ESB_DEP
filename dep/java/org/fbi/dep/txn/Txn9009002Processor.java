package org.fbi.dep.txn;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.component.jms.JmsBytesClient;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.TiaXml9009002;
import org.fbi.dep.model.txn.ToaXml9009002;
import org.fbi.dep.transform.SbsTxnDataTransform;
import org.fbi.dep.util.JaxbHelper;
import org.fbi.dep.util.PropertyManager;
import org.fbi.dep.util.StringPad;
import org.fbi.endpoint.mbp.domain.ClientRequestHead;
import org.fbi.endpoint.mbp.domain.transactrequest.TransactRequestParam;
import org.fbi.endpoint.mbp.domain.transactrequest.TransactRequestRoot;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.re.M000;
import org.fbi.endpoint.sbs.model.form.re.T531;
import org.fbi.endpoint.sbs.model.form.re.T999;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 交易处理 SBS-MBP
 * 韩建龙
 * 本交易处理两个SBS交易（先冻结再支付）
 * 以下是SBS支付交易逻辑：
 * SBS 返回 T531 代表交易处理完成（银行收到了交易信息，不代表交易处理成功） DEP应返回0000，文档里应标明0000不代表业务成功。
 * SBS 返回 T999 代表重复提交，已发送银行 处理完成。同T531。   DEP应返回0000，文档里应标明0000不代表业务成功。
 * SBS 返回 MB01 代表MBP明确返回超时，需做结果查询交易进行确认。  DEP应返回2000。
 * SBS 返回 MZZZ 代表银行返回业务处理失败（比如余额不足）。  DEP应返回1000。
 * SBS 返回 非T，W开头  代表SBS业务处理失败，但应该根据SBS明确的错误码进行判断。  （具体看XML配置）
 * SBS 返回 其它响应码 未不明。
 */
public class Txn9009002Processor extends AbstractTxnProcessor {

    private static Logger logger = LoggerFactory.getLogger(Txn9009002Processor.class);

    public String process(String userid, String msgData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        // 先发起SBS交易
        // byte[] sbsReqMsg = new TiaXml9009002Transform().run(msgData, userid);
        TiaXml9009002 tia = (TiaXml9009002) (new TiaXml9009002().getTia(msgData));
        String termID = PropertyManager.getProperty("sbs.termid." + userid.trim());
        logger.info("SBS代理支付_USERID：" + userid.trim());
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }

        // 预先冻结
        byte[] sbsReqMsgN02b = SbsTxnDataTransform.convertToTxnN02b(tia, termID);
        // SBS
        byte[] bytesN02b = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", "9009002", userid,
                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsgN02b);
        // 解析SBS报文
        FebResponse responseN02b = new FebResponse();
        responseN02b.init(bytesN02b);
        String formCodeN02b = responseN02b.getFormCodes().get(0);
        logger.info("SBS代理支付_冻结交易返回码：" + formCodeN02b);
        // bean -> txn bean
        ToaXml9009002 toaN02b = new ToaXml9009002();
        // 返回暂时无用,临时存放
        if ("T531".equalsIgnoreCase(formCodeN02b)) {
            toaN02b.INFO.RET_MSG = "冻结交易完成";
            SOFForm form = responseN02b.getSofForms().get(0);
            T531 t531 = (T531) form.getFormBody();
            logger.info(toaN02b.INFO.RET_MSG);
        } else if ("T999".equalsIgnoreCase(formCodeN02b)) {
            toaN02b.INFO.RET_MSG = "冻结交易完成";
            SOFForm form = responseN02b.getSofForms().get(0);
            T999 t999 = (T999) form.getFormBody();
            logger.info(toaN02b.INFO.RET_MSG);
        } else if ("W001".equalsIgnoreCase(formCodeN02b)) {
            toaN02b.INFO.RET_MSG = "冻结交易完成";
            SOFForm form = responseN02b.getSofForms().get(0);
            //W001 w001 = (W001) form.getFormBody();
            logger.info(toaN02b.INFO.RET_MSG);
        } else {
            String msg = "";
            try {
                SOFForm form = responseN02b.getSofForms().get(0);
                M000 m000 = (M000)form.getFormBody();
                msg = m000.getRTNMSG();
            } catch (Exception e) {
            }
            if (StringUtils.isEmpty(msg)) {
                toaN02b.INFO.RET_MSG = SBSFormCode.valueOfAlias(formCodeN02b).getTitle();
            } else {
                toaN02b.INFO.RET_MSG = msg;
            }
            if (StringUtils.isEmpty(toaN02b.INFO.RET_MSG)) {
                toaN02b.INFO.RET_MSG = "冻结交易失败";
            }
            logger.info(toaN02b.INFO.RET_MSG);
            String strFormCodeN02bTmp =  SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", formCodeN02b);
            toaN02b.INFO.RET_CODE = strFormCodeN02bTmp;
            return toaN02b.toString();
        }

        // 代理支付
        byte[] sbsReqMsg = SbsTxnDataTransform.convertToTxnN020(tia, termID);
        // SBS
        byte[] bytes = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", "9009002", userid,
                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsg);
        // 解析SBS报文
        FebResponse response = new FebResponse();
        response.init(bytes);

        // bean -> txn bean
        ToaXml9009002 toa = new ToaXml9009002();

        try {
            String strSbsRtnformCodeTmp = response.getFormCodes().get(0);
            logger.info("SBS代理支付_支付交易返回码：" + strSbsRtnformCodeTmp);
            String strRtnCodeTmp = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", strSbsRtnformCodeTmp);

            if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(strRtnCodeTmp)) {
                toa.INFO.RET_MSG = TxnStatus.TXN_SUCCESS.getTitle();
                SOFForm form = response.getSofForms().get(0);
                if ("T531".equalsIgnoreCase(strSbsRtnformCodeTmp)) {
                    T531 t531 = (T531) form.getFormBody();
                    copyFormBodyToToa(t531, toa);
                } else if ("T999".equalsIgnoreCase(strSbsRtnformCodeTmp)) {
                    T999 t999 = (T999) form.getFormBody();
                    copyFormBodyToToa(t999, toa);
                }
            } else {
                // 失败的情况
                if (TxnStatus.TXN_RELATED_TRADE_FAILED.getCode().equalsIgnoreCase(strRtnCodeTmp)) {
                    // 失败原因
                    toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(strSbsRtnformCodeTmp).getTitle().getBytes(), "GBK");
                    if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
                        toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
                    }
                } else {
                    // 不明原因
                    toa.INFO.RET_MSG = TxnStatus.TXN_QRY_PEND.getTitle();
                }
            }
            toa.INFO.RET_CODE = strRtnCodeTmp;
        }catch (Exception e){
            logger.info("单笔对外支付：", e.getMessage());
            // 不明原因
            toa.INFO.RET_CODE = TxnStatus.TXN_QRY_PEND.getCode();
            toa.INFO.RET_MSG = TxnStatus.TXN_QRY_PEND.getTitle();
        }

        return toa.toString();
    }

    private String assembleMbpMsg(TiaXml9009002 tia) {

        StringBuilder builder = new StringBuilder();
        builder.append(StringPad.rightPad4ChineseToByteLength("Transact", 32, " "));
        builder.append(StringPad.rightPad4ChineseToByteLength("SBS", 16, " "));
        builder.append(StringPad.rightPad4ChineseToByteLength("105", 16, " "));

        // TODO 拼接报文
        String xml = "";
        TransactRequestRoot clientReqBean = new TransactRequestRoot();
        ClientRequestHead clientReqHead = new ClientRequestHead();
        TransactRequestParam clientReqParam = new TransactRequestParam();
        clientReqBean.setHead(clientReqHead);
        clientReqBean.setParam(clientReqParam);
        // TODO Head内变量赋值
        clientReqHead.setOpBankCode("105");
        clientReqHead.setOpName("Transacts");
        clientReqHead.setOpName("Transacts");
        clientReqHead.setOpEntID("SBS");
//        clientReqHead.setOpDate(tia.BODY.);

        /*
        public String ORGIDT;     // 交易机构
        public String FBTACT;     // 客户号
        public String ORDTYP;     // 交易类型
        public String RMTTYP;	  // 汇款类型
        public String CUSTYP;	  // 汇款帐户类型
        public String FEETYP;	  // 是否见单
        public String FEEACT;	  // 费用帐户
        public String PBKACT;	  // 人行账号
        public String CHQTYP;	  // 支票类型
        public String CHQPWD;	  // 支票密码
        public String FUNCDE;	  // 保留项
        public String ADVNUM;	  // FS流水号
         */
        clientReqParam.setToAccount(tia.BODY.BENACT);
        clientReqParam.setToName(tia.BODY.BENNAM);
        clientReqParam.setToBank(tia.BODY.BENBNK);
        clientReqParam.setReserved1(tia.BODY.CHQNUM);         // 转入账户内部行号
        clientReqParam.setReserved2(tia.BODY.CHQNUM);         // 转入账户12位行号

        clientReqParam.setFromAccount(tia.BODY.CUSACT);
        clientReqParam.setFromName(tia.BODY.RETNAM);
        clientReqParam.setFromBank(tia.BODY.AGENBK);          // 转出行
        clientReqParam.setEnterpriseSerial(tia.BODY.REQNUM);  // 请求序列号
        clientReqParam.setAmount(tia.BODY.TXNAMT);            // 金额
        clientReqParam.setCurrency(tia.BODY.TXNCUR);          // 币种
        clientReqParam.setUsage(tia.BODY.RETAUX);             // 用途
        clientReqParam.setTransDate(tia.BODY.ORDDAT);         // 交易日期

        clientReqParam.setSystem("1");


        // TODO

        return new JaxbHelper().beanToXml(TransactRequestRoot.class, clientReqBean);
    }
}

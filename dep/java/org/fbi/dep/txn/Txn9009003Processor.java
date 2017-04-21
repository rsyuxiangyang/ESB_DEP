package org.fbi.dep.txn;

import org.apache.commons.lang.StringUtils;
import org.fbi.dep.helper.sbsHelper.SBSRtnCodeConverter;
import org.fbi.dep.component.jms.JmsBytesClient;
import org.fbi.dep.enums.SBSFormCode;
import org.fbi.dep.enums.TxnStatus;
import org.fbi.dep.model.txn.TiaXml9009003;
import org.fbi.dep.model.txn.ToaXml9009003;
import org.fbi.dep.transform.SbsTxnDataTransform;
import org.fbi.dep.util.JaxbHelper;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.mbp.domain.ClientRequestHead;
import org.fbi.endpoint.mbp.domain.queryresultrequest.QueryResultRequestParam;
import org.fbi.endpoint.mbp.domain.queryresultrequest.QueryResultRequestRoot;
import org.fbi.endpoint.sbs.core.FebResponse;
import org.fbi.endpoint.sbs.domain.SOFForm;
import org.fbi.endpoint.sbs.model.form.re.T543;
import org.fbi.endpoint.sbs.model.form.re.WB02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 交易处理 单笔支付结果查询 SBS-MBP
 */
public class Txn9009003Processor extends AbstractTxnProcessor {

    private static Logger logger = LoggerFactory.getLogger(Txn9009003Processor.class);

    public String process(String userid, String msgData) {
        // 先发起SBS交易
        // byte[] sbsReqMsg = new TiaXml9009002Transform().run(msgData, userid);
        TiaXml9009003 tia = (TiaXml9009003) (new TiaXml9009003().getTia(msgData));
        String termID = PropertyManager.getProperty("sbs.termid." + userid.trim());
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }
        byte[] sbsReqMsg = SbsTxnDataTransform.convertToTxnN022(tia, termID);
        // SBS
        byte[] bytes = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", "9009003", userid,
                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsg);
        // 解析SBS报文
        FebResponse response = new FebResponse();
        response.init(bytes);
        // bean -> txn bean
        ToaXml9009003 toa = new ToaXml9009003();

        try {
            String strSbsRtnformCodeTmp = response.getFormCodes().get(0);
            logger.info("单笔支付结果查询_USERID：" + strSbsRtnformCodeTmp);
            String strRtnCodeTmp = SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("OuterSBS", strSbsRtnformCodeTmp);

            // 成功的情况
            if (TxnStatus.TXN_SUCCESS.getCode().equalsIgnoreCase(strRtnCodeTmp)) {

                SOFForm form = response.getSofForms().get(0);
                if ("T543".equalsIgnoreCase(strSbsRtnformCodeTmp)) {
                    T543 t543 = (T543) form.getFormBody();
                    copyFormBodyToToa(t543, toa);
                }
                if(StringUtils.isEmpty(toa.INFO.RET_MSG)){
                    toa.INFO.RET_MSG = TxnStatus.TXN_SUCCESS.getTitle();
                }
            } else {
                // 失败的情况
                if (TxnStatus.TXN_RELATED_TRADE_FAILED.getCode().equalsIgnoreCase(strRtnCodeTmp)) {
                    if ("WB02".equalsIgnoreCase(strSbsRtnformCodeTmp)) {
                        SOFForm form = response.getSofForms().get(0);
                        WB02 wb02 = (WB02) form.getFormBody();
                        copyFormBodyToToa(wb02, toa);
                    }

                    if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
                        // 失败原因
                        toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(strSbsRtnformCodeTmp).getTitle().getBytes(), "GBK");
                    }
                }else{
                    // 不明原因
                    toa.INFO.RET_MSG = TxnStatus.TXN_QRY_PEND.getTitle();
                }
            }
            // 返回码
            toa.INFO.RET_CODE=strRtnCodeTmp;
        }catch (Exception e){
            logger.error("单笔对外支付结果查询：", e);
            // 不明原因
            toa.INFO.RET_CODE = TxnStatus.TXN_QRY_PEND.getCode();
            toa.INFO.RET_MSG = TxnStatus.TXN_QRY_PEND.getTitle();
        }

        return toa.toString();
    }

    private String assembleMbpMsg(TiaXml9009003 tia) {
        String xml = "";
        QueryResultRequestRoot clientReqBean = new QueryResultRequestRoot();
        ClientRequestHead clientReqHead = new ClientRequestHead();
        QueryResultRequestParam clientReqParam = new QueryResultRequestParam();
        clientReqBean.setHead(clientReqHead);
        clientReqBean.setParam(clientReqParam);

        // TODO

        return new JaxbHelper().beanToXml(QueryResultRequestRoot.class, clientReqBean);
    }
}

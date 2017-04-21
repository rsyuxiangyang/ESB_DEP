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
 * ���״��� SBS-MBP
 * ������
 * �����״�������SBS���ף��ȶ�����֧����
 * ������SBS֧�������߼���
 * SBS ���� T531 �����״�����ɣ������յ��˽�����Ϣ���������״���ɹ��� DEPӦ����0000���ĵ���Ӧ����0000������ҵ��ɹ���
 * SBS ���� T999 �����ظ��ύ���ѷ������� ������ɡ�ͬT531��   DEPӦ����0000���ĵ���Ӧ����0000������ҵ��ɹ���
 * SBS ���� MB01 ����MBP��ȷ���س�ʱ�����������ѯ���׽���ȷ�ϡ�  DEPӦ����2000��
 * SBS ���� MZZZ �������з���ҵ����ʧ�ܣ��������㣩��  DEPӦ����1000��
 * SBS ���� ��T��W��ͷ  ����SBSҵ����ʧ�ܣ���Ӧ�ø���SBS��ȷ�Ĵ���������жϡ�  �����忴XML���ã�
 * SBS ���� ������Ӧ�� δ������
 */
public class Txn9009002Processor extends AbstractTxnProcessor {

    private static Logger logger = LoggerFactory.getLogger(Txn9009002Processor.class);

    public String process(String userid, String msgData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        // �ȷ���SBS����
        // byte[] sbsReqMsg = new TiaXml9009002Transform().run(msgData, userid);
        TiaXml9009002 tia = (TiaXml9009002) (new TiaXml9009002().getTia(msgData));
        String termID = PropertyManager.getProperty("sbs.termid." + userid.trim());
        logger.info("SBS����֧��_USERID��" + userid.trim());
        if (StringUtils.isEmpty(termID)) {
            termID = "MT01";
        }

        // Ԥ�ȶ���
        byte[] sbsReqMsgN02b = SbsTxnDataTransform.convertToTxnN02b(tia, termID);
        // SBS
        byte[] bytesN02b = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", "9009002", userid,
                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsgN02b);
        // ����SBS����
        FebResponse responseN02b = new FebResponse();
        responseN02b.init(bytesN02b);
        String formCodeN02b = responseN02b.getFormCodes().get(0);
        logger.info("SBS����֧��_���ύ�׷����룺" + formCodeN02b);
        // bean -> txn bean
        ToaXml9009002 toaN02b = new ToaXml9009002();
        // ������ʱ����,��ʱ���
        if ("T531".equalsIgnoreCase(formCodeN02b)) {
            toaN02b.INFO.RET_MSG = "���ύ�����";
            SOFForm form = responseN02b.getSofForms().get(0);
            T531 t531 = (T531) form.getFormBody();
            logger.info(toaN02b.INFO.RET_MSG);
        } else if ("T999".equalsIgnoreCase(formCodeN02b)) {
            toaN02b.INFO.RET_MSG = "���ύ�����";
            SOFForm form = responseN02b.getSofForms().get(0);
            T999 t999 = (T999) form.getFormBody();
            logger.info(toaN02b.INFO.RET_MSG);
        } else if ("W001".equalsIgnoreCase(formCodeN02b)) {
            toaN02b.INFO.RET_MSG = "���ύ�����";
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
                toaN02b.INFO.RET_MSG = "���ύ��ʧ��";
            }
            logger.info(toaN02b.INFO.RET_MSG);
            String strFormCodeN02bTmp =  SBSRtnCodeConverter.GetRtnCodeFromSBSRtnCode("InnerSBS", formCodeN02b);
            toaN02b.INFO.RET_CODE = strFormCodeN02bTmp;
            return toaN02b.toString();
        }

        // ����֧��
        byte[] sbsReqMsg = SbsTxnDataTransform.convertToTxnN020(tia, termID);
        // SBS
        byte[] bytes = new JmsBytesClient().sendRecivMsg("900", "fcdep", "fcdep", "9009002", userid,
                "queue.dep.core.fcdep.sbs", "queue.dep.core.sbs.fcdep", sbsReqMsg);
        // ����SBS����
        FebResponse response = new FebResponse();
        response.init(bytes);

        // bean -> txn bean
        ToaXml9009002 toa = new ToaXml9009002();

        try {
            String strSbsRtnformCodeTmp = response.getFormCodes().get(0);
            logger.info("SBS����֧��_֧�����׷����룺" + strSbsRtnformCodeTmp);
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
                // ʧ�ܵ����
                if (TxnStatus.TXN_RELATED_TRADE_FAILED.getCode().equalsIgnoreCase(strRtnCodeTmp)) {
                    // ʧ��ԭ��
                    toa.INFO.RET_MSG = new String(SBSFormCode.valueOfAlias(strSbsRtnformCodeTmp).getTitle().getBytes(), "GBK");
                    if (StringUtils.isEmpty(toa.INFO.RET_MSG)) {
                        toa.INFO.RET_MSG = TxnStatus.TXN_FAILED.getTitle();
                    }
                } else {
                    // ����ԭ��
                    toa.INFO.RET_MSG = TxnStatus.TXN_QRY_PEND.getTitle();
                }
            }
            toa.INFO.RET_CODE = strRtnCodeTmp;
        }catch (Exception e){
            logger.info("���ʶ���֧����", e.getMessage());
            // ����ԭ��
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

        // TODO ƴ�ӱ���
        String xml = "";
        TransactRequestRoot clientReqBean = new TransactRequestRoot();
        ClientRequestHead clientReqHead = new ClientRequestHead();
        TransactRequestParam clientReqParam = new TransactRequestParam();
        clientReqBean.setHead(clientReqHead);
        clientReqBean.setParam(clientReqParam);
        // TODO Head�ڱ�����ֵ
        clientReqHead.setOpBankCode("105");
        clientReqHead.setOpName("Transacts");
        clientReqHead.setOpName("Transacts");
        clientReqHead.setOpEntID("SBS");
//        clientReqHead.setOpDate(tia.BODY.);

        /*
        public String ORGIDT;     // ���׻���
        public String FBTACT;     // �ͻ���
        public String ORDTYP;     // ��������
        public String RMTTYP;	  // �������
        public String CUSTYP;	  // ����ʻ�����
        public String FEETYP;	  // �Ƿ����
        public String FEEACT;	  // �����ʻ�
        public String PBKACT;	  // �����˺�
        public String CHQTYP;	  // ֧Ʊ����
        public String CHQPWD;	  // ֧Ʊ����
        public String FUNCDE;	  // ������
        public String ADVNUM;	  // FS��ˮ��
         */
        clientReqParam.setToAccount(tia.BODY.BENACT);
        clientReqParam.setToName(tia.BODY.BENNAM);
        clientReqParam.setToBank(tia.BODY.BENBNK);
        clientReqParam.setReserved1(tia.BODY.CHQNUM);         // ת���˻��ڲ��к�
        clientReqParam.setReserved2(tia.BODY.CHQNUM);         // ת���˻�12λ�к�

        clientReqParam.setFromAccount(tia.BODY.CUSACT);
        clientReqParam.setFromName(tia.BODY.RETNAM);
        clientReqParam.setFromBank(tia.BODY.AGENBK);          // ת����
        clientReqParam.setEnterpriseSerial(tia.BODY.REQNUM);  // �������к�
        clientReqParam.setAmount(tia.BODY.TXNAMT);            // ���
        clientReqParam.setCurrency(tia.BODY.TXNCUR);          // ����
        clientReqParam.setUsage(tia.BODY.RETAUX);             // ��;
        clientReqParam.setTransDate(tia.BODY.ORDDAT);         // ��������

        clientReqParam.setSystem("1");


        // TODO

        return new JaxbHelper().beanToXml(TransactRequestRoot.class, clientReqBean);
    }
}

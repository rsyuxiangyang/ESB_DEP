package org.fbi.endpoint.sbs.domain;

import org.apache.commons.lang.StringUtils;
import org.fbi.endpoint.sbs.model.form.re.M000;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sbs��Ӧ����form
 */
public class SOFForm {
    private static Logger logger = LoggerFactory.getLogger(SOFForm.class);

    public int length;                                      // Form�ܳ���
    public final static int formHeaderLength = 27;          // Formͷ����
    public final static int formBodyFieldLength = 2;        // Form�峤�����ֽ���
    public short formBodyLength;                            // Form�峤��
    private SOFFormHeader formHeader;                       // Formͷ
    private SOFFormBody formBody;                           // Form��

    public void assembleFields(int offset, byte[] buffer) {
        // ��ͷ����
        formHeader = new SOFFormHeader();
        formHeader.assembleFields(offset, buffer);
        // ���峤��
        byte[] dataLengthBytes = new byte[formBodyFieldLength];
        System.arraycopy(buffer, offset + formHeaderLength, dataLengthBytes, 0, formBodyFieldLength);
        short s1 = (short) (dataLengthBytes[1] & 0xff);
        short s2 = (short) ((dataLengthBytes[0] << 8) & 0xff00);
        formBodyLength = (short) (s2 | s1);
        // ���ܳ���
        length = formHeaderLength + formBodyFieldLength + formBodyLength;

        if (formBodyLength != 0 && !StringUtils.isEmpty(formHeader.getFormCode().trim())) {
            // ����
            logger.info("FormCode:" + formHeader.getFormCode() + " ���峤�ȣ�" + formBodyLength);
            /*
            * (SBS���ش���ΪW,T��ͷ����ɹ���������ĸ��ͷ����ʧ�ܣ�
            * �ɹ�ʱ�����峤�Ⱦ�����0��W���سɹ���Ϣ����form�壬���������Լ��form��ʽ,T���ؾ���form��ʽ��
            * ʧ��ʱ�����峤��ͨ��Ϊ0��)ADD BY YXY
            * */
            if (formHeader.getFormCode().startsWith("T")||formHeader.getFormCode().startsWith("W")){
                try {
                    // ʵ����Form��
                    // ����ϵͳ���ж�
                    Class clazz = Class.forName("org.fbi.endpoint.sbs.model.form." + formHeader.getFormSys().toLowerCase() + "." + formHeader.getFormCode());
                    formBody = (SOFFormBody) clazz.newInstance();
                    // ��ȡForm���ֽ�����
                    byte[] bodyBytes = new byte[formBodyLength];
                    System.arraycopy(buffer, offset + formHeaderLength + formBodyFieldLength, bodyBytes, 0, formBodyLength);
                    // װ��Form��
                    formBody.assembleFields(0, bodyBytes);
                } catch (Exception e) {
                    logger.error("Form��������", e);
                    throw new RuntimeException(formHeader.getFormCode() + "|SBS�����룺" + formHeader.getFormCode());
                }
            } else {
                try {
                    if(formHeader.getFormCode().startsWith("M")){
                        // ʵ����Form��
                        // ����ϵͳ���ж�
                        //Class clazz = Class.forName("org.fbi.endpoint.sbs.model.form.re.M000");
                        //formBody = (SOFFormBody) clazz.newInstance();
                        M000 m000=new M000();
                        m000.fieldLengths = new int[]{formBodyLength};
                        formBody = (SOFFormBody)m000;
                        // ��ȡForm���ֽ�����
                        byte[] bodyBytes = new byte[formBodyLength];
                        System.arraycopy(buffer, offset + formHeaderLength + formBodyFieldLength, bodyBytes, 0, formBodyLength);
                        // װ��Form��
                        formBody.assembleFields(0, bodyBytes);
                    }
                } catch (Exception e) {
                    logger.error("Form��������", e);
                    throw new RuntimeException(formHeader.getFormCode() + "|SBS�����룺" + formHeader.getFormCode());
                }
            }
        }
    }

    public SOFFormHeader getFormHeader() {
        return formHeader;
    }

    public SOFFormBody getFormBody() {
        return formBody;
    }
}

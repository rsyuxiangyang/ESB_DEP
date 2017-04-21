package org.fbi.endpoint.sbs.txn;

import org.fbi.endpoint.sbs.core.SOFDataDetail;

/**
 01	SOF-TRANSMIT-DATA
 15	SOF-HEADER-G
 20	SOF-FMH		X(3)	�ո�
 20	SOF-BRANCH-ID	������	X(10)	��������ȡ��	010
 20	SOF-TERM-ID		X(4)	��������ȡ��
 20	SOF-OUT-DEVICE		X(1)	�̶�ֵ
 20	SOF-FORM-ID
 25	SOF-FORM-ID-DEV	�豸��	X(1)
 25	SOF-FORM-ID-SYS	ϵͳ��	X(2)
 25	SOF-FORM-ID-CODE	FORM��	X(4)		T***���ɹ�
 20	SOF-STATUS	�����ֶ�	X(2)
 15	SOF-DATA-G
 20	SOF-DATA-LEN	������	X(2)	ʧ��ʱΪ0,�ɹ�ʱΪ�������ʵ�ʳ���
 15	TXXX-CURCNT	�����ڼ�¼��	X(6)	��6λǰ��0
 	����Ϊ�ظ�������ݣ��ظ�n�Σ�n=�����ڼ�¼����

 20	T108-ACTNUM	�ʺ�	X(22)	��������ȡ��
 20	T108-ACTNAM	�ʻ���	X(72)	����룬�Ҳ��ո�
 20	T108-BOKBAL	�ʻ����	X(17)	�Ҷ��룬�󲹿ո�,������Ϊ�����������������һ������	+9(13).99
 20	T108-AVABAL	��Ч���	X(17)	�Ҷ��룬�󲹿ո�	+9(13).99
 20	T108-FRZSTS	����״̬	X(1)		"0-û�м�¼
 1-ȫ����׼��
 2-ȫ����׼ȡ
 3-ȫ����׼��
 4-���ֲ�׼��
 R-�м�¼"
 20	T108-ACTSTS	�ʻ�״̬	X(1)		�ո�-����,I-ʧЧ
 20	T108-RECSTS	��¼״̬	X(1)		�ո�-����,I-ʧЧ

 */
public class T8119SOFDataDetail extends SOFDataDetail {

    private String ACTNUM;
    private String ACTNAM;
    private String BOKBAL;
    private String AVABAL;
    private String FRZSTS;
    private String ACTSTS;
    private String RECSTS;

    {
        offset = 0;
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{22, 72, 17, 17, 1, 1, 1};
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getACTSTS() {
        return ACTSTS;
    }

    public void setACTSTS(String ACTSTS) {
        this.ACTSTS = ACTSTS;
    }

    public String getAVABAL() {
        return AVABAL;
    }

    public void setAVABAL(String AVABAL) {
        this.AVABAL = AVABAL;
    }

    public String getBOKBAL() {
        return BOKBAL;
    }

    public void setBOKBAL(String BOKBAL) {
        this.BOKBAL = BOKBAL;
    }

    public String getFRZSTS() {
        return FRZSTS;
    }

    public void setFRZSTS(String FRZSTS) {
        this.FRZSTS = FRZSTS;
    }

    public String getRECSTS() {
        return RECSTS;
    }

    public void setRECSTS(String RECSTS) {
        this.RECSTS = RECSTS;
    }
}

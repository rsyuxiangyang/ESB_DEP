package org.fbi.endpoint.sbs.model.form.ac;

import org.fbi.endpoint.sbs.domain.AssembleModel;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 8872 �ռ���������������
 * Created by IntelliJ IDEA.
 * User: hanjianlong
 * Date: 15-8-11
 * Time: ����2:12
 * To change this template use File | Settings | File Templates.
 */
public class T846 extends SOFFormBody {
    {
        fieldTypes = new int[]{1, 1, 1, 1};
        fieldLengths = new int[]{10, 21, 10, 21};
    }

    private String DRCNT;   //�跽�ܱ���
    private String DRAMT;   //�跽�ܽ��
    private String CRCNT;   //�����ֶ�
    private String CRAMT;   //�����ֶ�

    public String getDRCNT() {
        return DRCNT;
    }

    public void setDRCNT(String DRCNT) {
        this.DRCNT = DRCNT;
    }

    public String getDRAMT() {
        return DRAMT;
    }

    public void setDRAMT(String DRAMT) {
        this.DRAMT = DRAMT;
    }

    public String getCRCNT() {
        return CRCNT;
    }

    public void setCRCNT(String CRCNT) {
        this.CRCNT = CRCNT;
    }

    public String getCRAMT() {
        return CRAMT;
    }

    public void setCRAMT(String CRAMT) {
        this.CRAMT = CRAMT;
    }
}

package org.fbi.endpoint.sbs.model.form.re;

import org.fbi.endpoint.sbs.domain.SOFFormBody;

/**
 *  ��ӦSBS-n022����
 */
public class WB02 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1};
        fieldLengths = new int[]{18, 72};
    }

    private String FBTIDX;	// ��Χϵͳ��ˮ��
    private String RTNMSG;   // ���з�����Ϣ

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }

    public String getRTNMSG() {
        return RTNMSG;
    }

    public void setRTNMSG(String RTNMSG) {
        this.RTNMSG = RTNMSG;
    }
}

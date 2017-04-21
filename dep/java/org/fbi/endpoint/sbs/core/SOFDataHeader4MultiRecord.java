package org.fbi.endpoint.sbs.core;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-6-14
 * Time: ����5:03
 * To change this template use File | Settings | File Templates.
 */
public class SOFDataHeader4MultiRecord extends SOFDataHeader{
    /**
     * 20	SOF-DATA-LEN	������	X(2)	Ϊ0ʱ�����������
     * 15	TXXX-TOTCNT	�ܼ�¼��	X(6) 	��6λǰ��0
     * 15	TXXX-CURCNT	�����ڼ�¼��	X(6)	��6λǰ��0
     */
    private short datalen;
    private String totcnt;
    private String curcnt;

    {
        offset = SOFDATA_OFFSET ;
        fieldTypes = new int[]{2, 1, 1};
        fieldLengths = new int[]{2, 6, 6};
    }

    public String getTotcnt() {
        return totcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public String getCurcnt() {
        return curcnt;
    }

    public void setCurcnt(String curcnt) {
        this.curcnt = curcnt;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int[] getFieldLengths() {
        return fieldLengths;
    }

    public void setFieldLengths(int[] fieldLengths) {
        this.fieldLengths = fieldLengths;
    }
}

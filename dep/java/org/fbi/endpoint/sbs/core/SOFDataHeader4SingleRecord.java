package org.fbi.endpoint.sbs.core;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-6-14
 * Time: ����5:03
 * To change this template use File | Settings | File Templates.
 */
public class SOFDataHeader4SingleRecord extends SOFDataHeader{
    /**
     * 20	SOF-DATA-LEN	������	X(2)	Ϊ0ʱ�����������
     */
    private short datalen;

    {
        offset = SOFDATA_OFFSET ;
        fieldTypes = new int[]{2};
        fieldLengths = new int[]{2};
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

    public short getDatalen() {
        return datalen;
    }

    public void setDatalen(short datalen) {
        this.datalen = datalen;
    }
}

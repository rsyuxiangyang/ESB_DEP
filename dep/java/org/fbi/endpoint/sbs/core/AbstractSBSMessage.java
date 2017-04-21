package org.fbi.endpoint.sbs.core;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-6-15
 * Time: ����11:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSBSMessage implements FieldAssembler {
    protected int offset = 0;

    //�ֶ�����  1���ַ��� 2��������
    protected int[] fieldTypes;

    //�ֶγ���  �ֽ���
    protected int[] fieldLengths;

    public void assembleFields(byte[] buffer) {
        Class clazz = this.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            int pos = this.offset;
            for (int i = 0; i < this.fieldLengths.length; i++) {
                byte[] bytes = new byte[fieldLengths[i]];
                System.arraycopy(buffer, pos, bytes, 0, bytes.length);
                fields[i].setAccessible(true);
                if (this.fieldTypes[i] == 1) {
                    String s = new String(bytes);
                    fields[i].set(this, s);
                }else if (this.fieldTypes[i] == 2) {
                    short len = 0;
                    len = (short)((bytes[0] << 8 & 0xFF00 )| (bytes[1]  & 0x00FF ));
                    fields[i].set(this, len);
                }else {
                    throw new RuntimeException("�ֶ�����");
                }
                pos += bytes.length;
            }
        } catch (Exception e) {
            throw new RuntimeException("���Ĵ�������", e);
        }
    }

    public int getMessageLength(){
        int len = 0;
        for (int fieldLength : this.fieldLengths) {
            len += fieldLength;
        }
        return len;
    }

    public int getOffset() {
        return offset;
    }

    /*
    public void assembleFields(Object object, byte[] buffer, int[] fieldLengths, int offset) {
        Class clazz = object.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            int pos = offset;
            for (int i = 0; i < fieldLengths.length; i++) {
                byte[] b = new byte[fieldLengths[i]];
                System.arraycopy(buffer, pos, b, 0, b.length);
                String s = new String(b);
                fields[i].setAccessible(true);
                fields[i].set(object, s);
                pos += b.length;
            }
        } catch (Exception e) {
            throw new RuntimeException("���Ĵ�������", e);
        }
    }
*/

}

package org.fbi.endpoint.sbs.core;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-6-15
 * Time: ����11:51
 * To change this template use File | Settings | File Templates.
 */
public interface FieldAssembler {
    void assembleFields(byte[] buffer);
//    void assembleFields(Object object, byte[] buffer, int[] fieldLengths, int offset);
}

package org.fbi.dep.enums;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-23
 * Time: ����3:29
 * To change this template use File | Settings | File Templates.
 */
public interface EnumApp {
    public String getCode();

    public String getTitle();

    public int ordinal();

    @Override
    public String toString();
}

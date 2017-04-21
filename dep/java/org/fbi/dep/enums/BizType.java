package org.fbi.dep.enums;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-23
 * Time: ����3:30
 * To change this template use File | Settings | File Templates.
 */
public enum BizType implements EnumApp {
    XF("XF", "�����Ŵ�"),
    XFSF("XFSF", "�����Ŵ��׸���"),
    FD("FD", "ס������"),
    GM("GM", "�������Ŵ�"),
    HCSP("HCSP", "�ۺ�ϵͳ");

    private String code = null;
    private String title = null;
    private static Hashtable<String, BizType> aliasEnums;

    BizType(String code, String title) {
        this.init(code, title);
    }

    @SuppressWarnings("unchecked")
    private void init(String code, String title) {
        this.code = code;
        this.title = title;
        synchronized (this.getClass()) {
            if (aliasEnums == null) {
                aliasEnums = new Hashtable();
            }
        }
        aliasEnums.put(code, this);
        aliasEnums.put(title, this);
    }

    public static BizType valueOfAlias(String alias) {
        BizType bizType = aliasEnums.get(alias);
        return bizType;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}

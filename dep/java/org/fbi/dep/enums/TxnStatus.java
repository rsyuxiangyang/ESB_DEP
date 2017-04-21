package org.fbi.dep.enums;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-23
 * Time: ����3:30
 * To change this template use File | Settings | File Templates.
 */
public enum TxnStatus implements EnumApp {
    TXN_SUCCESS("0000", "���׳ɹ�"),
    TXN_FAILED("1000", "����ʧ��"),
    TXN_RELATED_TRADE_FAILED("1001", "��������ʧ��"),
    TXN_RSLT_NOTEXIST("1002", "��ѯ���������"),
    TXN_QRY_PEND("2000", "���׽������");

    private String code = null;
    private String title = null;
    private static Hashtable<String, TxnStatus> aliasEnums;

    TxnStatus(String code, String title) {
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

    public static TxnStatus valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}

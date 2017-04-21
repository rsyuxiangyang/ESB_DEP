package org.fbi.dep.enums;

import java.util.Hashtable;

/**
 交易成功类（0开头的：交易成功；）
 0000	交易成功。
 交易失败类（1开头的：交易失败；）
 1000	交易失败。
 1001	详细见9009003交易。
 1002	普通查询类交易，结果不存在。
 1...
 交易不明类（2开头的：交易结果不明。对于非0和1开头的，都是2开头的。）
 2000	交易结果不明。
 2...
 */

public enum TxnBusiRtnCode implements EnumApp {
    TXN_PROCESSED("0000", "交易成功"),
    TXN_PROFAILED("1000", "交易失败"),
    TXN_PREFAILED("1001", "前面交易结果失败"),
    TXN_QRYNOTFOUND("1002", "查询结果不存在"),
    TXN_DONTKNOW("2000", "交易结果不明");

    private String code = null;
    private String title = null;
    private static Hashtable<String, TxnBusiRtnCode> aliasEnums;

    TxnBusiRtnCode(String code, String title) {
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

    public static TxnBusiRtnCode valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String toRtnMsg() {
        return this.code + "|" + this.title;
    }
}

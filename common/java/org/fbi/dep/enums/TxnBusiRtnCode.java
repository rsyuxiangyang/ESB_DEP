package org.fbi.dep.enums;

import java.util.Hashtable;

/**
 ���׳ɹ��ࣨ0��ͷ�ģ����׳ɹ�����
 0000	���׳ɹ���
 ����ʧ���ࣨ1��ͷ�ģ�����ʧ�ܣ���
 1000	����ʧ�ܡ�
 1001	��ϸ��9009003���ס�
 1002	��ͨ��ѯ�ཻ�ף���������ڡ�
 1...
 ���ײ����ࣨ2��ͷ�ģ����׽�����������ڷ�0��1��ͷ�ģ�����2��ͷ�ġ���
 2000	���׽��������
 2...
 */

public enum TxnBusiRtnCode implements EnumApp {
    TXN_PROCESSED("0000", "���׳ɹ�"),
    TXN_PROFAILED("1000", "����ʧ��"),
    TXN_PREFAILED("1001", "ǰ�潻�׽��ʧ��"),
    TXN_QRYNOTFOUND("1002", "��ѯ���������"),
    TXN_DONTKNOW("2000", "���׽������");

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

package org.fbi.dep.enums;

import java.util.Hashtable;

/**
 * ����xml���ո����׷�����
 * TODO
 * 0000	���������ա���������֤�ɹ���
 1000	���Ľ������󣬰�����ʽ�����ȵ����⡣
 2001	����У�����MACУ���
 2002	����У������û������ڡ�
 2003	����У����󣺽������ڴ�
 3001	�˽��ײ����ã����ײ����ڡ�
 3002	�˽��ײ����ã����û�������ʹ�øý��ס�
 3003	�˽��ײ����ã�SBS�ӿ��ѹرգ���ʱ�β�������н��ס�
 9000	�������쳣��
 */
 /*
    6001 SBS���أ�����ϵͳ��δ������[F705]
    6002 SBS���أ�ҵ�����ڲ�����[M612]
    6003 SBS���أ��������������д�[M311]
    6004 SBS���أ������ظ���[T999]
    6005 SBS���أ����ʻ������ڡ�[M103]
    6006 SBS���أ��˻����㡣[M309]
    6007 SBS���أ���¼�����ڡ�[M104]
    6008 SBS���أ����׽��������[MZZZ]
    6999 SBS���أ���������[��]
     */
public enum TxnRtnCode implements EnumApp {
    TXN_PROCESSED("0000", "���������ս����ɹ�"),

    MSG_ANALYSIS_ILLEGAL("1000", "���Ľ�������"),

    MSG_VERIFY_MAC_ILLEGAL("2001", "����MACУ���"),
    MSG_VERIFY_USER_ILLEGAL("2002", "���Ĵ����û�������"),
    MSG_VERIFY_DATE_ILLEGAL("2002", "���Ĵ��󣺽������ڴ�"),

    TXN_NOT_EXIST("3001", "���ײ����ã����ײ�����"),
    TXN_NOT_ALLOWED("3002", "���ײ����ã�������ʹ��"),
    TXN_NOT_OPEN("3003", "���ײ����ã��ӿ��ѹر�"),

    TXN_ACT_CHECK_ERR("4000", "�����˺Ų�����ת��"),
    TXN_SYSID_CHECK_ERR("4001", "ϵͳID��,��������"),
    TXN_CHECK_ERR("4099", "��������δͨ��բ��У��"),

    SERVER_EXCEPTION("9000", "�������쳣");

    private String code = null;
    private String title = null;
    private static Hashtable<String, TxnRtnCode> aliasEnums;

    TxnRtnCode(String code, String title) {
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

    public static TxnRtnCode valueOfAlias(String alias) {
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

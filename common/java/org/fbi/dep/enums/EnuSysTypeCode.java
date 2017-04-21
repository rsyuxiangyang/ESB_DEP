package org.fbi.dep.enums;

import java.util.Hashtable;

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
public enum EnuSysTypeCode implements EnumApp {
     UNIONPAY("100", "����"),
     ALLINPAY("120", "ͨ��"),
     SBS("900", "SBS���ļ���ϵͳ"),
     FIP("910", "FIP�ʽ𽻻�ƽ̨"),
     TA_FDC("990", "̩�����ز��ʽ���ϵͳ");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuSysTypeCode> aliasEnums;

    EnuSysTypeCode(String code, String title) {
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

    public static EnuSysTypeCode valueOfAlias(String alias) {
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

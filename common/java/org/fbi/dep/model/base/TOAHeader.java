package org.fbi.dep.model.base;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 12-1-31
 * Time: ����2:10
 */
public class TOAHeader implements Serializable {
    public String CHANNEL_ID;                // ����
    public String APP_ID;                    // Ӧ��ID
    public String BIZ_ID;                    // ҵ��ID
    public String REQ_SN;                    // ��ˮ�� �ͻ���Ӧ��ϵͳ��Ψһ
    public String TX_CODE;                   // ����������
    public String RETURN_CODE;               // ������Ӧ��
    public String RETURN_MSG;                // ������Ӧ��Ϣ
    public String SIGNED_MSG;                // ���ױ���ǩ����Ϣ
}

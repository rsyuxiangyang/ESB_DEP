package org.fbi.endpoint.unionpay.core;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 2010-8-27
 * Time: 13:24:32
 * To change this template use File | Settings | File Templates.
 */

/*
    INFO	TRX_CODE	���״���	C(1, 20)	100004	��
	VERSION	�汾	C(2)	04	��
	DATA_TYPE	���ݸ�ʽ	N(1)	2��xml��ʽ	��
	LEVEL	������	N(1)	0ʵʱ����
            Ĭ��0	��
	USER_NAME	�û���	C(1,20)		��
	USER_PASS	�û�����			��
	REQ_SN	������ˮ��	C(30)		��	���ظ���ˮ(�Դ��ո�ϵͳ��ҪΨһ)
	SIGNED_MSG	ǩ����Ϣ	C		��	�Ա����ĵ�������Ϣ����ǩ�����������⡪��Ҳ����<SIGNED_MSG> </SIGNED_MSG>��
 */
public class BaseTiaHeader {
    public String TRX_CODE = "";
    public String VERSION = "04";
    public String DATA_TYPE = "2";
    public String LEVEL = "0";
    //����
    //public String USER_NAME = "YSCS002";
    //public String USER_PASS = "111111";

    //����
    //ס������
    //�û���   	�û���	�û�����	֤������	�̻����	          Ȩ��	�̻�����
    //DSF01954	HAIER3	123456	123456	000191400100880	����Ա	�������Ų����������ι�˾���˰��ң����գ�
    //DSF01955	HAIER4	123456	123456	000191400100880	�����	�������Ų����������ι�˾���˰��ң����գ�
    //public String USER_NAME = "DSF01955";

    //�����Ŵ�
    //DSF01956	HAIER5	123456	123456	000191400100881	����Ա	�������Ų����������ι�˾������գ�
    //DSF01957	HAIER6	123456	123456	000191400100881	�����	�������Ų����������ι�˾������գ�
    //public String USER_NAME = "DSF01957";

    public String USER_NAME = "";
    public String USER_PASS = "";


    public String REQ_SN = "";
    public String SIGNED_MSG = "";

}
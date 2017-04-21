package org.fbi.dep.model.txn;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.model.base.TIABody;
import org.fbi.dep.model.base.TIAHeader;

import java.io.Serializable;

/**
 * SBS: �˻�������ϸ��ѯ
 * User: zhanrui
 * Date: 2012-02-14
 */

public class TIA9008853 extends TIA implements Serializable {
    public  Header header = new Header();
    public  Body body = new Body();

    @Override
    public TIAHeader getHeader() {
        return  header;
    }

    @Override
    public TIABody getBody() {
        return  body;
    }

    //====================================================================
    public  static class Header extends TIAHeader {
    }

    public static class Body extends TIABody {
        public String BATSEQ = "111111";    //���� �̶�ֵ
        public String ORGIDT = "010";       //��Ա������ �̶�ֵ
        public String DEPNUM = "60";        //��Ա���ź� �̶�ֵ
        public String ORGID3 = "010";       //�ʻ������� �̶�ֵ
        public String ACTNUM = "";          //�ʻ��ʺ�   X(22)
        public String BEGDAT = "";          //��ʼ��������   ��ʽ��YYYYMMDD
        public String ENDDAT = "";          //��ֹ��������   ��ʽ��YYYYMMDD
        public String BEGNUM = "";          //��ʼ����  X(6)  �Ҷ��룬��0
                                            //ע�⣺�ý��������������-Ӧ��Ľ�����
                                            //��һ����0����n����д�ۼ�n-1��ʵ���յ��ļ�¼��+1
                                            //���磬�����ܹ��ɲ�ѯ��123����ϸ������һ�������������20��
                                            //��һ��������д000000���յ�Ӧ���յ�2�ʣ�01-20
                                            //�ڶ���������д000021���յ�Ӧ���յ�20�ʣ�21-40
                                            //���һ��������д000121���յ�Ӧ���յ�3�ʣ�121-123
    }
}

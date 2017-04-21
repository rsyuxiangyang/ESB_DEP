package org.fbi.dep.helper.allinPayHelper.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;

/**
 * Created by Lichao.W At 2015/6/24 22:21
 * wanglichao@163.com
 */
public class CryptNoRestrict implements CryptInf{
    public Provider prvd = null; //new BouncyCastleProvider();
    /**
     * ���캯��
     */
    public CryptNoRestrict()
    {
    }
    public CryptNoRestrict(String encoding)
    {
        this.encoding=encoding;
    }
    public CryptNoRestrict(String encoding,Provider prv)
    {
        this.encoding=encoding;
        this.prvd=prv;
    }
    private String encoding = "GBK";

    /**
     * ȡ���ϴε��ü��ܡ����ܡ�ǩ�������ɹ����������
     */
    protected String lastResult;

    /**
     * ������һ��ǩ�����
     */
    protected String lastSignMsg;

    /**
     * ���ַ�������ǩ��
     *
     * @param TobeSigned
     *            ��Ҫ����ǩ�����ַ���
     * @param KeyFile
     *            PFX֤���ļ�·��
     * @param PassWord
     *            ˽Կ��������
     * @return ǩ���ɹ�����true(��LastResult���Ի�ȡ���)��ʧ�ܷ���false(��LastErrMsg���Ի�ȡʧ��ԭ��)
     */
    public boolean SignMsg(final String TobeSigned, final String KeyFile, final String PassWord) throws Exception
    {
        boolean result = false;
        FileInputStream fiKeyFile = null;
        this.lastSignMsg = "";
        KeyStore ks = prvd==null?KeyStore.getInstance("PKCS12"):KeyStore.getInstance("PKCS12",prvd);
        // ks.load(new FileInputStream(KeyFile), PassWord.toCharArray());
        fiKeyFile = new FileInputStream(KeyFile);
        //PassWord.toCharArray()
        try
        {
            ks.load(fiKeyFile,PassWord.toCharArray());
        }
        catch(Exception ex)
        {
            if(fiKeyFile!=null) fiKeyFile.close();
            throw ex;
        }
        Enumeration myEnum = ks.aliases();
        String keyAlias = null;
        RSAPrivateCrtKey prikey = null;
        // keyAlias = (String) myEnum.nextElement();
		/* IBM JDK����ʹ��Whileѭ��ȡ���һ�����������ܵõ�����˽Կ���� */
        while (myEnum.hasMoreElements())
        {
            keyAlias = (String) myEnum.nextElement();
            // System.out.println("keyAlias==" + keyAlias);
            if (ks.isKeyEntry(keyAlias))
            {
                prikey = (RSAPrivateCrtKey) ks.getKey(keyAlias, PassWord.toCharArray());
                break;
            }
        }
        if (prikey == null)
        {
            result = false;
            throw new Exception("û���ҵ�ƥ��˽Կ");
        }
        else
        {
            Signature sign =prvd==null?Signature.getInstance("SHA1withRSA"):Signature.getInstance("SHA1withRSA",prvd);
            sign.initSign(prikey);
            sign.update(TobeSigned.getBytes(encoding));
            byte signed[] = sign.sign();
            byte sign_asc[] = new byte[signed.length * 2];
            Hex2Ascii(signed.length, signed, sign_asc);
            this.lastResult = new String(sign_asc);
            this.lastSignMsg = this.lastResult;
            result = true;
        }
        return result;
    }

    /**
     * ��֤ǩ��
     *
     * @param TobeVerified
     *            ����֤ǩ��������
     * @param PlainText
     *            ����֤ǩ��������
     * @param CertFile
     *            ǩ���߹�Կ֤��
     * @return ��֤�ɹ�����true��ʧ�ܷ���false(��LastErrMsg���Ի�ȡʧ��ԭ��)
     */
    public boolean VerifyMsg(String TobeVerified, String PlainText, String CertFile) throws Exception
    {
        boolean result = false;
        FileInputStream certfile = null;
        certfile = new FileInputStream(CertFile);
        CertificateFactory cf =prvd==null?CertificateFactory.getInstance("X.509"):CertificateFactory.getInstance("X.509",prvd);

        X509Certificate x509cert=null;
        try
        {
            x509cert = (X509Certificate) cf.generateCertificate(certfile);
        }
        catch(Exception ex)
        {
            if(certfile!=null) certfile.close();
            throw ex;
        }

        RSAPublicKey pubkey = (RSAPublicKey) x509cert.getPublicKey();
        Signature verify = prvd==null?Signature.getInstance("SHA1withRSA"):Signature.getInstance("SHA1withRSA",prvd);
        verify.initVerify(pubkey);
        byte signeddata[] = new byte[TobeVerified.length() / 2];
        Ascii2Hex(TobeVerified.length(), TobeVerified.getBytes(encoding), signeddata);
        verify.update(PlainText.getBytes(encoding));
        if (verify.verify(signeddata))
        {
            result = true;
        }
        else
        {
            result = false;
            throw new Exception("��ǩʧ��");
        }
        return result;
    }

    /**
     * �����ϴε��ü��ܡ����ܡ�ǩ�������ɹ����������
     *
     * @return �����ϴε��ü��ܡ����ܡ�ǩ�������ɹ����������
     */
    public String getLastResult()
    {
        return this.lastResult;
    }

    /**
     * ������һ��ǩ�����
     *
     * @return ǩ�����
     */
    public String getLastSignMsg()
    {
        return this.lastSignMsg;
    }

    /**
     * ��ʮ����������ת����ASCII�ַ���
     *
     * @param len
     *            ʮ���������ݳ���
     * @param data_in
     *            ��ת����ʮ����������
     * @param data_out
     *            ��ת����ASCII�ַ���
     */
    private static void Hex2Ascii(int len, byte data_in[], byte data_out[])
    {
        byte temp1[] = new byte[1];
        byte temp2[] = new byte[1];
        for (int i = 0, j = 0; i < len; i++)
        {
            temp1[0] = data_in[i];
            temp1[0] = (byte) (temp1[0] >>> 4);
            temp1[0] = (byte) (temp1[0] & 0x0f);
            temp2[0] = data_in[i];
            temp2[0] = (byte) (temp2[0] & 0x0f);
            if (temp1[0] >= 0x00 && temp1[0] <= 0x09)
            {
                (data_out[j]) = (byte) (temp1[0] + '0');
            }
            else if (temp1[0] >= 0x0a && temp1[0] <= 0x0f)
            {
                (data_out[j]) = (byte) (temp1[0] + 0x57);
            }

            if (temp2[0] >= 0x00 && temp2[0] <= 0x09)
            {
                (data_out[j + 1]) = (byte) (temp2[0] + '0');
            }
            else if (temp2[0] >= 0x0a && temp2[0] <= 0x0f)
            {
                (data_out[j + 1]) = (byte) (temp2[0] + 0x57);
            }
            j += 2;
        }
    }

    /**
     * ��ASCII�ַ���ת����ʮ����������
     *
     * @param len
     *            ASCII�ַ�������
     * @param data_in
     *            ��ת����ASCII�ַ���
     * @param data_out
     *            ��ת����ʮ����������
     */
    private static void Ascii2Hex(int len, byte data_in[], byte data_out[])
    {
        byte temp1[] = new byte[1];
        byte temp2[] = new byte[1];
        for (int i = 0, j = 0; i < len; j++)
        {
            temp1[0] = data_in[i];
            temp2[0] = data_in[i + 1];
            if (temp1[0] >= '0' && temp1[0] <= '9')
            {
                temp1[0] -= '0';
                temp1[0] = (byte) (temp1[0] << 4);

                temp1[0] = (byte) (temp1[0] & 0xf0);

            }
            else if (temp1[0] >= 'a' && temp1[0] <= 'f')
            {
                temp1[0] -= 0x57;
                temp1[0] = (byte) (temp1[0] << 4);
                temp1[0] = (byte) (temp1[0] & 0xf0);
            }

            if (temp2[0] >= '0' && temp2[0] <= '9')
            {
                temp2[0] -= '0';

                temp2[0] = (byte) (temp2[0] & 0x0f);

            }
            else if (temp2[0] >= 'a' && temp2[0] <= 'f')
            {
                temp2[0] -= 0x57;

                temp2[0] = (byte) (temp2[0] & 0x0f);
            }
            data_out[j] = (byte) (temp1[0] | temp2[0]);

            i += 2;
        }

    }

    protected String replaceAll(String strURL, String strAugs)
    {

        // JDK1.3��String��û��replaceAll�ķ���
        /** ********************************************************** */
        int start = 0;
        int end = 0;
        String temp = new String();
        while (start < strURL.length())
        {
            end = strURL.indexOf(" ", start);
            if (end != -1)
            {
                temp = temp.concat(strURL.substring(start, end).concat("%20"));
                if ((start = end + 1) >= strURL.length())
                {
                    strURL = temp;
                    break;
                }

            }
            else if (end == -1)
            {
                if (start == 0)
                    break;
                if (start < strURL.length())
                {
                    temp = temp.concat(strURL.substring(start, strURL.length()));
                    strURL = temp;
                    break;
                }
            }

        }

        temp = "";
        start = end = 0;

        while (start < strAugs.length())
        {
            end = strAugs.indexOf(" ", start);
            if (end != -1)
            {
                temp = temp.concat(strAugs.substring(start, end).concat("%20"));
                if ((start = end + 1) >= strAugs.length())
                {
                    strAugs = temp;
                    break;
                }

            }
            else if (end == -1)
            {
                if (start == 0)
                    break;
                if (start < strAugs.length())
                {
                    temp = temp.concat(strAugs.substring(start, strAugs.length()));
                    strAugs = temp;
                    break;
                }
            }

        }

        /** **************************************************************** */
        return strAugs;
    }

}

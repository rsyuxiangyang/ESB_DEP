package org.fbi.endpoint.bestpay.com.bestpay.rsa;

import org.fbi.dep.util.PropertyManager;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class RsaUtil {
    public static ServerCert serverCert = new ServerCert();
    public static PrivateKey key;
    public static String cert;

    /**
     * 获取私钥
     * @return
     * @throws java.security.GeneralSecurityException
     */
    public static PrivateKey getPrivateKey()
            throws GeneralSecurityException {
        if (key == null) {
            key = serverCert.getServerPrivateKey();
        }
        return key;
    }

    /**
     * 获取公钥
     * @return
     * @throws java.security.GeneralSecurityException
     */
    public static String getPublicKey()
            throws GeneralSecurityException {
        if ((cert == null) || ("".equals(cert))) {
            X509Certificate x509Certificate = null;
            x509Certificate = serverCert.getServerCert();
            cert = CertUtils.certToBase64Str(x509Certificate);
        }
        return cert;
    }

    /**
     * 加签
     * @param plainText
     * @return
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.GeneralSecurityException
     */
    public static String sign(String plainText)
            throws UnsupportedEncodingException, GeneralSecurityException {
        byte[] bytes = RsaCipher.sign(plainText.getBytes("UTF-8"), getPrivateKey());
        return Base64Utils.encode(bytes);
    }

    private static boolean DEP_IS_RUNNING_DEBUG = !"0".equals(PropertyManager.getProperty("dep.running.debug"));
    /**
     * 初始化证书信息
     */
    static {
        /*serverCert.setCertAlias("server");
        serverCert.setKeyStorePwd("123456");
        serverCert.setKeyStore("org/fbi/endpoint/bestpay/resources/cert/server.jks");*/
        if (DEP_IS_RUNNING_DEBUG) {
            serverCert.setCertAlias(PropertyManager.getProperty("test_bestpay_serverCert_certAlias"));
            serverCert.setKeyStorePwd(PropertyManager.getProperty("test_bestpay_serverCert_keyStorePwd"));
            serverCert.setKeyStore(PropertyManager.getProperty("test_bestpay_serverCert_keyStore"));
        } else {
            serverCert.setCertAlias(PropertyManager.getProperty("product_bestpay_serverCert_certAlias"));
            serverCert.setKeyStorePwd(PropertyManager.getProperty("product_bestpay_serverCert_keyStorePwd"));
            serverCert.setKeyStore(PropertyManager.getProperty("product_bestpay_serverCert_keyStore"));
        }
    }
}
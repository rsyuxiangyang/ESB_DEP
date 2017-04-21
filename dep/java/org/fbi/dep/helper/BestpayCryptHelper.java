package org.fbi.dep.helper;

import org.fbi.dep.helper.allinPayHelper.XmlTools;
import org.fbi.dep.util.PropertyManager;
import org.fbi.endpoint.bestpay.com.bestpay.rsa.RsaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.GeneralSecurityException;

public class BestpayCryptHelper {

    private static final Logger logger = LoggerFactory.getLogger(BestpayCryptHelper.class);
    private static final String pathCer = PropertyManager.getProperty("allinpay_crypt_pathCer");

    /**
     * 翼支付报文加签
     */
    public static String signMsgBest(String plainText) throws Exception{
        String signStr = "";
        try {
            signStr = RsaUtil.sign(plainText);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            signStr = "加签失败";
        }

        return signStr;
    }
}

package org.fbi.endpoint.bestpay.com.bestpay.rsa;

import org.bouncycastle.util.encoders.Base64;

public final class Base64Utils
{
    public static byte[] decode(String base64)
    {
        return Base64.decode(base64.getBytes());
    }

    public static String encode(byte[] bytes)
    {
        return new String(Base64.encode(bytes));
    }
}
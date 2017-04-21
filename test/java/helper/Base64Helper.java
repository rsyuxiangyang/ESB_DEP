package helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.plugin2.util.SystemUtil;

/**
 * MD5
 */
public class Base64Helper {

    String base64String = "whuang123";
    byte[] result = Base64.encodeBase64(base64String.getBytes());


    /**
    * 编码
    *
    * @param bstr
    * @return String
    */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
    byte[] bt = null;
    try {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
        e.printStackTrace();
        }
        return bt;
    }

    /***
    * encode by Base64
    */
    public static String encodeBase64(byte[] input) throws Exception {
          Class clazz = Class
                  .forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
          Method mainMethod = clazz.getMethod("encode", byte[].class);
          mainMethod.setAccessible(true);
          Object retObj = mainMethod.invoke(null, new Object[] { input });
           return (String) retObj;
       }

    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception {
        Class clazz = Class
                .forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
    }

    // 加密
    public static String getBase64(String str) {
            byte[] b = null;
            String s = null;
            try {
                b = str.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        if (b != null) {
                s = new BASE64Encoder().encode(b);
            }
        return s;
        }

    // 解密
    public static String getFromBase64(String s) {
    byte[] b = null;
    String result = null;
    if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                    b = decoder.decodeBuffer(s);
                    result = new String(b, "utf-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    return result;
    }


    public static void main(String[] args) {
        String md5 = null;
        md5 = MD5Helper.getMD5String("中国");
        System.out.println("md5:" + md5);

        String str = "Hello World";
        try{
                byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
                System.out.println("RESULT: " + new String(encodeBase64));
            } catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }


        String base64String = "whuang123";
        byte[] result = Base64.encodeBase64(base64String.getBytes());
        //SystemUtil.printBytes(result);
        //byte[] result2 = SystemUtil.encode(base64String.getBytes()).getBytes();
        //System.out.println("result2:"+result2);
        //byte[] result3 = SystemUtil.encodeBase64(base64String.getBytes()).getBytes();
        //boolean isSuccess = SystemUtil.isSame(result, result2);
        //Assert.assertEquals(true, isSuccess);
        //SystemUtil.printBytes(result2);
        //SystemUtil.printBytes(result3);
        //System.out.println(isSuccess);
    }
}

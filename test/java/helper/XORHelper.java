package helper;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * MD5
 */
public class XORHelper {

    private static int ENCRYPT_KEY_LEN = 8;

    /**
     * 数据解密
     * @param encrypData
     * @return
     */
    public static String XorCrevasse(String encrypData)
    {
        StringBuilder builder = new StringBuilder();
        int length = encrypData.length();
        if (length < (ENCRYPT_KEY_LEN * 8))
        {
            return "";
        }
        int num2 = Integer.parseInt(encrypData.substring(0, 4), 0x10);
        if (length != (((((num2 + ENCRYPT_KEY_LEN) - 1) / ENCRYPT_KEY_LEN) * ENCRYPT_KEY_LEN) * 8))
        {
            return "";
        }

        for (int i = 0; i < num2; i++)
        {
            String str2 = "";
            String str = "";
            str2 = Substring(i * 8,4,encrypData.getBytes());
            str = Substring((i * 8) + 4, 4,encrypData.getBytes());
            int num4 = Integer.parseInt(str2,0x10);
            int num5 = Integer.parseInt(str,0x10);
            builder.append((char) (num4 ^ num5));
        }
        return builder.toString();
    }

    /**
     * 数据加密
     * @param sourceData
     * @return
     */
    public static String XorEncrypt(String sourceData){
        StringBuilder builder = new StringBuilder();
        int[] numArray = new int[ENCRYPT_KEY_LEN];
        numArray[0] = sourceData.length();
        for (int i = 1; i < numArray.length; i++)
        {
            numArray[i] = (int) ((Math.random()*0xffff) % 0xffff);
        }
        int num2 = 0;
        int num3 = (int) ((((numArray[0] + ENCRYPT_KEY_LEN) - 1) / ENCRYPT_KEY_LEN) * ENCRYPT_KEY_LEN);
        String[] sourceArr = sourceData.split("");
        for (int j = 0; j < num3; j = j + 1)
        {
            if (j < numArray[0])
            {
                num2 = (sourceArr[j+1].hashCode() ^ numArray[j % ENCRYPT_KEY_LEN]);
            }
            else
            {
                num2 = (int) (numArray[j % ENCRYPT_KEY_LEN] ^ ((int) ((Math.random()*0xffff) % 0xffff)));
            }
            builder.append(stringToHex(numArray[j % ENCRYPT_KEY_LEN])).append(stringToHex(num2));
        }
        return builder.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String encrypData = "000A00692C992CF11D431D2290C990A7F24CF22B346E341D61C961A147774712000A00642C992CFE1D43EA4B90C98A9DF24CA55D346E03AE61C9B53D477789C4";
        //System.out.println("XOR加密:"+XorEncrypt("changsheng"));
        System.out.println("XOR解密："+XorCrevasse(encrypData));
        System.out.println(62661 & 15);
    }

    /**
     * 十六进制转换缺位补0
     * @param value
     * @return
     */
    public static String stringToHex(int value){
        String toHex = Integer.toHexString(value).toUpperCase();
        switch (toHex.length()) {
            case 1:
                toHex="000"+toHex;
                break;
            case 2:
                toHex="00"+toHex;
                break;
            case 3:
                toHex="0"+toHex;
                break;
            default:
                break;
        }
        return toHex;
    }

    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * 字符串截取
     * @param startIndex 起始位置
     * @param length 截取字符串长度
     * @param byt 字符字节
     * @return
     */
    public static String Substring(int startIndex,int length,byte[] byt){
        return new String(byt,startIndex,length);
    }


}

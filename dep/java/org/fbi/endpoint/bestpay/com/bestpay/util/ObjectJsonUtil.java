package org.fbi.endpoint.bestpay.com.bestpay.util;

import net.sf.json.JSONObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Object、JSon格式转换工具类
 */
public class ObjectJsonUtil
{
    /*public static String objToJson(Object obj)
            throws IOException, JsonMappingException {
        if (obj == null) return "";
        ObjectMapper mapper = new ObjectMapper();
        Writer strWriter = new StringWriter();
        mapper.writeValue(strWriter, obj);
        return strWriter.toString();
    }

    public static <T> T jsonToObj(String dataJsonStr, Class<T> clazz)
            throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(dataJsonStr, clazz);
    }*/


    public static String objectToJson(Object obj){
        JSONObject jsonStu = JSONObject.fromObject(obj);
        System.out.println(jsonStu.toString());
        return jsonStu.toString();
    }

    public static <T> Object jsonToObject(String json, Class<T> clazz){
        JSONObject jsonobject = JSONObject.fromObject(json);
        return JSONObject.toBean(jsonobject,clazz);
    }
}
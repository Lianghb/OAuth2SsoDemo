package com.boxfish.lhb.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by boxfish on 15/12/18.
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 根据json字符串转化为类对象
     *
     * @param jsonString json字符串
     * @param object     目标类
     * @param <T>        类
     * @return 转化好的类对象
     * @throws Exception 转化时异常
     */
    public static <T> T getFromJson(String jsonString, Class<T> object) throws Exception {
        return mapper.readValue(jsonString, object);
    }

    /**
     * 把一个对象转化为另一个对象
     *
     * @param srcObject 源对象
     * @param desObject  目标对象
     * @param <T>       类型
     * @return 转化好的对象
     * @throws Exception 转化时异常
     */
    public static <T> T readFromObject(Object srcObject, Class<T> desObject) throws Exception {
        return mapper.readValue(mapper.writeValueAsString(srcObject), desObject);
    }
}

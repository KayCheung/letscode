package com.tuniu.mob.category.util;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 * Created by zhangbin2 on 15/6/1.
 */
public class StringUtils {
    private static Logger logger= LoggerFactory.getLogger(StringUtils.class);

    /**
     * 字符串用base64解码后转换成指定charset的字符串
     * @param source
     * @param charset
     * @return
     */
    public static String getFromBase64(String source,Charset charset) {
        if(isEmpty(source)){
            return source;
        }
        return new String(Base64.decodeBase64(source.getBytes()),charset);
    }

    public static String getFromBase64(String source,String charset) {
        return getFromBase64(source,Charset.forName(charset));
    }

    public static String getFromBase64(String source){
        return getFromBase64(source, Charset.defaultCharset());
    }

    /**
     * 判断字符串是否为空
     * @param target
     * @return
     */
    public static boolean isEmpty(String target){
        if(null==target||"".equals(target)){
            return true;
        }
        return false;
    }
}

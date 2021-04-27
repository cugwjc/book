package com.cug.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author warogychenger
 */
public class WebUtils {

    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String str, int defaultValue){
        try {
            defaultValue = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defaultValue;
    }
}

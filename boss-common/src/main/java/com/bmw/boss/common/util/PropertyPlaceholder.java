package com.bmw.boss.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yd on 2018/3/30.
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer{
    private static Map<String,String> propertyMap;




    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new ConcurrentHashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    //static method for accessing context properties
    public static Object getProperty(String name) {
        return propertyMap.get(name);
    }
    //static method for accessing context properties
    public static String getPropertyString(String name) {
        return propertyMap.get(name).trim();
    }
    //static method for accessing context properties
    public static Integer getPropertyInteger(String name) {
        return Integer.valueOf(propertyMap.get(name).trim());
    }
}

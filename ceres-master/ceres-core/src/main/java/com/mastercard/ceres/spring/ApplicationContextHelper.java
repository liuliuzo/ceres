package com.mastercard.ceres.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @className ApplicationContextHelper
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static void setContext(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        setContext(applicationContext);
    }

    public static <T> T getBean(Class<T> targetClz) {
        return applicationContext.getBean(targetClz);
    }
}

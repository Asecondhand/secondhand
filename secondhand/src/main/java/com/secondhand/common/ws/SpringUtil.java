package com.secondhand.common.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-03-27 09:47
 **/
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;

        }
        for (String beanName:
        applicationContext.getBeanDefinitionNames()) {
            System.out.println("注册的bean名称有"+beanName);
        }
        System.out.println("---------------simple.plugin.spring.SpringUtil------------------------------------------------------");
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+SpringUtil.applicationContext+"========");
    }
    /**
     * 获取applicationContext
     */

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * //通过name获取 Bean.
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
    /**
     *  //通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);

    }
    /**
     *  //通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
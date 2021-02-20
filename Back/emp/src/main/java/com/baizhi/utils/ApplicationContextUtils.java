/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ApplicationContextUtils
 * Author:   Administrator
 * Date:     2020/9/8 10:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/9/8
 * @since 1.0.0
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }

    /*
     * 返回值为Redis的redisTemplate
     *
     * */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
}

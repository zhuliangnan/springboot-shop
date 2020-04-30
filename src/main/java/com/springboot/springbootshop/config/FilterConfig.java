package com.springboot.springbootshop.config;


import com.springboot.springbootshop.filter.Myfilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/*
* springbot 没有xml  @@Configuration 可以表示一个springxml的配置文件
*比如 applicationContext.xml
* */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean myFilterRegistrationBean(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean((Filter) new Myfilter());
        registrationBean.addUrlPatterns("/*");
        return  registrationBean;
    }
   //注册一个拦截字符编码的filter
    /*
    * 等价于 web.xml 中配置 characterEncodingFilter. 字符编码过滤器
    *
    * 默认情况这个配置不会生效 需要在spring.http.encoding.enable=false 配置成false才会生效
    * 即在核心配置文件配置
    * */
/*
   @Bean
   public FilterRegistrationBean filterRegistrationBean(){
       FilterRegistrationBean reeangistrationB=new FilterRegistrationBean();
       CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
       characterEncodingFilter.setForceEncoding(true);
       characterEncodingFilter.setEncoding("UTF-8");//设置utf-8 解决乱码相关问题
       registrationBean.setFilter(characterEncodingFilter);
       registrationBean.addUrlPatterns("/*");
       return  registrationBean;
   }
*/



}

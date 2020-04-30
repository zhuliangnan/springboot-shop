package com.springboot.springbootshop.config;


import com.springboot.springbootshop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  //把当前类变为配置类 这样在启动spingboot的时候会自动扫描这个类
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截路径
        String[]  addPathPatterns={
                "/Showallcart/**",
                "/Myorder/**",
                "/AddCartByDetail/**",
                "/Addorder/**",
                "/AddCartByDetail/**",
                "/PersonalCenter/**",
                "/OrdersManagement/**",
                "/AccountSecurity/**"

        };
        //不拦截路径
        String[]  excludePathPatterns={
                "/Admin/**",
                "/Login/**"
        };
        //addPathPatterns() 里面写拦截地址 excludePathPatterns()不拦截地址
        //注册登录拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
        //注册权限拦截器
       // registry.addInterceptor(new PowerInterceptor()).addPathPatterns(addPathPatterns1).excludePathPatterns(excludePathPatterns1);
    }
}

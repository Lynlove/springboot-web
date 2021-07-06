package com.example.springbootweb.config;

import com.example.springbootweb.component.LoginHandlerIntercept;
import com.example.springbootweb.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * webmvcconfigurer 扩展springMvc的功能
 */
@Configuration//标注配置类
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送succ请求，跳转到success界面
        registry.addViewController("/succ").setViewName("success");

        //设置欢迎页
        registry.addViewController("/").setViewName("index");

        //设置主页（登录成功后）
        registry.addViewController("main.html").setViewName("dashboard");

    }
    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求再排除允许通过的请求
        registry.addInterceptor(new LoginHandlerIntercept()).addPathPatterns("/**").excludePathPatterns("/","/user/login","/asserts/**");
    }

    @Bean
   public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
   }
}

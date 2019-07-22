package com.demeter.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

/**
*@Description web配置文件
*@Author 刘海亮
*@DateTime 2019/7/20 20:12
*/

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.demeter.portal"})
public class ServletConfig  implements WebMvcConfigurer {
    /**
     *@Description 配置jsp视图解析器
     *@Author 刘海亮
     *@DateTime 2019/6/12 16:52
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     *@Description 放行全部静态资源
     *@Author 刘海亮
     *@DateTime 2019/6/2 18:14
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }
    /**
    *@Description 静态资源自定义放行
    *@Author 刘海亮
    *@DateTime 2019/7/22 9:25
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**")//过滤静态资源
                .addResourceLocations("/public/");
    }
    /**
     *@Description 设置文件上传大小限制
     *@Author 刘海亮
     *@DateTime 2019/6/15 15:54
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setUploadTempDir();
        multipartResolver.setMaxInMemorySize(9999999);
        multipartResolver.setMaxInMemorySize(1);
        return multipartResolver;
    }
}

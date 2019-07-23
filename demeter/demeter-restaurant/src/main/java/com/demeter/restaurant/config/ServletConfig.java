package com.demeter.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.io.IOException;

/**
*@Description web配置文件
*@Author 刘海亮
*@DateTime 2019/7/20 20:12
*/

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.demeter.restaurant"})
public class ServletConfig  implements WebMvcConfigurer {
    @Autowired
    private SpringTemplateEngine templateEngine;
    /**
     *@Description Thymelear视图解析器
     *@Author 刘海亮
     *@DateTime 2019/7/6 11:59
     */
    @Bean
    public ViewResolver ThymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine);

        //解决中文乱码，这里必加
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    /**
     *@Description 模板引擎
     *@Author 刘海亮
     *@DateTime 2019/7/6 14:53
     */
    @Bean
    public SpringTemplateEngine TemplateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     *@Description 模板解析器
     *@Author 刘海亮
     *@DateTime 2019/7/6 15:10
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setOrder(0);
        //解决中文乱码，这里必加
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
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

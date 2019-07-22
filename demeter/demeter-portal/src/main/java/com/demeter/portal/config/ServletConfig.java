package com.demeter.portal.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//    @Bean
//    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter(){
//        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//        requestMappingHandlerAdapter.setMessageConverters();
//        }


//    @Bean
////    public FastJsonHttpMessageConverter getFastJsonHttpMessageConverter(){
////        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
////        List<MediaType> listMedia = new ArrayList<>();
////        listMedia.add(new MediaType("text/html;charset=UTF-8"));
////        listMedia.add(new MediaType("application/json"));
////        fastJsonHttpMessageConverter.setSupportedMediaTypes(listMedia);
////
////        fastJsonHttpMessageConverter.setFeatures();
////    }
//@Bean
//public HandlerAdapter handlerAdapter(WebBindingInitializer webBindingInitializer){
//
//    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//    //注入FastJSON
//    FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//    List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//    supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//    supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//    supportedMediaTypes.add(MediaType.TEXT_HTML);
//    fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes );
//    messageConverters.add(fastJsonHttpMessageConverter);
//    RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//    //参数及返回值处理
//    requestMappingHandlerAdapter.setMessageConverters(messageConverters);
//    //参数校验
//    requestMappingHandlerAdapter.setWebBindingInitializer(webBindingInitializer);
//    return requestMappingHandlerAdapter;
//}
}

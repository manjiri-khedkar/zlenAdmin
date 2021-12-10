package com.zlenadmin.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Value("${uploadPath}")
	String localStorage;
	
	@Value("${profilePath}")
	String profileImageStorage;
	
	@Value("${groupImagePath}")
	String groupImageStorage;
	
	@Value("${storiesImagePath}")
	String storiesImageStorage;
	
	@Value("${qrImagePath}")
	String qrImageStorage;
	
	@Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }    
	@Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){ 
    	registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/"); 
//            registry.addResourceHandler("/resources/imgs/**")
//                 .addResourceLocations("classpath:/imgs/");
            registry.addResourceHandler("/files/**")
            .addResourceLocations("file:///"+localStorage);
            
            registry.addResourceHandler("/storyImage/**")
            .addResourceLocations("file:///"+storiesImageStorage);
            
            registry.addResourceHandler("/storyVideo/**")
            .addResourceLocations("file:///"+groupImageStorage);
            
            registry.addResourceHandler("/qr/**")
            .addResourceLocations("file:///"+qrImageStorage);
    }
    
   
}
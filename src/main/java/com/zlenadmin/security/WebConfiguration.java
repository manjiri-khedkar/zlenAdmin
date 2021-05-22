package com.zlenadmin.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

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
    public void addResourceHandlers(ResourceHandlerRegistry registry){ 
            registry.addResourceHandler("/resources/imgs/**")
                 .addResourceLocations("classpath:/imgs/");
            registry.addResourceHandler("/files/**")
            .addResourceLocations("file:///"+localStorage);
            registry.addResourceHandler("/storyImage/**")
            .addResourceLocations("file:///"+profileImageStorage);
            registry.addResourceHandler("/storyVideo/**")
            .addResourceLocations("file:///"+groupImageStorage);
            registry.addResourceHandler("/qr/**")
            .addResourceLocations("file:///"+qrImageStorage);
    }
    
   
}
package com.zlenadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class ProcurementApp extends SpringBootServletInitializer{

	
	
	public static void main(String[] args) {
		 SpringApplication.run(ProcurementApp.class, args);
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}
	
	@Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("vivekraj.agrawal@gmail.com");
        message.setFrom("infosane.nagpur@gmail.com");
        message.setText("Mail from Infosane..");
        return message;
    }
	
	

}

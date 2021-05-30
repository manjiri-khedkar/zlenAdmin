package com.zlenadmin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.zlenadmin.AppUserDetailsServiceDAO;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
   @Autowired
   private AppUserDetailsServiceDAO userDetailsService;
	 
   @Autowired
   private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
  
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	  System.out.println("web security configure method... ");
    http
      .authorizeRequests()
        .antMatchers("/","/resources/**","/loginProcess","/login","/loginError","/resetPassword","/user/barChart","/user/bar-chart","/userDetailsListContents","/userStoriesListContents").permitAll()
        .antMatchers("/","/resources/**","/files/**","/loginProcess","/storyImage/**",
        		"/login","/loginError","/resetPassword","/user/barChart","/user/bar-chart",
        		"/userDetailsListContents","/userStoriesListContents").permitAll()
        .anyRequest().authenticated()
        .and()
      .formLogin()
      .successHandler(authenticationSuccessHandler)
      .failureHandler(authenticationSuccessHandler)
        .loginPage("/login")
        .permitAll()
        .and()
      .logout()
        .permitAll();
    http.csrf().disable();
  }
  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
      return authenticationManager();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
  }
  
}

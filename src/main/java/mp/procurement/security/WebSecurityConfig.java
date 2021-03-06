package mp.procurement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import mp.procurement.AppUserDetailsServiceDAO;

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
        .antMatchers("/","/resources/**","/loginProcess","/login","/loginError","/resetPassword").permitAll()
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

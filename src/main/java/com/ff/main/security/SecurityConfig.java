package com.ff.main.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Override
    @Bean("userDetailsService")
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean(); //default
    }
	
	@Autowired
	private DataSource dataSource;
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.cors().and().anonymous().disable().authorizeRequests()
            .antMatchers("/oauth/token").permitAll();
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select * from (select provider_email as email, provider_password as password, 1 from providers union " + 
        		"select user_email as email, user_password as password, 1 from users) as us where email = ?").authoritiesByUsernameQuery("select email as username, authority "
        				+ "from authorities " + "where email = ?");
    }
      
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
}

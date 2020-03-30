package com.ff.main.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
	private static final String RESOURCE_ID="my_rest_api";
	@Override
	public void configure(ResourceServerSecurityConfigurer resources){
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
			.cors().disable().anonymous().disable().authorizeRequests().antMatchers("/users/**").access("hasRole('USER')")
			.and().authorizeRequests().antMatchers("/providers/**").access("hasRole('PROVIDER')")
			.and().authorizeRequests().antMatchers("/auth/**").hasAnyRole("PROVIDER","USER") //.access("hasRole('PROVIDER', 'USER')")
			.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}

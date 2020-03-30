package com.ff.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
@Order(1)
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter{
	
	 @Autowired
     private UserDetailsService userDetailsService;
	
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	private AuthenticationManager authenticationManager;

	@Autowired
	public AuthorizationServer(AuthenticationManager authenticationManager) {
	    this.authenticationManager = authenticationManager;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore());
		endpoints.userDetailsService(userDetailsService);
	   endpoints.authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()")
			.allowFormAuthenticationForClients();
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients
			.inMemory()
			.withClient("client").secret(passwordEncoder.encode("123456"))
			.authorizedGrantTypes("password","authorization_code","refresh_token")
			.authorities("ROLE_CLIENT")
			.scopes("read","write","trust")
			.accessTokenValiditySeconds(120)
			.refreshTokenValiditySeconds(3600);
	}
}

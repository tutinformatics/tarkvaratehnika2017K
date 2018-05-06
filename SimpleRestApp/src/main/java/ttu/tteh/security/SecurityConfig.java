package ttu.tteh.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ttu.tteh.auth.AuthTokenFilter;
import ttu.tteh.auth.TokenService;
import ttu.tteh.user.UserService;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private TokenService tokenService;
	private UserService userService;
	
	public SecurityConfig(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}
	
    @Bean
    public AuthTokenFilter authTokenFilterBean() throws Exception {
        AuthTokenFilter authenticationTokenFilter = new AuthTokenFilter(tokenService, userService);
        return authenticationTokenFilter;
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.csrf().disable();
    	 httpSecurity.addFilterBefore(authTokenFilterBean(), BasicAuthenticationFilter.class);
    }
}

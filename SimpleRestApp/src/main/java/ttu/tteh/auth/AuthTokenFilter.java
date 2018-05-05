package ttu.tteh.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class AuthTokenFilter extends GenericFilterBean{

	private TokenService tokenService;
	
	private String tokenHeaderName = "authorization";

	public AuthTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		chain.doFilter(request, response);
	}
	
}

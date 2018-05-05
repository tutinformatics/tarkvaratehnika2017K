package ttu.tteh.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private String tokenHeaderName = "authorization";

	public AuthTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	/*@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	}*/

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Filtering stuff...........");
		filterChain.doFilter(request, response);
		
	}
}

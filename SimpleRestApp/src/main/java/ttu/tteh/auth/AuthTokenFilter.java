package ttu.tteh.auth;

import java.io.IOException;
import java.util.Optional;

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

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(this.tokenHeaderName);
		System.out.println("Looking for token");
		if (authToken != null) {
			Optional<Long> userId = tokenService.getUserIdFromToken(authToken);
			System.out.println("Hmz... inside token validation");
            if (userId.isPresent()) {
                System.out.println("USER ID FOUND!" + userId.get());
            }
		}
		System.out.println("filter chain...");
		filterChain.doFilter(request, response);
	}
}

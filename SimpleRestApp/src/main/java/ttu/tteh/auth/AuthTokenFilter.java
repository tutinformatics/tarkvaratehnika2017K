package ttu.tteh.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		if (authToken != null) {
			Optional<Long> userId = tokenService.getUserIdFromToken(authToken);
            if (userId.isPresent()) {
            	Authentication auth = new AuthenticationData(null);
            	SecurityContextHolder.getContext().setAuthentication(null);
            }
		}
		filterChain.doFilter(request, response);
	}
}

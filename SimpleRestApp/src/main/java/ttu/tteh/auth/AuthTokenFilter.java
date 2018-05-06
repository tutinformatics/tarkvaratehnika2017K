package ttu.tteh.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import ttu.tteh.user.UserService;

public class AuthTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private String tokenHeaderName = "authorization";

	private UserService userService;

	public AuthTokenFilter(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(this.tokenHeaderName);
		if (authToken != null) {
			Optional<Long> userId = tokenService.getUserIdFromToken(authToken);
            if (userId.isPresent()) {
            	UserDetails userDetails = userService.getUserById(userId.get());
            	Authentication auth = new AuthenticationData(null, userDetails);
            	SecurityContextHolder.getContext().setAuthentication(auth);
            }
		}
		filterChain.doFilter(request, response);
	}
}

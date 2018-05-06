package ttu.tteh.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationData extends AbstractAuthenticationToken{

	private static final long serialVersionUID = 1L;
	private UserDetails principal;

	public AuthenticationData(Collection<? extends GrantedAuthority> authorities, UserDetails principal) {
		super(authorities);
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}

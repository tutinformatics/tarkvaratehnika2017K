package ttu.tteh.auth;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
public class TokenService {
	private SecretKey JWTS_KEY = null;
	
	public TokenService() {
		generateSecretKeyToSignTokens();
	}

	private void generateSecretKeyToSignTokens() {
		this.JWTS_KEY = MacProvider.generateKey(SignatureAlgorithm.HS256);
	}
	
	String generateToken(long userId, long secondsToExpire) {
		Date now = new Date(System.currentTimeMillis());
		Date dateLimit = new Date(now.getTime() + secondsToExpire * 1000);
		String jsonWebToken = Jwts.builder()
				.setSubject(Long.toString(userId)) // user id of the logged user
				.setIssuedAt(now)
				.setExpiration(dateLimit)
				.signWith(SignatureAlgorithm.HS256, JWTS_KEY)
				.compact();
		return jsonWebToken;
	}
	
	public Optional<Long> getUserIdFromToken(String authToken) throws SignatureException{
		authToken = removeBearerPrefix(authToken);
		try {
			return Optional.of(Long.valueOf(Jwts.parser()
					.setSigningKey(JWTS_KEY)
					.parseClaimsJws(authToken)
					.getBody()
					.getSubject()));
		} catch (SignatureException e) {
			// token validation failed, no valid user information
			System.out.println();
			return Optional.empty();
		}
	}
	
	private String removeBearerPrefix(String token) {
		return token.substring(7);
	}
}

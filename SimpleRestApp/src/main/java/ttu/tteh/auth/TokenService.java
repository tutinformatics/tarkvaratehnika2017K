package ttu.tteh.auth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
}

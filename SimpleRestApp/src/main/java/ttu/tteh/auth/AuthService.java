package ttu.tteh.auth;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import ttu.tteh.user.User;
import ttu.tteh.user.UserService;

@Service
public class AuthService {

	private static final int TOKEN_LIFETIME = 6000;

	private TokenService tokenService;

	private UserService userService;
	
	public AuthService(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}
	
	private static final String FRONTEND_URL = "http://localhost:9000";

	private static final String GOOGLE_API_URL = "https://www.googleapis.com/oauth2/v4/token";
	
	// do not add google api json data to repository! security risk!
	final String CLIENT_SECRET_FILE = "/home/martin/Documents/google_cs.json";

	public AuthResponse authenticate(AuthRequest request) throws IllegalAccessError, GeneralSecurityException, IOException {
		
			GoogleTokenResponse tokenResponse = exchangeAuthCodeForToken(request);
			
			GoogleIdToken gid = tokenResponse.parseIdToken();
			
			// Payload means data on board the JWT
			Payload payload = gid.getPayload();
			
			verifyToken(gid, payload);
				
			String email = (String) payload.get("email");
			
			User user = userService.getOrCreateUserByEmail(email);
			
			String token = tokenService.generateToken(user.getId(), TOKEN_LIFETIME);
			
			return new AuthResponse(token);
	}

	private void verifyToken(GoogleIdToken gid, Payload payload)
			throws GeneralSecurityException, IOException, IllegalAccessError {
		// check if the token is authentic
		if(!new GoogleIdTokenVerifier(new NetHttpTransport(), JacksonFactory.getDefaultInstance()).verify(gid)) {
			throw new IllegalAccessError("False token, verification failed!");
		}
		// we might also accept only tokens where email is verified
		if(!(boolean)payload.get("email_verified")) {
			throw new IllegalAccessError("Please verify your Google email first!");
		}
	}

	private GoogleTokenResponse exchangeAuthCodeForToken(AuthRequest request)
			throws IOException, FileNotFoundException {
		// Load connection data from json file
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(
						JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));

		// Exchange auth code for access token
		GoogleTokenResponse tokenResponse =
				new GoogleAuthorizationCodeTokenRequest(
						new NetHttpTransport(),
						JacksonFactory.getDefaultInstance(),
						GOOGLE_API_URL,
						clientSecrets.getDetails().getClientId(),
						clientSecrets.getDetails().getClientSecret(),
						request.getCode(),
						FRONTEND_URL)  // Specify the same redirect URI that you use with your web
													// app. If you don't have a web version of your app, you can
													// specify an empty string.
				.execute();
		
		String token = tokenResponse.getAccessToken();
		return tokenResponse;
	}
}

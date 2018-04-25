package ttu.tteh.auth;

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

@Service
public class AuthService {
	
	// do not add google api json data to repository! security risk!
	final String CLIENT_SECRET_FILE = "/home/martin/Documents/google_cs.json";

	public void authenticate(AuthRequest request) throws IllegalAccessError, GeneralSecurityException, IOException {
		
			// Load connection data from json file
			GoogleClientSecrets clientSecrets =
					GoogleClientSecrets.load(
							JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));

			// Exchange auth code for access token
			GoogleTokenResponse tokenResponse =
					new GoogleAuthorizationCodeTokenRequest(
							new NetHttpTransport(),
							JacksonFactory.getDefaultInstance(),
							"https://www.googleapis.com/oauth2/v4/token",
							clientSecrets.getDetails().getClientId(),
							clientSecrets.getDetails().getClientSecret(),
							request.getCode(),
							"http://localhost:9000")  // Specify the same redirect URI that you use with your web
														// app. If you don't have a web version of your app, you can
														// specify an empty string.
					.execute();
			
			String token = tokenResponse.getAccessToken();
			
			GoogleIdToken gid = tokenResponse.parseIdToken();
			
			// Payload means data on board the JWT
			Payload payload = gid.getPayload();
			
			System.out.println(payload.getSubject());
			
			System.out.println(payload.get("name"));
			System.out.println(payload.get("email"));
			System.out.println(payload);

	}
}

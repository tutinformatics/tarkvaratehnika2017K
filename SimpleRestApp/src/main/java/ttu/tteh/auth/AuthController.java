package ttu.tteh.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(value ="/auth/{provider}", method=RequestMethod.POST, 
		consumes = "application/json")
	public String authenticate(@PathVariable("provider") String provider, @RequestBody AuthRequest request) throws IllegalAccessError, GeneralSecurityException, IOException {
		
		return authService.authenticate(request);
	}
}

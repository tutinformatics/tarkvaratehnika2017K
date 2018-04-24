package ttu.tteh.auth;

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
	public String authenticate(@PathVariable("provider") String provider, @RequestBody AuthRequest request) {
		
		authService.authenticate(request);
		
		return "Debug to see if it works" + request.getClientId();
	}
}

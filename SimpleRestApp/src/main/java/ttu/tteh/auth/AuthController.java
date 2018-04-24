package ttu.tteh.auth;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ttu.tteh.user.UserService;

@RestController
public class AuthController {
	
	UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value ="/auth/{provider}", method=RequestMethod.POST, 
		consumes = "application/json")
	public String authenticate(@PathVariable("provider") String provider, @RequestBody AuthRequest request) {
		return "Debug to see if it works" + request.getClientId();
	}
}

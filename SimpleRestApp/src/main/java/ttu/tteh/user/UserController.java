package ttu.tteh.user;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ttu.tteh.auth.AuthenticationData;

@RestController
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/users/add", method=RequestMethod.POST,
			consumes = "application/json")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable("id") long userId) {
		return userService.getUserById(userId);
	}
	
	@RequestMapping(value = "/users/search/{searchStr}", method=RequestMethod.GET)
	public List<User> searchUsers(@PathVariable("searchStr") String searchStr) {
		return userService.searchUsersByLastName(searchStr);
	}
	
	@RequestMapping(value = "/auth/me", method = RequestMethod.GET)
	public User getLoggedUser(Principal principal) {
		AuthenticationData auth = (AuthenticationData) principal;
		User user = (User) auth.getPrincipal();
		
		return user;
	}
	
}

package ttu.tteh.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User addUser(User user) {
		// here you can do some validations etc before saving the user
		return userRepository.save(user);
	}
}

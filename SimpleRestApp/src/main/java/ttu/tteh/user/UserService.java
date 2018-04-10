package ttu.tteh.user;

import java.util.List;

import org.springframework.stereotype.Service;

import ttu.tteh.car.Car;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	User addUser(User user) {
		// here you can do some validations etc before saving the user
		user.setCar(new Car());
		return userRepository.save(user);
	}

	List<User> getAllUsers() {
		return userRepository.findAll();
	}

	User getUserById(long userId) {
		return userRepository.findOne(userId);
	}

	List<User> searchUsersByLastName(String searchStr) {
		return userRepository.findByLastName(searchStr);
	}
}

package ttu.tteh.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	long id;
	String firstName;
	String lastName;
	int numOfPets;
}

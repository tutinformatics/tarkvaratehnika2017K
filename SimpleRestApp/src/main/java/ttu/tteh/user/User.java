package ttu.tteh.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue
	long id;
	String firstName;
	String lastName;
	int numOfPets;
	@OneToOne(mappedBy="user",  // välja nimi Car klassis
			cascade=CascadeType.ALL)
	Car car;
}

package ttu.tteh.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import ttu.tteh.car.Car;
import ttu.tteh.pen.Pen;

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
	String email;
	
	@OneToOne(mappedBy="user",  // välja nimi Car klassis
			cascade=CascadeType.ALL)
	Car car;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	List<Pen> pens;
}

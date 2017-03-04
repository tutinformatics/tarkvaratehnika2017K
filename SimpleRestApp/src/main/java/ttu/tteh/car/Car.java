package ttu.tteh.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import ttu.tteh.user.User;

@Entity
@Getter
@Setter
public class Car {
	@Id
	@GeneratedValue
	long id;
	String name;
	@OneToOne()
	User user;
}

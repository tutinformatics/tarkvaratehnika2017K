package ttu.tteh.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
	@Id
	@GeneratedValue
	long id;
	String name;
}

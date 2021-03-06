package ttu.tteh.pen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import ttu.tteh.user.User;

@Entity
@Getter
@Setter
public class Pen {
	@Id
	@GeneratedValue
	long id;
	String color;
	@ManyToOne
	User user;
}

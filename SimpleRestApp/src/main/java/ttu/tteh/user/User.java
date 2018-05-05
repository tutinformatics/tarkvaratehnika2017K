package ttu.tteh.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import ttu.tteh.car.Car;
import ttu.tteh.pen.Pen;

@Entity
@Getter
@Setter
public class User implements UserDetails{
	@Id
	@GeneratedValue
	long id;
	String firstName;
	String lastName;
	int numOfPets;
	@Column(unique=true)
	String email;
	
	@OneToOne(mappedBy="user",  // välja nimi Car klassis
			cascade=CascadeType.ALL)
	Car car;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	List<Pen> pens;

	@Transient
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@JsonIgnore
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@JsonIgnore
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Transient
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Transient
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Transient
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}

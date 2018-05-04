package ttu.tteh.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	@Override
	public List<User> findAll();

	public List<User> findByLastName(String searchStr);
	
	@Query("SELECT U FROM User U"
			+ " JOIN U.pens P"
			+ " WHERE P.color=:color"
			+ " GROUP BY U")
	public List<User> findAllByPenColor(@Param("color") String color);
	
	public Optional<User> findByEmail(String email);
}

package cl.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import cl.app.model.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}

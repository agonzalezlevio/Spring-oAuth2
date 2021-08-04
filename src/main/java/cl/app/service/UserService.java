package cl.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.app.model.entity.User;
import cl.app.model.repository.IUserDao;

@Service
public class UserService implements IUserService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null) {
			logger.error("Username not found: " + user);
			throw new UsernameNotFoundException("Username Not Found");
		}
		return user;
	}

	@Override
	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
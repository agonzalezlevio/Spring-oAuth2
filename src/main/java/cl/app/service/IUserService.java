package cl.app.service;

import cl.app.model.entity.User;

public interface IUserService {

	public User findByUsername(String username);
}


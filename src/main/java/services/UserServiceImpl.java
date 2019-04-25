package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jdk.nashorn.internal.runtime.logging.Logger;
import model.User;
import repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired	
	private UserRepository userRepository;
	@Override
	@Logger
	@org.springframework.transaction.annotation.Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}
	
}

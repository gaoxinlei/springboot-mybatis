package net.self.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.self.bean.User;
import net.self.mapper.UserMapper;
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userMapper.selectUsers();
	}

}

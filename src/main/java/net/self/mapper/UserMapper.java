package net.self.mapper;

import java.util.List;

import net.self.bean.User;


public interface UserMapper {

	User selectUserById(int id);
	List<User> selectUsers();
}

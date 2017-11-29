package net.self.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.self.bean.User;
import net.self.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/list")
	public String listUsers(Map<String, Object> map) {
		List<User> users = userService.getUsers();
		map.put("users", users);
		return "users";
	}
}

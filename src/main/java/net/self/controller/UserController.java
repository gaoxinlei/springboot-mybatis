package net.self.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
	
	//读取配置文件的属性
		//共四种方式:http://www.imooc.com/article/18252
		//一、@ConfigurationProperties方式(个人认为需要在配置文件中配置复杂对象如list或map或有级联对象属性时用它.
		//二、使用@Value注解方式(只是简单字符串适用).
		//三、使用Environment(同样适用于简单字符串).
		//四、使用PropertiesLoaderUtils(个人认为简单字符串适用),复杂.
		//测试用value注解和environment.
		@Value("${spring.datasource.url}")
		private String url;
		
		//测试使用Environment方式获取属性
		@Autowired
		private Environment env;
	
	@RequestMapping("/property")
	public String getProperties() {
		System.out.println("@Value注解读取到的url:"+url);
		System.out.println("自动注入Environment读取到的url:"+env.getProperty("spring.datasource.url"));
		return "index";
	}
}

package cn.no7player.controller;

import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回首页的controller,测试jsp是否成功导入.
 * @author ezgaoxi
 *
 */
@Controller
@EnableAutoConfiguration
public class IndexController {

	
	@RequestMapping("/index.do")
	public String toIndex() {
		return "index";
	}
	
	@RequestMapping("/")
	public String index(Map<String, Object> map) {
		//放入一个要显示的属性以测试thymeleaf模板.
		map.put("name", "gao");
		return "index";
	}

}

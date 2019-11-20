package online.shixun.project.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import online.shixun.project.model.Sort;
import online.shixun.project.service.UserService;


@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	
	// 首页
	@RequestMapping(value = "")
	public String index(Model model, HttpSession session) {

		// 获取当前用户可访问的商品分类
		Set<Sort> sorts = userService.getSortByUserName((String) session.getAttribute("username"));
		
		model.addAttribute("sorts", sorts);
		return "index";
	}
}

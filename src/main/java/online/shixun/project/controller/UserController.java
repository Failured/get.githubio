package online.shixun.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.project.model.User;
import online.shixun.project.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// 登录页面
	@RequestMapping(value = "login")
	public String login() {
		
		return "login";
	}
	
	
	// 请求登录(只接受POST请求)
	@PostMapping(value = "doLogin")
	@ResponseBody
	public String doLogin(User user, HttpSession session) {
		
		User u = userService.login(user);
		if (u != null) {
			// 登录成功
			session.setAttribute("username", u.getUsername());
			return "success";
		} else {
			// 登录失败
			return "fail";
		}
	}
	
	
	// 用户注销
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}

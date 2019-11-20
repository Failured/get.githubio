package online.shixun.project.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.project.dao.UserDao;
import online.shixun.project.model.Sort;
import online.shixun.project.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public User getUserByUserName(String username) {
		return userDao.selectUserByUserName(username);
	}
	
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
		// 根据用户查询用户信息
		User u = getUserByUserName(user.getUsername());
		if (u != null && u.getPassword() != null && u.getPassword().equals(user.getPassword())) {
			return u;
		}
		return null;
	}
	
	
	/**
	 * 根据用户名查询用户可访问的商品分类
	 * @param username
	 * @return
	 */
	public Set<Sort> getSortByUserName(String username){
		return userDao.selectSortByUserName(username);
	}
}

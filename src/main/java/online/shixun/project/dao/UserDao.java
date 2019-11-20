package online.shixun.project.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.shixun.project.model.Sort;
import online.shixun.project.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public User selectUserByUserName(String username) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			User user = (User) session.createQuery("from User where username=:username").setParameter("username", username).uniqueResult();
			return user;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 根据用户名查询用户可访问的商品分类
	 * @param username
	 * @return
	 */
	public Set<Sort> selectSortByUserName(String username){
		Session session = null;
		Set<Sort> sorts = new HashSet<Sort>();
		try {
			session = sessionFactory.openSession();
			User user = (User) session.createQuery("from User where username=:username").setParameter("username", username).uniqueResult();
			sorts = user.getPermission().getSorts();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sorts;
	}
}

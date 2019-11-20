package online.shixun.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.shixun.project.model.Sort;

@Repository
public class SortDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * 获取全部分类数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sort> selectAllSort(){
		Session session = null;
		List<Sort> sorts = new ArrayList<Sort>();
		
		try {
			session = sessionFactory.openSession();
			
			sorts = session.createQuery("from Sort order by id").list();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return sorts;
	}
	
	/**
	 * 根据分类ID获取分类数据
	 * @param id
	 * @return
	 */
	public Sort selectSortById(Long id) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			
			Sort sort = (Sort) session.createQuery("from Sort where id=:id").setParameter("id", id).uniqueResult();
			
			return sort;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return null;
	}
}

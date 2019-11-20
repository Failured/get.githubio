package online.shixun.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.shixun.project.model.Commodity;
import online.shixun.project.model.Sort;


@Repository
public class CommodityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * 获取全部商品数量
	 * @param sorts
	 * @return
	 */
	public Long selectAllCommodityCount(Set<Sort> sorts) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Long count = (Long) session.createQuery("select count(*) from Commodity where effective=:effective and sort in (:sorts)")
						.setParameter("effective", true)
						.setParameterList("sorts", sorts)
						.uniqueResult();
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return 0L;
	}
	
	/**
	 * 根据商品分类获取全部商品数量
	 * @param sortId
	 * @param sorts
	 * @return
	 */
	public Long selectAllCommodityCountBySortId(Long sortId, Set<Sort> sorts) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Long count = (Long) session.
					createQuery("select count(*) from Commodity where sort.id=:id and effective=:effective and sort in (:sorts)")
					.setLong("id", sortId)
					.setParameterList("sorts", sorts)
					.setBoolean("effective", true)
					.uniqueResult();
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return 0L;
	}
	
	/**
	 * 获取全部商品分页数据
	 * @param page
	 * @param size
	 * @param sorts
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Commodity> selectAllCommodityPagination(Integer page, int size, Set<Sort> sorts){
		Session session = null;
		List<Commodity> commoditys = new ArrayList<Commodity>();
		
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Commodity where effective=:effective and sort in (:sorts)");
			query.setParameter("effective", true);
			query.setParameterList("sorts", sorts);
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
			commoditys = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return commoditys;
	}
	
	/**
	 * 根据分类ID获取全部商品分页数据
	 * @param sortId
	 * @param page
	 * @param size
	 * @param sorts
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Commodity> selectAllCommodityPaginationBySortId(Long sortId, Integer page, int size, Set<Sort> sorts){
		Session session = null;
		List<Commodity> commoditys = new ArrayList<Commodity>();
		
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Commodity where sort.id=:id and effective=:effective and sort in (:sorts)");
			query.setLong("id", sortId);
			query.setBoolean("effective", true);
			query.setParameterList("sorts", sorts);
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
			commoditys = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return commoditys;
	}
	
	
	/**
	 * 插入商品数据
	 * @param commodity
	 * @param sortId
	 * @return
	 */
	public boolean insertCommodity(Commodity commodity, Long sortId) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// 获取分类
			Sort sort = (Sort) session.get(Sort.class, sortId);
			commodity.setSort(sort);
			
			session.save(commodity);
			session.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return false;
	}
	
	/**
	 * 根据商品ID获取商品数据
	 * @param id
	 * @return
	 */
	public Commodity selectCommodityById(Long id) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Commodity commodity = (Commodity) session.get(Commodity.class, id);
			
			return commodity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return null;
	}
	
	/**
	 * 更新商品数据
	 * @param commodity
	 * @param sortId
	 * @return
	 */
	public boolean updateCommodity(Commodity commodity, Long sortId) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// 获取分类数据
			Sort sort = (Sort) session.get(Sort.class, sortId);
			
			// 获取商品信息
			Commodity comm = (Commodity) session.get(Commodity.class, commodity.getId());
			
			// 更新信息
			comm.setName(commodity.getName());
			comm.setPrice(commodity.getPrice());
			comm.setStock(commodity.getStock());
			comm.setVolume(commodity.getVolume());
			comm.setImgPath(commodity.getImgPath());
			comm.setEffective(commodity.isEffective());
			comm.setClickVolume(commodity.getClickVolume());
			comm.setIntroduction(commodity.getIntroduction());
			comm.setSort(sort);
			
			session.save(comm);
			session.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return false;
	}
	
	
	/**
	 * 根据商品名称关键字查询包含关键字的商品总记录数
	 * @param keyword
	 * @return
	 */
	public Long selectCommodityCountByNameKeyword(String keyword){
Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Long count = 0L;
			Query query = session.createQuery("select count(*) from Commodity where name like :keyword and effective=:effective")
					.setString("keyword", "%" + keyword + "%")
					.setBoolean("effective", true);
			count = (Long) query.uniqueResult();
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return 0L;
	}
	
	/**
	 * 根据商品名称关键字查询商品信息
	 * @param keyword
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Commodity> selectCommodityByNameKeyword(String keyword, Integer page, int size){
		Session session = null;
		List<Commodity> commoditys = new ArrayList<Commodity>();
		
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Commodity where name like :keyword and effective=:effective");
			query.setString("keyword", "%" + keyword + "%");
			query.setBoolean("effective", true);
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
			commoditys = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return commoditys;
	}
}

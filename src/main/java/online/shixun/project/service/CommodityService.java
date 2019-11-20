package online.shixun.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.project.dao.CommodityDao;
import online.shixun.project.model.Commodity;
import online.shixun.project.model.Sort;

@Service
public class CommodityService {

	@Autowired
	private CommodityDao commodityDao;
	
	/**
	 * 获取全部商品数量
	 * @param sorts
	 * @return
	 */
	public Long getAllCommodityCount(Set<Sort> sorts) {
		return commodityDao.selectAllCommodityCount(sorts);
	}
	
	/**
	 * 根据商品分类获取全部商品数量
	 * @param sortId
	 * @param sorts
	 * @return
	 */
	public Long getAllCommodityCountBySortId(Long sortId, Set<Sort> sorts) {
		return commodityDao.selectAllCommodityCountBySortId(sortId, sorts);
	}
	
	/**
	 * 获取全部商品分页数据
	 * @param page
	 * @param size
	 * @param sorts
	 * @return
	 */
	public List<Commodity> getAllCommodityPagination(Integer page, int size, Set<Sort> sorts){
		return commodityDao.selectAllCommodityPagination(page, size, sorts);
	}
	
	/**
	 * 根据分类ID获取全部商品分页数据
	 * @param sortId
	 * @param page
	 * @param size
	 * @param sorts
	 * @return
	 */
	public List<Commodity> getAllCommodityPaginationBySortId(Long sortId, Integer page, int size, Set<Sort> sorts){
		return commodityDao.selectAllCommodityPaginationBySortId(sortId, page, size, sorts);
	}
	
	/**
	 * 添加商品数据
	 * @param commodity
	 * @param sortId
	 * @return
	 */
	public boolean addCommodity(Commodity commodity, Long sortId) {
		return commodityDao.insertCommodity(commodity, sortId);
	}
	
	/**
	 * 根据商品ID获取商品数据
	 * @param id
	 * @return
	 */
	public Commodity getCommodityById(Long id) {
		return commodityDao.selectCommodityById(id);
	}
	
	/**
	 * 更新商品数据
	 * @param commodity
	 * @param sortId
	 * @return
	 */
	public boolean updateCommodity(Commodity commodity, Long sortId) {
		return commodityDao.updateCommodity(commodity, sortId);
	}
	
	/**
	 * 根据商品ID删除商品数据
	 * @param id
	 * @return
	 */
	public boolean deleteCommodityById(Long id) {
		// 查询商品信息
		Commodity commodity = getCommodityById(id);
		if (commodity == null) {
			return false;
		}
		commodity.setEffective(false);
		return updateCommodity(commodity, commodity.getSort().getId());
	}
	
	
	/**
	 * 给商品添加点击量
	 * @param id
	 * @return
	 */
	public boolean addCommodityClickVolume(Long id) {
		// 查询商品信息
		Commodity commodity = getCommodityById(id);
		if (commodity == null) {
			return false;
		}
		commodity.setClickVolume(commodity.getClickVolume() + 1);
		
		// 更新数据
		return updateCommodity(commodity, commodity.getSort().getId());
	}
	
	/**
	 * 根据商品名称关键字查询包含关键字的商品总记录数
	 * @param keyword
	 * @return
	 */
	public Long getCommodityCountByNameKeyword(String keyword){
		return commodityDao.selectCommodityCountByNameKeyword(keyword);
	}
	
	/**
	 * 根据商品名称关键字查询商品信息
	 * @param keyword
	 * @return
	 */
	public List<Commodity> getCommodityByNameKeyword(String keyword, Integer page, int size){
		return commodityDao.selectCommodityByNameKeyword(keyword, page, size);
	}
}

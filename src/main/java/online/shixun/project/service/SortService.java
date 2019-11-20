package online.shixun.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.project.dao.SortDao;
import online.shixun.project.model.Sort;

@Service
public class SortService {

	@Autowired
	private SortDao sortDao;
	
	/**
	 * 获取全部分类数据
	 * @return
	 */
	public List<Sort> getAllSort(){
		return sortDao.selectAllSort();
	}
	
	/**
	 * 根据分类ID获取分类数据
	 * @param id
	 * @return
	 */
	public Sort getSortById(Long id) {
		return sortDao.selectSortById(id);
	}
}

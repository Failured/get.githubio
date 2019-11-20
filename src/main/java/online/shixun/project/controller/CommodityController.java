package online.shixun.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.shixun.project.dto.PageDto;
import online.shixun.project.model.Commodity;
import online.shixun.project.model.Sort;
import online.shixun.project.service.CommodityService;
import online.shixun.project.service.SortService;
import online.shixun.project.service.UserService;

@Controller
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	@Autowired
	private SortService sortService;
	@Autowired
	private UserService userService;

	
	// 更加分类ID获取全部商品信息
	@RequestMapping(value = "allCommodity")
	public String allCommodity(@RequestParam("sortId") Long sortId, 
							   @RequestParam("page") Integer page, 
							   @RequestParam("size") int size, 
							   HttpSession session,
							   Model model) {
		List<Commodity> commoditys = new ArrayList<Commodity>();
		
		Set<Sort> sorts = userService.getSortByUserName((String) session.getAttribute("username"));
		
		if (sortId == -1) {
			// 获取全部商品分页页数据
			commoditys = commodityService.getAllCommodityPagination(page, size, sorts);
			
			if (commoditys.size() == 0) {
				model.addAttribute("message", "暂无商品信息");
				return "prompt";
			}
			
			// 获取全部商品的分页数据
			Long count = commodityService.getAllCommodityCount(sorts);
			PageDto pageDto = new PageDto(count, size, page);
			
			model.addAttribute("pageDto", pageDto);
			model.addAttribute("title", "全部商品 - 信息");
		} else {
			// 根据商品分类获取全部商品信息
			commoditys = commodityService.getAllCommodityPaginationBySortId(sortId, page, size, sorts);
			
			if (commoditys.size() == 0) {
				model.addAttribute("message", "暂无商品信息");
				return "prompt";
			}
			
			// 获取根据商品分类获取全部商品的分页数据
			Long count = commodityService.getAllCommodityCountBySortId(sortId, sorts);
			PageDto pageDto = new PageDto(count, size, page);
			
			// 根据分类ID获取分类信息
			Sort sort = sortService.getSortById(sortId);
			model.addAttribute("pageDto", pageDto);
			model.addAttribute("title", sort.getName() + " - 信息");
		}
		
		model.addAttribute("commoditys", commoditys);
		return "commodityList";
	}
	
	
	// 获取分类数据
	@RequestMapping(value = "getSorts")
	@ResponseBody
	public String getSorts(HttpSession session) {
		
//		List<Sort> sorts = sortService.getAllSort();
		Set<Sort> sorts = userService.getSortByUserName((String) session.getAttribute("username"));
		return JSON.toJSONString(sorts);
	}
	
	
	// 添加商品
	@RequestMapping(value = "addCommodity")
	@ResponseBody
	public String addCommodity(Commodity commodity, @RequestParam("sortId") Long sortId) {
		
		// 插入商品数据
		if (commodityService.addCommodity(commodity, sortId)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	
	// 根据ID获取商品数据
	@RequestMapping(value = "getCommodity")
	@ResponseBody
	public String getCommodity(@RequestParam("commodityId") Long id) {
		
		// 根据商品ID获取商品信息
		Commodity commodity = commodityService.getCommodityById(id);
		
		return JSON.toJSONString(commodity);
	}
	
	
	// 更新商品数据
	@RequestMapping(value = "updateCommodity")
	@ResponseBody
	public String updateCommodity(Commodity commodity, @RequestParam("sortId") Long sortId) {
		
		// 更新商品数据
		if (commodityService.updateCommodity(commodity, sortId)) {
			return "success";
		}
		
		return "fail";
	}
	
	// 删除商品数据
	@RequestMapping(value = "deleteCommodity")
	@ResponseBody
	public String deleteCommodity(@RequestParam("id") Long id) {
		
		// 根据商品ID删除商品数据
		if (commodityService.deleteCommodityById(id)) {
			return "success";
		}
		
		return "fail";
	}
	
	// 商品详细信息页面
	@RequestMapping(value = "info")
	public String info(@RequestParam("id") Long id, Model model) {
		
		// 增加该商品点击数
		commodityService.addCommodityClickVolume(id);
		
		// 根据ID获取获取商品信息
		Commodity commodity = commodityService.getCommodityById(id);
		
		// 无该商品信息
		if (commodity == null) {
			model.addAttribute("message", "暂无该商品信息");
			return "prompt";
		}
		
		model.addAttribute("commodity", commodity);
		return "info";
	}
	
	// 商品搜索
	@RequestMapping(value = "search")
	public String info(@RequestParam("keyword") String keyword,
						@RequestParam("page") Integer page, 
						@RequestParam("size") int size, 
						Model model) {
		
		// 根据商品名称查询商品数据
		List<Commodity> commoditys = commodityService.getCommodityByNameKeyword(keyword, page, size);

		if (commoditys.size() == 0) {
			// 暂无商品信息
			model.addAttribute("message", "暂无商品信息");
			return "prompt";
			
		} else if (commoditys.size() == 1 && page == 1){
			// 添加点击量
			commodityService.addCommodityClickVolume(commoditys.get(0).getId());
			
			// 只有一个结果，跳转到商品详情页面
			model.addAttribute("commodity", commoditys.get(0));
			return "info";
		
		} else {
			// 当数据大于两条时，以列表形式展示
			Long count = commodityService.getCommodityCountByNameKeyword(keyword);
			PageDto pageDto = new PageDto(count, size, page);
			
			model.addAttribute("pageDto", pageDto);
			model.addAttribute("commoditys", commoditys);
			model.addAttribute("title", "搜索结果");
			return "commodityList";
		}
	}
	
}

package online.shixun.project.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDto {
	
	// 总数
	private Long total = 0L;
	// 每页显示数量
	private int size = 10;
	// 分页列表
	private List<Integer> pageList = new ArrayList<Integer>();
	// 当前页数
	private int pageNow = 1;
	// 是否有上一页
	private boolean previousPage = false;
	// 上一页页码
	private Integer previous = 1;
	// 是否有下一页
	private boolean nextPage = false;
	// 下一页页码
	private Integer next = 1;
	
	/**
	 * 构造函数
	 * @param total   总数
	 * @param size    每页大小
	 * @param pageNow 当前页码
	 */
	public PageDto(Long total, int size, int pageNow) {
		
		this.total = total;
		this.size = size;
		this.pageNow = pageNow;
		
		// 计算总页数
		int roughPage = (int) (total/size);
		int totalPage = roughPage != ((double)total/size) ? roughPage + 1 : roughPage;

		// 页码是否有
		// 上一页
		if (pageNow - 1 > 0) {
			previousPage = true;
			previous -= 1;
		}
		// 下一页
		if (pageNow + 1 <= totalPage) {
			nextPage = true;
			next += 1;
		}
		
		
		// 低于或等于11页
		if (totalPage <= 11) {
			for (int i=1; i<=totalPage; i++) {
				pageList.add(i);
			}
			
		} else {
			// 高于11页
			int index = 1;
			if (pageNow > 6) {
				index = pageNow - 5;
			}
			if (pageNow > totalPage - 5) {
				index = totalPage - 10;
			}
			for (int i=0; i<11; i++) {
				pageList.add(index++);
				if (index > totalPage) {
					break;
				}
			}
		}
		
	}

	
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public boolean isPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(boolean previousPage) {
		this.previousPage = previousPage;
	}

	public Integer getPrevious() {
		return previous;
	}

	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}
	
}

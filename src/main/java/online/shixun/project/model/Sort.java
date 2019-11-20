package online.shixun.project.model;


/**
 * 商品分类实体类
 * @author am
 *
 */
public class Sort {

	// 编号
	private Long id;
	// 分类名称
	private String name;
	
	
	
	public Sort() { }

	public Sort(String name) {
		this.name = name;
	}

	public Sort(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

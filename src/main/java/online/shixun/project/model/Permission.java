package online.shixun.project.model;

import java.util.Set;

/**
 * 权限实体类
 * @author am
 *
 */
public class Permission {

	// 编号
	private Long id;
	// 权限等级
	private int level;
	// 可访问分类（多对多）
	private Set<Sort> sorts;
	
	
	
	public Permission() { }

	public Permission(int level, Set<Sort> sorts) {
		this.level = level;
		this.sorts = sorts;
	}

	public Permission(Long id, int level, Set<Sort> sorts) {
		this.id = id;
		this.level = level;
		this.sorts = sorts;
	}

	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Set<Sort> getSorts() {
		return sorts;
	}
	
	public void setSorts(Set<Sort> sorts) {
		this.sorts = sorts;
	}
	
}

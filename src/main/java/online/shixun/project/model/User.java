package online.shixun.project.model;

/**
 * 用户实体类
 * @author am
 *
 */
public class User {

	// 编号
	private Long id;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 权限(多对一)
	private Permission permission;
	
	
	
	public User() { }

	public User(String username, String password, Permission permission) {
		this.username = username;
		this.password = password;
		this.permission = permission;
	}

	public User(Long id, String username, String password, Permission permission) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.permission = permission;
	}

	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Permission getPermission() {
		return permission;
	}
	
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
}

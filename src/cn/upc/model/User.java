package cn.upc.model;
/**
 * 用户实体
 * @author green
 *
 */
public class User {
	private int id;              //用户id
	private String username;     //用户名称
	private String password;     //用户密码
	public User() {
		
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
}

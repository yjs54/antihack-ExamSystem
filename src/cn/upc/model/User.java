package cn.upc.model;
/**
 * �û�ʵ��
 * @author green
 *
 */
public class User {
	private int id;              //�û�id
	private String username;     //�û�����
	private String password;     //�û�����
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

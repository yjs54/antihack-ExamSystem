package cn.upc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.upc.model.User;

/**
 * 用户数据访问对象
 * @author green
 *
 */
public class UserDao {
	/**
	 * 登录验证
	 * @param con 数据库连接对象
	 * @param user 登录的账户
	 * @return 返回一个用户对象,为null,则登录失败;反之,则登录成功
	 * @throws Exception 让调用者处理异常
	 */
	public User login(Connection con,User user) throws SQLException{
		//定义一个返回用户对象
		User resultUser=null;
		//拼写sql查询语句
		String sql="select * from user where username=? and password=?";
		//获取sql语句预编译对象
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		//ps执行sql查询语句返回结果集
		ResultSet rs=ps.executeQuery();
		//遍历结果集
		while(rs.next()){
			//初始化返回用户对象
			resultUser=new User(); 
			resultUser.setId(rs.getInt("id"));    //设置用户id
			resultUser.setUsername(rs.getString("username"));  //设置用户名称
			resultUser.setPassword(rs.getString("password"));  //设置用户密码
		}
		//返回用户对象
		return resultUser;
	}
}

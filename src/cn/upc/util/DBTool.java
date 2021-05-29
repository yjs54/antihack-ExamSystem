package cn.upc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author green
 *
 */
public class DBTool {
	private static String driver="com.mysql.jdbc.Driver";  //数据库驱动
	private static String url="jdbc:mysql://localhost:3306/examsystem";  //数据库连接地址
	private static String user="root"; //数据库连接用户
	private static String password="123456";  //数据库连接密码
	
	static{
		//新建一个properties,用于读取db.properties配置文件
		Properties p=new Properties();
		//新建一个字符串,保存配置文件的路径
		String path="cn//upc//util//db.properties";
		try {
			//调用Properties.load通过类加载获得配置文件的输入流
			/*p.load(DBTool.class.getClassLoader().getResourceAsStream(path));
			//读取配置文件中的配置参数
			driver=p.getProperty("driver");  //获取驱动
			url=p.getProperty("url");  //获取数据库连接地址
			user=p.getProperty("user");  //获取数据库用户
			password=p.getProperty("password");  //获取数据库密码*/
			try {
				//加载数据库驱动类到程序中
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("加载驱动失败",e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("找不到配置文件",e);
		}
	}
	/**
	 * 获取数据库连接
	 * @return 数据库连接对象
	 * @throws SQLException 提醒调用者捕获异常,并在finally中关闭关闭异常
	 */
	public static Connection getConnetion() throws SQLException{
		//通过DriverManager获得数据库连接
		return DriverManager.getConnection(url, user, password);
	}
	/**
	 * 关闭数据库连接
	 * @param con
	 */
	public static void close(Connection con){
		if(con!=null){ //如果数据连接不为空
			try {
				//关闭数据库连接
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("数据库关闭失败",e);
			}
		}
	}
//	/**
//	 * 测试数据库连接工具是否可用
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		Connection con=null;
//		try {
//			con=DBTool.getConnetion();
//			System.out.println("数据库连接成功!");
//		} catch (SQLException e) {
//			System.out.println("数据库连接失败!");
//			e.printStackTrace();
//		}finally{
//			DBTool.close(con);
//		}
//	}
}

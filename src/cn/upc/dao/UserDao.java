package cn.upc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.upc.model.User;

/**
 * �û����ݷ��ʶ���
 * @author green
 *
 */
public class UserDao {
	/**
	 * ��¼��֤
	 * @param con ���ݿ����Ӷ���
	 * @param user ��¼���˻�
	 * @return ����һ���û�����,Ϊnull,���¼ʧ��;��֮,���¼�ɹ�
	 * @throws Exception �õ����ߴ����쳣
	 */
	public User login(Connection con,User user) throws SQLException{
		//����һ�������û�����
		User resultUser=null;
		//ƴдsql��ѯ���
		String sql="select * from user where username=? and password=?";
		//��ȡsql���Ԥ�������
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps����
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		//psִ��sql��ѯ��䷵�ؽ����
		ResultSet rs=ps.executeQuery();
		//���������
		while(rs.next()){
			//��ʼ�������û�����
			resultUser=new User(); 
			resultUser.setId(rs.getInt("id"));    //�����û�id
			resultUser.setUsername(rs.getString("username"));  //�����û�����
			resultUser.setPassword(rs.getString("password"));  //�����û�����
		}
		//�����û�����
		return resultUser;
	}
}

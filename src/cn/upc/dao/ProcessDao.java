package cn.upc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.upc.model.Process;

public class ProcessDao {
	
	public ResultSet check(Connection con,Process process) throws SQLException{
		//ƴдsql��ѯ���
		String sql="select * from process";
		//��ȡsql���Ԥ�������
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeQuery();
	}

}


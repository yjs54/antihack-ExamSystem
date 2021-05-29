package cn.upc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.upc.model.Process;

public class ProcessDao {
	
	public ResultSet check(Connection con,Process process) throws SQLException{
		//∆¥–¥sql≤È—Ø”Ôæ‰
		String sql="select * from process";
		//ªÒ»°sql”Ôæ‰‘§±‡“Î∂‘œÛ
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeQuery();
	}

}


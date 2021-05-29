package cn.upc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import cn.upc.dao.ProcessDao;
import cn.upc.model.Process;
import cn.upc.util.DBTool;
 
public class Processkill extends Thread {
	Connection con=null;
	Process process=null;
	ArrayList<String> arr = new ArrayList<String>();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public void run()  {
		while(true){
			try {
				checkprocessName();
				sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	public Processkill(){
		
		
		try {
			con=DBTool.getConnetion();
			ProcessDao processDao=new ProcessDao();
			ResultSet rs=processDao.check(con, process);
			//遍历查询结果
			while(rs.next()){
				process=new Process();
				String psName=rs.getString("processName");
				arr.add(psName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("初始化列表失败",e);
		}
	}
	public boolean isRunning(String processName)
    {
        BufferedReader bufferedReader = null;
        try
        {
            java.lang.Process proc = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq "
                    + processName
                    + "\"");
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                if (line.contains(processName))
                {
                	System.out.println(processName);
                    return true;
                }
            }
            return false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (Exception ex)
                {
                }
            }
        } 
    }
	private  void checkprocessName() throws IOException{
		
		try {
			for(int i=0;i<arr.size();i++){
				boolean isrun=isRunning(arr.get(i));
				if(isrun){
					Runtime.getRuntime().exec("taskkill /f /im "+arr.get(i));
					//System.out.println("Having already killed");
				}else{
					//System.out.println("没有运行" + arr.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初始化列表失败",e);
		}
	}
	

}

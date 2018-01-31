/*
 * dao层
 */
package com.xueji.model2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlHelper {
	     //驱动使用
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
		String URL = "jdbc:sqlserver://localhost:1400; DatabaseName=Study";
		String user="sa";
		String password="jcl19950923";
		
		//定义数据库资源
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
		
		//查询数据库的操作
		public ResultSet quExectue(String sql,String[] date)
		{
	       try {
	    	 //1.加载驱动
   			Class.forName(driver);
   			//2.得到连接
   			ct=DriverManager.getConnection(URL,user,password);
   			//3.创建ps
   			ps=ct.prepareStatement(sql);
   			//4.给?赋值
   			for(int i=0;i<date.length;i++)
   			{
   				ps.setString(i+1, date[i]);
   			}
   			
   			rs=ps.executeQuery();
   			
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   }	
	       return rs;
	   }
		
      
		//把增删改合在一起
    	public 	boolean updExecute(String sql,String[] date)   //只负责修改,不管业务逻辑,老师,学生..都一样
    	{
    		boolean b=true;
    		try {
    			//1.加载驱动
    			Class.forName(driver);
    			//2.得到连接
    			ct=DriverManager.getConnection(URL,user,password);
    			//3.创建ps
    			ps=ct.prepareStatement(sql);
    			//4.给?赋值
    			for(int i=0;i<date.length;i++)
    			{
    				ps.setString(i+1, date[i]);
    			}
    			//5,更新
    			if(ps.executeUpdate()!=1){
    				b=false;
    			};
    		} catch (Exception e) {
    			// TODO: handle exception
    			b=false;
    			e.printStackTrace();
    		}finally{
    			this.downdate();
    		}
    		return b;
    	}
    	
    	//关闭数据库资源
    	public void downdate(){  
    		try {
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
    	}
			
}

/*
 * dao��
 */
package com.xueji.model2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlHelper {
	     //����ʹ��
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
		String URL = "jdbc:sqlserver://localhost:1400; DatabaseName=Study";
		String user="sa";
		String password="jcl19950923";
		
		//�������ݿ���Դ
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
		
		//��ѯ���ݿ�Ĳ���
		public ResultSet quExectue(String sql,String[] date)
		{
	       try {
	    	 //1.��������
   			Class.forName(driver);
   			//2.�õ�����
   			ct=DriverManager.getConnection(URL,user,password);
   			//3.����ps
   			ps=ct.prepareStatement(sql);
   			//4.��?��ֵ
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
		
      
		//����ɾ�ĺ���һ��
    	public 	boolean updExecute(String sql,String[] date)   //ֻ�����޸�,����ҵ���߼�,��ʦ,ѧ��..��һ��
    	{
    		boolean b=true;
    		try {
    			//1.��������
    			Class.forName(driver);
    			//2.�õ�����
    			ct=DriverManager.getConnection(URL,user,password);
    			//3.����ps
    			ps=ct.prepareStatement(sql);
    			//4.��?��ֵ
    			for(int i=0;i<date.length;i++)
    			{
    				ps.setString(i+1, date[i]);
    			}
    			//5,����
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
    	
    	//�ر����ݿ���Դ
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

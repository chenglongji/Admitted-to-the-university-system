package com.xueji.model1;

import java.sql.*;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel; //JTable的子类

/*
 * 这是我的一个stu表的模型
 */
public class StuModel extends AbstractTableModel {

	
	   // 重写显示列名的方法
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

	//定义数据库资源
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
	// rowDate用来存放数据    columnNames存放列名
	Vector rowDate, columnNames;

	// 构造函数_1(查看)
	public StuModel() 
	{
		// 设置列名
		columnNames = new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");

		// rowDate可以存放多行
		rowDate = new Vector();
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
		String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
		
		try{
		   //1.加载驱动
			Class.forName(driver);
		   //2.得到连接
			ct=DriverManager.getConnection(URL,"sa","j19950923");
		   //3.创建ps
			ps=ct.prepareStatement("select * from Stu");
			//4执行
			rs=ps.executeQuery();
			//4.返回结果集rs(执行) 
			while(rs.next())
			{
			   Vector hang=new Vector();
			   hang.add(rs.getInt(1));
			   hang.add(rs.getString(2));
			   hang.add(rs.getString(3));
			   hang.add(rs.getInt(4));
			   hang.add(rs.getString(5));
			   hang.add(rs.getString(6));
			   
			   //加入到rowData
			   rowDate .add(hang);
			}
			
		}catch(Exception e)		{
   			e.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}
	
	// 构造函数_2(查询)
		public StuModel(String sql) 
		{
			// 设置列名
			columnNames = new Vector();
			columnNames.add("学号");
			columnNames.add("姓名");
			columnNames.add("性别");
			columnNames.add("年龄");
			columnNames.add("籍贯");
			columnNames.add("系别");

			// rowDate可以存放多行
			rowDate = new Vector();
			
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
			
			try{
			   //1.加载驱动
				Class.forName(driver);
			   //2.得到连接
				ct=DriverManager.getConnection(URL,"sa","j19950923");
			   //3.创建ps
				ps=ct.prepareStatement(sql);
				//4执行
				rs=ps.executeQuery();
				//4.返回结果集rs(执行) 
				while(rs.next())
				{
				   Vector hang=new Vector();
				   hang.add(rs.getInt(1));
				   hang.add(rs.getString(2));
				   hang.add(rs.getString(3));
				   hang.add(rs.getInt(4));
				   hang.add(rs.getString(5));
				   hang.add(rs.getString(6));
				   
				   //加入到rowData
				   rowDate .add(hang);
				}
				
			}catch(Exception e)		{
	   			e.printStackTrace();
			}finally{
				try{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		}
	

	// 得到共有多少列
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowDate.size();
	}

	// 得到共多少行
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// 得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowDate.get(rowIndex)).get(columnIndex);
	}

}


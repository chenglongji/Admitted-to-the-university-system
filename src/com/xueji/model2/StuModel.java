package com.xueji.model2;

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
	
	  //驱动使用
	//	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	//	String URL = "jdbc:sqlserver://localhost:1400; DatabaseName=Study";
	//	String user="sa";
	//	String password="jcl19950923";
		
		//定义数据库资源
	//	PreparedStatement ps=null;
	//	Connection ct=null;
		ResultSet rs=null;
		
	// rowDate用来存放数据    columnNames存放列名
	Vector rowDate, columnNames;
	
	/*
	 * 无构造函数
	 */

	// 查看,查询
	public void StuModel(String sql,String[] date) 
	{
		// 设置列名
		columnNames = new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("专业");
		columnNames.add("院系");

		// rowDate可以存放多行
		rowDate = new Vector();
		SqlHelper sqlhelper=new SqlHelper();
		
		try{
		    rs=sqlhelper.quExectue(sql, date);          //查询
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
			sqlhelper.downdate();
		}

	}
		
	//添加,修改,删除
	public boolean UpStu(String sql,String[] date)
	{
        //创建一个SqlHelper,如果不考虑并发性,可以把SqlHelper做成static,或者単态
		SqlHelper sqluer=new SqlHelper();
		boolean b=sqluer.updExecute(sql, date);
		return b;
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


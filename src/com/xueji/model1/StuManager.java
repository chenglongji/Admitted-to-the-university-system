/*
 * mini版学籍系统
 */

package com.xueji.model1;

import javax.swing.*;

import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class StuManager extends JFrame implements ActionListener{

	/**
	 * 定义一些控件
	*/
	JPanel jp1,jp2,jp3;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	JTextField  jtf;
	JTable jt1=null;
	JScrollPane jsp=null;
	StuModel sm;
	String sql=null;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        StuManager test_1=new StuManager();
	}
	
	//构造函数
	public StuManager()
	{
		/*(上部)
		 
		 * 
		 * 
		 * *
		 */
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入名字");
		
	    //加入jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		/*（下部）
		 * */
	    jp2=new JPanel();
	    jb2=new JButton("添加");
	    jb2.addActionListener(this);
	    jb3=new JButton("修改");
	    jb3.addActionListener(this);
	    jb4=new JButton("删除");
	    jb4.addActionListener(this);
	    
	    //加入jp2
	    jp2.add(jb2);
	    jp2.add(jb3);
	    jp2.add(jb4);

	    //创建一个数据模型对象（JTable的一个子类）
	    sm=new StuModel();
	    
	    //初始化JTable
	    jt1=new JTable(sm);
	    
	    jsp=new JScrollPane(jt1);
	    
	    this.add(jp1,"North");
	    this.add(jsp);
	    this.add(jp2,"South");
	    this.setSize(500, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
			
	}

	//按钮的使用函数
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//判断哪个按钮
		if(e.getSource()==jb1)
		{
			//查询
			//查询数据库，更新JTable
			String name=this.jtf.getText().trim();
			if(name.length()!=0)  //
		    {
			   sql="select * from Stu where Stu_Name='"+name+"'";
			   sm=new StuModel(sql);
			   //更新数据模型
		       jt1.setModel(sm);
		    }else {
				sm=new StuModel();
				   //更新数据模型
			    jt1.setModel(sm);
			  
			}
		}else if(e.getSource()==jb2)
		{
			//添加
			StuAddDialog sad=new StuAddDialog(this,"添加学生",true);
			//更新模型
		    sm=new StuModel();
			jt1.setModel(sm);
		}else if(e.getSource()==jb3)
		{
			//修改
			int rowNum=this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			StuUpdateDilog upd=new StuUpdateDilog(this,"修改信息",true,sm,rowNum);
			sm=new StuModel();
			jt1.setModel(sm);	
			
		}else if(e.getSource()==jb4)
		{
			//删除
			//得到学生ID
			//getSelectRow()会返回用户点击的行，没有则返回-1
			int rowNum=this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			
			
			int Stu_id=(int) sm.getValueAt(rowNum, 0);
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
			
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			
		   try{
	        	  //1.加载驱动			
			     Class.forName(driver);		
		    	//2.得到连接
    			ct=DriverManager.getConnection(URL,"sa","j19950923");
	    		//3.ps
        		ps=ct.prepareStatement("delete from Stu where Stu_Id=?");
        		//4.赋值
        		ps.setInt(1, Stu_id);
	    		//5.执行
    			int i=ps.executeUpdate();
    	    }catch(Exception e_1){
	    			e_1.printStackTrace();
    		}finally{
	    			try{
		    		  if(ps!=null) ps.close();
     				  if(ct!=null) ct.close();
	    			}catch(Exception e_2){
		    			e_2.printStackTrace();
	    		 	}
    			}
		    sm=new StuModel();
		    jt1.setModel(sm);
    		}
		}

}

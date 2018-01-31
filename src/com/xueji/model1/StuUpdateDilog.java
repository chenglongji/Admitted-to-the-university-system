package com.xueji.model1;

/*
 * 修改学生
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StuUpdateDilog extends JDialog implements ActionListener{
	
	//定义控件
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	
	//构造函数
	//owner 父窗口
	//title 窗口名
	//model 什么模态
	public StuUpdateDilog(Frame owner,String title,boolean modal,StuModel sm,int rowNum)
	{
		super(owner,title,modal);   //调用父类构造方法，达到模式对话框效果
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("籍贯");
		jl6=new JLabel("派系");
		
		//初始化数据
		jtf1=new JTextField();
		jtf1.setText(sm.getValueAt(rowNum, 0).toString());
		//jtf1不能改
		jtf1.setEditable(false);
		jtf2=new JTextField();
		jtf2.setText((String)sm.getValueAt(rowNum, 1));
		jtf3=new JTextField();
		jtf3.setText(sm.getValueAt(rowNum, 2).toString());
		jtf4=new JTextField();
		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
		jtf5=new JTextField();
		jtf5.setText((String)sm.getValueAt(rowNum, 4));
		jtf6=new JTextField();
		jtf6.setText((String)sm.getValueAt(rowNum, 5));
		
		jb1=new JButton("修改");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
	
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp1.setLayout(new GridLayout (6,1));
		jp2.setLayout(new GridLayout (6,1));
		
		
	
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		
		//展示
		this.setSize(300,300);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jb1)
		{
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
			String sql="update Stu1 set Stu_Name=?,Stu_Sex=?,Stu_Age=?,Stu_Hone=?,Stu_Style=? where Stu_Id=?";
			
			//定义数据库资源
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			
			try{
				//1.加载驱动
				Class.forName(driver);
				//得到连接
				ct=DriverManager.getConnection(URL,"sa","j19950923");
				//ps
				ps=ct.prepareStatement(sql);
				//给ps赋值
				ps.setString(1, jtf2.getText().trim());
				ps.setString(2, jtf3.getText().trim());
				ps.setString(3, jtf4.getText().trim());
				ps.setString(4, jtf5.getText().trim());
				ps.setString(5, jtf6.getText().trim());
				ps.setString(6, jtf1.getText().trim());
				//执行
				ps.executeUpdate();
				this.dispose();
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
				
			}else if(e.getSource()==jb2)
				 this.dispose();
	}
		
		
}
	
	



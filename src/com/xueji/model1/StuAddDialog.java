package com.xueji.model1;

/*
 * ���ѧ��
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StuAddDialog extends JDialog implements ActionListener{
	
	//����ؼ�
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	String sql=null;
	
	
	//���캯��
	//owner ������
	//title ������
	//model ʲôģ̬
	public StuAddDialog(Frame owner,String title,boolean modal)
	{
		super(owner,title,modal);   //���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("����");
		jl6=new JLabel("��ϵ");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		
		jb1=new JButton("���");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
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
		
		
		//չʾ
		this.setSize(300,300);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jb1)
		{
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
			String sql="insert into Stu values(?,?,?,?,?,?)";
			
			//�������ݿ���Դ
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			
			try{
				//1.��������
				Class.forName(driver);
				//�õ�����
				ct=DriverManager.getConnection(URL,"sa","j19950923");
				//ps
				ps=ct.prepareStatement(sql);
				//��ps��ֵ
				ps.setString(1,jtf1.getText().trim());
				ps.setString(2, jtf2.getText().trim());
				ps.setString(3, jtf3.getText().trim());
				ps.setString(4, jtf4.getText().trim());
				ps.setString(5, jtf5.getText().trim());
				ps.setString(6, jtf6.getText().trim());
				//ִ��
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
			this.dispose();		
			}else if(e.getSource()==jb2)
				 this.dispose();
		
	}
	
	
}


/*
 * mini��ѧ��ϵͳ
 */

package com.xueji.model1;

import javax.swing.*;

import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class StuManager extends JFrame implements ActionListener{

	/**
	 * ����һЩ�ؼ�
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
	
	//���캯��
	public StuManager()
	{
		/*(�ϲ�)
		 
		 * 
		 * 
		 * *
		 */
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("����������");
		
	    //����jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		/*���²���
		 * */
	    jp2=new JPanel();
	    jb2=new JButton("���");
	    jb2.addActionListener(this);
	    jb3=new JButton("�޸�");
	    jb3.addActionListener(this);
	    jb4=new JButton("ɾ��");
	    jb4.addActionListener(this);
	    
	    //����jp2
	    jp2.add(jb2);
	    jp2.add(jb3);
	    jp2.add(jb4);

	    //����һ������ģ�Ͷ���JTable��һ�����ࣩ
	    sm=new StuModel();
	    
	    //��ʼ��JTable
	    jt1=new JTable(sm);
	    
	    jsp=new JScrollPane(jt1);
	    
	    this.add(jp1,"North");
	    this.add(jsp);
	    this.add(jp2,"South");
	    this.setSize(500, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
			
	}

	//��ť��ʹ�ú���
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�ж��ĸ���ť
		if(e.getSource()==jb1)
		{
			//��ѯ
			//��ѯ���ݿ⣬����JTable
			String name=this.jtf.getText().trim();
			if(name.length()!=0)  //
		    {
			   sql="select * from Stu where Stu_Name='"+name+"'";
			   sm=new StuModel(sql);
			   //��������ģ��
		       jt1.setModel(sm);
		    }else {
				sm=new StuModel();
				   //��������ģ��
			    jt1.setModel(sm);
			  
			}
		}else if(e.getSource()==jb2)
		{
			//���
			StuAddDialog sad=new StuAddDialog(this,"���ѧ��",true);
			//����ģ��
		    sm=new StuModel();
			jt1.setModel(sm);
		}else if(e.getSource()==jb3)
		{
			//�޸�
			int rowNum=this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			StuUpdateDilog upd=new StuUpdateDilog(this,"�޸���Ϣ",true,sm,rowNum);
			sm=new StuModel();
			jt1.setModel(sm);	
			
		}else if(e.getSource()==jb4)
		{
			//ɾ��
			//�õ�ѧ��ID
			//getSelectRow()�᷵���û�������У�û���򷵻�-1
			int rowNum=this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			
			
			int Stu_id=(int) sm.getValueAt(rowNum, 0);
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
			
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			
		   try{
	        	  //1.��������			
			     Class.forName(driver);		
		    	//2.�õ�����
    			ct=DriverManager.getConnection(URL,"sa","j19950923");
	    		//3.ps
        		ps=ct.prepareStatement("delete from Stu where Stu_Id=?");
        		//4.��ֵ
        		ps.setInt(1, Stu_id);
	    		//5.ִ��
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

/*
 * mini��ѧ��ϵͳ
 */

package com.xueji.model2;

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

	    //����һ������ģ�Ͷ���JTable��һ������)
	    sm=new StuModel();
	    String sql="select * from Stu1 where 1=?";
	    String[] date={"1"};
	    sm.StuModel(sql,date);
	    
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
			String name=this.jtf.getText().trim();
			if(name.length()!=0)  //
		    {
			   sm=new StuModel(); 
			   String sql="select * from Stu1 where Stu_name like '%'+?+'%' ";
			   String[] date={name};
			   sm.StuModel(sql,date);   //�����ݿ������Table 
			   
			   //��������ģ��
		       jt1.setModel(sm);
		    }else {
		    	sm=new StuModel(); 
		    	String sql="select * from Stu1 where 1=?";
			    String[] date={"1"};
				sm.StuModel(sql,date);
				   //��������ģ��
			    jt1.setModel(sm);
			  
			}
		}else if(e.getSource()==jb2)
		{
			//���
			StuAddDialog sad=new StuAddDialog(this,"���ѧ��",true);
			//����ģ��
			 sm=new StuModel(); 
			String sql="select * from Stu1 where 1=?";
		    String[] date={"1"};
			sm.StuModel(sql,date);
			   //��������ģ��
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
			String sql="select * from Stu1 where 1=?";
		    String[] date={"1"};
			sm.StuModel(sql,date);
			   //��������ģ��
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
						
			String sql="delete from Stu1 where Stu_id=?";
			String[] date={sm.getValueAt(rowNum, 0).toString()};
			sm.UpStu(sql, date);
			//����ģ��
			sm=new StuModel(); 
			String sql1="select * from Stu1 where 1=?";
		    String[] date1={"1"};
			sm.StuModel(sql1,date1);
			   //��������ģ��
		    jt1.setModel(sm);
    		}
		}

}

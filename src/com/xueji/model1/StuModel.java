package com.xueji.model1;

import java.sql.*;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel; //JTable������

/*
 * �����ҵ�һ��stu���ģ��
 */
public class StuModel extends AbstractTableModel {

	
	   // ��д��ʾ�����ķ���
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

	//�������ݿ���Դ
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
	// rowDate�����������    columnNames�������
	Vector rowDate, columnNames;

	// ���캯��_1(�鿴)
	public StuModel() 
	{
		// ��������
		columnNames = new Vector();
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");

		// rowDate���Դ�Ŷ���
		rowDate = new Vector();
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
		String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
		
		try{
		   //1.��������
			Class.forName(driver);
		   //2.�õ�����
			ct=DriverManager.getConnection(URL,"sa","j19950923");
		   //3.����ps
			ps=ct.prepareStatement("select * from Stu");
			//4ִ��
			rs=ps.executeQuery();
			//4.���ؽ����rs(ִ��) 
			while(rs.next())
			{
			   Vector hang=new Vector();
			   hang.add(rs.getInt(1));
			   hang.add(rs.getString(2));
			   hang.add(rs.getString(3));
			   hang.add(rs.getInt(4));
			   hang.add(rs.getString(5));
			   hang.add(rs.getString(6));
			   
			   //���뵽rowData
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
	
	// ���캯��_2(��ѯ)
		public StuModel(String sql) 
		{
			// ��������
			columnNames = new Vector();
			columnNames.add("ѧ��");
			columnNames.add("����");
			columnNames.add("�Ա�");
			columnNames.add("����");
			columnNames.add("����");
			columnNames.add("ϵ��");

			// rowDate���Դ�Ŷ���
			rowDate = new Vector();
			
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			String URL = "jdbc:sqlserver://127.0.0.1\\SQLserver2005; DatabaseName=study";	
			
			try{
			   //1.��������
				Class.forName(driver);
			   //2.�õ�����
				ct=DriverManager.getConnection(URL,"sa","j19950923");
			   //3.����ps
				ps=ct.prepareStatement(sql);
				//4ִ��
				rs=ps.executeQuery();
				//4.���ؽ����rs(ִ��) 
				while(rs.next())
				{
				   Vector hang=new Vector();
				   hang.add(rs.getInt(1));
				   hang.add(rs.getString(2));
				   hang.add(rs.getString(3));
				   hang.add(rs.getInt(4));
				   hang.add(rs.getString(5));
				   hang.add(rs.getString(6));
				   
				   //���뵽rowData
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
	

	// �õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowDate.size();
	}

	// �õ���������
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// �õ�ĳ��ĳ�е�����
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowDate.get(rowIndex)).get(columnIndex);
	}

}


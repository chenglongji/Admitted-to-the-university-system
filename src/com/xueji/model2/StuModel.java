package com.xueji.model2;

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
	
	  //����ʹ��
	//	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	//	String URL = "jdbc:sqlserver://localhost:1400; DatabaseName=Study";
	//	String user="sa";
	//	String password="jcl19950923";
		
		//�������ݿ���Դ
	//	PreparedStatement ps=null;
	//	Connection ct=null;
		ResultSet rs=null;
		
	// rowDate�����������    columnNames�������
	Vector rowDate, columnNames;
	
	/*
	 * �޹��캯��
	 */

	// �鿴,��ѯ
	public void StuModel(String sql,String[] date) 
	{
		// ��������
		columnNames = new Vector();
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("רҵ");
		columnNames.add("Ժϵ");

		// rowDate���Դ�Ŷ���
		rowDate = new Vector();
		SqlHelper sqlhelper=new SqlHelper();
		
		try{
		    rs=sqlhelper.quExectue(sql, date);          //��ѯ
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
			sqlhelper.downdate();
		}

	}
		
	//���,�޸�,ɾ��
	public boolean UpStu(String sql,String[] date)
	{
        //����һ��SqlHelper,��������ǲ�����,���԰�SqlHelper����static,���߅g̬
		SqlHelper sqluer=new SqlHelper();
		boolean b=sqluer.updExecute(sql, date);
		return b;
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


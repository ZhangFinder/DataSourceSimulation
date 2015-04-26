package com.zjq.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/***
 * ���ݿ����ӹ���
 * @author ZJQ
 * **/
public class JDBConnection {
	private final String dbDriver = "com.mysql.jdbc.Driver"; // ����SQL
	String url = "jdbc:mysql://localhost:3306/sshexample?user=root&password="
		+ "zjq1048826985&characterEncoding=utf-8";

	private ResultSet rs = null;
	private Statement stmt = null;
	private Connection con = null;
	// ͨ�����췽���������ݿ�����
	public JDBConnection(){
		try{
			Class.forName(dbDriver).newInstance();//ͨ��Java������ƽ��м������ݿ���������
		}catch(Exception e){
			System.out.println("���ݿ����ʧ��");
		}
	} 
	//�������ݿ�����
	public boolean createConnection(){
		try{
			con=DriverManager.getConnection(url);//�����û�����url��ַ�Լ���������������Ӳ���
			con.setAutoCommit(true);
			return true;
		}catch(SQLException e){
			//System.out.println(e.getMessage());
			System.out.println("createConnectionError");
			return false;
		}
	}
	//�����ݿ�����ӡ��޸ĺ�ɾ���Ĳ���
	public boolean executeUpdate(String sql){
		if(con==null){//��con����Ϊ�գ�����creatConnection����,ʵ�����ݿ����Ӳ���
			createConnection();
		}
		try{
			 stmt=con.createStatement();
		    int iCount =stmt.executeUpdate(sql);//ִ�в���SQL���
		//    System.out.println("�����ɹ�����Ӱ��ļ�¼��Ϊ��"+String.valueOf(iCount));//�ڿ���̨����ʾִ�еĽ��
		    return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//�����ݿ�Ĳ�ѯ�Ĳ���
	public ResultSet executeQuery(String sql){
		try{
		   if(con ==null){ //��con����Ϊnull,ȡ�����ݿ����Ӳ���
			   createConnection();
		   }	
		    stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		   try{
			   rs =stmt.executeQuery(sql);//ִ�в����е�SQL���
			   
		   }catch(SQLException e){
			  // System.out.println(e.getMessage());
			   return null;
		   }
		}catch(SQLException e){
		//	System.out.println(e.getMessage());
			//System.out.println("executeQueryError");
			return null;
		}
		return rs;//����ѯ�Ľ��ͨ���ؼ���return ���в�ѯ
	}
	//�ر����ݿ�Ĳ���
	public void closeConnection(){
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con !=null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			//	System.out.println("Falied to close connection ");
			}finally{
				con=null;
			}
		}
	}
}

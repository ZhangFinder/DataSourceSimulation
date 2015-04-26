package com.zjq.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/***
 * 数据库连接工具
 * @author ZJQ
 * **/
public class JDBConnection {
	private final String dbDriver = "com.mysql.jdbc.Driver"; // 设置SQL
	String url = "jdbc:mysql://localhost:3306/sshexample?user=root&password="
		+ "zjq1048826985&characterEncoding=utf-8";

	private ResultSet rs = null;
	private Statement stmt = null;
	private Connection con = null;
	// 通过构造方法加载数据库驱动
	public JDBConnection(){
		try{
			Class.forName(dbDriver).newInstance();//通过Java反射机制进行加载数据库驱动操作
		}catch(Exception e){
			System.out.println("数据库加载失败");
		}
	} 
	//创建数据库连接
	public boolean createConnection(){
		try{
			con=DriverManager.getConnection(url);//根据用户名，url地址以及密码进行数据连接操作
			con.setAutoCommit(true);
			return true;
		}catch(SQLException e){
			//System.out.println(e.getMessage());
			System.out.println("createConnectionError");
			return false;
		}
	}
	//对数据库的增加、修改和删除的操作
	public boolean executeUpdate(String sql){
		if(con==null){//当con对象为空，进行creatConnection方法,实现数据库连接操作
			createConnection();
		}
		try{
			 stmt=con.createStatement();
		    int iCount =stmt.executeUpdate(sql);//执行参数SQL语句
		//    System.out.println("操作成功，所影响的记录数为："+String.valueOf(iCount));//在控制台中显示执行的结果
		    return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//对数据库的查询的操作
	public ResultSet executeQuery(String sql){
		try{
		   if(con ==null){ //当con对象为null,取得数据库连接操作
			   createConnection();
		   }	
		    stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		   try{
			   rs =stmt.executeQuery(sql);//执行参数中的SQL语句
			   
		   }catch(SQLException e){
			  // System.out.println(e.getMessage());
			   return null;
		   }
		}catch(SQLException e){
		//	System.out.println(e.getMessage());
			//System.out.println("executeQueryError");
			return null;
		}
		return rs;//将查询的结果通过关键字return 进行查询
	}
	//关闭数据库的操作
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

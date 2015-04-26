package com.zjq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.zjq.model.SenseData;
import com.zjq.tools.JDBConnection;

public class SenseDataDao {
	private static SenseDataDao instance = null;
	private JDBConnection connection = null; // 定义数据库连接类JDBConnection 对象并赋值为null

	private SenseDataDao() {
		connection = new JDBConnection();
	}

	/**
	 * 返回一个SenseDataDao实例,单例模式
	 * 
	 * @return
	 */
	public static SenseDataDao getInstance() {
		if (instance == null) {
			instance = new SenseDataDao();
		}
		return instance;
	}

	/**
	 * 保存传感器信息
	 * @param name
	 * @return 如果用户信息保存成功，则返回true,否则返回false
	 */
	public boolean saveSenseData(SenseData senseData){
		String sql;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		
		sql="insert into sense_data (`id`,`addressId`,`voltage`,`current`,`temp`,`dampness`) values ('"+senseData.getId()+"','"+senseData.getAddressId()+"','"+senseData.getVoltage()+"','"+senseData.getCurrent()+"','"+senseData.getTemp()+"','"+senseData.getDampness()+"') on duplicate key update addressId='"+senseData.getAddressId()+"' ,voltage='"+senseData.getVoltage()+"' ,"+
          "current='"+senseData.getCurrent()+"',temp='"+senseData.getTemp()+"' ,dampness='"+senseData.getDampness()+"'";
		// System.out.println(sql);
		boolean flag=connection.executeUpdate(sql);
		connection.closeConnection();
		return flag;
	}
 
}

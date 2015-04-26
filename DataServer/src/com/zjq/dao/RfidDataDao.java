package com.zjq.dao;

import com.zjq.model.RfidData;
import com.zjq.model.SenseData;
import com.zjq.tools.JDBConnection;

public class RfidDataDao {
	private static RfidDataDao instance = null;
	private JDBConnection connection = null; // 定义数据库连接类JDBConnection 对象并赋值为null

	private RfidDataDao() {
		connection = new JDBConnection();
	}

	/**
	 * 返回一个RfidDataDao实例,单例模式
	 * 
	 * @return
	 */
	public static RfidDataDao getInstance() {
		if (instance == null) {
			instance = new RfidDataDao();
		}
		return instance;
	}

	/**
	 * 保存RFID信息
	 * @param name
	 * @return 如果用户信息保存成功，则返回true,否则返回false
	 */
	public boolean saveRfidData(RfidData rfidData){
		String sql;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		
		sql="insert into rfid_data (`id`,`addressId`,`productId`,`stateId`) values ('"+rfidData.getId()+"','"+rfidData.getAddressId()+"','"+rfidData.getProductId()+"','"+rfidData.getStateId()+"') on duplicate key update addressId='"+rfidData.getAddressId()+"' ,productId='"+rfidData.getProductId()+"' ,"+
          "stateId='"+rfidData.getStateId()+"'";
		// System.out.println(sql);
		boolean flag=connection.executeUpdate(sql);
		connection.closeConnection();
		return flag;
	}
}

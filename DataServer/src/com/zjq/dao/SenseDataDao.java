package com.zjq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.zjq.model.SenseData;
import com.zjq.tools.JDBConnection;

public class SenseDataDao {
	private static SenseDataDao instance = null;
	private JDBConnection connection = null; // �������ݿ�������JDBConnection ���󲢸�ֵΪnull

	private SenseDataDao() {
		connection = new JDBConnection();
	}

	/**
	 * ����һ��SenseDataDaoʵ��,����ģʽ
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
	 * ���洫������Ϣ
	 * @param name
	 * @return ����û���Ϣ����ɹ����򷵻�true,���򷵻�false
	 */
	public boolean saveSenseData(SenseData senseData){
		String sql;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		
		sql="insert into sense_data (`id`,`addressId`,`voltage`,`current`,`temp`,`dampness`) values ('"+senseData.getId()+"','"+senseData.getAddressId()+"','"+senseData.getVoltage()+"','"+senseData.getCurrent()+"','"+senseData.getTemp()+"','"+senseData.getDampness()+"') on duplicate key update addressId='"+senseData.getAddressId()+"' ,voltage='"+senseData.getVoltage()+"' ,"+
          "current='"+senseData.getCurrent()+"',temp='"+senseData.getTemp()+"' ,dampness='"+senseData.getDampness()+"'";
		// System.out.println(sql);
		boolean flag=connection.executeUpdate(sql);
		connection.closeConnection();
		return flag;
	}
 
}

package com.zjq.socket;


import com.zjq.dao.SenseDataDao;
import com.zjq.model.SenseData;

public class Main {
   public static void main(String args[]){
	   SenseData senseData=new SenseData();
	   senseData.setId(1);
	   senseData.setAddressId(1);
	   senseData.setVoltage(220.20f);
	   senseData.setCurrent(1.27f);
	   senseData.setTemp(27.32f);
	   senseData.setDampness(23.34f);
		boolean res = SenseDataDao.getInstance().saveSenseData(senseData);
		if(res){
			System.out.println("插入数据成功");
		}else {
			System.out.println("插入数据失败");
		}
   }
}

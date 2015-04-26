package com.zjq.tools;

import java.util.Random;

public class RandomData {
	/***
	    * ���������ѹֵ����ѹ����219-221֮��,����2λС��
	    * ****/
	   public static String getVoltage(Random random){
		   float data;
		   data=random.nextFloat();
		   data *=(random.nextBoolean()? 1: -1);
		   data +=220;
		   data=(float)(Math.round(data*100))/100;//����2λС��
		   return String.valueOf(data);
	   }
	   /***
	    * �����������ֵ������ֵ��0-2֮��,����2λС��
	    * **/
	   public static String getCurrent(Random random){
		   float data;
		   data=random.nextFloat();
		   data *=(random.nextBoolean()? 1: -1);
		   data +=1;
		   data=(float)(Math.round(data*100))/100;//����2λС��
		   return String.valueOf(data);  
	   }
	   /****
	    * ��������¶�ֵ,�¶�ֵ��24-32֮��,����2λС��
	    * ***/
	   public static String getTemp(Random random){
		   float data;
		   data=random.nextFloat()*4;
		   data *=(random.nextBoolean()? 1: -1);
		   data +=28;
		   data=(float)(Math.round(data*100))/100;//����2λС��
		   return String.valueOf(data);  
	   }
	   /**
	    * �������ʪ��ֵ��ʪ��ֵ��0.25-0.65֮�䣬�м�ֵΪ0.45������2λС��
	    * **/
	   public static String getDampness(Random random){
		   float data;
		   data=(float) (0.25+random.nextInt(40)*1.0f/100);
		   data=(float)(Math.round(data*100))/100;//����2λС��
		   return String.valueOf(data); 
	   }
	   public static String getSenseDataByAddrId(int addrId,Random random){
		   return addrId+"-"+getVoltage(random)+"-"+getCurrent(random)+"-"+getTemp(random)+"-"+getDampness(random);
	   }
	   
}

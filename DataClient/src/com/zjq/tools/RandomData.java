package com.zjq.tools;

import java.util.Random;

public class RandomData {
	/***
	    * 随机产生电压值，电压在在219-221之间,保留2位小数
	    * ****/
	   public static String getVoltage(Random random){
		   float data;
		   data=random.nextFloat();
		   data *=(random.nextBoolean()? 1: -1);
		   data +=220;
		   data=(float)(Math.round(data*100))/100;//保留2位小数
		   return String.valueOf(data);
	   }
	   /***
	    * 随机产生电流值，电流值在0-2之间,保留2位小数
	    * **/
	   public static String getCurrent(Random random){
		   float data;
		   data=random.nextFloat();
		   data *=(random.nextBoolean()? 1: -1);
		   data +=1;
		   data=(float)(Math.round(data*100))/100;//保留2位小数
		   return String.valueOf(data);  
	   }
	   /****
	    * 随机产生温度值,温度值在24-32之间,保留2位小数
	    * ***/
	   public static String getTemp(Random random){
		   float data;
		   data=random.nextFloat()*4;
		   data *=(random.nextBoolean()? 1: -1);
		   data +=28;
		   data=(float)(Math.round(data*100))/100;//保留2位小数
		   return String.valueOf(data);  
	   }
	   /**
	    * 随机产生湿度值，湿度值在0.25-0.65之间，中间值为0.45，保留2位小数
	    * **/
	   public static String getDampness(Random random){
		   float data;
		   data=(float) (0.25+random.nextInt(40)*1.0f/100);
		   data=(float)(Math.round(data*100))/100;//保留2位小数
		   return String.valueOf(data); 
	   }
	   public static String getSenseDataByAddrId(int addrId,Random random){
		   return addrId+"-"+getVoltage(random)+"-"+getCurrent(random)+"-"+getTemp(random)+"-"+getDampness(random);
	   }
	   
}

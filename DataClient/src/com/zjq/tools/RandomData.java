package com.zjq.tools;

import java.util.Random;

public class RandomData {
	  private static int productId[];
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
	   /**
	    * 根据监测点Id获取该监测点所有传感器数据
	    * ******/
	   public static String getSenseDataByAddrId(int addrId,Random random){
		   return addrId+"-"+getVoltage(random)+"-"+getCurrent(random)+"-"+getTemp(random)+"-"+getDampness(random);
	   }
	   /***
	    *随机产生5个不同的产品编号id ,范围为0-20;
	    * **/
	   public static int[] getRandomProductId(Random random){
		   int product[] = new int[5];
		   product[0]=random.nextInt(20);
		   boolean flag;
		   for(int i=1;i<5;i++){
			   do{
				   flag =false;
				   product[i]=random.nextInt(20);
				   for(int j=0;j<i;j++){
					   if(product[i]==product[j]){
						   flag=true;
						   break;
					   }
				   }
			   }while(flag);
		   }
		   return product;
	   }
	   /***
	    * 随机产生加工程度id,id为0-6
	    * *****/
	   public static int getStateId(Random random){
		   return random.nextInt(6);
	   }
	   /****
	    * 获取产品编号，范围为1-20
	    * *****/
	   public static int getProductId(int addrId,Random random){
		   if(addrId==0){
			  
			   RandomData.productId=getRandomProductId(random);
		   }
		   return RandomData.productId[addrId];
	   }
	   /**
	    * 根据监测点Id获取该监测点的RFID数据
	    *
	    * ******/
	   public static String getRfidDataByAddrId(int addrId,Random random){
		   return addrId+"-"+getProductId(addrId,random)+"-"+getStateId(random);
	   }
}

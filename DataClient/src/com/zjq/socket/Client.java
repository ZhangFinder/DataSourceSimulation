package com.zjq.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

import com.zjq.tools.RandomData;

public class Client {
   public static void main(String args[]){
	   Socket mysocket=null;
	 
	   BufferedWriter out=null;
	   try{
		   mysocket=new Socket("127.0.0.1",4331);
		   
		   out = new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream(),"UTF-8"));
		 
		   int i=0;
		   while( i<30){
			   
			   if(i%2==0){//偶数次发传感器数据
			    out.write(getSenseData());
			   }else{//奇数次发RFID数据
			    out.write(getRfidData());
			   }
			   out.flush();
			   i++;
			   Thread.sleep(500);
		   }
	   }catch(Exception e){
		   System.out.println("服务器已经断开！"+e);
	   }finally{
		   try {
			 out.close();
			 mysocket.close();
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		   
	   }
   }
   /****
    * 得到7个检测点的数据，数据格式为:
    *  id-监测点Id-电压值-电流值-温度值-湿度值#
    *  eg:1-1-220.52-1.99-24.24-0.51#2-2-220.52-1.99-24.24-0.51
    * 
    * ****/
   public static String getSenseData(){
	   Random random=new Random();
	   StringBuffer senseData=new StringBuffer("SenseData:");
	   for(int i=1;i<7;i++){
		   senseData.append(i+"-"+RandomData.getSenseDataByAddrId(i, random)+"#");
	   }
	   senseData.append("7"+"-"+RandomData.getSenseDataByAddrId(7, random)+"\r\n");
	   return senseData.toString();
   }
   /*******
    * 得到5个RFID监测点的数据，数据格式为
    * id-监测点id-产品编号Id-加工程度id
    * ******/
   public static String getRfidData(){
	   Random random=new Random();
	   StringBuffer rfidData=new StringBuffer("RfidData:");
	   for(int i=0;i<4;i++){
		   rfidData.append(i+"-"+RandomData.getRfidDataByAddrId(i, random)+"#");
	   }
	   rfidData.append("4"+"-"+RandomData.getRfidDataByAddrId(4, random)+"\r\n");
	   return rfidData.toString();
   }
}


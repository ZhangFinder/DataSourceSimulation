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
			   i++;
			   out.write(getSenseData());
			   out.flush();
			   Thread.sleep(500);//ÿ500ms��������һ��
			 
			   
		   }
	   }catch(Exception e){
		   System.out.println("�������Ѿ��Ͽ���"+e);
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
    * �õ�7����������ݣ����ݸ�ʽΪ:
    *  id-����Id-��ѹֵ-����ֵ-�¶�ֵ-ʪ��ֵ#
    *  eg:1-1-220.52-1.99-24.24-0.51#2-2-220.52-1.99-24.24-0.51
    * 
    * ****/
   public static String getSenseData(){
	   Random random=new Random();
	   StringBuffer senseData=new StringBuffer();
	   for(int i=1;i<7;i++){
		   senseData.append(i+"-"+RandomData.getSenseDataByAddrId(i, random)+"#");
	   }
	   senseData.append("7"+"-"+RandomData.getSenseDataByAddrId(7, random)+"\r\n");
	   return senseData.toString();
   }
}


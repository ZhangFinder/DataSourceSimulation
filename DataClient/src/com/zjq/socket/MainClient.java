package com.zjq.socket;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainClient {
   public static void main(String args[]){
	   Socket mysocket=null;
	   DataInputStream in=null;
	   BufferedWriter out=null;
	   try{
		   mysocket=new Socket("127.0.0.1",4331);
		   in=new DataInputStream(mysocket.getInputStream());
		   out = new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream(),"UTF-8"));
		   char c='a';
		   char s;
		   while(true){
			   if(c>'z')
				   break;
			   out.write("200-300-400"+"\r\n");
			   out.flush();
//			   s=in.readChar();
			   System.out.println("客户端发送："+c);
			   c++;
			   Thread.sleep(100);
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
}



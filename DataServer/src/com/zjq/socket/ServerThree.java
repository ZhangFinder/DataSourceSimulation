package com.zjq.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThree {
  public static void main(String args[]){
	  ServerSocket server=null;
	  Socket client=null;
	  
	 
	  try{
		  server=new ServerSocket(4331);
	  }catch(IOException ex){
		  System.out.println(ex);
	  }
	  try{
		  System.out.println("等待客户呼叫");
		  client=server.accept();//堵塞状态,除非有客户呼叫
		  System.out.println("连接到一个客户");
		  String line;
		  BufferedReader is=new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
		  PrintWriter os=new PrintWriter(client.getOutputStream());
//		  os.println("aa");
//		  os.flush();
		  while(true){
			 // char c=in.readChar();//in读取消息，阻塞状态
			  System.out.println("********");
			  String str=is.readLine();
			  System.out.println("服务器收到:"+str);
			  Thread.sleep(100);
			  
		  }
		 
		  
	  }catch(Exception e){
		  System.out.println("客户端已经断开"+e);
	  }
  }
}

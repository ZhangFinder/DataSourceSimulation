package com.zjq.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {
  public static void main(String args[]){
	  ServerSocket server=null;
	  Socket client=null;
	  DataOutputStream out=null;
	  DataInputStream in=null;
	  try{
		  server=new ServerSocket(4331);
	  }catch(IOException ex){
		  System.out.println(ex);
	  }
	  try{
		  System.out.println("等待客户呼叫");
		  client=server.accept();//堵塞状态,除非有客户呼叫
		  System.out.println("连接到一个客户");
		  out=new DataOutputStream(client.getOutputStream());
		  in=new DataInputStream(client.getInputStream());
		  while(true){
			  char c=in.readChar();//in读取消息，阻塞状态
			  System.out.println("服务器收到:"+c);
//			  out.write((char)(c-32));
//			  out.flush();
			  Thread.sleep(500);
		  }
	  }catch(Exception e){
		  System.out.println("客户端已经断开"+e);
	  }
  }
}

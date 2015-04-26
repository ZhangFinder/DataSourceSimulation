package com.zjq.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {
	 public static void main(String args[]) {
		   ServerSocket server=null;
		   boolean flag=true;
		   int clientCount=0;
		   try{
			   server=new ServerSocket(4331);
		   }catch(IOException e1){
			   System.out.println(e1);
		   }
		   System.out.println("服务器端正在运行");
		   Socket client=null;
		   while(flag){
			   
			   try {
				 client=server.accept();//堵塞状态，除非有客户呼叫
			   }catch (IOException e) {
				 e.printStackTrace();
			  }
			   if(client!=null){
				   System.out.println("当前共已连接"+(++clientCount)+"个客户端");
				   new Thread(new MainServerThread(client)).start();
			   }
		   }
		   System.out.println("服务器端正在运行");
		   
		   try {
			   client.close();
			  server.close();
			
		  } catch (IOException e) {
			e.printStackTrace();
		  }
	   }
}
class MainServerThread implements Runnable{
	private Socket client;
	private BufferedReader bufferReader;
	
	public MainServerThread(Socket socket){
		this.client=socket;
		try{
			bufferReader=new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void run() {
	     boolean runFlag=true;//运行标记
	     String str=null;
	     while(runFlag){
	    	    try {
					str=bufferReader.readLine();//一直处于赌塞状态，除非有"\n"
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		System.out.println(str);
	    		
	    		 if(str==null){//程序结束
	    			// out.println("byebye");
	    			 runFlag=false;//退出循环
	    			 System.out.println(str);
	    		 }
	    	 }
	     
	     try{
	    	 client.close();
	     }catch(IOException e){
	    	 e.printStackTrace();
	     }
		
	}
	
}

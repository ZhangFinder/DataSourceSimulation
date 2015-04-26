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
		   System.out.println("����������������");
		   Socket client=null;
		   while(flag){
			   
			   try {
				 client=server.accept();//����״̬�������пͻ�����
			   }catch (IOException e) {
				 e.printStackTrace();
			  }
			   if(client!=null){
				   System.out.println("��ǰ��������"+(++clientCount)+"���ͻ���");
				   new Thread(new MainServerThread(client)).start();
			   }
		   }
		   System.out.println("����������������");
		   
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
	     boolean runFlag=true;//���б��
	     String str=null;
	     while(runFlag){
	    	    try {
					str=bufferReader.readLine();//һֱ���ڶ���״̬��������"\n"
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		System.out.println(str);
	    		
	    		 if(str==null){//�������
	    			// out.println("byebye");
	    			 runFlag=false;//�˳�ѭ��
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

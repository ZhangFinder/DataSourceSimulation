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
		  System.out.println("�ȴ��ͻ�����");
		  client=server.accept();//����״̬,�����пͻ�����
		  System.out.println("���ӵ�һ���ͻ�");
		  String line;
		  BufferedReader is=new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
		  PrintWriter os=new PrintWriter(client.getOutputStream());
//		  os.println("aa");
//		  os.flush();
		  while(true){
			 // char c=in.readChar();//in��ȡ��Ϣ������״̬
			  System.out.println("********");
			  String str=is.readLine();
			  System.out.println("�������յ�:"+str);
			  Thread.sleep(100);
			  
		  }
		 
		  
	  }catch(Exception e){
		  System.out.println("�ͻ����Ѿ��Ͽ�"+e);
	  }
  }
}

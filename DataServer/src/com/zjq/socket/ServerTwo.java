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
		  System.out.println("�ȴ��ͻ�����");
		  client=server.accept();//����״̬,�����пͻ�����
		  System.out.println("���ӵ�һ���ͻ�");
		  out=new DataOutputStream(client.getOutputStream());
		  in=new DataInputStream(client.getInputStream());
		  while(true){
			  char c=in.readChar();//in��ȡ��Ϣ������״̬
			  System.out.println("�������յ�:"+c);
//			  out.write((char)(c-32));
//			  out.flush();
			  Thread.sleep(500);
		  }
	  }catch(Exception e){
		  System.out.println("�ͻ����Ѿ��Ͽ�"+e);
	  }
  }
}

package com.zjq.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.zjq.dao.RfidDataDao;
import com.zjq.dao.SenseDataDao;
import com.zjq.model.RfidData;
import com.zjq.model.SenseData;

public class Server {
	public static void main(String args[]) {
		ServerSocket server = null;
		boolean flag = true;
		int clientCount = 0;
		try {
			server = new ServerSocket(4331);
		} catch (IOException e1) {
			System.out.println(e1);
		}
		System.out.println("服务器端正在运行");
		Socket client = null;
		while (flag) {

			try {
				client = server.accept();// 堵塞状态，除非有客户呼叫
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (client != null) {
				System.out.println("当前共已连接" + (++clientCount) + "个客户端");
				new Thread(new ServerThread(client)).start();
			}
		}
		System.out.println("服务器端端断开");

		try {
			client.close();
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ServerThread implements Runnable {
	private Socket client;
	private BufferedReader bufferReader;

	public ServerThread(Socket socket) {
		this.client = socket;
		try {
			bufferReader = new BufferedReader(new InputStreamReader(client
					.getInputStream(), "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		boolean runFlag = true;// 运行标记
		String str = null;
		while (runFlag) {
			try {
				str = bufferReader.readLine();// 一直处于赌塞状态，除非有"\n"
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(str);

			if (str == null) {// 程序结束
				// out.println("byebye");
				runFlag = false;// 退出循环
				System.out.println("传输数据停止");
			} else {
				boolean res=false;
				if(str.startsWith("SenseData:")){
					str=str.substring("SenseData:".length());
					res=saveSenseDataToDatabase(str);
				}else if(str.startsWith("RfidData:")){
					str=str.substring("RfidData:".length());
					//System.out.println(str);
					res=saveRfidDataToDatabase(str);
				}
               if(!res)
               {
            	  System.out.println("插入数据到数据库时错误");   
               }
			}
		}

		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
   /***
    * 将7个监测点传感器数据存入数据库
    * ***/
	public boolean saveSenseDataToDatabase(String str) {
		String data[] = str.split("#");// 得到7个监测点数据,每个监测点数据格式为：1-1-219.36-0.75-24.3-0.41
		SenseData senseData = new SenseData();
		String detail[];
		SenseDataDao dao=SenseDataDao.getInstance();
		boolean res=false;
		for (int i = 0; i < data.length; i++) {
			detail = data[i].split("-");// 得到：id, 监测点id,电压，电流，温度，湿度，检测时间
			senseData.setId(Integer.parseInt(detail[0]));//id
			senseData.setAddressId(Integer.parseInt(detail[1]));//监测点id
			senseData.setVoltage(Float.parseFloat(detail[2]));//电压值
			senseData.setCurrent(Float.parseFloat(detail[3]));  //电流值
			senseData.setTemp(Float.parseFloat(detail[4]));  //温度值
			senseData.setDampness(Float.parseFloat(detail[5]));//湿度值
		    res = dao.saveSenseData(senseData);
			if (!res) {
				break;
			} 
		}

		return res;
	}
  public boolean saveRfidDataToDatabase(String str){
	  String data[] = str.split("#");
	  RfidData rfidData=new RfidData();
	  String detail[];
	  RfidDataDao rfidDataDao =RfidDataDao.getInstance();
	  boolean res=false;
	  for(int i=0;i<data.length;i++){
		  detail=data[i].split("-");
		  rfidData.setId(Integer.parseInt(detail[0]));
		  rfidData.setAddressId(Integer.parseInt(detail[1]));
		  rfidData.setProductId(Integer.parseInt(detail[2]));
		  rfidData.setStateId(Integer.parseInt(detail[3]));
		  res=rfidDataDao.saveRfidData(rfidData);
		  if(!res){
			  break;
		  }
			  
		  
	  }
	  return res;
  }
}

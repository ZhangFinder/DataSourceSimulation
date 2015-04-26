package com.zjq.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.zjq.dao.SenseDataDao;
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
		System.out.println("����������������");
		Socket client = null;
		while (flag) {

			try {
				client = server.accept();// ����״̬�������пͻ�����
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (client != null) {
				System.out.println("��ǰ��������" + (++clientCount) + "���ͻ���");
				new Thread(new ServerThread(client)).start();
			}
		}
		System.out.println("�������˶˶Ͽ�");

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
		boolean runFlag = true;// ���б��
		String str = null;
		while (runFlag) {
			try {
				str = bufferReader.readLine();// һֱ���ڶ���״̬��������"\n"
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(str);

			if (str == null) {// �������
				// out.println("byebye");
				runFlag = false;// �˳�ѭ��
				System.out.println("��������ֹͣ");
			} else {
				if(saveDataToDatabase(str)){
					System.out.println("�������ݳɹ�!");
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
    * ��7�����㴫�������ݴ������ݿ�
    * ***/
	public boolean saveDataToDatabase(String str) {
		String data[] = str.split("#");// �õ�7����������,ÿ���������ݸ�ʽΪ��1-1-219.36-0.75-24.3-0.41
		SenseData senseData = new SenseData();
		String detail[];
		SenseDataDao dao=SenseDataDao.getInstance();
		boolean res=false;
		for (int i = 0; i < data.length; i++) {
			detail = data[i].split("-");// �õ���id, ����id,��ѹ���������¶ȣ�ʪ�ȣ����ʱ��
			senseData.setId(Integer.parseInt(detail[0]));//id
			senseData.setAddressId(Integer.parseInt(detail[1]));//����id
			senseData.setVoltage(Float.parseFloat(detail[2]));//��ѹֵ
			senseData.setCurrent(Float.parseFloat(detail[3]));  //����ֵ
			senseData.setTemp(Float.parseFloat(detail[4]));  //�¶�ֵ
			senseData.setDampness(Float.parseFloat(detail[5]));//ʪ��ֵ
		    res = dao.saveSenseData(senseData);
			if (!res) {
				break;
			} 
		}

		return res;
	}

}

package com.zjq.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerOne {
	public static void main(String args[]) {
		ServerSocket server = null;
		Socket client = null;
		boolean flag = true;
		InputStream in = null;
		PrintStream out = null;
		try {
			server = new ServerSocket(4331);
			System.out.println("服务器正在运行...");
			client = server.accept();// 接收客户端请求
			in = client.getInputStream();
			out = new PrintStream(client.getOutputStream());
		} catch (IOException e) {

			e.printStackTrace();
		}
		while (flag) {
			byte[] b = new byte[1024];
			try {
				in.read(b);
				in.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

			System.out.println("读取到的字符串为：" + new String(b));

		}
		System.out.println("服务器停止运行");
		try {
			client.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

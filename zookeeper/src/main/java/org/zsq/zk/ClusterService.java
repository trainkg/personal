package org.zsq.zk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.zsq.zk.services.PrintServices;
import org.apache.zookeeper.ZooDefs.Ids;

public class ClusterService {

	public static String[] args;
	public static ZooKeeper zk;

	public static void main(String[] args) {
		ClusterService.args = args;
		publicService();
		startService();
	}

	/**
	 * 启动服务
	 */
	private static void startService() {
		String port = args[0];
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(Integer.valueOf(port));
			while (true) {
				try {
					final Socket socket = ss.accept(); // 监听连接队列
					System.out.println("start connecting, local port is " + socket.getLocalPort());
					Thread workThread = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								System.out.println("start reciver message...");
								BufferedReader reader = new BufferedReader(
										new InputStreamReader(socket.getInputStream()));
								String line = "";
								while ((line = reader.readLine()) != null) {
									ClusterService.handerCommand(line, socket.getLocalPort());
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}); // 创建线程
					workThread.start(); // 启动线程
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ss != null) {
					ss.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected static void handerCommand(String line, int port) {
		System.out.println("reciver command : " + line);
		if (line.equals("print")) {
			System.out.println("execute at server localhost and port " + port);
			new PrintServices().print();
		} else {
			System.out.println("invalid command.");
		}
	}

	/**
	 * 注册服务
	 */
	private static void publicService() {
		createSession();
		createEphemeralNode();
	}

	private static void createEphemeralNode() {
		String create;
		try {
			create = zk.create("/servers/session", args[0].getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(create);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void createSession() {
		String hostPort = "192.168.182.132:2181,192.168.182.132:2182,192.168.182.132:2183";
		try {
			zk = new ZooKeeper(hostPort, 3000, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

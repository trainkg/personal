package org.zsq.zk;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * 查找服务
 * 
 * @author peculiar.1@163.com
 * @version $ID: ClientApplication.java, V1.0.0 2020年12月14日 下午2:22:15 $
 */
public class ClientApplication {

	public static ZooKeeper zk;
	private static List<String> services = new ArrayList<String>();

	public static void main(String[] args) {
		loadingServices();
		// 执行20次
		for (int i = 0; i < 25; i++) {
			System.out.println("request no " + i);
			createConnection();
		}
	}

	private static void loadingServices() {
		String hostPort = "192.168.182.132:2181,192.168.182.132:2182,192.168.182.132:2183";
		try {
			zk = new ZooKeeper(hostPort, 3000, null);
			List<String> servers = zk.getChildren("/servers", false);
			for (String string : servers) {
				System.out.println(string);
				byte[] byteas = zk.getData("/servers/" + string, false, null);
				services.add(new String(byteas));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建连接
	 */
	private static void createConnection() {
		int index = chooseRandom();
		System.out.println(services.get(index));
		try {
			Socket s = new Socket("127.0.0.1", Integer.valueOf(services.get(index)));
//			while (s.isConnected()) {
//				Scanner scanner = new Scanner(System.in);
//				while (scanner.hasNext()) {
//					String line = scanner.next();
//					System.out.println("recive :" + line);
//					if (!s.isOutputShutdown()) {
//						s.getOutputStream().write(line.getBytes());
//						s.getOutputStream().write("\r\n".getBytes());// 终止标志
//						s.getOutputStream().flush();
//						// s.shutdownOutput();
//					} else {
//						System.out.println("socket outputstream has closed.");
//					}
//				}
//				scanner.close();
//			}
			s.getOutputStream().write("print".getBytes());
			s.getOutputStream().write("\r\n".getBytes());// 终止标志
			s.getOutputStream().flush();
			s.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	private static int chooseRandom() {
		System.out.println("current services " + services);
		Random random = new Random();
		return random.nextInt(services.size());
	}
}

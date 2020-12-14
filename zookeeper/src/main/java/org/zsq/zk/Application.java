package org.zsq.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * main  
 * 
 * @author peculiar.1@163.com
 * @version $ID: Application.java, V1.0.0 2020年12月13日 下午3:18:54 $
 */
public class Application {
	
	public static ZooKeeper zk;
	
	public static void main(String[] args) {
		createSession();
		createEphemeralNode();
		closeSession();
	}

	private static void closeSession() {
		try {
			Thread.sleep(15 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createEphemeralNode() {
		String create;
		try {
			create = zk.create("/servers/session", "192.168.182.132:2181".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(create);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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

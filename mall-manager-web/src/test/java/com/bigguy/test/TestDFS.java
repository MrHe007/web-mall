package com.bigguy.test;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class TestDFS {
	
	public static void main(String[] args) throws Exception {
		
		// 加载配置文件的地址，配置文件里就是 tracker 的地址
		
		String confPath = "E:\\workspace\\eclipse\\mall-manager-web\\src\\main\\resources\\conf\\client.conf";
		ClientGlobal.init(confPath);
		
		// 创建一个 TrackerClient 对象
		TrackerClient trackerClient = new TrackerClient();
		
		// 通过一个 TrackerClient 得到一个TrackerServer 对象
		TrackerServer trackerServer = trackerClient.getConnection();
		
		StorageServer storageServer = null;
		
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		
		// 上传到服务器
		
		String filePath = "F:\\mmimg\\极品御女小野猫紧致肌肤\\10.jpg";
		String[] fileInfo = storageClient.upload_file(filePath,"jpg", null);
		
		
		for (String string : fileInfo) {
			System.out.println(string);
		}
		
	}
	
}

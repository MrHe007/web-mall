package com.bigguy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bigguy.utils.FastDFSClient;
import com.bigguy.utils.JsonUtils;

@Controller
public class PictureController {
	
	@Value("${IAMGE_SERVER}") 		// 从类路径下的 properties 中加载配置 
	private String IMAGE_SERVER;
	
	@ResponseBody
	@RequestMapping(value="/pic/upload",produces= {MediaType.TEXT_PLAIN_VALUE+";charset=utf-8"})
	public String fileUpload(MultipartFile uploadFile) {
		
		// 上传到图片服务器中
		String confPath = "classpath:conf/client.conf";
		try {
			FastDFSClient fastDFSClient = new FastDFSClient(confPath);
			
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			// 得到扩展名
			String imgUrl = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			
			
			imgUrl = IMAGE_SERVER+imgUrl; 		// 得到全路径 
			
			System.out.println(imgUrl);
			
			Map map =new HashMap<>();
			map.put("error", 0);
			map.put("url",imgUrl );
			
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			
			Map map =new HashMap<>();
			map.put("error", 1);
			map.put("message","图片上传失败!" );
			
			return JsonUtils.objectToJson(map);
		}
	}
	
}

package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;

@RestController
public class FileUploadController {
	// 从配置文件appplication.properties文件中获得指定的值，并赋给相应的成员变量
	@Value("${web.user-path}")
	private String userPath;

	@Value("${web.goods-path}")
	private String goodsPath;

	@RequestMapping(value = "/userUpload")
	public ResultValue userUplaod(@RequestParam("userPhoto") MultipartFile file) {

		ResultValue rv = new ResultValue();
		try {
			Map<String, Object> map = this.uploadFile(userPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像上传失败！");
		return rv;
	}

	@RequestMapping(value = "/goodsUpload")
	public ResultValue goodsUpload(@RequestParam("goodsImage") MultipartFile file) {
		System.out.println(userPath);
		System.out.println(goodsPath);
		ResultValue rv = new ResultValue();
		try {
			Map<String, Object> map = this.uploadFile(goodsPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品图片上传失败！");
		return rv;
	}

	@RequestMapping(value = "/deleteGoodsImg")
	public ResultValue deleteGoodsImage(@RequestParam("goodsImg") String goodsImg) {
		ResultValue resultValue = new ResultValue();
		try {
			// 从url中获得商品图片的名字
			int indexOf = goodsImg.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = goodsImg.substring(indexOf + 1);
				File file = new File(this.goodsPath + fileName);
				//判断文件是否存在，若存在则删除
				if(file.exists()) {
					if(file.isFile()) {
						file.delete();
						resultValue.setCode(0);
						return resultValue;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		return resultValue;
	}
	
	@RequestMapping(value = "/deleteUserImg")
	public ResultValue deleteUserImage(@RequestParam("userPhoto") String userPhoto) {
		ResultValue resultValue = new ResultValue();
		try {
			// 从url中获得商品图片的名字
			int indexOf = userPhoto.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = userPhoto.substring(indexOf + 1);
				File file = new File(this.userPath + fileName);
				//判断文件是否存在，若存在则删除
				if(file.exists()) {
					if(file.isFile()) {
						file.delete();
						resultValue.setCode(0);
						return resultValue;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		return resultValue;
	}

	/**
	 * 上传文件
	 * 
	 * @param path 文件路径
	 * @param file 上传的文件
	 * @return 成功返回java.util.Map<String, Object>类型实例
	 * @throws IOException
	 */
	private Map<String, Object> uploadFile(String path, MultipartFile file) throws IOException {
		// 获得新的文件名
		String fileName = UploadUtils.getFileName();
		// 获得文件的扩展名
		String extendedName = UploadUtils.getExendedName(file.getOriginalFilename());
		// 将文件转换为字节类型的数组
		byte[] bytes = file.getBytes();
		// 创建file类对象 并设置文件上传路径
		File saveFile = new File(path + fileName + extendedName);
		// 上传文件(判断文件不为空)
		if (extendedName != null) {
			FileCopyUtils.copy(bytes, saveFile);
		}
		// 当文件上传成功时 将文件的文件名和文件扩展名传递给视图层
		Map<String, Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendedName", extendedName);
		return map;
	}

}

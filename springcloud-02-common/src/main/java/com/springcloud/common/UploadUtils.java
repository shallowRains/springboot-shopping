package com.springcloud.common;

import java.util.Date;

/**
 * 文件上传的工具类
 * @author RLDS
 *
 */
public class UploadUtils {

	/**
	 * 获得新的文件名称
	 * 
	 * @return 新的文件名称
	 */
	public static String getFileName() {
		String fileName = null;
		//获得1-1000之间的随机数
		int num = (int)(Math.random() * 1000 + 1);
		//将随机数设置为四位
		String tempNum = String.format("%04d", num);
		Date date = new Date();
		fileName = date.getTime() + tempNum;
		return fileName;
	}

	public static String getExendedName(String fileName) {
		
		if(fileName == null || fileName.length() == 0) {
			return null;
		}
		//获得文件中点.最后一次出现的位置
		int index = fileName.lastIndexOf(".");
		if(index == -1) {
			return null;
		}
		return fileName.substring(index);
	}

//	public static void main(String[] args) {
//		
//		for(int i = 0;i<=10;i++) {
//			System.out.println(getFileName());
//		}
//		System.out.println(getExendedName("asdfhosad.asdfh.img.png"));
//	}
}

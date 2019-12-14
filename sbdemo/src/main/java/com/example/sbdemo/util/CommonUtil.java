/*
 * Title: Tools.java
 * Company: EMI
 * Author: xiaochen zhu
 * Date: May 18, 2014 8:27:36 PM
 * Version: 4.0
 */
package com.example.sbdemo.util;
import java.net.URISyntaxException;

/**
 * 常用工具�?
 * 
 * @version 1.0
 * @since 2014-5-14
 */
public class CommonUtil {
	/**
	 * 获取根目�?
	 * 
	 * @return 项目的根路径
	 */
	public static String getRootPath() {
		String result = null;
		try {
			result = CommonUtil.class.getResource("CommonUtil.class").toURI()
					.getPath().toString();
//			System.out.println("fun-rootpath:"+result);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		int index = result.indexOf("WEB-INF");
		if (index == -1) {
			index = result.indexOf("bin");
		}
		if(WindowOrLinux()){//windows系统要截取第�?���?�?
			result = result.substring(1, index);
		}else{
			result = result.substring(0, index);
		}
		if (result.endsWith("/"))
			result = result.substring(0, result.length() - 1);// 不包含最后的"/"
		return result;
	}
	
	/**
	 * 判断当前系统是WINDOWS还是LINUX
	 * 
	 * @param
	 * @return true windows false linux
	 */
	public static boolean WindowOrLinux() {
		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			return true;
		}
		return false;
	}
	/**
	 * 判断输入的对象是否为null，如果是null则返回"",否则，返回 对象.toString()。
	 * 
	 * @param str
	 * @return
	 */
	public static String Obj2String(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	public static boolean isNullObject(Object object) {
		if (object == null || "".equals(object.toString())
				|| "null".equals(object.toString())) {
			return true;
		}
		return false;
	}
	public static boolean notNullObject(Object object) {
		return !isNullObject(object);
	}
}
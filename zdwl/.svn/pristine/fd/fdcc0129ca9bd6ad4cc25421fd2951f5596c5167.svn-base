package com.zd.tools;

import java.security.MessageDigest;

/**
 * 加解密、验证工具类
 */
public class DigestUtil {


	/**
	 * 将String转换成32位的加密字符
	 * @param String inStr 需要加密的字符
	 * @return String  加密后的字符编码
	 * */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();//转换成字节数组
		byte[] byteArray = new byte[charArray.length];//新创建一个字节数组

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}
	public static void main(String[] args) {
		System.out.println(DigestUtil.MD5("brdtec0.8548692593834738"));
	}
}

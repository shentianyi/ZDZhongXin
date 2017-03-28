package com.zd.tools;

import java.util.Random;

/**
 * Date工具?
 * 提供方法处两个日期差?
 * */
public class RandomString {
	
	public String randomString(int length)
	 {
	  String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	  Random  random = new Random();
	  StringBuffer buf = new StringBuffer();
	  
	  for(int i = 0 ;i < length ; i ++)
	  {
	   int num = random.nextInt(36);
	   buf.append(str.charAt(num));
	  }
	  
	  return buf.toString();
	 }

}

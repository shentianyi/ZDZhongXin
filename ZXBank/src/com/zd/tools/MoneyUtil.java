package com.zd.tools;

/**
 * money工具?
 * 提供方法处理金额风格?
 * */
public class MoneyUtil {
	
	/**
	 * 三个数一个??
	 * @param  money三位数一个??
	 * @return String
	 * */
	public static String format(String money){
		
		if(money == null || "".equals(money)){
			 return "0";
		}
		String headmoney=money;
		String backmoney="";
		
		int index1 = money.lastIndexOf("-");//寻找负号?”的索引位置，若不是负数，则?1
		
		if(index1 > -1){
			money=money.substring(index1+1);
		}
		
		int index = money.lastIndexOf(".");//寻找小数点的索引位置，若不是小数，则?1
		if(index > -1) {
			
			headmoney=money.substring(0, index);
			backmoney=money.substring(index);
		}
		
		
		//获取小数点前面的数据
		headmoney = new StringBuilder(headmoney).reverse().toString();     //先将字符串颠倒顺?
		String str2 = "";
		for(int i=0;i<headmoney.length();i++){
			if(i*3+3>headmoney.length()){
				str2 += headmoney.substring(i*3, headmoney.length());
				break;
			}
			str2 += headmoney.substring(i*3, i*3+3)+",";
		}
		if(str2.endsWith(",")){
			str2 = str2.substring(0, str2.length()-1);
		}
		//?再将顺序反转过来
		if(index1 >-1){
			return "-"+new StringBuilder(str2).reverse().toString()+backmoney;
		}
		return new StringBuilder(str2).reverse().toString()+backmoney;
	}
	
	
//	public static void main(String[] args) {
//		String a="-99.34";
//		System.out.println(new MoneyUtil().format(a));
//		
//	}

}

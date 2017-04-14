package com.zd.tools.time;

import java.util.Date;
import java.sql.Timestamp;

/**
 * 时间工具接口
 * 用于获取不同格式的系统当前时?
 * */
public interface ITimeTools {

	/**
	 * 获取当前时间
	 * @return Timestamp
	 * */
	public Timestamp getCurrentTimestamp();

	/**
	 * 获取当前时间
	 * @return Date
	 * */
    public Date getCurrentDate();

	/**
	 * 获取当前时间
	 * @return long
	 * */
	public long getTime();

}

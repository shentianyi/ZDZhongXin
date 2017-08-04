package com.zd.csms.salary.model;

import com.zd.core.annotation.table;

/**
 * 基本工资常量表
 *
 */
@table(name = "t_base_pay")
public class BasePay {
	/**
	 * 主键
	 */
	private int id=0;
	/**
	 * 转正特殊
	 */
	private double zhuanzheng_teshu = 2200;

	/**
	 * 转正一类
	 */
	private double zhuanzheng_yilei = 1700;

	/**
	 * 转正二类
	 */
	private double zhuanzheng_erlei = 1500;

	/**
	 * 试用特殊
	 */
	private double shiyong_teshu = 1760;

	/**
	 * 试用一类
	 */
	private double shiyong_yilei = 1360;

	/**
	 * 试用二类
	 */
	private double shiyong_erlei = 1200;

	
	/**
	 * 房补:特殊
	 */
	private double fangbu_teshu_waipai = 800;

	/**
	 * 房补:一类
	 */
	private double fangbu_yilei_waipai = 600;

	/**
	 * 房补：二类
	 */
	private double fangbu_erlei_waipai = 400;
	
	/**
	 * 房补:特殊_属地
	 */
	private double fangbu_teshu_shudi;

	/**
	 * 房补:一类_属地
	 */
	private double fangbu_yilei_shudi;

	/**
	 * 房补：二类_属地
	 */
	private double fangbu_erlei_shudi;
	
	

	/**
	 * 交通补：特殊_属地
	 */
	private double jiaotong_teshu_shudi;
	
	/**
	 * 交通补：一类_属地
	 */
	private double jiaotong_yilei_shudi;
	
	/**
	 * 交通补：二类_属地
	 */
	private double jiaotong_erlei_shudi;
	
	/**
	 * 交通补：特殊_外派
	 */
	private double jiaotong_teshu_waipai;
	
	/**
	 * 交通补：一类_外派
	 */
	private double jiaotong_yilei_waipai;
	
	/**
	 * 交通补：二类_外派
	 */
	private double jiaotong_erlei_waipai;
	
	
	/**
	 * 话补：特殊_属地
	 */
	private double huabu_teshu_shudi;
	
	/**
	 * 话补：一类_属地
	 */
	private double huabu_yilei_shudi;
	
	/**
	 * 话补：二类_属地
	 */
	private double huabu_erlei_shudi;
	
	/**
	 * 话补：特殊_外派
	 */
	private double huabu_teshu_waipai;
	
	/**
	 * 话补：一类_外派
	 */
	private double huabu_yilei_waipai;
	
	/**
	 * 话补：二类_外派
	 */
	private double huabu_erlei_waipai;
	
	
	/**
	 * 饭补：特殊_属地
	 */
	private double fanbu_teshu_shudi;
	
	/**
	 * 饭补：一类_属地
	 */
	private double fanbu_yilei_shudi;
	
	/**
	 * 饭补：二类_属地
	 */
	private double fanbu_erlei_shudi;
	
	/**
	 * 饭补：特殊_外派
	 */
	private double fanbu_teshu_waipai;
	
	/**
	 * 饭补：一类_外派
	 */
	private double fanbu_yilei_waipai;
	
	/**
	 * 饭补：二类_外派
	 */
	private double fanbu_erlei_waipai;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getZhuanzheng_teshu() {
		return zhuanzheng_teshu;
	}

	public void setZhuanzheng_teshu(double zhuanzheng_teshu) {
		this.zhuanzheng_teshu = zhuanzheng_teshu;
	}

	public double getZhuanzheng_yilei() {
		return zhuanzheng_yilei;
	}

	public void setZhuanzheng_yilei(double zhuanzheng_yilei) {
		this.zhuanzheng_yilei = zhuanzheng_yilei;
	}

	public double getZhuanzheng_erlei() {
		return zhuanzheng_erlei;
	}

	public void setZhuanzheng_erlei(double zhuanzheng_erlei) {
		this.zhuanzheng_erlei = zhuanzheng_erlei;
	}

	public double getShiyong_teshu() {
		return shiyong_teshu;
	}

	public void setShiyong_teshu(double shiyong_teshu) {
		this.shiyong_teshu = shiyong_teshu;
	}

	public double getShiyong_yilei() {
		return shiyong_yilei;
	}

	public void setShiyong_yilei(double shiyong_yilei) {
		this.shiyong_yilei = shiyong_yilei;
	}

	public double getShiyong_erlei() {
		return shiyong_erlei;
	}

	public void setShiyong_erlei(double shiyong_erlei) {
		this.shiyong_erlei = shiyong_erlei;
	}

	public double getFangbu_teshu_waipai() {
		return fangbu_teshu_waipai;
	}

	public void setFangbu_teshu_waipai(double fangbu_teshu_waipai) {
		this.fangbu_teshu_waipai = fangbu_teshu_waipai;
	}

	public double getFangbu_yilei_waipai() {
		return fangbu_yilei_waipai;
	}

	public void setFangbu_yilei_waipai(double fangbu_yilei_waipai) {
		this.fangbu_yilei_waipai = fangbu_yilei_waipai;
	}

	public double getFangbu_erlei_waipai() {
		return fangbu_erlei_waipai;
	}

	public void setFangbu_erlei_waipai(double fangbu_erlei_waipai) {
		this.fangbu_erlei_waipai = fangbu_erlei_waipai;
	}

	public double getFangbu_teshu_shudi() {
		return fangbu_teshu_shudi;
	}

	public void setFangbu_teshu_shudi(double fangbu_teshu_shudi) {
		this.fangbu_teshu_shudi = fangbu_teshu_shudi;
	}

	public double getFangbu_yilei_shudi() {
		return fangbu_yilei_shudi;
	}

	public void setFangbu_yilei_shudi(double fangbu_yilei_shudi) {
		this.fangbu_yilei_shudi = fangbu_yilei_shudi;
	}

	public double getFangbu_erlei_shudi() {
		return fangbu_erlei_shudi;
	}

	public void setFangbu_erlei_shudi(double fangbu_erlei_shudi) {
		this.fangbu_erlei_shudi = fangbu_erlei_shudi;
	}

	public double getJiaotong_teshu_shudi() {
		return jiaotong_teshu_shudi;
	}

	public void setJiaotong_teshu_shudi(double jiaotong_teshu_shudi) {
		this.jiaotong_teshu_shudi = jiaotong_teshu_shudi;
	}

	public double getJiaotong_yilei_shudi() {
		return jiaotong_yilei_shudi;
	}

	public void setJiaotong_yilei_shudi(double jiaotong_yilei_shudi) {
		this.jiaotong_yilei_shudi = jiaotong_yilei_shudi;
	}

	public double getJiaotong_erlei_shudi() {
		return jiaotong_erlei_shudi;
	}

	public void setJiaotong_erlei_shudi(double jiaotong_erlei_shudi) {
		this.jiaotong_erlei_shudi = jiaotong_erlei_shudi;
	}

	public double getJiaotong_teshu_waipai() {
		return jiaotong_teshu_waipai;
	}

	public void setJiaotong_teshu_waipai(double jiaotong_teshu_waipai) {
		this.jiaotong_teshu_waipai = jiaotong_teshu_waipai;
	}

	public double getJiaotong_yilei_waipai() {
		return jiaotong_yilei_waipai;
	}

	public void setJiaotong_yilei_waipai(double jiaotong_yilei_waipai) {
		this.jiaotong_yilei_waipai = jiaotong_yilei_waipai;
	}

	public double getJiaotong_erlei_waipai() {
		return jiaotong_erlei_waipai;
	}

	public void setJiaotong_erlei_waipai(double jiaotong_erlei_waipai) {
		this.jiaotong_erlei_waipai = jiaotong_erlei_waipai;
	}

	public double getHuabu_teshu_shudi() {
		return huabu_teshu_shudi;
	}

	public void setHuabu_teshu_shudi(double huabu_teshu_shudi) {
		this.huabu_teshu_shudi = huabu_teshu_shudi;
	}

	public double getHuabu_yilei_shudi() {
		return huabu_yilei_shudi;
	}

	public void setHuabu_yilei_shudi(double huabu_yilei_shudi) {
		this.huabu_yilei_shudi = huabu_yilei_shudi;
	}

	public double getHuabu_erlei_shudi() {
		return huabu_erlei_shudi;
	}

	public void setHuabu_erlei_shudi(double huabu_erlei_shudi) {
		this.huabu_erlei_shudi = huabu_erlei_shudi;
	}

	public double getHuabu_teshu_waipai() {
		return huabu_teshu_waipai;
	}

	public void setHuabu_teshu_waipai(double huabu_teshu_waipai) {
		this.huabu_teshu_waipai = huabu_teshu_waipai;
	}

	public double getHuabu_yilei_waipai() {
		return huabu_yilei_waipai;
	}

	public void setHuabu_yilei_waipai(double huabu_yilei_waipai) {
		this.huabu_yilei_waipai = huabu_yilei_waipai;
	}

	public double getHuabu_erlei_waipai() {
		return huabu_erlei_waipai;
	}

	public void setHuabu_erlei_waipai(double huabu_erlei_waipai) {
		this.huabu_erlei_waipai = huabu_erlei_waipai;
	}

	public double getFanbu_teshu_shudi() {
		return fanbu_teshu_shudi;
	}

	public void setFanbu_teshu_shudi(double fanbu_teshu_shudi) {
		this.fanbu_teshu_shudi = fanbu_teshu_shudi;
	}

	public double getFanbu_yilei_shudi() {
		return fanbu_yilei_shudi;
	}

	public void setFanbu_yilei_shudi(double fanbu_yilei_shudi) {
		this.fanbu_yilei_shudi = fanbu_yilei_shudi;
	}

	public double getFanbu_erlei_shudi() {
		return fanbu_erlei_shudi;
	}

	public void setFanbu_erlei_shudi(double fanbu_erlei_shudi) {
		this.fanbu_erlei_shudi = fanbu_erlei_shudi;
	}

	public double getFanbu_teshu_waipai() {
		return fanbu_teshu_waipai;
	}

	public void setFanbu_teshu_waipai(double fanbu_teshu_waipai) {
		this.fanbu_teshu_waipai = fanbu_teshu_waipai;
	}

	public double getFanbu_yilei_waipai() {
		return fanbu_yilei_waipai;
	}

	public void setFanbu_yilei_waipai(double fanbu_yilei_waipai) {
		this.fanbu_yilei_waipai = fanbu_yilei_waipai;
	}

	public double getFanbu_erlei_waipai() {
		return fanbu_erlei_waipai;
	}

	public void setFanbu_erlei_waipai(double fanbu_erlei_waipai) {
		this.fanbu_erlei_waipai = fanbu_erlei_waipai;
	}
	
	
	
}

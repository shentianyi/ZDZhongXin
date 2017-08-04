package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="T_CHECKSTOCK_MANAGE")
public class CheckStockManageVO implements Serializable{

	private static final long serialVersionUID = 6042099173454896019L;
	/**
	 * 主键ID
	 */
	private int id;
	/**
	 * 经销商ID
	 */
	private int dealerid;
	/**
	 * 在库车辆
	 */
	private int all_wh_count;
	/**
	 * 在库本库
	 */
	private int wh_count;
	/**
	 * 在库二库
	 */
	private int two_wh_count;
	/**
	 * 在库移动
	 */
	private int move_count;
	/**
	 * 实盘在库
	 */
	private int actual_all_wh_count;
	/**
	 * 实盘本库
	 */
	private int actual_wh_count;
	/**
	 * 实盘二库
	 */
	private int actual_two_wh_count;
	/**
	 * 实盘移动
	 */
	private int actual_move_count;
	/**
	 * 实盘出库
	 */
	private int actual_out_count;

	/**
	 * 实盘异常车辆
	 */
	private int actual_abnormal_count;
	/**
	 * 私自移动
	 */
	private int secretly_move_count;
	/**
	 * 私自销售
	 */
	private int secretly_sale_count;
	/**
	 * 在途销售
	 */
	private int way_sale_count;
	/**
	 * 在途移动
	 */
	private int way_move_count;
	/**
	 * 信誉额度
	 */
	private int credit_line_count;
	/**
	 * 展厅
	 */
	private int exhibition_room_count;
	/**
	 * 盘点结果与实际库存差异 1:一致  2：差异
	 */
	private int result;
	/**
	 * 盘点时间
	 */
	private Date check_date;
	/**
	 * 常规差异
	 */
	private String normal_difference;
	/**
	 * 非常规差异
	 */
	private String un_normal_difference;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 创建人
	 */
	private int createuser;
	/**
	 * 修改时间
	 */
	private Date updatedate;
	/**
	 * 修改人
	 */
	private int updateuser;
	/**
	 * 提交标识 1:未提交 2:已提交
	 */
	private int submitflag;
	/**
	 * 查库方式1：手工查库，2：设备查库
	 */
	
	
	private int checkStockType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerid() {
		return dealerid;
	}
	public void setDealerid(int dealerid) {
		this.dealerid = dealerid;
	}
	public int getAll_wh_count() {
		return all_wh_count;
	}
	public void setAll_wh_count(int all_wh_count) {
		this.all_wh_count = all_wh_count;
	}
	public int getWh_count() {
		return wh_count;
	}
	public void setWh_count(int wh_count) {
		this.wh_count = wh_count;
	}
	public int getTwo_wh_count() {
		return two_wh_count;
	}
	public void setTwo_wh_count(int two_wh_count) {
		this.two_wh_count = two_wh_count;
	}
	public int getMove_count() {
		return move_count;
	}
	public void setMove_count(int move_count) {
		this.move_count = move_count;
	}
	public int getActual_all_wh_count() {
		return actual_all_wh_count;
	}
	public void setActual_all_wh_count(int actual_all_wh_count) {
		this.actual_all_wh_count = actual_all_wh_count;
	}
	public int getActual_wh_count() {
		return actual_wh_count;
	}
	public void setActual_wh_count(int actual_wh_count) {
		this.actual_wh_count = actual_wh_count;
	}
	public int getActual_two_wh_count() {
		return actual_two_wh_count;
	}
	public void setActual_two_wh_count(int actual_two_wh_count) {
		this.actual_two_wh_count = actual_two_wh_count;
	}
	public int getActual_move_count() {
		return actual_move_count;
	}
	public void setActual_move_count(int actual_move_count) {
		this.actual_move_count = actual_move_count;
	}
	public int getActual_out_count() {
		return actual_out_count;
	}
	public void setActual_out_count(int actual_out_count) {
		this.actual_out_count = actual_out_count;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getNormal_difference() {
		return normal_difference;
	}
	public void setNormal_difference(String normal_difference) {
		this.normal_difference = normal_difference;
	}
	public String getUn_normal_difference() {
		return un_normal_difference;
	}
	public void setUn_normal_difference(String un_normal_difference) {
		this.un_normal_difference = un_normal_difference;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getCreateuser() {
		return createuser;
	}
	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(int updateuser) {
		this.updateuser = updateuser;
	}
	public int getSubmitflag() {
		return submitflag;
	}
	public void setSubmitflag(int submitflag) {
		this.submitflag = submitflag;
	}
	public int getCheckStockType() {
		return checkStockType;
	}
	public void setCheckStockType(int checkStockType) {
		this.checkStockType = checkStockType;
	}
	public int getActual_abnormal_count() {
		return actual_abnormal_count;
	}
	public void setActual_abnormal_count(int actual_abnormal_count) {
		this.actual_abnormal_count = actual_abnormal_count;
	}
	public int getSecretly_move_count() {
		return secretly_move_count;
	}
	public void setSecretly_move_count(int secretly_move_count) {
		this.secretly_move_count = secretly_move_count;
	}
	public int getSecretly_sale_count() {
		return secretly_sale_count;
	}
	public void setSecretly_sale_count(int secretly_sale_count) {
		this.secretly_sale_count = secretly_sale_count;
	}
	public int getWay_sale_count() {
		return way_sale_count;
	}
	public void setWay_sale_count(int way_sale_count) {
		this.way_sale_count = way_sale_count;
	}
	public int getWay_move_count() {
		return way_move_count;
	}
	public void setWay_move_count(int way_move_count) {
		this.way_move_count = way_move_count;
	}
	public int getCredit_line_count() {
		return credit_line_count;
	}
	public void setCredit_line_count(int credit_line_count) {
		this.credit_line_count = credit_line_count;
	}
	public int getExhibition_room_count() {
		return exhibition_room_count;
	}
	public void setExhibition_room_count(int exhibition_room_count) {
		this.exhibition_room_count = exhibition_room_count;
	}
	
}

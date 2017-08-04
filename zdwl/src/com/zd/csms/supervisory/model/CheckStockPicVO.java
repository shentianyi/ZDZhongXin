package com.zd.csms.supervisory.model;

public class CheckStockPicVO {

	/**
	 * CheckStockManageVO 主键id
	 */
	private int  csmid;
	/**
	 * 图片序号
	 */
    private int indexs;
    /**
     * 图片ID
     */
    private int picid;
	public int getCsmid() {
		return csmid;
	}
	public void setCsmid(int csmid) {
		this.csmid = csmid;
	}
	public int getIndexs() {
		return indexs;
	}
	public void setIndexs(int indexs) {
		this.indexs = indexs;
	}
	public int getPicid() {
		return picid;
	}
	public void setPicid(int picid) {
		this.picid = picid;
	}
}

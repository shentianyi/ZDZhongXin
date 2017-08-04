package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.List;

public class FixedAssetsAndUserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FixedAssetsVO vo;
	
	List<FixedAssetListVO> list;

	public FixedAssetsVO getVo() {
		return vo;
	}

	public void setVo(FixedAssetsVO vo) {
		this.vo = vo;
	}

	public List<FixedAssetListVO> getList() {
		return list;
	}

	public void setList(List<FixedAssetListVO> list) {
		this.list = list;
	}
	
	
	

}

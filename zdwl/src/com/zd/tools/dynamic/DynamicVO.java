package com.zd.tools.dynamic;

/**
 * 动态新增行VO基础类，提供了动态新增行必须得状态属性。
 * */
public class DynamicVO {
	
	private int stateFlag=-1;	//行状态标识

	public int getStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(int stateFlag) {
		this.stateFlag = stateFlag;
	}

}

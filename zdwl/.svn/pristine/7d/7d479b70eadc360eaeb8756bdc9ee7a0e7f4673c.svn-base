package com.zd.csms.base.option.model;

public class OptionObject {
	
	private String label;	//选项名称
	private Object value;	//选项值
	
	private Object upper;	//级联选项中上级选项值
	private int level;		//级联选项中选项所属级别

	public OptionObject(Object value,String label){
		this.label = label;
		/*if(value.toString().matches("^(0|[1-9][0-9]*)$")){
			this.value=Integer.parseInt(value.toString());
		}else{
			this.value = value;
		}*/
		this.value=value;
	}
	
	public OptionObject(Object value,String label,Object upper,int level){
		this.label = label;
		/*if(value.toString().matches("^(0|[1-9][0-9]*)$")){
			this.value=Integer.parseInt(value.toString());
		}else{
			this.value = value;
		}*/
		this.value = value;
		this.upper = upper;
		this.level = level;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Object getUpper() {
		return upper;
	}
	public void setUpper(String upper) {
		this.upper = upper;
	}

}

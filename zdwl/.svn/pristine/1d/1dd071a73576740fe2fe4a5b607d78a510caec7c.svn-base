package com.zd.csms.planandreport.contants;

public enum QuestionClassifyContants {

	BGSB(1,"办公设备"),
	
	BGGW(2,"办公工位"),
	
	QT1(3,"其他"),
	
	RZZL(4,"入职资料"),
	
	KQ(5,"考勤"),
	
	QT2(6,"其他"),
	
	JDZL(7,"进店资料"),
	
	ZZ(8,"总账"),
	
	XT(9,"系统"),
	
	SGTZ(10,"手工台帐"),
	
	RCKBD(11,"日查库表单"),
	
	YSJJB(12,"钥匙交接表"),
	
	JGQRS(13,"监管确认书"),
	
	SFSQS(14,"释放申请书"),
	
	YDSQS(15,"移动申请书"),
	
	YKCHDQD(16,"月库存核对清单"),
	
	SZFZ(17,"私自放证"),
	
	TQFZ(18,"提前放证"),
	
	SY(19,"私移"),
	
	SS(20,"私售"),
	
	QT3(21,"其他");
	
	
	private int code;
	private String name;
	private QuestionClassifyContants(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * 通过name获取code
	 */
	public int getCode(String name){
		QuestionClassifyContants[] values = QuestionClassifyContants.values();
		for (QuestionClassifyContants questionClassifyContants : values) {
			if(questionClassifyContants.getName().equals(name.trim())){
				return questionClassifyContants.getCode();
			}
		}
		return -1;
	}
	
	
	/*
	 * 通过code获取name
	 */
	public String getName(int code){
		QuestionClassifyContants[] values = QuestionClassifyContants.values();
		for (QuestionClassifyContants questionClassifyContants : values) {
			if(code==questionClassifyContants.getCode()){
				return questionClassifyContants.getName();
			}
		}
		return null;
	}
	
	
	
	
}

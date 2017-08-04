package com.zd.csms.windcontrol.model;
import java.io.Serializable;
import com.zd.core.annotation.table;

/**
 * @author zhangzhicheng 巡店报告-检查过程中发现的问题及监管员优/缺点
 */
@table(name = "windcontrol_inspec_record")
public class InspectionRecordVO implements Serializable {
	private static final long serialVersionUID = 218673819488451017L;
	private int inspectionId;// 巡店报告id
	private int record_type;// 1问题 2 监管员优/缺点
	private String content;// 描述问题
	private String result;// 处理结果
   
	public int getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(int inspectionId) {
		this.inspectionId = inspectionId;
	}

	public int getRecord_type() {
		return record_type;
	}

	public void setRecord_type(int record_type) {
		this.record_type = record_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

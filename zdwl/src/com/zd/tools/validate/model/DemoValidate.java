package com.zd.tools.validate.model;

import com.zd.tools.validate.Validate;
import com.zd.tools.validate.constants.DataTypeConstants;
import com.zd.tools.validate.constants.NotEmptyConstants;
import com.zd.tools.validate.constants.PatternConstants;
import com.zd.tools.validate.model.FileVO;

public class DemoValidate extends Validate {
	
	public void setFiles(){
		
		FileVO file;

		file = new FileVO();
		file.fileCode = "id";
		file.cName = "ID";
		file.dataType = DataTypeConstants.NUMBER.getCode();
		file.notEmpty = NotEmptyConstants.TRUE.getCode();
		this.addFile(file);

		file = new FileVO();
		file.fileCode = "state";
		file.cName = "状态";
		file.dataType = DataTypeConstants.NUMBER.getCode();
		file.notEmpty = NotEmptyConstants.FALSE.getCode();
		file.maxlength = 2;
		this.addFile(file);

		file = new FileVO();
		file.fileCode = "name";
		file.cName = "名称";
		file.dataType = DataTypeConstants.STRING.getCode();
		file.notEmpty = NotEmptyConstants.TRUE.getCode();
		file.maxlength = 20;
		this.addFile(file);

		file = new FileVO();
		file.fileCode = "time";
		file.cName = "时间";
		file.dataType = DataTypeConstants.TIMESTAMP.getCode();
		file.notEmpty = NotEmptyConstants.TRUE.getCode();
		file.pattern = PatternConstants.TIMESTAMP.getCode();
		this.addFile(file);

	}
}

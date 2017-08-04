package com.zd.tools.validate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zd.tools.StringUtil;
import com.zd.tools.ValueObjectUtil;
import com.zd.tools.validate.constants.NotEmptyConstants;
import com.zd.tools.validate.model.FileVO;

/**
 * 后台数据校验类（未使用）
 * */
public class ValidateUtil {
	
	private Validate vo;	//校验信息对象
	private Object obj;	//被校验对象
	
	private List<String> messages;	//校验信息集合
	private StringBuffer message;	//完整的校验信息
	
	public ValidateUtil(Validate vo,Object obj){
		this.vo = vo;
		this.obj = obj;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(){
		
		messages = null;
		message = null;
		
		Map values = ValueObjectUtil.getVoValue(obj);
		
		for(Iterator iter=vo.getFiles().iterator(); iter.hasNext();){

			FileVO file = (FileVO)iter.next();
			if(file.valdataFlag && !vo.isDisabled(file.fileCode)){
				Object value = values.get(file.fileCode);
				if(isEmpty(file,value)){
					String msg = getFileName(file) + "不能为空";
					
					if(message==null){
						message = new StringBuffer();
						messages = new ArrayList<String>();
					}
					else{
						message.append("<br>");
					}
					
					messages.add(msg);
					message.append(msg);
				}
			}
		}
		
	}
	
	public String getFileName(FileVO file){
		String name = "该项";
		if(StringUtil.isBlank(file.cName)){
			name = file.cName;
		}
		return name;
	}
	
	private boolean isEmpty(FileVO file,Object value){
		
		if(value==null){
			if(NotEmptyConstants.FALSE.equals(file.notEmpty)){
				return false;
			}
			return true;
		}
		
		if(NotEmptyConstants.TRUE.equals(file.notEmpty) && value.toString().equals("")){
			return true;
		}
		
		if(NotEmptyConstants.SPACING.equals(file.notEmpty) && StringUtil.isBlank(value.toString())){
			return true;
		}
		
		if(NotEmptyConstants.NUMBER.equals(file.notEmpty) 
		&& (value.toString().equals("") || value.toString().equals("0"))){
			return true;
		}
		
		return false;
	}

	public String getMessage() {
		return message.toString();
	}

	@SuppressWarnings("unchecked")
	public List getMessages() {
		return messages;
	}

}

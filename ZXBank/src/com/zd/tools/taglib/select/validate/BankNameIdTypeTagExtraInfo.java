package com.zd.tools.taglib.select.validate;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

/**
 * <p>版权?:北京科码先锋软件?有限公司</p>
 * @作?: JIANGYE
 * @日期: 2013-7-12 下午04:51:26
 * @描述: [EntityTypeTagExtraInfo]?BankNameTag和DistribNameTag标签中entityType?
 */
public class BankNameIdTypeTagExtraInfo extends TagExtraInfo{

	public boolean isValid(TagData data){
		boolean flag=false;
		Object o = data.getAttribute("idType");
		if(o==null){
			o=BankNameIdTypeConstants.BANK.getCode();
		}
		if(o != TagData.REQUEST_TIME_VALUE){
			for(BankNameIdTypeConstants idTypeConstants: BankNameIdTypeConstants.values()){
				if(((String) o).toLowerCase().equals(idTypeConstants.getCode())){
					flag=true;
					break;
				}
			}
		}
		return flag;
	}
}

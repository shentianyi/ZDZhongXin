package com.zd.csms.message.approval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.DAOSupport;
import com.zd.core.annotation.table;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.RoleVO;

/**
 * 审批消息Dao查询类
 * @author licheng
 *
 */
public class ApprovalDao extends DAOSupport {

	public ApprovalDao(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 这个方法是查询当前角色 已统计的审批列表中全部的消息数量
	 * @param request
	 * @return
	 */
	public int getApprovalToalCount(HttpServletRequest request){
		List<RoleVO> roles = UserSessionUtil.getUserSession(request).getRole();
		if(roles==null||roles.size()==0){
			return 0;
		}
		List<Integer> roleIds = new ArrayList<Integer>();
		for (RoleVO roleVO : roles) {
			roleIds.add(roleVO.getId());
		}
		String roleSql = Arrays.toString(roleIds.toArray()).replace('[', '(').replace(']', ')');
		
		List<ApprovalMessage> tableList = ApprovalTable.list;
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		for (ApprovalMessage am : tableList) {
			String tableName = am.getType().getAnnotation(table.class).name();
			sql.append("(select count(1) from "+tableName 
					+ " t where t.approvalstate = "+ApprovalContant.APPROVAL_WAIT.getCode()
					+" and t.nextapproval in "+roleSql+") +");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(" from dual ");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		return count;
	}
	
	
}

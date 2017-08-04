package com.zd.csms.rbac.util;

import java.io.Serializable;
public class DealerGroupUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150025313909667161L;

	public static String getSql(int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" in ( select dealerGroup.dealerId ");
		sql.append("  from t_rbac_dealerGroup_user  groupUser  ");
		sql.append("  inner join  t_rbac_dealerGroup_dealer  dealerGroup");
		sql.append("  on  dealerGroup.groupId=groupUser.groupId ");
		sql.append("  where  groupUser.userId="+userId+ ") " );
		return sql.toString() ;
	}
    
}

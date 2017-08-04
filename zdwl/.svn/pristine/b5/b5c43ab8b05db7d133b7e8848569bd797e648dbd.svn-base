package com.zd.csms.rbac.dao;

import java.util.List;
import com.zd.core.IDAO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.rbac.model.DealerGroupVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IDealerGroupDAO extends IDAO {
    public String getDataSourceName();
    public List<DealerGroupVO> findList(String name, IThumbPageTools tools);
    
    public List<DealerVO> dealerList(String dealerName,int groupId, IThumbPageTools tools);
    
	public List<DealerVO> newDealerList(String dealerName,int groupId, IThumbPageTools tools);
	
	public List<UserVO> userList(String userName,int groupId, IThumbPageTools tools);
	
	public List<UserVO> newUserList(String userName,int groupId, int roleId,IThumbPageTools tools);
	
	public int RoleIdByClientType(int code);
	
	public boolean delDealer(int groupId, int dealerId);
	
	public boolean delUser(int groupId, int userId);
	
	public boolean delUserWithGroup(int groupId);
	
	public boolean delDealerWithGroup(int groupId);
	
	public List<Integer> findDealerIdsByUserId(int userId);
}

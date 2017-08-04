package com.zd.csms.rbac.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.model.brand.BrandGroupVO;
import com.zd.tools.thumbPage.IThumbPageTools;


public interface IBrandGroupDAO extends IDAO {
    public String getDataSourceName();
    public List<BrandGroupVO> findList(String name, IThumbPageTools tools);
    
    public List<BrandVO> brandList(String BrandName,int groupId, IThumbPageTools tools);
    
	public List<BrandVO> newBrandList(String BrandName,int groupId, IThumbPageTools tools);
	
	public List<UserVO> userList(String userName,int groupId, IThumbPageTools tools);
	
	public List<UserVO> newUserList(String userName,int groupId, int roleId,IThumbPageTools tools);
	
	public int RoleIdByClientType(int code);
	
	public boolean delBrand(int groupId, int brandId);
	
	public boolean delUser(int groupId, int userId);
	
	public boolean delUserWithGroup(int groupId);
	
	public boolean delBrandWithGroup(int groupId);
	
	public List<Integer> findBrandIdsByUserId(int userId);
	
	public List<Number> findDealerIdsByUserId(int userId);
}

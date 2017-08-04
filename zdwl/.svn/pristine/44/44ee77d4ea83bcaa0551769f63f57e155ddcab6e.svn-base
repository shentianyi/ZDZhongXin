package com.zd.csms.rbac.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.dao.IBrandGroupDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.DealerGroupUserVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.model.brand.BrandGroupBrandVO;
import com.zd.csms.rbac.model.brand.BrandGroupUserVO;
import com.zd.csms.rbac.model.brand.BrandGroupVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BrandGroupService extends ServiceSupport {
	private IBrandGroupDAO dao = RbacDAOFactory.getBrandGroupDAO();

	public List<BrandGroupVO> findList(String name, IThumbPageTools tools) {
		return dao.findList(name,tools);
	}

	public BrandGroupVO getGroupById(int id) {
		return dao.get(BrandGroupVO.class, id, new BeanPropertyRowMapper(
				BrandGroupVO.class));
	}

	public boolean updateGroup(BrandGroupVO dealerGroup) {
		dealerGroup.setModifyTime(new Date());
		return dao.update(dealerGroup);
	}

	public boolean addGroup(BrandGroupVO dealerGroup) throws Exception {
		dealerGroup.setCreateTime(new Date());
		dealerGroup.setModifyTime(dealerGroup.getCreateTime());
		dealerGroup.setId(Util.getID(BrandGroupVO.class));
		return dao.add(dealerGroup);
	}

	public List<BrandVO> brandList(String dealerName, int groupId, IThumbPageTools tools) {
		return dao.brandList(dealerName,groupId, tools);
	}

	public List<BrandVO> newBrandList(String dealerName, int groupId, IThumbPageTools tools) {
		return dao.newBrandList(dealerName,groupId, tools);
	}

	public List<UserVO> userList(String userName, int groupId, IThumbPageTools tools) {
		return dao.userList(userName,groupId, tools);
	}

	public List<UserVO> newUserList(String userName,int groupId, int roleId, IThumbPageTools tools) {
		return dao.newUserList(userName,groupId, roleId, tools);
	}

	public int getRoleIdByClientType(int code) {
		return dao.RoleIdByClientType(code);
	}

	public boolean addBrand(int groupId, int[] brandIds) {
		boolean flag = false;
		BrandGroupBrandVO vo = null;
		try {
			for (int brandId : brandIds) {
				vo = new BrandGroupBrandVO();
				vo.setGroupId(groupId);
				vo.setBrandId(brandId);
				flag = dao.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addUser(int groupId, int[] userIds) {
		boolean flag = false;
		try {
			for (int userId : userIds) {
				BrandGroupUserVO vo = new BrandGroupUserVO();
				vo.setGroupId(groupId);
				vo.setUserId(userId);
				flag = dao.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delDealer(int groupId, int[] brandIds) {
		boolean flag = false;
		try {
			for (int brandId : brandIds) {
				flag = dao.delBrand(groupId, brandId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delUser(int groupId, int[] userIds) {
		boolean flag = false;
		try {
			for (int userId : userIds) {
				flag = dao.delUser(groupId, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delGroup(int id) {
		boolean flag = dao.delete(BrandGroupVO.class, id);
		flag = dao.delBrandWithGroup(id);
		flag = dao.delUserWithGroup(id);
		return flag;
	}
	
	public List<Integer> findDealerIdsByUserId(int userId) {
		//return dao.findDealerIdsByUserId(userId);
		return null;
	}
	public List<Integer> findDealerIdsByUserVOId(int userId) {
		List<Number> castList = dao.findDealerIdsByUserId(userId);
		List<Integer> returnList = new ArrayList<Integer>();
		for (Number number : castList) {
			try{
				returnList.add(number.intValue());
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
		return returnList;
	}

}

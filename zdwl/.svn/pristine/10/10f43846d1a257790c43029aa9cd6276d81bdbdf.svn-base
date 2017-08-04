package com.zd.csms.region.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.region.dao.IRegionDAO;
import com.zd.csms.region.dao.RegionDAOFactory;
import com.zd.csms.region.model.RegionQueryVO;
import com.zd.csms.region.model.RegionVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RegionService extends ServiceSupport {

	private IRegionDAO dao = RegionDAOFactory.getRegionDAO();

	public List<RegionVO> searchList(RegionQueryVO regionQuery) {
		try {
			return dao.findList(regionQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<RegionVO> searchPageList(RegionQueryVO regionQuery,
			IThumbPageTools tools) {
		try {
			return dao.searchRegionListByPage(regionQuery, tools);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(int id) {
		boolean flag= dao.delete(RegionVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}

	public boolean update(RegionVO region) {
		List<RegionVO> regionList= dao.validationName(region.getParentId(),region.getName());
		if(regionList!=null&&regionList.size()>0){
			for(RegionVO oldregion:regionList){
				if(oldregion.getId()!=region.getId()){
					this.setExecuteMessage("修改失败，地区名称不能重复！");
					return false;
				}
			}
		}
		boolean flag=dao.update(region);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}

	public RegionVO load(int id) {
		return dao.get(RegionVO.class, id, new BeanPropertyRowMapper(RegionVO.class));
	}

	public String getNameById(int id){
		RegionVO region=load(id);
		if(region!=null){
			return region.getName();
		}else{
			return "";
		}
	}
	public boolean add(RegionVO region) {
		try {
			List<RegionVO> regionList= dao.validationName(region.getParentId(),region.getName());
			if(regionList!=null&&regionList.size()>0){
				for(RegionVO oldregion:regionList){
					if(oldregion.getId()!=region.getId()){
						this.setExecuteMessage("新增失败，地区名称不能重复！");
						return false;
					}
				}
			}
			region.setId(Util.getID(RegionVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag=dao.add(region);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}

	public boolean updRegionState(int id, int status) {
		boolean flag=dao.updRegionState(id,status);
		if(flag){
			if(status==StateConstants.USING.getCode()){
				this.setExecuteMessage("启用成功！");
			}else if(status==StateConstants.UNUSED.getCode()){
				this.setExecuteMessage("停用成功！");
			}
		}else{
			if(status==StateConstants.USING.getCode()){
				this.setExecuteMessage("启用失败！");
			}else if(status==StateConstants.UNUSED.getCode()){
				this.setExecuteMessage("停用失败！");
			}
		}
		return flag;
	}
	/**
	 * 查询所有启用状态的省
	 * @return
	 */
	public List<RegionVO> findAllProvince(){
		RegionQueryVO regionQuery=new RegionQueryVO();
		regionQuery.setParentId(0);
		regionQuery.setStatus(1);
		try {
			return dao.findList(regionQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据父级ID查询子地区
	 * @return
	 */
	public List<RegionVO> findRegionByParentId(int parentId){
		RegionQueryVO regionQuery=new RegionQueryVO();
		regionQuery.setParentId(parentId);
		regionQuery.setStatus(1);
		try {
			return dao.findList(regionQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getIdByName(int parentId,String name){
		List<RegionVO> regionList= dao.validationName(parentId,name);
		if(regionList!=null && regionList.size()>0){
			return regionList.get(0).getId();
		}else{
			return 0;
		}
	}
}

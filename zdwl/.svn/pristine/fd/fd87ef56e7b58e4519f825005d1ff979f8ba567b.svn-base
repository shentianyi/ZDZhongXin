package com.zd.csms.supervisorymanagement.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisorymanagement.dao.IFixedAssetsDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.dao.mapper.FixedAssetsMapper;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class FixedAssetsService extends ServiceSupport {

	private IFixedAssetsDAO dao = SupervisoryManagementDAOFactory.getFixedAssetsDAO();
	
	public boolean addFixedAssets(FixedAssetsVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(FixedAssetsVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updFixedAssets(FixedAssetsVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteFixedAssets(int id) throws Exception {
		return dao.delete(FixedAssetsVO.class, id);
    }
	
	public FixedAssetsVO loadFixedAssetsById(int id) throws Exception{
		FixedAssetsVO fixedAssets = dao.get(FixedAssetsVO.class, id,new FixedAssetsMapper());
		return fixedAssets;
	}
	
	public List<FixedAssetsVO> searchFixedAssets(FixedAssetsQueryVO query){
		return dao.searchFixedAssetsList(query);
	}
	
	public List<FixedAssetsVO> searchFixedAssetsListByPage(FixedAssetsQueryVO vo, IThumbPageTools tools){
		List<FixedAssetsVO> list = dao.searchFixedAssetsListByPage(vo, tools);
		for (FixedAssetsVO vo1 : list) {
			//计算已使用年限（天）
				if(vo1.getPurchase_date() != null){
					
				   Calendar aCalendar = Calendar.getInstance();
	
			       aCalendar.setTime(new Date());
	
			       int nowDAY1 = aCalendar.get(Calendar.DAY_OF_YEAR);
			       
	
			       aCalendar.setTime(vo1.getPurchase_date());
	
			       int oldDAY2 = aCalendar.get(Calendar.DAY_OF_YEAR);
			       
			       vo1.setUseful_life((nowDAY1-oldDAY2)+"");
			}
		}
		return list;
	}
	/**
	 * 修改固定资产使用人
	 * @param fixedAssets
	 * @param sendee
	 * @return
	 */
	public boolean updateFixedAssetsSendee(int fixedAssetsId,int sendee){
		return dao.updateFixedAssetsSendee(fixedAssetsId,sendee);
	}
	
	
	/**
	 * 根据用户Id查询当前使用资产列表
	 * @param id
	 * @return List<FixedAssetsVO>
	 */
	public List<FixedAssetsVO> searchFixedAssetsSendeeByUserId(int id){
		return dao.searchFixedAssetsSendeeByUserId(id);
	}
	
}

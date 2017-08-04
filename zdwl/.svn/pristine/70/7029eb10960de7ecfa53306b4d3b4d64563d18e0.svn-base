package com.zd.csms.messagequartz.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.messagequartz.dao.UnInspectDao;
import com.zd.csms.messagequartz.model.UnInspectMes;
import com.zd.csms.messagequartz.model.UnInspectVO;
import com.zd.csms.messagequartz.model.UnInspectQueryVO;
import com.zd.csms.region.model.RegionVO;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class UnInspectService extends ServiceSupport{

	private UnInspectDao dao = SupervisorDAOFactory.getUnInspectDao();
	
	public List<UnInspectMes> findList(){
		return dao.findList();
	}
	
	public boolean addUnInspectMessage(UnInspectVO vo) throws Exception {
		vo.setId(Util.getID(UnInspectVO.class));
		boolean flag =dao.add(vo);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public List<UnInspectVO> searchUnInspectList(UnInspectQueryVO query){
		return dao.searchUnInspectList(query);
	}
	
	public List<UnInspectVO> searchUnInspectListByPage(UnInspectQueryVO vo, IThumbPageTools tools){
		
		//获取金融机构的名字
		RegionService reService = new RegionService();
		if (!StringUtil.isNull(vo.getProvinceId(),vo.getCityId(),vo.getAreaId())) {
			int oneId = vo.getProvinceId();
			int twoId = vo.getCityId();
			int threeId = vo.getAreaId();
			RegionVO one = reService.load(oneId);
			RegionVO two = reService.load(twoId);
			RegionVO three = reService.load(threeId);
			String name = one.getName()+"/"+two.getName()+"/"+three.getName();
			vo.setAddress(name);
		}else if (!StringUtil.isNull(vo.getProvinceId(),vo.getCityId())) {
			int oneId = vo.getProvinceId();
			int twoId = vo.getCityId();
			RegionVO one = reService.load(oneId);
			RegionVO two = reService.load(twoId);
			String name = one.getName()+"/"+two.getName();
			vo.setAddress(name);
		}
		List<UnInspectVO> list = dao.searchUnInspectByPage(vo, tools);
		for (UnInspectVO unInspectVO : list) {
			unInspectVO.setContent("风控专员"+unInspectVO.getDirector()+"未进行巡店检查");
		}
		return list;
	}
	
	/**
	 * 更改 已读状态 
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateReadStatus(int userId,int id) {
		return dao.updateReadStatus(userId,id);
	}
	
}

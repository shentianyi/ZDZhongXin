package com.zd.csms.roster.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.core.util.CommonUtil;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.dao.IRosterDAO;
import com.zd.csms.roster.dao.RosterDAOFactory;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterEntity;
import com.zd.csms.roster.model.RosterQueryBean;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RosterService extends ServiceSupport {
	
	IRosterDAO dao=RosterDAOFactory.getRosterDAO();
	
	public boolean delete(int id) {
		boolean flag=dao.delete(RosterVO.class, id);
		if(flag){
			List<PostChangeVO> postChangeList=dao.getPostChangeByRosterId(id);
			if(postChangeList!=null){
				for(PostChangeVO postChange:postChangeList){
					flag=dao.delete(PostChangeVO.class, postChange.getId());
					if(!flag){
						break;
					}
				}
			}
		}
		if(flag){
			this.setExecuteMessage("删除成功！");
			return true;
		}else{
			this.setExecuteMessage("删除失败！");
			return false;
		}
	}

	public boolean update(RosterVO roster) {
		boolean flag=dao.update(roster);
		if(flag){
			this.setExecuteMessage("修改成功！");
			return true;
		}else{
			this.setExecuteMessage("修改失败！");
			return false;
		}
	}

	public RosterVO load(int id) {
		RosterVO roster=dao.get(RosterVO.class, id,new BeanPropertyRowMapper(RosterVO.class));
		return roster;
	}
	
	public RepositoryVO loadRepositoryById(int id) {
		RepositoryVO repository=dao.get(RepositoryVO.class, id,new BeanPropertyRowMapper(RepositoryVO.class));
		return repository;
	}

	public boolean add(RosterVO roster) {
		try {
			roster.setId(Util.getID(RosterVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag=dao.add(roster);
		if(flag){
			this.setExecuteMessage("新增成功！");
			return true;
		}else{
			this.setExecuteMessage("新增失败！");
			return false;
		}
	}
	public boolean deletePostChange(int id) {
		boolean flag=dao.delete(PostChangeVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
			return true;
		}else{
			this.setExecuteMessage("删除失败！");
			return false;
		}
	}

	public boolean updatePostChange(PostChangeVO postChange) {
		boolean flag=dao.update(postChange);
		if(flag){
			this.setExecuteMessage("修改成功！");
			return true;
		}else{
			this.setExecuteMessage("修改失败！");
			return false;
		}
	}

	public PostChangeVO loadPostChange(int id) {
		PostChangeVO postChange=dao.get(PostChangeVO.class, id,new BeanPropertyRowMapper(PostChangeVO.class));
		return postChange;
	}

	public boolean addPostChange(PostChangeVO postChange) {
		try {
			postChange.setId(Util.getID(PostChangeVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag=dao.add(postChange);
		if(flag){
			this.setExecuteMessage("新增成功！");
			return true;
		}else{
			this.setExecuteMessage("新增失败！");
			return false;
		}
	}
	public List<PostChangeVO> searchPostChangeListByRosterId(int rosterId){
		return dao.getPostChangeByRosterId(rosterId);
	}
	public List<RosterEntity> searchList(
			RosterQueryVO rosterQueryVO) {
		List<RosterEntity> result=new ArrayList<RosterEntity>();
		List<RosterVO> list=dao.searchList(rosterQueryVO);
		if(list!=null){
			for(RosterVO roster:list){
				if(roster!=null){
					RosterEntity rosterEntity=new RosterEntity();
					rosterEntity.setRoster(roster);
					rosterEntity.setPostChangeList(dao.getPostChangeByRosterId(roster.getId()));
					result.add(rosterEntity);
				}
			}
		}
		return result;
	}
	

	public List<RosterEntity> searchPageList(
			RosterQueryVO rosterQueryVO, IThumbPageTools tools) {
		List<RosterEntity> result=new ArrayList<RosterEntity>();
		List<RosterVO> list	=dao.searchPageList(rosterQueryVO, tools);
		if(list!=null){
			for(RosterVO roster:list){
				if(roster!=null){
					//set驻派地区
					String dispatchCity="";
					if (roster.getDispatchCity() !=null && !(isInteger(roster.getDispatchCity()))) {
						dispatchCity = roster.getDispatchCity();
					}else{
						RepositoryService repositoryService=new RepositoryService();
						RepositoryDispatchCityVO dispatchCityVO=repositoryService.loadDispatchCity(StringUtil.intValue(roster.getDispatchCity(),0));
						if(dispatchCityVO!=null){
							dispatchCity=dispatchCityVO.getProvince()+" "+dispatchCityVO.getCity()+" "+dispatchCityVO.getCounty();
						}
					}
					
					if (roster.getRepositoryId() >0) {
						TransferService transferService = new TransferService();
						List<TransferRepositoryVO> tsList = transferService.searchLeaveTimeAndEntryTimeByRepositoryId(roster.getRepositoryId());
						if (tsList!=null) {
							 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							 String leavetime ;
							 String entrytime ;
							 int months = 0;
							 for (int i = 0; i < tsList.size(); i++) {
								if (tsList.get(i).getLeaveTime() !=null && tsList.get(i).getEntryTime() !=null) {
									leavetime = sdf.format(tsList.get(i).getLeaveTime());
									entrytime = sdf.format(tsList.get(i).getEntryTime());
									try {
										//months = getMonthSpace(leavetime,entrytime);
										months =months + CommonUtil.countMonths(leavetime,entrytime,"yyyy-MM-dd");
									} catch (Exception e) {
										e.printStackTrace();
									}
									
								}else {
									leavetime = "0";
									entrytime = "0";
								}
							 }
							 int year = months / 12;
							 months = months % 12;
							 roster.setDriveYear(year);
							 roster.setDriveMonth(months);
						}
					}
					
					roster.setDispatchCity(dispatchCity);
					RosterEntity rosterEntity=new RosterEntity();
					rosterEntity.setRoster(roster);
					rosterEntity.setPostChangeList(dao.getPostChangeByRosterId(roster.getId()));
					result.add(rosterEntity);
				}
			}
		}
		return result;
	}
	
	
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[0-9]\\d*$");    
	    return pattern.matcher(str).matches();    
	  } 
	
	/**
	 * 根据花名册的主键Id获取监管员信息
	 * @param rosterId
	 * @return
	 */
	public RosterQueryBean findRosterById(int rosterId){
		return dao.findRosterById(rosterId);
	}
	
	public String getLoginidList(int REPOSITORYID){
		return dao.getLoginidList(REPOSITORYID);
	}
	
	public RosterVO loadById(int id) {
		return dao.loadById(id);
	}

	public List<RosterVO> searchRosterList(RosterQueryVO query){
		return dao.searchList(query);
	}
	public List<RosterVO> searchRosterByRepositoryId(int repositoryId){
		return dao.searchRosterByRepositoryId(repositoryId);
	}
	
	public RosterVO getRosterBySupervisorId(int supervisorId){
		return dao.getRosterBySupervisorId(supervisorId);
	}
	
	public BusinessTransferVO loadBusinessTransferByRId(int repositoryId){
		return dao.loadBusinessTransferByRId(repositoryId);
	}
	
	public DealerVO loadDealerByRId(int repositoryId){
		return dao.loadDealerByRId(repositoryId);
	}
}

package com.zd.csms.supervisorymanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisorymanagement.dao.ITransferDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.TransferQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class TransferService extends ServiceSupport {

	private ITransferDAO dao = SupervisoryManagementDAOFactory.getTransferDAO();
	
	public boolean add(TransferVO vo) throws Exception{
		vo.setId(Util.getID(TransferVO.class));
		boolean flag=dao.add(vo);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean update(TransferVO vo) throws Exception{
		boolean flag=dao.update(vo);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}
	
	public boolean delete(int id) throws Exception{
		boolean flag=dao.delete(TransferVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}
	
	public TransferVO load(int id) throws Exception{
		return dao.get(TransferVO.class, id,  new BeanPropertyRowMapper(TransferVO.class));
	}
	
	public List<TransferVO> searchTransferList(TransferQueryVO query){
		return dao.searchTransferList(query);
	}
	
	public List<TransferVO> searchTransferListByPage(TransferQueryVO query,IThumbPageTools tools){
		return dao.searchTransferListByPage(query, tools);
	}

	public List<TransferRepositoryVO> getSupervisorListByDealerId(int dealerId) {
		return dao.getSupervisorListByDealerId(dealerId);
	}
	public List<TransferRepositoryVO> getSupervisorListByDealerIdAndRepId(String[] dealerIds,int repositoryId) {
		return dao.getSupervisorListByDealerIdAndRepId(dealerIds,repositoryId);
	}
	public boolean addDealerSupervisor(TransferRepositoryVO vo) throws Exception{
		vo.setId(Util.getID(TransferRepositoryVO.class));
		return dao.add(vo);
	}
	
	public List<TransferRepositoryVO> getSupervisorListByEntryTime(Date entryTime,int msgType) {
		List<TransferRepositoryVO> transferRepResult=new ArrayList<TransferRepositoryVO>();
		//获得到5个月的人员列表
		List<TransferRepositoryVO> transferRepList=dao.getSupervisorListByEntryTime(entryTime,msgType);
		if(transferRepList!=null && transferRepList.size()>0){
			for(TransferRepositoryVO transferRep:transferRepList){
				//查询该监管员这五个月之间有没有去别的经销商
				List<TransferRepositoryVO> transferReps=dao.getSupervisorListByEntryTimeAndRepId(transferRep.getRepositoryId(),transferRep.getEntryTime());
				if(transferReps==null || transferReps.size()==0){
					transferRepResult.add(transferRep);
				}
			}
		}
		return transferRepResult;
	}

	//查询监管员的上岗日期和离岗日期
	public List<TransferRepositoryVO> searchLeaveTimeAndEntryTimeByRepositoryId(
			int repositoryId) {
		return dao.searchLeaveTimeAndEntryTimeByRepositoryId(repositoryId);
	}
}

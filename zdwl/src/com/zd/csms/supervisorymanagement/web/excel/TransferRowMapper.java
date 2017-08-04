package com.zd.csms.supervisorymanagement.web.excel;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.util.ArrayUtils;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferSupervisorVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class TransferRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		TransferService service=new TransferService();
		TransferVO transfer=(TransferVO) obj;
		if(transfer==null){
			transfer=new TransferVO();
		}
		//经销商
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		DealerQueryBean dealer=null;
		String headBank="";
		String subBank="";
		String branchBank="";
		try {
			dealer=dealerByIdJsonAction.getDealer(transfer.getDealerId());
			if(dealer!=null){
				String bank=dealer.getBankName();
				String[] banks=bank.split("/");
				if(banks!=null && banks.length==3){
					headBank=banks[0];
					subBank=banks[1];
					branchBank=banks[2];
				}else if(banks!=null && banks.length==2){
					headBank=banks[0];
					subBank=banks[1];
				}else if(banks!=null && banks.length==1){
					headBank=banks[0];
				}
			}else{
				dealer=new DealerQueryBean();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取监管员列表
		BusinessTransferService businessTransferService = new BusinessTransferService();
		List<TransferSupervisorVO> list=new ArrayList<TransferSupervisorVO>();
		TransferSupervisorVO ts=null;
		List<TransferRepositoryVO> transferRepositoryList=new ArrayList<TransferRepositoryVO>();
		transferRepositoryList=service.getSupervisorListByDealerId(transfer.getDealerId());
		if(transferRepositoryList!=null && transferRepositoryList.size()>0){
			for(TransferRepositoryVO transferRepository:transferRepositoryList){
				int repositoryId=transferRepository.getRepositoryId();
				SupervisorJsonBean sBaseInfo;
				try {
					sBaseInfo = businessTransferService.getSupervisorByRepositoryId(repositoryId);
					if(sBaseInfo!=null){
						ts=new TransferSupervisorVO();
						ts.setName(sBaseInfo.getName());
						ts.setGender(sBaseInfo.getGender());
						ts.setContactNumber(sBaseInfo.getSelfTelephone());
						ts.setEntryDate(transferRepository.getEntryTime());
						ts.setEntryNature(transferRepository.getEntryNature());
						ts.setRemark("");
						ts.setWorkCondition("");
						RosterService rosterService=new RosterService();
						RosterVO roster=rosterService.getRosterBySupervisorId(sBaseInfo.getId());
						if(roster!=null){
							ts.setStaffNo(roster.getStaffNo());
						}
						list.add(ts);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		TransferSupervisorVO present=null;
		if(list.size()>0){
			//现任监管员
			 present=list.get(list.size()-1);
			//其他监管员列表
			list.remove(list.size()-1);
		}else{
			present=new TransferSupervisorVO();
		}
		//现任监管员
		String[] presentStr=new String[]{present.getName(),present.getContactNumber(),present.getGender(),present.getStaffNo()
				,present.getEntryDate()==null?" ":DateUtil.getStringFormatByDate(present.getEntryDate(),"yyyy-MM-dd"),present.getEntryNature()," "," "};
		
		String[] supervisorStr=new String[]{};
		supervisorStr=(String[]) ArrayUtils.concat(supervisorStr, presentStr);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				TransferSupervisorVO transferSupervisor=list.get(i);
				String[] laterStr=new String[]{transferSupervisor.getName(),transferSupervisor.getContactNumber(),transferSupervisor.getGender()
						,transferSupervisor.getStaffNo(),DateUtil.getStringFormatByDate(transferSupervisor.getEntryDate(),"yyyy-MM-dd")
						,transferSupervisor.getEntryNature()};
				String[] leaveStr=null;
				//店方要求填什么？
				if(i+1==list.size()){
					leaveStr=new String[]{DateUtil.getStringFormatByDate(present.getEntryDate(),"yyyy-MM-dd")
							," "," "," "};
				}else{
					leaveStr=new String[]{DateUtil.getStringFormatByDate(list.get(i+1).getEntryDate(),"yyyy-MM-dd")
							," "," "," "};
				}
				laterStr=(String[]) ArrayUtils.concat(laterStr,leaveStr);
				supervisorStr=(String[]) ArrayUtils.concat(supervisorStr, laterStr);
			}
		}
		String[] result=new String[]{dealer.getDealerName(),headBank,subBank,branchBank,dealer.getProvince(),dealer.getCity(),
				dealer.getBrand(),dealer.getAddress()," ",dealer.getBindInfo(),dealer.getBankName(),
				transfer.getWorkCardNumber()+"",transfer.getIdentifyNumber()+"",
				DateUtil.getStringFormatByDate(transfer.getGrantDate(),"yyyy-MM-dd"),
				transfer.getQqNumber(),transfer.getQqPassword(),transfer.getQqPasswordProtect()};
		return (String[]) ArrayUtils.concat(result,supervisorStr);
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"经销商","合作银行（总）","合作银行（分）","合作银行（支）","省","市","品牌","地址","店方要求","绑定店","绑定行",
				"工牌/个","标识/个","发放日期","QQ账号名称","密码","密保",
				"现任监管员","联系电话","性别","员工工号","调入时间","调入性质","备注","工服情况",
				"第一任监管员","联系电话","性别","员工工号","调入时间","调入性质","调离时间","调离性质","备注","工服情况",
				"第二任监管员","联系电话","性别","员工工号","调入时间","调入性质","调离时间","调离性质","备注","工服情况",
				"第三任监管员","联系电话","性别","员工工号","调入时间","调入性质","调离时间","调离性质","备注","工服情况","…"};
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

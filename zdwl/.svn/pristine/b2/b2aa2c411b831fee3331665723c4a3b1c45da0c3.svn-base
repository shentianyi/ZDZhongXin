package com.zd.csms.supervisory.service.costmail;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.costmaill.SupervisorCostMailMessageDao;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageQueryVO;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageVO;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisorCostMailMessageService extends ServiceSupport{

	private SupervisorCostMailMessageDao dao = SupervisorDAOFactory.getSupervisorCostMail();
	
	public boolean addCostMailMessage(SupervisorCostMailMessageVO vo) throws Exception {
		vo.setId(Util.getID(SupervisorCostMailMessageVO.class));
		boolean flag =dao.add(vo);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public List<SupervisorCostMailMessageVO> searchCostMailList(SupervisorCostMailMessageQueryVO query){
		return dao.searchCostMailList(query);
	}
	
	public List<SupervisorCostMailMessageVO> searchCostMailListByPage(SupervisorCostMailMessageQueryVO vo, IThumbPageTools tools){
		
		//获取金融机构的名字
		BankService bankService = new BankService();
		if (!StringUtil.isNull(vo.getOneBankId(),vo.getTwoBankId(),vo.getThreeBankId())) {
			int onebankId = vo.getOneBankId();
			int twoBandId = vo.getTwoBankId();
			int threeBankId = vo.getThreeBankId();
			BankVO one = bankService.get(onebankId);
			BankVO two = bankService.get(twoBandId);
			BankVO three = bankService.get(threeBankId);
			String bankName = one.getBankName()+"/"+two.getBankName()+"/"+three.getBankName();
			vo.setBankname(bankName);
		}else if (!StringUtil.isNull(vo.getOneBankId(),vo.getTwoBankId())) {
			int onebankId = vo.getOneBankId();
			int twoBandId = vo.getTwoBankId();
			BankVO one = bankService.get(onebankId);
			BankVO two = bankService.get(twoBandId);
			String bankName = one.getBankName()+"/"+two.getBankName();
			vo.setBankname(bankName);
		}
		
		return dao.searchCostMailListByPage(vo, tools);
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

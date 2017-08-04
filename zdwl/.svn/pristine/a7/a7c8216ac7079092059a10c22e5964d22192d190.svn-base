package com.zd.csms.supervisory.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.BusinessTransferContant;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.dao.IAbnormalDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.AbnormalQueryBean;
import com.zd.csms.supervisory.model.AbnormalQueryVO;
import com.zd.csms.supervisory.model.AbnormalVO;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 异常事件/异常数据
 *
 */

public class AbnormalService extends ServiceSupport{
	private IAbnormalDAO dao = SupervisorDAOFactory.getAbnormalDAO();
	
	public AbnormalVO get(int id ){
		return dao.get(AbnormalVO.class, id, new BeanPropertyRowMapper(AbnormalVO.class));
	}
	
	public boolean add(AbnormalVO bean) throws Exception{
		bean.setId(Util.getID(AbnormalVO.class));
		/*boolean flag=validateDealerId(bean.getDealerId(),bean.getId());
		if(!flag){
			this.setExecuteMessage("该经销商已经存在异常数据记录，请重新选择经销商！");
			return false;
		}else{*/
			boolean flag=dao.add(bean);
			if(flag){
				this.setExecuteMessage("新增成功！");
			}else{
				this.setExecuteMessage("新增失败！");
			}
			return flag ;
		//}
	}
	
	public boolean update(AbnormalVO oldBean,int currRole) throws Exception{
		AbnormalVO bean = dao.get(AbnormalVO.class, oldBean.getId(), new BeanPropertyRowMapper(AbnormalVO.class));
		/*boolean flag=validateDealerId(bean.getDealerId(),bean.getId());
		if(!flag){
			this.setExecuteMessage("该经销商已经存在异常数据记录，请重新选择经销商！");
			return false;
		}*/
		if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			bean.setRemark(oldBean.getRemark());
			bean.setGjDate(oldBean.getGjDate());
			bean.setProgress(oldBean.getProgress());
			bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
			boolean flag= dao.update(bean);
			if(flag){
				this.setExecuteMessage("修改成功！");
				//新增-给部长、总监、视频经理、风控经理、内控专员发送信息提醒
				/*try {
					MessageUtil.sendMsg(new String[]{
							RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
							RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"",
							RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
							RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+"",
							RoleConstants.VIDEO_AMALDAR.getCode()+"",
							RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
							RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""}, "异常事件/异常数据",
							"/supervisory/abnormal.do?method=findList", 1,MessageTypeContant.EXCEPTIONDATE.getCode(),bean.getCreateUser());
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				try {
					//给部长、总监、视频经理、风控经理、内控专员发送信息提醒
					String[] roleIds = new String[]{
							RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
							RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"",
							RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
							RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+"",
							RoleConstants.VIDEO_AMALDAR.getCode()+"",
							RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
							RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""};
							for (String rid : roleIds) {
							int uid = MessageUtil.getUserId(rid);
								MessageService msgService = new MessageService();
								MessageVO msg = msgService.loadMsgByUserAndType(Integer.valueOf(uid), MessageTypeContant.DEALEREXIT.getCode());
								if(msg != null){
									int name = 1;
									if(StringUtil.isNumber(msg.getName())){
										name = Integer.parseInt(msg.getName())+1;
									}
									msg.setName(name+"");
									msgService.updMessage(msg);
								}else{
									MessageUtil.sendOrUpdateMeg(roleIds,  1+"", 
											"/supervisory/abnormal.do?method=findList", 1,MessageTypeContant.EXCEPTIONDATE.getCode(),bean.getCreateUser());
								}
							}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				this.setExecuteMessage("修改失败！");
			}
			return flag;
		}else{
			bean.setBusiness(oldBean.getBusiness());
			bean.setDealerId(oldBean.getDealerId());
			bean.setTotalStock(oldBean.getTotalStock());
			bean.setJyAnomalies(oldBean.getJyAnomalies());
			
			bean.setShCarNumber(oldBean.getShCarNumber());
			bean.setShCarMoney(oldBean.getShCarMoney());
			bean.setShContinuedDay(oldBean.getShContinuedDay());
			
			bean.setXsCarNumber(oldBean.getXsCarNumber());
			bean.setXsCarMoney(oldBean.getXsCarMoney());
			bean.setXsContinuedDay(oldBean.getXsContinuedDay());
			
			bean.setSyCarNumber(oldBean.getSyCarNumber());
			bean.setSyCarMoney(oldBean.getSyCarMoney());
			bean.setSyContinuedDay(oldBean.getSyContinuedDay());
			
			bean.setYcproportion(oldBean.getYcproportion());
			bean.setEarliestInvoice(oldBean.getEarliestInvoice());
			bean.setAmountnotfilled(oldBean.getAmountnotfilled());
			
			bean.setEarliestExpire(oldBean.getEarliestExpire());
			bean.setOutstandingAmount(oldBean.getOutstandingAmount());
			boolean flag= dao.update(bean);
			if(flag){
				this.setExecuteMessage("修改成功！");
			}else{
				this.setExecuteMessage("修改失败！");
			}
			return flag;
		}
		
	}
	
	public boolean delete(AbnormalVO bean) throws Exception{
		boolean flag= dao.delete(AbnormalVO.class, bean.getId());
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<AbnormalQueryBean> findList(AbnormalQueryVO query,IThumbPageTools tools){
		RegionService regionService=new RegionService();
		List<AbnormalQueryBean> list=dao.findList(query, tools);
		if(list!=null && list.size()>0){
			for(AbnormalQueryBean abnormal:list){
				abnormal.setProvince(regionService.getNameById(StringUtil.intValue(abnormal.getProvince(), 0)));
				abnormal.setCity(regionService.getNameById(StringUtil.intValue(abnormal.getCity(), 0)));
			}
		}
		return list;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		AbnormalVO bean =  get(id);
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		//新增-提交后给业务专员、业务经理、银行发送信息提醒
		/*try {
			MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",
					RoleConstants.BUSINESS_AMALDAR.getCode()+"",
					RoleConstants.BANK_APPROVE.getCode()+""}, "异常事件/异常数据",
					"/supervisory/abnormal.do?method=findList", 1,MessageTypeContant.EXCEPTIONDATE.getCode(),bean.getCreateUser());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {
			String[] userids = new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",
					RoleConstants.BUSINESS_AMALDAR.getCode()+"",
					RoleConstants.BANK_APPROVE.getCode()+""};
					for (String uid : userids) {
						MessageService msgService = new MessageService();
						MessageVO msg = msgService.loadMsgByUserAndType(Integer.valueOf(uid), MessageTypeContant.DEALEREXIT.getCode());
						if(msg != null){
							int name = 1;
							if(StringUtil.isNumber(msg.getName())){
								name = Integer.parseInt(msg.getName())+1;
							}
							msg.setName(name+"");
							msgService.updMessage(msg);
						}else{
							MessageUtil.addOrUpdateMeg(Integer.parseInt(uid),  1+"", 
									"/supervisory/abnormal.do?method=findList", 1,MessageTypeContant.EXCEPTIONDATE.getCode(),bean.getCreateUser());
						}
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.update(bean);
	}
	
	/**
	 * 根据经销商Id查询异常数量
	 * @param id
	 * @return
	 */
	public int findCountByDealerId(int id){
		return dao.findCountByDealerId(id);
	}
	
	public boolean validateDealerId(int dealerId,int abnormalId){
		List<AbnormalVO> abnormalList=dao.getAbnormalListByDealerId(dealerId);
		boolean flag=true;
		if(abnormalList!=null && abnormalList.size()>0){
			for(AbnormalVO abnormal : abnormalList){
				if(abnormal.getId()==abnormalId){
					flag=flag&&true;
				}else{
					flag=false;
				}
			}
		}
		return flag;
	}
}

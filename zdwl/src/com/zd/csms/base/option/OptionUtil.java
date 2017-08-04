package com.zd.csms.base.option;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.csms.bank.contants.BankContants;
import com.zd.csms.base.option.dao.IOptionDAO;
import com.zd.csms.base.option.dao.OptionDAOFactory;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.business.contants.ComplaintSorts;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.business.service.TwoAddressService;
import com.zd.csms.business.service.YwBankService;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.BusinessTranfContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.contants.DealerExitContant;
import com.zd.csms.marketing.contants.MarketProjectCirculationContant;
import com.zd.csms.marketing.contants.SupervisorAttributeContant;
import com.zd.csms.marketing.model.BrandQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RbacConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.region.contants.RegionLevelContant;
import com.zd.csms.repository.constants.RepositoryReason;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.set.contants.BankDockType;
import com.zd.csms.supervisory.contants.RecommendChannelContants;
import com.zd.csms.supervisory.contants.RegisteredStatusContants;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.ActualAddressService;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.AfterHandoverNatureContants;
import com.zd.csms.supervisorymanagement.contants.AssetsStateContant;
import com.zd.csms.supervisorymanagement.contants.AssetsTypeContant;
import com.zd.csms.supervisorymanagement.contants.DataMailcostReceiverTypeContants;
import com.zd.csms.supervisorymanagement.contants.HandoverNatureContants;
import com.zd.csms.supervisorymanagement.contants.HandoverTypeContants;
import com.zd.csms.supervisorymanagement.contants.ReceiveNatureContants;
import com.zd.csms.supervisorymanagement.contants.ResignReasonContants;
import com.zd.csms.util.DateUtil;

/**
 * 
 * @author licheng
 *
 */
public class OptionUtil {

	private static IOptionDAO dao = OptionDAOFactory.getOptionsDAO();
	private static DraftService draftService = new DraftService();

	public static List<OptionObject> stateOptions() {

		List<OptionObject> options = new ArrayList<OptionObject>();

		OptionObject option;

		option = new OptionObject(StateConstants.USING.getCode(), StateConstants.USING.getName());
		options.add(option);
		option = new OptionObject(StateConstants.UNUSED.getCode(), StateConstants.UNUSED.getName());
		options.add(option);

		return options;
	}
	public static List<OptionObject> regionLevel() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		option = new OptionObject(RegionLevelContant.FIRST_LEVEL.getCode(), RegionLevelContant.FIRST_LEVEL.getName());
		options.add(option);
		option = new OptionObject(RegionLevelContant.SECOND_LEVEL.getCode(), RegionLevelContant.SECOND_LEVEL.getName());
		options.add(option);
		option = new OptionObject(RegionLevelContant.THIRD_LEVEL.getCode(), RegionLevelContant.SECOND_LEVEL.getName());
		options.add(option);
		return options;
	}

	/**
	 * 客户类型下拉列表
	 * 
	 * @return List<OptionObject>
	 */
	public static List<OptionObject> clientTypeOptions() {

		List<OptionObject> options = new ArrayList<OptionObject>();

		OptionObject option;

		for (ClientTypeConstants item : ClientTypeConstants.values()) {
			option = new OptionObject(item.getCode(), item.getName());
			options.add(option);
		}

		return options;

	}

	/**
	 * 资源类型选项集合
	 * 
	 * @return List<OptionObject>
	 */
	public static List<OptionObject> resourceTypeOptions() {
		List<OptionObject> nodeTypeOptions = new ArrayList<OptionObject>();

		OptionObject option = new OptionObject(RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode(), "目录");
		nodeTypeOptions.add(option);

		option = new OptionObject(RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode(), "节点");
		nodeTypeOptions.add(option);

		return nodeTypeOptions;
	}

	/**
	 * 完整状态选项集合 包含StateConstants下定义的所有枚举值
	 * 
	 * @return List<OptionObject>
	 */
	public static List<OptionObject> fullStateOptions() {

		List<OptionObject> options = new ArrayList<OptionObject>();

		OptionObject option;
		for (StateConstants state : StateConstants.values()) {
			option = new OptionObject(state.getCode(), state.getName());
			options.add(option);
		}

		return options;
	}

	public static List<OptionObject> bankTypeOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		BankContants[] bcs = BankContants.values();
		for (BankContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		
		
		return options;
	}
	
	public static List<OptionObject> supervisionModes(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()
				, MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()
				, MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.HDXK.getCode()
				, MarketProjectCirculationContant.HDXK.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.JRJGK.getCode()
				, MarketProjectCirculationContant.JRJGK.getName()));
		return options;
	}
	
	public static List<OptionObject> cooperationModels(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(MarketProjectCirculationContant.COOPERATIONMODEL_TWO.getCode(), MarketProjectCirculationContant.COOPERATIONMODEL_TWO.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.COOPERATIONMODEL_THREE.getCode(), MarketProjectCirculationContant.COOPERATIONMODEL_THREE.getName()));
		return options;
	}
	
	public static List<OptionObject> yesorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"是"));
		options.add(new OptionObject(2,"否"));
		return options;
	}
	
	public static List<OptionObject> isClearTicket(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"清票"));
		options.add(new OptionObject(2,"未清票"));
		return options;
	}
	
	public static List<OptionObject> bankState(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"待审批"));
		options.add(new OptionObject(2,"审核通过"));
		options.add(new OptionObject(3,"审核不通过"));
		return options;
	}
	public static List<OptionObject> bankType(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(2,"入库"));
		options.add(new OptionObject(3,"出库"));
		options.add(new OptionObject(4,"移动"));
		return options;
	}
	public static List<OptionObject> twoAddressType(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"本库"));
		options.add(new OptionObject(2,"二库"));
		options.add(new OptionObject(3,"二网"));
		return options;
	}
	
	public static List<OptionObject> payModes(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(MarketProjectCirculationContant.PAYMODE_MONTH.getCode(), MarketProjectCirculationContant.PAYMODE_MONTH.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.PAYMODE_QUARTER.getCode(), MarketProjectCirculationContant.PAYMODE_QUARTER.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.PAYMODE_SIXMONTH.getCode(), MarketProjectCirculationContant.PAYMODE_SIXMONTH.getName()));
		options.add(new OptionObject(MarketProjectCirculationContant.PAYMODE_YEAR.getCode(), MarketProjectCirculationContant.PAYMODE_YEAR.getName()));
		return options;
	}
	
	public static List<OptionObject> getDealers(){
		return dao.options("market_dealer", "id", "dealerName");
	}
	
	
	public static List<OptionObject> ActualAddressDealers(UserVO user){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		DealerService dservice = new DealerService();
		ActualAddressService aaservice = new ActualAddressService();
		List<String> list = new ArrayList<String>();
		try {
			if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
				list = aaservice.findAllIds(user, 1);
			}else{
				RoleService rs = new RoleService();
				UserRoleQueryVO urquery = new UserRoleQueryVO();
				urquery.setUser_id(user.getId());
				List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
				if(urList != null && urList.size()>0){
					for(UserRoleVO urvo : urList){
						if(urvo.getRole_id() == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
							list = aaservice.findAllIds(user, 2);
						}
					}
				}
			}
			
			List<DealerVO> dList = new ArrayList<DealerVO>();
			if(list != null && list.size()>0){
				for(int i=0; i<list.size();i++){
					DealerVO dvo = dservice.loadDealerById(Integer.parseInt(list.get(i)));
					dList.add(dvo);
				}
			}
			
			if(dList != null && dList.size() > 0){
				for(DealerVO dvo : dList){
					option = new OptionObject(dvo.getId(),dvo.getDealerName());
					options.add(option);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}
	
	public static List<OptionObject> TwoAddressDealers(UserVO user){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		DealerService dservice = new DealerService();
		TwoAddressService aaservice = new TwoAddressService();
		TwoAddressQueryVO aaQuery = new TwoAddressQueryVO();
		try {
			if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
				aaQuery.setTypes(1);
				aaQuery.setUserid(user.getClient_id());
			}else{
				RoleService rs = new RoleService();
				UserRoleQueryVO urquery = new UserRoleQueryVO();
				urquery.setUser_id(user.getId());
				List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
				if(urList != null && urList.size()>0){
					for(UserRoleVO urvo : urList){
						if(urvo.getRole_id() == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
							aaQuery.setTypes(2);
							aaQuery.setUserid(user.getId());
						}
					}
				}
			}
			List<TwoAddressVO> list = aaservice.searchTwoAddressList(aaQuery);
			List<DealerVO> dList = new ArrayList<DealerVO>();
			if(list != null && list.size()>0){
				for(TwoAddressVO aavo : list){
					DealerVO dvo = dservice.loadDealerById(aavo.getDistribid());
					dList.add(dvo);
				}
			}
			
			if(dList != null && dList.size() > 0){
				for(DealerVO dvo : dList){
					option = new OptionObject(dvo.getId(),dvo.getDealerName());
					options.add(option);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}
	
	public static List<OptionObject> getDealersByYW(int userid,int roleId){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		DealerService dservice = new DealerService();
		
		if(roleId == RoleConstants.SR.getCode()||roleId == RoleConstants.BUSINESS_AMALDAR.getCode()){
			options = dao.options("market_dealer t", "id", "dealerName"," t.cooperationstate = ?",new Object[]{DealerContant.COOPERATIONSTATE_IN.getCode()});
		}else{
			List<DealerVO> dList = dservice.findListByYW(userid);
			if(dList != null && dList.size() > 0){
				for(DealerVO dvo : dList){
					option = new OptionObject(dvo.getId(),dvo.getDealerName());
					options.add(option);
				}
			}
		}
		
		return options;
	}
	
	/**
	 * 获取经销商列表
	 * @param state 合作状态
	 * @return
	 */
	public static List<OptionObject> getDealers(int state){
		List<OptionObject> options = dao.options("market_dealer t", "id", "dealerName"," t.cooperationstate = ?",new Object[]{state});
		return options;
	}
	
	public static List<OptionObject> getDealersByAbnormal(int userId){
		return dao.options("market_dealer_supervisor t left join market_dealer md on md.id = t.dealerid "
				, "md.id", "md.dealerName"," t.repositoryid = ? ",new Object[]{userId});
	}

	public static List<OptionObject> superVisorRecommendChannelOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		RecommendChannelContants[] bcs = RecommendChannelContants.values();
		for (RecommendChannelContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	public static List<OptionObject> afterHandoverNatureOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		AfterHandoverNatureContants[] bcs = AfterHandoverNatureContants.values();
		for (AfterHandoverNatureContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	public static List<OptionObject> handoverNatureOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		HandoverNatureContants[] bcs = HandoverNatureContants.values();
		for (HandoverNatureContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	public static List<OptionObject> receiveNatureOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		ReceiveNatureContants[] bcs = ReceiveNatureContants.values();
		for (ReceiveNatureContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	public static List<OptionObject> resignReasonOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		ResignReasonContants[] bcs = ResignReasonContants.values();
		for (ResignReasonContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	
	
	/**
	 * 
	 * number:		63
	 * author:		sxs
	 * describe:    根据用户id查询使用的固定资产列表  并生成下拉框数据
	 * params:		userid
	 * return:		List<OptionObject>
	 */
	public static List<OptionObject> fixedAssetsOptions(int id){
		StringBuffer sql = new StringBuffer();
		sql.append("(select t3.* from  t_supervisor_basic_information t1 ");
		sql.append(" join t_fixed_asset_list t2 on t1.id = t2.username ");
		sql.append(" join t_fixed_assets t3 on t3.id = t2.fid ");
		sql.append(" join t_repository t4 on t1.id = t4.supervisorid ");
		sql.append(" join t_rbac_user t5 on t5.client_id = t4.id ");
		sql.append(" where t5.id = "+id + ") t");
		System.out.println("固定资产列表 sql:"+sql.toString());
		return dao.options(sql.toString(), "t.id", "t.asset_name");
	}
	

	/**
	 * 根据上岗状态从储备库中查询监管员
	 * @param state
	 * @return
	 */
	public static List<OptionObject> getRepository(int state){
		return dao.options(" T_REPOSITORY t "
				+ " left join t_supervisor_basic_information s on t.supervisorid = s.id where t.STATUS = "+state, "t.id", "s.name");
	}
	public static List<OptionObject> getRepository(int...state){
		StringBuffer sql = new StringBuffer();
		sql.append(" T_REPOSITORY t "
		+ " left join t_supervisor_basic_information s on t.supervisorid = s.id ");
		if(state!=null&&state.length>0){
			sql.append(" where t.STATUS in ( ");
			for (int i : state) {
				sql.append(i+",");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append( ")");
		}
		return dao.options(sql.toString(), "t.id", "s.name");
	}
	
	public static List<OptionObject> getStaffNo(int...state){
		StringBuffer sql = new StringBuffer();
		sql.append(" T_REPOSITORY t "
		+ " inner join T_ROSTER r on t.id = r.repositoryId where t.STATUS in (");
		if(state!=null&&state.length>0){
			for (int i : state) {
				sql.append(i+",");
			}
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append( ")");
		sql.append(" and r.staffNo is not null ");
		return dao.options(sql.toString(), "t.id", "r.staffNo");
	}
	public static List<OptionObject> getDealerRepository(){
		StringBuffer sql = new StringBuffer();
		sql.append(" T_REPOSITORY t "
		+ " left join t_supervisor_basic_information s on t.supervisorid = s.id where t.id in "
		+ "(select distinct repositoryId from  MARKET_DEALER_SUPERVISOR  )");
		return dao.options(sql.toString(), "t.id", "s.name");
	}
	
	public static List<OptionObject> getDealerByRepositoryId(int repositoryId){
		StringBuffer sql = new StringBuffer();
		sql.append(" market_dealer md "
		+ "where md.id in "
		+ "(select distinct dealerId from  MARKET_DEALER_SUPERVISOR  where repositoryId = "+repositoryId+")");
		return dao.options(sql.toString(), "md.id", "md.dealerName");
	}
	
	
	public static List<OptionObject> getDealerSupervisor(int dealerId){
		StringBuffer sql = new StringBuffer();
		sql.append(" t_supervisor_basic_information s "
		+ " inner join T_REPOSITORY t on t.supervisorid = s.id where t.id in "
		+ "(select distinct repositoryId from  MARKET_DEALER_SUPERVISOR  where dealerId = "+dealerId +")");
		return dao.options(sql.toString(), "s.id", "s.name");
	}
	
	public static List<OptionObject> getAllSupervise(){
		return dao.options("t_supervisor_basic_information", "id", "name");
	}
	
	public static List<OptionObject> getNoRepositorySupervise(int supervisorId){
		return dao.options("t_supervisor_basic_information s "
				+ " where s.id not in ( select r.supervisorId from  T_REPOSITORY r where r.supervisorId !="+supervisorId+") ", "s.id", "s.name");
	}
	public static List<OptionObject> getRepositorySupervise(){
		return dao.options("t_supervisor_basic_information s "
				+ " where s.id in ( select r.supervisorId from  T_REPOSITORY r where r.status in (1,2) ) ", "s.id", "s.name");
	}
	
	/**
	 * 根据经销商主键Id获取对应的监管员
	 * @param dealerId
	 * @return
	 */
	public static List<OptionObject> getSupervisorByDealer(int dealerId){
		StringBuffer sql = new StringBuffer();
		sql.append(" T_REPOSITORY t "
				+ " left join t_supervisor_basic_information s on t.supervisorid = s.id ");
		sql.append(" where t.id in "
				+ "(select mds.repositoryid from market_dealer_supervisor mds where mds.dealerid=?)");
		
		return dao.options(sql.toString(), "t.id", "s.name","",new Object[]{dealerId});
	}
	
	public static List<OptionObject> getSupervisorSources(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(BusinessTranfContant.SUPERVISORSOURCE_SD.getCode(), BusinessTranfContant.SUPERVISORSOURCE_SD.getName()));
		options.add(new OptionObject(BusinessTranfContant.SUPERVISORSOURCE_WP.getCode(), BusinessTranfContant.SUPERVISORSOURCE_WP.getName()));
		return options;
	}
	
	public static List<OptionObject> getSupervisorAttrubutes(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		
		SupervisorAttributeContant[] sac = SupervisorAttributeContant.values();
		for (SupervisorAttributeContant as : sac) {
			options.add(new OptionObject(as.getCode(), as.getName()));
		}
		
		return options;
	}
	//根据经销商ID获取二网地址
	public static List<OptionObject> getTwoAddress(int dealerId){
		
		TwoAddressService taservice = new TwoAddressService();
		TwoAddressQueryVO taquery = new TwoAddressQueryVO();
		List<OptionObject> options = new ArrayList<OptionObject>();
		
		taquery.setDistribid(dealerId);
		taquery.setType(3);
		List<TwoAddressVO> taList = taservice.searchTwoAddressList(taquery);
		if(taList != null && taList.size()>0){
			for (TwoAddressVO ta : taList) {
				options.add(new OptionObject(ta.getId(), ta.getTwo_name()));
			}
		}
		return options;
	}
	
	//根据经销商ID获取二网,二库,以及本库
		public static List<OptionObject> getAllAddress(int dealerId){
			
			TwoAddressService taservice = new TwoAddressService();
			TwoAddressQueryVO taquery = new TwoAddressQueryVO();
			List<OptionObject> options = new ArrayList<OptionObject>();
			
			taquery.setDistribid(dealerId);
			List<TwoAddressVO> taList = taservice.searchTwoAddressList(taquery);
			if(taList != null && taList.size()>0){
				for (TwoAddressVO ta : taList) {
					options.add(new OptionObject(ta.getId(), ta.getTwo_name()));
				}
			}
			return options;
		}
		
	//二网地址
	public static List<OptionObject> getTwoAddress(UserVO user){
		
		TwoAddressService taservice = new TwoAddressService();
		TwoAddressQueryVO taquery = new TwoAddressQueryVO();
		List<OptionObject> options = new ArrayList<OptionObject>();
		YwBankService ybservice = new YwBankService();
		
		if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			DealerSupervisorService dss = new DealerSupervisorService();
			DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
			dsquery.setSupervisorId(user.getClient_id());
			List<DealerSupervisorVO> dsList = dss.searchDealerSupervisorList(dsquery);
			StringBuffer sb = new StringBuffer();
			if(dsList != null && dsList.size()>0){
				for(DealerSupervisorVO dsvo : dsList){
					sb.append(dsvo.getDealerId()+",");
				}
				
				taquery.setDistribids(sb.toString().substring(0, sb.toString().length()-1));
				List<TwoAddressVO> taList = taservice.searchTwoAddressList(taquery);
				if(taList != null && taList.size()>0){
					for (TwoAddressVO ta : taList) {
						options.add(new OptionObject(ta.getId(), ta.getTwo_name()));
					}
				}
			}
		}else if(user.getClient_type() == ClientTypeConstants.BUSINESS.getCode()){
			List<TwoAddressVO> taList = ybservice.searchTwoAddress(user.getId());
			if(taList != null && taList.size()>0){
				for (TwoAddressVO ta : taList) {
					options.add(new OptionObject(ta.getId(), ta.getTwo_name()));
				}
			}
		}
		
		
		return options;
	}
	
	//品牌
	public static List<OptionObject> getBrand(){
		return dao.options("t_brand", "id", "name");
	}
	
	//资产类型
	public static List<OptionObject> getAssetsTypeContant(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(AssetsTypeContant.ELECTRONICS.getCode(), AssetsTypeContant.ELECTRONICS.getName()));
		options.add(new OptionObject(AssetsTypeContant.WORK.getCode(), AssetsTypeContant.WORK.getName()));
		options.add(new OptionObject(AssetsTypeContant.OTHER.getCode(), AssetsTypeContant.OTHER.getName()));
		return options;
	}
	//资产状态
	public static List<OptionObject> getAssetsStateContant(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(AssetsStateContant.USEING.getCode(), AssetsStateContant.USEING.getName()));
		options.add(new OptionObject(AssetsStateContant.UNUSED.getCode(), AssetsStateContant.UNUSED.getName()));
		options.add(new OptionObject(AssetsStateContant.SCRAP.getCode(), AssetsStateContant.SCRAP.getName()));
		options.add(new OptionObject(AssetsStateContant.UNMANAGE.getCode(), AssetsStateContant.UNMANAGE.getName()));
		options.add(new OptionObject(AssetsStateContant.DAMAGE.getCode(), AssetsStateContant.DAMAGE.getName()));
		return options;
	}
	
	public static List<OptionObject> getEndMode(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(DealerExitContant.ENDMODE_QP.getCode(), DealerExitContant.ENDMODE_QP.getName()));
		options.add(new OptionObject(DealerExitContant.ENDMODE_JIAOJIE.getCode(), DealerExitContant.ENDMODE_JIAOJIE.getName()));
		return options;
	}
	//质押物状态
	public static List<OptionObject> superviseState(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"在途"));
		options.add(new OptionObject(2,"在库"));
		options.add(new OptionObject(3,"出库"));
		options.add(new OptionObject(4,"移动"));
		options.add(new OptionObject(5,"私自售卖"));
		options.add(new OptionObject(6,"私自移动"));
		return options;
	}
	
	public static List<OptionObject> repositoryStatusOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		RepositoryStatus[] rs = RepositoryStatus.values();
		for (RepositoryStatus r : rs) {
			options.add(new OptionObject(r.getCode(), r.getName()));
		}
		return options;
	}
	
	public static List<OptionObject> repositoryReasonOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();

		RepositoryReason[] rrs = RepositoryReason.values();
		for (RepositoryReason rr : rrs) {
			options.add(new OptionObject(rr.getCode(), rr.getName()));
		}
		return options;
	}
	
	public static List<OptionObject> complaintSorts(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		ComplaintSorts [] list = ComplaintSorts.values();
		for (ComplaintSorts cs : list) {
			options.add(new OptionObject(cs.getCode(), cs.getName()));
		}
		
		return options;
	}
	
	public static List<OptionObject> processingDepartment(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(ClientTypeConstants.BUSINESS.getCode(), ClientTypeConstants.BUSINESS.getName()));
		options.add(new OptionObject(ClientTypeConstants.SUPERVISORYMANAGEMENT.getCode(), ClientTypeConstants.SUPERVISORYMANAGEMENT.getName()));
		options.add(new OptionObject(ClientTypeConstants.MARKET.getCode(), ClientTypeConstants.MARKET.getName()));
		options.add(new OptionObject(ClientTypeConstants.WINDCONRTOL.getCode(), ClientTypeConstants.WINDCONRTOL.getName()));
		options.add(new OptionObject(ClientTypeConstants.VIDEO.getCode(), ClientTypeConstants.VIDEO.getName()));
		
		return options;
	}
	
	public static List<OptionObject> complaintObjs(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1, "经销商"));
		options.add(new OptionObject(2, "金融机构"));
		options.add(new OptionObject(3, "监管员"));
		return options;
	}
	
	/**
	 * 获取花名册所有监管员
	 * @return
	 */
	public static List<OptionObject> getRosters(){
		StringBuffer sql = new StringBuffer();
		sql.append("t_roster t " + 
						"  left join t_supervisor_basic_information bi " + 
						"    on t.supervisorid = bi.id");
		
		return dao.options(sql.toString(), "t.id","bi.name");
	}
	//邮寄项目
	public static List<OptionObject> mailcostState(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"保险柜"));
		options.add(new OptionObject(2,"笔记本电脑"));
		options.add(new OptionObject(3,"配件"));
		options.add(new OptionObject(4,"资料"));
		options.add(new OptionObject(5,"其它"));
		return options;
	}
	
	//监管员列表
	public static List<OptionObject> superviseOptions(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		
		OptionObject option;
		
		RosterService rs = new RosterService();
		SupervisoryService sservice = new SupervisoryService();
		RosterQueryVO rquery = new RosterQueryVO();
		List<RosterVO> rList = rs.searchRosterList(rquery);
		if(rList != null && rList.size() > 0){
			for(RosterVO rvo : rList){
				SupervisorBaseInfoVO svo = sservice.getBaseInfo(rvo.getSupervisorId());
				option = new OptionObject(rvo.getSupervisorId(),svo.getName());
				options.add(option);
			}
		}
		return options;
	}
	
	//汇票未清票
	public static List<OptionObject> draftOptions(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		
		OptionObject option;
		
		DraftService ds = new DraftService();
		DraftQueryVO dq = new DraftQueryVO();
		dq.setState(2);
		List<DraftVO> dList = ds.searchDraftList(dq);
		
		if(dList != null && dList.size() > 0){
			for(DraftVO dvo : dList){
				option = new OptionObject(dvo.getId(),dvo.getDraft_num());
				options.add(option);
			}
		}
		return options;
	}
	
	public static List<OptionObject> draftsOptions(UserVO user,int role){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts = draftService.findDraftNumByRole(user, role);
		if(drafts != null && drafts.size() > 0){
			for(String draftNum : drafts){
				option = new OptionObject(draftNum,draftNum);
				options.add(option);
			}
		}
		return options;
	}
		//当前监管员未结清汇票
		public static List<OptionObject> getDraftOptionsByDealerId(int dealerId){
			List<OptionObject> options = new ArrayList<OptionObject>();
			OptionObject option;
			DraftService dservice = new DraftService();
			DraftQueryVO dq = new DraftQueryVO();
			dq.setState(2);
			dq.setDistribid(dealerId);
			List<DraftVO> dList = dservice.searchDraftList(dq);
			if(dList != null && dList.size() > 0){
				for(DraftVO dvo : dList){
					option = new OptionObject(dvo.getId(),dvo.getDraft_num());
					options.add(option);
				}
			}
			return options;
		}
		
	//根据角色获取角色下的用户id和姓名
	public static List<OptionObject> getUserByRole(int roleId){
		String sql=
				" t_rbac_user_role t " + 
						"  left join t_rbac_user ru " + 
						"    on ru.id = t.user_id " + 
						" where t.role_id = "+roleId;
		return dao.options(sql, "t.user_id", "ru.username");
	}
	
	public static List<OptionObject> getBusinessByAbnormal(int userid){
		String sql=
				" market_dealer_supervisor t " + 
						"  inner join t_yw_bank tyw on tyw.bankid = t.bankid " + 
						"    left join t_rbac_user ru on ru.id = tyw.userid ";
		return dao.options(sql, "tyw.userid", "ru.username","t.repositoryid=?",new Object[]{userid});
	}
	
	
	public static List<OptionObject> getBrands(){
		return dao.options("t_brand","id","name");
	}
	
	public static List<OptionObject> getBanks(){
		return dao.options("t_bank","id","bankFullName");
	}
	
	public static List<OptionObject> haveorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"有"));
		options.add(new OptionObject(2,"无"));
		return options;
	}
	public static List<OptionObject> haveornoDebt(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(2,"无欠款"));
		options.add(new OptionObject(1,"有欠款"));
		return options;
	}
	public static List<OptionObject> matchorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"相符"));
		options.add(new OptionObject(2,"不相符"));
		return options;
	}
	public static List<OptionObject> nomalorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"正常"));
		options.add(new OptionObject(2,"不正常"));
		return options;
	}
	public static List<OptionObject> informorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"告知"));
		options.add(new OptionObject(2,"未告知"));
		options.add(new OptionObject(3,"无"));
		return options;
	}
	public static List<OptionObject> breakorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"有损"));
		options.add(new OptionObject(2,"无损"));
		return options;
	}
	public static List<OptionObject> keyAmountorno(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"一套"));
		options.add(new OptionObject(2,"两套"));
		return options;
	}
	public static List<OptionObject> getCooperationState(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(DealerContant.COOPERATIONSTATE_IN.getCode(), DealerContant.COOPERATIONSTATE_IN.getName()));
		options.add(new OptionObject(DealerContant.COOPERATIONSTATE_OUT.getCode(), DealerContant.COOPERATIONSTATE_OUT.getName()));
		options.add(new OptionObject(DealerContant.COOPERATIONSTATE_EXIT.getCode(), DealerContant.COOPERATIONSTATE_EXIT.getName()));
		return options;
	}
	public static Object superVisorRegisteredStatusOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		RegisteredStatusContants[] bcs = RegisteredStatusContants.values();
		for (RegisteredStatusContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	public static Object dateType() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"本库日查库"));
		options.add(new OptionObject(2,"二网日查库"));
		return options;
	}
	public static List<OptionObject> trainingEffect(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"好"));
		options.add(new OptionObject(2,"中"));
		options.add(new OptionObject(3,"差"));
		return options;
	}
	
	public static List<OptionObject> getCust(HttpServletRequest request,int role){
		if(role==RoleConstants.SUPERVISORY.getCode()){
			UserVO user = UserSessionUtil.getUserSession(request).getUser();
			return dao.options(" t_zs_cust tc "
					+ " inner join t_distribset td on tc.custno = td.zscustno "
					+ " inner join market_dealer_supervisor mds on mds.dealerid = td.distribid", 
					"custNo", "pledgeName"," mds.repositoryid=? ",new Object[]{user.getClient_id()});
		}else{
			return dao.options("t_zs_cust", "custNo", "pledgeName");	
		}
	}
	public static List<OptionObject> getShouxinCust(HttpServletRequest request,int role){
		if(role==RoleConstants.SUPERVISORY.getCode()){
			UserVO user = UserSessionUtil.getUserSession(request).getUser();
			return dao.options(" t_zs_cust tc "
					+ " inner join t_distribset td on tc.custno = td.zscustno "
					+ " inner join market_dealer_supervisor mds on mds.dealerid = td.distribid", 
					"custNo", "pledgeName"," mds.repositoryid=? and CREDITFLAG = '1' ",new Object[]{user.getClient_id()});
		}else{
			return dao.options("t_zs_cust", "custNo", "pledgeName"," CREDITFLAG = '1'");
		}
	}
	public static Object getBankDockTypes() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		BankDockType[] bds = BankDockType.values();
		for (BankDockType bd : bds) {
			options.add(new OptionObject(bd.getCode(), bd.getName()));
		}
		return options;
	}
	public static Object getHandoverType() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		HandoverTypeContants[] bcs = HandoverTypeContants.values();
		for (HandoverTypeContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	public static Object getDataMailCostReceiverTypes() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		DataMailcostReceiverTypeContants[] bcs = DataMailcostReceiverTypeContants.values();
		for (DataMailcostReceiverTypeContants bc : bcs) {
			options.add(new OptionObject(bc.getCode(), bc.getName()));
		}
		return options;
	}
	
	//来电记录单来电类型
	public static List<OptionObject> getTelephoneTypes(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"业务问题"));
		options.add(new OptionObject(2,"市场问题"));
		options.add(new OptionObject(3,"风控问题"));
		options.add(new OptionObject(4,"投诉监管员 "));
		options.add(new OptionObject(5,"投诉总部人员 "));
		options.add(new OptionObject(6,"其他"));
		return options;
	}
	public static Object leaveType() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"事假"));
		options.add(new OptionObject(2,"病假"));
		options.add(new OptionObject(3,"倒休"));
		options.add(new OptionObject(4,"正休"));
		return options;
	}
	
	public static Object getCityType() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"一类"));
		options.add(new OptionObject(2,"二类"));
		options.add(new OptionObject(3,"特殊"));
		return options;
	}
	
	public static List<OptionObject> getQueryApprovalState(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		ApprovalContant[] acs = ApprovalContant.values();
		for (ApprovalContant ac : acs) {
			if(ac.getCode()==ApprovalContant.APPROVAL_NOT_SAVE.getCode()){
				continue;
			}
			options.add(new OptionObject(ac.getCode(), ac.getName()));
		}
		options.add(new OptionObject(-1, "全部"));
		return options;
	}
	
	public static List<OptionObject> getApprovalStates(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		ApprovalContant[] acs = ApprovalContant.values();
		for (ApprovalContant ac : acs) {
			if(ac.getCode()==ApprovalContant.APPROVAL_NOT_SUBMIT.getCode()){
				continue;
			}
			options.add(new OptionObject(ac.getCode(), ac.getName()));
		}
		return options;
	}
	public static Object getYears() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		String nowYear = DateUtil.getStringFormatByDate(new Date(), "yyyy");
		int j = Integer.parseInt(nowYear);
		for(int i = j; i >= j - 5; i--){
			options.add(new OptionObject(i,String.valueOf(i)));
		}
		return options;
	}
	public static Object getMonths() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		for(int i=1;i<=12;i++){
			options.add(new OptionObject(i,String.valueOf(i)));
		}
		return options;
	}
	public static Object getApprovalDepartments() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"监管员管理部"));
		options.add(new OptionObject(2,"业务部"));
		options.add(new OptionObject(3,"风控部"));
		options.add(new OptionObject(4,"视频部"));
		options.add(new OptionObject(5,"市场部"));
		return options;
	}
	public static List<OptionObject> carStatusOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"本库"));
		options.add(new OptionObject(2,"二库"));
		options.add(new OptionObject(3,"移动"));
		options.add(new OptionObject(4,"出库"));
		options.add(new OptionObject(5,"私自移动"));
		options.add(new OptionObject(6,"私自销售"));
		options.add(new OptionObject(7,"在途销售"));
		options.add(new OptionObject(8,"在途移动"));
		options.add(new OptionObject(9,"信誉额度"));
		options.add(new OptionObject(10,"展厅"));
		return options;
	}
	/**
	 * 盘点结果与实际库存差异 1:一致  2：差异
	 * @return
	 */
	public static Object checkStockResults() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"一致"));
		options.add(new OptionObject(2,"差异"));
		return options;
	}
	/**
	 * 车辆实际状态  1:本库  2：二库 3：移动 4：出库
	 * @return
	 */
	public static Object checkStockCarActualstate() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		options.add(new OptionObject(1,"本库"));
		options.add(new OptionObject(2,"二库"));
		options.add(new OptionObject(3,"移动"));
		options.add(new OptionObject(4,"出库"));
		return options;
	}
	/**
	 * 省份 -- 20170512
	 * @return
	 */
	public static List<OptionObject> getRegionProvince() {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_region t where t.regionlevel = 1 ");
		return dao.options(sql.toString(), "t.id","t.name");
	}
	/**
	 * 全部市 -- 20170512
	 * @return
	 */
	public static List<OptionObject> getRegionCity() {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_region t where t.regionlevel = 2 ");
		return dao.options(sql.toString(), "t.id","t.name");
	}
	/**
	 * 全部区 -- 20170512
	 * @return
	 */
	public static List<OptionObject> getRegionArea() {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_region t where t.regionlevel = 3 ");
		return dao.options(sql.toString(), "t.id","t.name");
	}
	/**
	 * 根据省获取市 -- 20170512
	 * @return
	 */
	public static List<OptionObject> getCitys(String parentId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_region t where t.parentid = "+Integer.valueOf(parentId));
		return dao.options(sql.toString(), "t.id","t.name");
	}
	/**
	 * 获取合作状态中的经销商 -- 20170518
	 * @return
	 */
	/*public static List<OptionObject> getDealers(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct md.id, md.dealername from t_supervise_import t ");
		sql.append(" left join t_draft td on t.draft_num = td.draft_num  ");
		sql.append(" left join market_dealer md on md.id = td.distribid ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerid=md.id ");
		sql.append(" inner join t_yw_bank ty on mds.bankid = ty.bankid ");
		sql.append(" where ty.userid="+userId+" ");
		sql.append(" and md.cooperationstate = 1 ");
		return dao.options(sql.toString(), "md.id", "md.dealername");
		
	}*/
	/**
	 * 根据经销商id获取票号 -- 20170518
	 * @return
	 */
	public static List<OptionObject> getDraftNums(int dealerId){
		StringBuffer sql = new StringBuffer();
		sql.append(" t_draft d where d.distribid="+dealerId);
		return dao.options(sql.toString(), "d.id", "d.draft_num");
	}
	/**
	 * 品牌根据拼音排序 -- 20170519
	 * @return
	 */
	public static Object getBrandsByPinYin() {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_brand b order by nlssort(b.name,'NLS_SORT=SCHINESE_PINYIN_M')");
		return dao.options(sql.toString(), "b.id", "b.name");
	}
	/**
	 * 监管员 -- 20170524
	 * @return
	 */
	public static Object getClientTypeOptions() {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_rbac_role where client_type=3 and id=10");
		return dao.options(sql.toString(), "id", "role_name");
	}
	/*
	 * 需求57 交付人-下拉框应该显示全部监管员
	 * @time 20170526
	*/
	public static List<OptionObject> getAllSupervisors() {
		StringBuffer sql = new StringBuffer();
		sql.append(" T_REPOSITORY t left join t_supervisor_basic_information s on t.supervisorid = s.id");
		return dao.options(sql.toString(), "t.id", "s.name");
	}
	/*
	 * 需求139 -- 显示与品牌集团合作的经销商
	*/
	public static Object getBrandMasterDealers(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" t_two_address ta ");
		sql.append(" left join market_dealer md on md.id = ta.distribid ");
		sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid = md.brandId ");
		sql.append(" left join t_rbac_brandgroup_user bu on bu.groupid = bb.groupid ");
		sql.append(" where bu.userid = "+id+" and md.cooperationstate = 1 ");
		sql.append(" group by  md.id,md.dealername,md.cooperationstate ");
		return dao.options(sql.toString(),  "md.id","md.dealername");
	}
	/*
	 * 需求86 -- 审批状态
	*/
	public static List<OptionObject> getSQueryApprovalState(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		ApprovalContant[] acs = ApprovalContant.values();
		for (ApprovalContant ac : acs) {
			if(ac.getCode()==ApprovalContant.APPROVAL_NOT_SAVE.getCode()){
				continue;
			}
			options.add(new OptionObject(ac.getCode(), ac.getName()));
		}
		options.add(new OptionObject(-2, "全部"));
		return options;
	}
	//银行关联经销商
	public static Object getDealersByClientId(int client_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" market_dealer md ");
		sql.append(" left join market_dealer_bank mdb on md.id = mdb.dealerid ");
		sql.append(" left join t_bank tb on mdb.bankid = tb.id ");
		sql.append(" left join t_rbac_user tru on tb.id = tru.client_id ");
		sql.append(" where ( tru.client_id = "+client_id);
		sql.append(" or tru.client_id in (select bcm.childrenid from t_bank_children_manager bcm where bcm.parentid = "+client_id);
		sql.append(" ))");
		
		return dao.options(sql.toString(),  "md.id","md.dealername");
	}
	//查询监管员绑定的经销商
	public static Object getDealersBySUPERVISOR(int client_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("  market_dealer md ");
		sql.append(" left join MARKET_DEALER_SUPERVISOR mds on mds.dealerid = md.id ");
		sql.append(" left join t_repository r on r.id = mds.repositoryid");
		//sql.append(" left join T_SUPERVISOR_BASIC_INFORMATION tsb on tsb.id =  r.supervisorid ");
		sql.append(" where r.id= "+client_id);
		System.out.println("根据监管员获取绑定经销商："+sql.toString());
		return dao.options(sql.toString(),  "md.id","md.dealername");
	}
}

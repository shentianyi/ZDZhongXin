package com.zd.csms.marketing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.csms.bank.model.BankQueryVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.marketing.contants.MarketProjectCirculationContant;
import com.zd.csms.marketing.contants.PayModeContant;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.IDealerSupervisorDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.ExtDealerVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.querybean.DealerBankQueryBean;
import com.zd.csms.marketing.querybean.DealerBusinessQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.DealerSupervisorBean;
import com.zd.csms.marketing.querybean.ExpenseTotalQueryBean;
import com.zd.csms.marketing.querybean.SelectDealerBean;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;
import com.zd.csms.set.model.DistribsetQueryVO;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商名录表
 * @author licheng
 *
 */

public class DealerService extends ServiceSupport{
	IDealerDAO dao = MarketFactory.getIDealerDAO();
	IBankDAO bankDao = BankDAOFactory.getBankDAO();
	RegionService regionService=new RegionService();
	private IDealerSupervisorDAO dsDao = MarketFactory.getDealerSupervisorDAO();
	IDistribsetDAO disDao = SetDAOFactory.getDistribsetDAO();
	
	public double getPaymentMoney(double money,int mode){
		if(money<=0)
			return 0;
		if(mode==PayModeContant.PAYMODE_MONTH.getCode()){
			return money;
		}else if(mode==PayModeContant.PAYMODE_QUARTER.getCode()){
			return money/3;
		}else if(mode==PayModeContant.PAYMODE_SIXMONTH.getCode()){
			return money/6;
		}else if(mode==PayModeContant.PAYMODE_YEAR.getCode()){
			return money/12;
		}
		return 0;
	}
	
	public boolean update(DealerVO dealer){
		return dao.update(dealer);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<DealerQueryBean> findList(DealerQueryVO query,IThumbPageTools tools) throws Exception{
		List<DealerVO> list = dao.findList(query, tools);
		return transformData(query, list);
	}

	private List<DealerQueryBean> transformData(DealerQueryVO query, List<? extends DealerVO> list) throws Exception {
		List<DealerQueryBean> querBeanList =  new ArrayList<DealerQueryBean>();
		List<Integer> ids = DealerVOToDealerQueryBean(list,querBeanList);
		query.setIds(ids.toArray(new Integer[0]));
		List<DealerBankVO> dealerBanks =  dao.findBankListByIds(query);
		Map<Integer, List<DealerBankVO>> dealerBankMaps = DealerBankListToMap(dealerBanks);
		//BankQueryVO bankQuery = new BankQueryVO();
		
		for (DealerQueryBean queryBean : querBeanList) {
			List<DealerBankVO> banks = dealerBankMaps.get(queryBean.getId());
			StringBuffer bankName = new StringBuffer();
			if(banks!=null){
				for (DealerBankVO bank : banks) {
					BankVO bankVO = bankDao.get(BankVO.class, bank.getBankId(), new BeanPropertyRowMapper(BankVO.class));
					if(bankVO!=null)
						bankName.append(bankVO.getBankFullName()+",");
				}
				if(bankName.length()>0)
					bankName.deleteCharAt(bankName.length()-1);
			}
			queryBean.setBankName(bankName.toString());
			//System.out.println("金融机构ID"+queryBean.getBankManagerId());
			BankManagerVO bankManager = dao.get(BankManagerVO.class, queryBean.getBankManagerId(), new BeanPropertyRowMapper(BankManagerVO.class)) ;
			if(bankManager!=null){
//				20170513
				queryBean.setBankManagerId(bankManager.getId());
				queryBean.setBankId(String.valueOf(bankManager.getBankId()));
				queryBean.setBankContact(bankManager.getManager());
				queryBean.setBankPhone(bankManager.getManagerPhone());
			}

			queryBean.setProvince(regionService.getNameById(StringUtil.intValue(queryBean.getProvince(), 0)));
			queryBean.setCity(regionService.getNameById(StringUtil.intValue(queryBean.getCity(), 0)));
			
			
			//将监管模式由数字变成汉字
			String supervisionMode = queryBean.getSupervisionMode();
			if(supervisionMode!=null){
				StringBuffer supervisionModeStr = new StringBuffer();
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.HDXK.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.HDXK.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.JRJGK.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.JRJGK.getName()+",");
				if(supervisionModeStr.length()>0)
					supervisionModeStr.deleteCharAt(supervisionModeStr.length()-1);
				queryBean.setSupervisionMode(supervisionModeStr.toString());
			}
			if(queryBean.getBrandId()>0){
				BrandVO brand = dao.get(BrandVO.class,queryBean.getBrandId(), new BeanPropertyRowMapper(BrandVO.class));
				if(brand!=null){
					queryBean.setBrand(brand.getName());
				}
			}
			DealerSupervisorBean dsBean = dsDao.getSupervisorNameByDealerId(queryBean.getId());
			if(dsBean!=null){
				queryBean.setSuperviseName(dsBean.getName());
				queryBean.setSupervisePhone(dsBean.getSelftelePhone());
			}
			if(StringUtil.isNotEmpty(queryBean.getBindInfo())){
				queryBean.setBindInfo(dao.findBindNameByIds(queryBean.getBindInfo()));
			}
			
			//监管费
			queryBean.setSuperviseMoney(getMoneyByClientId(queryBean.getSuperviseMoney(), query.getCurrClientType()));
			//变更前监管费标准
			queryBean.setChangeBeforeInfo(getChangeBeforeInfoByClientId(queryBean.getChangeBeforeInfo(), query.getCurrClientType()));
			
			
			DistribsetQueryVO disQuery = new DistribsetQueryVO();
			disQuery.setDistribid(queryBean.getId());
			List<DistribsetVO> disList = disDao.searchDistribsetList(disQuery);
			if(disList!=null){
				for (DistribsetVO disVO : disList) {
					if(StringUtil.isNotEmpty(disVO.getMovePerc())){
						queryBean.setYdbl(disVO.getMovePerc()+"%");
					}
				}
			}
		}
		
		return querBeanList;
	}
	
	public DealerQueryBean detailInfo(int id,int currClientType) throws Exception{
		DealerQueryVO query = new DealerQueryVO();
		List<DealerVO> list = new ArrayList<DealerVO>();
		list.add(get(id));
		query.setCurrClientType(currClientType);
		List<DealerQueryBean> resultList = transformData(query, list);
		if(resultList!=null&&resultList.size()>0){
			DealerQueryBean bean = resultList.get(0);
			return bean;
		}else
			return null;
	}
	
	/**
	 * 查询该交监管费的经销商
	 * @return
	 */
	public List<DealerVO> findListByPaymentDate(){
		return dao.findListByPaymentDate();
	}
	
	
	public List<DealerQueryBean> findDealerList(DealerQueryVO query) throws Exception{
		List<DealerVO> list = dao.findList(query);
		List<DealerQueryBean> querBeanList =  new ArrayList<DealerQueryBean>();
		List<Integer> ids = DealerVOToDealerQueryBean(list,querBeanList);
		query.setIds(ids.toArray(new Integer[0]));
		List<DealerBankVO> dealerBanks =  dao.findBankListByIds(query);
		Map<Integer, List<DealerBankVO>> dealerBankMaps = DealerBankListToMap(dealerBanks);
		//BankQueryVO bankQuery = new BankQueryVO();
		
		for (DealerQueryBean queryBean : querBeanList) {
			List<DealerBankVO> banks = dealerBankMaps.get(queryBean.getId());
			StringBuffer bankName = new StringBuffer();
			if(banks!=null){
				for (DealerBankVO bank : banks) {
					BankVO bankVO = bankDao.get(BankVO.class, bank.getBankId(), new BeanPropertyRowMapper(BankVO.class));
					if(bankVO!=null)
						bankName.append(bankVO.getBankFullName()+",");

				}
				if(bankName.length()>0)
					bankName.deleteCharAt(bankName.length()-1);
			}
			queryBean.setBankName(bankName.toString());
			
			BankManagerVO bankManager = dao.get(BankManagerVO.class, queryBean.getBankManagerId(), new BeanPropertyRowMapper(BankManagerVO.class)) ;
			if(bankManager!=null){
				queryBean.setBankContact(bankManager.getManager());
				queryBean.setBankPhone(bankManager.getManagerPhone());
			}
			//将监管模式由数字变成汉字
			String supervisionMode = queryBean.getSupervisionMode();
			if(supervisionMode!=null){
				StringBuffer supervisionModeStr = new StringBuffer();
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.HDXK.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.HDXK.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.JRJGK.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.JRJGK.getName()+",");
				if(supervisionModeStr.length()>0)
					supervisionModeStr.deleteCharAt(supervisionModeStr.length()-1);
				queryBean.setSupervisionMode(supervisionModeStr.toString());
			}
		}
		
		return querBeanList;
	}
	
	public List<DealerQueryBean> findDealersList(DealerQueryVO query) throws Exception{
		List<DealerVO> list = dao.findDealersList(query);
		List<DealerQueryBean> querBeanList =  new ArrayList<DealerQueryBean>();
		List<Integer> ids = DealerVOToDealerQueryBean(list,querBeanList);
		query.setIds(ids.toArray(new Integer[0]));
		List<DealerBankVO> dealerBanks =  dao.findBankListByIds(query);
		Map<Integer, List<DealerBankVO>> dealerBankMaps = DealerBankListToMap(dealerBanks);
		//BankQueryVO bankQuery = new BankQueryVO();
		
		for (DealerQueryBean queryBean : querBeanList) {
			List<DealerBankVO> banks = dealerBankMaps.get(queryBean.getId());
			StringBuffer bankName = new StringBuffer();
			if(banks!=null){
				for (DealerBankVO bank : banks) {
					BankVO bankVO = bankDao.get(BankVO.class, bank.getBankId(), new BeanPropertyRowMapper(BankVO.class));
					if(bankVO!=null)
						bankName.append(bankVO.getBankFullName()+",");

				}
				if(bankName.length()>0)
					bankName.deleteCharAt(bankName.length()-1);
			}
			queryBean.setBankName(bankName.toString());
			
			BankManagerVO bankManager = dao.get(BankManagerVO.class, queryBean.getBankManagerId(), new BeanPropertyRowMapper(BankManagerVO.class)) ;
			if(bankManager!=null){
				queryBean.setBankContact(bankManager.getManager());
				queryBean.setBankPhone(bankManager.getManagerPhone());
			}
			//将监管模式由数字变成汉字
			String supervisionMode = queryBean.getSupervisionMode();
			if(supervisionMode!=null){
				StringBuffer supervisionModeStr = new StringBuffer();
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.HDXK.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.HDXK.getName()+",");
				if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.JRJGK.getCode()+""))
					supervisionModeStr.append(MarketProjectCirculationContant.JRJGK.getName()+",");
				if(supervisionModeStr.length()>0)
					supervisionModeStr.deleteCharAt(supervisionModeStr.length()-1);
				queryBean.setSupervisionMode(supervisionModeStr.toString());
			}
			
			
		}
		
		return querBeanList;
	}
	
	//这方法是谁写的
	public List<DealerVO> findList(DealerQueryVO query) throws Exception{
		return dao.findList(query);
	}
	
	
	private Map<Integer, List<DealerBankVO>> DealerBankListToMap(List<DealerBankVO> dealerBanks){
		Map<Integer, List<DealerBankVO>> map = new HashMap<Integer, List<DealerBankVO>>();
		if(dealerBanks!=null)
			for (DealerBankVO db : dealerBanks) {
				int id = db.getDealerId();
				if(map.containsKey(id)){
					List<DealerBankVO>  temp = map.get(id);
					temp.add(db);
				}else{
					List<DealerBankVO>  temp = new ArrayList<DealerBankVO>();
					temp.add(db);
					map.put(id, temp);
				}
			}
		return map;
	}
	

	
	private List<Integer> DealerVOToDealerQueryBean(List<? extends DealerVO> fromList,List<DealerQueryBean> targetList){
		List<Integer> ids = new ArrayList<Integer>();
		for (DealerVO bean : fromList) {
			DealerQueryBean queryBean = new DealerQueryBean();
			BeanUtils.copyProperties(bean, queryBean);
			targetList.add(queryBean);
			ids.add(bean.getId());
		}
		return ids;
	}
	
	public DealerVO loadDealerById(int id) throws Exception{
		DealerVO dealerVO = dao.get(DealerVO.class, id,new BeanPropertyRowMapper(DealerVO.class));
		return dealerVO;
	}
	
	public DealerVO get(int id){
		return dao.get(DealerVO.class, id, new BeanPropertyRowMapper(DealerVO.class));
	}
	
	/**
	 * 根据主键查询银行列表
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<DealerBankVO> findBankListByIds(DealerQueryVO query) throws Exception{
		return dao.findBankListByIds(query);
	}
	
	/**
	 * 根据经销商Id获取所有银行的名称
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getBankNameByDealerId(int id) throws Exception{
		DealerQueryVO query = new DealerQueryVO();
		BankQueryVO bankQuery = new BankQueryVO();
		query.setIds(new Integer[]{id});
		List<DealerBankVO> banks = findBankListByIds(query);
		StringBuffer bankName = new StringBuffer();
		if(banks!=null){
			for (DealerBankVO bank : banks) {
				bankQuery.setId(bank.getBankId());
				bankName.append(bankDao.findBankNameById(bankQuery).replace(",", "/")+",");
			}
			bankName.deleteCharAt(bankName.length()-1);
		}
		return bankName.toString();
	}
	
	public BankVO getBankByDealerId(Integer...ids) throws Exception{
		BankVO bankVO = new BankVO();
		
		DealerQueryVO query = new DealerQueryVO();
		BankQueryVO bankQuery = new BankQueryVO();
		query.setIds(ids);
		List<DealerBankVO> banks = findBankListByIds(query);
		StringBuffer bankName = new StringBuffer();
		if(banks!=null){
			for (DealerBankVO bank : banks) {
				BankVO bankVo = bankDao.get(BankVO.class, bank.getBankId(), new BeanPropertyRowMapper(BankVO.class));
				bankQuery.setId(bank.getBankId());
				if(bankVo!=null){
					bankName.append(bankVo.getBankFullName()+",");
				}
			}
			if(bankName.length()>0){
				bankName.deleteCharAt(bankName.length()-1);
			}
		}
		bankVO.setBankName(bankName.toString());

		return bankVO;
	}
	
	
	/**
	 * 监管费用统计分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<ExpenseTotalQueryBean> expenseTotalList(DealerQueryVO query,IThumbPageTools tools){
		return dao.expenseTotalList(query, tools);
	}
	
	public List<DealerVO> searchDealerList(DealerQueryVO query)throws Exception{
		return dao.findDealerList(query);
	}
	
	public List<DealerVO> searchDealerqcList()throws Exception{
		return dao.findDealerqcList();
	}
	
	/**
	 * 弹窗选择
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<SelectDealerBean> selectDealerList(DealerQueryVO query,IThumbPageTools tools){
		
		List<SelectDealerBean> list =  dao.selectDealerList(query, tools);
		for (SelectDealerBean bean : list) {
			bean.setBindInfo(bean.getBindShopName());
			if(StringUtil.isNotEmpty(bean.getBindShopName())){
				bean.setBindShopName(dao.findBindNameByIds(bean.getBindShopName()));
			}
		}
		return list;
				
	}
	
	public String getBindNameById(String ids){
		return dao.findBindNameByIds(ids);
	}
	
	/**
	 * 提前一个月预警余额不足的的经销商
	 * @return
	 */
	public List<DealerVO> findListByArrearsRemind(){
		return dao.findListByArrearsRemind();
	}
	
	public List<DealerVO> findListByYW(int userid){
		return dao.findListByYW(userid);
	}
	
	/**
	 * 只有市场部 市场管理部和总监可以查看监管费
	 * @param superviseMoney
	 * @param clientId
	 * @return
	 */
	public String getMoneyByClientId(String superviseMoney,int clientType){
		if(clientType == ClientTypeConstants.MARKET.getCode()
				|| clientType == ClientTypeConstants.MARKETMANAGEMENT.getCode()
				|| clientType == ClientTypeConstants.LOGISTICSFINANCECENTER.getCode()){
			return superviseMoney;
		}else{
			return "*****";
		}
		
	}
	
	/**
	 * 只有市场部 市场管理部和总监可以查看变更前监管费标准
	 * @param superviseMoney
	 * @param clientId
	 * @return
	 */
	public String getChangeBeforeInfoByClientId(String changeBeforeInfo,int clientType){
		if(clientType == ClientTypeConstants.MARKET.getCode()
				|| clientType == ClientTypeConstants.MARKETMANAGEMENT.getCode()
				|| clientType == ClientTypeConstants.LOGISTICSFINANCECENTER.getCode()){
			return changeBeforeInfo;
		}else{
			return "*****";
		}
		
	}
	
	
	/**
	 * 根据监管员储备库Id查询经销商
	 * @param id
	 * @return
	 */
	public List<DealerBankQueryBean> findDealerListByRepId(int id){
		return dao.findDealerListByRepId(id);
	}
	
    /**
     * 经销商名录表：业务部 
     * @param query
     * @param tools
     * @return
     * @throws Exception 
     */
	public List<DealerQueryBean> findBusinessList(DealerQueryVO query,
			IThumbPageTools tools) throws Exception {
		List<DealerBusinessQueryBean> list = dao.findBusinessList(query, tools);
		return transformData(query, list);
	}
    
	/**
	 * 经销商名录表：业务部 -导出
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	public List<DealerQueryBean> findBusinessForExcel(DealerQueryVO query) throws Exception {
		List<DealerBusinessQueryBean> list = dao.findBusinessForExcel(query);
		return transformData(query, list);
	}

	/**
	 * 经销商名录表：市场部 -导出
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	public List<DealerQueryBean> findListForExcel(DealerQueryVO query) throws Exception {
		List<DealerVO> list = dao.findListForExcel(query);
		return transformData(query, list);
	}

	

	/*
	 * 经销商名录表（业务）修改入口
	 * 需求53 -- 20170511 -- cym
	 * */
	public DealerBusinessQueryBean dealerModify(DealerQueryVO query) {
		return dao.dealerModify(query);
	}

	/*
	 * 修改DealerVO数据
	 * @time 20170512
	*/
	public int updateDealer(DealerQueryBean query) {
		return dao.updateDealer(query);
	}
	/*
	 * 修改BankManagerVO数据
	 * @time 20170513
	*/
	public int updateBankManagerInfo(DealerQueryBean query) {
		return dao.updateBankManagerInfo(query);
	}
	/*
	 * 修改合作状态
	 * @time 20170513
	*/
	public boolean updateStopCooperationState(DealerQueryBean query) {
		return dao.updateStopCooperationState(query);
	}

	/*
	 * 导出监管费台账 
	 * com.zd.csms.ledger.DealerAction
	 * @time 20170518
	*/
	public List<ExpenseTotalQueryBean> ExtexpenseTotalLedger(DealerQueryVO query) {
		return dao.ExtexpenseTotalLedger(query);
	}

	/*
	 * 需求38 -- 导出监管费管理台账 
	 * com.zd.csms.marketing.web.action.DealerAction
	 * @time 20170519
	*/
	public List<ExpenseTotalQueryBean> ExtExpenseTotalList(DealerQueryVO query) {
		return dao.ExtExpenseTotalList(query);
	}

	/*
	 * 需求38 
	 * 导出经销商考勤时间台账 
	*/
	public List<ExtDealerVO> ExtFindDealerList(DealerQueryVO query) {
		return dao.ExtFindDealerList(query);
	}

	public List<ModeChangeLogVO> findModeChangeLogVOById(int id) {
		 List<ModeChangeLogVO> list = dao.findModeChangeLogVOById(id);
		 for (ModeChangeLogVO vo : list) {
				//将监管模式由数字变成汉字
				String supervisionMode = vo.getBeforeOperationMode();
				if(supervisionMode!=null){
					StringBuffer supervisionModeStr = new StringBuffer();
					if(supervisionMode.contains(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()+",");
					if(supervisionMode.contains(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()+""))
							supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()+",");
					if(supervisionMode.contains(MarketProjectCirculationContant.HDXK.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.HDXK.getName()+",");
					if(supervisionMode.contains(MarketProjectCirculationContant.JRJGK.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.JRJGK.getName()+",");
					if(supervisionModeStr.length()>0)
						supervisionModeStr.deleteCharAt(supervisionModeStr.length()-1);
					vo.setBeforeOperationMode(supervisionModeStr.toString());
				}
				
				supervisionMode = vo.getLastOperationMode();
				if(supervisionMode!=null){
					StringBuffer supervisionModeStr = new StringBuffer();
					if(supervisionMode.contains(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()+",");
					if(supervisionMode.contains(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()+""))
							supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()+",");
					if(supervisionMode.contains(MarketProjectCirculationContant.HDXK.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.HDXK.getName()+",");
					if(supervisionMode.contains(MarketProjectCirculationContant.JRJGK.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.JRJGK.getName()+",");
					if(supervisionModeStr.length()>0)
						supervisionModeStr.deleteCharAt(supervisionModeStr.length()-1);
					vo.setLastOperationMode(supervisionModeStr.toString());
				}
		}
	
		 
		 return list;
	}

	
}

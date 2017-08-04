package com.zd.csms.zxbank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.marketing.dao.IAgreementBackDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.service.AgreementBackService;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.IAgreementDAO;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.csms.zxbank.web.bean.AgreementFar;
import com.zd.csms.zxbank.web.bean.FinancingFar;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 监管协议Service
 */
public class AgreementService extends ServiceSupport {
	private IAgreementDAO dao = ZXBankDAOFactory.getAgreementDAO();
	private ICustomerDAO cdao = ZXBankDAOFactory.getcustDAO();
	private IDistribsetDAO ddao = SetDAOFactory.getDistribsetDAO();
	private AgreementBackService as = new AgreementBackService();
	
	private static Log log = LogFactory.getLog(AgreementService.class);

	public List<Agreement> findBusinessList(Agreement query, IThumbPageTools tools) {
		return dao.firnAllAgList(query, tools);
	}

	// 远程数据与本地数据比对
	public boolean autoUpdateCust(List<AgreementFar> bankList) throws Exception {
		List<Agreement> list = dao.query();
		AgreementBackVO agrVO = new AgreementBackVO();
		if (bankList != null && bankList.size() > 0 && list != null){
			this.transactionBegin(dao.getDataSourceName());//开启
			for (AgreementFar agreementFar : bankList) {
				int tem = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getSpvagtid().trim().equals(agreementFar.getSpvAgtId().trim())) {
						if (update(agreementFar)) {
							AgreementBackQueryVO query = new AgreementBackQueryVO();
							query.setAgreement_num(agreementFar.getSpvAgtId());
							List<AgreementBackVO> lstagvo=as.searchAgreementBackList(query);
//							if(lstagvo!=null||lstagvo.size()>0){//业务系统和本地数据不一致导致
								AgreementBackVO agtVO =lstagvo.get(0);
								agtVO.setAgreement_num(agreementFar.getSpvAgtId());
								agtVO.setAgreementsigntime(DateUtil.getDateFormatByString(agreementFar.getStartDate(),"yyyyMMdd"));//协议签署日期
								agtVO.setAgreementexpiretime(DateUtil.getDateFormatByString(agreementFar.getEndDate(), "yyyyMMdd"));//协议到期日
								agtVO.setIsback(Integer.parseInt(agreementFar.getAgtStt()));
								
								if(as.updAgreementBack(agtVO)){
									log.info("业务系统更新协议结果成功["+agreementFar.getSpvAgtId()+"]");
								}else{
									log.info("业务系统更新协议结果失败");
									log.info("失败回滚结果:["+this.transactionRollback(dao.getDataSourceName())+"]");//回滚
									return false;
								}
								
//							}else{
//								log.error("系统无相关协议"+agreementFar.getSpvAgtId()+"信息，更新失败");
//							}
						}
						tem++;
						break;
					}
				}
				if (tem == 0) {
					if (add(agreementFar)) {
						//业务系统协议保存
						Customer customer=cdao.findByNo(agreementFar.getHostNo());
						if(customer!=null){
							int did  =  ddao.findDistidByOrg(customer.getCustOrganizationcode());
							AgreementBackVO addAGT = new AgreementBackVO();
							addAGT.setDistribid(did);//经销商id
							addAGT.setAgreement_num(agreementFar.getSpvAgtId());//协议编号
							addAGT.setAgreementsigntime(DateUtil.getDateFormatByString(agreementFar.getStartDate(),"yyyyMMdd"));//协议签署日期
							addAGT.setAgreementexpiretime(DateUtil.getDateFormatByString(agreementFar.getEndDate(), "yyyyMMdd"));//协议到期日
							addAGT.setCreatedate(new Date());
							addAGT.setUpddate(new Date());
							addAGT.setIsback(Integer.parseInt(agreementFar.getAgtStt()));
							if(as.addAgreementBack(addAGT)){
								log.info("业务系统保存协议结果成功["+agreementFar.getSpvAgtId()+"]");
							}else{
								log.info("业务系统保存协议结果失败");
								log.info("失败回滚结果:["+this.transactionRollback(dao.getDataSourceName())+"]");//回滚
								return false;
							}
						}else{
							log.info("未查到"+agreementFar.getHostNo()+"相关的客户信息");
							log.info("失败回滚结果:["+this.transactionRollback(dao.getDataSourceName())+"]");//回滚
							return false;
						}
						
					}
				}
			}
			log.info("成功提交结果["+this.transactionCommit(dao.getDataSourceName())+"]");//提交
			return true;
		}else{
			return false;
		}
		
	}

	public boolean add(AgreementFar agtFar) {
		Agreement agt = new Agreement();
		agt.setAgId(SqlUtil.getID(Agreement.class));
		agt.setAgcreatedate(new Date());
		agt.setEnddate(agtFar.getEndDate());
		agt.setIsmv(agtFar.getIsMv());
		agt.setIsauth(agtFar.getIsAuth());
		agt.setHostno(agtFar.getHostNo());
		agt.setLonnm(agtFar.getLonNm());
		agt.setOperorg(agtFar.getOperOrg());
		agt.setSpvagtno(agtFar.getSpvAgtNo());
		agt.setSpvagtid(agtFar.getSpvAgtId());
		agt.setAgtstt(agtFar.getAgtStt());
		agt.setStartdate(agtFar.getStartDate());
		agt.setTotnum(agt.getTotnum());//总记录条数         待更改
		agt.setAgloncpid(agtFar.getHostNo());
		return dao.add(agt);
	}

	public boolean update(AgreementFar agtFar) {
		Agreement agt = new Agreement();
		agt.setEnddate(agtFar.getEndDate());
		agt.setIsmv(agtFar.getIsMv());
		agt.setIsauth(agtFar.getIsAuth());
		agt.setHostno(agtFar.getHostNo());
		agt.setLonnm(agtFar.getLonNm());
		agt.setOperorg(agtFar.getOperOrg());
		agt.setSpvagtno(agtFar.getSpvAgtNo());
		agt.setSpvagtid(agtFar.getSpvAgtId());
		agt.setAgtstt(agtFar.getAgtStt());
		agt.setStartdate(agtFar.getStartDate());
		agt.setAgupdatedate(new Date());
		agt.setTotnum(agt.getTotnum());//待 更改
		agt.setAgloncpid(agtFar.getHostNo());
		return dao.update(agt);
	}

	public Agreement findByNo(String no) {
		return dao.getAgreement(no);
	}
	
	
	/**
	 * 解析的dat文件的结果List<List<String>>转List<AgreementFar>
	 * @param xmlList
	 * @return
	 */
	public List<AgreementFar> ConversionList(List<List<String>> xmlList){
		List<AgreementFar> list=new ArrayList<AgreementFar>();
		for (List<String> list2 : xmlList) {
			if(list2.size()>0){
				log.info("集合长度："+list2.size());
				if(list2.size()==10){
					AgreementFar agrFar=new AgreementFar();
					//根据内容设置值
					agrFar.setHostNo(list2.get(0));
					agrFar.setLonNm(list2.get(1));
					agrFar.setSpvAgtId(list2.get(2));
					agrFar.setSpvAgtNo(list2.get(3));
					agrFar.setAgtStt(list2.get(4));
					agrFar.setStartDate(list2.get(5));
					agrFar.setEndDate(list2.get(6));
					agrFar.setIsAuth(list2.get(7));
					agrFar.setIsMv(list2.get(8));
					agrFar.setOperOrg(list2.get(9));
					list.add(agrFar);
				}else{
					log.error("日终批量文件数据内容格式错误，解析出错");
				}
			}
		}
		return list;
	}
	/**
	 * 输入框下拉选项列表
	 * @return
	 */
	public List<OptionObject> draftsOptions(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts = dao.getListNtcno();
		if(drafts != null && drafts.size() > 0){
			for(String draftNum : drafts){
				option = new OptionObject(draftNum,draftNum);
				options.add(option);
			}
		}
		return options;
	}
	
	
	
	
	
}

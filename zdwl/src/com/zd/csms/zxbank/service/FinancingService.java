package com.zd.csms.zxbank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.code.constants.BusinessCodeConstants;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IDraftDAO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.csms.zxbank.constants.BusinessConstants;
import com.zd.csms.zxbank.constants.CreditProductsConstants;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.dao.IFinancingDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.csms.zxbank.web.bean.FinancingFar;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 融资信息Service
 */
public class FinancingService extends ServiceSupport {
	private static Log log = LogFactory.getLog(FinancingService.class);
	private IFinancingDAO fidao = ZXBankDAOFactory.getFinancingDAO();
	private IDraftDAO ddao = BusinessDAOFactory.getDraftDAO();
	private IDistribsetDAO didao = SetDAOFactory.getDistribsetDAO();
	private ICustomerDAO idao = ZXBankDAOFactory.getcustDAO();
	private BusinessConstants[] bcvalues=BusinessConstants.values();
	private CreditProductsConstants[] cpcvalues=CreditProductsConstants.values();
	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools) {
		return fidao.findByQuery(query, tools);
	}

	public Financing getFinancing(String code) {
		return fidao.getFinancing(code);
	}

	public boolean update(Financing financing) {
		return fidao.update(financing);
	}
	
	public List<Financing> getFinancingList(){
		return fidao.getFinancingList();
	}

	public boolean addOrUpdate(List<FinancingFar> list) {
		Financing fg = new Financing();
		DraftVO draft = new DraftVO();
		log.info("事务开启结果：["+this.transactionBegin(fidao.getDataSourceName())+"]");//开启事务
		for (FinancingFar ffar : list) {
			//接口表更新或添加
			fg.setFgStDate(ffar.getLoanstDate());
			fg.setFgEndDate(ffar.getLoanendDate());
			fg.setFgBailRat(ffar.getBailRat());
			fg.setFgFstblRat(ffar.getFstblRat());
			fg.setFgLoanAmt(ffar.getLoanAmt());
			fg.setFgLoanCode(ffar.getLoanCode());
			fg.setFgOperOrg(ffar.getOperOrg());
			fg.setFgScftxNo(ffar.getScftxNo());
			fg.setFgSlfcap(ffar.getSlfcapRat());
			fg.setFgLonentNo(ffar.getLonEntId());// 借款企业id
			fg.setFgLoncpName(ffar.getLonEntNm());// 借款企业名称
//			fg.setFgBizMod(ffar.getBizMod());//业务模式
//			fg.setFgProcrt(ffar.getProcrt());//授信产品，
			for (BusinessConstants bcs  : bcvalues) {
				if(ffar.getBizMod().equals(bcs.getName())){
					fg.setFgBizMod(bcs.getCode()+"");//业务模式
				}
				if(ffar.getBizMod().equals(bcs.getCode()+"")){
					fg.setFgBizMod(bcs.getCode()+"");//业务模式
				}
			}
			for (CreditProductsConstants cpcs : cpcvalues) {
				if(ffar.getProcrt().equals(cpcs.getName())){
					fg.setFgProcrt(cpcs.getCode()+"");//授信产品，
				}
				if(ffar.getProcrt().equals(cpcs.getCode()+"")){
					fg.setFgProcrt(cpcs.getCode()+"");//授信产品，
				}
			}
			int count = fidao.getCountByOther(ffar.getLoanCode());
			if (count > 0) {
				fg.setFgUpdateDate(new Date());
				if(fidao.update(fg)){
					log.info("融资信息更新结果成功["+ffar.getLoanCode()+"]");
				}else{
					log.info("融资信息更新结果失败");
					log.error("失败回滚结果：["+this.transactionRollback(fidao.getDataSourceName())+"]");//回滚
					return false;
				}
			} else {
				fg.setFgCreateDate(new Date());
				fg.setFgUpdateDate(new Date());
				if(fidao.add(fg)){
					log.info("融资信息添加结果成功["+ffar.getLoanCode()+"]");
				}else{
					log.info("融资信息添加结果失败");
					log.error("失败回滚结果：["+this.transactionRollback(fidao.getDataSourceName())+"]");//回滚
					return false;
				}
			}
			Customer customer=idao.findByNo(ffar.getLonEntId());
			String orgcode ="";
			if(customer!=null){
				orgcode=customer.getCustOrganizationcode();
			}else{
				log.info("未查到"+ffar.getLonEntId()+"相关的客户信息");
				log.error("失败回滚结果：["+this.transactionRollback(fidao.getDataSourceName())+"]");//回滚
				return false;
			}

			//业务系统表更新或者添加
			draft.setAgreement("");//质押协议号
			
			//draft.setBail_num(ffar.getBailRat());//保证金账号
			draft.setDistribid(didao.findDistidByOrg(orgcode));//经销商
			//draft.setFinancial_institution();//金融机构
			//draft.setBrand();//品牌
			draft.setDraft_num(ffar.getLoanCode());//汇票号码
			draft.setBilling_date(DateUtil.getDateFormatByString(ffar.getLoanstDate(), "yyyyMMdd"));//开票日
			draft.setDue_date(DateUtil.getDateFormatByString(ffar.getLoanendDate(), "yyyyMMdd"));//到期日
			draft.setBilling_money(ffar.getLoanAmt());//开票金额
			draft.setMortgagecar_money("");//已押车金额
			draft.setPayment_money("");//回款金额
			draft.setState(2);//状态  2:未清票
			//draft.setCreateuserid();//创建人
			//draft.setUpduserid();//修改人
			draft.setBailscale(ffar.getBailRat());//保证金比例
			
			DraftVO temp = ddao.findDraftByDraftNum(ffar.getLoanCode());
			if (temp != null) {
				draft.setId(temp.getId());
				draft.setCreatedate(temp.getCreatedate());
				draft.setUpddate(new Date());//修改时间
				if(ddao.update(draft)){
					log.info("业务系统汇票信息更新结果成功["+ffar.getLoanCode()+"]");	
				}else{
					log.info("业务系统汇票信息更新结果失败");
					log.error("失败回滚结果：["+this.transactionRollback(fidao.getDataSourceName())+"]");//回滚
					return false;
				}
				
			} else {
				try {
					draft.setId(Util.getID(DraftVO.class));
					draft.setCreatedate(new Date());//创建时间
					if(ddao.add(draft)){
						log.info("业务系统汇票信息添加结果成功["+ffar.getLoanCode()+"]");
					}else{
						log.info("业务系统汇票信息添加结果失败");
						log.error("失败回滚结果：["+this.transactionRollback(fidao.getDataSourceName())+"]");//回滚
						return false;
					}
				} catch (Exception e) {
					log.error("汇票信息错误：",e);
					e.printStackTrace();
				}
			}
		}
		log.info("成功提交结果:["+this.transactionCommit(fidao.getDataSourceName())+"]");//提交
		return true;
	}
	
	/**
	 * 解析的dat文件的结果List<List<String>>转List<FinancingFar>
	 * @param xmlList
	 * @return
	 */
	public List<FinancingFar> ConversionList(List<List<String>> xmlList){
		List<FinancingFar> list=new ArrayList<FinancingFar>();
		for (List<String> list2 : xmlList) {
			if(list2.size()>0){
				if(list2.size()==12){
					FinancingFar finf=new FinancingFar();
		//			System.out.println("监管企业ID:"+list2.get(0));
		//			//根据内容设置值
		//			System.out.println("放款日期:"+list2.get(1));
					finf.setLonEntId(list2.get(0));
					finf.setLoanCode(list2.get(1));
					finf.setScftxNo(list2.get(2));
					finf.setLoanAmt(list2.get(3));
					finf.setBailRat(list2.get(4));
					finf.setSlfcapRat(list2.get(5));
					finf.setFstblRat(list2.get(6));
					finf.setLoanstDate(list2.get(7));
					finf.setLoanendDate(list2.get(8));
					finf.setProcrt(list2.get(9));
					finf.setOperOrg(list2.get(10));
					finf.setBizMod(list2.get(11));
					Customer customer=idao.findByNo(list2.get(0));
					if(customer!=null){
						finf.setLonEntNm(customer.getCustName());
					}else{
						log.info("日终批量同步融资，未查到"+list2.get(0)+"相关的客户信息");
					}
					list.add(finf);
				}else{
					log.error("日终批量文件数据内容格式错误，解析出错");
				}
			}
//			finf.setLonEntId(list2.get(2));
//			finf.setLonEntNm(list2.get(3));
		}
		return list;
	}
}

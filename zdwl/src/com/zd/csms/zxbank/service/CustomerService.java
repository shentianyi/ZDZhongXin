package com.zd.csms.zxbank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;
import com.zd.csms.set.dao.oracle.DistribsetOracleDAO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 客户信息Service
 */
public class CustomerService extends ServiceSupport {
	private ICustomerDAO idao = ZXBankDAOFactory.getcustDAO();
	private IDistribsetDAO idsbsdao = SetDAOFactory.getDistribsetDAO();
	private static Log log = LogFactory.getLog(ZXBankInterfaceAction.class);
	
	
	public List<Customer> findcustallList(Customer customer, IThumbPageTools tools) {
		return idao.findcustallList(customer, tools);
	}

	// 远程数据与本地数据比对
	public void autoUpdateCust(List<CustomerFar> bankList, Customer customer) throws Exception {
		List<Customer> list = idao.query();
		if (bankList != null && bankList.size() > 0 && list != null)
			for (CustomerFar customerFar : bankList) {
				int tem = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getCustNo().trim().equals(customerFar.getEcifCode().trim())) {
						/*System.out.println("客户已存在，正在更新。。");
						if (update(customerFar, customer.getCustOrganizationcode())) {
							System.out.println("更新客户：" + customerFar.getCustName() + "成功");
						} else {
							System.out.println("更新客户：" + customerFar.getCustName() + "失败");
						}*/
						log.info("客户信息更新：["+update(customerFar, customer.getCustOrganizationcode())+"]");
						tem++;
						break;
					}
				}
				if (tem == 0) {
					/*if (add(customerFar, customer.getCustOrganizationcode())) {
						System.out.println("保存客户：" + customerFar.getCustName() + "  成功");
					} else {
						System.out.println("保存客户：" + customerFar.getCustName() + "  失败");
					}*/
					log.info("客户信息保存：["+add(customerFar, customer.getCustOrganizationcode())+"]");
				}
			}

	}

	// 更新客户信息
	public boolean update(CustomerFar customerFar, String orgCode) {
		Customer cus = new Customer();
		cus.setCustNo(customerFar.getEcifCode());
		cus.setCustName(customerFar.getCustName());
		cus.setCustOrganizationcode(orgCode);
		cus.setCustUpdateDate(new Date());
		return idao.update(cus);//自增更新方法
	}

	// 保存客户信息
	public boolean add(CustomerFar customerFar, String orgCode) {
		Customer cus = new Customer();
		cus.setCustId(SqlUtil.getID(Customer.class));
		cus.setCustName(customerFar.getCustName());
		cus.setCustNo(customerFar.getEcifCode());
		cus.setCustCreateDate(new Date());
		cus.setCustUpdateDate(new Date());
		cus.setCustOrganizationcode(orgCode);
		cus.setCusConnumber(1);
		return idao.add(cus);
	}
	/**
	 * 输入框下拉选项列表
	 * @return
	 */
	public List<OptionObject> draftsOptions(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts =new ArrayList<String>();
		List<String> list=idao.findAllByECIF();
		for (String string : list) {
			if(string!=null)
				if(!string.trim().isEmpty())
						drafts.add(string);
		}
		if(drafts != null && drafts.size() > 0){
			for(String draftNum : drafts){
				option = new OptionObject(draftNum,draftNum);
				options.add(option);
			}
		}
		return options;
	}
	
	public boolean update(Customer cus){
		return idao.update(cus);
	}

	public List<String> findAllByECIF() {
		return idao.findAllByECIF();
	}

	public Customer findByECIF(String no) {
		return idao.findByNo(no);
	}
	
	public Customer findByQuery(Customer query) {
		return idao.findByQuery(query);
	}
	
	public String getCuAddress(String custNo){
		String address="";
		Customer cusr=idao.findByNo(custNo);
		if(cusr!=null){
			String orgCode=cusr.getCustOrganizationcode();
			int disId=idsbsdao.findDistidByOrg(orgCode);
			if(disId!=0&&disId>0){
				address=idao.getCuAddress(disId);
			}
		}
		return address;
	}
}

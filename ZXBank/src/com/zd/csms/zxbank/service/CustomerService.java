package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.tools.thumbPage.IThumbPageTools;


/**
 * 客户信息Service
 */
public class CustomerService extends ServiceSupport {
	private ICustomerDAO idao = ZXBankDAOFactory.getcustDAO();

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
						System.out.println("客户已存在，正在更新。。");
						if(update(customerFar,customer.getCustOrganizationcode())){
							System.out.println("更新客户："+customerFar.getCustName()+"成功");
						}else{
							System.out.println("更新客户："+customerFar.getCustName()+"失败");
						}
						tem++;
						break;
					}
				}
				if (tem == 0) {
					if(add(customerFar, customer.getCustOrganizationcode())){
						System.out.println("保存客户：" + customerFar.getCustName()+"  成功");
					}else{
						System.out.println("保存客户：" + customerFar.getCustName()+"  失败");
					}
					
				}
			}

	}

	// 更新客户信息
	private boolean update(CustomerFar customerFar, String orgCode) {
		Customer cus = new Customer();
		cus.setCustNo(customerFar.getEcifCode());
		cus.setCustName(customerFar.getCustName());
		cus.setCustOrganizationcode(orgCode);
		cus.setCustUpdateDate(new Date());
		return idao.upadat(cus);//自增更新方法
	}

	// 保存客户信息
	public boolean add(CustomerFar customerFar, String orgCode) {
		Customer cus = new Customer();
		cus.setCustId(SqlUtil.getID(Customer.class));
		cus.setCustName(customerFar.getCustName());
		cus.setCustNo(customerFar.getEcifCode());
		cus.setCustCreateDate(new Date());
		cus.setCustOrganizationcode(orgCode);
		return idao.add(cus);
	}

	public List<String> findAllByECIF(){
		return idao.findAllByECIF();
	}
}

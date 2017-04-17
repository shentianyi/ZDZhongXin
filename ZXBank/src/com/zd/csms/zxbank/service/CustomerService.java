package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
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
		if (bankList != null && bankList.size() > 0)
			for (CustomerFar customerFar : bankList) {
				int tem = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getCustNo().trim().equals(customerFar.getEcifCode().trim())) {
						System.out.println("客户已存在");
						//System.out.println("更新客户："+customerFar.getCustName());
						//update(customerFar,customer.getCustOrganizationcode());
						tem++;
						break;
					}
				}
				if (tem == 0) {
					add(customerFar, customer.getCustOrganizationcode());
					System.out.println("保存客户：" + customerFar.getCustName());
				}
			}

	}

	// 更新客户信息
	@SuppressWarnings("unused")
	private void update(CustomerFar customerFar, String orgCode) {
		Customer cus = new Customer();
		Date date = new Date();
		String updatedate = DateUtil.getStringFormatByDate(date, "yyyy-MM-dd HH:mm:ss");
		cus.setCustOrganizationcode(orgCode);
		cus.setCustUpdateDate(updatedate);
		idao.update("", customerFar, cus);
	}

	// 保存客户信息
	public void add(CustomerFar customerFar, String orgCode) {
		Date date = new Date();
		Customer cus = new Customer();
		cus.setCustName(customerFar.getCustName());
		cus.setCustNo(customerFar.getEcifCode());
		cus.setCustCreateDate(DateUtil.getStringFormatByDate(date, "yyyy-MM-dd HH:mm:ss"));
		cus.setCustOrganizationcode(orgCode);
		idao.add(cus);
	}

}

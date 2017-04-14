package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IWareHouseDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.csms.zxbank.web.bean.WarehouseFar;
import com.zd.tools.thumbPage.IThumbPageTools;

public class WareHouseService extends ServiceSupport {
	private IWareHouseDAO wdao=ZXBankDAOFactory.getWareHouseDAO();
	public List<Warehouse> findBusinessList(Warehouse query,
			IThumbPageTools tools){
		return wdao.findBusinessList(query, tools);
	}
	
	// 远程数据与本地数据比对
	public void autoUpdateCust(List<WarehouseFar> bankList, Warehouse warehouse)
				throws Exception {
			List<Warehouse> list = wdao.query(warehouse.getCustNo());
			System.out.println(list);
			if (bankList != null && bankList.size() > 0)
				for (WarehouseFar warehouseFar : bankList) {
					int tem = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getWhCode().trim().equals(warehouseFar.getBkwhCode().trim())) {
							System.out.println("更新仓库："+warehouseFar.getWhName());
//							update(customerFar,customer.getCustOrganizationcode());
							tem++;
							break;
						}
					}
					if (tem == 0) {
						add(warehouseFar, warehouse.getCustNo());
						System.out.println("保存仓库："+warehouseFar.getWhName());
					}
				}

		}

		// 更新仓库信息
		private void update(WarehouseFar warehouseFar,String orgCode) {
			Date date=new Date();
			String updatedate=DateUtil.getStringFormatByDate(date, "yyyy-MM-dd HH:mm:ss");
//			wdao.upadat(warehouseFar, orgCode, updatedate);
		}

		// 保存仓库信息
		public void add(WarehouseFar waFar, String custNo) {
			Date date = new Date();
			String createDate=DateUtil.getStringFormatByDate(date, "yyyy-MM-dd HH:mm:ss");
			Warehouse was=new Warehouse();
			was.setCreateDate(createDate);
			was.setCustNo(custNo);
			was.setLoncpname(waFar.getLonNm());
			was.setPhone(waFar.getPhone());
			was.setUpdateDate(createDate);
			was.setWhAddress(waFar.getAddress());
			was.setWhCode(waFar.getBkwhCode());
			was.setWhLevel(waFar.getWhLevel());
			was.setWhName(waFar.getWhName());
			was.setWhOperorg(waFar.getOperOrg());
			wdao.add(was);
		}
	
}

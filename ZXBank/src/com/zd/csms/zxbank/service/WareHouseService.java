package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IWareHouseDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.bean.WarehouseFar;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 仓库信息Service
 */
public class WareHouseService extends ServiceSupport {
	private IWareHouseDAO wdao = ZXBankDAOFactory.getWareHouseDAO();

	public List<Warehouse> findBusinessList(Warehouse query, IThumbPageTools tools) {
		return wdao.findBusinessList(query, tools);
	}
	// 远程数据与本地数据比对
	public void autoUpdateWare(List<WarehouseFar> bankList, Warehouse warehouse) throws Exception {
		List<Warehouse> list = wdao.query(warehouse.getCustNo());
		if (bankList != null && bankList.size() > 0 && list != null)
			for (WarehouseFar warehouseFar : bankList) {
				int tem = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getWhCode().trim().equals(warehouseFar.getBkwhCode().trim())) {
						if(update(warehouseFar, warehouse.getCustNo())){
							System.out.println("更新仓库：" + warehouseFar.getWhName()+" 成功");
						}
						tem++;
						break;
					}
				}
				if (tem == 0){
					if(add(warehouseFar, warehouse.getCustNo())){
						System.out.println("保存仓库：" + warehouseFar.getWhName()+"  成功");
					}else{
						System.out.println("保存仓库：" + warehouseFar.getWhName()+"  失败");
					}
				}
			}
	}

	// 更新仓库信息
	private boolean update(WarehouseFar weFar, String custNo) {
		Warehouse ware=new Warehouse();
		ware.setWhid(SqlUtil.getID(Warehouse.class));
		ware.setCustNo(custNo);
		ware.setLoncpname(weFar.getLonNm());
		ware.setPhone(weFar.getPhone());
		ware.setWhAddress(weFar.getAddress());
		ware.setWhCode(weFar.getBkwhCode());
		ware.setWhLevel(weFar.getWhLevel());
		ware.setWhName(weFar.getWhName());
		ware.setWhOperorg(weFar.getOperOrg());
		ware.setUpdateDate(new Date());
		return wdao.upadat(ware);
	}

	// 保存仓库信息
	public boolean add(WarehouseFar waFar, String custNo) {
		Warehouse was = new Warehouse();
		was.setWhid(SqlUtil.getID(Warehouse.class));//获取下一个主键id
		was.setCustNo(custNo);
		was.setLoncpname(waFar.getLonNm());
		was.setPhone(waFar.getPhone());
		was.setWhAddress(waFar.getAddress());
		was.setWhCode(waFar.getBkwhCode());
		was.setWhLevel(waFar.getWhLevel());
		was.setWhName(waFar.getWhName());
		was.setWhOperorg(waFar.getOperOrg());
		was.setCreateDate(new Date());
		return wdao.add(was);
	}

}

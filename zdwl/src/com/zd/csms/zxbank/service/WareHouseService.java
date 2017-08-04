package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.service.TwoAddressService;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.ICustomerDAO;
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
	private TwoAddressService taser = new TwoAddressService();
	private ICustomerDAO cdao = ZXBankDAOFactory.getcustDAO();
	private IDistribsetDAO ddao = SetDAOFactory.getDistribsetDAO();
	private static Log log = LogFactory.getLog(WareHouseService.class);
	private CustomerService cuse = new CustomerService();

	public List<Warehouse> findBusinessList(Warehouse query,
			IThumbPageTools tools) {
		return wdao.findBusinessList(query, tools);
	}

	// 远程数据与本地数据比对
	public boolean autoUpdateWare(List<WarehouseFar> bankList, String custNo) {
		this.transactionBegin(wdao.getDataSourceName());// 开启
		if (saveWare(bankList, custNo)) {
			this.transactionCommit(wdao.getDataSourceName());// 提交
			return true;
		} else {
			this.transactionRollback(wdao.getDataSourceName());// 回滚
			return false;
		}
	}

	public boolean saveWare(List<WarehouseFar> bankList, String custNo) {
		String oneAddress = "";
		// 获得本库地址
		for (WarehouseFar temp : bankList) {
			if (temp.getWhLevel() == "1") {
				oneAddress = temp.getAddress();
			}
		}

		List<Warehouse> list = wdao.query(custNo);
		TwoAddressVO vo = null;
		if (bankList != null && bankList.size() > 0 && list != null)
			for (WarehouseFar warehouseFar : bankList) {
				warehouseFar.setLonentid(custNo);
				int tem = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getWhCode().trim()
							.equals(warehouseFar.getBkwhCode().trim())) {
						// if (update(warehouseFar)) {
						// System.out.println("更新仓库：" + warehouseFar.getWhName()
						// + " 成功");
						// }
						if (update(warehouseFar)) {
							log.info("仓库信息更新成功");
						} else {
							log.info("仓库信息更新失败");
							return false;
						}

						vo = taser.searchBycode(warehouseFar.getBkwhCode());
						conversion(vo, warehouseFar, oneAddress);
						vo.setUpddate(new Date());
						try {
							if (taser.updTwoAddress(vo)) {
								log.info("监管场地信息更新成功");
							} else {
								log.info("监管场地信息更新失败");
								return false;
							}
						} catch (Exception e) {
							log.error("仓库更新错误：", e);
							e.printStackTrace();
							return false;
						}
						tem++;
						break;
					}
				}
				if (tem == 0) {
					if (add(warehouseFar)) {
						log.info("仓库信息保存结果成功");
					} else {
						log.info("仓库信息保存结果失败");
						return false;
					}
				}
				// 保存至业务系统内仓库信息。
				vo = new TwoAddressVO();
				conversion(vo, warehouseFar, oneAddress);
				Customer cusm = cdao.findByNo(warehouseFar.getLonentid());
				if (cusm != null) {
					int did = ddao.findDistidByOrg(cusm
							.getCustOrganizationcode());
					vo.setDistribid(did);
					vo.setCreatedate(new Date());
					// taser.addTwoAddress(vo);
					log.info("TwoAddressVO:[" + vo.toString() + "]");
					try {
						TwoAddressVO twadvo = taser.searchBycode(warehouseFar
								.getBkwhCode());
						if (twadvo != null) {
							vo.setId(twadvo.getId());
							if (taser.updTwoAddress(vo)) {
								log.info("业务系统监管场地更新结果成功");
							} else {
								return false;
							}
						} else {
							vo.setCreateuserid(10010020);
							if (taser.addTwoAddress(vo)) {
								log.info("业务系统监管场地添加结果成功");
							} else {
								return false;
							}
						}
					} catch (Exception e) {
						log.error("业务系统监管场地更新错误：", e);
						e.printStackTrace();
						return false;
					}
				} else {
					log.error("未查到与" + warehouseFar.getLonentid() + "相关的仓库信息");
					return false;
				}
			}
		return true;
	}

	// 更新仓库信息
	private boolean update(WarehouseFar weFar) {
		Warehouse ware = new Warehouse();
		ware.setWhid(SqlUtil.getID(Warehouse.class));
		ware.setCustNo(weFar.getLonentid());
		ware.setWhlonentnm(weFar.getLonNm());
		ware.setPhone(weFar.getPhone());
		ware.setWhAddress(weFar.getAddress());
		ware.setWhCode(weFar.getBkwhCode());
		try {
			ware.setWhLevel(Integer.parseInt(weFar.getWhLevel()));
		} catch (Exception e) {
			log.error("转换等级失败");
		}
		/* ware.setWhLevel(Integer.parseInt(weFar.getWhLevel())); */
		ware.setWhName(weFar.getWhName());
		ware.setWhOperorg(weFar.getOperOrg());
		ware.setUpdateDate(new Date());
		ware.setLonentid(weFar.getLonentid());
		return wdao.update(ware);
	}

	// 保存仓库信息
	public boolean add(WarehouseFar waFar) {
		Warehouse was = new Warehouse();
		was.setWhid(SqlUtil.getID(Warehouse.class));// 获取下一个主键id
		was.setCustNo(waFar.getLonentid());
		was.setWhlonentnm(waFar.getLonNm());
		was.setPhone(waFar.getPhone());
		was.setWhAddress(waFar.getAddress());
		was.setWhCode(waFar.getBkwhCode());
		try {
			was.setWhLevel(Integer.parseInt(waFar.getWhLevel()));
		} catch (Exception e) {
			log.error("转换等级失败");
		}
		/* was.setWhLevel(Integer.parseInt(waFar.getWhLevel())); */
		was.setWhName(waFar.getWhName());
		was.setWhOperorg(waFar.getOperOrg());
		was.setCreateDate(new Date());
		was.setUpdateDate(new Date());
		was.setLonentid(waFar.getLonentid());
		return wdao.add(was);
	}

	public Warehouse getWarehouse(String custNo, String whCode) {
		return wdao.getWarehouse(custNo, whCode);
	}

	public boolean update(Warehouse warehouse) {
		return wdao.update(warehouse);
	}

	public List<Warehouse> findByCNo(String custNo) {
		return wdao.findByCustNo(custNo);
	}

	//
	public boolean conversion(TwoAddressVO tavo, WarehouseFar waFar,
			String address) {
		int num = 0;
		tavo.setTwo_name(waFar.getWhName());
		tavo.setTwo_address(waFar.getAddress());
		tavo.setPhone(waFar.getPhone());
		num = Integer.parseInt(waFar.getWhLevel());
		num = num==1?1:waFar.getAddress().equals(address)?2:3;
		System.out.println("仓库等级:"+num);
		tavo.setType(num);
		tavo.setBkwhcode(waFar.getBkwhCode());
		return true;
	}

}

package com.zd.csms.zxbank.dock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.csms.bank.dock.BankDock;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.supervisory.model.CheckStockManageBean;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Check;
import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.Checkwarehouse;
import com.zd.csms.zxbank.bean.Commodity;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.service.AgreementService;
import com.zd.csms.zxbank.service.CustomerService;
import com.zd.csms.zxbank.service.FinancingService;
import com.zd.csms.zxbank.service.WareHouseService;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.tools.StringUtil;

public class ZXBankDockImpl implements BankDock {
	private static int zxtimes = 1;
	private static Log log = LogFactory.getLog(ZXBankDockImpl.class);
	private ZXBankInterfaceAction zxbankInterface = new ZXBankInterfaceAction();

	public static int getZxtimes() {
		return zxtimes;
	}

	public static void setZxtimes(int zxtimes) {
		ZXBankDockImpl.zxtimes = zxtimes;
	}

	@Override
	public boolean ruku(List<SuperviseImportVO> carList,
			HttpServletRequest request) throws Exception {
		FinancingService fs = new FinancingService();
		WareHouseService ws = new WareHouseService();
		AgreementService as = new AgreementService();
		CustomerService cs = new CustomerService();

		// 获得用户
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String draftNum = carList.get(0).getDraft_num();

		Financing fi = fs.getFinancing(draftNum);
		//
		if (fi == null) {
			request.setAttribute("message", "未在中信银行同步");
			return false;
		}
		//
		Agreement agreement = as.findByNo(fi.getFgLonentNo());
		if (agreement == null) {
			request.setAttribute("message", "未查询到相关协议信息");
			return false;
		}
		String newDate = DateUtil.getStringFormatByDate(new Date(), "yyyyMMdd");
		Customer cus = cs.findByECIF(fi.getFgLonentNo());
		int MonTimes = cus.getCusConnumber();

		Gager gager = new Gager();
		gager.setGaLonentno(fi.getFgLonentNo());
		gager.setGaLonentname(fi.getFgLoncpName());
		gager.setGaOprtname(user.getUserName());

		gager.setGaOrderno(newDate + zxtimes);// 交易流水号
		gager.setGaPcgrtntno(agreement.getSpvagtno());
		gager.setGaCmgrtcntno(agreement.getSpvagtid());
		// 仓库代码
		List<Warehouse> allWare = ws.findByCNo(fi.getFgLonentNo());
		for (Warehouse warehouse : allWare) {
			if (warehouse.getWhLevel() == 1)
				gager.setGawhCode(warehouse.getWhCode());// 仓库代码
		}
		gager.setGaConfirmno(newDate + MonTimes);// 监管协议确认书编号
		gager.setGaRemark(carList.get(0).getDes());// 备注

		List<Commodity> lists = listToCommditys(carList);

		gager.setGaCount(lists.size());

		List<Map<String, Object>> lst = null;

		lst = zxbankInterface.gagerApp(gager, lists, request);
		String msg = (String) request.getAttribute("message");
		if (lst == null && msg != null) {
			if (msg.equals("")) {
				request.setAttribute("message", "直连超时，请稍后再试");
				return false;
			}
		}
		// lst为异常项
		SuperviseImportService service = new SuperviseImportService();
		BankApproveService baservice = new BankApproveService();
		CarOperationService coservice = new CarOperationService();
		int did = 0;
		DraftService ds = new DraftService();
		DraftQueryVO dquery = new DraftQueryVO();
		// 通过汇票号查询汇票信息，然后获取到经销商id
		String drafNum = carList.get(0).getDraft_num();// 汇票号
		dquery.setDraft_num(drafNum);
		List<DraftVO> dList = ds.searchDraftList(dquery);
		if (dList != null && dList.size() > 0) {
			did = dList.get(0).getDistribid();
		}

		String carIds = "";
		String abCarIds = "";
		String norVin = "";
		String abnorVin = "";

		try {
			// 1.改变车辆状态 SuperviseImportService
			boolean flag = false;
			for (SuperviseImportVO vo : carList) {
				BankApproveVO bavo = baservice.searchBankApproveBySid(
						vo.getId(), 2);
				if (null != bavo) {
					bavo.setType(2);
					bavo.setCreatetime(new Date());
					bavo.setApprovetime(new Date());
				} else {
					bavo = new BankApproveVO();
					bavo.setSid(vo.getId());
					bavo.setType(2);
					bavo.setCreatetime(new Date());
					bavo.setApprovetime(new Date());
				}

				// 判断异常车辆是否为空
				if (lst != null) {
					// 1.判断是否审批通过
					log.info("有[" + lst.size() + "]辆未通过申请");
					for (Map<String, Object> abCar : lst) {
						String abVin = abCar.get("vin").toString();
						log.info("abVin:[" + abVin + "]");
						if (vo.getVin().equals(abVin)) {
							// abnorVin
							// +="["+abCar.get("remark").toString()+"],";
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					// 审核未通过
					// 不需要改变车辆状态
					// 2.添加银行审批 BankApproveService
					bavo.setState(3);
					abCarIds = abCarIds + "," + vo.getId();
					abnorVin = abnorVin + "," + vo.getVin();
				} else {
					log.info("有[" + vo.getVin() + "]车辆通过申请");
					// 审核通过
					vo.setStoragetime(new Date());
					vo.setApply(0);
					vo.setState(2);
					vo.setUpduserid(user.getId());
					vo.setUpddate(new Date());
					//设置回款金额
					if(!StringUtil.isEmpty(vo.getPayment_amount())){
						vo.setPayment_amount(vo.getMoney());
					}
					service.updSuperviseImport(vo, 0);

					// 2.添加银行审批 BankApproveService
					bavo.setState(2);

					carIds = carIds + "," + vo.getId();
					norVin = norVin + "," + vo.getVin();
				}

				// 添加或修改银行审批
				if (bavo.getId() > 0)
					baservice.updBankApprove(bavo);
				else
					baservice.addBankApprove(bavo);

				flag = false;
			}
			// 3.添加车辆操作表 CarOperationService
			CarOperationVO covo = new CarOperationVO();
			covo.setDistribid(did);
			covo.setType(2);
			covo.setUserid(user.getId());
			covo.setCreatetime(new Date());

			if (carIds.length() > 0) {
				carIds = carIds.substring(1);
				norVin = norVin.substring(1);
				// 通过添加
				covo.setCars(carIds);
				covo.setApprovalState(2);
				coservice.addCarOperation(covo);
				log.info("入库审核通过车辆VINs：" + norVin);
			}

			if (abCarIds.length() > 0) {
				abCarIds = abCarIds.substring(1);
				abnorVin = abnorVin.substring(1);
				// 未通过添加
				covo.setCars(abCarIds);
				covo.setApprovalState(3);
				coservice.addCarOperation(covo);
				log.error("入库审核未通过车辆VINs：" + abnorVin);
				// request.setAttribute("message",request.getAttribute("message")+"--车架号:["+abnorVin+"]");
				// log.error(abnorVin);
				request.setAttribute("message", "入库审核未通过车辆VINs：" + abnorVin);
			} else {
				request.setAttribute("message", "申请成功");
			}

			// 4.监管物入库提醒
			List<UserVO> uList = service.searchUserById(0);
			if (uList != null && uList.size() > 0) {
				for (UserVO ur : uList) {
					MessageUtil.addMsg(ur.getId(), "监管物入库",
							"/carOperation.do?method=storageList", 1,
							MessageTypeContant.CARSTORAGE.getCode(),
							user.getId());
				}
			}

		} catch (Exception e) {
			log.error("入库错误信息", e);
			return false;
		}

		MonTimes++;
		cus.setCusConnumber(MonTimes);
		cs.update(cus);

		zxtimes++;
		// }else{
		// log.error("[入库状态码异常无法提交入库申请]");
		// }
		return true;

	}

	/**
	 * 处理数据
	 * 
	 * @param list
	 * @return
	 */
	public List<Commodity> listToCommditys(List<SuperviseImportVO> list) {
		List<Commodity> lists = new ArrayList<Commodity>();
		for (SuperviseImportVO si : list) {
			Commodity commodity = new Commodity();
			commodity.setCmCmdcode("");
			commodity.setCmStknum(1);
			commodity.setCmIstkprc(new BigDecimal(0));
			commodity.setCmVin(si.getVin());
			commodity.setCmHgzno(si.getCertificate_num());
			commodity.setCmCarprice(new BigDecimal(si.getMoney()));
			commodity.setCmLoancode(si.getDraft_num());
			lists.add(commodity);
		}
		return lists;
	}

	@Override
	public boolean chuku(List<SuperviseImportVO> list,
			HttpServletRequest request) throws Exception {
		// 无出库申请，直接返回true，更改车辆状态为出库申请中待审批。可等待接收到解除质押通知自动解除车辆。变为出库完成状态。
		return true;
	}

	public boolean checkStock(DistribsetVO distribset,
			CheckStockManageBean checkStockBean,
			List<CheckStockCarBean> checkStockCarBeans,
			HttpServletRequest request) {
		log.info("中信接口盘点信息提交");
		boolean result = false;

		CustomerService cs = new CustomerService();
		AgreementService as = new AgreementService();
		WareHouseService ws = new WareHouseService();
		UserSession user = (UserSession) request.getSession().getAttribute(
				"userMessage");

		// 获得ECIF客户号
		Customer query = new Customer();
		query.setCustOrganizationcode(distribset.getZxOrgCode());
		String ecifNo = cs.findByQuery(query).getCustNo();
		Agreement agreement = as.findByNo(ecifNo);

		// 远程提交参数
		Checkstock check = new Checkstock();
		check.setCsLoncpid(ecifNo);// --借款企业id
		check.setCsLoncpname(checkStockBean.getDealerName());// --借款企业名称
		check.setCsProtocolno(agreement.getSpvagtid());// --系统监管协议编号
		check.setCsProtocolcode(agreement.getSpvagtno());// --纸质监管协议编号
		check.setCsUserno(user.getUser().getId() + "");// --操作人编号
		check.setCsUsername(user.getUser().getUserName());// --操作人名称
		String TradeID = DateUtil.getStringFormatByDate(new Date(), "yyyyMMdd")
				+ zxtimes;
		check.setCsTradeid(TradeID);// --交易流水号
		check.setCsPlandate("");// --计划盘库日期
		check.setCsFactdate(DateUtil.getStringFormatByDate(
				checkStockBean.getCheck_date(), "yyyyMMdd"));// --实际盘库日期
		check.setCsErrorreport("常规差异:" + checkStockBean.getNormal_difference()
				+ "/n非常规差异:" + checkStockBean.getUn_normal_difference());// --差错报告
		check.setCsRemark("");// --备注
		check.setCsCreatedate(new Date());
		List<Checkwarehouse> wlist = new ArrayList<Checkwarehouse>();
		List<Warehouse> wares = ws.findByCNo(ecifNo);
		String OnCode = "";
		String MoveCode = "";

		for (Warehouse warehouse : wares) {
			if (warehouse.getWhLevel() == 1)
				OnCode = warehouse.getWhCode();
			else if (warehouse.getWhLevel() == 2)
				MoveCode = warehouse.getWhCode();

			Checkwarehouse cw = new Checkwarehouse();
			// cw.setChId(SqlUtil.getID(Checkwarehouse.class));
			cw.setChWhcode(warehouse.getWhCode());
			cw.setChWhname(warehouse.getWhName());
			cw.setChWhlevel(warehouse.getWhLevel() + "");
			cw.setChWhaddr(warehouse.getWhAddress());
			cw.setChNum(warehouse.getWhLevel() == 1 ? checkStockBean
					.getActual_wh_count() : checkStockBean
					.getActual_move_count());
			wlist.add(cw);
		}

		List<Check> clist = new ArrayList<Check>();
		for (CheckStockCarBean car : checkStockCarBeans) {
			Check checkCar = new Check();
			if (car.getActualstate() == 1) {
				checkCar.setCkSpvwhcode(OnCode);
			} else if (car.getActualstate() == 3) {
				checkCar.setCkSpvwhcode(MoveCode);
			}
			// 仓库代码
			checkCar.setCkVin(car.getVin());// 车架号
			// checkCar.setCkId(SqlUtil.getID(Check.class));
			clist.add(checkCar);
		}

		// 获得远程提交结果
		result = zxbankInterface.stockApp(check, wlist, clist, request);
		zxtimes++;
		return result;
	}
}

package com.zd.csms.zxbank.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.core.ServiceSupport;
import com.zd.core.SystemProperty;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.supervisory.dao.IBankApproveDAO;
import com.zd.csms.supervisory.dao.ISuperviseImportDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.util.Util;
import com.zd.csms.zxbank.bean.MoveDetail;
import com.zd.csms.zxbank.bean.MoveNotice;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.bean.PushNoticeDetail;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IMoveDetailDAO;
import com.zd.csms.zxbank.dao.IMoveNoticeDAO;
import com.zd.csms.zxbank.dao.INoticeDAO;
import com.zd.csms.zxbank.dao.IReceivingDetailDAO;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.csms.zxbank.dao.IRemovePledgeDetailDAO;
import com.zd.csms.zxbank.dao.IWareHouseDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.util.ZXNoticeUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.csms.zxbank.web.bean.MoveDetailFar;
import com.zd.csms.zxbank.web.bean.MoveNoticeFar;
import com.zd.csms.zxbank.web.bean.ReceivingDetailFar;
import com.zd.csms.zxbank.web.bean.ReceivingNoticeFar;
import com.zd.csms.zxbank.web.bean.RemovePledgeDetailFar;
import com.zd.csms.zxbank.web.bean.RemovePledgeFar;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


/**
 * 通知推送
 * @author caizhuo
 *
 */
public class NoticeService extends ServiceSupport {
	private static final String userName;
	private static Log log = LogFactory.getLog(NoticeService.class);
	static{
		userName=SystemProperty.getPropertyValue("bankdock.properties","zx.userName");
	}
	
	private INoticeDAO ndao = ZXBankDAOFactory.getnoticeDAO();

	private IReceivingNoticeDAO irndao = ZXBankDAOFactory.getReceivingNoticeDAO();
	private IReceivingDetailDAO irddao = ZXBankDAOFactory.getReceivingDetailDAO();

	private IMoveNoticeDAO imndao = ZXBankDAOFactory.getMoveNoticeDAO();
	private IMoveDetailDAO imddao = ZXBankDAOFactory.getMoveDetailDAO();

	private IRemovePledgeDAO irpdao = ZXBankDAOFactory.getRemovePledgeDAO();
	private IRemovePledgeDetailDAO irpddao = ZXBankDAOFactory.getRemovePledgeDetailDAO();

	//车辆信息导入DAO
	private ISuperviseImportDAO isidao = SupervisorDAOFactory.getSuperviseImportDAO();
	//仓库信息DAO
	private IWareHouseDAO whdao = ZXBankDAOFactory.getWareHouseDAO();
	//银行审批表DAO
	private IBankApproveDAO ibadao = SupervisorDAOFactory.getBankApproveDAO();
	
	public List<Notice> findNotice(Notice notice, IThumbPageTools tools) {
		return ndao.findNotice(notice, tools);
	}

	//查询通知类型
	public List<Notice> findnoticetype() {
		return ndao.findnoticetype();
	}

	//判断是否存在
//	public boolean isNotice(Notice notice) {
//		if (ndao.getNotice(notice) != null) {
//			return true;
//		}
//		return false;
//	}
	
	public Notice getNotice(Notice notice) {
		return ndao.getNotice(notice);
	}
	

	public boolean add(Notice notice) {
		notice.setNid(SqlUtil.getID(Notice.class));
		return ndao.add(notice);
	}

	public boolean update(Notice notice) {
		return ndao.update(notice);
	}
	
	/**
	 * 通知推送表及通知详情保存 业务系统车辆信息保存。
	 * @param noticeType
	 * @param resultList
	 * @return
	 */
	public Map<String, Object> saveNotice(int noticeType, List<Object> resultList, Object bean) {
		Map<String, Object> map=new HashMap<String, Object>();//存储交易状态，操作状态
		map.put("message", "");
		map.put("boolean", false);
		//根据类型不同
		log.info("事务开启：["+this.transactionBegin(ndao.getDataSourceName())+"]");
		if (noticeType == 1) {
			//保存收货通知
			ReceivingNoticeFar rnf = (ReceivingNoticeFar) bean;
			ReceivingNotice rn = new ReceivingNotice();
			rn.setNyNo(rnf.getRcvcmdntcNo());//通知书编号
			log.info("[收到收货通知书:"+rnf.getRcvcmdntcNo()+"]");
			rn.setNyCorentnm(rnf.getCorentNm());
			rn.setNyCsndate(DateUtil.getDateFormatByString(rnf.getCsnDate(), "yyyyMMdd"));
			rn.setNyEpa(rnf.getEpa());
			rn.setNyEta(DateUtil.getDateFormatByString(rnf.getEtaDate(), "yyyyMMdd"));
			rn.setNyExcplace(rnf.getExcPlace());
			rn.setNyLonentname(rnf.getLonentNm());
			rn.setNyLonentno(rnf.getLonentId());
			rn.setNyNtcdate(DateUtil.getDateFormatByString(rnf.getNtcDate(), "yyyyMMdd"));
			rn.setNyOfflnsatno(rnf.getOfflnsatNo());
			rn.setNyOnwspvenm(rnf.getOnwspveNm());
			rn.setNyRemark(rnf.getRemark());
			rn.setNySpventnm(rnf.getSpventNm());
			rn.setNyTotnum(rnf.getTotnum());
			rn.setNyTrsptentnm(rnf.getTrsptentNm());
			rn.setNyTtlcmdval(rnf.getTtlcmdval());
//			log.info("通知编号"+rn.getNyNo());
			ReceivingNotice rent=irndao.getNotify(rn.getNyNo());
			if(rent!=null){
				rn.setId(rent.getId());
				rn.setNyUpdatedate(new Date());
				if(irndao.update(rn)){
					log.info("收货通知书更新成功["+rn.getNyNo()+"]");
					map.put("boolean", true);
				}else{
					log.error("收货通知书更新失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
			}else{
				rn.setNyCreatedate(new Date());
				rn.setNyUpdatedate(new Date());
				if(irndao.add(rn)){
					log.info("收货通知书添加成功["+rn.getNyNo()+"]");
				}else{
					log.error("收货通知书添加失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
			}
			SuperviseImportVO sivo = new SuperviseImportVO();
			//保存收货通知详情
			List<ReceivingDetail> redt=irddao.findAll(rn.getNyNo());
			if(redt==null||redt.size()==0){
				for (int i = 0; i < resultList.size(); i++) {
					ReceivingDetail rd = new ReceivingDetail();
					ReceivingDetailFar rdf = (ReceivingDetailFar) resultList.get(i);
					rd.setNdNo(rnf.getRcvcmdntcNo());//通知书编号
					rd.setNdCarprice(rdf.getCarPrice());
					rd.setNdCmdcode(rdf.getCmdCode());
					rd.setNdCmdname(rdf.getCmdName());
					rd.setNdCsnnum(rdf.getCsnNum());
					rd.setNdCsnprc(rdf.getCsnprc());
					rd.setNdHgzno(rdf.getHgzNo());
					rd.setNdIdtplannm(rdf.getIdtplanNm());
					rd.setNdIdtplanno(rdf.getIdtplanNo());
					rd.setNdLoancode(rdf.getLoanCode());
					rd.setNdMtgcntno(rdf.getMtgcntNo());
					rd.setNdRemark(rdf.getRemark());
					rd.setNdReqcsnval(rdf.getReqcsnval());
					rd.setNdScflonno(rdf.getScflonNo());
					rd.setNdUnit(rdf.getUnit());
					rd.setNdVin(rdf.getVin());
//					log.info("[收货通知书详情保存结果:"+irddao.add(rd)+"]");
					if(irddao.add(rd)){
						log.info("详情保存成功");
					}else{
						log.error("通知详情保存失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
						map.put("message","读取失败");
						map.put("boolean",false);
						return map;
					}
				//保存至业务系统
				sivo.setCertificate_num(rd.getNdHgzno());
				sivo.setVin(rd.getNdVin());
				sivo.setMoney(rd.getNdCarprice());//金额--车价
				sivo.setDes(rd.getNdRemark());
				sivo.setDraft_num(rd.getNdLoancode());//汇票号--融资编号
				sivo.setState(1);
				sivo.setPrice(rd.getNdCsnprc());//单价--发货单价
				sivo.setApply(0);
				sivo.setImporttime(new Date());
				sivo.setCreatedate(new Date());
				sivo.setCertificate_intime(new Date());
				sivo.setUpddate(new Date());
//				log.info("车辆信息导入结果：["+addSuperviseImport(sivo)+"]");
				if(addSuperviseImport(sivo,1)){
					log.info("车辆入库成功,VIN码:"+sivo.getVin());
				}else{
					log.error("车辆信息导入失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
				}

				log.info("车辆信息导入成功，事务提交：["+this.transactionCommit(ndao.getDataSourceName())+"]");
				map.put("message","读取成功");
				map.put("boolean",true);
				return map;
			}
			
			
			log.info("更新成功，事务提交：["+this.transactionCommit(ndao.getDataSourceName())+"]");
		} else if (noticeType == 2) {
			//保存移库通知
			try {
			MoveNoticeFar mnf = (MoveNoticeFar) bean;
//			System.out.println(mnf.toString());
			MoveNotice mn = new MoveNotice();
			mn.setMnLoncpname(mnf.getLonentNm());
			mn.setMnMovedate(mnf.getMwapyDate());
			log.info("[移库通知书编号:"+mnf.getMwntcNo()+"]");
			mn.setMnNo(mnf.getMwntcNo());
			mn.setMnNoticedate(mnf.getNtcDate());
			mn.setMnOperorg(mnf.getOperOrg());
			mn.setMnSupervisename(mnf.getSpventnm());
			mn.setMnTotnum(mnf.getTotnum());
			MoveNotice mnte=imndao.fingByNo(mnf.getMwntcNo());
			if(mnte!=null){
				mn.setId(mnte.getId());
				mn.setMnUpdatedate(DateUtil.getDateFormatByString(new Date().toString(),"yyyy-MM-dd HH:mm:ss"));
				mn.setMnCreatedate(mnte.getMnCreatedate());
				if(imndao.update(mn)){
					log.info("移库通知更新成功：["+mn.getMnNo()+"]");
					map.put("boolean", true);
				}else{
					log.error("移库通知更新失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
//				log.info("[移库通知书更新:"+imndao.update(mn)+"]");
			}else{
				mn.setMnCreatedate(new Date());
				mn.setMnUpdatedate(new Date());
				if(imndao.add(mn)){
					log.info("移库通知书添加：["+mn.getMnNo()+"]");
				}else{
					log.error("移库通知书添加失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
//				log.info("[移库通知书保存:"+imndao.add(mn)+"]");
			}
			SuperviseImportVO sivo = new SuperviseImportVO();
			List<MoveDetail> mds=imddao.findAll(mnf.getMwntcNo());
			if(mds==null||mds.size()==0){
			//保存移库通知详情
				for (int i = 0; i < resultList.size(); i++) {
					MoveDetailFar mdf = (MoveDetailFar) resultList.get(i);
					MoveDetail mdt = new MoveDetail();
					mdt.setMdCarprice(mdf.getCarPrice());
					mdt.setMdCertificationno(mdf.getHgzNo());
					mdt.setMdChassisno(mdf.getVin());
					mdt.setMdMovenumber(mdf.getStkNum());
					mdt.setMdNo(mnf.getMwntcNo());
					mdt.setMdRemoveinno(mdf.getIwCode());
					mdt.setMdRemoveoutno(mdf.getMwCode());
//					log.info("[移库通知书详情保存:"+imddao.add(mdt)+"]");
					if(imddao.add(mdt)){
						log.info("移库通知书详情保存：["+mdt.getMdNo()+"]");
					}else{
						log.error("移库通知书详情保存失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
						map.put("message","读取失败");
						map.put("boolean",false);
						return map;
					}
					//更新车辆状态
					sivo.setVin(mdt.getMdChassisno());
					sivo.setCertificate_num(mdt.getMdCertificationno());
					sivo.setPrice(mdt.getMdCarprice());
					Warehouse wh=whdao.getWarehouse(mdt.getMdRemoveinno());
					if(wh==null){
						log.info("无相关"+mdt.getMdRemoveinno()+"的仓库信息，移库失败");
						map.put("message", "无相关"+mdt.getMdRemoveinno()+"的仓库信息，移库失败");
						map.put("boolean",false);
						return map;
					}
					sivo.setTwo_name(wh.getWhName());
					sivo.setState(4);
					sivo.setMovetime(DateUtil.getDateFormatByString(mnf.getNtcDate(), "yyyyMMdd"));
					if(addSuperviseImport(sivo,2)){
						log.info("车辆移库成功,VIN码:"+sivo.getVin());
					}else{
						log.error("车辆信息更新失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
						map.put("message","读取失败");
						map.put("boolean",false);
						return map;
					}
					//审批更新
				}
			}
			//移库完成回执发送
				Map<String, Object> statusMap=null;
				statusMap=ZXNoticeUtil.retReceipt(ZXNoticeUtil.MOVE, mnf.getMwntcNo(), 1, "");
				if (statusMap.get("status").equals("AAAAAAA")) {
					log.info("移库通知回执成功]，事务提交：["+this.transactionCommit(ndao.getDataSourceName())+"]");
					statusMap.put("boolean",true);
					return statusMap;
				} else {
					log.error("移库通知回执失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					statusMap.put("boolean",false);
					return statusMap;
				}
			} catch (Exception e) {
				log.error("移库错误", e);
				e.printStackTrace();
			}
		} else if (noticeType == 3) {
			//保存解除质押通知
			try {
			RemovePledgeFar rpgf = (RemovePledgeFar) bean;
			RemovePledge rpg = new RemovePledge();
			rpg.setRpContent(rpgf.getOstkrsn());
			rpg.setRpCorename(rpgf.getCorentNm());
			rpg.setRpLoncpid(rpgf.getLoaentId());
			rpg.setRpLoncpname(rpgf.getLonentNm());
			log.info("[解除质押通知书编号:"+rpgf.getRlsmgntcNo()+"]");
			rpg.setRpNo(rpgf.getRlsmgntcNo());//通知书编号
			rpg.setRpNoticedate(rpgf.getNtcDate());
			rpg.setRpOperorg(rpgf.getOperOrg());
			rpg.setRpPldegeename(rpgf.getMtgpsnNm());
			rpg.setRpRelievepddate(rpgf.getRlsmgDate());
			rpg.setRpSupervisename(rpgf.getSpventNm());
			
			RemovePledge rpdg=irpdao.fingByNo(rpgf.getRlsmgntcNo());
			if(rpdg!=null){
				rpg.setId(rpdg.getId());
				rpg.setRpUpdatedate(new Date());
				if(irpdao.update(rpg)){
					log.info("解除质押通知书更新成功:["+rpg.getRpNo()+"]");
					map.put("boolean", true);
				}else{
					log.error("");
					log.error("解除质押通知书更新失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
			}else{
				rpg.setRpCreatedate(new Date());
				rpg.setRpUpdatedate(new Date());
				if(irpdao.add(rpg)){
					log.info("[解除质押通知书添加:["+rpg.getRpNo()+"]");
				}else{
					log.error("解除质押通知书添加失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
					map.put("message","读取失败");
					map.put("boolean",false);
					return map;
				}
				
			}
			SuperviseImportVO sivo = new SuperviseImportVO();
			//保存解除质押通知详情
			List<RemovePledgeDetail> rpdds=irpddao.findAll(rpgf.getRlsmgntcNo());
			if(rpdds==null||rpdds.size()==0){
				for (int i = 0; i < resultList.size(); i++) {
					RemovePledgeDetailFar rpdf = (RemovePledgeDetailFar) resultList.get(i);
					RemovePledgeDetail rpd = new RemovePledgeDetail();
					rpd.setRdNo(rpgf.getRlsmgntcNo());//通知书编号
					rpd.setRdWhcode(rpdf.getWhCode());
					rpd.setRdCarprice(rpdf.getCarPrice());
					rpd.setRdCertificationno(rpdf.getHgzNo());
					rpd.setRdChassisno(rpdf.getVin());
					rpd.setRdChattelpdno(rpdf.getGrtcntNo());
					rpd.setRdCmdcode(rpdf.getCmdCode());
					rpd.setRdCmdname(rpdf.getCmdName());
					rpd.setRdNumber(rpdf.getMvstkNum());
					rpd.setRdRlsmgnum(rpdf.getRlsmgNum());
					rpd.setRdScflonno(rpdf.getScflonNo());
					rpd.setRdStknum(rpdf.getStkNum());
					rpd.setRdUnit(rpdf.getUnit());
					rpd.setRdUsercardid(rpdf.getRdmopridNo());//赎货经办人身份证号码
					rpd.setRdUsername(rpdf.getRdmoprName());//赎货经办人姓名
					if(irpddao.add(rpd)){
						log.info("解除质押通知书详情添加:["+rpd.getRdNo()+"]");
					}else{
						log.error("解除质押通知书详情添加失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
						map.put("message","读取失败");
						map.put("boolean",false);
						return map;
					}
					
					//更新车辆状态
					sivo.setVin(rpd.getRdChassisno());//车架号
					sivo.setCertificate_num(rpd.getRdCertificationno());//合格证编号
					sivo.setPrice(rpd.getRdCarprice());//车辆单价
					sivo.setState(3);
					sivo.setOuttime(DateUtil.getDateFormatByString(rpgf.getNtcDate(),"yyyyMMdd"));
					if(addSuperviseImport(sivo,3)){
						log.info("车辆出库成功,VIN码:"+sivo.getVin());
					}else{
						log.error("车辆信息更新失败，事务回滚：["+this.transactionRollback(ndao.getDataSourceName())+"]");
						map.put("message","读取失败");
						map.put("boolean",false);
						return map;
					}
					//车辆解除质押标记为出库，
				}
			}
			log.info("更新成功，事务提交：["+this.transactionCommit(ndao.getDataSourceName())+"]");
			map.put("boolean",true);
			return map;
			} catch (Exception e) {
				log.error("解除质押错误",e);
				e.printStackTrace();
			}
			
		}
		return map;
	}

	/**
	 * 系统自动同步车辆信息的保存方法
	 * @param list
	 * @param draftNum
	 * @return
	 */
	public boolean addSuperviseImport(SuperviseImportVO vo,int type) {
		try {
			SuperviseImportVO car = this.findByVin(vo.getVin());
			if (car == null) {//新增车辆
				vo.setState(1);
				vo.setApply(0);
				vo.setImporttime(new Date());
				vo.setCertificate_intime(new Date());
				vo.setCreatedate(new Date());
				vo.setUpddate(new Date());
				vo.setId(Util.getID(SuperviseImportVO.class));
				vo.setCreateuserid(10010020);
				if(isidao.add(vo)){
					log.info("车辆保存结果成功");
				}else{
					log.info("车辆保存结果失败");
					return false;
				}
				//车辆审批信息保存。
				if(type!=4 && saveBankApprove(vo)){
					log.info("车辆车辆审批信息保存结果成功");
					return true;
				}else{
					log.info("车辆车辆审批信息保存结果成功");
					return false;
				}
			} else {
				if (car.getCertificate_date() == null && vo.getCertificate_date() != null) {
					car.setCertificate_date(vo.getCertificate_date());
					car.setImporttime(new Date());
				}
				if (StringUtil.isEmpty(car.getCertificate_num()) && !StringUtil.isEmpty(vo.getCertificate_num())) {
					car.setCertificate_num(vo.getCertificate_num());
				}
				if (StringUtil.isEmpty(car.getCar_model()) && !StringUtil.isEmpty(vo.getCar_model())) {
					car.setCar_model(vo.getCar_model());
				}
				if (StringUtil.isEmpty(car.getCar_structure()) && !StringUtil.isEmpty(vo.getCar_structure())) {
					car.setCar_structure(vo.getCar_structure());
				}
				if (StringUtil.isEmpty(car.getDisplacement()) && !StringUtil.isEmpty(vo.getDisplacement())) {
					car.setDisplacement(vo.getDisplacement());
				}
				if (StringUtil.isEmpty(car.getColor()) && !StringUtil.isEmpty(vo.getColor())) {
					car.setColor(vo.getColor());
				}
				if (StringUtil.isEmpty(car.getEngine_num()) && !StringUtil.isEmpty(vo.getEngine_num())) {
					car.setEngine_num(vo.getEngine_num());
				}
				if (StringUtil.isEmpty(car.getKey_num()) && !StringUtil.isEmpty(vo.getKey_num())) {
					car.setKey_num(vo.getKey_num());
				}
				if (StringUtil.isEmpty(car.getMoney()) && !StringUtil.isEmpty(vo.getMoney())) {
					car.setMoney(vo.getMoney());
				}
				if (StringUtil.isEmpty(car.getDes()) && !StringUtil.isEmpty(vo.getDes())) {
					car.setDes(vo.getDes());
				}
				//释放时间
				if (car.getOuttime() == null  && vo.getOuttime()!=null) {
					car.setOuttime(vo.getOuttime());
				}
				//移动时间
				if (car.getMovetime() == null  && vo.getMovetime()!=null) {
					car.setMovetime(vo.getMovetime());
				}
				
				//汇票号
				if (!StringUtil.isEmpty(vo.getDraft_num())) {
					car.setDraft_num(vo.getDraft_num());
				}else if(type==4){
					car.setDraft_num("");
				}
				
				if (StringUtil.isEmpty(car.getTwo_name()) && !StringUtil.isEmpty(vo.getTwo_name())) {
					car.setTwo_name(vo.getTwo_name());
				}//二网名称
				
				car.setUpddate(new Date());
				if(!StringUtil.isEmpty(vo.getState()+"") && type!=4){
					car.setState(vo.getState());
				}
				
				car.setApply(0);
				
				
				if(isidao.update(car)){
					log.info("车辆信息更新成功");
				}else{
					log.info("车辆信息更新失败");
					return false;
				}
				//车辆信息更新
				
				//车辆的审批信息更新。
				if(type!=4 && saveBankApprove(car)){
					log.info("车辆的审批信息更新成功");
					return true;
				}else{
					log.info("车辆的审批信息更新失败");
					return false;
				}
			}
		} catch (Exception e) {
			log.info("车辆导入错误:",e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 以下几个方法只为保存车辆信息。//
	 */
	/**
	 * 根据车架号查询车辆信息
	 * @param vin
	 * @return
	 */
	public SuperviseImportVO findByVin(String vin) {
		return isidao.findByVin(vin);
	}

	//银行审批表添加方法
	public boolean addBankApprove(BankApproveVO vo) throws Exception {

		boolean flag = false;

		vo.setId(Util.getID(BankApproveVO.class));

		ibadao.add(vo);

		if (vo.getId() > 0) {
			flag = true;
		}
		return flag;
	}

	//审批状态的更新
	public boolean saveBankApprove(SuperviseImportVO sivo) {
		BankApproveVO bavo = ibadao.searchBankApproveBySid(sivo.getId(),sivo.getState());
		if (null != bavo) {
			log.info("更新审批记录"+bavo.getId());
			bavo.setState(2);
			bavo.setType(sivo.getState());
			bavo.setCreatetime(new Date());
			bavo.setApprovetime(new Date());
			return ibadao.update(bavo);
		} else {
			log.info("添加审批记录");
			bavo = new BankApproveVO();
			bavo.setSid(sivo.getId());
			bavo.setState(2);
			bavo.setType(sivo.getState());
			bavo.setCreatetime(new Date());
			bavo.setApprovetime(new Date());
			try {
				return addBankApprove(bavo);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	//通知推送报文解析
	@SuppressWarnings("rawtypes")
	public Notice parsePushNotice(String xml) throws DocumentException {
		// 拼接返回值
		boolean flg=true;
		Document doc = DocumentHelper.parseText(xml);
		Element bodyNode = doc.getRootElement();
		Notice notice = new Notice();
		if (bodyNode.hasContent()) {
			String ntcno = bodyNode.element("NTCNO")==null?"":bodyNode.element("NTCNO").getText();
			String ntctp = bodyNode.element("NTCTP").getText();
			String branchid = bodyNode.element("BRANCHID")==null?"":bodyNode.element("BRANCHID").getText();
			String ntcdate = bodyNode.element("NTCDATE").getText();
			String totnum=bodyNode.element("TOTNUM").getText();
			totnum = totnum==""?"0":totnum;
			if (ntctp.equals(ZXNoticeUtil.RECEIVING)) {//收货
				notice.setNtctp(1);
			} else if (ntctp.equals(ZXNoticeUtil.MOVE)) {//移库
				notice.setNtctp(2);
			} else if (ntctp.equals(ZXNoticeUtil.REMOVEPLEDGE)) {//解除质押
				notice.setNtctp(3);
			} else if (ntctp.equals(ZXNoticeUtil.CHANGE_FINANCING)) {//质物与融资变更
				notice.setNtctp(4);
			} else if(ntctp.equals(ZXNoticeUtil.WAREHOUSING)){//入库通知
				notice.setNtctp(5);
			}
			notice.setNtcno(ntcno);//通知编号
			notice.setNtcdate(DateUtil.StringToDate(ntcdate));//通知发送时间
			notice.setNtbranchid(branchid);//分行id
			notice.setNttotnum(Integer.parseInt(totnum));//记录条数。
			Notice nte=ndao.getNotice(notice);
			if(nte!=null){
				notice.setNid(nte.getNid());
				log.info("通知推送信息更新结果:["+update(notice)+"]");
			}else{
				log.info("通知推送信息添加结果:["+add(notice)+"]");
			}
			notice.setNttotnum(Integer.parseInt(totnum));//总记录条数
			//保存通知推送信息
			if (notice.getNtctp() ==4||notice.getNtctp()==5) {//质物入库与融资关系的变更通知//入库通知
				//保存通知推送明细
				PushNoticeDetail pnd = new PushNoticeDetail();
				Element list = bodyNode.element("list");
				List infos = list.elements("row");
				if (infos != null) {
					if(notice.getNtctp()==4){
						this.transactionBegin(ndao.getDataSourceName());//开启
					}
					for (Iterator it = infos.iterator(); it.hasNext();) {
						Element info = (Element) it.next();
						pnd.setId(notice.getNid());
						pnd.setPndecifcode(info.element("ECIFCODE").getText());
						pnd.setPndoperorg(info.element("OPERORG").getText());
						pnd.setPndvin(info.element("FIELD7").getText());//车架号
						pnd.setPndloancode(info.element("FIELD8").getText());// 融资编号
						pnd.setNtcno(notice.getNtcno());
						if(notice.getNtctp()==4){
							if(!saveDetail(pnd,4)){
								flg=false;
								break;
							}	
						}else{
							if(!gager(pnd)){
								flg=false;
								break;
							}
						}
					}
					if(notice.getNtctp()==4 && flg == true){
						this.transactionCommit(ndao.getDataSourceName());//提交
					}
				}
			}
		}
		if(!flg){
			notice.setNtfailflag(1);
		}else{
			notice.setNtfailflag(2);
		}
		return notice;
	}
	
	public boolean saveDetail(PushNoticeDetail pnd,int type) {
		PushNoticeDetailService punds = new PushNoticeDetailService();
		//车辆信息变更
		if(alteration(pnd,type)){//变更
			log.info("质物与融资关系变更结果成功");
			pnd.setState(1);
		}else{
			log.info("质物与融资关系变更结果失败");
			pnd.setState(2);
		}
		//推送详情保存
		if(punds.add(pnd)){
			log.info("质物与融资关系变更信息保存成功");
		}else{
			log.info("质物与融资关系变更信息保存失败");
			this.transactionRollback(ndao.getDataSourceName());//回滚
			return false;
		}
		return true;
	}

	/**
	 * 质物入库与融资关系的变更Method
	 */
	public boolean alteration(PushNoticeDetail pud,int type){
		SuperviseImportVO sivo = new SuperviseImportVO();
		sivo.setVin(pud.getPndvin());
		sivo.setDraft_num(pud.getPndloancode());
		return addSuperviseImport(sivo,type);
	}
	
	/**
	 * 根据入库通知修改入库车辆状态。Method
	 * @param pud
	 * @return
	 */
	public boolean gager(PushNoticeDetail pud){
		SuperviseImportVO sivo = new SuperviseImportVO();
		sivo.setVin(pud.getPndvin());
		sivo.setDraft_num(pud.getPndloancode());
		sivo.setState(2);
		return addSuperviseImport(sivo,5);
	}
	
	/**
	 * 输入框下拉选项列表
	 * @return
	 */
	public List<OptionObject> draftsOptions(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts = ndao.getListNtcno();
		if(drafts != null && drafts.size() > 0){
			for(String draftNum : drafts){
				option = new OptionObject(draftNum,draftNum);
				options.add(option);
			}
		}
		return options;
	}
	
	/**
	 * 手动读取远程通知信息详情
	 * @param ntcno
	 * @param ntctp
	 * @return
	 * @throws Exception
	 */
	public boolean FarQuery(String ntcno, int ntctp,HttpServletRequest request){
		Map<String,Object> msgMap=null;
		try {
			if (ntcno != null) {
				Map<String, Object> body = new HashMap<String, Object>();
				if (ntctp==1) {//收货通知
					body.put("action", "DLCDRGNQ");
					body.put("userName",userName);
					body.put("rvccmdntcNo", ntcno);
					log.info("收货通知回查:");
					msgMap=ZXBankInterfaceAction.NoticeSynchronous(1, body, "cmdinf", ReceivingNoticeFar.class,ReceivingDetailFar.class);
				} else if (ntctp==2) {//移库通知
					body.put("action", "DLCDTWNQ");
					body.put("userName",userName);
					body.put("mwntcNo", ntcno);
					log.info("移库通知回查:");
					msgMap=ZXBankInterfaceAction.NoticeSynchronous(2, body, "", MoveNoticeFar.class, MoveDetailFar.class);
				} else if (ntctp==3) {//解除质押通知
					body.put("action", "DLCDUINQ");
					body.put("userName",userName);
					body.put("rlsmgntcNo", ntcno);
					log.info("解除质押通知回查:");
					msgMap=ZXBankInterfaceAction.NoticeSynchronous(3, body, "rdmopr", RemovePledgeFar.class,RemovePledgeDetailFar.class);
				}
			}
		} catch (Exception e) {
			log.error("银企直连查询错误：",e);
			e.printStackTrace();
		}
		if(msgMap!=null){
			//从map中取出消息msg，执行结果boolean
			request.setAttribute("message",msgMap.get("message"));
			if(msgMap.get("boolean").toString().equals("true")){
				return true;
			}else{
				return false;
			}
		}else{
			log.info("执行返回为空");
			return false;
		}
	}
	
	
	
	/**
	 * 接收通知推送信息回查详情
	 * @param ntcno
	 * @param ntctp
	 * @return
	 * @throws Exception
	 */
	public boolean FarQuery(String ntcno, int ntctp){
		Map<String,Object> msgMap=null;
		try {
			if (ntcno != null) {
				Map<String, Object> body = new HashMap<String, Object>();
				if (ntctp==1) {//收货通知
					body.put("action", "DLCDRGNQ");
					body.put("userName",userName);
					body.put("rvccmdntcNo", ntcno);
					log.info("收货通知回查报文:");
					msgMap= ZXBankInterfaceAction.NoticeSynchronous(1, body, "cmdinf", ReceivingNoticeFar.class,ReceivingDetailFar.class);
				} else if (ntctp==2) {//移库通知
					body.put("action", "DLCDTWNQ");
					body.put("userName",userName);
					body.put("mwntcNo", ntcno);
					log.info("移库通知回查报文:");
					msgMap= ZXBankInterfaceAction.NoticeSynchronous(2, body, "", MoveNoticeFar.class, MoveDetailFar.class);
				} else if (ntctp==3) {//解除质押通知
					body.put("action", "DLCDUINQ");
					body.put("userName",userName);
					body.put("rlsmgntcNo", ntcno);
					log.info("解除质押通知回查报文:");
					msgMap= ZXBankInterfaceAction.NoticeSynchronous(3, body, "rdmopr", RemovePledgeFar.class,RemovePledgeDetailFar.class);
				}
			}
		} catch (Exception e) {
			log.error("银企直连查询错误：",e);
			e.printStackTrace();
		}
		if(msgMap!=null){
			if(msgMap.get("boolean").toString().equals("true")){
				return true;
			}else{
				return false;
			}
		}else{
			log.info("执行返回为空");
			return false;
		}
	}
//			String xml="<?xml version='1.0' encoding='GBK'?><stream><NTCNO>IN2100000039</NTCNO><NTCTP>STXTCIWH</NTCTP><BRANCHID></BRANCHID><NTCDATE>20210113155906</NTCDATE><TOTNUM>1</TOTNUM><FIELD1></FIELD1><FIELD2></FIELD2><FIELD3></FIELD3><FIELD4></FIELD4><FIELD5></FIELD5><FIELD6></FIELD6><list name='LST'><row><ECIFCODE></ECIFCODE><OPERORG></OPERORG><FIELD7>AWVAA156XEA000320</FIELD7><FIELD8>811368001053003</FIELD8></row></list></stream>";
	
}
package com.zd.csms.marketing.warning;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zd.csms.marketing.dao.IAgreementBackDAO;
import com.zd.csms.marketing.dao.IAgreementSendDAO;
import com.zd.csms.marketing.dao.IBusinessTransferDAO;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.model.AgreementSendVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.WarningTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

/**
 * 市场部预警
 *
 */
public class Warring {
	private IDealerDAO dao = MarketFactory.getIDealerDAO();
	private IAgreementBackDAO agreementBackDao = MarketFactory.getAgreementBackDAO();
	private DealerService dealerService = new DealerService();
	//private IAgreementSendDAO sendDao = MarketFactory.getAgreementSendDAO();
	private IBusinessTransferDAO btDao = MarketFactory.getIBusinessTransferDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();

	/**
	 * 进驻7天未交监管费提醒
	 * 
	 * @throws Exception
	 */
	public void inNotPay() throws Exception {
		List<DealerVO> list = dao.findDealerListByWarring();
		for (DealerVO dealerVO : list) {
			MessageUtil.addMsg(dealerVO.getCreateUserid(), "经销商" + dealerVO.getDealerName() + "进驻7天未交监管费",
					"/market/warring.do?method=inNotPay&id=" + dealerVO.getId(), 2,
					WarningTypeContant.INNOTPAY.getCode(),dealerVO.getCreateUserid());
		}
	}

	/**
	 * 协议到期提醒和预警
	 */
	public void agreementExpires() {
		List<AgreementBackVO> list = agreementBackDao.agreementExpires(30);
		Calendar cNow = Calendar.getInstance();
		cNow.setTime(new Date());
		cNow.add(Calendar.DAY_OF_MONTH, 7);
		for (AgreementBackVO vo : list) {
			DealerVO dealer = dealerService.get(vo.getDistribid());
			Date now = new Date();
			Date expireTime = vo.getAgreementexpiretime();
			String tip = "";
			int msgType = 2;
			int type = WarningTypeContant.AGREEMENTEXPIRES7.getCode();
			if (DateUtil.getStringFormatByDate(now, "yyyyMMdd")
					.equals(DateUtil.getStringFormatByDate(expireTime, "yyyyMMdd"))) {
				tip = "的协议今日过期";
			} else {
				msgType = 2;
				type = WarningTypeContant.AGREEMENTEXPIRES7.getCode();
				tip = "的协议将在" + DateUtil.getStringFormatByDate(expireTime, "yyyy年MM月dd日") + "到期";
			}

			MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + tip,
					"/agreementBack.do?method=agreementBackList", msgType, type,dealer.getCreateUserid());
			
			MessageUtil.sendMsg(new String[]{RoleConstants.MARKET_AMALDAR.getCode() + "",
					RoleConstants.MARKETMANAGEMENT_MINISTER.getCode() + ""}, "经销商" + dealer.getDealerName() + tip,
					"/agreementBack.do?method=agreementBackList", msgType, type,dealer.getCreateUserid());

		}
	}

	/**
	 * 协议到期未续签
	 */
	public void daoqi() {
		List<AgreementBackVO> list = agreementBackDao.daoqi();
		if (list != null)
			for (AgreementBackVO vo : list) {
				DealerVO dealer = dealerService.get(vo.getDistribid());
				MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + "协议到期未续签",
						"/agreementBack.do?method=agreementBackList", 2,
						WarningTypeContant.AGREEMENTEXPIRESNOTNEXT.getCode(),dealer.getCreateUserid());

				MessageUtil.sendMsg(new String[]{RoleConstants.MARKET_AMALDAR.getCode() + "",
						RoleConstants.MARKETMANAGEMENT_MINISTER.getCode() + ""}, "经销商" + dealer.getDealerName() + "协议到期未续签",
						"/agreementBack.do?method=agreementBackList", 2,
						WarningTypeContant.AGREEMENTEXPIRESNOTNEXT.getCode(),dealer.getCreateUserid());
			}
	}

	/**
	 * 协议邮寄30天未回收预警 30天
	 */
	public void agreementOverdue() {
		List<AgreementBackVO> list = agreementBackDao.findListByWarring(30);
		if (list != null)
			for (AgreementBackVO vo : list) {
				DealerVO dealer = dealerService.get(vo.getDistribid());
				MessageUtil.addMsg(dealer.getCreateUserid(),
						"经销商" + dealer.getDealerName() + "协议编号:" + vo.getAgreement_num() + "邮寄超过30天未回收",
						"/agreementBack.do?method=agreementBackList", 2,
						WarningTypeContant.AGREEMENTOVERDUE.getCode(),dealer.getCreateUserid());
				MessageUtil.sendMsg(new String[]{RoleConstants.MARKET_AMALDAR.getCode() + "",
						RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+""},
						"经销商" + dealer.getDealerName() + "协议编号:" + vo.getAgreement_num() + "邮寄超过30天未回收",
						"/agreementBack.do?method=agreementBackList", 2,
						WarningTypeContant.AGREEMENTOVERDUE.getCode(),dealer.getCreateUserid());
			}
	}

	/**
	 * 监管费到期前30天进行提醒
	 * 
	 * @throws Exception
	 */
	public void payDateBefore30() throws Exception {
		List<DealerVO> list = dao.payDateBefore(30);
		for (DealerVO dealer : list) {
			MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + "的监管费还有30天到期",
					"/market/warring.do?method=payDateBefore30&id=" + dealer.getId(), 2,
					WarningTypeContant.PAYDATEBEFORE30.getCode(),dealer.getCreateUserid());
		}
	}

	/**
	 * 发送进店流转单未按时进驻提醒
	 * 
	 * @throws Exception
	 */
	public void notApplyDate() throws Exception {
		List<BusinessTransferVO> list = btDao.findListByWarring();
		for (BusinessTransferVO vo : list) {
			DealerVO dealer = dealerService.get(vo.getDealerId());
			MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode() + ""}, "经销商" + dealer.getDealerName() + "进店流转单未按时进驻,请安排监管员",
					"/market/warring.do?method=notApplyDate&id=" + vo.getId(), 2,
					WarningTypeContant.NOTAPPLYDATE.getCode(),dealer.getCreateUserid());

		}

	}

	/**
	 * 协议到期7天提醒
	 */
	public void agreementExpires7() {
		List<AgreementBackVO> list = agreementBackDao.findListByWarring(7);
		for (AgreementBackVO vo : list) {
			DealerVO dealer = dealerService.get(vo.getDistribid());
			Date now = new Date();
			Date expireTime = vo.getAgreementexpiretime();
			String tip = "";
			if (now.compareTo(expireTime) >= 0) {
				tip = "的协议已过期";
			} else {
				tip = "的协议将在" + DateUtil.getStringFormatByDate(expireTime, "yyyy年MM月dd日") + "到期";
			}

			MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + tip,
					"/market/warring.do?method=agreementExpires15&id=" + vo.getId(), 2,
					WarningTypeContant.AGREEMENTEXPIRES7.getCode(),dealer.getCreateUserid());
		}
	}

	/**
	 * 监管费到期前15天
	 * 
	 * @throws Exception
	 */
	public void payDateBefore15() throws Exception {
		List<DealerVO> list = dao.payDateBefore(15);
		for (DealerVO dealer : list) {
			MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + "的监管费还有15天到期",
					"/market/warring.do?method=payDateBefore15&id=" + dealer.getId(), 2,
					WarningTypeContant.PAYDATEBEFORE15.getCode(),dealer.getCreateUserid());
		}
	}

	/**
	 * 监管费到期前一周预警
	 * 
	 * @throws Exception
	 */
	public void payDateBefore7() throws Exception {
		List<DealerVO> list = dao.payDateBefore(7);
		for (DealerVO dealer : list) {
			MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + "的监管费还有7天到期",
					"/market/warring.do?method=payDateBefore15&id=" + dealer.getId(), 2,
					WarningTypeContant.PAYDATEBEFORE7.getCode(),dealer.getCreateUserid());
		}
	}

	/**
	 * 监管费当天到期提醒
	 * 
	 * @throws Exception
	 */
	public void payDateExpire() throws Exception {
		List<DealerVO> list = dao.payDateBefore(0);
		for (DealerVO dealer : list) {
			MessageUtil.addMsg(dealer.getCreateUserid(), "经销商" + dealer.getDealerName() + "的监管费今日到期",
					"/market/warring.do?payDateExpire&id=" + dealer.getId(), 2,
					WarningTypeContant.PAYDATEEXPIRE.getCode(),dealer.getCreateUserid());
		}
	}

}

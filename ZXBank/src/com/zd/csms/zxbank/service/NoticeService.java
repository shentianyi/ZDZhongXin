package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import oracle.jdbc.driver.SQLUtil;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.MoveDetail;
import com.zd.csms.zxbank.bean.MoveNotice;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.csms.zxbank.dao.IMoveDetailDAO;
import com.zd.csms.zxbank.dao.IMoveNoticeDAO;
import com.zd.csms.zxbank.dao.INoticeDAO;
import com.zd.csms.zxbank.dao.IReceivingDetailDAO;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.csms.zxbank.dao.IRemovePledgeDetailDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.bean.MoveDetailFar;
import com.zd.csms.zxbank.web.bean.MoveNoticeFar;
import com.zd.csms.zxbank.web.bean.ReceivingDetailFar;
import com.zd.csms.zxbank.web.bean.ReceivingNoticeFar;
import com.zd.csms.zxbank.web.bean.RemovePledgeDetailFar;
import com.zd.csms.zxbank.web.bean.RemovePledgeFar;
import com.zd.tools.thumbPage.IThumbPageTools;
/**
 * 通知推送
 * @author caizhuo
 *
 */
public class NoticeService extends ServiceSupport{
	private INoticeDAO ndao = ZXBankDAOFactory.getnoticeDAO();
	
	private IReceivingNoticeDAO irndao=ZXBankDAOFactory.getReceivingNoticeDAO();
	private IReceivingDetailDAO irddao=ZXBankDAOFactory.getReceivingDetailDAO();
	
	private IMoveNoticeDAO imndao=ZXBankDAOFactory.getMoveNoticeDAO();
	private IMoveDetailDAO  imddao=ZXBankDAOFactory.getMoveDetailDAO();
	
	private IRemovePledgeDAO irpdao=ZXBankDAOFactory.getRemovePledgeDAO();
	private IRemovePledgeDetailDAO irpddao=ZXBankDAOFactory.getRemovePledgeDetailDAO();
	public List<Notice> findNotice(Notice notice,IThumbPageTools tools){
		return ndao.findNotice(notice, tools);
	}
	//查询通知类型
	public List<Notice> findnoticetype(){
		return ndao.findnoticetype();
	}
	
	public boolean addOrUpdate(Notice notice){
		
		System.out.println("推送接收到的通知："+notice);
		
		
		if(ndao.getNotice(notice)!=null){
			return update(notice);
		}else{
			return add(notice);
		}
	}
	
	private boolean add(Notice notice){
		notice.setNtId(SqlUtil.getID(Notice.class));
		return ndao.add(notice);
	}
	public boolean update(Notice notice){
		notice.setNtEnddate(new Date());
		System.out.println("-通知推送更新信息-");
		return ndao.update(notice);
	}
	
	/**
	 * 通知推送表及通知详情保存保存
	 * @param noticeType
	 * @param resultList
	 * @return
	 */
	public boolean saveNotice(int noticeType,List<Object> resultList,Object bean){
		//根据类型不同
		if(noticeType==1){
			System.out.println("--收货通知--");
			//保存收货通知
			ReceivingNoticeFar rnf=(ReceivingNoticeFar)bean;
			ReceivingNotice rn=new ReceivingNotice();
			rn.setNyId(SqlUtil.getID(ReceivingNotice.class));
			rn.setNyNo(rnf.getRcvcmdntcNo());//通知书编号
			rn.setNyCorentnm(rnf.getCorentNm());
			rn.setNyCsndate(DateUtil.StringToDate(rnf.getCsnDate()));
			rn.setNyEpa(rnf.getEpa());
			rn.setNyEta(DateUtil.StringToDate(rnf.getEtaDate()));
			rn.setNyExcplace(rnf.getExcPlace());
			rn.setNyLonentname(rnf.getLonentNm());
			rn.setNyLonentno(rnf.getLonentId());
			rn.setNyNtcdate(DateUtil.StringToDate(rnf.getNtcDate()));
			rn.setNyOfflnsatno(rnf.getOfflnsatNo());
			rn.setNyOnwspvenm(rnf.getOnwspveNm());
			rn.setNyRemark(rnf.getRemark());
			rn.setNySpventnm(rnf.getSpventNm());
			rn.setNyTotnum(rnf.getTotnum());
			rn.setNyTrsptentnm(rnf.getTrsptentNm());
			rn.setNyTtlcmdval(rnf.getTtlcmdval());
			rn.setNyCreatedate(new Date());
			if(irndao.add(rn)){
				System.out.println("--保存成功--");
			}
			//保存收货通知详情
			for (int i = 0; i < resultList.size(); i++) {
				ReceivingDetail rd=new ReceivingDetail();
				ReceivingDetailFar rdf=(ReceivingDetailFar) resultList.get(i);
				rd.setNdId(SqlUtil.getID(ReceivingDetail.class));
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
				if(irddao.add(rd)){
					System.out.println("--详情保存成功--");
				}
			}
			
		}else if(noticeType==2){
			System.out.println("--移库通知--");
			//保存移库通知
			MoveNoticeFar mnf=(MoveNoticeFar)bean;
			System.out.println(mnf.toString());
			MoveNotice mn=new MoveNotice();
			mn.setMnId(SqlUtil.getID(MoveNotice.class));
			mn.setMnLoncpname(mnf.getLonentNm());
			mn.setMnMovedate(mnf.getMwapyDate());
			mn.setMnNo(mnf.getMwntcNo());
			mn.setMnNoticedate(mnf.getNtcDate());
			mn.setMnOperorg(mnf.getOperOrg());
			mn.setMnSupervisename(mnf.getSpventnm());
			mn.setMnTotnum(mnf.getTotnum());
			mn.setMnCreatedate(new Date());
			if(imndao.add(mn)){
				System.out.println("--保存成功--");
			}
			//保存移库通知详情
			for (int i = 0; i < resultList.size(); i++) {
				MoveDetailFar  mdf=(MoveDetailFar)resultList.get(i);
				MoveDetail mdt=new MoveDetail();
				mdt.setMdId(SqlUtil.getID(MoveDetail.class));
				mdt.setMdCarprice(mdf.getCarPrice());
				mdt.setMdCertificationno(mdf.getHgzNo());
				mdt.setMdChassisno(mdf.getVin());
				mdt.setMdMovenumber(mdf.getStkNum());
				mdt.setMdNo(mnf.getMwntcNo());
				mdt.setMdRemoveinno(mdf.getIwCode());
				mdt.setMdRemoveoutno(mdf.getMwCode());
				mdt.setMdWareno(mdf.getCmdCode());
				if(imddao.add(mdt)){
					System.out.println("--详情保存成功--");
				}
			}
		}else if(noticeType==3){
			System.out.println("解除质押通知");
			//保存解除质押通知
			RemovePledgeFar rpgf=(RemovePledgeFar)bean;
			RemovePledge rpg=new RemovePledge();
			rpg.setRpId(SqlUtil.getID(RemovePledge.class));
			rpg.setRpContent(rpgf.getOstkrsn());
			rpg.setRpCorename(rpgf.getCorentNm());
			rpg.setRpLoncpid(rpgf.getLoaentId());
			rpg.setRpLoncpname(rpgf.getLonentNm());
			rpg.setRpNo(rpgf.getRlsmgntcNo());//通知书编号
			rpg.setRpNoticedate(rpgf.getNtcDate());
			rpg.setRpOperorg(rpgf.getOperOrg());
			rpg.setRpPldegeename(rpgf.getMtgpsnNm());
			rpg.setRpRelievepddate(rpgf.getRlsmgDate());
			rpg.setRpSupervisename(rpgf.getSpventNm());
			rpg.setRpCreatedate(new Date());
			if(irpdao.add(rpg)){
				System.out.println("---保存成功----");
			}
			//保存解除质押通知详情
			for (int i = 0; i < resultList.size(); i++) {
				RemovePledgeDetailFar rpdf=(RemovePledgeDetailFar)resultList.get(i);
				RemovePledgeDetail rpd=new RemovePledgeDetail();
				rpd.setRdId(SqlUtil.getID(RemovePledgeDetail.class));
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
					System.out.println("--详情保存成功");
				}
			}
		}
		return true;
	}
}

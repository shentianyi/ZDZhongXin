package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.dao.IMoveDetailDAO;
import com.zd.csms.zxbank.dao.IMoveNoticeDAO;
import com.zd.csms.zxbank.dao.INoticeDAO;
import com.zd.csms.zxbank.dao.IReceivingDetailDAO;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.csms.zxbank.dao.IRemovePledgeDetailDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
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
	/**
	 * 通知推送表及通知详情保存保存
	 * @param noticeType
	 * @param resultList
	 * @return
	 */
	public boolean save(int noticeType,List<Object> resultList,Object bean){
		//根据类型不同
		if(noticeType==1){
			System.out.println("收货通知");
			System.out.println(resultList);
			System.out.println(bean);
			//保存收货通知
			irndao.add(bean);
			//保存收货通知详情
			for (int i = 0; i < resultList.size(); i++) {
				irddao.add(resultList.get(i));
			}
			
			
		}else if(noticeType==2){
			System.out.println("移库通知");
			System.out.println(resultList);
			System.out.println(bean);
			//保存移库通知
			imndao.add(bean);
			//保存移库通知详情
			for (int i = 0; i < resultList.size(); i++) {
				imddao.add(resultList.get(i));
			}
		}else if(noticeType==3){
			System.out.println("解除质押通知");
			System.out.println(resultList);
			System.out.println(bean);
			//保存解除质押通知
			irpdao.add(bean);
			//保存解除质押通知详情
			for (int i = 0; i < resultList.size(); i++) {
				irpddao.add(resultList.get(i));
			}
		}
		
		
		return true;
	}
}

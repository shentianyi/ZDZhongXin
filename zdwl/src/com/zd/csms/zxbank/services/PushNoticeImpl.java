package com.zd.csms.zxbank.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;

import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.service.NoticeService;
import com.zd.csms.zxbank.util.GZipFileUtil;
import com.zd.csms.zxbank.util.ZXNoticeUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.csms.zxbank.web.bean.MoveDetailFar;
import com.zd.csms.zxbank.web.bean.MoveNoticeFar;
import com.zd.csms.zxbank.web.bean.ReceivingDetailFar;
import com.zd.csms.zxbank.web.bean.ReceivingNoticeFar;
import com.zd.csms.zxbank.web.bean.RemovePledgeDetailFar;
import com.zd.csms.zxbank.web.bean.RemovePledgeFar;

/**
 * 通知推送WS实现
 * @author duyong
 */
public class PushNoticeImpl implements IPushNotice {
	private static Log log = LogFactory.getLog(PushNoticeImpl.class);
	@Override
	public String pushNotice(String xml) {
		try {
			final String pushXml= GZipFileUtil.unGZip(ZXNoticeUtil.pushNoticeParse(xml));//第一阶段解析通知报文xml
			String backhaul=ZXNoticeUtil.backhaulxml(pushXml);//校验
			if(backhaul.equals("success")){//校验成功
				Thread th=new Thread(new Runnable() {
					@Override
					public void run() {
						push(pushXml);
					}
				});
				th.start();
			}
			return backhaul;
		}  catch (Exception e) {
			log.info("解析或校验错误",e);
			e.printStackTrace();
			return "服务器内部错误,请您稍后再试";
		}
	}
	
	private static void push(String pushXml){
		NoticeService ns = new NoticeService();
		Notice notice = new Notice();
		try{
			notice = ns.parsePushNotice(pushXml);// 1.第二阶段解析通知xml报文
			if(notice.getNtctp()!=4){
				if (ns.FarQuery(notice.getNtcno(),notice.getNtctp())) {//直连查询
					notice.setNtfailflag(2);
					ns.update(notice);
					log.info("[详情接收成功并更新接收成功状态]");
				} else {
					log.info("[详情接收查询失败更新接收失败状态]");
					notice.setNtfailflag(1);
					ns.update(notice);
				}
			}else{
				ns.update(notice);
				log.info("[详情接收成功并更新接收成功状态]");
			}
			
		} catch (Exception e) {
			log.error("解析及查询错误：",e);
			e.printStackTrace();
		}
	}
}

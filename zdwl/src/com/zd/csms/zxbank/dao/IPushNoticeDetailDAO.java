package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.PushNoticeDetail;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IPushNoticeDetailDAO extends IDAO {
	/**
	 * 分页查询所有
	 */
	public List<PushNoticeDetail> findNotice(PushNoticeDetail query,IThumbPageTools tools);
	/**
	 * 添加
	 */
	//public boolean add(PushNoticeDetail pnd);
	public List<PushNoticeDetail> findPushNoticeDetail();
	public List<String> getListNtcno();
}

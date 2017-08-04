package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.querybean.AgreementQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IAgreementBackDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<AgreementBackVO> searchAgreementBackList(AgreementBackQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<AgreementQueryBean> searchAgreementBackListByPage(AgreementBackQueryVO query,IThumbPageTools tools);
	
	/**
	 * 监管协议到期前30天提醒
	 * @param query
	 * @return
	 */
	public List<AgreementBackVO> findListByWarring(int day);
	
	/**
	 * 协议到期预警
	 * @return
	 */
	public List<AgreementBackVO> agreementExpires(int day);
	
	/**
	 * 协议到期未续期
	 * @param day
	 * @return
	 */
	public List<AgreementBackVO> daoqi();
	/*
	 * 需求38 -- 导出协议管理台账
	 * @time 20170518
	*/
	public List<AgreementQueryBean> ExtAgreementLedger(AgreementBackQueryVO asQuery);
	public boolean updBackDateAndIsback(AgreementBackVO abvo);
}

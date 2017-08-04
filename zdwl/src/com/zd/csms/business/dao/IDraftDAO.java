package com.zd.csms.business.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.queryBean.DraftQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IDraftDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<DraftVO> searchDraftList(DraftQueryVO query);
	public List<DraftQueryBean> findListByNowDate(int type);
	public List<DealerQueryBean> findListDealer(int type);
	public List<CarInfoQueryBean> findListCar(int type);
	public List<DraftQueryBean> findListByNowDateNoFull();
	public List<String> searchDraftList();
	public List<String> findDraftToLncis();
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<DraftQueryBean> searchDraftListByPage(DraftQueryVO query,IThumbPageTools tools);
	
	public List<DraftVO> searchDraftListById(int userid,int type);
	
	public List<String> findDistribIds(int userid);
	
	public List<String> findDraftIds();
	
	/**
	 * 验证此汇票是重复
	 * @param draftNum
	 * @return true代表重复，flase代表不重复
	 */
	public boolean validateDraftIsRepeat(String draftNum);
	public List<String> findDraftNumByRole(UserVO user,int role);
	public DraftVO findDraftByDraftNum(String draftNum);
}

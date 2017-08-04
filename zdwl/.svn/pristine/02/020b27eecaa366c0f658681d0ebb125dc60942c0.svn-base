package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.AbnormalQueryBean;
import com.zd.csms.supervisory.model.AbnormalQueryVO;
import com.zd.csms.supervisory.model.AbnormalVO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 异常事件/异常数据
 *
 */
public interface IAbnormalDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<AbnormalQueryBean> findList(AbnormalQueryVO query,IThumbPageTools tools);
	
	/**
	 * 根据经销商Id查询异常数量
	 * @param id
	 * @return
	 */
	public int findCountByDealerId(int id);
	
	public List<AbnormalVO> getAbnormalListByDealerId(int dealerId);
}

package com.zd.csms.business.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IDraftDAO;
import com.zd.csms.business.dao.mapper.DraftMapper;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.queryBean.DraftQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.web.action.SuperviseImportAction;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class DraftService extends ServiceSupport {

	private static Log log = LogFactory.getLog(DraftService.class);
	private IDraftDAO dao = BusinessDAOFactory.getDraftDAO();
	private DecimalFormat df = new DecimalFormat("0.00");
	public boolean addDraft(DraftVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(DraftVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean addDraftList(List<DraftVO> list){
		boolean bool = true;
		try {
			for (DraftVO draftVO : list) {
				bool = addDraft(draftVO);
				if(!bool){
					transactionRollback(dao.getDataSourceName());
					return bool;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return bool;
	}
	
	public boolean updDraft(DraftVO vo) throws Exception {
		DraftVO draft = loadDraftById(vo.getId());
		draft.setAgreement(vo.getAgreement());
		draft.setBail_num(vo.getBail_num());
		draft.setBilling_date(vo.getBilling_date());
		draft.setDue_date(vo.getDue_date());
		draft.setBilling_money(vo.getBilling_money());
		draft.setState(vo.getState());
		draft.setUpddate(vo.getUpddate());
		draft.setUpduserid(vo.getUpduserid());
		draft.setBailscale(vo.getBailscale());
		return dao.update(draft);
	}
	
	public boolean deleteDraft(int id) throws Exception {
		return dao.delete(DraftVO.class, id);
    }
	
	public DraftVO loadDraftById(int id) throws Exception{
		DraftVO draft = dao.get(DraftVO.class, id,new DraftMapper());
		return draft;
	}
	
	public List<DraftVO> searchDraftList(DraftQueryVO query){
		return dao.searchDraftList(query);
	}
	public List<DraftQueryBean> findListByNowDate(int type){
		return dao.findListByNowDate(type);
	}
	public List<DealerQueryBean> findListDealer(int type){
		return dao.findListDealer(type);
	}
	public List<CarInfoQueryBean> findListCar(int type){
		return dao.findListCar(type);
	}
	public List<DraftQueryBean> findListByNowDateNoFull(){
		return dao.findListByNowDateNoFull();
	}
	
	public List<String> searchDraftList(){
		return dao.searchDraftList();
	}
	
	public List<DraftQueryBean> searchDraftListByPage(DraftQueryVO vo, IThumbPageTools tools) throws Exception{
		List<DraftQueryBean> list = dao.searchDraftListByPage(vo, tools);
		if(list!=null&&list.size()>0)
			for (DraftQueryBean bean : list) {
				if(NumberUtils.isNumber(bean.getBilling_money())){
					Double bm = df.parse(bean.getBilling_money()).doubleValue();
					//敞口金额(敞口金额设置公式=开票金额*（1-保证金比例）-回款金额。)
					BigDecimal  aDecimal  = new BigDecimal(100-Double.valueOf(bean.getBailscale()));
					BigDecimal  bs  = new BigDecimal(100);
					bean.setCkMoney(bm*(aDecimal.divide(bs).doubleValue())-bean.getHkMoney());
					bean.setWycMoney(bm-bean.getYycMoney());
				}
			}
		return list;
	}
	
	public List<DraftVO> searchDraftListById(int userid, int type){
		return dao.searchDraftListById(userid,type);
	}
	
	public List<String> findDistribIds(int userid) {
		return dao.findDistribIds(userid);
	}
	public List<String> findDraftIds() {
		return dao.findDraftIds();
	}
	
	public List<String> findDraftToLncis(){
		return dao.findDraftToLncis();
	}
	
	/**
	 * 验证此汇票是重复
	 * @param draftNum
	 * @return true代表重复，flase代表不重复
	 */
	public boolean validateDraftIsRepeat(String draftNum){
		return dao.validateDraftIsRepeat(draftNum);
	}
	
	/**
	 * 根据不同的角色获取不同的汇票信息
	 * @return
	 */
	public List<String> findDraftNumByRole(UserVO user,int role){
		return dao.findDraftNumByRole(user,role);
	}
	
	/**
	 * 根据汇票号码查询汇票
	 * @return
	 */
	public DraftVO findDraftByDraftNum(String draftNum){
		return dao.findDraftByDraftNum(draftNum);
	}
}

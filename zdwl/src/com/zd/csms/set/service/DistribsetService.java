package com.zd.csms.set.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;
import com.zd.csms.set.model.DistribsetQueryVO;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.model.ExtendDistribsetVO;
import com.zd.csms.util.Util;
import com.zd.csms.windcontrol.model.InspectionInfoVO;

public class DistribsetService extends ServiceSupport {

	private static final DistribsetService DISTRIBSET_SERVICE = new DistribsetService();

	public static DistribsetService getInstance() {
		return DISTRIBSET_SERVICE;
	}

	private IDistribsetDAO dao = SetDAOFactory.getDistribsetDAO();

	public boolean addDistribset(DistribsetVO vo) throws Exception {

		boolean flag = false;
		if (vo.getZsCustNo() != null && vo.getZsCustNo() != "") {
			// 验证浙商银行客户号唯一性
			flag = validateZsCustNo(vo.getZsCustNo(), vo.getId());
			if (!flag) {
				this.setExecuteMessage("浙商银行客户号不能重复，请重新输入！");
				return flag;
			}
		}
		vo.setId(Util.getID(DistribsetVO.class));
		flag = dao.add(vo);
		if (flag) {
			this.setExecuteMessage("添加成功！");
		} else {
			this.setExecuteMessage("添加失败！");
		}
		return flag;
	}

	public boolean updDistribset(DistribsetVO vo) throws Exception {
		boolean flag = false;
		if (vo.getZsCustNo() != null && vo.getZsCustNo() != "") {
			// 验证浙商银行客户号唯一性
			flag = validateZsCustNo(vo.getZsCustNo(), vo.getId());
			if (!flag) {
				this.setExecuteMessage("浙商银行客户号不能重复，请重新输入！");
				return flag;
			}
		}
		flag = dao.update(vo);
		if (flag) {
			this.setExecuteMessage("修改成功！");
		} else {
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}

	public DistribsetVO loadDistribsetById(int id) throws Exception {
		DistribsetVO distribset = dao.get(DistribsetVO.class, id,
				new BeanPropertyRowMapper(DistribsetVO.class));
		return distribset;
	}

	public List<DistribsetVO> searchDistribsetList(DistribsetQueryVO query) {
		return dao.searchDistribsetList(query);
	}

	public DistribsetVO loadDistribsetByTwo(int distribId) {

		DistribsetQueryVO query = new DistribsetQueryVO();
		query.setDistribid(distribId);
		List<DistribsetVO> list = dao.searchDistribsetList(query);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 浙商银行客户号唯一性校验
	 * 
	 * @param zsCustNo
	 * @param distribsetId
	 * @return
	 */
	public boolean validateZsCustNo(String zsCustNo, int distribsetId) {
		boolean flag = true;
		DistribsetQueryVO query = new DistribsetQueryVO();
		query.setZsCustNo(zsCustNo);
		List<DistribsetVO> distribsetList = dao.searchDistribsetList(query);
		if (distribsetList != null && distribsetList.size() > 0) {
			for (DistribsetVO distribset : distribsetList) {
				if (distribset.getId() != distribsetId) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 根据客户号获取经销商ID
	 * 
	 * @param custNo
	 * @return
	 */
	public int findDistidByCustNo(String custNo) {
		return dao.findDistidByCustNo(custNo);
	}

	/**
	 * 根据经销商Id获取DistribsetVO
	 * 
	 * @param id
	 * @return
	 */
	public DistribsetVO getDistribsetVOByDistribid(int id) {
		return dao.getDistribsetVOByDistribid(id);
	}

	/**
	 * 根据客户号同步合同编号
	 * 
	 * @param contractNo
	 * @param zsCustNo
	 * @return
	 */
	public boolean updateContractNoByZsCustNo(String contractNo, String zsCustNo) {
		return dao.updateContractNoByZsCustNo(contractNo, zsCustNo);
	}

	/**
	 * 验证此经销商是否是对接银行
	 * 
	 * @return
	 */
	public boolean validateDealer(int dealerId) {
		return dao.validateDealer(dealerId);
	}

	public ExtendDistribsetVO getExtendDistribsetVOById(int id) {
		return dao.get(ExtendDistribsetVO.class, id, new BeanPropertyRowMapper(
				ExtendDistribsetVO.class));
	}

	public boolean addExtendDistribset(ExtendDistribsetVO vo) throws Exception {
		boolean flag = false;
		flag = dao.add(vo);
		return flag;
	}

	public boolean updExtendDistribset(ExtendDistribsetVO vo) throws Exception {
		boolean flag = false;
		flag = dao.update(vo);
		return flag;
	}

	public DealerVO getDealerEquipmentProvideAndCredit(int dealerId) {
		return dao.getDealerEquipmentProvideAndCredit(dealerId);
	}

	public boolean updDealerEquipmentProvideAndCredit(DealerVO query) {
		return dao.updDealerEquipmentProvideAndCredit(query);
	}

	/**
	 * 输入框下拉选项列表
	 * 
	 * @return
	 */
	public List<OptionObject> draftsOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts = new ArrayList<String>();
		List<String> list = dao.getListZxOrgCode();
		if (list != null && list.size() > 0) {
			for (String string : list) {
				if (string != null)
					if (!string.trim().isEmpty())
						drafts.add(string);
			}
		}
		if (drafts != null && drafts.size() > 0) {
			for (String draftNum : drafts) {
				option = new OptionObject(draftNum, draftNum);
				options.add(option);
			}
		}
		return options;
	}

}

package com.zd.csms.bank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.bank.model.BankQueryVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IBankDAO extends IDAO{

	/**
	 * 根据parentId获取子集合
	 * @author licheng at 2016年7月14日 上午11:51:41
	 * @param id
	 * @return
	 */
	public List<BankVO> findChildListById(int id);
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BankVO> findBankList(BankQueryVO query,IThumbPageTools tools);
	
	/**
	 * 查询银行和其子银行的名称，用逗号隔开
	 * @param query
	 * @return
	 */
	public String findBankNameById(BankQueryVO query);
	
	/**
	 * 根据经销商名称查询银行数量
	 * @param bankName
	 * @return
	 */
	public int findCountByBankName(String bankName,int parentId);
	
	/**
	 * 级联删除
	 * @param id
	 * @return
	 */
	public boolean deleteBank (int id);
	
	
	/**
	 * 支行管理 已选列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BankVO> findChildrenBankIn(BankQueryVO query,IThumbPageTools tools);
	
	/**
	 * 支行管理未选列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BankVO> findChildrenBankOut(BankQueryVO query,IThumbPageTools tools);
	
	/**
	 * 删除分行银行管理支行
	 * @param parentId
	 * @param childId
	 * @return
	 */
	public boolean deleteBankChildren(int parentId,int childId);

	/**
	 * 根据银行ID查询银行名称
	 * @param bankId
	 * @return
	 */
	public String getBankNameById(int bankId);

	public BankVO getTopBank(int bankId);
}

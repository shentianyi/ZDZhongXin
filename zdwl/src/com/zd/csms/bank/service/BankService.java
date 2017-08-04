package com.zd.csms.bank.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.bank.contants.BankContants;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.bank.model.BankChildrenManagerVO;
import com.zd.csms.bank.model.BankQueryVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class BankService extends ServiceSupport{
	private IBankDAO dao= BankDAOFactory.getBankDAO();
	
	public boolean add(BankVO bank) throws Exception{
		bank.setId(Util.getID(BankVO.class));
		if(bank.getBankType()==BankContants.MAIN_BANK.getCode()){
			bank.setBankFullName(bank.getBankName());
		}else{
			BankQueryVO query = new BankQueryVO();
			query.setId(bank.getParentId());
			String fullName = dao.findBankNameById(query);
			fullName = fullName.replace(",","/")+"/"+bank.getBankName();
			bank.setBankFullName(fullName);
		}
		return dao.add(bank);
	}
	
	public boolean addBankChildren(BankChildrenManagerVO bcm) throws Exception{
		bcm.setId(Util.getID(BankChildrenManagerVO.class));
		return dao.add(bcm);
	}
	
	
	public boolean update(BankVO bank){
		BankVO newBank = get(bank.getId());
		newBank.setBankName(bank.getBankName());

		if(newBank.getBankType()==BankContants.MAIN_BANK.getCode()){
			newBank.setBankFullName(bank.getBankName());
		}else{
			BankQueryVO query = new BankQueryVO();
			query.setId(newBank.getParentId());
			String fullName = dao.findBankNameById(query);
			fullName = fullName.replace(",","/")+"/"+bank.getBankName();
			newBank.setBankFullName(fullName);
		}
		
		return dao.update(newBank);
	}
	
	public boolean delete(int id){
		return dao.deleteBank(id);
	}
	
	public BankVO get(int id){
		return dao.get(BankVO.class, id, new BeanPropertyRowMapper(BankVO.class));
	}
	
	/**
	 * 根据parentId获取子集合
	 * @author licheng at 2016年7月14日 上午11:51:41
	 * @param id
	 * @return
	 */
	public List<BankVO> findChildListById(int id){
		return dao.findChildListById(id);
	}
	
	public List<BankVO> findBankList(BankQueryVO bankQuery,IThumbPageTools tools){
		return dao.findBankList(bankQuery, tools);
	}
	
	/**
	 * 查询银行和其子银行的名称，用逗号隔开
	 * @param query
	 * @return
	 */
	public String findBankNameById(BankQueryVO query){
		return dao.findBankNameById(query);
	}
	/**
	 * 根据银行ID查询银行名称
	 * @param query
	 * @return
	 */
	public String getBankNameById(int bankId){
		return dao.getBankNameById(bankId);
	}
	public int findCountByBankName(String bankName,int parentId){
		return dao.findCountByBankName(bankName, parentId);
	}
	
	/**
	 * 支行管理 已选列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BankVO> findChildrenBankIn(BankQueryVO query,IThumbPageTools tools){
		return dao.findChildrenBankIn(query, tools);
	}
	
	/**
	 * 支行管理未选列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BankVO> findChildrenBankOut(BankQueryVO query,IThumbPageTools tools){
		return dao.findChildrenBankOut(query, tools);
	}
	
	/**
	 * 删除分行银行管理支行
	 * @param parentId
	 * @param childId
	 * @return
	 */
	public boolean deleteBankChildren(int parentId,int childId){
		return dao.deleteBankChildren(parentId, childId);
	}
	
	public Integer[] convertIntegerArray(List<Integer> list){
		if(list.size()==0){
			return new Integer[]{};
		}
		Integer[] array = new Integer[list.size()];
		for (int i = 0; i < list.size();i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public BankVO getTopBank(int bankId) {
		return dao.getTopBank(bankId);
	}
}

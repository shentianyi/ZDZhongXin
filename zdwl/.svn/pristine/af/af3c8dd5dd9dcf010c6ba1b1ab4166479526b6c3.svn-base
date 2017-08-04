package com.zd.csms.bank.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankManagerDao;
import com.zd.csms.bank.model.BankManagerQueryVO;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.csms.util.Util;


public class BankManagerService extends ServiceSupport{
	private IBankManagerDao bankManagerDao = BankDAOFactory.getBankManagerDAO();
	
	public boolean add(BankManagerVO vo) throws Exception{
		vo.setId(Util.getID(BankManagerVO.class));
		return bankManagerDao.add(vo);
	}
	
	public boolean update(BankManagerVO vo){
		BankManagerVO bean = get(vo.getId());
		bean.setManager(vo.getManager());
		bean.setManagerPhone(vo.getManagerPhone());
		return bankManagerDao.update(bean);
	}
	
	public BankManagerVO get(int id){
		return bankManagerDao.get(BankManagerVO.class, id, new BeanPropertyRowMapper(BankManagerVO.class));
	}
	
	public boolean delete(int id){
		return bankManagerDao.delete(BankManagerVO.class, id);
	}
	
	/**
	 * 列表查询
	 * @param query
	 * @return
	 */
	public List<BankManagerVO> findList(BankManagerQueryVO query){
		return bankManagerDao.findList(query);
	}
}

package com.zd.csms.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.finance.dao.FinanceDAOFactory;
import com.zd.csms.finance.dao.ISupervisionfeeDAO;
import com.zd.csms.finance.dao.mapper.SupervisionfeeMapper;
import com.zd.csms.finance.model.SupervisionfeeQueryVO;
import com.zd.csms.finance.model.SupervisionfeeVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SupervisionfeeService extends ServiceSupport {

	private ISupervisionfeeDAO dao = FinanceDAOFactory.getSupervisionfeeDAO();
	
	public boolean addSupervisionfee(SupervisionfeeVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(SupervisionfeeVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updSupervisionfee(SupervisionfeeVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteSupervisionfee(int id) throws Exception {
		return dao.delete(SupervisionfeeVO.class, id);
    }
	
	public SupervisionfeeVO loadSupervisionfeeById(int id) throws Exception{
		SupervisionfeeVO supervisionfee = dao.get(SupervisionfeeVO.class, id,new SupervisionfeeMapper());
		return supervisionfee;
	}
	
	public List<SupervisionfeeVO> searchSupervisionfeeList(SupervisionfeeQueryVO query){
		return dao.searchSupervisionfeeList(query);
	}
	
	public List<SupervisionfeeVO> searchSupervisionfeeListByPage(SupervisionfeeQueryVO vo, IThumbPageTools tools){
		return dao.searchSupervisionfeeListByPage(vo, tools);
	}
}

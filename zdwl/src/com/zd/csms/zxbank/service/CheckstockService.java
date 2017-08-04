package com.zd.csms.zxbank.service;

import java.util.List;

import oracle.jdbc.driver.SQLUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Check;
import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.CheckstockVO;
import com.zd.csms.zxbank.bean.Checkwarehouse;
import com.zd.csms.zxbank.dao.ICheckstockDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 质物入库Service
 */
public class CheckstockService extends ServiceSupport {
	private ICheckstockDAO dao = ZXBankDAOFactory.getCheckstockDAO();
	private static Log log=LogFactory.getLog(CheckstockService.class);
	
	
	public List<Checkstock> findBusinessList(Checkstock query, IThumbPageTools tools) {
		return dao.findAllList(query, tools);
	}

	public Checkstock getCheckstock(int csid) {
		return dao.getCheckstock(csid);
	}

	public List<CheckstockVO> findAllVOList(int id, IThumbPageTools tools) {
		return dao.findAllVOList(id, tools);
	}

	public boolean addCS(Checkstock cs) {
		cs.setCsId(SqlUtil.getID(Checkstock.class));
		return dao.add(cs);
	}

	public boolean addList(List<?> lists) {
		try {
			for (Object object : lists) {
				dao.add(object);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean addCheckwarehouseLst(List<Checkwarehouse> wlist,int ckID){
		try {
			for (Checkwarehouse checkwarehouse : wlist) {
				checkwarehouse.setChId(SqlUtil.getID(Checkwarehouse.class));
				checkwarehouse.setChCsid(ckID);
				//添加
				dao.add(checkwarehouse);
				//更新
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean addCheckList(List<Check> clist,int ckID) {
		try {
			for (Check check : clist) {
				check.setCkId(SqlUtil.getID(Check.class));
				check.setCkCsid(ckID);
				//添加
				dao.add(check);
				//更新
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean save(Checkstock check, List<Checkwarehouse> wlist, List<Check> clist){
		if(addCS(check)){
			int ckID = check.getCsId();  
			if(addCheckwarehouseLst(wlist,ckID)){
			}else{
				return false;
			}
			if(addCheckList(clist,ckID)){
			}else{
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	public boolean saveCheckstock(Checkstock check, List<Checkwarehouse> wlist, List<Check> clist){
		log.info("盘库信息保存及更新开始,事务开启：["+this.transactionBegin(dao.getDataSourceName())+"]");
		if(save(check, wlist, clist)){
			log.info("结束，事务提交：["+this.transactionCommit(dao.getDataSourceName())+"]");
			return true;
		}else{
			log.info("结束，事务回滚:["+this.transactionRollback(dao.getDataSourceName())+"]");
			return false;
		}
	}
	
	
}

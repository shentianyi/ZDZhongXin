package com.zd.csms.supervisory.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.dao.ISuperviseImportDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.SuperviseImportMapper;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.querybean.CarSummary;
import com.zd.csms.supervisory.querybean.ExportCarInfoBean;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SuperviseImportService extends ServiceSupport {

	private ISuperviseImportDAO dao = SupervisorDAOFactory.getSuperviseImportDAO();
	
	public boolean addSuperviseImport(SuperviseImportVO vo) throws Exception {
		
		boolean flag = false;
		if(StringUtil.isEmpty(vo.getDraft_num())){
			super.setExecuteMessage("汇票号不能为空！");
			return false;
		}
		
		vo.setId(Util.getID(SuperviseImportVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean addSuperviseImportList(List<SuperviseImportVO> list,String draftNum,UserVO user){
		try {
			for (int i = 0; i < list.size(); i++) {
				SuperviseImportVO vo = list.get(i);
				SuperviseImportVO car = this.findByVin(vo.getVin());
				if(car == null){//新增车辆
					vo.setDraft_num(draftNum);
					vo.setState(1);
					vo.setApply(0);
					vo.setImporttime(new Date());
					vo.setCertificate_intime(new Date());
					vo.setCreateuserid(user.getId());
					vo.setCreatedate(new Date());
					vo.setUpddate(new Date());
					vo.setUpduserid(user.getId());
					this.addSuperviseImport(vo);
				}else{
//					if(car.getCertificate_date() == null && vo.getCertificate_date() != null){
						car.setCertificate_date(vo.getCertificate_date());
						car.setImporttime(new Date());
//					}
//					if(StringUtil.isEmpty(car.getCertificate_num()) && !StringUtil.isEmpty(vo.getCertificate_num())){
						car.setCertificate_num(vo.getCertificate_num());
//					}
//					if(StringUtil.isEmpty(car.getCar_model()) && !StringUtil.isEmpty(vo.getCar_model())){
						car.setCar_model(vo.getCar_model());
//					}
//					if(StringUtil.isEmpty(car.getCar_structure()) && !StringUtil.isEmpty(vo.getCar_structure())){
						car.setCar_structure(vo.getCar_structure());
//					}
//					if(StringUtil.isEmpty(car.getDisplacement()) && !StringUtil.isEmpty(vo.getDisplacement())){
						car.setDisplacement(vo.getDisplacement());
//					}
//					if(StringUtil.isEmpty(car.getColor()) && !StringUtil.isEmpty(vo.getColor())){
						car.setColor(vo.getColor());
//					}
//					if(StringUtil.isEmpty(car.getEngine_num()) && !StringUtil.isEmpty(vo.getEngine_num())){
						car.setEngine_num(vo.getEngine_num());
//					}
//					if(StringUtil.isEmpty(car.getKey_num()) && !StringUtil.isEmpty(vo.getKey_num())){
						car.setKey_num(vo.getKey_num());
//					}
//					if(StringUtil.isEmpty(car.getMoney()) && !StringUtil.isEmpty(vo.getMoney())){
//						car.setMoney(vo.getMoney());
//					}
//					if(StringUtil.isEmpty(car.getDes()) && !StringUtil.isEmpty(vo.getDes())){
						car.setDes(vo.getDes());
//					}
					car.setUpduserid(user.getId());
					car.setUpddate(new Date());
					this.updSuperviseImport(car, 0);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updSuperviseImport(SuperviseImportVO vo, int type) throws Exception {
		SuperviseImportVO bean =  loadSuperviseImportById(vo.getId());
		if(type == 2){
			bean.setCar_model(vo.getCar_model());
			bean.setColor(vo.getColor());
			bean.setVin(vo.getVin());
			bean.setEngine_num(vo.getEngine_num());
			bean.setCertificate_num(vo.getCertificate_num());
			bean.setKey_amount(vo.getKey_amount());
			bean.setMoney(vo.getMoney());
			bean.setCertificate_intime(vo.getCertificate_intime());
			bean.setDisplacement(vo.getDisplacement());
			bean.setUpduserid(vo.getUpduserid());
			bean.setUpddate(vo.getUpddate());
			bean.setDes(vo.getDes());
			bean.setCar_structure(vo.getCar_structure());
			bean.setKey_num(vo.getKey_num());
			bean.setCertificate_date(vo.getCertificate_date());
			return dao.update(bean);
		}else if(type == 3){
			bean.setPayment_amount(vo.getPayment_amount());
			bean.setUpduserid(vo.getUpduserid());
			bean.setUpddate(vo.getUpddate());
			return dao.update(bean);
		}else if(type == 4){
			bean.setBond(vo.getBond());
			bean.setUpduserid(vo.getUpduserid());
			bean.setUpddate(vo.getUpddate());
			return dao.update(bean);
		}else{
			return dao.update(vo);
		}
		
	}
	
	public boolean deleteSuperviseImport(int id) throws Exception {
		return dao.delete(SuperviseImportVO.class, id);
    }

	
	public SuperviseImportVO loadSuperviseImportById(int id) throws Exception{
		//return dao.get(SuperviseImportVO.class, id,new BeanPropertyRowMapper(SuperviseImportVO.class));
		return dao.get(SuperviseImportVO.class, id,new SuperviseImportMapper());
	}
	
	public List<SuperviseImportVO> searchSuperviseImportList(SuperviseImportQueryVO query){
		if(!StringUtil.isEmpty(query.getVin())){
			String vin = query.getVin();
			if(vin.contains("-")){
				String aa[] = vin.split("-");
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i<aa.length;i++){
					sb.append("'"+aa[i]+"',");
				}
				query.setVins(sb.toString().substring(0,sb.toString().length()-1));
				query.setVin("");
			}
		}
		return dao.searchSuperviseImportList(query);
	}
	
	/**
	 * 重写页面的列表查询代码实现不变，只修改了查询传递shuju
	 * @param vo
	 * @param tools
	 * @return
	 */
	public List<CarInfoQueryBean> searchSuperviseImportList(SuperviseImportQueryVO vo, IThumbPageTools tools){
		if(!StringUtil.isEmpty(vo.getVin())){
			String vin = vo.getVin();
			if(vin.contains("-")){
				String aa[] = vin.split("-");
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i<aa.length;i++){
					sb.append("'"+aa[i]+"',");
				}
				vo.setVins(sb.toString().substring(0,sb.toString().length()-1));
				vo.setVin("");
			}
		}
		return dao.searchSuperviseImportList(vo, tools);
	}
	
	public List<String> findVinsByVins(List<String> vins) {
		return dao.findVinsByVins(vins);
	}
	
	public List<UserVO> searchUserById(int id){
		return dao.searchUserById(id);
	}
	public List<UserVO> searchUserBySId(int sid){
		return dao.searchUserBySId(sid);
	}

	public List<UserVO> searchUserByYWId(int id){
		return dao.searchUserByYWId(id);
	}
	
	public List<String> findUserIds(int id) {
		return dao.findUserIds(id);
	}
	
	public List<SuperviseImportVO> findListByIds(String...ids){
		return dao.findListByIds(ids);
	}
	
	public Long findTotalPricesByIds(String...ids){
		return dao.findTotalPricesByIds(ids);
	}
	
	public int carCountByDraft(String draftNum,int state){
		return dao.carCountByDraft(draftNum,state);
	}
	
	public int carCountByDealerId(int dealerId,int[] status){
		return dao.carCountByDealerId(dealerId,status);
	};
	
	/**
	 * 根据经销商Id查询经销商下所有的车辆信息
	 * @param dealer
	 * @param state
	 * @return
	 */
	public List<SuperviseImportVO> findListByDealerIdAndState(int dealer,int state){
		return dao.findListByDealerIdAndState(dealer, state);
	}
	public List<SuperviseImportVO> findListByDraftNumAndState(String draftNum,int state){
		return dao.findListByDraftNumAndState(draftNum, state);
	}
	
	public CarSummary getSummaryByBank(SuperviseImportQueryVO query) {
		return dao.getSummaryByBank(query);
	}
	
	public List<ExportCarInfoBean> exportCarInfo(SuperviseImportQueryVO vo){
		if(!StringUtil.isEmpty(vo.getVin())){
			String vin = vo.getVin();
			if(vin.contains("-")){
				String aa[] = vin.split("-");
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i<aa.length;i++){
					sb.append("'"+aa[i]+"',");
				}
				vo.setVins(sb.toString().substring(0,sb.toString().length()-1));
				vo.setVin("");
			}
		}
		return dao.exportCarInfo(vo);
	}
	
	/**
	 * 根据车架号查询车辆信息
	 * @param vin
	 * @return
	 */
	public SuperviseImportVO findByVin(String vin){
		return dao.findByVin(vin);
	}
	/**
	 * 批量删除
	 */
	public boolean batchDeleteSuperviseImport(String ids,HttpServletRequest request){
		dao.addSuperviseReport(ids,request);
		return dao.batchDeleteSuperviseImport(ids);
	}
}

package com.zd.csms.bank.dock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.csms.bank.bean.Gyl009VO;
import com.zd.csms.bank.bean.Gyl016VO;
import com.zd.csms.bank.bean.Gyl020VO;
import com.zd.csms.bank.web.action.BankInterfaceAction;
import com.zd.csms.supervisory.model.SuperviseImportVO;

/**
 * 浙商银行对接
 */
public class ZSBankDockImpl  implements BankDock{
	BankInterfaceAction bankInterface = new BankInterfaceAction();

	@Override
	public boolean ruku(List<SuperviseImportVO> list, HttpServletRequest request) throws Exception {
		List<Gyl009VO> addList = superviseImportList2gyl009List(list);
		boolean result = bankInterface.gyl009(addList, request);//发货清单录入
		if(result){
			List<Gyl016VO> gyl016List = superviseImportList2gyl016List(list);//出质入库申请
			result = bankInterface.gyl016(gyl016List, request);
		}
		return result;
	}
	private List<Gyl009VO> superviseImportList2gyl009List(List<SuperviseImportVO> list){
		List<Gyl009VO> resultList = new ArrayList<Gyl009VO>();
		for (SuperviseImportVO importVo : list) {
			Gyl009VO vo = new Gyl009VO();
			vo.setName("");
			vo.setModel(importVo.getCar_model()==null?"":importVo.getCar_model());
			vo.setManufacturer("");
			vo.setQuantity("1");
			vo.setMortgageUnits("辆");
			vo.setPrice(importVo.getMoney()==null?"":importVo.getMoney());
			vo.setEngineNo(importVo.getEngine_num()==null?"":importVo.getEngine_num());
			vo.setChassisNo(importVo.getVin());
			vo.setCertificationNo(importVo.getCertificate_num()==null?"":importVo.getCertificate_num());
			resultList.add(vo);	
		}
		return resultList;
	}
	
	private List<Gyl016VO> superviseImportList2gyl016List(List<SuperviseImportVO> list){
		List<Gyl016VO> resultList = new ArrayList<Gyl016VO>();
		for (SuperviseImportVO importVo : list) {
			Gyl016VO vo = new Gyl016VO();
			vo.setName("");
			vo.setMorgageNo("");
			vo.setModel(importVo.getCar_model()==null?"":importVo.getCar_model());	
			vo.setManufacturer("");
			vo.setQuantity("1");
			vo.setMortgageUnits("辆");
			vo.setPrice(importVo.getMoney()==null?"":importVo.getMoney());
			vo.setEngineNo(importVo.getEngine_num()==null?"":importVo.getEngine_num());
			vo.setChassisNo(importVo.getVin());
			vo.setCertificationNo(importVo.getCertificate_num()==null?"":importVo.getCertificate_num());
			vo.setMemo("");
			resultList.add(vo);	
		}
		return resultList;
	}
	
	private List<Gyl020VO> superviseImportList2gyl020List(List<SuperviseImportVO> list){
		List<Gyl020VO> resultList = new ArrayList<Gyl020VO>();
		for (SuperviseImportVO importVo : list) {
			Gyl020VO vo = new Gyl020VO();
			vo.setChassisNo(importVo.getVin());
			vo.setDeliveryQuantity("1");
			resultList.add(vo);
		}
		return resultList;
	}
	
	@Override
	public boolean chuku(List<SuperviseImportVO> list, HttpServletRequest request) throws Exception {
		List<Gyl020VO> addList = superviseImportList2gyl020List(list);
		boolean result = bankInterface.gyl020(addList, request);//提货申请
		return result;
	}
}

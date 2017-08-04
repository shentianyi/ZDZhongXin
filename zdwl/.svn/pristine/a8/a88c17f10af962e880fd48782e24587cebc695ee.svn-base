package com.zd.csms.messagequartz.service;

import java.util.List;
import com.zd.core.ServiceSupport;
import com.zd.csms.messagequartz.dao.InspectionSupervisorDao;
import com.zd.csms.messagequartz.model.MsgBillNoCarVO;
import com.zd.csms.messagequartz.model.MsgNoProcessCarVO;
import com.zd.csms.messagequartz.model.MsgZeroSkuDraftVO;
import com.zd.csms.messagequartz.model.InspectionVO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


/**
 *
 */
public class InspectionSupervisorService extends ServiceSupport{

	private InspectionSupervisorDao dao = SupervisorDAOFactory.getSupervisorInspection();
	
	public boolean add(MsgBillNoCarVO obj){
		int id;
		try {
			id = Util.getID(MsgBillNoCarVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	public boolean add(MsgNoProcessCarVO obj){
		int id;
		try {
			id = Util.getID(MsgNoProcessCarVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	public boolean add(MsgZeroSkuDraftVO obj){
		int id;
		try {
			id = Util.getID(MsgZeroSkuDraftVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	public boolean add(InspectionVO obj){
        int id;
		try {
			id = Util.getID(InspectionVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	/**
	 * 列表页
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionVO> findListInspections(
		InspectionVO query, IThumbPageTools tools,int userId) throws Exception {
		List<InspectionVO> list = dao.findListInspections(query, tools,userId);
		return list;
	}
	
	/**
	 * 更改提醒
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateReadStatus(int userId,int id) {
		return dao.updateReadStatus(userId,id);
	}
	
	/**
	 * 巡检视频提醒列表页
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionVO> findListInspectionVideo(
		InspectionVO query, IThumbPageTools tools,int userId) throws Exception {
		List<InspectionVO> list = dao.findListInspectionVideo(query, tools,userId);
		return list;
	}
	
	
	/**
	 * 更改巡检视频 提醒
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateInVideoStatus(int userId,int id) {
		return dao.updateInVideoStatus(userId,id);
	}
	
	
}

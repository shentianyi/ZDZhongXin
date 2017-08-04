package com.zd.csms.business.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IFlowDAO;
import com.zd.csms.business.dao.mapper.FlowMapper;
import com.zd.csms.business.model.FlowQueryVO;
import com.zd.csms.business.model.FlowVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class FlowService extends ServiceSupport {

	private IFlowDAO dao = BusinessDAOFactory.getFlowDAO();
	
	public boolean addFlow(FlowVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(FlowVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
			if (flag) {
				try {
					MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_AMALDAR.getCode()+"",
							RoleConstants.SUPERVISORY.getCode()+""},
							"操作流程", "/flow.do?method=superviseflowList", 1,1,vo.getUploadid());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return flag;
	}
	
	public boolean updFlow(FlowVO vo) throws Exception {
		if (vo.getId() > 0) {
			try {
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_AMALDAR.getCode()+"",
						RoleConstants.SUPERVISORY.getCode()+""},
						"操作流程", "/flow.do?method=superviseflowList", 1,1,vo.getUploadid());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return dao.update(vo);
	}
	
	public boolean deleteFlow(int id) throws Exception {
		return dao.delete(FlowVO.class, id);
    }
	
	public FlowVO loadFlowById(int id) throws Exception{
		FlowVO flow = dao.get(FlowVO.class, id,new FlowMapper());
		return flow;
	}
	
	public List<FlowVO> searchFlowList(FlowQueryVO query){
		return dao.searchFlowList(query);
	}
	
	public List<FlowVO> searchFlowListByPage(FlowQueryVO vo, IThumbPageTools tools){
		return dao.searchFlowListByPage(vo, tools);
	}
}

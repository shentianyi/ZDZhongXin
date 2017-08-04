package com.zd.csms.messagequartz.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.dao.InspectionSupervisorDao;
import com.zd.csms.messagequartz.model.InspectionVO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 */
public class InspectionSupervisorDaoImpl extends DAOSupport implements InspectionSupervisorDao{

	public InspectionSupervisorDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	@Override
	public List<InspectionVO> findListInspections(InspectionVO query,
			IThumbPageTools tools,int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_SUPERVISOR_INSPECTION where MESSAGETYPE = "+ MessageTypeContant.INSPECTIONSU.getCode() +" and userid ="+userId);
		sql.append(" order by isread asc ");
		List<InspectionVO> list = null;
		try {
			list = tools.goPage(sql.toString(),
					new BeanPropertyRowMapper(InspectionVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_INSPECTION set isread = 2  where userid = ? and id = ? ";
		
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.INSPECTIONSU.getCode(),userId);
		if (!flag) {
			Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.INSPECTIONSU.getCode() +"";
			flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
		}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(int messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_INSPECTION where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userId = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<InspectionVO> findListInspectionVideo(InspectionVO query,
			IThumbPageTools tools,int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_SUPERVISOR_INSPECTION where MESSAGETYPE = "+ MessageTypeContant.INSPECTIONVIDEO.getCode() +" and userId ="+userId);
		sql.append(" order by isread asc ");
		List<InspectionVO> list = null;
		try {
			list = tools.goPage(sql.toString(),
					new BeanPropertyRowMapper(InspectionVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean updateInVideoStatus(int userId,int id) {
		String Mssql="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.INSPECTIONVIDEO.getCode() +"";
		String sql="update T_SUPERVISOR_INSPECTION set isread = 2  where userId = ? and id = ? ";
		boolean flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.INSPECTIONVIDEO.getCode(),userId);
			if (!flag) {
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}
	
	
	
}

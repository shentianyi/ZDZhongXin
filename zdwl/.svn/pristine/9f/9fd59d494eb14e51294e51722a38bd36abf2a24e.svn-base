package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.model.SupMaMsgQueryVO;
import com.zd.csms.supervisorymanagement.dao.ISupMaMsgDAO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupMaMsgOracleDAO extends DAOSupport implements ISupMaMsgDAO{

	public SupMaMsgOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	@Override
	public List<SupMaMsgInfoVO> searchMessageInfoList(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_SupMa_message_info where messageId = ? ");
		List<SupMaMsgInfoVO> list;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper(SupMaMsgInfoVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{id});
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	@Override
	public boolean updateMessageInfoIsread(int id, int isRead) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_SupMa_message_info set isread = ? where id = ? ");
		return this.getJdbcTemplate().update(sql.toString(), new Object[]{isRead,id})>0;
	}
	@Override
	public boolean updateMessageInfoIsmask(int id, int ismask) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_SupMa_message_info set ismask = ? where id = ? ");
		return this.getJdbcTemplate().update(sql.toString(), new Object[]{ismask,id})>0;
	}
	@Override
	public int searchNotReadMessageInfoList(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from t_SupMa_message_info where messageId = ? and isread = 1 ");
		int count=0;
		try{
			count = this.getJdbcTemplate().queryForInt(sql.toString(), new Object[]{id});
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{id});
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<SupMaMsgInfoVO> searchMessageInfoListByPage(SupMaMsgQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<Object>();
		sql.append(" select * from t_SupMa_message_info where 1=1 ");
		formatSupMaMessageWhereSQL(query,sql,params);
		sql.append(" order by isread,ismask,sendDate desc ");
		List<SupMaMsgInfoVO> list;
		try{
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupMaMsgInfoVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{query.getMessageId()});
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	private void formatSupMaMessageWhereSQL(SupMaMsgQueryVO vo, StringBuffer sql, List<Object> params) {
		if(vo.getMessageId()>0){
			sql.append(" and messageId = ? ");
			params.add(vo.getMessageId());
		}
		if(StringUtil.isNotEmpty(vo.getDealerName())){
			sql.append(" and dealerName like ? ");
			params.add("%"+vo.getDealerName()+"%");
		}
		if(StringUtil.isNotEmpty(vo.getBankName())){
			sql.append(" and bankName like ? ");
			params.add("%"+vo.getBankName()+"%");
		}
		if(StringUtil.isNotEmpty(vo.getBrandName())){
			sql.append(" and brandName like ? ");
			params.add("%"+vo.getBrandName()+"%");
		}
		if(StringUtil.isNotEmpty(vo.getAddress())){
			sql.append(" and address like ? ");
			params.add("%"+vo.getAddress()+"%");
		}
		if(vo.getHandoverPlanTime()!=null){
			sql.append(" and handoverPlanTime = ? ");
			params.add(vo.getHandoverPlanTime());
		}
		if(StringUtil.isNotEmpty(vo.getSupervisorName())){
			sql.append(" and supervisorName like ? ");
			params.add("%"+vo.getSupervisorName()+"%");
		}
		if(StringUtil.isNotEmpty(vo.getStaffNo())){
			sql.append(" and staffNo like ? ");
			params.add("%"+vo.getStaffNo()+"%");
		}
		if(vo.getExpectedInDate()!=null){
			sql.append(" and expectedInDate = ? ");
			params.add(vo.getExpectedInDate());
		}
		if(vo.getInterviewTime()!=null){
			sql.append(" and to_char(interviewTime,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getInterviewTime(), "yyyyMMdd"));
		}
		if(StringUtil.isNotEmpty(vo.getIntentionCity())){
			sql.append(" and intentionCity like ? ");
			params.add("%"+vo.getIntentionCity()+"%");
		}
		if(vo.getTrainTime()!=null){
			sql.append(" and to_char(trainTime,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getTrainTime(), "yyyyMMdd"));
		}
		if(vo.getEntryTime()!=null){
			sql.append(" and to_char(entryTime,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getEntryTime(), "yyyyMMdd"));
		}
		if(vo.getLeaveTime()!=null){
			sql.append(" and to_char(leaveTime,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getLeaveTime(), "yyyyMMdd"));
		}
		if(vo.getDimissionDate()!=null){
			sql.append(" and to_char(dimissionDate,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getDimissionDate(), "yyyyMMdd"));
		}
		if(vo.getHandoverDuedate()!=null){
			sql.append(" and to_char(handoverDuedate,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getHandoverDuedate(), "yyyyMMdd"));
		}
		if(vo.getExpectResignTime()!=null){
			sql.append(" and to_char(expectResignTime,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getExpectResignTime(), "yyyyMMdd"));
		}
		if(vo.getResignApplyDate()!=null){
			sql.append(" and to_char(resignApplyDate,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getResignApplyDate(), "yyyyMMdd"));
		}
		if(vo.getHandoverOverDuedate()>0){
			sql.append(" and handoverOverDuedate = ? ");
			params.add(vo.getHandoverOverDuedate());
		}
		if(vo.getTrainCheckDate()!=null){
			sql.append(" and to_char(trainCheckDate,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getTrainCheckDate(), "yyyyMMdd"));
		}
	}
	@Override
	public List<MessageVO> searchMsgListByMsgIdAndIsmask(int infoHashCode, int ismask) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<Object>();
		sql.append(" select * from t_message m "
				+ "inner join t_SupMa_message_info sm on m.id=sm.messageId where 1=1 ");
		sql.append(" and sm.infoHashCode = ? and sm.ismask = ? ");
		params.add(infoHashCode);
		params.add(ismask);
		List<MessageVO> list;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(MessageVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	@Override
	public List<SupMaMsgInfoVO> searchMsgInfoList(SupMaMsgQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<Object>();
		sql.append("select * from (select sm.*,row_number() over(partition by sm.infoHashCode order by sm.sendDate desc) r "
				+ "from t_SupMa_message_info sm inner join t_message m on sm.messageId = m.id where 1=1 ");
		formatSupMaMsgInfoWhereSQL(query,sql,params);
		sql.append(") where r = 1");
		List<SupMaMsgInfoVO> list;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupMaMsgInfoVO.class));
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{query.getMessageId()});
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{query.getMessageId()});
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	private void formatSupMaMsgInfoWhereSQL(SupMaMsgQueryVO vo, StringBuffer sql, List<Object> params) {
		if(vo.getMessageId()>0){
			sql.append(" and m.id = ? ");
			params.add(vo.getMessageId());
		}
		if(vo.getType()>0){
			sql.append(" and m.type = ? ");
			params.add(vo.getType());
		}
		if(vo.getUserid()>0){
			sql.append(" and m.userid = ? ");
			params.add(vo.getUserid());
		}
		if(vo.getSendDate()!=null){
			sql.append(" and to_char(sendDate,'yyyyMM') = ? ");
			params.add(DateUtil.getStringFormatByDate(vo.getSendDate(), "yyyyMM"));
		}
	}
	@Override
	public int updateMessageInfoIsreadByAll(SupMaMsgQueryVO query) {
		try {
			String sql = "update t_SupMa_message_info SET  isread = 2 WHERE id " +
					"in (select id from t_SupMa_message_info where messageId="+query.getMessageId()+" and isread=1) ";
					
			int result = getJdbcTemplate().update(sql);
			if (result > 0) {
				System.out.println("标记已读成功！");
				System.out.println("updateMessageInfoIsreadByAll sql:"+sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}

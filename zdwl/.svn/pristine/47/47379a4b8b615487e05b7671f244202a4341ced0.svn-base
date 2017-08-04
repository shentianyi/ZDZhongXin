package com.zd.csms.message.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.message.dao.INoticeContentDao;
import com.zd.csms.message.model.NoticeContentQueryVO;
import com.zd.csms.message.model.NoticeContentVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

import oracle.sql.CLOB;

public class NoticeContentOracleDao extends DAOSupport implements INoticeContentDao {

	public NoticeContentOracleDao(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public boolean add(Object obj) {
		final NoticeContentVO nc = (NoticeContentVO) obj;
		return (Boolean) getJdbcTemplate().execute(new ConnectionCallback() {

			@Override
			public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {
				int i = 0;
				boolean commit = con.getAutoCommit();
				con.setAutoCommit(false);
				try {
					String sql = "insert into t_notice_content(id,title,createUserId,createDate,type,nextApproval,approvalState,contentType,content) values (?,?,?,?,?,?,?,?,EMPTY_CLOB())";
					getJdbcTemplate().update(sql,
							new Object[] { nc.getId(), nc.getTitle(), nc.getCreateUserId(), nc.getCreateDate() ,nc.getType(),0,ApprovalContant.APPROVAL_NOT_SUBMIT.getCode(),nc.getContentType()});
					ResultSet rs = con.createStatement()
							.executeQuery("select content from t_notice_content t where t.id=" + nc.getId() + " for update");
					if (rs.next()) {
						CLOB c = (CLOB) rs.getClob("content");
						c.putChars(1, nc.getContent().toCharArray());
						sql = "update t_notice_content set content = ? where id=" + nc.getId();
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setClob(1, c);
						i = pstmt.executeUpdate();
						pstmt.close();
					}
					con.commit();
					con.setAutoCommit(commit);
				} catch (Exception e) {
					con.rollback();
					e.printStackTrace();
				}
				return i > 0;
			}
		});
	}

	public boolean updateContent(Object obj) {
		final NoticeContentVO nc = (NoticeContentVO) obj;
		return (Boolean) getJdbcTemplate().execute(new ConnectionCallback() {
			@Override
			public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {
				int i = 0;
				boolean commit = con.getAutoCommit();
				con.setAutoCommit(false);
				try {
					ResultSet rs = con.createStatement()
							.executeQuery("select content from t_notice_content t where t.id=" + nc.getId() + " for update");
					if (rs.next()) {
						CLOB c = (CLOB) rs.getClob("content");
						c.putChars(1, nc.getContent().toCharArray());
						String sql = "update t_notice_content set title=?,type=?,content = ? where id=" + nc.getId();
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, nc.getTitle());
						pstmt.setInt(2, nc.getType());
						pstmt.setClob(3, c);
						i = pstmt.executeUpdate();
						pstmt.close();
					}
					con.commit();
					con.setAutoCommit(commit);
				} catch (Exception e) {
					con.rollback();
					e.printStackTrace();
				}
				return i > 0;
			}
		});
	}
	
	

	// 资源查询语句
	private static String select_NoticeContent = "select id,title,createUserId,createDate from t_Notice_Content";

	private void formatNoticeContentWhereSQL(NoticeContentQueryVO query, StringBuffer sql, List<Object> params) {
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.SR.getCode()||currRole == RoleConstants.ADMINISTRATOR.getCode()){
				sql.append(" and t.approvalState=? or t.approvalState=?");
				sql.append(" and t.contentType = ?");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				if(query.getContentType()==1){
					params.add(1);
				}else{
					params.add(2);
				}
			}else{
				sql.append(" and ((t.createUserId=? and (t.approvalState = ? or t.approvalState=? )) or (t.nextApproval = ? and t.approvalState=? )) ");
				params.add(query.getCurrUserId());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(query.getCurrRole());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				sql.append(" and t.contentType = ?");
				if(query.getContentType()==1){
					params.add(1);
				}else{
					params.add(2);
				}
			}
		}else if(pageType==2){//已审批
			if (currRole == RoleConstants.BUSINESS_AMALDAR.getCode()||currRole == RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()||currRole == RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()) {
				sql.append(" and t.approvalState=? ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				sql.append(" and t.contentType = ?");
				if(query.getContentType()==1){
					params.add(1);
				}else{
					params.add(2);
				}
			}
			if(currRole == RoleConstants.SR.getCode()||currRole == RoleConstants.ADMINISTRATOR.getCode()){
				sql.append(" and t.approvalState=? ");
				sql.append(" and t.contentType = ?");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				if(query.getContentType()==1){
					params.add(1);
				}else{
					params.add(2);
				}
			}else{
				//sql.append(" and ((t.createUserId=? and (t.approvalState = ? or t.approvalState=? ) or t.approvalState = ?)");
				sql.append(" and ((t.createUserId=? and t.approvalState = ?)");
				params.add(query.getCurrUserId());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				//params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				sql.append(" and t.contentType = ?");
				if(query.getContentType()==1){
					params.add(1);
				}else{
					params.add(2);
				}
				sql.append(" or t.id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? )) ");
				if(query.getContentType()==1){
					params.add(ApprovalTypeContant.GONGGAO.getCode());
				}else{
					params.add(ApprovalTypeContant.ZHIDU.getCode());
				}
				params.add(currRole);
			}
		}
		
		if (!StringUtil.isEmpty(query.getTitle())) {
			sql.append(" and title like ?");
			params.add("%" + query.getTitle() + "%");
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticeContentVO> searchNoticeContentListByPage(NoticeContentQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select id,title,createUserId,createDate,nextApproval,approvalState from t_Notice_Content t ");
		formatNoticeContentWhereSQL(query, sql, params);
		sql.append(" order by  t.approvalstate desc, id desc ");
		List<NoticeContentVO> result;
		System.out.println("searchNoticeContentListByPage sql: "+sql.toString());
		System.out.println("searchNoticeContentListByPage params "+params);
		
		
		try {
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoticeContentVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
	/**
	 * 根据公告id查询对应的部门
	 * @param id
	 * @return
	 */
	@Override
	public String findDeptByContentId(int id){
		String sql="select wm_concat(t.clienttypeid) from t_content_department t where t.contentid="+id;
		return (String) getJdbcTemplate().queryForObject(sql, String.class);
	}
	
	/**
	 * 根据公告id删除绑定的部门
	 * @param id
	 */
	public void deleteDeptVOBycontentId(int id){
		String sql="delete from t_content_department t where t.contentId="+id;
		getJdbcTemplate().update(sql);
	}

	//审批状态 1：审批成功 2：审批失败 3：待审批 4:未提交 
	@Override
	public int selectMessageCountByUserAndType(int roleId, int type,int status) {
		String sql = "select count(id) from t_Notice_Content where  NEXTAPPROVAL = ? and CONTENTTYPE = ? and APPROVALSTATE = ?";
		return getJdbcTemplate().queryForInt(sql, new Object[]{roleId,type,status});
	}
}

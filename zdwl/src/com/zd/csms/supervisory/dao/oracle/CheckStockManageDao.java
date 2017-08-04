package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.contants.CheckStockCarOperationContants;
import com.zd.csms.supervisory.dao.ICheckStockManageDAO;
import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.supervisory.model.CheckStockManageBean;
import com.zd.csms.supervisory.model.CheckStockManageQueryVO;
import com.zd.csms.supervisory.model.CheckStockManageVO;
import com.zd.csms.supervisory.model.CheckStockPicVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CheckStockManageQueryBean;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.TimestampUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.time.TimeToolsFactory;

public class CheckStockManageDao extends DAOSupport implements ICheckStockManageDAO{

	public CheckStockManageDao(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockManageQueryBean> findList(CheckStockManageQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(
						"select t.*, " +
						"       ru.username, " + 
						"       (select wm_concat(md.dealername) " + 
						"          from t_check_stock_dealer csd " + 
						"          left join market_dealer md " + 
						"            on md.id = csd.dealerid " + 
						"         where csd.checkstockid = t.id) as \"dealerName\" " + 
						"  from t_check_stock t " + 
						"  left join t_rbac_user ru " + 
						"    on t.createuserid = ru.id ");
		List<Object> params = new ArrayList<Object>();
		List<CheckStockManageQueryBean> list = null;
		formatSQL(sql, params, query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockManageQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> params, CheckStockManageQueryVO query) {
		if(query.getDealerId()>0){
			sql.append(" and t.dealerid = ? ");
			params.add(query.getDealerId());
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and  tb.bankfullname like ? ");
			params.add("%"+query.getBankName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBrandName())){
			sql.append(" and  tbr.name like ? ");
			params.add("%"+query.getBrandName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getDealerAttr())){
			sql.append(" and  md.dealerNature like ? ");
			params.add("%"+query.getDealerAttr()+"%");
		}
		if(query.getBeginTime()!=null){
			sql.append("and  t.check_date >= ? ");
			params.add(query.getBeginTime());
		}
		if(query.getEndTime()!=null){
			sql.append("and  t.check_date <= ? ");
			params.add(query.getEndTime());
		}
		if(query.getCheckStockType()>0){
			sql.append("and  t.checkStockType = ? ");
			params.add(query.getCheckStockType());
		}
		if(query.getSubmitflag()>0){
			sql.append("and  t.submitflag = ? ");
			params.add(query.getSubmitflag());
		}
	}

	//日查库管理新台账
	@Override
	public List<CheckStockManageBean> findCheckStockManageList(CheckStockManageQueryVO query, IThumbPageTools tools,HttpServletRequest request) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		List<CheckStockManageBean> list = null;
		UserSession user = UserSessionUtil.getUserSession(request);
		
		sql.append(" select t.*, md.dealername,tb.bankfullname as \"bankName\",tbr.name as \"brandName\","
				+ " md.dealerNature as \"dealerAttr\" from t_checkStock_manage t");
		sql.append(" left join market_dealer md on md.id = t.dealerid " +
				" left join market_dealer_supervisor mds on mds.dealerid  = md.id "+
				" left join t_bank tb on tb.id =mds.bankid  "+
				" left join t_brand tbr on tbr.id = md.brandid ");
		/*for (RoleVO u: user.getRole()) {
			if (u.getClient_type()==3) {//监管员
				sql.append(" join t_rbac_user tru on mds.repositoryid=tru.client_id ");
				sql.append(" and tru.client_type = 3 ");
				sql.append("and tru.client_id=? ");
				params.add(user.getUser().getClient_id());
			}else {
				sql.append("where 1=1");
			}
			
			}*/
		for (RoleVO role: user.getRole()) {
			if (role.getClient_type()==3) {
				sql.append(" left join t_rbac_user tru on t.createuser=tru.id where tru.id=? ");
				params.add(user.getUser().getId());
			}else {
				
				sql.append("where 1=1");
			}
		}
		
		
		formatSQL(sql, params, query);
		sql.append(" order by  updatedate desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockManageBean.class));
			System.out.println("日查库管理新台账sql:"+sql);
			System.out.println("日查库管理新台账params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<CheckStockManageBean> findCheckStockManage(CheckStockManageQueryVO query, IThumbPageTools tools, HttpServletRequest request) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		List<CheckStockManageBean> list = null;
		UserSession user = UserSessionUtil.getUserSession(request);
		sql.append(" select t.*, md.dealername,tb.bankfullname as \"bankName\",tbr.name as \"brandName\","
				+ " md.dealerNature as \"dealerAttr\" from t_checkStock_manage t");
		sql.append(" left join market_dealer md on md.id = t.dealerid " +
				" left join market_dealer_supervisor mds on mds.dealerid  = md.id "+
				" left join t_bank tb on tb.id =mds.bankid  "+
				" left join t_brand tbr on tbr.id = md.brandid ");
		
		for (RoleVO role: user.getRole()) {
			if (role.getClient_type()==3) {
				sql.append(" left join t_rbac_user tru on t.createuser=tru.id where tru.id=? ");
				params.add(user.getUser().getId());
			}else {
				
				sql.append("where 1=1");
			}
		}
		
		formatSQL(sql, params, query);
		sql.append(" order by  updatedate desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockManageBean.class));
			System.out.println("日查库管理新findCheckStockManage sql："+sql.toString());
			System.out.println("日查库管理新findCheckStockManage params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<CheckStockCarBean> findCheckStockCarList(int checkstockId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.*,substr(t.vin,13,16) as \"lastFiveVin\",cm.check_date from T_CHECKSTOCK_CAR t "
				+ " inner join t_checkstock_manage cm on cm.id=t.checkstock_id "
				+ "where t.checkstock_id = ? ");
		params.add(checkstockId);
		List<CheckStockCarBean> list = null;
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockCarBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<CheckStockCarBean> findCheckStockCarList(int checkstockId) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.*,substr(t.vin,13,16) as lastFiveVin,cm.check_date from T_CHECKSTOCK_CAR t "
				+ " inner join t_checkstock_manage cm on cm.id=t.checkstock_id "
				+ "where t.checkstock_id = ? ");
		params.add(checkstockId);
		List<CheckStockCarBean> list = null;
		try {
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockCarBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SuperviseImportVO> findSuperviseImportList(int dealerid, int[] carStatus, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.* from t_supervise_import t ");
		params.add(dealerid);
		List<SuperviseImportVO> list = null;
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SuperviseImportVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SuperviseImportVO> findSuperviseImportList(int dealerid, int carStatus) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("  select t.*,md.id from t_supervise_import t"
				+ " left join t_draft d on t.draft_num=d.draft_num"
				+ "  left join market_dealer md on md.id=d.distribid "
				+ " left join t_two_address ta on t.addressId=ta.id"
				+ " where 1=1 ");
		if(dealerid>0){
			sql.append(" and md.id = ? ");
			params.add(dealerid);
		}
		if(carStatus<0){
			sql.append(" and ( ( t.state=2 or  (t.state=4 and t.apply=1) or ( t.state=3 and t.apply=1) ) "
					+ "or ( t.state=4 and ta.type=2 ) or ( t.state=4 and ta.type=3 ) ) ");
		}else if(carStatus==CheckStockCarOperationContants.WH.getCode()){
			//本库（在库、移动申请中、出库申请中）
			sql.append(" and ( t.state=2 or  (t.state=4 and t.apply=1) or ( t.state=3 and t.apply=1) )");
		}else if(carStatus==CheckStockCarOperationContants.TWO_WH.getCode()){
			//当前车辆状态：二库（移动且地点类型是二库）
			sql.append(" and ( t.state=4 and ta.type=2 )");
		}else if(carStatus==CheckStockCarOperationContants.MOVE.getCode()){
			//当前车辆状态：移动（移动且地点类型是二网）
			sql.append(" and ( t.state=4 and ta.type=3 )");
		}
		List<SuperviseImportVO> list = null;
		try {
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(SuperviseImportVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CheckStockManageBean getCheckStockManageBranById(int id) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.*, md.dealername,tb.bankfullname as \"bankName\",tbr.name as \"brandName\","
				+ " md.dealerNature as \"dealerAttr\" from t_checkStock_manage t");
		sql.append(" left join market_dealer md on md.id = t.dealerid " +
				" left join market_dealer_supervisor mds on mds.dealerid  = md.id "+
				" left join t_bank tb on tb.id =mds.bankid  "+
				" left join t_brand tbr on tbr.id = md.brandid ");
		sql.append(" where t.id = ? ");
		params.add(id);
		CheckStockManageBean bean = null;
		try {
			bean = (CheckStockManageBean) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockManageBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	@Override
	public boolean updateCheckStockManage(CheckStockManageBean bean) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		 int exhibition_room_count = 0;
		sql.append(" update T_CHECKSTOCK_MANAGE set actual_all_wh_count= ? ,actual_wh_count=? ,actual_two_wh_count=? ,"
				+ " actual_move_count=? ,actual_out_count=? ,actual_abnormal_count=?,secretly_move_count=?,secretly_sale_count = ?,way_sale_count = ?,way_move_count = ?,credit_line_count = ?,exhibition_room_count = ?,result = ?,normal_difference=? ,un_normal_difference=? ,updatedate = ? ,updateuser=? where id = ? ");
		params.add(bean.getActual_all_wh_count());
		params.add(bean.getActual_wh_count());
		params.add(bean.getActual_two_wh_count());
		params.add(bean.getActual_move_count());
		params.add(bean.getActual_out_count());
		params.add(bean.getActual_abnormal_count());
		params.add(bean.getSecretly_move_count());
		params.add(bean.getSecretly_sale_count());
		params.add(bean.getWay_sale_count());
		params.add(bean.getWay_move_count());
		params.add(bean.getCredit_line_count());
		params.add(bean.getExhibition_room_count());
		params.add(bean.getResult());
		params.add(bean.getNormal_difference());
		params.add(bean.getUn_normal_difference());
		params.add(bean.getUpdatedate());
		params.add(bean.getUpdateuser());
		params.add(bean.getId());
		int count = 0;
		try{
			count = this.getJdbcTemplate().update(sql.toString(), params.toArray());
		}catch(Exception e){
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
		}
		return count > 0;
	}

	@Override
	public boolean updateCheckStockCarActualstate(int id, int actualstate,String remark,boolean faTimeflag) {
		String sql= "";
		List<Object> params = new ArrayList<Object>();
		
		if(faTimeflag){
			sql = " update T_CHECKSTOCK_CAR set actualstate = ?,remark = ?,first_abnormal_time = ? where id = ?";
			params.add(actualstate);
			params.add(remark);
			params.add(TimeToolsFactory.getTimeTools().getCurrentTimestamp());
			params.add(id);
		}else{
			sql = " update T_CHECKSTOCK_CAR set actualstate = ?,remark = ? where id = ? ";
			params.add(actualstate);
			params.add(remark);
			params.add(id);
		}
		
		int count = 0;
		try{
			count = this.getJdbcTemplate().update(sql, params.toArray());
		}catch(Exception e){
			SqlUtil.debug(getDataSourceName(), sql,params.toArray());
			e.printStackTrace();
		}
		return count > 0;
	}
	
	@Override
	public boolean submit(int id) {
		String sql=" update  T_CHECKSTOCK_MANAGE set submitflag = ?,check_date = ?  where id = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{2,new Date(),id})>0;
	}

	@Override
	public int findCheckStockCarById(int id) {
		String sql = "select count(1) from T_CHECKSTOCK_CAR where first_abnormal_time is null and id = ?";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{id});
	}

	@Override
	public boolean addPic(int id,int uplId) {
		String sql = "insert into T_CHECKSTOCK_MANAGE_PIC(csmid,indexs,picid) (select ?,nvl(MAX(indexs),0)+1,? from T_CHECKSTOCK_MANAGE_PIC where csmid = ?)";
		return this.getJdbcTemplate().update(sql, new Object[]{id,uplId,id}) > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckStockPicVO> findCheckStockPicById(int checkstockId) {
		String sql = "select * from T_CHECKSTOCK_MANAGE_PIC where csmid = ? order by indexs";
		return this.getJdbcTemplate().query(sql, new Object[]{checkstockId}, new BeanPropertyRowMapper(CheckStockPicVO.class));
	}

	@Override
	public int findPicIndex(int id) {
		String sql = "select MAX(indexs) from T_CHECKSTOCK_MANAGE_PIC where csmid = ?";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{id});
	}
}

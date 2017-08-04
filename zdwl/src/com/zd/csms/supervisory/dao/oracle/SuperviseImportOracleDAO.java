package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.mapper.UserMapper;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.dao.ISuperviseImportDAO;
import com.zd.csms.supervisory.dao.mapper.CarInfoQueryBeanMapper;
import com.zd.csms.supervisory.dao.mapper.SuperviseImportMapper;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.querybean.CarSummary;
import com.zd.csms.supervisory.querybean.ExportCarInfoBean;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SuperviseImportOracleDAO extends DAOSupport implements ISuperviseImportDAO {

	public SuperviseImportOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_supervise_import = "select * from t_supervise_import";
	private static Log log = LogFactory.getLog(SuperviseImportOracleDAO.class);

	private boolean formatSuperviseImportWhereSQL(SuperviseImportQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = true;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getCertificate_datebegin() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.certificate_date,'yyyymmdd') >= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getCertificate_datebegin(), "yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getCertificate_dateend() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.certificate_date,'yyyymmdd') <= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getCertificate_dateend(), "yyyyMMdd"));
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getCertificate_num())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.certificate_num like ?");
			params.add("%"+vo.getCertificate_num()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getCar_model())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.car_model like ?");
			params.add("%"+vo.getCar_model()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getCar_structure())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.car_structure like ?");
			params.add("%"+vo.getCar_structure()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDisplacement())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.displacement like ?");
			params.add("%"+vo.getDisplacement()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getColor())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.color like ?");
			params.add("%"+vo.getColor()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getEngine_num())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.engine_num like ?");
			params.add("%"+vo.getEngine_num()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getVin())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.vin like ?");
			params.add("%"+vo.getVin()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getKey_num())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.key_num like ?");
			params.add("%"+vo.getKey_num()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getKey_amount())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.key_amount = ?");
			params.add(vo.getKey_amount());
			queryFlag = true;
		}
		if(vo.getState() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.state = ? ");
			params.add(vo.getState());
			queryFlag = true;
		}
		if(vo.getApply() > -1){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.apply = ?");
			params.add(vo.getApply());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getStates())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.state in (");
			sql.append(vo.getStates());
			sql.append(") ");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDraftNums())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.draft_num in (");
			sql.append(vo.getDraftNums());
			sql.append(") ");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDraft_num()) && !(vo.getDraft_num().equals("-1"))){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.draft_num like ?");
			params.add("%"+vo.getDraft_num()+"%");
			queryFlag = true;
		}
		if(vo.getBrandid() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("md.brandid = ?");
			params.add(vo.getBrandid());
			queryFlag = true;
		}
		
		if(vo.getStoragetimebegin() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.storagetime,'yyyymmdd')  >= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getStoragetimebegin(),"yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getStoragetimeend() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.storagetime,'yyyymmdd') <= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getStoragetimeend(),"yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getMovetimebegin() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.movetime,'yyyymmdd') >= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getMovetimebegin(),"yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getMovetimeend() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.movetime,'yyyymmdd') <= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getMovetimeend(),"yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getOuttimebegin() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.outtime,'yyyymmdd') >= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getOuttimebegin(),"yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getOuttimeend() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.outtime,'yyyymmdd') <= ?");
			params.add(DateUtil.getStringFormatByDate(vo.getOuttimeend(),"yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getDealerid() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" t.draft_num in(select tdr.draft_num from t_draft tdr where tdr.distribid = ?)");
			params.add(vo.getDealerid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getVins())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" t.vin in( ");
			sql.append(vo.getVins());
			sql.append(" ) ");
			queryFlag = true;
		}
		
		
		
		
		 if(!StringUtil.isEmpty(vo.getDealername()) && StringUtil.isNumber(vo.getDealername())){
				if(queryFlag){
					sql.append(" and ");
				} else{
					sql.append(" where ");
				}
				sql.append(" md.id = ? ");
				params.add(vo.getDealername());
				queryFlag = true;
			}else if(!StringUtil.isEmpty(vo.getDealername())){
				if(queryFlag){
					sql.append(" and ");
				} else{
					sql.append(" where ");
				}
				sql.append(" md.dealerName like ? ");
				params.add("%"+vo.getDealername()+"%");
				queryFlag = true;
		}
		 
		if(!StringUtil.isEmpty(vo.getBankname())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" tb.bankfullname like ? ");
			params.add("%"+vo.getBankname()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getTwo_name())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" t.two_name like ? ");
			params.add("%"+vo.getTwo_name()+"%");
			queryFlag = true;
		}
		if (StringUtil.isNotEmpty(vo.getBrandName())) {
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" bra.name like ? ");
			params.add("%"+vo.getBrandName()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<SuperviseImportVO> searchSuperviseImportList(SuperviseImportQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//监管员
		if(query.getType() == 1){
			sql.append("select distinct t.* from t_supervise_import t "+
					" inner join t_draft td on t.draft_num = td.draft_num "+
					" inner join market_dealer_supervisor mds on td.distribid = mds.dealerid "+
					" inner join market_dealer md on md.id = mds.dealerid "+
					" inner join t_bank tb on tb.id = mds.bankid "+
					" inner join t_repository tr on tr.id = mds.repositoryid "+
					" inner join t_rbac_user tru on tru.client_id = tr.id "+
					" where tru.id="+query.getUserid());
		//银行
		}else if(query.getType() == 2){
			sql.append("select distinct t.* from t_supervise_import t"+
						" inner join t_draft td on td.draft_num = t.draft_num"+
						" inner join market_dealer_supervisor mds on mds.dealerid=td.distribid"+
						" inner join t_bank tb on tb.id = mds.bankid "+
						" inner join market_dealer md on md.id = mds.dealerid "+
						" where (mds.bankid=?");
			params.add(query.getUserid());
			sql.append(" or mds.bankid in (select bcm.childrenid from t_bank_children_manager bcm where bcm.parentid = ?))");
			params.add(query.getUserid());
		//业务专员
		}else if(query.getType() == 3){
			sql.append("select distinct t.* from t_supervise_import t"+
					" inner join t_draft td on td.draft_num = t.draft_num"+
					" inner join market_dealer_supervisor mds on mds.dealerid=td.distribid"+
					" inner join t_bank tb on tb.id = mds.bankid "+
					" inner join t_yw_bank ty on mds.bankid = ty.bankid "+
					" inner join market_dealer md on md.id = mds.dealerid "+
					" where ty.userid="+query.getUserid());
		}else{
			sql.append("select * from t_supervise_import t where 1=1 ");
		}
		formatSuperviseImportWhereSQL(query,sql,params);
		sql.append(" order by t.id desc ");
		List<SuperviseImportVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new SuperviseImportMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 重写页面列表查询
	 */
	@SuppressWarnings("unchecked")
	public List<CarInfoQueryBean> searchSuperviseImportList(SuperviseImportQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select distinct t.*,md.dealername,md.id as \"dealerId\","
				+ "tb.bankfullname,bra.name as \"brandName\","
				+ "cru.username as \"createUserName\",upu.username as \"updUserName\" "
				+ "from t_supervise_import t ");
		sql.append(" left join t_draft td on t.draft_num = td.draft_num "+
				" left join market_dealer md on md.id = td.distribid "+
				" left join market_dealer_supervisor mds on mds.dealerid=md.id"+
				" left join t_bank tb on tb.id = mds.bankid ");
		sql.append(
				" left join t_brand bra on md.brandid = bra.id "+
				" left join t_rbac_user cru on t.createuserid = cru.id "+
				" left join t_rbac_user upu on t.upduserid = upu.id ");
		//监管员
		if(query.getType() == 1){
			sql.append(
					" inner join t_repository tr on tr.id = mds.repositoryid "+
					" inner join t_rbac_user tru on tru.client_id = tr.id "+
					" where tru.id="+query.getUserid());
		//银行
		}else if(query.getType() == 2){
			//此处getuserid取得是当前角色所属的银行id
			sql.append(" where (mds.bankid=?");
			params.add(query.getUserid());
			sql.append(" or mds.bankid in (select bcm.childrenid from t_bank_children_manager bcm where bcm.parentid = ?))");
			params.add(query.getUserid());
			if(query.getNextApproval()>0){
				sql.append(" and t.nextApproval = ? ");
				params.add(query.getNextApproval());
			}
		//业务专员
		}else if(query.getType() == 3){
			sql.append(" inner join t_yw_bank ty on mds.bankid = ty.bankid "+
					" where ty.userid="+query.getUserid());
			if(query.getNextApproval()>0){
				sql.append(" and t.nextApproval = ? ");
				params.add(query.getNextApproval());
			}
		//品牌集团
		}else if(query.getType() == 4){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ?");
			params.add(query.getUserid());
		}else if (query.getType() == 5) {
			//经销商集团
			sql.append(" where md.id in (");
			sql.append(" select  md1.id from  market_dealer md1 ");
			sql.append(" left join t_rbac_dealergroup_dealer tdd on tdd.dealerid = md1.id ");
			sql.append(" left join t_rbac_dealerGroup trd on tdd.groupid = trd.id ");
			sql.append(" left join t_rbac_dealerGroup_user tbu on tdd.groupid = tbu.groupid ");
			sql.append(" where tbu.userid = ? ");
			params.add(query.getUserid());
			sql.append(")");
		}else{
			sql.append(" where 1=1 ");
		}
		
		formatSuperviseImportWhereSQL(query,sql,params);
		sql.append(" order by t.upddate desc nulls last ");
		List<CarInfoQueryBean> result;
		try{
			//result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarInfoQueryBean.class));
			result = tools.goPage(sql.toString(), params.toArray(), new CarInfoQueryBeanMapper());
			log.info("SQL:"+sql.toString());
			log.info("params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public CarSummary getSummaryByBank(SuperviseImportQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		//此处getuserid取得是当前角色所属的银行id
		sql.append(
				"select count(t.id) as \"count\", " +
						"       sum(to_number(nvl(t.money, 0))) as \"carMoney\", " + 
						"       sum(to_number(nvl(t.payment_amount, 0))) as \"carPaymentAmount\", " + 
						"       sum(to_number(nvl(t.bond, 0))) as \"carBond\" from (" );
		sql.append("select distinct t.* from t_supervise_import t ");
		sql.append(" left join t_draft td on t.draft_num = td.draft_num "+
				" left join market_dealer md on md.id = td.distribid "+
				" left join market_dealer_supervisor mds on mds.dealerid=md.id"+
				" left join t_bank tb on tb.id = mds.bankid ");
		sql.append(
				" left join t_brand bra on md.brandid = bra.id "+
				" left join t_rbac_user cru on t.createuserid = cru.id "+
				" left join t_rbac_user upu on t.upduserid = upu.id ");
		//监管员
		if(query.getType() == 1){
			sql.append(
					" inner join t_repository tr on tr.id = mds.repositoryid "+
					" inner join t_rbac_user tru on tru.client_id = tr.id "+
					" where tru.id="+query.getUserid());
		//银行
		}else if(query.getType() == 2){
			//此处getuserid取得是当前角色所属的银行id
			sql.append(" where (mds.bankid=?");
			params.add(query.getUserid());
			sql.append(" or mds.bankid in (select bcm.childrenid from t_bank_children_manager bcm where bcm.parentid = ?))");
			params.add(query.getUserid());
			if(query.getNextApproval()>0){
				sql.append(" and t.nextApproval = ? ");
				params.add(query.getNextApproval());
			}
		//业务专员
		}else if(query.getType() == 3){
			sql.append(" inner join t_yw_bank ty on mds.bankid = ty.bankid "+
					" where ty.userid="+query.getUserid());
			/*if(query.getNextApproval()>0){
				sql.append(" and t.nextApproval = ? ");
				params.add(query.getNextApproval());
			}*/
		//品牌集团
		}else if(query.getType() == 4){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ?");
			params.add(query.getUserid());
		}else if (query.getType() == 5) {
			//经销商集团
			sql.append(" where md.id in ( ");
			sql.append(" select md1.id from market_dealer md1 ");
			sql.append(" left join t_rbac_dealergroup_dealer tdd on tdd.dealerid = md1.id ");
			sql.append(" left join t_rbac_dealergroup trd on trd.id = tdd.groupid ");
			sql.append(" left join t_rbac_dealerGroup_user tbu  on tdd.groupid = tbu.groupid ");
			sql.append("  where tbu.userid = ? ");
			params.add(query.getUserid());
			sql.append(" ) ");
		}else{
			sql.append(" where 1=1 ");
		}
		
		formatSuperviseImportWhereSQL(query,sql,params);
		sql.append(") t");
		CarSummary result;
		try{
			result = (CarSummary) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarSummary.class));
			System.out.println("getSummaryByBank sql:"+sql.toString());
			System.out.println("getSummaryByBank params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	

	/**
	 * 根据车架号查询系统中是否有这些车架号，如果有则返回已有车架号，如果没有则返回空
	 * 返回的格式为 aaaa,bbbb,cccc  多个车架号之间用逗号隔开
	 * @param vins
	 * @return
	 */
	@Override
	public List<String> findVinsByVins(List<String> vins) {
		StringBuffer sql = new StringBuffer();
		sql.append("select vin from t_supervise_import t");
		sql.append(" where t.vin in (");
		for (String string : vins) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		
		return (List<String>) getJdbcTemplate().queryForList(sql.toString(), vins.toArray(),String.class);
	}

	@Override
	public List<UserVO> searchUserById(int id) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select tr.* from t_rbac_user tr "+
				" left join market_dealer_supervisor md on tr.client_id= md.bankid "+
				" left join t_draft td on md.dealerid = td.distribid "+
				" left join t_supervise_import ts on td.draft_num = ts.draft_num"+
				" where ts.id= " + id);
		
		List<UserVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public List<UserVO> searchUserByYWId(int id) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select tr.* from t_rbac_user tr "+
				" left join t_yw_bank ty on ty.userid = tr.id "+
				" left join market_dealer_supervisor mds on mds.bankid = ty.bankid "+
				" left join t_draft td on td.distribid = mds.dealerid "+
				" left join t_supervise_import ts on ts.draft_num = td.draft_num "+
				" where ts.id =" + id);
		
		List<UserVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<UserVO> searchUserBySId(int sid) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select tr.* from t_rbac_user tr"+
				" left join market_dealer_supervisor md on tr.client_id = md.repositoryid"+
				" left join t_draft td on md.dealerid = td.distribid"+
				" left join t_supervise_import ts on td.draft_num = ts.draft_num"+
				" where ts.id="+sid);
		
		List<UserVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public List<String> findUserIds(int id) {
		return getJdbcTemplate().queryForList("select ty.userid from t_yw_bank ty "
				+ "inner join market_dealer_supervisor mds on mds.bankid = ty.bankid"
				+ " where mds.dealerid = "+id, String.class);
	}
	
	/**
	 * 根据id集合查询列表
	 * @param ids
	 * @return
	 */
	public List<SuperviseImportVO> findListByIds(String...ids){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_supervise_import t where t.id in (");
		for (String id : ids) {
			sql.append(id+",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		
		return getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper(SuperviseImportVO.class));
	}
	/**
	 * 根据id集合查询列表
	 * @param ids
	 * @return
	 */
	public Long findTotalPricesByIds(String...ids){
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(t.money) from t_supervise_import t where t.id in (");
		for (String id : ids) {
			sql.append(id+",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		return getJdbcTemplate().queryForLong(sql.toString());
	}
	
	@Override
	public int carCountByDraft(String draftNum,int state) {
		String sql=
				"select count(1) from t_supervise_import t " +
						"inner join t_draft td on t.draft_num = td.draft_num " + 
						"where td.state = ? "
						+ "and td.distribid = "
						+ "(select td1.distribid from t_draft td1 where td1.draft_num = ? group by td1.distribid)";

		return getJdbcTemplate().queryForInt(sql, new Object[]{state,draftNum});
	}

	/**
	 * 根据车架号获取资源对象
	 * @param vin
	 * @return
	 */
	@Override
	public SuperviseImportVO searchSupervise(String vin) {
		String sql = " select * from t_supervise_import ts where ts.vin = ? "; 
		return (SuperviseImportVO) getJdbcTemplate().queryForObject(sql, new Object[]{vin}, new BeanPropertyRowMapper(SuperviseImportVO.class));
	}

	/**
	 * 检查交易流水号是否已同步
	 * @param serialNo
	 * @return
	 */
	@Override
	public boolean serialNoExist(String serialNo) {
		String sql = " select ts.serialNo from t_syncapply_info ts where ts.serialNo = ? "; 
		return getJdbcTemplate().queryForObject(sql, new Object[]{serialNo}, String.class) != null ? true : false;
	}

	@Override
	public int carCountByDealerId(int dealerId, int[] status) {
		
		StringBuffer sql=new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select count(1) from (select distinct t.* from t_supervise_import t "
				+ "inner join t_draft td on t.draft_num = td.draft_num "
				+ "where td.distribid = ? and t.state in ( ");
		params.add(dealerId);
		for(int state:status){
			sql.append("?,");
			params.add(state);
		}
		sql.deleteCharAt(sql.length()-1); 
		sql.append(" ) and t.apply = 0 ) ");
		return getJdbcTemplate().queryForInt(sql.toString(), params.toArray());
	}

	/**
	 * 根据经销商Id查询经销商下所有的车辆信息
	 * @param dealer
	 * @param state
	 * @return
	 */
	public List<SuperviseImportVO> findListByDealerIdAndState(int dealer,int state){
		if(dealer==0)
			return null;
		String sql=
				"select t.* " +
						"  from t_supervise_import t " + 
						"  inner join t_draft td " + 
						"    on t.draft_num = td.draft_num " + 
						" where td.distribid = ? " + 
						"   and t.state = ? "+
						" and t.apply = 0 ";//只查询审批成功的
//		return getJdbcTemplate().query(sql, new Object[]{dealer,state}, new BeanPropertyRowMapper(SuperviseImportVO.class));
		return getJdbcTemplate().query(sql, new Object[]{dealer,state}, new SuperviseImportMapper());
	}
	
	/**
	 * 根据汇票号和状态查询经销商下所有的车辆信息
	 * @param dealer
	 * @param state
	 * @return
	 */
	public List<SuperviseImportVO> findListByDraftNumAndState(String draftNum, int state){
		if(StringUtil.isEmpty(draftNum))
			return null;
		String sql=
				"select t.* " +
						"  from t_supervise_import t " + 
						" where t.draft_num = ? " + 
						"   and t.state > ? "+
						" and t.apply = 0 ";//只查询审批成功的
		return getJdbcTemplate().query(sql, new Object[]{draftNum,state}, new BeanPropertyRowMapper(SuperviseImportVO.class));
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ExportCarInfoBean> exportCarInfo(SuperviseImportQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		/*sql.append("select distinct t.*,md.dealername,md.id as \"dealerId\","
				+ "tb.bankfullname,bra.name as \"brandName\","
				+ "cru.username as \"createUserName\",upu.username as \"updUserName\" ,"
				+ "td.billing_money as \"billing_money\",td.billing_date as \"billing_date\",td.due_date as \"due_date\" ,"
				+ "nvl(cc.actualstate,0) as \"actualstate\",nvl(cc.remark,'') as \"remark\",cc.first_abnormal_time as \"first_abnormal_time\""
				+ " from t_supervise_import t ");*/
		
		sql.append("select distinct t.*,md.dealername,md.id as \"dealerId\","
				+ "tb.bankfullname,bra.name as \"brandName\","
				+ "cru.username as \"createUserName\",upu.username as \"updUserName\" ,"
				+ "td.billing_money as \"billing_money\",td.billing_date as \"billing_date\",td.due_date as \"due_date\" "
				+ " from t_supervise_import t ");
		sql.append(" left join t_draft td on t.draft_num = td.draft_num "+
				" left join market_dealer md on md.id = td.distribid "+
				" left join market_dealer_supervisor mds on mds.dealerid=md.id"+
				" left join t_bank tb on tb.id = mds.bankid ");
		sql.append(
				" left join t_brand bra on md.brandid = bra.id "+
				" left join t_rbac_user cru on t.createuserid = cru.id "+
				" left join t_rbac_user upu on t.upduserid = upu.id ");
	
		//日查库台账
		//sql.append(" left join (select * from t_checkstock_car where id in ( select max(id) as id from t_checkstock_car  group by vin)) cc on t.vin = cc.vin");
		
		
		//监管员
		if(query.getType() == 1){
			sql.append(
					" inner join t_repository tr on tr.id = mds.repositoryid "+
					" inner join t_rbac_user tru on tru.client_id = tr.id "+
					" where tru.id="+query.getUserid());
		//银行
		}else if(query.getType() == 2){
			//此处getuserid取得是当前角色所属的银行id
			sql.append(" where (mds.bankid=?");
			params.add(query.getUserid());
			sql.append(" or mds.bankid in (select bcm.childrenid from t_bank_children_manager bcm where bcm.parentid = ?))");
			params.add(query.getUserid());
		//业务专员
		}else if(query.getType() == 3){
			sql.append(" inner join t_yw_bank ty on mds.bankid = ty.bankid "+
					" where ty.userid="+query.getUserid());
		//品牌集团
		}else if(query.getType() == 4){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ?");
			params.add(query.getUserid());
		}else{
			sql.append(" where 1=1 ");
		}
		
		formatSuperviseImportWhereSQL(query,sql,params);
		sql.append(" order by t.id desc ");
		
		System.out.println("exportCarInfo sql:"+sql.toString());
		System.out.println("exportCarInfo params:"+params);
		
		List<ExportCarInfoBean> result;
		try{
			result = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExportCarInfoBean.class));
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public SuperviseImportVO findByVin(String vin) {
		String sql =  select_supervise_import+" where vin = ? ";
		try {
			return  (SuperviseImportVO) getJdbcTemplate().queryForObject(sql, new Object[]{vin}, new BeanPropertyRowMapper(SuperviseImportVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean batchDeleteSuperviseImport(String ids) {
		String sql = "delete t_supervise_import where id in ("+ids+")";
		int count = 0;
		
		try {
			count = this.getJdbcTemplate().update(sql);
		} catch (DataAccessException e) {
			SqlUtil.debug(getDataSourceName(), sql);
			e.printStackTrace();
			count = -1;
		}
		
		return count > 0;
	}
	// 录入删除车辆信息
		public boolean addSuperviseReport(String ids, HttpServletRequest request) {
			UserVO user = UserSessionUtil.getUserSession(request).getUser();
			java.util.Date date = new java.util.Date();
			java.sql.Date data1 = new java.sql.Date(date.getTime());

			List<Object> params = new ArrayList<Object>();
			// 执行SQL 参数1:执行用户id 参数二：执行时间 参数三：需要记录删除的车辆id
			String sql = "insert into t_supervise_import_delete(id, certificate_date, certificate_num, car_model, engine_num, car_structure,"
					+ "displacement, color,vin,key_num,money,des , deleteUserId,delete_date)(select  t.id,  t.certificate_date,  t.certificate_num,"
					+ "t.car_model,  t.engine_num, t.car_structure, t.displacement, t.color, t.vin, t.key_num,t.money,t.des ,"
					+ "? as deleteUserId,?  as delete_date from t_supervise_import t where t.id in("+ids+"))";
			params.add(user.getId());
			params.add(data1);
			int count = 0;
			try {
				count = this.getJdbcTemplate().update(sql,params.toArray());
				System.out.println("导入删车信息sql" + sql + "\nSuperviseDeleteVO参数:"
						+ params);
			} catch (DataAccessException e) {
				SqlUtil.debug(getDataSourceName(), sql);
				System.out.println("导入删车信息sql" + sql + "\nSuperviseDeleteVO参数:"
						+ params);
				e.printStackTrace();
				count = -1;
			}

			return count > 0;

		}
}

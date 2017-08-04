package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.core.jdbc.JdbcTemplate;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.mapper.ExtDealerVOMapper;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.ExtDealerVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.querybean.DealerBankQueryBean;
import com.zd.csms.marketing.querybean.DealerBusinessQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.ExpenseTotalQueryBean;
import com.zd.csms.marketing.querybean.SelectDealerBean;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 关于经销商的方法都写在这儿
 * @author licheng
 *
 */
public class DealerDAOImpl extends DAOSupport implements IDealerDAO{

	public DealerDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<DealerVO> findList(DealerQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from market_dealer t ");
		sql.append(" left join t_brand tb on t.brandid = tb.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join t_bank b on mds.bankId = b.id ");
		List<Object> params = new ArrayList<Object>();
		List<DealerVO> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
			System.out.println("经销商考勤时间台账findList sql："+sql.toString());
			System.out.println("经销商考勤时间台账findList params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**经销商名录表：业务部 
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerBusinessQueryBean> findBusinessList(DealerQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		/*sql.append(" select t.* , ed.draft_way , ed.guard_way , ed.certificate_delivery , ed.actual_supervision , ed.key_supervise , ") ;
		sql.append("  ed.website , ed.old_car , ed.special_oper  from market_dealer t ");
		sql.append(" left join t_brand tb on t.brandid = tb.id ");
		sql.append(" left join t_extend_distribset  ed on ed.id = t.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join t_bank b on mds.bankId = b.id ");*/
		//修改后 -- 20170511 --	需求53	
		sql.append(" select t.* , ed.draft_way , ed.guard_way , ed.certificate_delivery , ed.actual_supervision , ed.key_supervise , ") ;
		sql.append("  ed.website , ed.old_car , ed.special_oper , d.moveperc  from market_dealer t ");
		sql.append(" left join t_brand tb on t.brandid = tb.id ");
		sql.append(" left join t_extend_distribset  ed on ed.id = t.id ");
		sql.append(" left join t_distribset  d on d.distribid = t.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join t_bank b on mds.bankId = b.id ");
		List<Object> params = new ArrayList<Object>();
		List<DealerBusinessQueryBean> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerBusinessQueryBean.class));
			System.out.println("经销商名录表（业务）sql:"+sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	private void formatSQL(StringBuffer sql,List<Object> params,DealerQueryVO query){
		sql.append(" where 1=1 ");
		if(query.getId()>0){
			sql.append(" and t.id = ? ");
			params.add(query.getId());
		}
		
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBrand())){
			sql.append(" and tb.name like ? ");
			params.add("%"+query.getBrand().trim()+"%");
		}
		if(query.getStartInDate()!=null){
			sql.append(" and t.indate >= ? ");
			params.add(query.getStartInDate());
		}
		if(query.getEndInDate()!=null){
			sql.append(" and t.indate <= ? ");
			params.add(query.getEndInDate());
		}
		if(query.getCooperationState()>0){
			sql.append(" and t.cooperationstate = ? ");
			params.add(query.getCooperationState());
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and b.bankFullName like ? ");
			params.add("%"+query.getBankName()+"%");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DealerVO> findList(DealerQueryVO query) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.id,t.dealerName from market_dealer t");
		List<DealerVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<DealerVO> findDealersList(DealerQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from market_dealer t");
		List<DealerVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		return list;
	}
	
	/**
	 * 根据主键查询银行列表
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<DealerBankVO> findBankListByIds(DealerQueryVO query) throws Exception{
		Integer[] ids = query.getIds();
		if(ids==null||ids.length<=0)
			return null;
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from market_dealer_bank t where t.dealerid in  (");
		for (int i : ids) {
			sql.append("?,");
			params.add(i);
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		System.out.println("findBankListByIds sql:"+sql.toString());
		System.out.println("findBankListByIds params:"+params);
		List<DealerBankVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerBankVO.class));
		return list;
	}
	
	
	/**
	 * 监管费用统计分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<ExpenseTotalQueryBean> expenseTotalList(DealerQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		/*sql.append(
				"select t.id as \"dealerId\", " +
						"t.dealername, " + 
						"tb.bankfullname as \"bank\", " + 
						" b.name as \"brand\", " + 
						"t.indate, " + 
						"t.supervisemoney, " + 
						"t.paymode, " + 
						"(select sum(to_number(p.actualpaymentmoney)) "
						+ "from market_payment p "
						+ "where p.dealerid = t.id and p.approvalstate = ? "
						+ " and to_char(p.actualpaymentdate,'yyyy') = to_char(sysdate,'yyyy')) as \"totalMoney\", " 
						+" ru.userName as \"createUserName\", "
						+" decode(sign(0-t.balance),1,-t.balance,-1,0,0) as \"arrearsMoney\", "
						+" decode(sign(0-t.balance),1,t.paydate,-1,null,null) as \"arrearsDate\" "
						+" from market_dealer t");
		sql.append(" left join t_brand b on t.brandId = b.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerId = t.id ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankId ");
		sql.append(" left join t_rbac_user ru on ru.id = t.createUserId ");
		sql.append(" where 1=1 ");*/
		sql.append(
				"select t.id as \"dealerId\", " +
						"t.dealername, " + " tr.name as province, tr1.name as city, t.address, "+
						"tb.bankfullname as \"bank\", " + 
						" b.name as \"brand\", " + 
						"t.indate, " + " t.contact, t.contactphone, t.ddorbd, t.bindinfo, tbm.manager as bankContact ,tbm.managerphone as bankPhone,tbm.bankid as bankId, "+
						"t.supervisemoney, " + 
						"t.paymode, " + "t.balance, mp.actualpaymentdate, mp.actualpaymentmoney, "
						+" (select sum(to_number(p.actualpaymentmoney)) "
						+ "from market_payment p "
						+ "where p.dealerid = t.id and p.approvalstate = ? "
						+ " and to_char(p.actualpaymentdate,'yyyy') = to_char(sysdate,'yyyy')) as \"totalMoney\", " 
						+" ru.userName as \"createUserName\", "
						+" decode(sign(0-t.balance),1,-t.balance,-1,0,0) as \"arrearsMoney\", "
						+" decode(sign(0-t.balance),1,t.paydate,-1,null,null) as \"arrearsDate\" "
						+" from market_dealer t");
		sql.append(" left join t_brand b on t.brandId = b.id ");
		sql.append(" left join t_bank_manager tbm on tbm.id = t.bankmanagerid ");
		sql.append(" left join market_payment mp on mp.dealerid = t.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerId = t.id ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankId ");
		sql.append(" left join t_region tr on tr.id = t.province ");
		sql.append(" left join t_region tr1 on tr1.id = t.city ");
		sql.append(" left join t_rbac_user ru on ru.id = t.createUserId ");
		sql.append(" where 1=1 ");
		
		params.add(ApprovalContant.APPROVAL_PASS.getCode());
		List<ExpenseTotalQueryBean> list = null;
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankFullName like ? ");
			params.add("%"+query.getBankName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getUserName())){
			sql.append(" and ru.username like ? ");
			params.add("%"+query.getUserName()+"%");
		}
		if(query.getIsArrears()>0){
			if(query.getIsArrears()==1)
				sql.append(" and decode(sign(0-t.balance),1,-t.balance,-1,0,0)>0");
			else
				sql.append(" and decode(sign(0-t.balance),1,-t.balance,-1,0,0)=0");
		}
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExpenseTotalQueryBean.class));
			System.out.println("expenseTotalList sql："+sql.toString());
			System.out.println("expenseTotalList params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DealerVO> findDealerList(DealerQueryVO query) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from market_dealer ");
		formatDealerWhereSQL(query,sql,params);
		
		List<DealerVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public List<DealerVO> findDealerqcList() throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from market_dealer md where md.cooperationstate=1 and md.id not in (select td.distribid from t_draft td where td.state=2 group by td.distribid) ");
		
		List<DealerVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	private boolean formatDealerWhereSQL(DealerQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getCooperationState()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("cooperationState=?");
			params.add(vo.getCooperationState());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDealerName())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("dealerName like ?");
			params.add("%"+vo.getDealerName()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}
	
	/**
	 * 进驻7天未交监管费提醒
	 * @return
	 * @throws Exception
	 */
	public List<DealerVO> findDealerListByWarring() throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.id,t.createUserid,t.dealerName " +
						"  from market_dealer t " + 
						" where t.indate = t.paydate " + 
						"   and to_char(sysdate,'yyyyMMdd') = to_char(t.indate+7,'yyyyMMdd')");
		
		List<DealerVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 监管费到期前30天进行提醒
	 * @return
	 * @throws Exception
	 */
	public List<DealerVO> payDateBefore(int day) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from market_dealer t where to_char(sysdate+"+day+",'yyyyMMdd')=to_char(t.paydate,'yyyyMMdd')");
		
		List<DealerVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 查询该交监管费的经销商
	 * @return
	 */
	public List<DealerVO> findListByPaymentDate(){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.* from market_dealer t ");
		sql.append(" where t.cooperationstate = ? ");
		params.add(DealerContant.COOPERATIONSTATE_IN.getCode());
		sql.append(" and to_char(t.paydate,'yyyymmdd')<=to_char(sysdate,'yyyymmdd')");
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
	}
	
	
	/**
	 * 
	 * @param dealerId
	 * @return
	 */
	public DealerBankVO getDealerBankByDealerId(int dealerId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from market_dealer_bank t where t.dealerid=?");
		List<DealerBankVO> list = getJdbcTemplate().query(sql.toString(),new Object[]{dealerId}, new BeanPropertyRowMapper(DealerBankVO.class));
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	/**
	 * 弹窗选择
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<SelectDealerBean> selectDealerList(DealerQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select md.id, md.dealername, " +
						" mds.bankid, tb.bankfullname as \"bankName\", " + 
						" b.id as \"brandId\", b.name as \"brandName\",md.bindInfo as \"bindShopName\", " + 
						" md.bindCount as \"bindCount\" "+
						"  from market_dealer md " + 
						"  left join market_dealer_supervisor mds " + 
						"    on mds.dealerid = md.id " + 
						"  left join t_bank tb " + 
						"    on mds.bankid = tb.id " + 
						"  left join t_brand b " + 
						"    on md.brandid = b.id ");
		sql.append(" where 1=1 ");
		sql.append(" and md.cooperationState=? ");
		params.add(DealerContant.COOPERATIONSTATE_IN.getCode());
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealername like ? ");
			params.add("%"+query.getDealerName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankfullname like ? ");
			params.add("%"+query.getBankName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBrand())){
			sql.append(" and b.name like ? ");
			params.add("%"+query.getBrand()+"%");
		}
		if(query.getDdorbd()>0){//绑定是选择 传入3 查询单店和绑定未满3个的点
			if(query.getDdorbd()==3){
				sql.append(" and (md.bindCount = 1 or md.bindCount = 2) ");
			}else{
				sql.append(" and md.ddorbd = ? ");
				params.add(query.getDdorbd());
			}	
		}
		sql.append(" order by md.id desc ");
		List<SelectDealerBean> list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SelectDealerBean.class));
		return list;
	}
	
	/**
	 * 查询绑定店名称
	 * @param ids
	 * @return
	 */
	public String findBindNameByIds(String ids){
		String sql = "select wm_concat(t.dealername)from market_dealer t where t.id in ("+ids+")";
		return (String) getJdbcTemplate().queryForObject(sql, null, String.class);
	}
	
	/**
	 * 提前一个月预警余额不足的的经销商
	 * 查询说明：如果需要交的监管费大于所剩的余额则提醒
	 * @return
	 */
	public List<DealerVO> findListByArrearsRemind(){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.* from market_dealer t "
				+ " where t.cooperationstate = ? and to_number(t.supervisemoney)>t.balance ");
		params.add(DealerContant.COOPERATIONSTATE_IN.getCode());
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
	}
	
	public List<DealerVO> findListByYW(int userid){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select md.* from market_dealer md "+
				" left join market_dealer_supervisor mds on mds.dealerid = md.id "+
				" left join t_yw_bank ty on ty.bankid = mds.bankid "+
				" where ty.userid="+userid);
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
	}
	/**
	 * 根据监管员储备库Id查询经销商
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DealerBankQueryBean> findDealerListByRepId(int id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id as \"dealerId\",t.dealerName as \"dealerName\",tb.id as \"bankId\",tb.bankfullname as \"bankName\" "
				+ " from market_dealer t "+
					" inner join market_dealer_supervisor mds on mds.dealerid = t.id "+
					" inner join t_bank tb on tb.id = mds.bankid  "+
					" where mds.repositoryid=? ");
		return (List<DealerBankQueryBean>)getJdbcTemplate().query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper(DealerBankQueryBean.class));
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DealerBankVO> getDealerListByBankName(String bankName){
		String sql=" select * from MARKET_DEALER_BANK db  "
					+ " inner join  t_bank b on db.bankId=b.id "
					+ " where b.bankName like ? ";
		return (List<DealerBankVO>)getJdbcTemplate().query(sql, new Object[]{"%"+bankName+"%"}, new BeanPropertyRowMapper(DealerBankVO.class));
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DealerVO> getDealerListByBrandId(int brandId){
		String sql=" select * from market_dealer d where d.brandId = ? ";
		return (List<DealerVO>)getJdbcTemplate().query(sql, new Object[]{brandId}, new BeanPropertyRowMapper(DealerVO.class));
		
	}
	
	/**经销商名录表：业务部-导出
	 * 
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerBusinessQueryBean> findBusinessForExcel(
			DealerQueryVO query) {
		
			StringBuffer sql = new StringBuffer();
			sql.append(" select t.* , ed.draft_way , ed.guard_way , ed.certificate_delivery , ed.actual_supervision , ed.key_supervise , ") ;
			sql.append("  ed.website , ed.old_car , ed.special_oper  from market_dealer t ");
			sql.append(" left join t_brand tb on t.brandid = tb.id ");
			sql.append(" left join t_extend_distribset  ed on ed.id = t.id ");
			sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
			sql.append(" left join t_bank b on mds.bankId = b.id ");
			List<Object> params = new ArrayList<Object>();
			List<DealerBusinessQueryBean> list = null;
			formatSQL(sql, params,query);
			sql.append(" order by t.id desc ");
			try {
				list = (List<DealerBusinessQueryBean>)getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerBusinessQueryBean.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}

	
	/**经销商名录表：市场部-导出
	 * 
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerVO> findListForExcel(DealerQueryVO query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from market_dealer t ");
		sql.append(" left join t_brand tb on t.brandid = tb.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join t_bank b on mds.bankId = b.id ");
		List<Object> params = new ArrayList<Object>();
		List<DealerVO> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = (List<DealerVO>)getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 经销商名录表（业务）修改入口
	 * 需求53 -- 20170511 -- cym
	 * */
	public DealerBusinessQueryBean dealerModify(DealerQueryVO query){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* , ed.draft_way , ed.guard_way , ed.certificate_delivery , ed.actual_supervision , ed.key_supervise , ");
		sql.append(" ed.website , ed.old_car , ed.special_oper ,d.moveperc from market_dealer t  left join t_brand tb on t.brandid = tb.id");
		sql.append(" left join t_extend_distribset  ed on ed.id = t.id ");
		sql.append(" left join t_distribset d on d.distribid = t.id");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id");
		sql.append(" left join t_bank b on mds.bankId = b.id ");
		List<Object> params = new ArrayList<Object>();
		DealerBusinessQueryBean bean = null;
		formatSQL(sql, params,query);
		try {
			bean = (DealerBusinessQueryBean) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerBusinessQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/*
	 * 经销商名录表（业务）修改
	 * @time 20170512
	 * updateDealer
	*/
	public int updateDealer(DealerQueryBean query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("update market_dealer t set");
		boolean flag = false;
		if(query.getProvince() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.province=? ");
			params.add(query.getProvince());
			flag = true;
		}
		if(query.getCity() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.city=? ");
			params.add(query.getCity());
			flag = true;
		}
		if(query.getBrand() !=null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.brandId=? ");
			params.add(Integer.valueOf(query.getBrand()));
			flag = true;
		}
		if(query.getDealerNature() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.dealerNature=? ");
			params.add(query.getDealerNature());
			flag = true;
		}
		if(query.getAddress() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.address=? ");
			params.add(query.getAddress());
			flag = true;
		}
		if(query.getContact() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.contact=? ");
			params.add(query.getContact());
			flag = true;
		}
		if(query.getContactPhone() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.contactPhone=? ");
			params.add(query.getContactPhone());
			flag = true;
		}
		if(query.getEquipmentProvide() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.equipmentProvide=? ");
			params.add(query.getEquipmentProvide());
			flag = true;
		}
		if(query.getCredit() > 0){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" t.credit=? ");
			params.add(query.getCredit());
			flag = true;
		}
		if(!flag){
			return -1;
		}
		sql.append(" where id = ?");
		params.add(query.getId());
		
		System.out.println("updateDealer sql:"+sql.toString());
		System.out.println("updateDealer params:"+params);
		
		int count = 0;
		
		try{
			count = this.getJdbcTemplate().update(sql.toString(), params.toArray());
		}catch(Exception e){
			e.printStackTrace();
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			count = -1;
		}
		return count;
		
	}
	
	/*
	 * T_BANK_MANAGER 修改金融机构联系人及电话
	 * @time 20170513
	*/
	public int updateBankManagerInfo(DealerQueryBean query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("update t_bank_manager tbm set");
		boolean flag = false;
		if(query.getBankId() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" tbm.bankId=? ");
			params.add(query.getBankId());
			flag = true;
		}
		if(query.getBankContact() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" tbm.manager=? ");
			params.add(query.getBankContact());
			flag = true;
		}
		if(query.getBankPhone() != null){
			if(flag){
				sql.append(" ,");
			}
			sql.append(" tbm.managerPhone=? ");
			params.add(query.getBankPhone());
			flag = true;
		}
		sql.append(" where id = ?");
		params.add(query.getBankManagerId());
		
		System.out.println("updateBankManagerInfo sql:"+sql.toString());
		System.out.println("updateBankManagerInfo params:"+params);
		
		int count = 0;
		
		try{
			count = getJdbcTemplate().update(sql.toString(), params.toArray());
		}catch(Exception e){
			e.printStackTrace();
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			count = -1;
		}
		return count;
		
	}
	
	/*
	 * 修改合作状态
	 * @time 20170513
	*/
	public boolean updateStopCooperationState(DealerQueryBean query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("update market_dealer t set t.cooperationState = 3, t.exitDate=? where t.id=?");
		params.add(query.getExitDate());
		params.add(query.getId());
		System.out.println("updateStopCooperationState sql:"+sql.toString());
		System.out.println("updateStopCooperationState params:"+params);
		try{
			this.getJdbcTemplate().update(sql.toString(), params.toArray());
		}catch(Exception e){
			e.printStackTrace();
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpenseTotalQueryBean> ExtexpenseTotalLedger(DealerQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select t.id as \"dealerId\", " +
						"t.dealername, " + " t.province, t.city, t.address, "+
						"tb.bankfullname as \"bank\", " + 
						" b.name as \"brand\", " + 
						"t.indate, " + " t.contact, t.contactphone, t.ddorbd, t.bindinfo, tbm.manager,tbm.managerphone,tbm.bankid, "+
						"t.supervisemoney, " + 
						"t.paymode, " + "t.balance, mp.actualpaymentdate, mp.actualpaymentmoney, "+
						"(select sum(to_number(p.actualpaymentmoney)) "
						+ "from market_payment p "
						+ "where p.dealerid = t.id and p.approvalstate = ? "
						+ " and to_char(p.actualpaymentdate,'yyyy') = to_char(sysdate,'yyyy')) as \"totalMoney\", " 
						+" ru.userName as \"createUserName\", "
						+" decode(sign(0-t.balance),1,-t.balance,-1,0,0) as \"arrearsMoney\", "
						+" decode(sign(0-t.balance),1,t.paydate,-1,null,null) as \"arrearsDate\" "
						+" from market_dealer t");
		sql.append(" left join t_brand b on t.brandId = b.id ");
		sql.append(" left join t_bank_manager tbm on tbm.id = t.bankmanagerid ");
		sql.append(" left join market_payment mp on mp.dealerid = t.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerId = t.id ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankId ");
		sql.append(" left join t_rbac_user ru on ru.id = t.createUserId ");
		sql.append(" where 1=1 ");
		
		params.add(ApprovalContant.APPROVAL_PASS.getCode());
		List<ExpenseTotalQueryBean> list = null;
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankFullName like ? ");
			params.add("%"+query.getBankName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getUserName())){
			sql.append(" and ru.username like ? ");
			params.add("%"+query.getUserName()+"%");
		}
		if(query.getIsArrears()>0){
			if(query.getIsArrears()==1)
				sql.append(" and decode(sign(0-t.balance),1,-t.balance,-1,0,0)>0");
			else
				sql.append(" and decode(sign(0-t.balance),1,-t.balance,-1,0,0)=0");
		}
		
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExpenseTotalQueryBean.class));
			System.out.println("ExtexpenseTotalLedger sql："+sql.toString());
			System.out.println("ExtexpenseTotalLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 需求38 -- 导出监管费管理台账
	 * @time 20170519
	*/
	@SuppressWarnings("unchecked")
	public List<ExpenseTotalQueryBean> ExtExpenseTotalList(DealerQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select t.id as \"dealerId\", " +
						"t.dealername, " + " t.province, t.city, t.address, "+
						"tb.bankfullname as \"bank\", " + 
						" b.name as \"brand\", " + 
						"t.indate, " + " t.contact, t.contactphone, t.ddorbd, t.bindinfo, tbm.manager,tbm.managerphone,tbm.bankid, "+
						"t.supervisemoney, " + 
						"t.paymode, " + "t.balance, mp.actualpaymentdate, mp.actualpaymentmoney, "+
						" (select re.name from t_region re where re.id = t.province) as province,  "+
						" (select rg.name from t_region rg where rg.id = t.city) as city, "
						+" (select sum(to_number(p.actualpaymentmoney)) "
						+ "from market_payment p "
						+ "where p.dealerid = t.id and p.approvalstate = ? "
						+ " and to_char(p.actualpaymentdate,'yyyy') = to_char(sysdate,'yyyy')) as \"totalMoney\", " 
						+" ru.userName as \"createUserName\", "
						+" decode(sign(0-t.balance),1,-t.balance,-1,0,0) as \"arrearsMoney\", "
						+" decode(sign(0-t.balance),1,t.paydate,-1,null,null) as \"arrearsDate\" "
						+" from market_dealer t");
		sql.append(" left join t_brand b on t.brandId = b.id ");
		sql.append(" left join t_bank_manager tbm on tbm.id = t.bankmanagerid ");
		sql.append(" left join market_payment mp on mp.dealerid = t.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerId = t.id ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankId ");
		sql.append(" left join t_rbac_user ru on ru.id = t.createUserId ");
		sql.append(" where 1=1 ");
		
		params.add(ApprovalContant.APPROVAL_PASS.getCode());
		List<ExpenseTotalQueryBean> list = null;
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankFullName like ? ");
			params.add("%"+query.getBankName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getUserName())){
			sql.append(" and ru.username like ? ");
			params.add("%"+query.getUserName()+"%");
		}
		if(query.getIsArrears()>0){
			if(query.getIsArrears()==1)
				sql.append(" and decode(sign(0-t.balance),1,-t.balance,-1,0,0)>0");
			else
				sql.append(" and decode(sign(0-t.balance),1,-t.balance,-1,0,0)=0");
		}
		
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExpenseTotalQueryBean.class));
			System.out.println("ExtExpenseTotalList sql："+sql.toString());
			System.out.println("ExtExpenseTotalList params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ExtDealerVO> ExtFindDealerList(DealerQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.id,t.dealername,t.brandid,b.id as bankId, b.bankname,r.name as provinceName,tr.name as cityName,t.address,tb.name as brandName,");
		sql.append(" at.morningstart,at.morningend,at.afternoonstart,at.afternoonend,at.systemcontent,at.workdays");
		sql.append("  from market_dealer t ");
		sql.append(" left join t_brand tb on t.brandid = tb.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join t_bank b on mds.bankId = b.id ");
		sql.append(" left join t_region r on r.id = t.province ");
		sql.append(" left join t_region tr on tr.id = t.city ");
		sql.append(" left join t_attendance_time at on t.id = at.id");
		formatDealerWhereSQL(query,sql,params);
		sql.append(" order by t.id desc ");
		List<ExtDealerVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new ExtDealerVOMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModeChangeLogVO> findModeChangeLogVOById(int id) {
		
		String sql = "select * from t_modechange_log where dealerId = ? order by id desc";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new BeanPropertyRowMapper(ModeChangeLogVO.class));
	}

	
	
	
}

package com.zd.csms.messageAndWaring.message.dao.oracle.business;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.messageAndWaring.message.dao.business.IBunisessMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.model.business.BusinMessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.business.BusinWaringInfoVO;
import com.zd.csms.messageAndWaring.message.queryBean.business.BusinMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.business.BusinWaringQueryBean;
import com.zd.csms.messageAndWaring.message.web.form.business.BusinessMessageForm;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BunisessMessageTypeDAOImpl extends DAOSupport implements
		IBunisessMessageTypeDAO {
	
	private IBankDAO bankDAO = BankDAOFactory.getBankDAO();
	public BunisessMessageTypeDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
   //-----------------------------信息提醒---------------------------
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageQueryBean> findMessageList(BusinessMessageForm form, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		 sql.append("SELECT INFO.* , INFOUSER.READ , DEALER.DEALERNAME , BRAND.NAME  AS BRANDNAME , BANK.BANKFULLNAME ");
		 sql.append(" FROM  T_BUSINMSG_INFO  INFO   ");
		 sql.append(" INNER JOIN  T_MSG_INFO_USER  INFOUSER ON INFOUSER.MSGINFOID=INFO.ID  ");
		
		List<Object> params = new ArrayList<Object>();
		formatModeChangeSQL(sql, params, form);
		List<BusinMessageQueryBean> list = null;
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(BusinMessageQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findBankOutStorage(List<Integer> ids,int state) {
		   String sql=getBankOutAndMoveSql(ids,state);
			List<BusinMessageInfoVO> result;
			try {
				result = this.getJdbcTemplate().query(sql,new Object[]{},new BeanPropertyRowMapper(BusinMessageInfoVO.class));
			} catch (Exception e) {
				SqlUtil.debug(getDataSourceName(), sql, new Object[]{});
				e.printStackTrace();
				result = null;
			}
			return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findBankMoveStorage(List<Integer> ids,int state) {
		   String sql=getBankOutAndMoveSql(ids,state);
			List<BusinMessageInfoVO> result;
			try {
				result = this.getJdbcTemplate().query(sql,new Object[]{},new BeanPropertyRowMapper(BusinMessageInfoVO.class));
			} catch (Exception e) {
				SqlUtil.debug(getDataSourceName(), sql, new Object[]{});
				e.printStackTrace();
				result = null;
			}
			return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findLastBusiness(Set<String> draftNums) {
		    StringBuffer sql = new StringBuffer();
		    sql.append(" select td.draft_num , md.id  dealerId ");
			sql.append(" from  t_draft  td ");   
			sql.append(" inner join  market_dealer  md  on  md.id=td.distribid  ");
			Iterator<String> it = draftNums.iterator();
			if(draftNums.size()>1){
				StringBuffer sb = new StringBuffer();
				sql.append("  where td.draft_num  in ( "); 
				while (it.hasNext())
				{
					sb.append(it.next()+",");
				}
				
				if(sb.length()>0){
					sb.deleteCharAt(sb.length()-1) ;
				}
				sql.append(sb.toString());
				sql.append("  ) "); 
			}else{
				while (it.hasNext()){
					sql.append("  where td.draft_num ="+it.next());
				}
				
			}
			
			List<BusinMessageInfoVO> result;
			
			try {
				result = this.getJdbcTemplate().query(sql.toString(),new Object[]{},new BeanPropertyRowMapper(BusinMessageInfoVO.class));
			} catch (Exception e) {
				SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
				e.printStackTrace();
				result = null;
			}
			return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findBillNoCarTen() {
		String sqlStr=" to_char(td.billing_date + 10,'yyyy-MM-dd') < to_char(sysdate,'yyyy-MM-dd') " ;
		String sql=getBillNoCarSql(sqlStr);
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql,new Object[]{},new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	
	@Override
	public BusinMessageInfoVO findBillingMoneyForMsg(String draftNum) {
		 String sql=getBillingMoneySql();
		 BusinMessageInfoVO result;
			try {
				result=(BusinMessageInfoVO) this.getJdbcTemplate().queryForObject(sql,
						new Object[]{draftNum},
						new BeanPropertyRowMapper(BusinMessageInfoVO.class)) ;
				
			} catch (Exception e) {
				SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{draftNum});
				e.printStackTrace();
				result = null;
			}
			return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findBillThirtyNoFull() {
		String sqlStr=" and to_char(td.billing_date +30 ,'yyyy-MM-dd') = to_char(sysdate,'yyyy-MM-dd') " ;
		String sql = getBillNoCarSql(sqlStr);
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),new Object[]{},
					new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findBillNoCarSeven() {
		String sqlStr=" and to_char(td.due_date,'yyyy-MM-dd') = to_char(sysdate+7,'yyyy-MM-dd') and td.state = 2 " ;
		String sql=getBillNoCarSql(sqlStr);
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql,new Object[]{},new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findZeroSkuDraft() {
		String sqlStr = " and ( select count(1) from t_draft td where td.distribid = md.id  and  td.state=2 )=0  " ;
		String sql = getNoBusinessSql(sqlStr);
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql, new Object[] {},
					new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[] {});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findThreeDayNoBusiness() {
		String sqlStr = " and ( select count(1) from t_draft td where td.distribid = md.id  and  td.state=2 )!=0  "
				        + " and  to_char(a.upddate,'yyyy-MM-dd') = to_char(sysdate + 3,'yyyy-MM-dd')  " ;
		String sql=getNoBusinessSql(sqlStr);
		
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql, new Object[]{} ,
					new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
		
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findCarInfoTwentyFive() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select md.id  dealerId, t.draft_num , t.money , t.vin , t.movetime  from t_supervise_import t  ");
		sql.append( " inner join t_draft  td  on td.draft_num = t.draft_num  " );
		sql.append( " inner join market_dealer  md  on md.id = td.distribid  " );
		sql.append(" where  and to_char(t.movetime + 25,'yyyy-MM-dd') = to_char(sysdate,'yyyy-MM-dd')  ") ;
		sql.append(" and t.state = 4  and t.apply = 0 ");
		
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(), new Object[] {},
					new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[] {});
			e.printStackTrace();
			result = null;
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findMoveCarInfoTwenty() {
		
		String sqlStr1="  and tsi.state = 4 ";
		String sqlStr2="  and tsi.state = 2 and tsi.apply = 1 ";
		String sqlStr3="  and (t.yc / t.allcar) > 0.20 ";
		String sql=getCarInfoSql(sqlStr1,sqlStr2,sqlStr3);
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql, new Object[] {},
					new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[] {});
			e.printStackTrace();
			result = null;
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinMessageInfoVO> findCarInfoFifteen() {
		
		String sqlStr1="  and (tsi.state = 5 or tsi.state=6) ";
		String sqlStr2="  and tsi.state >=2  or (tsi.state = 2 and tsi.apply = 1) ";
		String sqlStr3="  and (t.yc / t.allcar) > 0.15";
		String sql=getCarInfoSql(sqlStr1,sqlStr2,sqlStr3);
		List<BusinMessageInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql, new Object[] {},
					new BeanPropertyRowMapper(BusinMessageInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[] {});
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	
	
	//---------------------------------信息预警----------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringQueryBean> findWaringList(BusinessMessageForm form,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		 sql.append("SELECT INFO.* , INFOUSER.READ , INFOUSER.SHIELD  , DEALER.DEALERNAME , BRAND.NAME  AS BRANDNAME , BANK.BANKFULLNAME  ");
		 sql.append("  FROM  T_BUSINWARING_INFO  INFO  ");
		 sql.append(" INNER JOIN  T_WARING_INFO_USER  INFOUSER ON INFOUSER.MSGINFOID=INFO.ID  ");
		 
		List<Object> params = new ArrayList<Object>();
		formatModeChangeSQL(sql, params, form);
		List<BusinWaringQueryBean> list = null;
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(BusinWaringQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findBillNoCarFifteen() {
		String sqlStr=" to_char(td.billing_date + 15,'yyyy-MM-dd') < to_char(sysdate,'yyyy-MM-dd') " ;
		String sql = getBillNoCarSql(sqlStr);
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql,new Object[]{},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findBillFortyFiveNoFull() {
		String sqlStr="  and to_char(td.billing_date + 45 ,'yyyy-MM-dd') < to_char(sysdate,'yyyy-MM-dd')  " ;
		String sql = getBillNoCarSql(sqlStr);
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),new Object[]{},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}


	@Override
	public BusinWaringInfoVO findBillingMoneyForWaring(String draftNum){
        String sql=getBillingMoneySql();
		BusinWaringInfoVO result;
		try {
			result=(BusinWaringInfoVO) this.getJdbcTemplate().queryForObject(sql,
					new Object[]{draftNum},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class)) ;
			
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{draftNum});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findBillNoDraftNowDate() {
		String sqlStr = " and to_char(td.due_date,'yyyy-MM-dd') >= to_char(sysdate,'yyyy-MM-dd')  and td.state = 2 ";
		String sql=getBillNoCarSql(sqlStr);
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql,new Object[]{},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findZeroSkuDraftSeven() {
		String sqlStr = " and ( select count(1) from t_draft td where td.distribid = md.id  and  td.state=2 )=0  " ;
		sqlStr += " and  to_char(md.indate,'yyyy-MM-dd') < to_char(sysdate-7,'yyyy-MM-dd')  " ;
		String sql = getNoBusinessSql(sqlStr);
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),new Object[]{},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findFiveDayNoBusiness() {
		String sqlStr = " and  to_char(a.upddate,'yyyy-MM-dd') < to_char(sysdate-5,'yyyy-MM-dd')  " ;
		String sql=getNoBusinessSql(sqlStr);
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql,new Object[]{} ,
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findCarInfoThirty() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select md.id  dealerId , t.draft_num , t.money , t.vin , t.movetime from t_supervise_import t  ");
		sql.append( " inner join t_draft  td  on td.draft_num = t.draft_num  " );
		sql.append( " inner join market_dealer  md  on md.id = td.distribid  " );
		sql.append(" where  and to_char(t.movetime,'yyyy-MM-dd') <= to_char(sysdate-30,'yyyy-MM-dd')  ") ;
		sql.append(" and t.state = 4  and t.apply = 0 ");
		
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),new Object[]{},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinWaringInfoVO> findCarInfoTwenty() {
		String sqlStr1="  and (tsi.state = 5 or tsi.state=6) ";
		String sqlStr2="  and tsi.state = 2 and tsi.apply = 1 ";
		String sqlStr3="  and (t.yc / t.allcar) > 0.20 ";
		String sql=getCarInfoSql(sqlStr1,sqlStr2,sqlStr3);
		List<BusinWaringInfoVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),new Object[]{},
					new BeanPropertyRowMapper(BusinWaringInfoVO.class));
		} catch (Exception e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			BusinessMessageForm query) {
		
		sql.append(" LEFT JOIN  MARKET_DEALER  DEALER  ON  INFO.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
	    sql.append(" LEFT JOIN  T_BANK  BANK  ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" WHERE INFOUSER.USERID=?  AND  INFOUSER.MSGTYPE=? ");
		params.add(query.getUserId());
		params.add(query.getMsgType());
		
		if (query.getThreeBankId()!=null&&(query.getThreeBankId()>0)) {
			String oneBankName = bankDAO.getBankNameById(query.getOneBankId());
			String twoBankName = bankDAO.getBankNameById(query.getTwoBankId());
			String threeBankName = bankDAO.getBankNameById(query.getThreeBankId());
			if (StringUtil.isNotEmpty(threeBankName)) {
				sql.append(" AND BANK.BANKFULLNAME LIKE ? ");
				params.add(oneBankName+"/"+twoBankName+"/"+threeBankName+ "%");
			
			}
		}else if (query.getTwoBankId()!=null&&(query.getTwoBankId() > 0)) {
			String oneBankName = bankDAO.getBankNameById(query.getOneBankId());
			String twoBankName = bankDAO.getBankNameById(query.getTwoBankId());
			if (StringUtil.isNotEmpty(twoBankName)) {
				sql.append(" AND BANK.BANKFULLNAME LIKE ? ");
				params.add(oneBankName+"/"+twoBankName+ "%");
			}
		}else if(query.getOneBankId()!=null&&(query.getOneBankId()>0)){
			String oneBankName = bankDAO.getBankNameById(query.getOneBankId());
			if (StringUtil.isNotEmpty(oneBankName)) {
				sql.append(" AND BANK.BANKFULLNAME LIKE ? ");
				params.add(oneBankName+ "%");
			}
		}

		
		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and DEALER.dealername LIKE ?");
			params.add("%" + query.getDealerName() + "%");
		}
		
		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and BRAND.NAME LIKE ?");
			params.add("%" + query.getBrandName() + "%");
		}
		
		if (StringUtil.isNotEmpty(query.getDraft_num())) {
			sql.append(" and INFO.draft_num = ? ");
			params.add(query.getDraft_num());
		}
		
		if (StringUtil.isNotEmpty(query.getVin())) {
			sql.append(" and INFO.vin = ? ");
			params.add(query.getVin());
		} 
		
		if (query.getBilling_date_begin() != null) {
			sql.append(" and to_char(INFO.billing_date,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getBilling_date_begin(), "yyyyMMdd"));
		}
		if (query.getBilling_date_end() != null) {
			sql.append(" and to_char(INFO.billing_date,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getBilling_date_end(),
					"yyyyMMdd"));
		}
		
		if (query.getDue_date_begin() != null) {
			sql.append(" and to_char(INFO.due_date,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getDue_date_begin(), "yyyyMMdd"));
		}
		if (query.getDue_date_end() != null) {
			sql.append(" and to_char(INFO.due_date,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getDue_date_end(),
					"yyyyMMdd"));
		}
		
		if (query.getMove_date_begin() != null) {
			sql.append(" and to_char(INFO.movetime,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getMove_date_begin(), "yyyyMMdd"));
		}
		
		if (query.getMove_date_end() != null) {
			sql.append(" and to_char(INFO.movetime,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getMove_date_end(),
					"yyyyMMdd"));
		}
		
		
		sql.append(" ORDER BY INFOUSER.READ ASC , INFO.ID  DESC ");
	}

	
	private String getBillNoCarSql(String sqlStr){
		StringBuffer sql = new StringBuffer();
	    sql.append(" select  md.id  dealerId , td.draft_num , td.billing_date , td.due_date , td.mortgagecar_money ");
		sql.append(" from t_draft td ");   
		sql.append(" left join   ");
		sql.append(" ( select  supervi.id , supervi.draft_num  from  t_supervise_import  supervi ");
		sql.append("  where supervi.state > 1  and  supervi.apply = 0 )  tsi  ");
		sql.append("  on  tsi.draft_num = td.draft_num  ");
		sql.append( " inner join market_dealer  md  on md.id = td.distribid  " );
		sql.append("  where  ");
		sql.append(" tsi.id is null  ");
		sql.append(sqlStr);
		return sql.toString();
	}
	
	private String getBillingMoneySql(){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" select  md.id  dealerId , td.billing_date , td.billing_money , td.draft_num  , td.due_date , td.mortgagecar_money , ");
		sql.append( " ( select sum(nvl(tsi.money, 0))   " );
		sql.append( "  from  t_supervise_import  tsi  " );
		sql.append( "  where  tsi.draft_num = td.draft_num  " );
        sql.append("   and ((tsi.state = 2  and  tsi.apply != 1)  or  tsi.state > 2) ");
        sql.append("   )  as yycMoney  ");
		sql.append("  from  t_draft td   " );
		sql.append( " inner join market_dealer  md  on md.id = td.distribid  " );
		sql.append("  where  td.draft_num=?  ");

        
        return sql.toString();
	}

	
	private String getNoBusinessSql(String sqlStr){
		StringBuffer sql = new StringBuffer();
		sql.append(" select md.id as dealerId , a.upddate as upddate , a.draft_num  from market_dealer md ");
		sql.append(" inner join t_draft td on td.distribid = md.id ");
		sql.append(" inner join ");
		sql.append(" ( select tsi.draft_num  as  draft_num , max(tsi.upddate)  as  upddate ");
		sql.append("  from t_supervise_import tsi  group by  tsi.draft_num ) a  ");
		sql.append(" on a.draft_num = td.draft_num  ");
		sql.append(" where 1=1  ");
		sql.append(sqlStr);
		return sql.toString() ;
	}

    private String getCarInfoSql(String...sqlstr){
    	StringBuffer sql = new StringBuffer();
    	sql.append("select t.id  as  dealerId , t.money , t.vin , t.movetime, t.yc , t.allcar ");
    	sql.append("  from ( select md.*, " );
    	sql.append(" (select count(1) " );
    	sql.append("  from t_supervise_import tsi " );
    	sql.append("  inner join t_draft td " );
    	sql.append("   on td.draft_num = tsi.draft_num " );
    	sql.append( "  where td.distribid = md.id " );
    	sql.append(sqlstr[0]);
    	sql.append( "  and tsi.apply = 0) as yc, " );
    	sql.append( "  ( select count(1) " );
    	sql.append( "  from t_supervise_import tsi " );
    	sql.append( "  inner join t_draft td " );
    	sql.append( "  on td.draft_num = tsi.draft_num " );
    	sql.append( "  where td.distribid = md.id " );
    	sql.append(sqlstr[1] );
    	sql.append( "  ) as allcar " );
    	sql.append( "  from market_dealer md) t " );
    	sql.append( "  where t.allcar > 0 ");
    	sql.append(sqlstr[2]);
    	return sql.toString();
     }

    private String getBankOutAndMoveSql(List<Integer> ids,int state){
    	    StringBuffer sql = new StringBuffer();
    	    //tsi.moveAddress ,
		    sql.append(" select tsi.draft_num , tsi.vin ,tsi.price , md.id dealerId, ");
		    sql.append( state +" state " );
			sql.append(" from t_supervise_import tsi ");   
			sql.append(" inner join  t_draft  td  on  td.draft_num=tsi.draft_num  ");
			sql.append(" inner join  market_dealer  md  on md.id=td.distribid  ");
			if(ids.size()>1){
				StringBuffer sb = new StringBuffer();
				sql.append("  where tsi.id  in ( "); 
				for (Integer id : ids) {
					sb.append(id+",");
				}
				if(sb.length()>0){
					sb.deleteCharAt(sb.length()-1) ;
				};
				sql.append(sb.toString());
				sql.append("  ) "); 
			}else{
				sql.append("  where tsi.id ="+ids.get(0));
			}
			return sql.toString() ;
    }

}

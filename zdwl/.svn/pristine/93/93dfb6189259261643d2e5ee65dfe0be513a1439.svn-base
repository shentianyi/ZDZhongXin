package com.zd.csms.messageAndWaring.message.dao.oracle.market;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.messageAndWaring.message.dao.market.IMarketMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.model.market.MarketMessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.market.MarketWaringInfoVO;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketWaringQueryBean;
import com.zd.csms.messageAndWaring.message.web.form.market.MarkrtMessageManagerForm;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class MarketMessageTypeDAOImpl extends DAOSupport implements
      IMarketMessageTypeDAO {
	private IBankDAO bankDAO = BankDAOFactory.getBankDAO();
	public MarketMessageTypeDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> sevenDaysNoFee(){
		//触发逻辑：查询状态为已进驻的且进驻日期超过7天且监管费记录为空的经销商
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE , BRAND.NAME  AS BRANDNAME , BANK.BANKFULLNAME , ");
		sql.append(" MANAGER.MANAGER  FROM  MARKET_DEALER  DEALER  ");
		sql.append(" LEFT JOIN  MARKET_PAYMENT  PAYMENT ON  PAYMENT.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK  ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  PAYMENT.ID IS NULL ");
		//sql.append("  AND  DEALER.INDATE=DEALER.PAYDATE  ");
		sql.append("  AND  DEALER.COOPERATIONSTATE=? ");
		sql.append("  AND  TO_CHAR(DEALER.INDATE ,'YYYY-MM-DD') = TO_CHAR(SYSDATE-7,'YYYY-MM-DD') ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {DealerContant.COOPERATIONSTATE_IN.getCode()},
				new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> feeExpire30DaysAgo() {
		// 查询距离今天还有30天就到达交费日的经销商
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,  DEALER.BALANCE ,   BRAND.NAME  AS BRANDNAME , ");
		sql.append(" BANK.BANKFULLNAME , MANAGER.MANAGER  , ");
		sql.append(" ( SELECT SUM(TO_NUMBER(P.ACTUALPAYMENTMONEY)) ");
		sql.append("  FROM MARKET_PAYMENT P ");
		sql.append("  WHERE P.DEALERID = DEALER.ID AND  P.APPROVALSTATE = ?  ");
		sql.append("  ) as PAYAMOUNT  ");
		sql.append("  FROM  MARKET_DEALER  DEALER   ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  DEALER.COOPERATIONSTATE=? ");
		sql.append("  AND  TO_CHAR(DEALER.PAYDATE,'YYYYMMDD')=TO_CHAR(sysdate+30,'YYYYMMDD') ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {ApprovalContant.APPROVAL_PASS.getCode(),DealerContant.COOPERATIONSTATE_IN.getCode()},
				new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> threeDaysAfterNoBilling() {
		//同一家店，判断最后一条票据的开票日期是否小于于最后一次收费日期，如果当前日期大于最后一次交费日期3天及以上，则触发。
		List<Integer> dealerIds=noBilling(3);
		if(dealerIds!=null&&dealerIds.size()>0){
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,  BRAND.NAME BRANDNAME , ");
			sql.append(" BANK.BANKFULLNAME , MANAGER.MANAGER , ");
			sql.append(" ( SELECT SUM(TO_NUMBER(P.ACTUALPAYMENTMONEY)) ");
			sql.append("  FROM MARKET_PAYMENT P ");
			sql.append("  WHERE P.DEALERID = DEALER.ID AND  P.APPROVALSTATE = ?  ");
			sql.append("  ) as PAYAMOUNT  ");
			sql.append("  FROM  MARKET_DEALER  DEALER   ");
			sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
			sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
			sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
			sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
			sql.append("  WHERE  DEALER.ID  IN ( ");
			for(Integer id:dealerIds){
				sql.append(id +",");
			}
			sql.deleteCharAt(sql.length()-1 );
			sql.append("  ) ");
			return  getJdbcTemplate().query(sql.toString(),
					new Object[] {ApprovalContant.APPROVAL_PASS.getCode()},
					new BeanPropertyRowMapper(MarketMessageInfoVO.class));
		}
		return null ;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> noBilling(int days) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DEALERID  FROM ");
		sql.append(" ( SELECT COUNT (paymentId) PAYMENTCOUNT , ");
		sql.append(" COUNT (invoiceId) InvoiceCOUNT , ");
		sql.append(" MAX(PAYDATE) MXDATE , ");
		sql.append(" DEALERID ");
		sql.append(" FROM ( SELECT ");
		sql.append(" PAYMENT.ACTUALPAYMENTDATE , ");
		sql.append(" PAYMENT.ID AS paymentId , ");
		sql.append(" PAYMENT.ACTUALPAYMENTDATE AS PAYDATE , ");
		sql.append(" PAYMENT.DEALERID , INVOICE.ID AS invoiceId ");
		sql.append(" FROM  MARKET_PAYMENT PAYMENT  ");
		sql.append(" INNER JOIN MARKET_INVOICE  INVOICE  ON PAYMENT.DEALERID=INVOICE.DEALERID ");
		sql.append(" ) ss ");
		sql.append(" GROUP BY  ss.DEALERID ");
		sql.append(" ) yy  ");
		if(days==3){
			sql.append(" WHERE  TO_CHAR (yy.MXDATE +3, 'YYYY-MM-DD') = TO_CHAR (SYSDATE, 'YYYY-MM-DD') ");	
		}else{
			sql.append(" WHERE  TO_CHAR (yy.MXDATE +7, 'YYYY-MM-DD') <= TO_CHAR (SYSDATE, 'YYYY-MM-DD') ");	
		}
		sql.append("  AND yy.PAYMENTCOUNT !=yy.InvoiceCOUNT ");
		
		return  getJdbcTemplate().query(sql.toString(),new Object[] {},new BeanPropertyRowMapper(Integer.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> agreementNoRecovered() {
		    //查询进驻日期是当天的经销商 如果存在对应的协议，那么判断协议是否回收，如果未收回则触发条件
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE , ");
			sql.append("  BRAND.NAME  AS BRANDNAME  , BANK.BANKFULLNAME , MANAGER.MANAGER ,  ");
			sql.append("  MBACK.AGREEMENT_DATE  AS  MAILINGDATE  FROM  MARKET_DEALER  DEALER ");
			sql.append(" INNER JOIN  T_AGREEMENT_BACK  MBACK ON MBACK.DISTRIBID=DEALER.ID  ");
			sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
			sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
			sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
			sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
			sql.append("  WHERE  MBACK.ISBACK =1  ");
			sql.append("  AND  TO_CHAR(DEALER.INDATE ,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD') ");
			return  getJdbcTemplate().query(sql.toString(),new Object[] {},
					new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> agreementExpire30DaysAgo() {
		//监管协议到期前30天提醒
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,  ");
		sql.append("  BRAND.NAME  AS  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  , MBACK.AGREEMENT_DATE  MAILINGDATE ,  ");
		sql.append("  MBACK.AGREEMENTEXPIRETIME  PROTOCOLEXPIRE  ,  MBACK.BACK_DATE  RECOVERYDATE  ");
		sql.append("  FROM  MARKET_DEALER  DEALER  ");
		sql.append("  INNER JOIN  T_AGREEMENT_BACK  MBACK  ON  MBACK.DISTRIBID=DEALER.ID   ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append("  LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append("  LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append("  LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  TO_CHAR(MBACK.AGREEMENTEXPIRETIME ,'YYYY-MM-DD') = TO_CHAR(SYSDATE+30,'YYYY-MM-DD')  ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {},
				new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> signing10DayNoRecovered() {
		//监管协议签署10天未收回提醒
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,   BRAND.NAME AS BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ,");
		sql.append("  MBACK.AGREEMENT_DATE  AS MAILINGDATE  ");
		sql.append("  FROM  MARKET_DEALER  DEALER  ");
		sql.append("  INNER JOIN  T_AGREEMENT_BACK  MBACK  ON  MBACK.DISTRIBID=DEALER.ID   ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append("  LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append("  LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append("  LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  MBACK.ISBACK=1 ");
		sql.append("  AND  TO_CHAR(MBACK.AGREEMENTSIGNTIME ,'YYYY-MM-DD') = TO_CHAR(SYSDATE-10,'YYYY-MM-DD')  ");
		return  getJdbcTemplate().query(sql.toString(),new Object[] {},
				new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> signing15DayNoTransfer() {
		//协议签署超15天未发进店流转单提醒
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME ,  BRAND.NAME AS BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  , ");
		sql.append("  MBACK.AGREEMENT_DATE  MAILINGDATE  , PROJ.INSTOREDATE  PROINTRANSFERDATE ");
		sql.append("  FROM  MARKET_DEALER  DEALER  ");
		sql.append("  INNER JOIN  T_AGREEMENT_BACK  MBACK  ON  MBACK.DISTRIBID=DEALER.ID   ");
		sql.append("  INNER JOIN  MARKET_PROJECT_CIRCULATION  PROJ  ON  PROJ.DEALERID=DEALER.ID   ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append("  LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append("  LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append("  LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  DEALER.COOPERATIONSTATE=?  ");
		sql.append("  AND  TO_CHAR(MBACK.AGREEMENTSIGNTIME ,'YYYY-MM-DD') = TO_CHAR(SYSDATE-15,'YYYY-MM-DD')  ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {DealerContant.COOPERATIONSTATE_OUT.getCode()},
				new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> projectIn(int id) {
		//触发逻辑：审批完成后，发送提醒
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME  , ") ;
		sql.append(" PROJECT.PROVINCE  PROVINCENAME , ");
		sql.append(" PROJECT.CITY  AREANAME ,  PROJECT.DISTRICT  COUNTYNAME ,");
		sql.append(" PROJECT.SUPERVISEADDRESS   DETAILADDR  , ");
		sql.append(" PROJECT.INSTOREDATE   PROINTRANSFERDATE , ");
		sql.append(" BRAND.NAME  BRANDNAME  ,  BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append(" FROM  MARKET_DEALER  DEALER ");
		sql.append(" INNER JOIN  MARKET_PROJECT_CIRCULATION  PROJECT  ON  PROJECT.DEALERID=DEALER.ID  ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  PROJECT.ID =? ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {id},
				new BeanPropertyRowMapper(MarketMessageInfoVO.class));		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> businessIn(int id) {
	  //触发逻辑：审批完成后，发送提醒
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , ") ;
		sql.append(" BUSI.INSTOREDATE  BUSINTRANSFERDATE ,  ");
		sql.append(" SUPERVI.LIVEADDRESSPROVINCE  PROVINCENAME , SUPERVI.LIVEADDRESSCITY  AREANAME ,  ");
		sql.append(" SUPERVI.LIVEADDRESSCOUNTY  COUNTYNAME , ");
		sql.append(" SUPERVI.LIVEADDRESS   DETAILADDR , ");
		sql.append(" BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append(" FROM   MARKET_DEALER   DEALER  ");
		sql.append(" INNER JOIN  MARKET_BUSINESSTRANSFER  BUSI  ON  BUSI.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_SUPERVISOR_BASIC_INFORMATION  SUPERVI ON SUPERVI.ID= BUSI.REPOSITORYID  ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  BUSI.ID =? ");
		return  getJdbcTemplate().query(sql.toString(),
						new Object[] {id},
						new BeanPropertyRowMapper(MarketMessageInfoVO.class));	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> projectOut(int id) {
		 //触发逻辑：审批完成后，发送提醒
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME  ,  ") ;
		sql.append(" DEAEX.EXITDATEBYFINANCE , MANAGER.MANAGER  , ");
		sql.append(" BANK.BANKFULLNAME  PREDICTOUTDATE , MANAGER.MANAGER  , ");
		sql.append(" SUPERVI.LIVEADDRESSPROVINCE  PROVINCENAME , SUPERVI.LIVEADDRESSCITY  AREANAME , ");
		sql.append(" SUPERVI.LIVEADDRESSCOUNTY  COUNTYNAME ,  ");
		sql.append(" SUPERVI.LIVEADDRESS   DETAILADDR  ");
		sql.append(" FROM  MARKET_DEALER  DEALER ");
		sql.append(" INNER JOIN  MARKET_DEALER_EXIT   DEAEX  ON  DEAEX.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_SUPERVISOR_BASIC_INFORMATION  SUPERVI ON SUPERVI.ID= DEAEX.SUPERVISORID  ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  DEAEX.ID =? ");
		return  getJdbcTemplate().query(sql.toString(),
						new Object[] {id},
						new BeanPropertyRowMapper(MarketMessageInfoVO.class));	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> projectBinding(int id) {
		 //触发逻辑：审批完成后，发送提醒
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME ,  BINDDE.ACTUALINDATE  BINDINGDATE , ") ;
		sql.append(" SUPERVI.LIVEADDRESSPROVINCE  PROVINCENAME , SUPERVI.LIVEADDRESSCITY  AREANAME ,  ");
		sql.append(" SUPERVI.LIVEADDRESSCOUNTY  COUNTYNAME , ");
		sql.append(" SUPERVI.LIVEADDRESS   DETAILADDR  , ");
		sql.append(" BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append(" FROM  MARKET_DEALER   DEALER  ");
		sql.append(" INNER JOIN   MARKET_BIND_DEALER  BINDDE  ON  BINDDE.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_SUPERVISOR_BASIC_INFORMATION  SUPERVI ON SUPERVI.ID= BINDDE.REPOSITORYID  ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  BINDDE.ID =? ");
		return  getJdbcTemplate().query(sql.toString(),
						new Object[] {id},
						new BeanPropertyRowMapper(MarketMessageInfoVO.class));	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> projectUnBinding(int id) {
		 //触发逻辑：审批完成后，发送提醒
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME  ,  UNBINDDE.ACTUALINDATE  BINDINGDATE , UNBINDDE.CREATEDATE  UNBINDINGDATE , ") ;
		sql.append(" SUPERVI.LIVEADDRESSPROVINCE  PROVINCENAME , SUPERVI.LIVEADDRESSCITY  AREANAME ,  SUPERVI.LIVEADDRESSCOUNTY  COUNTYNAME ,");
		sql.append(" SUPERVI.LIVEADDRESS   DETAILADDR  , ");
		sql.append("  BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append(" FROM  MARKET_DEALER   DEALER  ");
		sql.append(" INNER JOIN   MARKET_UNBIND_DEALER  UNBINDDE  ON  UNBINDDE.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_SUPERVISOR_BASIC_INFORMATION  SUPERVI ON SUPERVI.ID= UNBINDDE.REPOSITORYID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  UNBINDDE.ID =? ");
		return  getJdbcTemplate().query(sql.toString(),
						new Object[] {id},
						new BeanPropertyRowMapper(MarketMessageInfoVO.class));
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageInfoVO> businessInThreeDayNOSupervise() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME ,  BINDDE.ACTUALINDATE  BINDINGDATE , ") ;
		sql.append(" BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append(" FROM  MARKET_DEALER   DEALER  ");
		sql.append(" INNER JOIN   MARKET_BUSINESSTRANSFER BUSINE ON  BUSINE.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE   ");
		sql.append("  AND  to_char(BUSINE.INSTOREDATE,'yyyyMMdd')= to_char(sysdate+3,'yyyyMMdd') ");
		sql.append("  AND (BUSINE.REPOSITORYID IS NULL  OR  BUSINE.REPOSITORYID = 0 ) ");
		
		return  getJdbcTemplate().query(sql.toString(),
						new Object[] {},
						new BeanPropertyRowMapper(MarketMessageInfoVO.class));	
		
		
	}

    //------------------------------------------预警--------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> fifteenDaysNoFee() {
		//触发逻辑：查询状态为已进驻的且进驻日期超过15天且监管费记录为空的经销商
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,  BRAND.NAME  AS BRANDNAME , BANK.BANKFULLNAME , ");
		sql.append(" MANAGER.MANAGER  FROM  MARKET_DEALER  DEALER  ");
		sql.append(" LEFT JOIN  MARKET_PAYMENT  PAYMENT ON  PAYMENT.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK  ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  PAYMENT.ID IS NULL ");
		sql.append("  AND  DEALER.INDATE=DEALER.PAYDATE  ");
		sql.append("  AND  DEALER.COOPERATIONSTATE=? ");
		sql.append("  AND  TO_CHAR(DEALER.INDATE ,'YYYY-MM-DD') <= TO_CHAR(SYSDATE-15,'YYYY-MM-DD') ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {DealerContant.COOPERATIONSTATE_IN.getCode()},
				new BeanPropertyRowMapper(MarketWaringInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> feeExpire10DaysAgo() {
		//触发逻辑：查询距离今天还有10天就到达交费日的经销商。
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,   DEALER.BALANCE ,  BRAND.NAME  AS BRANDNAME , ");
		sql.append(" BANK.BANKFULLNAME , MANAGER.MANAGER  , ");
		sql.append(" ( SELECT SUM(TO_NUMBER(P.ACTUALPAYMENTMONEY)) ");
		sql.append("  FROM MARKET_PAYMENT P ");
		sql.append("  WHERE P.DEALERID = DEALER.ID AND  P.APPROVALSTATE = ?  ");
		sql.append("  ) as PAYAMOUNT  ");
		sql.append(" FROM  MARKET_DEALER  DEALER  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  DEALER.COOPERATIONSTATE=? ");
		sql.append("  AND  TO_CHAR(DEALER.PAYDATE,'YYYYMMDD')=TO_CHAR(sysdate+10,'YYYYMMDD') ");
		sql.append("  AND  TO_CHAR(DEALER.PAYDATE,'YYYYMMDD')>=TO_CHAR(sysdate+10,'YYYYMMDD') ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {ApprovalContant.APPROVAL_PASS.getCode(),DealerContant.COOPERATIONSTATE_IN.getCode()},
				new BeanPropertyRowMapper(MarketWaringInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> sevenDaysAfterNoBilling() {
		//触发逻辑：最后一次收费的日期大于同一店下最后一次开票日期超过七天，触发预警
		List<Integer> dealerIds=noBilling(7);
		if(dealerIds!=null&&dealerIds.size()>0){
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME ,  ");
			sql.append(" DEALER.INDATE ,  BRAND.NAME BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ,");
			sql.append(" ( SELECT SUM(TO_NUMBER(P.ACTUALPAYMENTMONEY)) ");
			sql.append("  FROM MARKET_PAYMENT P ");
			sql.append("  WHERE P.DEALERID = DEALER.ID AND  P.APPROVALSTATE = ?  ");
			sql.append("  ) as PAYAMOUNT  ");
			sql.append(" FROM  MARKET_DEALER  DEALER  ");
			sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
			sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
			sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
			sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
			sql.append("  WHERE  DEALER.ID  IN ( ");
			for(Integer id:dealerIds){
				sql.append(id +",");
			}
			sql.deleteCharAt(sql.length()-1 );
			sql.append("  ) ");
			return  getJdbcTemplate().query(sql.toString(),
					new Object[] {ApprovalContant.APPROVAL_PASS.getCode()},
					new BeanPropertyRowMapper(MarketWaringInfoVO.class));
		}
		return null ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> expireNoFee() {
		//触发逻辑：查询当天监管费用尽或欠费
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,");
		sql.append(" DEALER.SUPERVISEMONEY  FEESTANDARD , BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , ");
		sql.append(" FROM  MARKET_DEALER  DEALER  ");
		sql.append(" LEFT JOIN  MARKET_PAYMENT  PAYMENT ON  PAYMENT.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" TO_CHAR(PAYMENT.PAYMENTDATE,'YYYYMMDD')=TO_CHAR(sysdate,'YYYYMMDD')  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  DEALER.COOPERATIONSTATE=? ");
		sql.append("  AND  TO_CHAR(DEALER.PAYDATE,'YYYYMMDD')=TO_CHAR(sysdate,'YYYYMMDD') ");
		sql.append("  AND PAYMENT.ID IS NULL ");
		
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {DealerContant.COOPERATIONSTATE_IN.getCode()},
				new BeanPropertyRowMapper(MarketWaringInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> fifteenAgreementNoRecovered() {
		//查询进驻已经十五天的经销商 如果存在对应的协议，那么判断协议是否回收，如果未收回则触发条件。
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,  BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  , ");
		sql.append("  MBACK.AGREEMENT_DATE  AS  MAILINGDATE  FROM  MARKET_DEALER  DEALER ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_AGREEMENT_BACK  MBACK ON MBACK.DISTRIBID=DEALER.ID  ");
		sql.append("  WHERE  MBACK.ISBACK =1  ");
		sql.append("  AND  TO_CHAR(DEALER.INDATE ,'YYYY-MM-DD') = TO_CHAR(SYSDATE-15,'YYYY-MM-DD') ");
		sql.append("  AND  TO_CHAR(DEALER.INDATE ,'YYYY-MM-DD') >= TO_CHAR(SYSDATE,'YYYY-MM-DD') ");
		return  getJdbcTemplate().query(sql.toString(),new Object[] {},
				new BeanPropertyRowMapper(MarketWaringInfoVO.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> agreement15DaysAgo() {
		//触发逻辑：查询协议还有15天就到期的协议对应的经销商
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,  BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  , ");
		sql.append("  MBACK.AGREEMENT_DATE  MAILINGDATE ,  MBACK.AGREEMENTEXPIRETIME  PROTOCOLEXPIRE  ,  MBACK.BACK_DATE  RECOVERYDATE  ");
		sql.append("  FROM  MARKET_DEALER  DEALER  ");
		sql.append("  INNER JOIN  T_AGREEMENT_BACK  MBACK  ON  MBACK.DISTRIBID=DEALER.ID   ");
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append("  LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append("  LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append("  LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE  TO_CHAR(MBACK.AGREEMENTEXPIRETIME ,'YYYY-MM-DD') <= TO_CHAR(SYSDATE+15,'YYYY-MM-DD')  ");
		sql.append("  AND  TO_CHAR(MBACK.AGREEMENTEXPIRETIME ,'YYYY-MM-DD') >= TO_CHAR(SYSDATE,'YYYY-MM-DD')  ");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {},
				new BeanPropertyRowMapper(MarketWaringInfoVO.class));
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> agreementNoSigning() {
		//如果这家店的协议已到期，查询此店是否签订了其他未到期的协议，如果没有则触发。
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME , DEALER.INDATE ,   BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append("  FROM  MARKET_DEALER  DEALER  ");
		sql.append("  INNER JOIN ") ;
		sql.append(" ( SELECT  MAX(MBACK.AGREEMENTEXPIRETIME)  MAXDATE , MBACK.DISTRIBID  ");
		sql.append("  FROM   T_AGREEMENT_BACK  MBACK  ");
		sql.append("  GROUP BY  MBACK.DISTRIBID ");
		sql.append("  HAVING  TO_CHAR(MAXDATE ,'YYYY-MM-DD') <= TO_CHAR(SYSDATE,'YYYY-MM-DD')  ");
		sql.append("  )  AGREEMENT ");
		sql.append("  ON  AGREEMENT.DISTRIBID=DEALER.ID  " );
		sql.append("  LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append("  LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append("  LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append("  LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {},
				new BeanPropertyRowMapper(MarketWaringInfoVO.class));
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringInfoVO> businessInONEDayNOSupervise() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DEALER.ID  DEALERID, DEALER.DEALERNAME ,  BINDDE.ACTUALINDATE  BINDINGDATE , ") ;
		sql.append(" BRAND.NAME  BRANDNAME , BANK.BANKFULLNAME , MANAGER.MANAGER  ");
		sql.append(" FROM  MARKET_DEALER   DEALER  ");
		sql.append(" INNER JOIN   MARKET_BUSINESSTRANSFER BUSINE ON  BUSINE.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BRAND  BRAND  ON  BRAND.ID=DEALER.BRANDID  ");
		sql.append(" LEFT JOIN  MARKET_DEALER_BANK  DEALERBANK ON DEALERBANK.DEALERID=DEALER.ID  ");
		sql.append(" LEFT JOIN  T_BANK  BANK ON  BANK.ID=DEALERBANK.BANKID  ");
		sql.append(" LEFT JOIN  T_BANK_MANAGER  MANAGER ON MANAGER.ID=DEALER.ID  ");
		sql.append("  WHERE   ");
		sql.append("  AND  TO_CHAR(BUSINE.INSTOREDATE,'yyyyMMdd') <= TO_CHAR(sysdate+1,'yyyyMMdd') ");
		sql.append("  AND  TO_CHAR(BUSINE.INSTOREDATE,'yyyyMMdd') >= TO_CHAR(sysdate,'yyyyMMdd') ");
		sql.append("  AND (BUSINE.REPOSITORYID IS NULL  OR  BUSINE.REPOSITORYID = 0 ) ");
		
		return  getJdbcTemplate().query(sql.toString(),
						new Object[] {},
						new BeanPropertyRowMapper(MarketWaringInfoVO.class));	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketMessageQueryBean> findMessageList(MarkrtMessageManagerForm form,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
        sql.append("SELECT INFO.* , INFOUSER.READ  FROM  T_MARKETMSG_INFO  INFO ");
		sql.append(" INNER JOIN  T_MSG_INFO_USER  INFOUSER ON INFOUSER.MSGINFOID=INFO.ID  ");
		List<Object> params = new ArrayList<Object>();
		formatModeChangeSQL(sql, params, form);
		List<MarketMessageQueryBean> list = null;
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(MarketMessageQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketWaringQueryBean> findWaringList(MarkrtMessageManagerForm form,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
        sql.append("SELECT INFO.* , INFOUSER.READ ,INFOUSER.SHIELD  FROM  T_MARKETWARING_INFO  INFO ");
		sql.append(" INNER JOIN  T_WARING_INFO_USER  INFOUSER ON INFOUSER.MSGINFOID=INFO.ID  ");
		List<Object> params = new ArrayList<Object>();
		formatModeChangeSQL(sql, params, form);
		List<MarketWaringQueryBean> list = null;
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(MarketWaringQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			MarkrtMessageManagerForm form) {
		sql.append(" WHERE INFOUSER.USERID=?  AND  INFOUSER.MSGTYPE=? ");
		params.add(form.getUserId());
		params.add(form.getMsgType());
		
		if (StringUtil.isNotEmpty(form.getDealerName())) {
			sql.append(" AND INFO.DEALERNAME LIKE ?");
			params.add("%" + form.getDealerName() + "%");
		}
		
		if (StringUtil.isNotEmpty(form.getBrandName())) {
			sql.append(" AND INFO.BRANDNAME like ?");
			params.add("%" + form.getBrandName() + "%");
		}
		
		if (form.getThreeBankId() > 0) {
			String oneBankName = bankDAO.getBankNameById(form.getOneBankId());
			String twoBankName = bankDAO.getBankNameById(form.getTwoBankId());
			String threeBankName = bankDAO.getBankNameById(form.getThreeBankId());
			if (StringUtil.isNotEmpty(threeBankName)) {
				sql.append(" AND INFO.BANKFULLNAME LIKE ? ");
				params.add(oneBankName+"/"+twoBankName+"/"+threeBankName+ "%");
			
			}
		}else if (form.getTwoBankId() > 0) {
			String oneBankName = bankDAO.getBankNameById(form.getOneBankId());
			String twoBankName = bankDAO.getBankNameById(form.getTwoBankId());
			if (StringUtil.isNotEmpty(twoBankName)) {
				sql.append(" AND INFO.BANKFULLNAME LIKE ? ");
				params.add(oneBankName+"/"+twoBankName+ "%");
			}
		}else if(form.getOneBankId()>0){
			String oneBankName = bankDAO.getBankNameById(form.getOneBankId());
			if (StringUtil.isNotEmpty(oneBankName)) {
				sql.append(" AND INFO.BANKFULLNAME LIKE ? ");
				params.add(oneBankName+ "%");
			}
		}
		
		
		if (form.getCountyId()>0) {
			sql.append("  AND  INFO.COUNTYID=? ");
			params.add(form.getCountyId());
		}else if (form.getCityId() > 0) {
				sql.append("  AND  INFO.CITYID=? ");
				params.add(form.getCityId());
		}
		
		
		if (form.getInStartDate()!= null) {
			sql.append(" AND to_char(INFO.INDATE,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					form.getInStartDate(), "yyyyMMdd"));
		}
		
		if (form.getInEndDate() != null) {
			sql.append(" AND to_char(INFO.INDATE,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(form.getInEndDate(),
					"yyyyMMdd"));
		}
		
		if (form.getStartMailingDate()!= null) {
			sql.append(" AND to_char(INFO.MAILINGDATE , 'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					form.getStartMailingDate(), "yyyyMMdd"));
		}
		
		if (form.getEndMailingDate() != null) {
			sql.append(" AND to_char(INFO.MAILINGDATE , 'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(form.getEndMailingDate(), "yyyyMMdd"));
		}
		
		
		if (form.getStartProtocolExpire()!= null) {
			sql.append(" AND to_char(INFO.PROTOCOLEXPIRE , 'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					form.getStartProtocolExpire(), "yyyyMMdd"));
		}
		
		if (form.getEndProtocolExpire() != null) {
			sql.append(" AND to_char(INFO.PROTOCOLEXPIRE , 'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(form.getEndProtocolExpire(), "yyyyMMdd"));
		}
		
		
		if (form.getStartProInTransferDate()!= null) {
			sql.append(" and to_char(info.proInTransferDate,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					form.getStartProInTransferDate(), "yyyyMMdd"));
		}
		
		if (form.getEndProInTransferDate() != null) {
			sql.append(" and to_char(info.proInTransferDate,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(form.getEndProInTransferDate(),
					"yyyyMMdd"));
		}
		
		
	/*	if (form.getStartBusInTransferDate()!= null) {
			sql.append(" and to_char(info.busInTransferDate , 'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					form.getStartBusInTransferDate(), "yyyyMMdd"));
		}
		
		
		if (form.getEndBusInTransferDate() != null) {
			sql.append(" and to_char(info.busInTransferDate , 'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(form.getEndBusInTransferDate() ,
					"yyyyMMdd"));
		}*/
		
		
		if (form.getStartBindingDate()!= null) {
			sql.append(" AND to_char(INFO.BINDINGDATE , 'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					form.getStartBindingDate() , "yyyyMMdd"));
		}
		
		if (form.getEndBindingDate() != null) {
			sql.append(" AND to_char(INFO.BINDINGDATE , 'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(form.getEndBindingDate(),
					"yyyyMMdd"));
		}
		
	
		sql.append(" ORDER BY INFOUSER.READ ASC , INFO.ID  DESC ");
	}



}

package com.zd.csms.warringquartz.dao.impl;

import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.warringquartz.dao.MessRemindDao;

public class MessRemindDaoImpl extends DAOSupport implements MessRemindDao{

	public MessRemindDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 开票10个工作日未到车提醒
	 * 当前时间减去创建时间大于10
	 * 且当前车辆为0的业务
	 */
	public List<DraftVO> m1(){
		String sql="select t.* " +
						"  from t_draft t " + 
						" where (sysdate - t.createdate) >= 10 " + 
						"   and (select count(1) " + 
						"          from t_supervise_import tsi " + 
						"         where tsi.draft_num = t.draft_num " + 
						"           and tsi.state != 1) = 0 ";
		return null;
	}
	
	/**
	 * 开票30天汇票未押满
	 * 当前时间-创建时间大于等于30 
	 * 且
	 * 已押车金额-汇票金额小于0
	 * 
	 */
	public void m2(){
		String sql="select * " +
						"  from t_draft td " + 
						" where (sysdate - td.createdate) >= 30 " + 
						"   and ((nvl((select sum(nvl(tsi.money, 0)) " + 
						"               from t_supervise_import tsi " + 
						"              where tsi.draft_num = td.draft_num " + 
						"                and tsi.state != 1 " + 
						"                and (tsi.state != 2 or (tsi.state = 2 and tsi.apply != 1))), " + 
						"             0)) - to_number(td.billing_money)) < 0 ";
	}
	
	
	/**
	 * 汇票到期前7天未清票提醒
	 * 查询状态为未清票的
	 * 且
	 * 当前日期加7天等于汇票到期日的
	 */
	public void m3(){
		String sql="select td.*, td.rowid " +
						"  from t_draft td " + 
						" where td.state = 2 " + 
						"   and to_char(sysdate + 7, 'yyyymmdd') = to_char(td.due_date, 'yyyymmdd') ";

	}
	
	/**
	 * 零库存零汇票提醒
	 * 
	 */
	public void m4(){
		String sql=
				"select md.* " +
						"  from market_dealer md " + 
						" where md.id in (select t.id " + 
						"                   from market_dealer t " + 
						"                   left join t_draft td " + 
						"                     on td.distribid = t.id " + 
						"                  group by t.id " + 
						"                 having count(td.id) = 0)";
	}
	
	/**
	 * 连续三天无业务发生
	 */
	public void m5(){
		String sql="";
	}
	
	/**
	 * 移动车辆超25天未处理
	 * 查询移动时间增加25小于当前时间
	 * 且
	 * 是移动状态的
	 */
	public void m6(){
		String sql=
				"select t.*, t.rowid " +
						"  from t_supervise_import t " + 
						" where t.movetime + 25 < sysdate " + 
						"   and t.state = 4 " + 
						"   and t.apply = 0";
	}
	
	/**
	 * 移动车辆超过总库存20%提醒
	 * 查询，移动车辆
	 * 查询总库存
	 * 做个除法
	 * 
	 */
	public void m7(){
		String sql=
				"select * " +
						"  from (select md.*, " + 
						"               (select count(1) " + 
						"                  from t_supervise_import tsi " + 
						"                 inner join t_draft td " + 
						"                    on td.draft_num = tsi.draft_num " + 
						"                 where td.distribid = md.id " + 
						"                   and tsi.state = 4 " + 
						"                   and tsi.apply = 0) as yd, " + 
						"               (select count(1) " + 
						"                  from t_supervise_import tsi " + 
						"                 inner join t_draft td " + 
						"                    on td.draft_num = tsi.draft_num " + 
						"                 where td.distribid = md.id " + 
						"                   and tsi.state != 1 " + 
						"                   and (tsi.state != 2 or (tsi.state = 2 and tsi.apply = 1))) as allcar " + 
						"          from market_dealer md) t " + 
						" where t.allcar > 0 " + 
						"   and (t.yd / t.allcar) > 0.2";
	}
	
	/**
	 * 异常车辆超过总库存15%提醒
	 * 查询同上
	 */
	public void m8(){
		String sql=
				"select * " +
						"  from (select md.*, " + 
						"               (select count(1) " + 
						"                  from t_supervise_import tsi " + 
						"                 inner join t_draft td " + 
						"                    on td.draft_num = tsi.draft_num " + 
						"                 where td.distribid = md.id " + 
						"                   and (tsi.state = 5 or tsi.state=6) " + 
						"                   and tsi.apply = 0) as yc, " + 
						"               (select count(1) " + 
						"                  from t_supervise_import tsi " + 
						"                 inner join t_draft td " + 
						"                    on td.draft_num = tsi.draft_num " + 
						"                 where td.distribid = md.id " + 
						"                   and tsi.state != 1 " + 
						"                   and (tsi.state != 2 or (tsi.state = 2 and tsi.apply = 1))) as allcar " + 
						"          from market_dealer md) t " + 
						" where t.allcar > 0 " + 
						"   and (t.yc / t.allcar) > 0.15";
	}
	
	
	/**
	 * 开票15个工作日未到车预警
	 * 
	 */
	public void m9(){
		String sql="select t.* " +
				"  from t_draft t " + 
				" where (sysdate - t.createdate) >= 15 " + 
				"   and (select count(1) " + 
				"          from t_supervise_import tsi " + 
				"         where tsi.draft_num = t.draft_num " + 
				"           and tsi.state != 1) = 0 ";
	}
	
	/**
	 * 汇票到期当天未清票预警
	 * 查询汇票到期日
	 * 且
	 * 状态为未清票的
	 */
	public void m10(){
		String sql=
				"select * " +
						"  from t_draft td " + 
						" where td.state = 2 " + 
						"   and to_char(sysdate, 'yyyymmdd') = to_char(td.due_date, 'yyyymmdd')";
	}
	
	/**
	 * 异常车辆超过总库存20%预警
	 */
	public void m11(){
		String sql=
				"select * " +
						"  from (select md.*, " + 
						"               (select count(1) " + 
						"                  from t_supervise_import tsi " + 
						"                 inner join t_draft td " + 
						"                    on td.draft_num = tsi.draft_num " + 
						"                 where td.distribid = md.id " + 
						"                   and (tsi.state = 5 or tsi.state=6) " + 
						"                   and tsi.apply = 0) as yc, " + 
						"               (select count(1) " + 
						"                  from t_supervise_import tsi " + 
						"                 inner join t_draft td " + 
						"                    on td.draft_num = tsi.draft_num " + 
						"                 where td.distribid = md.id " + 
						"                   and tsi.state != 1 " + 
						"                   and (tsi.state != 2 or (tsi.state = 2 and tsi.apply = 1))) as allcar " + 
						"          from market_dealer md) t " + 
						" where t.allcar > 0 " + 
						"   and (t.yc / t.allcar) > 0.2";
	}
	
	
	//------------------------------------监管员-------------------------------------
	
	
}

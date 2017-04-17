package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.CheckstockVO;
import com.zd.csms.zxbank.dao.ICheckstockDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 盘库信息DAO实现 
 */
public class CheckstockDAO extends DAOSupport implements ICheckstockDAO {

	public CheckstockDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Checkstock> findAllList(Checkstock query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_checkstock");
		List<Object> parameter=new ArrayList<>();
		formatSQL(query,parameter,sql);
		List<Checkstock> list=null;
		try {
			list=tools.goPage(sql.toString(),parameter.toArray(),new BeanPropertyRowMapper(Checkstock.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(Checkstock query, List<Object> parameter,
			StringBuffer sql) {
		sql.append(" where 1=1");
		if(!StringUtil.isEmpty(query.getCsLoncpid())){
			parameter.add("%"+query.getCsLoncpid().trim()+"%");
			sql.append(" and cs_loncpid like ?");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Checkstock getCheckstock(int loncpid) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_checkstock where cs_loncpid="+loncpid);
		List<Checkstock> list=null;
		try {
			list = getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper(Checkstock.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckstockVO> findAllVOList(int id, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select ck.ck_csid as scid,ckw.ch_whlevel as whlevel ,ck.ck_spvwhcode as whcode, ckw.ch_whaddr as whaddr, ck.ck_cmcode as cmcode,ck.ck_cstkcmdnum as num,ck.ck_cmgrtcntno as cmgrtcntno,ck.ck_vin as vin from zx_checkstock cks join zx_check ck on ck.ck_csid=cks.cs_id join zx_checkwarehouse ckw on ckw.ch_whcode=ck.ck_spvwhcode");
		sql.append(" where ck_csid="+id);
		List<CheckstockVO> list=null;
		try {
			list=tools.goPage(sql.toString(),new BeanPropertyRowMapper(CheckstockVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}

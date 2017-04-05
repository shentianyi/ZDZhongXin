package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.dao.IGagerDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
public class GagerDAO extends DAOSupport implements IGagerDAO {

	public GagerDAO(String dataSourceName) {
		super(dataSourceName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Gager> findAllList(Gager query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_gager");
		List<Object> parameter=new ArrayList<>();
		List<Gager> list=null;
		formatSQL(query,parameter,sql);
		try {
			list=tools.goPage(sql.toString(),parameter.toArray(),new BeanPropertyRowMapper(Gager.class));
			for (Gager gager : list) {
				System.out.println(gager.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(Gager query, List<Object> parameter, StringBuffer sql) {
		sql.append(" where 1=1");
		if(!StringUtil.isEmpty(query.getGaLonentno())){
			parameter.add("%"+query.getGaLonentno().trim()+"%");
			sql.append(" and ga_lonentno like ?");
		}
	}

	@Override
	public Gager getGager(int gaid, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		if(!StringUtil.isEmpty(gaid+"")){
			sql.append("select * from zx_gager where ga_id="+gaid);
		}
		List<Gager> list=null;
		try {
			list = tools.goPage(sql.toString(), new BeanPropertyRowMapper(Gager.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gager ager=list.get(0);
		return ager;
	}


}

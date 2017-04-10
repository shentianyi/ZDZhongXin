package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Commodity;
import com.zd.csms.zxbank.dao.ICommodityDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CommodityDAO extends DAOSupport implements ICommodityDAO {

	public CommodityDAO(String dataSourceName) {
		super(dataSourceName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Commodity> findAllList(Commodity query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		List<Commodity> list = null;
		if(!StringUtil.isEmpty(query.getCmGaid()+"")){
			sql.append("select * from zx_commodity where cm_gaid="+query.getCmGaid());	
		}
		try {
			list = tools.goPage(sql.toString(),new BeanPropertyRowMapper(Commodity.class));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}

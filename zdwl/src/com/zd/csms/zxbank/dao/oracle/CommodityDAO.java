package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Commodity;
import com.zd.csms.zxbank.dao.ICommodityDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 *  质物入库详情DAO实现
 */
public class CommodityDAO extends DAOSupport implements ICommodityDAO {

	public CommodityDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> findAllList(Commodity query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Commodity> list = null;
		if (!StringUtil.isEmpty(query.getCmGaid() + "")) {
			sql.append("SELECT * FROM ZX_COMMODITY WHERE CMGAID=" + query.getCmGaid());
		}
		try {
			list = tools.goPage(sql.toString(), new BeanPropertyRowMapper(Commodity.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

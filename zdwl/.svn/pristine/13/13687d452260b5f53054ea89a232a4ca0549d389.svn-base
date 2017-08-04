package com.zd.csms.finance.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.finance.dao.IOpenInvoiceListDAO;
import com.zd.csms.finance.dao.mapper.OpenInvoiceListMapper;
import com.zd.csms.finance.model.OpenInvoiceListQueryVO;
import com.zd.csms.finance.model.OpenInvoiceListVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class OpenInvoiceListOracleDAO extends DAOSupport implements IOpenInvoiceListDAO {

	public OpenInvoiceListOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_open_invoice_list = "select * from t_open_invoice_list";

	private boolean formatOpenInvoiceListWhereSQL(OpenInvoiceListQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getOpen_invoice_id()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("open_invoice_id=?");
			params.add(vo.getOpen_invoice_id());
			queryFlag = true;
		}
		if(vo.getPay_time() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("pay_time = ?");
			params.add(vo.getPay_time());
			queryFlag = true;
		}
		if(vo.getIsinvoice()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("isinvoice=?");
			params.add(vo.getIsinvoice());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getInvoice_type())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("invoice_type = ?");
			params.add(vo.getInvoice_type());
			queryFlag = true;
		}
		if(vo.getIsTransfer()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("isTransfer=?");
			params.add(vo.getIsTransfer());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<OpenInvoiceListVO> searchOpenInvoiceList(OpenInvoiceListQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(OpenInvoiceListOracleDAO.select_open_invoice_list);
		formatOpenInvoiceListWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<OpenInvoiceListVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new OpenInvoiceListMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<OpenInvoiceListVO> searchOpenInvoiceListByPage(OpenInvoiceListQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(OpenInvoiceListOracleDAO.select_open_invoice_list);
		formatOpenInvoiceListWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<OpenInvoiceListVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new OpenInvoiceListMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}

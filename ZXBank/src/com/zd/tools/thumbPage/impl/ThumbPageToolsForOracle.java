package com.zd.tools.thumbPage.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.constants.Constants;
import com.zd.tools.SqlUtil;
import com.zd.tools.ValueObjectUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;

//翻页标签工具（Oracle实现类）
public class ThumbPageToolsForOracle extends DAOSupport implements IThumbPageTools {
	
	public ThumbPageToolsForOracle(String dataSourceName) {
		super(dataSourceName);
	}
	
	private String tableName;	//翻页列表名称
	private HttpServletRequest request;
	private ThumbPageVO thumbPageVO;	//翻页信息对象

	private JdbcTemplate jdbcTemplate = this.getJdbcTemplate();	//jdbc
	
	private String countSQL = null;
	private Object[] countParams = new Object[]{};
	private int[] countParamTypes = new int[]{};
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//初始化
	public void init(String tableName,HttpServletRequest request){
		this.tableName = tableName;
		this.request = request;
		/*if(mustLoadLastParams()){
			this.thumbPageVO = (ThumbPageVO)request.getSession().getAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		}*/

		if(this.thumbPageVO==null){
			this.thumbPageVO = creatThumbPageVO();
			//request.getSession().setAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName,this.thumbPageVO);
			request.setAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName,this.thumbPageVO);
		}
	}
	
	public void setCountSQL(String sql,Object[] params,int[] types){
		countSQL = sql;
		countParams = params;
		countParamTypes = types;
	}
	
	public void setCountSQL(String sql,Object[] params){
		countSQL = sql;
		countParams = params;
	}
	
	public void setCountSQL(String sql){
		countSQL = sql;
	}
	
	//记录或获取查询条件
	public void saveQueryVO(Object queryVO){
		
		/*Object fromVo = request.getSession().getAttribute(ThumbPageConstants.THUMBPAGE_QUERY.getCode() + tableName);
		
		if(mustLoadLastQuery()){
			if(fromVo!=null){
				ValueObjectUtil.setVoValue(queryVO,fromVo);
			}
		}
		else{
			if(fromVo!=null && ValueObjectUtil.hasDifferentValue(queryVO,fromVo)){
				this.thumbPageVO.setCurrentPageNum(1);
			}
			request.getSession().setAttribute(ThumbPageConstants.THUMBPAGE_QUERY.getCode() + tableName,queryVO);
		}*/
	}
	
	public Object getQueryVO(){
		return request.getSession().getAttribute(ThumbPageConstants.THUMBPAGE_QUERY.getCode() + tableName);
	}
	
	private boolean mustLoadLastParams(){
		
		//检查参数中是否标明查询条件需要重新读取
		String param_remember = request.getParameter(tableName + "_param." + ThumbPageConstants.PARAM_REMEMBER.getCode());
		if(ThumbPageConstants.PARAM_REMEMBER_TRUE.getCode().equals(param_remember)){
			return false;
		}
		return true;
	}
	
	private boolean mustLoadLastQuery(){
		
		//检查参数中是否标明查询条件需要重新读取
		String param_query = request.getParameter(tableName + "_param." + ThumbPageConstants.PARAM_QUERY.getCode());
		String param_query2 = request.getParameter(Constants.PARAM_MENU_ENTRY.getCode());
		if(ThumbPageConstants.PARAM_QUERY_TRUE.getCode().equals(param_query)
		|| Constants.VALUE_MENU_ENTRY_TRUE.getCode().equals(param_query2)){
			return false;
		}
		return true;
	}
	
	//根据request生成翻页信息对象
	private ThumbPageVO creatThumbPageVO(){
		ThumbPageVO vo = new ThumbPageVO();
		
		String totalPagesNum = request.getParameter(getParamName("totalPagesNum"));
		if(totalPagesNum!=null && !"".equals(totalPagesNum))
			vo.setTotalPagesNum(Integer.parseInt(totalPagesNum));	//总页数
		
		String currentPageNum = request.getParameter(getParamName("currentPageNum"));
		if(currentPageNum!=null && !"".equals(currentPageNum))
			vo.setCurrentPageNum(Integer.parseInt(currentPageNum));  //当前页数
		
		String pageSize = request.getParameter(getParamName("pageSize"));
		if(pageSize!=null && !"".equals(pageSize))
			vo.setPageSize(Integer.parseInt(pageSize)); //单页显示记录数
		
		String totalItemsNum = request.getParameter(getParamName("totalItemsNum"));
		if(totalItemsNum!=null && !"".equals(totalItemsNum))
			vo.setTotalItemsNum(Integer.parseInt(totalItemsNum));  //总记录数
		
		String orderField = request.getParameter(getParamName("orderField"));
		if(orderField!=null && !"".equals(orderField))
			vo.setOrderField(orderField);	//排序用字段	
		
		return vo;
	}
	
	private String getParamName(String name){
		return tableName + "_param." + name;
	}
	
	//翻页方法
	public List<?> goPage(String sql){
		setThumbPageVO(sql,new Object[]{},new int[]{});
		String exeSQl = formatSQL(sql);
		SqlUtil.debug(exeSQl, "数据库分页语句");
		return jdbcTemplate.queryForList(exeSQl);
	}

	//翻页方法
	public List<?> goPage(String sql,Object[] args){
		setThumbPageVO(sql,args,new int[]{});
		String exeSQl = formatSQL(sql);
		SqlUtil.debug(getDataSourceName(), exeSQl, args,"数据库分页语句");
		return jdbcTemplate.queryForList(exeSQl,args);
	}

	//翻页方法
	public List<?> goPage(String sql,Object[] args, int[] argTypes){
		setThumbPageVO(sql,args,argTypes);
		String exeSQl = formatSQL(sql);
		SqlUtil.debug(getDataSourceName(), exeSQl, args,"数据库分页语句");
		return jdbcTemplate.queryForList(exeSQl,args,argTypes);
	}

	//翻页方法
	public List<?> goPage(String sql,RowMapper rowMapper){
		setThumbPageVO(sql,new Object[]{},new int[]{});
		String exeSQl = formatSQL(sql);
		SqlUtil.debug(exeSQl, "数据库分页语句");
		return jdbcTemplate.query(exeSQl,rowMapper);
	}

	//翻页方法
	public List<?> goPage(String sql,Object[] args,RowMapper rowMapper){
		setThumbPageVO(sql,args,new int[]{});
		String exeSQl = formatSQL(sql);
		SqlUtil.debug(getDataSourceName(), exeSQl, args,"数据库分页语句");
		return jdbcTemplate.query(exeSQl,args,rowMapper);
	}

	//翻页方法
	public List<?> goPage(String sql,Object[] args, int[] argTypes,RowMapper rowMapper){
		setThumbPageVO(sql,args,argTypes);
		String exeSQl = formatSQL(sql);
		SqlUtil.debug(getDataSourceName(), exeSQl, args,"数据库分页语句");
		return jdbcTemplate.query(exeSQl,args,argTypes,rowMapper);
	}

	//设置翻页信息
	public void setThumbPageVO(String sql,Object[] args,int[] argTypes){
		
		StringBuffer executeSQL = new StringBuffer();
		
		if(countSQL==null){
			String lowerSQL=sql.toLowerCase();
			if(lowerSQL.lastIndexOf("order by")!=-1){
				sql = sql.substring(0,lowerSQL.lastIndexOf("order by"));
			}
			
			executeSQL.append("select count(*) from (").append(sql).append(")");
		}
		else{
			executeSQL.append(countSQL);
			args = countParams;
			argTypes = countParamTypes;
		}
		
		int totalItemsNum;

		SqlUtil.debug(getDataSourceName(), executeSQL.toString(),args,"数据库count语句");
		if(argTypes.length>0){
			totalItemsNum = jdbcTemplate.queryForInt(executeSQL.toString(),args,argTypes);
		}
		else{
			totalItemsNum = jdbcTemplate.queryForInt(executeSQL.toString(),args);
		}
		
		int totalPagesNum = (totalItemsNum / thumbPageVO.getPageSize());
		
		if(totalItemsNum % thumbPageVO.getPageSize()>0){
			totalPagesNum += 1;
		}
		
		thumbPageVO.setTotalItemsNum(totalItemsNum);
		thumbPageVO.setTotalPagesNum(totalPagesNum);
	}
	
	//格式化翻页用SQL语句
	private String formatSQL(String sql){
		
		int beginPageNum = thumbPageVO.getCurrentPageNum()-1;
		int beginItemNum = thumbPageVO.getPageSize() * beginPageNum;
		int endItemNum = beginItemNum + thumbPageVO.getPageSize();
		
		StringBuffer returnSQL = new StringBuffer();
		
		returnSQL.append("select * from (select row_.*, rownum rownum_ from (");
	
		if(thumbPageVO.getOrderField()==null || "".equals(thumbPageVO.getOrderField())){
			returnSQL.append(sql);
		}
		else{
			
			String lowerSQL=sql.toLowerCase();
			String defaultOrderSQL = "";
	
			if(lowerSQL.lastIndexOf("order")!=-1){
				
				returnSQL.append(sql.substring(0,lowerSQL.lastIndexOf("order")));
				returnSQL.append(" order by ");
				
				lowerSQL = lowerSQL.substring(lowerSQL.lastIndexOf("order") + 5);
				
				defaultOrderSQL = "," + lowerSQL.substring(lowerSQL.indexOf(" by") + 3);
			}
			else{
				returnSQL.append(sql).append(" order by ");
			}
			
			returnSQL.append(thumbPageVO.getOrderField()).append(defaultOrderSQL).append(",rownum");
		}
	
		returnSQL.append(") row_ where rownum <= ").append(endItemNum)
				 .append(") where rownum_ >= ").append(beginItemNum+1);
		
		return returnSQL.toString();
	}
	
	public void setTotalPagesNum(int totalPagesNum){
		this.thumbPageVO.setTotalPagesNum(totalPagesNum);
	}
	public void setCurrentPageNum(int currentPageNum){
		this.thumbPageVO.setCurrentPageNum(currentPageNum);
	}
	public void setPageSize(int pageSize){
		this.thumbPageVO.setPageSize(pageSize);
	}
	public void setTotalItemsNum(int totalItemsNum){
		this.thumbPageVO.setTotalItemsNum(totalItemsNum);
	}
	public void setOrderField(String field){
		this.thumbPageVO.setOrderField(field);
	}
	public ThumbPageVO getThumbPageVO(){
		return this.thumbPageVO;
	}
	

}

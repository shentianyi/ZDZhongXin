package com.zd.tools.thumbPage.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zd.core.DAOSupport;
import com.zd.tools.StringUtil;
import com.zd.tools.ValueObjectUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;

//翻页标签工具（Mysql实现类）
public class ThumbPageToolsForMysql extends DAOSupport implements IThumbPageTools {
	
	public ThumbPageToolsForMysql(String dataSourceName) {
		super(dataSourceName);
	}
	
	private String tableName;	//翻页列表名称
	private HttpServletRequest request;
	private ThumbPageVO thumbPageVO;	//翻页信息对象

	private JdbcTemplate jdbcTemplate = this.getJdbcTemplate();	//jdbc

	private String countSQL = null;
	private Object[] countParams = null;
	private int[] countParamTypes = null;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//初始化
	public void init(String tableName,HttpServletRequest request){
		this.tableName = tableName;
		this.request = request;
		
		if(mustLoadLastParams()){
			this.thumbPageVO = (ThumbPageVO)request.getSession().getAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		}

		if(this.thumbPageVO==null){
			this.thumbPageVO = creatThumbPageVO();
			request.getSession().setAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName,this.thumbPageVO);
		}
	}
	
	public void setCountSQL(String sql,Object[] params,int[] types){
		countSQL = sql;
		countParams = params;
		countParamTypes = types;
	}
	
	public void setCountSQL(String sql,Object[] params){
		setCountSQL(sql, params,new int[]{});
	}
	
	public void setCountSQL(String sql){
		setCountSQL(sql, new Object[]{}, new int[]{});
	}
	
	//记录或获取查询条件
	public void saveQueryVO(Object queryVO){
		
		Object fromVo = request.getSession().getAttribute(ThumbPageConstants.THUMBPAGE_QUERY.getCode() + tableName);
		
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
		}
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
		if(ThumbPageConstants.PARAM_QUERY_TRUE.getCode().equals(param_query)){
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
	@SuppressWarnings("unchecked")
	public List goPage(String sql){
		setThumbPageVO(sql,new Object[]{},new int[]{});
		return jdbcTemplate.queryForList(formatSQL(sql));
	}

	//翻页方法
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args){
		setThumbPageVO(sql,args,new int[]{});
		return jdbcTemplate.queryForList(formatSQL(sql),args);
	}

	//翻页方法
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args, int[] argTypes){
		setThumbPageVO(sql,args,argTypes);
		return jdbcTemplate.queryForList(formatSQL(sql),args,argTypes);
	}

	//翻页方法
	@SuppressWarnings("unchecked")
	public List goPage(String sql,RowMapper rowMapper){
		setThumbPageVO(sql,new Object[]{},new int[]{});
		return jdbcTemplate.query(formatSQL(sql),rowMapper);
	}

	//翻页方法
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args,RowMapper rowMapper){
		setThumbPageVO(sql,args,new int[]{});
		return jdbcTemplate.query(formatSQL(sql),args,rowMapper);
	}

	//翻页方法
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args, int[] argTypes,RowMapper rowMapper){
		setThumbPageVO(sql,args,argTypes);
		return jdbcTemplate.query(formatSQL(sql),args,argTypes,rowMapper);
	}

	//设置翻页信息
	private void setThumbPageVO(String sql,Object[] args,int[] argTypes){
		
		StringBuffer executeSQL = new StringBuffer();
		
		if(countSQL==null){
			String lowerSQL=sql.toLowerCase();
			if(lowerSQL.lastIndexOf("order")!=-1){
				sql = sql.substring(0,lowerSQL.lastIndexOf("order"));
			}
			executeSQL.append("select count(*) from (").append(sql).append(") a");
		}
		else{
			executeSQL.append(countSQL);
			args = countParams;
			argTypes = countParamTypes;
		}
		
		int totalItemsNum;
		
		if(argTypes !=null && argTypes.length>0){
			totalItemsNum = jdbcTemplate.queryForInt(executeSQL.toString(),args,argTypes);
		} else if(args !=null && args.length>0){
			totalItemsNum = jdbcTemplate.queryForInt(executeSQL.toString(),args);
		} else{
			totalItemsNum = jdbcTemplate.queryForInt(executeSQL.toString());
		}
		
		int totalPagesNum = (totalItemsNum / thumbPageVO.getPageSize());
		
		if(totalItemsNum % thumbPageVO.getPageSize()>0){
			totalPagesNum += 1;
		}
		
		thumbPageVO.setTotalItemsNum(totalItemsNum);
		
		if(totalPagesNum==0){
			thumbPageVO.setTotalPagesNum(1);
		}else{
			thumbPageVO.setTotalPagesNum(totalPagesNum);
		}
	}
	
	//格式化翻页用SQL语句
	private String formatSQL(String sql){
		
		int beginPageNum = thumbPageVO.getCurrentPageNum()-1;
		int beginItemNum = thumbPageVO.getPageSize() * beginPageNum;
		//int endItemNum = beginItemNum + thumbPageVO.getPageSize();
		String orderBy = thumbPageVO.getOrderField();
		if(StringUtil.isBlank(orderBy)){
			orderBy = "";
		} else {
			orderBy = " order by "+orderBy;
		}
		
		StringBuffer returnSQL = new StringBuffer();

		returnSQL.append(sql).append(orderBy).append(" limit ").append(beginItemNum).append(" , ").append(thumbPageVO.getPageSize());
		
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

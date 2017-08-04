package com.zd.csms.repository.web.excel;

import java.util.Map;

import com.zd.csms.repository.model.RepositoryEntity;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class RepositoryExportRowMapper implements IImportRowMapper {
	private Map<Integer,String[]> row;
	private String[] title;
	
	public RepositoryExportRowMapper(Map<Integer, String[]> row,
			String[] title) {
		this.row=row;
		this.title=title;
	}

	public Map<Integer, String[]> getRow() {
		return row;
	}

	public void setRow(Map<Integer, String[]> row) {
		this.row = row;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}

	@Override
	public String[] exportRow(Object obj) {
		
		RepositoryEntity entity=(RepositoryEntity) obj;
		//储备库招聘信息
		RepositoryVO repository=entity.getRepository();
		if(repository==null){
			return null;
		}else{
			return row.get(repository.getId());
		}
	}

	@Override
	public String[] exportTitle() {
		return title;
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

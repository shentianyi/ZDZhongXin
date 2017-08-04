package com.zd.csms.roster.web.excel;

import java.util.Map;

import com.zd.csms.roster.model.RosterEntity;
import com.zd.csms.roster.model.RosterVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class RosterExportRowMapper implements IImportRowMapper {
	private Map<Integer,String[]> row;
	private String[] title;
	public RosterExportRowMapper(Map<Integer, String[]> row, String[] title) {
		this.row=row;
		this.title=title;
	}

	@Override
	public String[] exportRow(Object obj) {
		RosterEntity entity=(RosterEntity)obj;
		RosterVO roster=entity.getRoster();
		return row.get(roster.getId());
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

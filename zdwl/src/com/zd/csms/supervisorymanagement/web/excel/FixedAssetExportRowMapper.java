package com.zd.csms.supervisorymanagement.web.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetListService;
import com.zd.csms.util.tagutil.RosterUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class FixedAssetExportRowMapper implements IImportRowMapper {
	private Map<Integer,String[]> row;
	private String[] title;
	
	public FixedAssetExportRowMapper(Map<Integer, String[]> row, String[] title) {
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
		FixedAssetsVO vo = (FixedAssetsVO)obj;
		return row.get(vo.getId());
	}

	@Override
	public String[] exportTitle() {
		return title;
	}

	@Override
	public Object importRow(String[] values) {
		
		UserService us = new UserService();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		FixedAssetsVO o = new FixedAssetsVO();
		try {
			o.setAsset_num(values[1]);
			String asset_type = values[2];
			if(asset_type.equals("电子设备")){
				o.setAsset_type(1);
			}else if(asset_type.equals("办公设备")){
				o.setAsset_type(2);
			}else if(asset_type.equals("其它")){
				o.setAsset_type(3);
			}else{
				o.setAsset_type(0);
			}
			o.setAsset_name(values[3]);
			o.setBrand(values[4]);
			o.setModel(values[5]);
			o.setFactory_code(values[6]);
			o.setAsset_moeny(values[7]);
			Date producedate = formatter.parse(values[8]);
			o.setProducedate(producedate);
			o.setAmount(values[9]);
			Date purchase_date = formatter.parse(values[10]);
			o.setPurchase_date(purchase_date);
			o.setUseful_life(values[11]);
			o.setLife(values[12]);
			String asset_state = values[13];
			if(asset_state.equals("使用中")){
				o.setAsset_state(1);
			}else if(asset_state.equals("闲置")){
				o.setAsset_state(2);
			}else if(asset_state.equals("报废")){
				o.setAsset_state(3);
			}else if(asset_state.equals("待处置")){
				o.setAsset_state(4);
			}else if(asset_state.equals("损毁待报废")){
				o.setAsset_state(5);
			}
			Date factory_date = formatter.parse(values[14]);
			o.setFactory_date(factory_date);
			o.setExpress(values[15]);
			o.setExpress_num(values[16]);
			o.setTrade_name(values[17]);
			o.setAddress(values[18]);
			String username = values[19];
			UserQueryVO uquery = new UserQueryVO();
			uquery.setUserName(username);
			List<UserVO> uList = us.searchUserList(uquery);
			if(uList != null && uList.size()>0){
				UserVO user = uList.get(0);
				o.setSendee(user.getId());
			}else{
				o.setSendee(0);
			}
			o.setKey(values[20]);
			o.setPassword(values[21]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return o;
	}

}

package com.zd.csms.supervisorymanagement.web.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.zd.csms.rbac.service.UserService;
import com.zd.csms.region.model.RegionQueryVO;
import com.zd.csms.region.model.RegionVO;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEntity;
import com.zd.csms.supervisory.model.SupervisorQueryVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsAndUserVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetListService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.tagutil.RosterUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class FixedAssetRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		
		FixedAssetListService service = new FixedAssetListService();
		RosterUtil rUtil = new RosterUtil();
		FixedAssetsVO vo = (FixedAssetsVO)obj;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		String asset_type = "";
		if(vo.getAsset_type() == 1){
			asset_type = "电子设备";
		}
		if(vo.getAsset_type() == 2){
			asset_type = "办公设备";
		}
		if(vo.getAsset_type() == 3){
			asset_type = "其它";
		}
		String asset_state = "";
		if(vo.getAsset_state() == 1){
			asset_state ="使用中";
		}
		if(vo.getAsset_state() == 2){
			asset_state ="闲置";
		}
		if(vo.getAsset_state() == 3){
			asset_state ="报废";
		}
		if(vo.getAsset_state() == 4){
			asset_state ="待处置";
		}
		if(vo.getAsset_state() == 5){
			asset_state ="损毁待报废";
		}
		
		String producedate = "";
		if(vo.getProducedate() != null){
			producedate = formatter.format(vo.getProducedate());
		}
		String purchase_date = "";
		if(vo.getPurchase_date() != null){
			purchase_date = formatter.format(vo.getPurchase_date());
		}
		String factory_date = "";
		if(vo.getFactory_date() != null){
			factory_date = formatter.format(vo.getFactory_date());
		}
		List<String> bb = new ArrayList<String>();
		
		FixedAssetListQueryVO falQuery = new FixedAssetListQueryVO();
		falQuery.setFid(vo.getId());
		List<FixedAssetListVO> faList = service.searchFixedAssetList(falQuery);
		StringBuffer sb = new StringBuffer();
		if(faList != null && faList.size()>0){
			for(FixedAssetListVO fvo : faList){
				bb.add(fvo.getDepartment());
				bb.add(rUtil.roster(fvo.getUsername(), "jgy"));
				bb.add(rUtil.roster(fvo.getUsername(), "gh"));
				bb.add(fvo.getTrade());
				bb.add(fvo.getTrade_province()+"");
				bb.add(fvo.getTrade_city()+"");
				bb.add(fvo.getAddress());
				bb.add(fvo.getPassword());
				bb.add(fvo.getKey());
				String deposit_time = "";
				if(fvo.getDeposit_time() != null){
					deposit_time = formatter.format(fvo.getDeposit_time());
				}
				bb.add(deposit_time);
				String out_time = "";
				if(fvo.getOut_time() != null){
					out_time = formatter.format(fvo.getOut_time());
				}
				bb.add(out_time);
				bb.add(fvo.getExpress());
				bb.add(fvo.getExpress_num());
				bb.add(fvo.getExpress_money());
				String repair_time = "";
				if(fvo.getRepair_time() != null){
					repair_time = formatter.format(fvo.getRepair_time());
				}
				bb.add(repair_time);
				bb.add(fvo.getRepair_money());
				bb.add(fvo.getDes());
			}
			
			
			String[] str1 = {vo.getAsset_num(),asset_type,vo.getAsset_name(),vo.getBrand(),vo.getModel(),
					vo.getFactory_code(),vo.getAsset_moeny(),producedate,vo.getAmount(),purchase_date,
					vo.getUseful_life(),vo.getLife(),asset_state,factory_date,vo.getExpress(),vo.getExpress_num(),
					vo.getTrade_name(),vo.getAddress(),rUtil.roster(vo.getSendee(), "jgy"),vo.getKey(),
					vo.getPassword()};
			
			String[] str2 = bb.toArray(new String[]{});
			
			int strLen1=str1.length;//保存第一个数组长度
	        int strLen2=str2.length;//保存第二个数组长度
	        str1= Arrays.copyOf(str1,strLen1+ strLen2);//扩容
	        System.arraycopy(str2, 0, str1, strLen1,strLen2 );//将第二个数组与第一个数组合并
	        
			return str1;
		}else{
			return new String[]{vo.getAsset_num(),asset_type,vo.getAsset_name(),vo.getBrand(),vo.getModel(),
					vo.getFactory_code(),vo.getAsset_moeny(),producedate,vo.getAmount(),purchase_date,
					vo.getUseful_life(),vo.getLife(),asset_state,factory_date,vo.getExpress(),vo.getExpress_num(),
					vo.getTrade_name(),vo.getAddress(),rUtil.roster(vo.getSendee(), "jgy"),vo.getKey(),
					vo.getPassword()};
		}
		
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"资产编码","资产类别","资产名称","品牌","规格型号","出厂编码","资产原值(元)","数量","购置日期"
				,"已使用年限","年限(年)","资产状态","出厂派发时间","快递公司","运单号","店名","地址","接收人","钥匙","密码"
				,"使用部门","使用人","员工工号","存放店","存放地址(省)","存放地址(市)","地址","密码","钥匙","存放时间","转出时间","运输公司"
				,"单号","运费(元)","维修时间","维修金额","维修内容","备注"};
	}

	@Override
	public Object importRow(String[] values) {
		
		//UserService us = new UserService();
		SupervisoryService ss = new SupervisoryService();
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		FixedAssetsVO o = new FixedAssetsVO();
		
		
		List<FixedAssetListVO> list = new ArrayList<FixedAssetListVO>();
		FixedAssetsAndUserVO faau = new FixedAssetsAndUserVO();
		
		
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
			if(values[8]!=null){
//				Date producedate = formatter.parse(values[8]);
				Date producedate = DateUtil.StringToDate(values[8]);
				
				o.setProducedate(producedate);
			}
			o.setAmount(values[9]);
			if(values[10]!=null){
				Date purchase_date = DateUtil.StringToDate(values[10]);
				o.setPurchase_date(purchase_date);
			}
			o.setUseful_life(values[10]);
			o.setLife(values[11]);
			String asset_state = values[12];
			if(asset_state.trim().equals("使用中")){
				o.setAsset_state(1);
			}else if(asset_state.trim().equals("闲置")){
				o.setAsset_state(2);
			}else if(asset_state.trim().equals("报废")){
				o.setAsset_state(3);
			}else if(asset_state.trim().equals("待处置")){
				o.setAsset_state(4);
			}else if(asset_state.trim().equals("损毁待报废")){
				o.setAsset_state(5);
			}
			if(values[13]!=null){
				Date factory_date = DateUtil.StringToDate(values[13]);
				o.setFactory_date(factory_date);
			}
			o.setExpress(values[14]);
			o.setExpress_num(values[15]);
			o.setTrade_name(values[16]);
			o.setAddress(values[17]);
			String username = values[18];
		/*	UserQueryVO uquery = new UserQueryVO();
			uquery.setUserName(username);
			List<UserVO> uList = us.searchUserList(uquery);*/
			SupervisorQueryVO sq = new SupervisorQueryVO();
			sq.setName(username.trim());
			List<SupervisorBaseInfoVO> sList = ss.searchLists(sq);
			
			if(sList != null && sList.size()>0){
			 SupervisorBaseInfoVO sbi = sList.get(0);
				o.setSendee(sbi.getId());
			}else{
				o.setSendee(0);
			}
			o.setKey(values[19]);
			o.setPassword(values[20]);
			
			
			int num = 18,i = 0;
			
			
			while(((values.length-20)-i) >= num-1){
				String[] value = Arrays.copyOfRange(values, (21 + i), (21+i)+num);
				FixedAssetListVO fal = excelParseToFixedAssetList(value);
				if(fal != null){
					list.add(fal);
				}
				i += num;
			}
			
			faau.setList(list);
			faau.setVo(o);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faau;
	}
	
	
	public FixedAssetListVO excelParseToFixedAssetList(String[] value){
		if((value[0] == null || "".equals(value[0].trim())) || (value[1] == null || "".equals(value[1].trim())) || (value[3] == null || "".equals(value[3].trim()))){
			return null;
		}
		SupervisoryService service = new SupervisoryService();
		FixedAssetListVO vo = new FixedAssetListVO();
		RegionService rs = new RegionService();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		SupervisorQueryVO query = new SupervisorQueryVO();
		query.setName(value[1]);
		List<SupervisorEntity> list = service.searchList(query);
		if(list != null && list.size()>0){
			SupervisorEntity seVO = list.get(0);
			vo.setUsername(seVO.getSuperVisorBaseInfo().getId());//使用人
		}else{
			return null;
		}
		vo.setDepartment(value[0]);//使用部门
		vo.setTrade(value[2]);//存放店
		vo.setAddress(value[3]);//地址
		
		RegionQueryVO regionQuery = new RegionQueryVO();
		if(value[4] != null && !"".equals(value[4].trim())){
			regionQuery.setName(value[4].trim());
			List<RegionVO> provinceRegion = rs.searchList(regionQuery);
				if(provinceRegion != null && provinceRegion.size()>0){
					RegionVO regionVO1 = provinceRegion.get(0);
					vo.setTrade_province(regionVO1.getId());//存放地址（省）
				}else{
					vo.setTrade_province(0);
				}
		}
		if(value[5] != null && !"".equals(value[5].trim())){
			regionQuery.setName(value[5].trim());
			List<RegionVO> cityRegion = rs.searchList(regionQuery);
			if(cityRegion != null && cityRegion.size()>0){
				RegionVO regionVO2 = cityRegion.get(0);
				vo.setTrade_city(regionVO2.getId());//存放地址（市）
			}else{
				vo.setTrade_city(0);
			}
		}
	
		vo.setPassword(value[6]);//密码
		vo.setKey(value[7]);//钥匙
		if(value[8] != null && !value[8].trim().equals("")){
			vo.setDeposit_time(DateUtil.StringToDate(value[8]));////存放时间
		}
		if(value[9] != null && !value[9].trim().equals("")){
			vo.setOut_time(DateUtil.StringToDate(value[9]));//转出时间
		}
		vo.setExpress(value[10]);////运输公司
		vo.setExpress_num(value[11]);//单号
		vo.setExpress_money(value[12]);//运费
		
		if(value[13] != null && !value[13].trim().equals("")){
			vo.setRepair_time(DateUtil.StringToDate(value[13]));//维修时间
		}
		
		vo.setRepair_money(value[14]);//维修金额
		vo.setRepair_des(value[15]);//维修内容
		//vo.setReceive_pic(value[16]);//设备接收单   //未定图片来源
		vo.setDes(value[17]);//备注
		return vo;
	}

}

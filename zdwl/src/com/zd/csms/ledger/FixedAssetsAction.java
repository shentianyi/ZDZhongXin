package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ArrayUtils;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetListService;
import com.zd.csms.supervisorymanagement.service.FixedAssetsService;
import com.zd.csms.supervisorymanagement.web.excel.FixedAssetExportRowMapper;
import com.zd.csms.supervisorymanagement.web.form.FixedAssetsForm;
import com.zd.csms.util.tagutil.RosterUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class FixedAssetsAction extends ActionSupport {

	public ActionForward fixedAssetsLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		FixedAssetsForm faform = (FixedAssetsForm)form;
		FixedAssetsService service = new FixedAssetsService();
		
		FixedAssetsQueryVO faQuery = faform.getFixedAssetsquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("fixedAssetsLedger",request);
		thumbPageTools.saveQueryVO(faQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<FixedAssetsVO> list = service.searchFixedAssetsListByPage(faQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("fixed_assets_ledger");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("AssetsStateOptions", OptionUtil.getAssetsStateContant());
		request.setAttribute("AssetsTypeOptions", OptionUtil.getAssetsTypeContant());
		request.setAttribute("supervisorsOptions", OptionUtil.getAllSupervise());
		
	}
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		FixedAssetsForm faform = (FixedAssetsForm)form;
		FixedAssetsService service = new FixedAssetsService();
		
		FixedAssetsQueryVO faQuery = faform.getFixedAssetsquery();
		
		//按条件查询分页数据
		List<FixedAssetsVO> list = service.searchFixedAssets(faQuery);
		IExportFile export = new ExportFileExcelImpl();
		//列内容
		Map<Integer,String[]> row=new HashMap<Integer,String[]>();
		//列标题
		String[] title=new String[]{};
		String[] title1=new String[]{"资产编码","资产类别","资产名称","品牌","规格型号","出厂编码","资产原值(元)","生产日期","数量","购置日期"
				,"已使用年限","年限(年)","资产状态","出厂派发时间","快递公司","运单号","店名","地址","接收人","钥匙","密码"};
		String[] title2=new String[]{"使用部门","使用人","员工工号","存放店","存放地址(省)","存放地址(市)","地址","密码","钥匙","存放时间","转出时间","运输公司"
				,"单号","运费(元)","维修时间","维修金额","维修内容","备注"};
		if(list!=null && list.size()>0){
			for(FixedAssetsVO vo:list){
				//当前title
				String[] titleCurr=new String[]{};
				
				FixedAssetListService fixedAssetListService = new FixedAssetListService();
				RosterUtil rUtil = new RosterUtil();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
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
				String[] str1 = {vo.getAsset_num(),asset_type,vo.getAsset_name(),vo.getBrand(),vo.getModel(),
						vo.getFactory_code(),vo.getAsset_moeny(),producedate,vo.getAmount(),purchase_date,
						vo.getUseful_life(),vo.getLife(),asset_state,factory_date,vo.getExpress(),vo.getExpress_num(),
						vo.getTrade_name(),vo.getAddress(),rUtil.roster(vo.getSendee(), "jgy"),vo.getKey(),
						vo.getPassword()};
				
				//使用人列表
				List<String> bb = new ArrayList<String>();
				FixedAssetListQueryVO falQuery = new FixedAssetListQueryVO();
				falQuery.setFid(vo.getId());
				List<FixedAssetListVO> faList = fixedAssetListService.searchFixedAssetList(falQuery);
				String[] title3=new String[]{};
				if(faList != null && faList.size()>0){
					for(int i=0;i<faList.size();i++){
						FixedAssetListVO fvo=faList.get(i);
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
						bb.add(fvo.getRepair_des());
						bb.add(fvo.getDes());
						title3 = (String[]) ArrayUtils.concat(title3, title2);
					}
					
					String[] str2 = bb.toArray(new String[]{});
			        row.put(vo.getId(), (String[]) ArrayUtils.concat(str1,str2));
			        
			        titleCurr= (String[]) ArrayUtils.concat(title1, title3);
				}else{
					row.put(vo.getId(),new String[]{vo.getAsset_num(),asset_type,vo.getAsset_name(),vo.getBrand(),vo.getModel(),
							vo.getFactory_code(),vo.getAsset_moeny(),producedate,vo.getAmount(),purchase_date,
							vo.getUseful_life(),vo.getLife(),asset_state,factory_date,vo.getExpress(),vo.getExpress_num(),
							vo.getTrade_name(),vo.getAddress(),rUtil.roster(vo.getSendee(), "jgy"),vo.getKey(),
							vo.getPassword()});
					titleCurr=title1;
				}
				//取Title长度最长的为最终Title
				if(titleCurr.length>title.length){
					title=titleCurr;
				}
				
			}
			
		}
	
		try {
			export.export(list, new FixedAssetExportRowMapper(row,title), export.createDefaultFileName("固定资产管理台账"),"固定资产管理台账", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
}

package com.zd.csms.business.web.excel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.queryBean.DraftQueryBean;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class DraftRowMapper implements IImportRowMapper {
	private DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public String[] exportRow(Object obj) {
		

		DraftQueryBean dvo = (DraftQueryBean)obj;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String billing_date = "";
		if(dvo.getBilling_date() != null){
			billing_date=formatter.format(dvo.getBilling_date());
		}
		
		String due_date = "";
		if(dvo.getBilling_date() != null){
			due_date = formatter.format(dvo.getDue_date());
		}
		
		String state = "";
		if(dvo.getState() == 1){
			state ="清票";
		}else{
			state ="未清票";
		}
		
		return new String[]{dvo.getDealerName(),dvo.getBankName(),dvo.getBrandName(),dvo.getAgreement(),dvo.getBail_num(),dvo.getBailscale()+"",dvo.getDraft_num(),billing_date,
				due_date,dvo.getBilling_money(),df.format(dvo.getYycMoney()),
				dvo.getKcts()+"",df.format(dvo.getKcMoney())
				,df.format(dvo.getCkMoney()),df.format(dvo.getHkMoney()),df.format(dvo.getWycMoney())
				,state};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"经销商","金融机构","品牌","质押协议号","保证金账号","保证金比例(%)","汇票号码","开票日","到期日","开票金额(元)","已押车金额",
				"库存台数","库存金额","敞口金额","回款金额","未押车金额","状态"};
	}

	@Override
	public Object importRow(String[] values) {
		/*DealerService ds = new DealerService();
		int dealerid = 0;
		DraftVO dvo = new DraftVO();
		try {
			dvo.setAgreement(values[1]);
			dvo.setBail_num(values[2]);
			String dealername = values[3];
			if(!StringUtil.isEmpty(dealername)){
				DealerQueryVO dquery = new DealerQueryVO();
				dquery.setDealerName(dealername);
				List<DealerVO> dList = ds.searchDealerList(dquery);
				if(dList != null && dList.size()>0){
					for (DealerVO dealerVO : dList) {
						String bankName = ds.getBankNameByDealerId(dealerVO.getId());
						if(bankName!=null&&bankName.equals(values[4])){
							dealerid = dealerVO.getId();
							break;
						}
					}
				}
			}
			dvo.setDistribid(dealerid);
			dvo.setFinancial_institution(values[4]);
			dvo.setBrand(values[5]);
			dvo.setDraft_num(values[6]);
			Date billing_date =DateUtil.StringToDate(values[7]);
			dvo.setBilling_date(billing_date);
			Date due_date =DateUtil.StringToDate(values[8]);
			dvo.setDue_date(due_date);
			if(StringUtil.isEmpty(values[9])){
				values[9]="0";
			}else{
				values[9]=df.format(Double.parseDouble(values[9]));
			}
			dvo.setBilling_money(values[9]);
			dvo.setState(2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dvo;*/
		return values;
	}

}

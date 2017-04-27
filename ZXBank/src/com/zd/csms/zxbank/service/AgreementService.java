package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.IAgreementDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.bean.AgreementFar;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 监管协议Service
 */
public class AgreementService extends ServiceSupport {
	private IAgreementDAO dao = ZXBankDAOFactory.getAgreementDAO();

	public List<Agreement> findBusinessList(Agreement query, IThumbPageTools tools) {
		return dao.firnAllAgList(query, tools);
	}
	// 远程数据与本地数据比对
		public void autoUpdateCust(List<AgreementFar> bankList,Agreement query) throws Exception {
			List<Agreement> list = dao.query();
			if (bankList != null && bankList.size() > 0 && list != null)
				for (AgreementFar agreementFar : bankList) {
					int tem = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getAgProtocolno().trim().equals(agreementFar.getSpvagtid().trim())) {
							if(update(agreementFar,query)){
								System.out.println("更新协议："+agreementFar.getSpvagtid()+"成功");
							}else{
								System.out.println("更新协议："+agreementFar.getSpvagtid()+"失败");
							}
							tem++;
							break;
						}
					}
					if (tem == 0) {
						if(add(agreementFar,query)){
							System.out.println("保存协议：" + agreementFar.getSpvagtid()+"  成功");
						}else{
							System.out.println("保存协议：" + agreementFar.getSpvagtid()+"  失败");
						}
						
					}
				}

		}
		
	/**
	 * @param agtFar
	 * @return
	 */
	public boolean add(AgreementFar agtFar,Agreement agt){
		
		agt.setAgId(SqlUtil.getID(Agreement.class));
		agt.setAgCreatedate(new Date());
		agt.setAgEnddate(agtFar.getEnddate());
		agt.setAgIsmove(agtFar.getIsmv());
		agt.setAgIsonline(agtFar.getIsauth());
		agt.setAgLoncpid(agtFar.getHostno());
		agt.setAgLoncpname(agtFar.getLonnm());
		agt.setAgOperorg(agtFar.getOperorg());
		agt.setAgProtocolcode(agtFar.getSpvagtno());
		agt.setAgProtocolno(agtFar.getSpvagtid());
		agt.setAgState(agtFar.getAgtstt());
		agt.setAgStdate(agtFar.getStartdate());
//		agt.setAgTotnum(agt.getAgTotnum());//总记录条数
		return  dao.add(agt);
	}
	
	public boolean update(AgreementFar agtFar,Agreement agt){
		agt.setAgEnddate(agtFar.getEnddate());
		agt.setAgIsmove(agtFar.getIsmv());
		agt.setAgIsonline(agtFar.getIsauth());
		agt.setAgLoncpid(agtFar.getHostno());
		agt.setAgLoncpname(agtFar.getLonnm());
		agt.setAgOperorg(agtFar.getOperorg());
		agt.setAgProtocolcode(agtFar.getSpvagtno());
		agt.setAgProtocolno(agtFar.getSpvagtid());
		agt.setAgState(agtFar.getAgtstt());
		agt.setAgStdate(agtFar.getStartdate());
		agt.setAgUpdatedate(new Date());
//		agt.setAgTotnum(agt.get)
		return dao.update(agt);
	}
	
}

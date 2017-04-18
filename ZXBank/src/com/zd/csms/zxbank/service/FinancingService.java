package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.csms.zxbank.dao.IAgreementDAO;
import com.zd.csms.zxbank.dao.IFinancingDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.bean.FinancingFar;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 融资信息Service
 */
public class FinancingService extends ServiceSupport {

	private IFinancingDAO dao = ZXBankDAOFactory.getFinancingDAO();
	private IAgreementDAO adao=ZXBankDAOFactory.getAgreementDAO();
	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}

	public void addOrUpdate(List<FinancingFar> list,FinancingQueryVO query) {
		System.out.println("报文集合"+list);
		System.out.println("查询条件："+query.getFgLonentNo());
		Agreement agreement=adao.getAgreement(query.getFgLonentNo().trim());
		System.out.println("agreement:"+agreement);
		Financing fg=new Financing();
		for (FinancingFar ffar : list) {
			int count = dao.getCountByOther(ffar.getLoanCode());
			if (count > 0) {
					fg.setFgStDate(ffar.getLoanstDate());
					fg.setFgEndDate(ffar.getLoanendDate());
					fg.setFgBailRat(ffar.getBailRat());
					fg.setFgBizMod(ffar.getBizMod());
					fg.setFgFstblRat(ffar.getFstblRat());
					fg.setFgLoanAmt(ffar.getLoanAmt());
					fg.setFgLoanCode(ffar.getLoanCode());
					fg.setFgOperOrg(ffar.getOperOrg());
					fg.setFgProcrt(ffar.getProcrt());
					fg.setFgScftxNo(ffar.getScftxNo());
					fg.setFgSlfcap(ffar.getSlfcapRat());
					fg.setFgLonentNo(query.getFgLonentNo());//借款企业id
					fg.setFgLoncpName(agreement.getAg_loncpname());//借款企业名称
					fg.setFgUpdateDate(new Date());
					if(dao.update(fg)){
						System.out.println("更新融资信息成功");
					}else{
						System.out.println("更新融资信息失败");
					}
			} else {
				fg.setFiId(SqlUtil.getID(Financing.class));
				fg.setFgCreateDate(new Date());
				fg.setFgStDate(ffar.getLoanstDate());
				fg.setFgEndDate(ffar.getLoanendDate());
				fg.setFgBailRat(ffar.getBailRat());
				fg.setFgBizMod(ffar.getBizMod());
				fg.setFgFstblRat(ffar.getFstblRat());
				fg.setFgLoanAmt(ffar.getLoanAmt());
				fg.setFgLoanCode(ffar.getLoanCode());
				fg.setFgOperOrg(ffar.getOperOrg());
				fg.setFgProcrt(ffar.getProcrt());
				fg.setFgScftxNo(ffar.getScftxNo());
				fg.setFgSlfcap(ffar.getSlfcapRat());
				fg.setFgLonentNo(query.getFgLonentNo());//借款企业id
				fg.setFgLoncpName(agreement.getAg_loncpname());//借款企业名称
				if(dao.add(fg)){
					System.out.println("融资添加成功");
				}else{
					System.out.println("添加失败");
				}
			}
		}
	}
}

package com.zd.csms.planandreport.dao;

import com.zd.core.IDAO;
import com.zd.csms.planandreport.model.InspectionPlanInfoVO;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;

public interface IInspectionPlanInfoDAO extends IDAO{
	public InspectionPlanInfoVO get(String planCode);
	public boolean update(String planCode);
	public InspectionPlanInfoQueryBean getDealerInfoByPlanCode(String planCode);
	public InspectionPlanInfoQueryBean getLedgerDealerInfo(InspectionPlanQueryVO query);
	public InspectionPlanInfoQueryBean getLedgerInfo(InspectionPlanQueryVO query);
	public InspectionPlanInfoQueryBean getDealerInfo(String planCode);
}

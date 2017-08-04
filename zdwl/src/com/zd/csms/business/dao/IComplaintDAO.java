package com.zd.csms.business.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.business.model.ComplaintInfoQueryBean;
import com.zd.csms.business.model.ComplaintInfoQueryVO;
import com.zd.csms.business.model.ComplaintQueryBean;
import com.zd.csms.business.model.ComplaintQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IComplaintDAO extends IDAO{
	public List<ComplaintInfoQueryBean> findList(ComplaintInfoQueryVO query, IThumbPageTools tools);
	public List<ComplaintInfoQueryBean> findListLedger(ComplaintInfoQueryVO query, IThumbPageTools tools);
	public List<ComplaintInfoQueryBean> ExtComplaintLedger(ComplaintInfoQueryVO query);
}

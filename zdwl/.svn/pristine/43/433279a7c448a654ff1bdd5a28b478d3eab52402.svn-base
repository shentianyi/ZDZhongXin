package com.zd.csms.bank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.bank.bean.Gyl001;
import com.zd.csms.bank.bean.Gyl022;
import com.zd.csms.business.model.DraftToLnciVO;

public interface IBankDockDAO extends IDAO{
	public List<Gyl001> findAllList();
	public boolean update(Gyl001 gyl);
	public boolean updateDraftToLnci(Gyl022 gyl022);
	public String getcustName(String custNo);
	public DraftToLnciVO getDraftToLnciByDraftNum(String draftNum);
}

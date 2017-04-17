package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.DistribsetZX;
import com.zd.csms.zxbank.dao.IDistribsetDAO;
import com.zd.csms.zxbank.dao.SetDAOFactory;

public class DistribsetService extends ServiceSupport {

	private IDistribsetDAO dao = SetDAOFactory.getDistribsetDAO();

	public List<DistribsetZX> findorg(String org) {
		return dao.findorg(org);
	}

	/**
	 * 添加中信经销商组织机构代码
	 * @param zx
	 * @return
	 * @throws Exception
	 */
	public boolean addDistribset(DistribsetZX zx) throws Exception {

		boolean flag = false;
		if (zx.getOrganizationcode() != null && zx.getOrganizationcode() != "") {
			// 验证中信银行组织机构代码唯一性
			flag = validateZXCode(zx.getOrganizationcode(), zx.getZx_did());
			if (!flag) {
				this.setExecuteMessage("中信银行组织机构代码不能重复，请重新输入！");
				return flag;
			}
		}
		//zx.setId(Util.getID(DistribsetZX.class));
		flag = dao.add(zx);
		if (flag) {
			this.setExecuteMessage("添加成功！");
		} else {
			this.setExecuteMessage("添加失败！");
		}
		return flag;
	}

	/**
	 * 修改中信经销商组织机构代码
	 * @param zx
	 * @return
	 * @throws Exception
	 */
	public boolean updDistribset(DistribsetZX zx) throws Exception {
		boolean flag = false;
		if (zx.getOrganizationcode() != null && zx.getOrganizationcode() != "") {
			// 验证中信银行组织机构代码唯一性
			flag = validateZXCode(zx.getOrganizationcode(), zx.getZx_did());
			if (!flag) {
				this.setExecuteMessage("中信银行组织机构代码不能重复，请重新输入！");
				return flag;
			}
		}
		flag = dao.update(zx);
		if (flag) {
			this.setExecuteMessage("修改成功！");
		} else {
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}

	/**
	 * 中信银行组织机构代码唯一验证
	 * @param zsCustNo
	 * @param distribsetId
	 * @return
	 */
	public boolean validateZXCode(String zxCode, int distribsetId) {
		boolean flag = true;
		DistribsetZX query = new DistribsetZX();
		query.setOrganizationcode(zxCode);
		List<DistribsetZX> distribsetList = dao.searchDistribsetList(query);
		if (distribsetList != null && distribsetList.size() > 0) {
			for (DistribsetZX distribset : distribsetList) {
				if (distribset.getZx_did() != distribsetId) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

}

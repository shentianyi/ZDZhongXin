package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.bean.MoveNotice;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.bean.Warehouse;

public class BankInterfaceForm extends ActionForm {
	private static final long serialVersionUID = -8302069280424430582L;

	/**
	 * 导入文件Form
	 */
	private FormFile importFile;
	/**
	 * 通知推送Form
	 */
	private Notice notice = new Notice();
	/**
	 * 用户信息Form
	 */
	private Customer customer = new Customer();
	/**
	 * 仓库信息Form
	 */
	private Warehouse warehouse = new Warehouse();
	/**
	 * 融资信息Form
	 */
	private FinancingQueryVO financingVO = new FinancingQueryVO();
	/**
	 * 融资信息Form
	 */
	private Financing financing=new Financing();
	
	/**
	 * 监管协议Form
	 */
	private Agreement agreement = new Agreement();
	/**
	 * 收货通知Form
	 */
	private ReceivingNotice receivingnotice = new ReceivingNotice();
	/**
	 *解除质押Form
	 */
	private RemovePledge removepledge = new RemovePledge();
	/**
	 * 移库通知Form
	 */
	private MoveNotice movenotice = new MoveNotice();
	/**
	 * 质物入库Form
	 */
	private Gager gager = new Gager();
	/**
	 * 盘库盘点Form
	 */
	private Checkstock checkstock = new Checkstock();

	public FormFile getImportFile() {
		return importFile;
	}

	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNoctice(Notice notice) {
		this.notice = notice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public FinancingQueryVO getFinancingVO() {
		return financingVO;
	}

	public void setFinancingVO(FinancingQueryVO financingVO) {
		this.financingVO = financingVO;
	}

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public ReceivingNotice getReceivingnotice() {
		return receivingnotice;
	}

	public void setReceivingnotice(ReceivingNotice receivingnotice) {
		this.receivingnotice = receivingnotice;
	}

	public RemovePledge getRemovepledge() {
		return removepledge;
	}

	public void setRemovepledge(RemovePledge removepledge) {
		this.removepledge = removepledge;
	}

	public MoveNotice getMovenotice() {
		return movenotice;
	}

	public void setMovenotice(MoveNotice movenotice) {
		this.movenotice = movenotice;
	}

	public Gager getGager() {
		return gager;
	}

	public void setGager(Gager gager) {
		this.gager = gager;
	}

	public Checkstock getCheckstock() {
		return checkstock;
	}

	public void setCheckstock(Checkstock checkstock) {
		this.checkstock = checkstock;
	}

	public Financing getFinancing() {
		return financing;
	}

	public void setFinancing(Financing financing) {
		this.financing = financing;
	}

}

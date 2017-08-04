package com.zd.csms.supervisorymanagement.model;

public class HandoverBookVO  {
	
	private ElectronicTextVO eText=new ElectronicTextVO();
	private OfficeEquipmentVO officeEquipment=new OfficeEquipmentVO();
	private OtherDataVO OData=new OtherDataVO();
	private PaperTextVO pText=new PaperTextVO();
	public ElectronicTextVO geteText() {
		return eText;
	}
	public void seteText(ElectronicTextVO eText) {
		this.eText = eText;
	}
	public OfficeEquipmentVO getOfficeEquipment() {
		return officeEquipment;
	}
	public void setOfficeEquipment(OfficeEquipmentVO officeEquipment) {
		this.officeEquipment = officeEquipment;
	}
	public OtherDataVO getOData() {
		return OData;
	}
	public void setOData(OtherDataVO oData) {
		OData = oData;
	}
	public PaperTextVO getpText() {
		return pText;
	}
	public void setpText(PaperTextVO pText) {
		this.pText = pText;
	}
	
}

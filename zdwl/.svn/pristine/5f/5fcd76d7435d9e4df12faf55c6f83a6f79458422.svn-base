package com.zd.csms.messageAndWaring.message.model.business;

import java.util.Date;
import com.zd.core.annotation.table;
import com.zd.csms.messageAndWaring.message.model.MessageInfoVO;
import com.zd.tools.StringUtil;

/**
 * 业务部信息提醒实体
 * @author zhangzhicheng
 *
 */
@table(name = "t_businMsg_info")
public class BusinMessageInfoVO extends MessageInfoVO   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5940198750499576506L;
	private int id;// 主键id
	private int dealerId;// 经销商id
	
	private String draft_num;// 汇票号
	private Date billing_date;// 开票日
	private Date due_date;// 到期日
	private String billing_money;// 开票金额
	private String nomortgagecar_money;// 未押车金额
	private int car_num = 1;// 台数
	private String money;// 金额,车辆价值,在库车辆金额,移动车辆金额
	private String vin;// 车架号
	private Date movetime;// 移动时间
	private String mortgagecar_money;// * 已押车金额
	private Double yycMoney;// * 已押车金额 经历过在库状态的车辆
	private int yc;//移动数量
	private int allCar;//总库存数量
	private Date updDate;// 最近一笔释放业务时间
	private String price;//单价
	private int state;//1.待审批2.审核通过3.审核不通过
	private String moveAddress;//移动位置
	private int type;// 消息类别
	private Date createDate;// 创建时间
	private int signId;//信息标识 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	
	
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public Date getBilling_date() {
		return billing_date;
	}
	public void setBilling_date(Date billing_date) {
		this.billing_date = billing_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public String getBilling_money() {
		return billing_money;
	}
	public void setBilling_money(String billing_money) {
		this.billing_money = billing_money;
	}
	public String getNomortgagecar_money() {
		return nomortgagecar_money;
	}
	public void setNomortgagecar_money(String nomortgagecar_money) {
		this.nomortgagecar_money = nomortgagecar_money;
	}
	public int getCar_num() {
		return car_num;
	}
	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Date getMovetime() {
		return movetime;
	}
	public void setMovetime(Date movetime) {
		this.movetime = movetime;
	}
	public String getMortgagecar_money() {
		return mortgagecar_money;
	}
	public void setMortgagecar_money(String mortgagecar_money) {
		this.mortgagecar_money = mortgagecar_money;
	}
	
	
	public Double getYycMoney() {
		return yycMoney;
	}
	public void setYycMoney(Double yycMoney) {
		this.yycMoney = yycMoney;
	}
	public int getYc() {
		return yc;
	}
	public void setYc(int yc) {
		this.yc = yc;
	}
	public int getAllCar() {
		return allCar;
	}
	public void setAllCar(int allCar) {
		this.allCar = allCar;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	public String getMoveAddress() {
		return moveAddress;
	}
	public void setMoveAddress(String moveAddress) {
		this.moveAddress = moveAddress;
	}
	
	
	public void noMortgageCarMoneyForSeven(){
	    if (StringUtil.isNotEmpty(billing_money)) {
		  this.nomortgagecar_money=billing_money;//未押车金额
		 }
		 if (StringUtil.isNotEmpty(billing_money)&& StringUtil.isNotEmpty(mortgagecar_money)) {
				//开票金额   - 已压车金额   = 未押车金额
				double money = Double.parseDouble(billing_money)-Double.parseDouble(mortgagecar_money);
				this.nomortgagecar_money=String.valueOf(money);//未押车金额
		}
   }
	
	public void noMortgageCarMoneyForTen(){
		if (yycMoney!=null&&yycMoney >0) {
			this.nomortgagecar_money =String.valueOf(yycMoney);//未押车金额
			if(StringUtil.isNotEmpty(mortgagecar_money)){
				double money = yycMoney- Double.parseDouble(mortgagecar_money);
				this.nomortgagecar_money =String.valueOf(money);//未押车金额
			}
		}
		
		
	}
	
	public boolean noMortgageCarMoneyForThirty(){
		if (StringUtil.isNotEmpty(billing_money)) {
			double billingMoney=Double.parseDouble(billing_money);
		    if(yycMoney<billingMoney&&(yycMoney>=(billingMoney-50000))){
			   if(StringUtil.isNotEmpty(mortgagecar_money)) {
					//开票金额   - 已压车金额   = 未押车金额
					double money = billingMoney - Double.parseDouble(mortgagecar_money);
					nomortgagecar_money=String.valueOf(money);//未押车金额
				}
			    return true ;
			  
		   }
		  
		}
		 return false ;
	}
}

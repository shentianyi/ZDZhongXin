package com.zd.csms.messageAndWaring.message.quartz;
import com.zd.core.ServiceSupport;
import com.zd.csms.messageAndWaring.message.service.business.BusinessMessageTypeService;
/**
 * 业务部-信息提醒和预警定时任务
 *
 */
public class BusinessMessageAndWaring  extends ServiceSupport{
	//---------------------------------信息提醒--------------------------------
	/**
	 * 开票十个工作日未到车提醒
	 * @throws Exception 
	 */
	public void msgBillNoCar() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgBillNoCar() ;
	}
	
	
	/**
	 * 开票三十天未押满-提醒
	 * @throws Exception 
	 */       
	public void msgBillNoFull() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgBillNoFull() ;
	}
	
	/**
	 * 汇票到期前7天未清票提醒
	 * @throws Exception 
	 */
	public void msgBillNoDraft() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgBillNoDraft();
	}
	
	
	/**
	 * 零库存零汇票提醒 
	 * @throws Exception 
	 */
	public void msgZeroSkuDraft() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgZeroSkuDraft() ;
	}
	
	//DOTO 最后一笔业务提醒
	
	/**
	 * 连续三天无业务发生提醒
	 * @throws Exception 
	 */
	public void msgThreeDayNoBusiness() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgThreeDayNoBusiness() ;
	}
	
	
	/**
	 * 移动车辆超过25天未处理提醒
	 * @throws Exception 
	 */
	public void msgCarInfoTwentyFive() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgCarInfoTwentyFive();
	}
	
	
	/**
	 * 移动车辆超过总库存20%提醒
	 * @throws Exception 
	 */
	public void msgCarInfoTwenty() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgCarInfoTwenty();
	}
	
	
	/**
	 * 异常车辆超过总库存15%提醒
	 * @throws Exception 
	 */
	public void msgCarInfoFifteen() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.msgCarInfoFifteen();
	}
	
	//---------------------------------信息预警---------------------------------
	
	/**
	 * 开票15个工作日未到车预警
	 * @throws Exception
	 */
	public void billNoCarFifteen() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.billNoCarFifteen();
			
	}
	
	
	/**
	 * 开票四十五天未押满-预警
	 * @throws Exception 
	 */
	public void billFortyFiveNoFull() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.billFortyFiveNoFull();
		
	}
	
	
	/**
	 * 汇票到期当天未清票预警
	 * @throws Exception 
	 */
	public void billNoDraftNowDate() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.billNoDraftNowDate();
	 }
	
	
	/**
	 * 零库存零汇票延续7天预警 
	 * @throws Exception 
	 */
	public void zeroSkuDraft() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.zeroSkuDraft();
	}
	
	
	/**
	 * 连续五天无业务发生预警
	 * @throws Exception 
	 */
	//DOTO
	public void fiveDayNoBusiness() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.fiveDayNoBusiness();
	}
	
	/**
	 * 移动车辆超过30天未处理预警
	 * @throws Exception 
	 */
	public void carInfoThirty() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.carInfoThirty();
	}
	
	/**
	 * 异常车辆超过总库存20%预警
	 * @throws Exception 
	 */
	public void carInfoTwenty() throws Exception{
		BusinessMessageTypeService businservice=new BusinessMessageTypeService();
		businservice.carInfoTwenty();
	}
	
}

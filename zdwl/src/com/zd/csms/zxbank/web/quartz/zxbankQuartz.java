package com.zd.csms.zxbank.web.quartz;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zd.core.ServiceSupport;
import com.zd.core.SystemProperty;
import com.zd.csms.set.model.DistribsetQueryVO;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.service.AgreementService;
import com.zd.csms.zxbank.service.CustomerService;
import com.zd.csms.zxbank.service.FinancingService;
import com.zd.csms.zxbank.service.WareHouseService;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.GZipFileUtil;
import com.zd.csms.zxbank.util.ZhongXinBankUtil;
import com.zd.csms.zxbank.web.bean.AgreementFar;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.csms.zxbank.web.bean.FinancingFar;
import com.zd.csms.zxbank.web.bean.WarehouseFar;

public class zxbankQuartz extends ServiceSupport {

	private static final String url;
	private static final String filePath;
	private static final String userName;
	private static Log log = LogFactory.getLog(zxbankQuartz.class);
	
	
	static {
		filePath=SystemProperty.getPropertyValue("receivefiles.properties", "filePath");
		url = SystemProperty.getPropertyValue("bankdock.properties", "zx.url");
		userName=SystemProperty.getPropertyValue("bankdock.properties","zx.userName");
	}
	
	public void zxbankAuto() {
		DistribsetService ds = new DistribsetService();
		CustomerService cs = new CustomerService();
		/**
		 * 客户信息的自动同步
		 */
		List<DistribsetVO> dss = ds.searchDistribsetList(new DistribsetQueryVO());
		for (DistribsetVO distribsetVO : dss) {
			if (distribsetVO.getZxOrgCode() != null) {
				try {
					customerAuto(url, distribsetVO.getZxOrgCode());
					log.info("同步客户："+distribsetVO.getZxOrgCode());
				} catch (Exception e) {
					log.error("客户同步错误", e);
					break;
				}
			}
		}
		log.info("每日用户信息同步结束");

		/**
		 * 仓库信息同步
		 */
		
		List<String> ECIFcodes = cs.findAllByECIF();
		for (String ecifcode : ECIFcodes) {
		try {
			warehouseAuto(url, ecifcode);
			log.info("仓库信息客户号："+ecifcode);
		} catch (Exception e) {
			log.error("仓库信息同步错误", e);
			break;
		}
		}
		log.info("每日仓库信息同步结束");

		/**
			* 监管协议同步
			*/
		for (String ecifcode : ECIFcodes) {
		try {
			agreementAuto(url, ecifcode);
			log.info("监管协议客户号："+ecifcode);
		} catch (Exception e) {
			log.error("监管协议同步错误", e);
			break;
		}
		}
		log.info("每日监管协议同步结束");

		/**
		* 融资信息同步
		*/
		for (String ecifcode : ECIFcodes) {
		try {
			financingAuto(url, ecifcode);
			log.info("融资信息客户号："+ecifcode);
		} catch (Exception e) {
			log.error("融资信息同步错误", e);
			break;
		}
		}
		log.info("每日融资信息同步结束");
	}

	public void customerAuto(String url, String orgcode) throws Exception {
		CustomerService cs = new CustomerService();
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, "DLCDCMLQ",userName, orgcode);
		String requestXml = ZhongXinBankUtil.parseXml(body);
		log.info("客户同步发送报文："+requestXml);
		String xml = ZhongXinBankUtil.sendPost(url, requestXml);
		log.info("客户同步接收报文："+xml);
		if("".equals(xml)){
			log.info("请求连接超时");
		}else{
			List list = parseXML(xml, "", CustomerFar.class);
			cs.autoUpdateCust(list, new Customer(orgcode));
		}
		
	}

	public void warehouseAuto(String url, String ecifcode) throws Exception {
		WareHouseService whs = new WareHouseService();
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, "DLCDWMLQ",userName, ecifcode);
		String requestXml = ZhongXinBankUtil.parseXml(body);
		log.info("仓库同步发送报文："+requestXml);
		String xml = ZhongXinBankUtil.sendPost(url, requestXml);
		log.info("仓库同步接收报文："+xml);
		if("".equals(xml)){
			log.info("请求连接超时");
		}else{
		List list = parseXML(xml, "", WarehouseFar.class);
		whs.autoUpdateWare(list,ecifcode);
		}
	}

	public void agreementAuto(String url, String ecifcode) throws Exception {
		AgreementService as = new AgreementService();
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, "DLCDAGTQ",userName, ecifcode);
		String requestXml = ZhongXinBankUtil.parseXml(body);
		log.info("监管协议同步发送报文："+requestXml);
		String xml = ZhongXinBankUtil.sendPost(url, requestXml);
		log.info("监管协议同步接收报文："+xml);
		if("".equals(xml)){
			log.info("请求连接超时");
		}else{
		List list = parseXML(xml, "", AgreementFar.class);
		as.autoUpdateCust(list);
		}
	}

	public void financingAuto(String url, String ecifcode) throws Exception {
		FinancingService fs = new FinancingService();
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, "DLCDLMLQ",userName, ecifcode,
				DateUtil.getStringFormatByDate(new Date(), "yyyyMMhh"));
		String requestXml = ZhongXinBankUtil.parseXml(body);
		log.info("融资信息同步发送报文："+requestXml);
		String xml = ZhongXinBankUtil.sendPost(url, requestXml);
		log.info("融资信息同步接收报文："+xml);
		if("".equals(xml)){
			log.info("请求连接超时");
		}else{
		List list = parseXML(xml, "", FinancingFar.class);
		fs.addOrUpdate(list);
		}
	}

	/**
	 * 融资日终批量数据同步
	 */
	public void allFile(){
		log.info("日终批量文件同步开始");
		allFileStart(1);
		log.info("日终批量文件同步结束");
	}
	
	/**
	 * 客户确认书编号次数重置Method
	 */
	public void Confirmation(){
		CustomerService cs = new CustomerService();
		List<String> ECIFcodes = cs.findAllByECIF();
		for (String ecifcode : ECIFcodes) {
			System.out.println(ecifcode);
			Customer customer = cs.findByECIF(ecifcode);
			System.out.println(customer.toString());
			if(customer!=null){
				customer.setCusConnumber(1);	
				if(cs.update(customer)){
					log.info("已成功重置编号次数");
				}else{
					log.error("重置编号次数失败");
				}
			}
			
		}
	}
	
	public void allFileStart(int fileseq) {
		// DLCSDFSY LOAN JGXY
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("action", "DLCSDFSY");
		body.put("userName",userName);
		body.put("fileSeq", fileseq);
//		body.put("fileDate", DateUtil.getStringFormatByDate(new Date(), "yyyyMMdd"));
		body.put("fileDate","20210101");
		try {
			// LOAN
			body.put("fileName", "LOAN");
			String requestXml = ZhongXinBankUtil.parseXml(body);
			log.info("发送日终批量同步请求报文:["+requestXml+"]");
			String xml = ZhongXinBankUtil.sendPost(url, requestXml);
			log.info("接收到中信接口日终批量文件报文:["+xml+"]");
			//解析报文并返回数据字符串。csv格式数据。没有或者出错返回空
			List<List<String>> xmlList=new ArrayList<List<String>>();
			xmlList=fileLOAN(xml);
			FinancingService fs = new FinancingService();
			//调用相应的业务处理方法，传入数据内容
			if(xmlList!=null){
				List<FinancingFar> list=fs.ConversionList(xmlList);
				if(list.size()>0){
					fs.addOrUpdate(list);
				}else{
					log.error("解析错误，结果为空");
				}
			}
			// JGXY
			body.put("fileName", "JGXY");
			body.put("fileDate","20210301");
			requestXml = ZhongXinBankUtil.parseXml(body);
			log.info("发送日终批量同步请求报文:["+requestXml+"]");
			xml = ZhongXinBankUtil.sendPost(url, requestXml);
			log.info("接收到中信接口日终批量文件报文:["+xml+"]");
			List<List<String>> xmlListJ=new ArrayList<List<String>>();
			xmlListJ=fileLOAN(xml);
			AgreementService ags=new AgreementService();
			if(xmlList!=null){
				List<AgreementFar> listJ=ags.ConversionList(xmlListJ);
				if(listJ.size()>0){
					ags.autoUpdateCust(listJ);
				}else{
					log.error("解析错误，结果为空");
				}
			}
			
		} catch (Exception e) {
			log.error("日终批量同步错误：",e);
			e.printStackTrace();
		}
	}

	/**
	 * 解析报文xml 并返回数据内容字符串，逗号隔开。dat文件
	 * 没有或者出错返回空
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> fileLOAN(String xml) throws Exception {
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		if (!root.hasContent()) {
			return null;
		}
		String status = root.element("status").getText();// 交易状态
		String statusText=root.element("statusText").getText();//交易状态信息
		log.info("交易状态信息：["+statusText+"]--交易代码：["+status+"]");
		if (status.equals("AAAAAAA")) {
			//返回SCF端监管企业ECIF客户号+“_”+LOAN拼装的文件名称，例：600001614307_LOAN
			String filename = root.element("fileName").getText();//文件名称  
			String filesize = root.element("fileSize").getText();//文件大小  单位：字节;最大传输4M的文件
			String datacount = root.element("dataCount").getText();//数据条数
			String fileseq = root.element("fileSeq").getText();//文件序号
			String filecount = root.element("fileCount").getText();//文件总数
			String context = root.element("context").getText();//文件内容
			
			String fileName=filename+".gz";
			File file=new File(fileName);
			int num=Integer.parseInt(filecount);
			//据文件总数同步
			if (num> 1) {
				GZipFileUtil.reXMLturnGZIP(context, filePath, filename.subSequence(0,fileName.lastIndexOf("."))+"-"+fileseq+".gz");
				if (fileseq.equals(filecount)) {
					//文件完整性检测
//					Long Size=(long) (file.length()/1024f);检测大小完整性
					//
					System.out.println("同步完成");
					log.info("[同步文件校验完整合并压缩文件]");
					//分卷合并
					GZipFileUtil.merge(filePath, num, fileName);
					//合并压缩文件解压返回数据
					GZipFileUtil.unZip(filePath, fileName);
					fileName=filePath+file.separator+fileName;
					return GZipFileUtil.readDATFile(fileName);
				}
				allFileStart(Integer.parseInt(fileseq) + 1);
			}else{
				//单个文件直接接收保存后解压返回数据。
				try {
					GZipFileUtil.reXMLturnGZIP(context, filePath, fileName);
					log.info("日终批量文件同步成功");
				} catch (Exception e) {
					log.error("日终批量文件同步失败",e);
					e.printStackTrace();
				}
				try {
					GZipFileUtil.unZip(filePath, fileName);
					log.info("解压日终批量文件,生产dat文件");
				} catch (Exception e) {
					log.error("解压失败", e);
					e.printStackTrace();
				}
				fileName=filePath+file.separator+fileName.substring(0, fileName.lastIndexOf("."))+".dat";
				List<List<String>> list=null;
				try {
					list=GZipFileUtil.readDATFile(fileName);
					log.info("数据解析成功,解析为list");
				} catch (Exception e) {
					log.error("数据解析失败", e);
					e.printStackTrace();
				}
				/*StringBuffer str=new StringBuffer();
				for (List<String> list2 : list) {
					for (String string : list2) {
						str.append(string+"\t");
					}
					log.info("集合大小:"+list2.size());
					str.append("\n");
				}
				log.info("数据集合:"+str.toString());*/
				
				return list;
			}
		} else if (status.equals("DFE0001")) {
			log.error("未生成文件");
			return null;
		}
		return null;
	}

	/*//解析
	public List<List<String>> fileJGXY(String xml) throws Exception {
	}
	*/

	//远程查询报文解析
	public List<Object> parseXML(String xml, String listName, Class<?> clazz) throws Exception {
		List<Object> lists = new ArrayList<Object>();
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element bodyNode = doc.getRootElement();
		if (!bodyNode.hasContent()) {
			return lists;
		}
		String status = bodyNode.element("status").getText();// 交易状态
		String statusText = bodyNode.element("statusText").getText();// 交易状态信息
		if (status != null && status.equals("AAAAAAA")) {
			/*Element list = bodyNode.element(listName + "lst");*/
			Element list = bodyNode.element("list");
			List infos = list.elements("row");
			if (infos != null)
				for (Iterator it = infos.iterator(); it.hasNext();) {
					Element info = (Element) it.next();
					Object bean = ZhongXinBankUtil.getBean(clazz, info);
					lists.add(bean);
				}
		}
		return lists;
	}

	
}

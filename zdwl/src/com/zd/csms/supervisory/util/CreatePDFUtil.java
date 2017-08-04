package com.zd.csms.supervisory.util;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.queryBean.DraftQueryBean;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.ApprovalQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.repository.model.RepositoryJsonVO;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.contants.RegisteredStatusContants;
import com.zd.csms.supervisory.contants.SuperVisoryStatusContants;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.CreatePdfVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorEntity;
import com.zd.csms.supervisory.model.SupervisorFamilyVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.model.SupervisorWorkExperienceVO;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.AssetsTypeContant;
import com.zd.csms.supervisorymanagement.model.ElectronicTextVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.model.HandoverBookVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.OfficeEquipmentVO;
import com.zd.csms.supervisorymanagement.model.OtherDataVO;
import com.zd.csms.supervisorymanagement.model.PaperTextVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetsService;
import com.zd.csms.supervisorymanagement.service.HandoverLogService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.windcontrol.model.InspectionCommunionVO;
import com.zd.csms.windcontrol.model.InspectionInfoVO;
import com.zd.csms.windcontrol.model.InspectionItemVO;
import com.zd.csms.windcontrol.model.InspectionRecordVO;
import com.zd.csms.windcontrol.model.InspectionResultVO;
import com.zd.csms.windcontrol.model.InspectionSupervisorVO;
import com.zd.csms.windcontrol.querybean.InspectionReportQueryBean;
import com.zd.csms.windcontrol.service.InspectionService;
import com.zd.tools.StringUtil;
import com.zd.tools.file.FileUtil;

public class CreatePDFUtil {

	/**
	 * 监管确认书
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static CreatePdfVO jgqrs(int id, HttpServletRequest request) {

		try {

			CarOperationService coservice = new CarOperationService();
			SuperviseImportService sservice = new SuperviseImportService();

			UserVO user = UserSessionUtil.getUserSession(request).getUser();

			CarOperationVO covo = coservice.loadCarOperationById(id);

			List<SuperviseImportVO> sList = new ArrayList<SuperviseImportVO>();

			DraftService draftservice = new DraftService();
			DealerService ds = new DealerService();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// 经销商
			String distrib = "";

			String draftnum = "";
			String cars = covo.getCars();
			if (!StringUtil.isEmpty(cars)) {
				String[] idArray = cars.split(",");
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO svo = sservice
							.loadSuperviseImportById(Integer
									.parseInt(idArray[i]));
					sList.add(svo);
				}
			}
			if (sList != null && sList.size() > 0) {
				SuperviseImportVO sivo = sList.get(0);
				draftnum = sivo.getDraft_num();
				DraftQueryVO draftquery = new DraftQueryVO();
				draftquery.setDraft_num(draftnum);
				List<DraftVO> draftList = draftservice
						.searchDraftList(draftquery);
				if (draftList != null && draftList.size() > 0) {
					DraftVO draftvo = draftList.get(0);
					DealerVO dealer = ds.get(draftvo.getDistribid());
					if (dealer != null) {
						distrib = dealer.getDealerName();
					}

				}
			}

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("监管确认书", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("   ", level1));

			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);
			// 头部
			List<CarOperationVO> cList = coservice.todaycount(2, user.getId());
			int count = 0;
			if (cList != null && cList.size() > 0) {
				count = cList.size();
			}

			document.add(new Paragraph("	", basicFont));

			float[] widths1 = { 1f };
			PdfPTable basicInfoTable1 = new PdfPTable(widths1);
			basicInfoTable1.setTotalWidth(500);
			basicInfoTable1.setLockedWidth(true);

			PdfPCell basicCell1 = new PdfPCell();
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("编号：" + distrib
					+ formatter.format(new Date()) + count + 1, basicFont));
			basicInfoTable1.addCell(basicCell1);
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("票号：" + draftnum, basicFont));
			basicInfoTable1.addCell(basicCell1);
			document.add(basicInfoTable1);

			document.add(new Paragraph("	", headFont));

			document.add(new Paragraph("致：_______________和_______________：",
					basicFont));
			document.add(new Paragraph(
					"  根据三方协议签订和编号为______________的《动产监管合作协议》，现我司将共计____台监管车辆交付监管公司进行监管，特此证明！",
					basicFont));
			document.add(new Paragraph("具体明细如下：", basicFont));
			document.add(new Paragraph("  ", basicFont));

			// 创建基础信息表格
			float[] widths = { 0.1f, 0.1f, 0.1f, 0.2f, 0.2f, 0.1f, 0.1f, 0.1f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("票号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车辆型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("发动机号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("合格证", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("钥匙(数)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("单价(元)", basicFont));
			basicInfoTable.addCell(basicCell);

			if (sList != null && sList.size() > 0) {
				for (int i = 0; i < sList.size(); i++) {
					SuperviseImportVO svo = sList.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getDraft_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell
							.setPhrase(new Paragraph(svo.getMoney(), basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			basicCell.setColspan(8);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("合计：", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(3);
			basicCell.setBorder(0);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("甲方签章", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(2);
			basicCell.setBorder(0);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("乙方签章", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(3);
			basicCell.setBorder(0);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("丙方签章", basicFont));
			basicInfoTable.addCell(basicCell);

			document.add(basicInfoTable);

			for (int i = 0; i < 8; i++) {
				document.add(new Paragraph("  ", basicFont));
			}

			document.add(new Paragraph("备注：", basicFont));
			document.add(new Paragraph("1.本表单一式两份、甲方和乙方各一份", basicFont));
			document.add(new Paragraph("2.共    页，第    页", basicFont));
			// 关闭PDF文件流
			document.close();

			CreatePdfVO cvo = new CreatePdfVO();
			cvo.setUrl("/pdf/" + randomFilename + ".pdf");
			cvo.setName(distrib + "监管确认书" + formatter.format(new Date()));
			return cvo;

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	/**
	 * 车辆钥匙
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static CreatePdfVO clysjj(int id, HttpServletRequest request) {

		try {

			CarOperationService coservice = new CarOperationService();
			SuperviseImportService sservice = new SuperviseImportService();

			CarOperationVO covo = coservice.loadCarOperationById(id);

			List<SuperviseImportVO> sList = new ArrayList<SuperviseImportVO>();

			DraftService draftservice = new DraftService();
			DealerService ds = new DealerService();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// 经销商
			String distrib = "";

			String draftnum = "";

			String cars = covo.getCars();
			if (!StringUtil.isEmpty(cars)) {
				String[] idArray = cars.split(",");
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO svo = sservice
							.loadSuperviseImportById(Integer
									.parseInt(idArray[i]));
					sList.add(svo);
				}
			}

			if (sList != null && sList.size() > 0) {
				SuperviseImportVO sivo = sList.get(0);
				draftnum = sivo.getDraft_num();
				DraftQueryVO draftquery = new DraftQueryVO();
				draftquery.setDraft_num(draftnum);
				List<DraftVO> draftList = draftservice
						.searchDraftList(draftquery);
				if (draftList != null && draftList.size() > 0) {
					DraftVO draftvo = draftList.get(0);
					DealerVO dealer = ds.get(draftvo.getDistribid());
					if (dealer != null) {
						distrib = dealer.getDealerName();
					}

				}
			}

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(
					new Paragraph("监管车辆钥匙交接表", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("   ", level1));

			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);

			UserVO user = UserSessionUtil.getUserSession(request).getUser();
			List<CarOperationVO> cList = coservice.todaycount(2, user.getId());
			int count = 0;
			if (cList != null && cList.size() > 0) {
				count = cList.size();
			}

			float[] widths1 = { 1f };
			PdfPTable basicInfoTable1 = new PdfPTable(widths1);
			basicInfoTable1.setTotalWidth(500);
			basicInfoTable1.setLockedWidth(true);

			PdfPCell basicCell1 = new PdfPCell();
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("编号：" + distrib
					+ formatter.format(new Date()) + count + 1, basicFont));
			basicInfoTable1.addCell(basicCell1);
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("票号：" + draftnum, basicFont));
			basicInfoTable1.addCell(basicCell1);
			document.add(basicInfoTable1);

			// 创建基础信息表格
			float[] widths = { 0.1f, 0.2f, 0.3f, 0.1f, 0.3f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("钥匙(把)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("备注", basicFont));
			basicInfoTable.addCell(basicCell);

			if (sList != null && sList.size() > 0) {
				for (int i = 0; i < sList.size(); i++) {
					SuperviseImportVO svo = sList.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph("", basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			basicCell.setColspan(5);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("合计：", basicFont));
			basicInfoTable.addCell(basicCell);

			document.add(basicInfoTable);
			document.add(new Paragraph(
					"  以上监管车辆共计____台，对应钥匙共计____把，现由                                  公司(丙方)与____年__月__日交付中都物流有限公司进行监管。",
					basicFont));
			document.add(new Paragraph(
					"交付人：                                                                                                接收人：",
					basicFont));
			document.add(new Paragraph(
					"日期：                                                                                                    日期：",
					basicFont));
			// 关闭PDF文件流
			document.close();

			CreatePdfVO cvo = new CreatePdfVO();
			cvo.setUrl("/pdf/" + randomFilename + ".pdf");
			cvo.setName(distrib + "监管车辆钥匙交接表" + formatter.format(new Date()));

			return cvo;

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	public static String workHandover(int id, HttpServletRequest request) {
		try {

			HandoverLogService hlservice = new HandoverLogService();
			HandoverLogVO handoverLog = hlservice.load(id);
			if (handoverLog == null) {
				return null;
			}
			// 经销商
			DealerService dealerService = new DealerService();
			DealerQueryBean queryBean = new DealerQueryBean();
			DealerVO dealer = dealerService.get(handoverLog.getDealerId());
			if (dealer == null) {
				dealer = new DealerVO();
			}
			// 品牌
			BrandService brandService = new BrandService();
			BrandVO brand = brandService.loadBrandById(dealer.getBrandId());
			if (brand == null) {
				brand = new BrandVO();
			}
			// 合作银行
			BankVO bank = dealerService.getBankByDealerId(dealer.getId());
			if (bank == null) {
				bank = new BankVO();
			}
			// 交付人信息
			SupervisoryService supervisoryService = new SupervisoryService();
			SupervisorBaseInfoJsonVO deliverer = supervisoryService
					.getBaseInfoJson(handoverLog.getDeliverer());
			if (deliverer == null) {
				deliverer = new SupervisorBaseInfoJsonVO();
			}
			// 接收人信息
			SupervisorBaseInfoJsonVO receiver = supervisoryService
					.getBaseInfoJson(handoverLog.getReceiver());
			if (receiver == null) {
				receiver = new SupervisorBaseInfoJsonVO();
			}
			// 接收人培训情况
			RepositoryService repositoryService = new RepositoryService();
			RepositoryJsonVO receiverRepository = repositoryService
					.loadRepositoryJsonBySupervisorId(handoverLog.getReceiver());
			if (receiverRepository == null) {
				receiverRepository = new RepositoryJsonVO();
			}

			// 汇票
			DraftService dservice = new DraftService();
			DraftQueryVO dquery = new DraftQueryVO();
			dquery.setDistribid(dealer.getId());
			List<DraftVO> dList = dservice.searchDraftList(dquery);
			if (dList == null) {
				dList = new ArrayList<DraftVO>();
			}

			// 在库车辆
			SuperviseImportService superviseImportService = new SuperviseImportService();
			SuperviseImportQueryVO siQuery = new SuperviseImportQueryVO();
			/*
			 * List<SuperviseImportVO> zkcars=new
			 * ArrayList<SuperviseImportVO>(); if(dList!=null &&
			 * dList.size()>0){ for(DraftVO dvo:dList){ siQuery.setState(2);
			 * siQuery.setDraft_num(dvo.getDraft_num()); zkcars.addAll(
			 * siService.searchSuperviseImportList(siQuery)); } }
			 */
			// 在库车辆
			double zkMoney = 0;
			List<SuperviseImportVO> zkcars = superviseImportService
					.findListByDealerIdAndState(handoverLog.getDealerId(),
							SuperVisoryStatusContants.IN_STORE.getCode());// 入库
			String zkMoneyStr = "";
			if (zkcars != null) {
				for (SuperviseImportVO zkcl : zkcars) {
					if (zkcl != null) {
						zkMoney = sum(zkMoney,
								Double.parseDouble(zkcl.getMoney()));
					}
				}
				BigDecimal db = new BigDecimal(Double.toString(zkMoney));
				zkMoneyStr = db.toPlainString();
			}

			// 在途车辆
			double ztMoney = 0;
			String ztMoneyStr = "";
			List<SuperviseImportVO> ztcars = superviseImportService
					.findListByDealerIdAndState(handoverLog.getDealerId(),
							SuperVisoryStatusContants.ON_WAY.getCode());// 在途
			if (ztcars != null) {
				for (SuperviseImportVO ztcl : ztcars) {
					if (ztcl != null) {
						ztMoney = sum(ztMoney,
								Double.parseDouble(ztcl.getMoney()));
					}
				}
				BigDecimal bd = new BigDecimal(Double.toString(ztMoney));
				ztMoneyStr = bd.toPlainString();
			}
			// 在途车辆
			/*
			 * List<SuperviseImportVO> ztcars=new
			 * ArrayList<SuperviseImportVO>(); if(dList!=null &&
			 * dList.size()>0){ for(DraftVO dvo:dList){ siQuery.setState(1);
			 * siQuery.setDraft_num(dvo.getDraft_num()); ztcars.addAll(
			 * siService.searchSuperviseImportList(siQuery)); } }
			 */

			// 销售未还款车辆/私自售卖
			List<SuperviseImportVO> xswhkcars = new ArrayList<SuperviseImportVO>();
			if (dList != null && dList.size() > 0) {
				for (DraftVO dvo : dList) {
					siQuery.setState(3);
					siQuery.setDraft_num(dvo.getDraft_num());
					List<SuperviseImportVO> whkcars = superviseImportService
							.searchSuperviseImportList(siQuery);
					for (SuperviseImportVO svo : whkcars) {
						if (StringUtil.isEmpty(svo.getPayment_amount())) {
							xswhkcars.add(svo);
						}
					}
				}
			}
			// 私自售卖
			List<SuperviseImportVO> privateSellCars = new ArrayList<SuperviseImportVO>();
			if (dList != null && dList.size() > 0) {
				for (DraftVO dvo : dList) {
					siQuery.setState(5);
					siQuery.setDraft_num(dvo.getDraft_num());
					List<SuperviseImportVO> whkcars = superviseImportService
							.searchSuperviseImportList(siQuery);
					for (SuperviseImportVO svo : whkcars) {
						if (StringUtil.isEmpty(svo.getPayment_amount())) {
							privateSellCars.add(svo);
						}
					}
				}
			}
			// 私自移动
			List<SuperviseImportVO> privateMoveCars = new ArrayList<SuperviseImportVO>();
			if (dList != null && dList.size() > 0) {
				for (DraftVO dvo : dList) {
					siQuery.setState(6);
					siQuery.setDraft_num(dvo.getDraft_num());
					List<SuperviseImportVO> whkcars = superviseImportService
							.searchSuperviseImportList(siQuery);
					for (SuperviseImportVO svo : whkcars) {
						if (StringUtil.isEmpty(svo.getPayment_amount())) {
							privateMoveCars.add(svo);
						}
					}
				}
			}
			// 未结清汇票
			List<DraftQueryBean> wjqdrafts = new ArrayList<DraftQueryBean>();
			double billing_money = 0;
			// 回款金额
			double payment_money = 0;
			// 已押车金额
			double mortgagecar_money = 0;
			if (dList != null && dList.size() > 0) {
				for (DraftVO draftvo : dList) {
					
//					DraftQueryBean dvo = (DraftQueryBean) draftvo;
					DraftQueryBean dvo = new DraftQueryBean();
					dvo.setId(draftvo.getId());
					dvo.setAgreement(draftvo.getAgreement());
					dvo.setBail_num(draftvo.getBail_num());
					dvo.setDistribid(draftvo.getDistribid());
					dvo.setFinancial_institution(draftvo.getFinancial_institution());
					dvo.setBrand(draftvo.getBrand());
					dvo.setDraft_num(draftvo.getDraft_num());
					dvo.setBilling_date(draftvo.getBilling_date());
					dvo.setDue_date(draftvo.getDue_date());
					dvo.setBilling_money(draftvo.getBilling_money());
					dvo.setMortgagecar_money(draftvo.getMortgagecar_money());
					dvo.setPayment_money(draftvo.getPayment_money());
					dvo.setState(draftvo.getState());
					dvo.setCreateuserid(draftvo.getCreateuserid());
					dvo.setCreatedate(draftvo.getCreatedate());
					dvo.setUpduserid(draftvo.getUpduserid());
					dvo.setUpddate(draftvo.getUpddate());
					dvo.setBailscale(draftvo.getBailscale());
					
					
					if (dvo.getBilling_money() != null) {
						billing_money = Double.parseDouble(dvo
								.getBilling_money());
					}

					// 合计已押车金额和回款金额
					siQuery.setDraft_num(dvo.getDraft_num());
					List<SuperviseImportVO> wjqdraftscars = superviseImportService
							.searchSuperviseImportList(siQuery);
					if (wjqdraftscars != null && wjqdraftscars.size() > 0) {
						for (SuperviseImportVO svo : wjqdraftscars) {
							if (svo != null) {
								if (svo.getPayment_amount() != null) {
									payment_money += Double.parseDouble(svo
											.getPayment_amount());
								}
								if (svo.getMoney() != null) {
									mortgagecar_money += Double.parseDouble(svo
											.getMoney());
								}
							}
						}
						dvo.setPayment_money(String.valueOf(payment_money));
						dvo.setMortgagecar_money(String
								.valueOf(mortgagecar_money));

						// 未押车金额
						dvo.setUn_mortgagecar_money(String
								.valueOf(billing_money - mortgagecar_money));
					}
					if (dvo.getPayment_money() == null) {
						dvo.setPayment_money("0");
					}
					if (dvo.getMortgagecar_money() == null) {
						dvo.setMortgagecar_money("0");
					}
					if (dvo.getUn_mortgagecar_money() == null) {
						dvo.setUn_mortgagecar_money("0");
					}
					if ((billing_money - payment_money) > 0) {
						wjqdrafts.add(dvo);
					}
				}
			}

			FixedAssetsService fixedAssetsService = new FixedAssetsService();
			FixedAssetsQueryVO query = new FixedAssetsQueryVO();
			query.setSendee(handoverLog.getDeliverer());
			query.setAsset_type(AssetsTypeContant.ELECTRONICS.getCode());
			// 电脑
			List<FixedAssetsVO> computers = fixedAssetsService
					.searchFixedAssets(query);
			FixedAssetsVO dn = null;
			if (computers != null && computers.size() > 0) {
				dn = computers.get(0);
			}
			// 保险柜
			query.setAsset_type(AssetsTypeContant.WORK.getCode());
			List<FixedAssetsVO> safetyBox = fixedAssetsService
					.searchFixedAssets(query);
			FixedAssetsVO bxg = null;
			if (safetyBox != null && safetyBox.size() > 0) {
				bxg = safetyBox.get(0);
			}
			HandoverBookVO book = hlservice
					.getHandoverBookBySupervisorId(handoverLog.getDeliverer());
			ElectronicTextVO eText = book.geteText();
			OfficeEquipmentVO officeEquipment = book.getOfficeEquipment();
			OtherDataVO OData = book.getOData();
			PaperTextVO pText = book.getpText();

			// 交接时间
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String jjsj = DateUtil.getStringFormatByDate(
					handoverLog.getHandoverDate(), "yyyy年MM月dd日");

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("监管员工作交接书", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("   ", level1));

			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);

			float[] widths = { 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.125f,
					0.125f, 0.125f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			// 第一行
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("店名", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell
					.setPhrase(new Paragraph(dealer.getDealerName(), basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("品牌", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(brand.getName() + "", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("合作银行", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(bank.getBankName(), basicFont));
			basicInfoTable.addCell(basicCell);
			
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("交付人确认", basicFont));
			basicInfoTable.addCell(basicCell);
			
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("", basicFont));
			basicInfoTable.addCell(basicCell);

			// 第二行
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("交付人", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(deliverer.getName(), basicFont));
			basicInfoTable.addCell(basicCell);
			
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("工号", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(deliverer.getStaffNo(), basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("联系方式", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(deliverer.getSelfTelephone(),
					basicFont));
			basicInfoTable.addCell(basicCell);
			
			

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("交接时间", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(jjsj, basicFont));
			basicInfoTable.addCell(basicCell);

			// 第三行
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("交付原因", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(7);
			basicCell.setRowspan(1);
			// 交接性质：1：辞职，2：辞退，3：正常轮岗，4：投诉轮岗
			//□轮岗     □停岗     □监管公司交接     □暂停合作     □辞职     □暂代     □上调    □拆绑
		/*	if (handoverLog.getHandoverNature() == 1) {
			basicCell.setPhrase(new Paragraph(
				//		" □ 正常轮岗    □ 辞退    □ 投诉轮岗       ■ 辞职    ", basicFont));
				" □ 轮岗     □ 停岗     □ 监管公司交接     □ 暂停合作      ■ 辞职     □ 暂代     □ 上调    □ 拆绑    ", basicFont));
			} else if (handoverLog.getHandoverNature() == 2) {
				basicCell.setPhrase(new Paragraph(
						//" □ 正常轮岗     □ 投诉轮岗       □ 辞职    ■ 辞退 ", basicFont));
						" □ 轮岗     □ 停岗     □ 监管公司交接     □ 暂停合作     □ 辞职     □ 暂代     □ 上调    □ 拆绑 ", basicFont));
			} else if (handoverLog.getHandoverNature() == 3) {
				basicCell.setPhrase(new Paragraph(
//						"  ■ 正常轮岗     □ 投诉轮岗       □ 辞职   □ 辞退", basicFont));
				"  ■ 轮岗     □ 停岗     □ 监管公司交接     □ 暂停合作     □ 辞职     □ 暂代     □ 上调    □ 拆绑", basicFont));
			} else if (handoverLog.getHandoverNature() == 4) {
				basicCell.setPhrase(new Paragraph(
		//				" □ 正常轮岗     ■ 投诉轮岗       □ 辞职   □ 辞退", basicFont));
						" □ 轮岗     □ 停岗     □ 监管公司交接     □ 暂停合作     □ 辞职     □ 暂代     □ 上调    □ 拆绑", basicFont));
			} else {
				basicCell.setPhrase(new Paragraph(
					//	" □ 正常轮岗     □ 投诉轮岗       □ 辞职   □ 辞退", basicFont));
						" □ 轮岗     □ 停岗     □ 监管公司交接     □ 暂停合作     □ 辞职     □ 暂代     □ 上调    □ 拆绑", basicFont));
			}*/
			basicCell.setPhrase(new Paragraph(
								" □ 轮岗     □ 停岗     □ 监管公司交接     □ 暂停合作     □ 辞职     □ 暂代     □ 上调    □ 拆绑", basicFont));
			basicInfoTable.addCell(basicCell);

			// 第四行
			basicCell.setColspan(8);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("接收人信息", basicFont));
			basicInfoTable.addCell(basicCell);

			// 第五行
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("接收人", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(receiver.getName(), basicFont));
			basicInfoTable.addCell(basicCell);

		/*	basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("联系方式", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(receiver.getSelfTelephone(),
					basicFont));
			basicInfoTable.addCell(basicCell);*/

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("学历", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(
					receiver.getEducationBackground(), basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("性别", basicFont));
			basicInfoTable.addCell(basicCell);
			
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(receiver.getGender(), basicFont));
			basicInfoTable.addCell(basicCell);
			
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("接受人确认", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("", basicFont));
			basicInfoTable.addCell(basicCell);

			// 第六行
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("性质", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(4);
			basicCell.setRowspan(1);
/*			// 接收性质：1：轮岗，2：新入职，3：二次上岗
			if (handoverLog.getReceiveNature() == 1) {
				basicCell.setPhrase(new Paragraph(
						//" ■ 轮岗     □ 新入职      □ 二次上岗  ", basicFont));
						" □ 新入职   □ 暂代    ■ 轮岗    □ 二次上岗   □ 拆绑  ", basicFont));
			} else if (handoverLog.getReceiveNature() == 2) {
				basicCell.setPhrase(new Paragraph(
//						" □ 轮岗     ■ 新入职      □ 二次上岗  ", basicFont));
				" ■ 新入职   □ 暂代    □ 轮岗    □ 二次上岗   □ 拆绑  ", basicFont));
			} else if (handoverLog.getReceiveNature() == 3) {
				basicCell.setPhrase(new Paragraph(
						//" □ 轮岗     □ 新入职      ■ 二次上岗  ", basicFont));
						" □ 新入职   □ 暂代    □ 轮岗    ■ 二次上岗   □ 拆绑  ", basicFont));
			} else {*/
				basicCell.setPhrase(new Paragraph(
						" □ 新入职   □ 暂代    □ 轮岗    □ 二次上岗   □ 拆绑  ", basicFont));
			/*}*/
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("身份证号", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(2);
			basicCell.setRowspan(1);
			basicCell
					.setPhrase(new Paragraph(receiver.getIdNumber(), basicFont));
			basicInfoTable.addCell(basicCell);

			// 第七行
			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("监管员属性", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(2);
			basicCell.setRowspan(1);
			if (handoverLog.getAfterHandoverNature() == 1) {
				basicCell.setPhrase(new Paragraph(" ■ 属地    □ 外派", basicFont));
			} else if (handoverLog.getAfterHandoverNature() == 2) {
				basicCell.setPhrase(new Paragraph(" □ 属地    ■ 外派", basicFont));
			} else {
				basicCell.setPhrase(new Paragraph(" □ 属地    □ 外派", basicFont));
			}

			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("推荐人", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(receiver.getRecommenderName(),
					basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph("接收时间", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(2);
			basicCell.setRowspan(1);
			basicCell.setPhrase(new Paragraph(jjsj, basicFont));
			basicInfoTable.addCell(basicCell);

			// 第八行
			basicCell.setColspan(1);
			basicCell.setPhrase(new Paragraph("监管员籍贯", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(3);
			basicCell.setPhrase(new Paragraph(receiver.getNativePlace(),
					basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setPhrase(new Paragraph("招聘地点", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(3);
			basicCell.setPhrase(new Paragraph(receiver.getRecommendChannel(),
					basicFont));
			basicInfoTable.addCell(basicCell);
			RepositoryService service = new RepositoryService();

			RepositoryVO bean = service
					.loadRepositoryBySupervisorId(handoverLog.getReceiver());
			// 第九行
			basicCell.setColspan(1);
			basicCell.setPhrase(new Paragraph("岗前是否进店培训", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(2);
			RepositoryTrainVO train = null;
			if (bean != null) {
				train = service.loadRepositoryTrainByRepositoryId(bean.getId());
				if (train != null) {

					basicCell
							.setPhrase(new Paragraph(" ■ 是    □ 否", basicFont));
				} else {

					basicCell
							.setPhrase(new Paragraph(" □ 是    ■ 否", basicFont));
				}
			} else {
				basicCell.setPhrase(new Paragraph(" □ 是    ■ 否", basicFont));
			}

			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setPhrase(new Paragraph("培训银行业务", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(2);
			if (train != null) {
				basicCell.setPhrase(new Paragraph(train.getTrainingContent(),
						basicFont));
			} else {
				basicCell.setPhrase(new Paragraph(" ", basicFont));
			}
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setPhrase(new Paragraph("考核人员姓名", basicFont));
			basicInfoTable.addCell(basicCell);

			basicCell.setColspan(1);
			basicCell.setPhrase(new Paragraph(receiver.getInterviewee(),
					basicFont));
			basicInfoTable.addCell(basicCell);

			document.add(basicInfoTable);

			// 在库车辆
			float[] width1 = { 0.09f, 0.09f, 0.09f, 0.09f, 0.09f, 0.09f, 0.09f,
					0.09f, 0.09f, 0.09f, 0.1f };
			PdfPTable zkclTable = new PdfPTable(width1);
			zkclTable.setTotalWidth(500);
			zkclTable.setLockedWidth(true);

			PdfPCell zkclCell = new PdfPCell();

			// 第一行
			zkclCell.setColspan(11);
			zkclCell.setRowspan(1);
			zkclCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			zkclCell.setPhrase(new Paragraph("一、业务内容交接", level2));
			zkclTable.addCell(zkclCell);

			// 第二行
			zkclCell.setColspan(11);
			zkclCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			zkclCell.setPhrase(new Paragraph("1.在库、移动车辆", basicFont));
			zkclTable.addCell(zkclCell);

			// 第三行
			zkclCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("序号", basicFont));
			zkclTable.addCell(zkclCell);
		/*	zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("票号", basicFont));
			zkclTable.addCell(zkclCell);*/
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("车辆型号", basicFont));
			zkclTable.addCell(zkclCell);
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("颜色", basicFont));
			zkclTable.addCell(zkclCell);
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("发动机号", basicFont));
			zkclTable.addCell(zkclCell);
			zkclCell.setColspan(2);
			zkclCell.setPhrase(new Paragraph("车架号", basicFont));
			zkclTable.addCell(zkclCell);
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("合格证号", basicFont));
			zkclTable.addCell(zkclCell);
		/*	zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("金额", basicFont));
			zkclTable.addCell(zkclCell);*/
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("钥匙号", basicFont));
			zkclTable.addCell(zkclCell);
	
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("钥匙数量(把)", basicFont));
			zkclTable.addCell(zkclCell);
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("价格", basicFont));
			zkclTable.addCell(zkclCell);
			zkclCell.setColspan(1);
			zkclCell.setPhrase(new Paragraph("车辆状态", basicFont));
			zkclTable.addCell(zkclCell);
			if (zkcars != null && zkcars.size() > 0) {
				for (int i = 0; i < zkcars.size(); i++) {
					SuperviseImportVO svo = zkcars.get(i);
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					zkclTable.addCell(zkclCell);
			/*		zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getDraft_num(),
							basicFont));
					zkclTable.addCell(zkclCell);*/
					
					//车辆型号
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					zkclTable.addCell(zkclCell);
					
					//颜色
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getColor(), basicFont));
					zkclTable.addCell(zkclCell);
					
					//发动机号
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					zkclTable.addCell(zkclCell);
					
					
					//车架号
					zkclCell.setColspan(2);
					zkclCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					zkclTable.addCell(zkclCell);
					
					//合格证号
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					zkclTable.addCell(zkclCell);
					
					//钥匙号
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getKey_num(),
							basicFont));
					zkclTable.addCell(zkclCell);
					
					//钥匙数量
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					zkclTable.addCell(zkclCell);
					
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph(svo.getMoney(), basicFont));
					zkclTable.addCell(zkclCell);
					
					zkclCell.setColspan(1);
					zkclCell.setPhrase(new Paragraph("在库", basicFont));
					zkclTable.addCell(zkclCell);
				}
			}
			// 1导入2在库3出库4移动5:私自售卖，6：私自移动
			zkclCell.setColspan(11);
			zkclCell.setPhrase(new Paragraph(
				/*	"        金额合计    "
							+ (zkMoneyStr == "" ? 0 : zkMoneyStr)*/
					"        数量合计    "
							+ "                                                             共"
							+ (zkcars == null ? 0 : zkcars.size()) + "台车辆",
					basicFont));
			zkclTable.addCell(zkclCell);
			document.add(zkclTable);

			// 在途车辆
		/*	float[] width2 = { 0.11f, 0.11f, 0.11f, 0.11f, 0.11f, 0.11f, 0.11f,
					0.11f, 0.12f };*/
			PdfPTable ztclTable = new PdfPTable(width1);
			ztclTable.setTotalWidth(500);
			ztclTable.setLockedWidth(true);

			PdfPCell ztclCell = new PdfPCell();

			// 第一行
			ztclCell.setColspan(11);
			ztclCell.setRowspan(1);
			ztclCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			ztclCell.setPhrase(new Paragraph("2.在途车辆", basicFont));
			ztclTable.addCell(ztclCell);
//序号	车辆型号	颜色	车架号	发动机号	合格证号	钥匙号	钥匙数量（把）	价格	车辆状态

			ztclCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("序号", basicFont));
			ztclTable.addCell(ztclCell);
			
/*			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("票号", basicFont));
			ztclTable.addCell(ztclCell);*/
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("车辆型号", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("颜色", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(2);
			ztclCell.setPhrase(new Paragraph("车架号", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("发动机号", basicFont));
			ztclTable.addCell(ztclCell);
			
			
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("合格证号", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("钥匙号", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("钥匙数量(把)", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("价格", basicFont));
			ztclTable.addCell(ztclCell);
			
			ztclCell.setColspan(1);
			ztclCell.setPhrase(new Paragraph("车辆状态", basicFont));
			ztclTable.addCell(ztclCell);
			if (ztcars != null && ztcars.size() > 0) {
				for (int i = 0; i < ztcars.size(); i++) {
					SuperviseImportVO svo = ztcars.get(i);
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					ztclTable.addCell(ztclCell);
					
				/*	ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getDraft_num(),
							basicFont));
					ztclTable.addCell(ztclCell);*/
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					ztclTable.addCell(ztclCell);
					
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getColor(), basicFont));
					ztclTable.addCell(ztclCell);
					
					ztclCell.setColspan(2);
					ztclCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					ztclTable.addCell(ztclCell);
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getEngine_num(), basicFont));
					ztclTable.addCell(ztclCell);
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					ztclTable.addCell(ztclCell);
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getKey_num(),
							basicFont));
					ztclTable.addCell(ztclCell);
				
				
			
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					ztclTable.addCell(ztclCell);
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph(svo.getMoney(), basicFont));
					ztclTable.addCell(ztclCell);
					
					
					ztclCell.setColspan(1);
					ztclCell.setPhrase(new Paragraph("在途 ", basicFont));
					ztclTable.addCell(ztclCell);
				}
			}
			ztclCell.setColspan(11);
			ztclCell.setPhrase(new Paragraph(
						"        数量合计                                                                共" + (ztcars == null ? 0 : ztcars.size()) + "台车辆",
					basicFont));
			ztclTable.addCell(ztclCell);
			document.add(ztclTable);

			// 销售未还款车辆
/*			float[] width3 = { 0.11f, 0.11f, 0.11f, 0.11f, 0.11f, 0.11f, 0.11f,
					0.11f, 0.12f };
*/			PdfPTable xswhkTable = new PdfPTable(width1);
			xswhkTable.setTotalWidth(500);
			xswhkTable.setLockedWidth(true);

			PdfPCell xswhkCell = new PdfPCell();

			// 第一行
			xswhkCell.setColspan(11);
			xswhkCell.setRowspan(1);
			xswhkCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			xswhkCell.setPhrase(new Paragraph("3.销售未还款车辆", basicFont));
			xswhkTable.addCell(xswhkCell);

			
			
			xswhkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("序号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
/*			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("票号", basicFont));
			xswhkTable.addCell(xswhkCell);*/
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("车辆型号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("颜色", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(2);
			xswhkCell.setPhrase(new Paragraph("车架号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("发动机号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("合格证号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("钥匙号", basicFont));
			xswhkTable.addCell(xswhkCell);
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("钥匙数量(把)", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("价格", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("车辆状态", basicFont));
			xswhkTable.addCell(xswhkCell);
//序号	车辆型号	颜色	车架号	发动机号	合格证号	钥匙号	钥匙数量（把）	价格	车辆状态
			if (xswhkcars != null && xswhkcars.size() > 0) {
				for (int i = 0; i < xswhkcars.size(); i++) {
					SuperviseImportVO svo = xswhkcars.get(i);
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					xswhkTable.addCell(xswhkCell);
		/*			xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getDraft_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);*/
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					xswhkCell.setColspan(1);
					xswhkCell.setColspan(1);
					xswhkCell
							.setPhrase(new Paragraph(svo.getColor(), basicFont));
					xswhkTable.addCell(xswhkCell);
					xswhkCell.setColspan(2);
					xswhkCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getKey_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
				
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getMoney(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph("销售未还款 ", basicFont));
					xswhkTable.addCell(xswhkCell);
				}
			}

			// 第一行
			xswhkCell.setColspan(11);
			xswhkCell.setRowspan(1);
			xswhkCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			xswhkCell.setPhrase(new Paragraph("4.私自售卖车辆", basicFont));
			xswhkTable.addCell(xswhkCell);


			xswhkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("序号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
/*			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("票号", basicFont));
			xswhkTable.addCell(xswhkCell);*/
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("车辆型号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("颜色", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(2);
			xswhkCell.setPhrase(new Paragraph("车架号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("发动机号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("合格证号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("钥匙号", basicFont));
			xswhkTable.addCell(xswhkCell);
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("钥匙数量(把)", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("价格", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("车辆状态", basicFont));
			xswhkTable.addCell(xswhkCell);

			if (privateSellCars != null && privateSellCars.size() > 0) {
				for (int i = 0; i < privateSellCars.size(); i++) {
					SuperviseImportVO svo = privateSellCars.get(i);
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					xswhkTable.addCell(xswhkCell);
		/*			xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getDraft_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);*/
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					xswhkCell.setColspan(1);
					xswhkCell.setColspan(1);
					xswhkCell
							.setPhrase(new Paragraph(svo.getColor(), basicFont));
					xswhkTable.addCell(xswhkCell);
					xswhkCell.setColspan(2);
					xswhkCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getKey_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
				
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getMoney(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(" 私售 ", basicFont));
					xswhkTable.addCell(xswhkCell);
				}
			}

			// 第一行
			xswhkCell.setColspan(11);
			xswhkCell.setRowspan(1);
			xswhkCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			xswhkCell.setPhrase(new Paragraph("5.私自移动车辆", basicFont));
			xswhkTable.addCell(xswhkCell);


			xswhkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("序号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
/*			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("票号", basicFont));
			xswhkTable.addCell(xswhkCell);*/
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("车辆型号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("颜色", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(2);
			xswhkCell.setPhrase(new Paragraph("车架号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("发动机号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("合格证号", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("钥匙号", basicFont));
			xswhkTable.addCell(xswhkCell);
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("钥匙数量(把)", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("价格", basicFont));
			xswhkTable.addCell(xswhkCell);
			
			xswhkCell.setColspan(1);
			xswhkCell.setPhrase(new Paragraph("车辆状态", basicFont));
			xswhkTable.addCell(xswhkCell);


			if (privateMoveCars != null && privateMoveCars.size() > 0) {
				for (int i = 0; i < privateMoveCars.size(); i++) {
					SuperviseImportVO svo = privateMoveCars.get(i);
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					xswhkTable.addCell(xswhkCell);
		/*			xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getDraft_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);*/
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					xswhkCell.setColspan(1);
					xswhkCell.setColspan(1);
					xswhkCell
							.setPhrase(new Paragraph(svo.getColor(), basicFont));
					xswhkTable.addCell(xswhkCell);
					xswhkCell.setColspan(2);
					xswhkCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getKey_num(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
				
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(svo.getMoney(),
							basicFont));
					xswhkTable.addCell(xswhkCell);
					
					xswhkCell.setColspan(1);
					xswhkCell.setPhrase(new Paragraph(" 私移 ", 
							basicFont));
					xswhkTable.addCell(xswhkCell);
				}
			}

			document.add(xswhkTable);

			// 电子质文本资料
			float[] width4 = { 0.166f, 0.166f, 0.166f, 0.166f, 0.166f, 0.17f };
			PdfPTable wbzlTable = new PdfPTable(width4);
			wbzlTable.setTotalWidth(500);
			wbzlTable.setLockedWidth(true);

			PdfPCell wbzlCell = new PdfPCell();

			// 第一行
			wbzlCell.setColspan(6);
			wbzlCell.setRowspan(1);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setPhrase(new Paragraph("6.电子文本资料", basicFont));
			wbzlTable.addCell(wbzlCell);

			/*
			 * wbzlCell.setColspan(1); wbzlCell.setPhrase(new Paragraph("系统用户名",
			 * basicFont)); wbzlTable.addCell(wbzlCell); wbzlCell.setColspan(2);
			 * wbzlCell.setPhrase(new Paragraph(eText.get, basicFont));
			 * wbzlTable.addCell(wbzlCell); wbzlCell.setColspan(1);
			 * wbzlCell.setPhrase(new Paragraph("系统密码", basicFont));
			 * wbzlTable.addCell(wbzlCell); wbzlCell.setColspan(2);
			 * wbzlCell.setPhrase(new Paragraph(" ", basicFont));
			 * wbzlTable.addCell(wbzlCell);
			 */

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("电子台账：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph(eText.getElectronBill(), basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("周库存核对清单：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共  "
					+ eText.getWeekBillAmount()
					+ " 份，时间：自   "
					+ DateUtil.getStringFormatByDate(
							eText.getWeekBillStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							eText.getWeekBillEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("接车明细：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共  "
					+ eText.getReceiveCarDetailAmount()
					+ "  份，时间：自 "
					+ DateUtil.getStringFormatByDate(
							eText.getReceiveCarDetailStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							eText.getReceiveCarDetailEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("月库存核对清单：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共  "
					+ eText.getMonthBillAmount()
					+ " 份，时间：自  "
					+ DateUtil.getStringFormatByDate(
							eText.getMonthBillStartTime(), "yyyy-MM-dd")
					+ " 至 "
					+ DateUtil.getStringFormatByDate(
							eText.getMonthBillEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("申领释放书：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ eText.getApplyFreeBookAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(
							eText.getApplyFreeBookStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							eText.getApplyFreeBookEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("监管确认书：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ eText.getConfirmationAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(
							eText.getConfirmationStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							eText.getConfirmationEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("二网申请：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ eText.getTowApplyAmount()
					+ " 份，时间：自  "
					+ DateUtil.getStringFormatByDate(
							eText.getTowApplyStartTime(), "yyyy-MM-dd")
					+ " 至   "
					+ DateUtil.getStringFormatByDate(
							eText.getTowApplyEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setColspan(3);
			wbzlCell.setPhrase(new Paragraph(" ", basicFont));
			wbzlTable.addCell(wbzlCell);
		/*	 wbzlCell.setColspan(2);
			 wbzlCell.setPhrase(new
			 Paragraph(" ",
//					 Paragraph("共 "+eText.get+" 份，时间：自                至                ",
			 basicFont));
			 wbzlTable.addCell(wbzlCell);*/

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("其它：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ eText.getOtherAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(eText.getOtherStartTime(),
							"yyyy-MM-dd")
					+ " 至 "
					+ DateUtil.getStringFormatByDate(eText.getOtherEndTime(),
							"yyyy-MM-dd"), basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setColspan(1);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setPhrase(new Paragraph("备注：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph(eText.getRemark(), basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setColspan(6);
			wbzlCell.setRowspan(1);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setPhrase(new Paragraph("7.纸 质 文 本 资 料", basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("手工台账：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ pText.getPaperBillAmount()
					+ " 份，时间：自  "
					+ DateUtil.getStringFormatByDate(
							pText.getPaperBillStartTime(), "yyyy-MM-dd")
					+ " 至   "
					+ DateUtil.getStringFormatByDate(
							pText.getPaperBillEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("二网申请：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ pText.getTowApplyAmount()
					+ " 份，时间：自  "
					+ DateUtil.getStringFormatByDate(
							pText.getTowApplyStartTime(), "yyyy-MM-dd")
					+ " 至   "
					+ DateUtil.getStringFormatByDate(
							pText.getTowApplyEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("换证申请：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ pText.getApplyFreeBookAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(
							pText.getChangeApplyStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							pText.getConfirmationEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("监管确认书：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ pText.getConfirmationAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(
							pText.getConfirmationStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							pText.getConfirmationEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("申领释放书：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ pText.getApplyFreeBookAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(
							pText.getApplyFreeBookStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							pText.getApplyFreeBookEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("授权书(店方)：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			if (pText.getAuthorization() == 1) {
				wbzlCell.setPhrase(new Paragraph(" ■ 有    □ 无", basicFont));
			} else if (pText.getAuthorization() == 1) {
				wbzlCell.setPhrase(new Paragraph(" □ 有    ■ 无", basicFont));
			} else {
				wbzlCell.setPhrase(new Paragraph(" □ 有    □ 无", basicFont));
			}
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("周库存核对清单：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph(new Paragraph("共  "
					+ pText.getWeekBillAmount()
					+ " 份，时间：自   "
					+ DateUtil.getStringFormatByDate(
							pText.getWeekBillStartTime(), "yyyy-MM-dd")
					+ " 至  "
					+ DateUtil.getStringFormatByDate(
							pText.getWeekBillEndTime(), "yyyy-MM-dd"),
					basicFont)));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("经销商告知函：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			if (pText.getInformLetter() == 1) {
				wbzlCell.setPhrase(new Paragraph(" ■ 有    □ 无", basicFont));
			} else if (pText.getInformLetter() == 2) {
				wbzlCell.setPhrase(new Paragraph(" □ 有    ■ 无", basicFont));
			} else {
				wbzlCell.setPhrase(new Paragraph(" □ 有    □ 无", basicFont));
			}
			wbzlTable.addCell(wbzlCell);

			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("月库存核对清单：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共  "
					+ pText.getMonthBillAmount()
					+ " 份，时间：自  "
					+ DateUtil.getStringFormatByDate(
							pText.getMonthBillStartTime(), "yyyy-MM-dd")
					+ " 至 "
					+ DateUtil.getStringFormatByDate(
							pText.getMonthBillEndTime(), "yyyy-MM-dd"),
					basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("合格证、车钥匙：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			if (pText.getKeyAmount() == 1) {
				wbzlCell.setPhrase(new Paragraph(" 数量： ■ 相符    □ 不相符",
						basicFont));
			} else if (pText.getKeyAmount() == 2) {
				wbzlCell.setPhrase(new Paragraph(" 数量： □ 相符    ■ 不相符",
						basicFont));
			} else {
				wbzlCell.setPhrase(new Paragraph(" 数量： □ 相符    □ 不相符",
						basicFont));
			}
			wbzlTable.addCell(wbzlCell);
			
		
			
			
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setColspan(1);
			wbzlCell.setPhrase(new Paragraph("其它：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph("共 "
					+ pText.getOtherAmount()
					+ " 份，时间：自 "
					+ DateUtil.getStringFormatByDate(pText.getOtherStartTime(),
							"yyyy-MM-dd")
					+ " 至 "
					+ DateUtil.getStringFormatByDate(pText.getOtherEndTime(),
							"yyyy-MM-dd"), basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setColspan(1);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			wbzlCell.setPhrase(new Paragraph("备注：", basicFont));
			wbzlTable.addCell(wbzlCell);
			wbzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			wbzlCell.setColspan(2);
			wbzlCell.setPhrase(new Paragraph(pText.getRemark(), basicFont));
			wbzlTable.addCell(wbzlCell);

			document.add(wbzlTable);

			// 其他资料交接
			float[] width5 = { 0.14f, 0.14f, 0.14f, 0.14f, 0.14f, 0.14f, 0.16f };
			PdfPTable qtzlTable = new PdfPTable(width5);
			qtzlTable.setTotalWidth(500);
			qtzlTable.setLockedWidth(true);

			PdfPCell qtzlCell = new PdfPCell();

			// 第一行
			qtzlCell.setColspan(7);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			qtzlCell.setPhrase(new Paragraph("8.其它资料交接", basicFont));
			qtzlTable.addCell(qtzlCell);

			int wjq = wjqdrafts.size();
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(wjq + 1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			qtzlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			qtzlCell.setPhrase(new Paragraph("未结清汇票信息", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("票号", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("开票日期", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("到期日期", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("票面金额", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("押车金额", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("未押车金额", basicFont));
			qtzlTable.addCell(qtzlCell);

			if (wjqdrafts != null && wjqdrafts.size() > 0) {
				for (DraftQueryBean dvo : wjqdrafts) {
					qtzlCell.setColspan(1);
					qtzlCell.setRowspan(1);
					qtzlCell.setPhrase(new Paragraph(dvo.getDraft_num(),
							basicFont));
					qtzlTable.addCell(qtzlCell);
					qtzlCell.setColspan(1);
					qtzlCell.setRowspan(1);
					qtzlCell.setPhrase(new Paragraph(DateUtil
							.getStringFormatByDate(dvo.getBilling_date(),
									"yyyy-MM-dd"), basicFont));
					qtzlTable.addCell(qtzlCell);
					qtzlCell.setColspan(1);
					qtzlCell.setRowspan(1);
					qtzlCell.setPhrase(new Paragraph(DateUtil
							.getStringFormatByDate(dvo.getDue_date(),
									"yyyy-MM-dd"), basicFont));
					qtzlTable.addCell(qtzlCell);
					qtzlCell.setColspan(1);
					qtzlCell.setRowspan(1);
					qtzlCell.setPhrase(new Paragraph(dvo.getBilling_money(),
							basicFont));
					qtzlTable.addCell(qtzlCell);
					qtzlCell.setColspan(1);
					qtzlCell.setRowspan(1);
					qtzlCell.setPhrase(new Paragraph(
							dvo.getMortgagecar_money(), basicFont));
					qtzlTable.addCell(qtzlCell);
					qtzlCell.setColspan(1);
					qtzlCell.setRowspan(1);
					qtzlCell.setPhrase(new Paragraph(dvo
							.getUn_mortgagecar_money(), basicFont));
					qtzlTable.addCell(qtzlCell);
				}
			}
			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("情况说明", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("情况说明", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(OData.getSituationExplain(),
					basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph("特殊操作说明", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(OData.getSpecialOperation(),
					basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(6);
			qtzlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			qtzlCell.setPhrase(new Paragraph("文档交接", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			qtzlCell.setPhrase(new Paragraph("附件标题(共      页)", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			qtzlCell.setPhrase(new Paragraph(
					"交付人(签字)：                                               接收人(签字)：",
					basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			qtzlCell.setPhrase(new Paragraph(
					"交接监交人部门：                             岗位：                                 监交人(签字)：",
					basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(1);
			qtzlCell.setRowspan(6);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			qtzlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			qtzlCell.setPhrase(new Paragraph("银行审核", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			qtzlCell.setPhrase(new Paragraph("意见：", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			qtzlCell.setPhrase(new Paragraph("审核人(签字):", basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			qtzlCell.setPhrase(new Paragraph("        年           月         日",
					basicFont));
			qtzlTable.addCell(qtzlCell);
			qtzlCell.setColspan(6);
			qtzlCell.setRowspan(1);
			qtzlCell.setPhrase(new Paragraph(" ", basicFont));
			qtzlTable.addCell(qtzlCell);

			document.add(qtzlTable);

			// 其他资料交接
			float[] width6 = { 0.2f, 0.15f, 0.15f, 0.15f,0.15f, 0.2f };
			PdfPTable bgsbTable = new PdfPTable(width6);
			bgsbTable.setTotalWidth(500);
			bgsbTable.setLockedWidth(true);

			PdfPCell bgsbCell = new PdfPCell();

			// 第一行
			bgsbCell.setColspan(6);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("二、办公设备及用品交接", level2));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(1);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("项目", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("1.本公司交接内容", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(1);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("交付人签字", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setColspan(1);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("接收人签字", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(6);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			bgsbCell.setPhrase(new Paragraph("笔记本电脑", basicFont));
			bgsbTable.addCell(bgsbCell);

			if (dn != null) {
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph(
						"品牌：" + dn.getBrand() == null ? "" : dn.getBrand(),
						basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph(
						"型号：" + dn.getModel() == null ? "" : dn.getModel() ,
						basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph(
						"资产编号：" + dn.getAsset_num() == null ? "" : dn
								.getAsset_num(), basicFont));
				bgsbTable.addCell(bgsbCell);
			} else {
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("品牌：", basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("型号：", basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("资产编号：", basicFont));
				bgsbTable.addCell(bgsbCell);
			}
			
			bgsbCell.setColspan(1);
			bgsbCell.setRowspan(6);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);
		
			bgsbCell.setColspan(1);
			bgsbCell.setRowspan(6);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);

			
			String cpr = officeEquipment.getComputerPropertyReason();
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getComputerProperty() == 1) {
				bgsbCell.setPhrase(new Paragraph("使用性能：■ 正常    □ 不正常  ____________ ",
						basicFont));
			} else if (officeEquipment.getComputerProperty() == 2) {
				bgsbCell.setPhrase(new Paragraph("使用性能：□ 正常    ■ 不正常   "
						+ (cpr == null ? " " : cpr), basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("使用性能：□ 正常    □ 不正常  ____________ ",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			String oep = officeEquipment.getComputerPassword();
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getComputerOnPassword() == 1) {
				bgsbCell.setPhrase(new Paragraph("开机密码：■  告知   "
						+ (oep == null ? "" : oep ) + " □ 未告知   □ 无", basicFont));
			} else if (officeEquipment.getComputerOnPassword() == 2) {
				bgsbCell.setPhrase(new Paragraph("开机密码：□ 告知    ■ 未告知   □ 无",
						basicFont));
			} else if (officeEquipment.getComputerProperty() == 3) {
				bgsbCell.setPhrase(new Paragraph("开机密码：□ 告知    □ 未告知   ■ 无",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("开机密码：□ 告知    □ 未告知  □ 无",
						basicFont));
			}
			
			bgsbTable.addCell(bgsbCell);
			

			/*
			 * bgsbCell.setColspan(3); bgsbCell.setRowspan(1);
			 * bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			 * bgsbCell.setPhrase(new
			 * Paragraph("电脑特殊情况说明："+officeEquipment.getc, basicFont));
			 * bgsbTable.addCell(bgsbCell);
			 */
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getComputerAppearance() == 1) {
				bgsbCell.setPhrase(new Paragraph("外观：■ 有损    □ 无损  ", basicFont));
			} else if (officeEquipment.getComputerAppearance() == 2) {
				bgsbCell.setPhrase(new Paragraph("外观：□ 有损    ■ 无损   ",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("外观：□ 有损    □ 无损   ",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getMouseProperty() == 1) {
				bgsbCell.setPhrase(new Paragraph("鼠标使用性能：■ 正常    □ 不正常   ",
						basicFont));
			} else if (officeEquipment.getMouseProperty() == 2) {
				bgsbCell.setPhrase(new Paragraph("鼠标使用性能：□ 正常    ■  不正常   ",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("鼠标使用性能：□ 正常    □ 不正常   ",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getCameraProperty() == 1) {
				bgsbCell.setPhrase(new Paragraph("摄像头使用性能：■ 正常    □ 不正常   ",
						basicFont));
			} else if (officeEquipment.getCameraProperty() == 2) {
				bgsbCell.setPhrase(new Paragraph("摄像头使用性能：□ 正常    ■ 不正常   ",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("摄像头使用性能：□ 正常    □ 不正常   ",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);
			// 保险柜
			bgsbCell.setRowspan(6);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			bgsbCell.setPhrase(new Paragraph("保险柜", basicFont));
			bgsbTable.addCell(bgsbCell);

			if (bxg != null) {
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("品牌：" + bxg.getBrand(),
						basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("型号：" + bxg.getModel(),
						basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("资产编号：" + bxg.getAsset_num(),
						basicFont));
				bgsbTable.addCell(bgsbCell);
			} else {
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("品牌：", basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("型号：", basicFont));
				bgsbTable.addCell(bgsbCell);
				bgsbCell.setRowspan(1);
				bgsbCell.setColspan(1);
				bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bgsbCell.setPhrase(new Paragraph("资产编号：", basicFont));
				bgsbTable.addCell(bgsbCell);
			}
			
			bgsbCell.setRowspan(6);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setRowspan(6);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);

			String bxgyy = officeEquipment.getSafetyBoxPropertyReason();
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getSafetyBoxProperty() == 1) {
				bgsbCell.setPhrase(new Paragraph("使用性能：■ 正常    □ 不正常",
						basicFont));
			} else if (officeEquipment.getSafetyBoxProperty() == 2) {
				bgsbCell.setPhrase(new Paragraph("使用性能： □ 正常    ■ 不正常  "
						+ (bxgyy == null ? " " : bxgyy), basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("使用性能：□ 正常    □ 不正常",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			String cumputerpassword = officeEquipment.getComputerPassword();
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getSafetyBoxPasswordStatus() == 1) {
				bgsbCell.setPhrase(new Paragraph("密码：■  告知   "
						+ (cumputerpassword == null ? " " : cumputerpassword)
						+ " □ 未告知   □ 无", basicFont));
			} else if (officeEquipment.getSafetyBoxPasswordStatus() == 2) {
				bgsbCell.setPhrase(new Paragraph("密码：□ 告知    ■ 未告知   □ 无",
						basicFont));
			} else if (officeEquipment.getSafetyBoxPasswordStatus() == 3) {
				bgsbCell.setPhrase(new Paragraph("密码：□ 告知    □ 未告知   ■ 无",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("密码：□ 告知    □ 未告知  □ 无",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getSafetyBoxAppearance() == 1) {
				bgsbCell.setPhrase(new Paragraph("外观：■ 有损    □ 无损  ", basicFont));
			} else if (officeEquipment.getSafetyBoxAppearance() == 2) {
				bgsbCell.setPhrase(new Paragraph("外观：□ 有损    ■ 无损   ",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("外观：□ 有损    □ 无损   ",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getKeyAmount() == 1) {
				bgsbCell.setPhrase(new Paragraph("钥匙(1套两把)：■ 1套  □ 2套  ",
						basicFont));
			} else if (officeEquipment.getKeyAmount() == 2) {
				bgsbCell.setPhrase(new Paragraph("钥匙(1套两把)：□ 1套   ■ 2套  ",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("钥匙(1套两把)：□ 1套  □ 2套  ",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			String otherEx = officeEquipment.getSituationExplain();
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			bgsbCell.setPhrase(new Paragraph("其他情况说明："
					+ (otherEx == null ? " " : otherEx), basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("耳麦", basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getHeadsetProperty() == 1) {
				bgsbCell.setPhrase(new Paragraph("使用性能：■ 正常    □ 不正常",
						basicFont));
			} else if (officeEquipment.getHeadsetProperty() == 2) {
				bgsbCell.setPhrase(new Paragraph("使用性能： □ 正常    ■ 不正常  ",
						basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("使用性能：□ 正常    □ 不正常",
						basicFont));
			}
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);


			/*
			 * bgsbCell.setRowspan(1); bgsbCell.setColspan(1);
			 * bgsbCell.setPhrase(new Paragraph("摄像头", basicFont));
			 * bgsbTable.addCell(bgsbCell); bgsbCell.setColspan(3);
			 * bgsbCell.setRowspan(1);
			 * bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * bgsbCell.setPhrase(new Paragraph("使用性能：□ 正常    □ 不正常",
			 * basicFont)); bgsbTable.addCell(bgsbCell); bgsbCell.setRowspan(1);
			 * bgsbCell.setColspan(1); bgsbCell.setPhrase(new Paragraph(" ",
			 * basicFont)); bgsbTable.addCell(bgsbCell);
			 */

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph("监管员工牌", basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getHaveCard() == 1) {
				bgsbCell.setPhrase(new Paragraph("□ 无    ■ 有", basicFont));
			} else if (officeEquipment.getHaveCard() == 2) {
				bgsbCell.setPhrase(new Paragraph("■ 无    □ 有", basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("□ 无    □ 有", basicFont));
			}
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);

			String qqNumber = officeEquipment.getQqNumber();
			String qqPassword = officeEquipment.getQqPassword();
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph("工作QQ", basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("号码：  "
					+ (qqNumber == null ? " " : qqNumber) + " 密码：    "
					+ (qqPassword == null ? " " : qqPassword), basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			bgsbCell.setColspan(6);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			bgsbCell.setPhrase(new Paragraph("2.关系解除确认", basicFont));
			bgsbTable.addCell(bgsbCell);
			
			// TODO
			
			bgsbCell.setColspan(6);
			bgsbCell.setRowspan(3);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			bgsbCell.setPhrase(new Paragraph("乙方因 个人 原因提出离职，现甲方同意于          年     月     日与乙方解除劳动关系，基于劳动关系项下无任何争议。\n"
                                           +"                                      \t                    \t                \t                 \t" +
                                           "                  \t                                            签字:", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setColspan(6);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			bgsbCell.setPhrase(new Paragraph("3.店方相关交接", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("借用物品", basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getBorrowGoods() == 1) {
				bgsbCell.setPhrase(new Paragraph("□ 无    ■ 有", basicFont));
			} else if (officeEquipment.getBorrowGoods() == 2) {
				bgsbCell.setPhrase(new Paragraph("■ 无    □ 有", basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("□ 无    □ 有", basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			//TODO
			bgsbCell.setRowspan(3);
			bgsbCell.setColspan(2);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_TOP);
			bgsbCell.setPhrase(new Paragraph("店方负责人签字(盖公章)", basicFont));
			bgsbTable.addCell(bgsbCell);

			String monerRemark = officeEquipment.getMoneyRemark();
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("钱款", basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (officeEquipment.getMoneyStatus() == 1) {
				bgsbCell.setPhrase(new Paragraph("□ 无欠款    ■ 有欠款："
						+ (monerRemark == null ? " " : monerRemark), basicFont));
			} else if (officeEquipment.getMoneyStatus() == 2) {
				bgsbCell.setPhrase(new Paragraph("■ 无欠款    □ 有欠款：", basicFont));
			} else {
				bgsbCell.setPhrase(new Paragraph("□ 无欠款   □ 有欠款：", basicFont));
			}
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("其它", basicFont));
			bgsbTable.addCell(bgsbCell);
			bgsbCell.setColspan(3);
			bgsbCell.setRowspan(1);
			bgsbCell.setPhrase(new Paragraph(" ", basicFont));
			bgsbTable.addCell(bgsbCell);

			// 第一行
			bgsbCell.setColspan(6);
			bgsbCell.setRowspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setPhrase(new Paragraph("三、物流金融中心审核", level2));
			bgsbTable.addCell(bgsbCell);
			// 查询审核意见
			MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(id);
			approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERLOG
					.getCode());
			List<ApprovalQueryBean> approval = approvalService
					.findListByApprovalType(approvalQuery);
			Map<String, String> appMap = new HashMap<String, String>();
			if (approval != null && approval.size() > 0) {
				for (ApprovalQueryBean b : approval) {
					appMap.put(b.getRoleName(), b.getRemark());
				}
			}
			// 业务部审核
			bgsbCell.setRowspan(2);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			bgsbCell.setPhrase(new Paragraph("业务部审核", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(5);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String ywbzy = appMap.get("业务部专员");
			String ywbzyyj = "意见："
					+ (ywbzy == null ? " " : ywbzy)
					+ "\n\n\n业务专员审核（签字）:                                      年         月         日                             ";
			bgsbCell.setPhrase(new Paragraph(ywbzyyj, basicFont));
			bgsbCell.setBorderColorBottom(BaseColor.WHITE);
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(5);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String ywbjl = appMap.get("业务部经理");
			String ywbjlyj = "意见："
					+ (ywbjl == null ? " " : ywbjl)
					+ "\n\n\n业务部经理审核（签字）:                                      年         月         日                             ";
			bgsbCell.setPhrase(new Paragraph(ywbjlyj, basicFont));
			bgsbCell.setBorderColorBottom(BaseColor.WHITE);
			bgsbTable.addCell(bgsbCell);

			// 监管员管理部审核
			bgsbCell.setRowspan(2);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			bgsbCell.setPhrase(new Paragraph("监管员管理部审核", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(5);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String zhzy = appMap.get("监管员管理部综合专员");
			String zhzyyj = "意见："
					+ (zhzy == null ? " " : zhzy)
					+ "\n\n\n监管员管理部综合专员审核（签字）:                                      年         月         日                             ";
			bgsbCell.setPhrase(new Paragraph(zhzyyj, basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(5);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String jgyjl = appMap.get("监管员管理部经理");
			String jgyjlyj = "意见："
					+ (jgyjl == null ? " " : jgyjl)
					+ "\n\n\n监管员管理部经理审核（签字）:                                      年         月         日                             ";
			bgsbCell.setPhrase(new Paragraph(jgyjlyj, basicFont));
			bgsbCell.setBorderColorBottom(BaseColor.WHITE);
			bgsbTable.addCell(bgsbCell);

			// 运营管理部审核
			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(1);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bgsbCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			bgsbCell.setPhrase(new Paragraph("运营管理部审核", basicFont));
			bgsbTable.addCell(bgsbCell);

			bgsbCell.setRowspan(1);
			bgsbCell.setColspan(5);
			bgsbCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String yyglbbz = appMap.get("运营管理部部长");
			String yyglbbzyj = "意见："
					+ (yyglbbz == null ? " " : yyglbbz)
					+ "\n\n\n运营管理部部长审核（签字）:                                      年         月         日                             ";
			bgsbCell.setPhrase(new Paragraph(yyglbbzyj, basicFont));
			bgsbTable.addCell(bgsbCell);

			document.add(bgsbTable);

			// 关闭PDF文件流
			document.close();
			return "/pdf/" + randomFilename + ".pdf";

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}


	/**
	 * 本地日查库
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static String ben(int id, HttpServletRequest request) {

		try {

			SuperviseImportService service = new SuperviseImportService();
			SuperviseImportQueryVO siQuery = new SuperviseImportQueryVO();

			UserService us = new UserService();
			DealerService ds = new DealerService();

			UserVO user = us.loadUserById(id);

			// 经销商
			String distrib = "";
			// 金融机构
			String financial_institution = "";

			if (user != null) {
				int userid = user.getClient_id();
				DealerSupervisorService dsservice = new DealerSupervisorService();
				DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
				dsquery.setSupervisorId(userid);
				List<DealerSupervisorVO> dsList = dsservice
						.searchDealerSupervisorList(dsquery);
				if (dsList != null && dsList.size() > 0) {
					int dealerId = dsList.get(0).getDealerId();
					DraftService draftService = new DraftService();
					DraftQueryVO dquery = new DraftQueryVO();
					dquery.setDistribid(dealerId);
					List<DraftVO> dList = draftService.searchDraftList(dquery);
					if (dList != null && dList.size() > 0) {
						DraftVO draft = dList.get(0);
						int distribid = draft.getDistribid();
						DealerVO dealer = ds.get(distribid);
						distrib = dealer.getDealerName();
						financial_institution = ds.getBankNameByDealerId(dealer
								.getId());
					}
				}
			}

			siQuery.setType(1);
			siQuery.setUserid(user.getId());
			// 在库
			siQuery.setState(2);
			// 按条件查询分页数据
			List<SuperviseImportVO> list = service
					.searchSuperviseImportList(siQuery);

			// 检查时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String jjsj = formatter.format(new Date());

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("质押监管日盘点表", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("经销商：" + distrib, level3));
			document.add(new Paragraph("金融机构：" + financial_institution, level3));
			document.add(new Paragraph("日期：" + jjsj, level3));
			document.add(new Paragraph(" "));
			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);

			// 创建基础信息表格
			float[] widths = { 0.1f, 0.2f, 0.2f, 0.2f, 0.2f, 0.1f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("检查时间(上午)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("检查时间(下午)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("备注", basicFont));
			basicInfoTable.addCell(basicCell);

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SuperviseImportVO svo = list.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setColspan(1);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setColspan(1);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setColspan(1);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			document.add(basicInfoTable);
			document.add(new Paragraph(
					"监管员确认：                                                                                         经销商授权人确认：",
					basicFont));
			// 关闭PDF文件流
			document.close();
			return "/pdf/" + randomFilename + ".pdf";

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	/**
	 * 二网日查库
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static String two(int id, HttpServletRequest request) {

		try {

			SuperviseImportService service = new SuperviseImportService();
			SuperviseImportQueryVO siQuery = new SuperviseImportQueryVO();

			UserService us = new UserService();
			DealerService ds = new DealerService();

			UserVO user = us.loadUserById(id);

			// 经销商
			String distrib = "";
			// 金融机构
			String financial_institution = "";

			if (user != null) {
				int userid = user.getId();
				DealerSupervisorService dsservice = new DealerSupervisorService();
				DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
				dsquery.setSupervisorId(user.getClient_id());
				List<DealerSupervisorVO> dsList = dsservice
						.searchDealerSupervisorList(dsquery);
				if (dsList != null && dsList.size() > 0) {
					int dealerId = dsList.get(0).getDealerId();
					DraftService draftService = new DraftService();
					DraftQueryVO dquery = new DraftQueryVO();
					dquery.setDistribid(dealerId);
					List<DraftVO> dList = draftService.searchDraftList(dquery);
					if (dList != null && dList.size() > 0) {
						DraftVO draft = dList.get(0);
						int distribid = draft.getDistribid();
						DealerVO dealer = ds.get(distribid);
						distrib = dealer.getDealerName();
						financial_institution = ds.getBankNameByDealerId(dealer
								.getId());
					}
				}
			}
			siQuery.setType(1);
			siQuery.setUserid(user.getId());
			// 移动
			siQuery.setState(4);
			// 按条件查询分页数据
			List<SuperviseImportVO> list = service
					.searchSuperviseImportList(siQuery);

			// 检查时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String jjsj = formatter.format(new Date());

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("二网日盘点表", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("经销商：" + distrib, level3));
			document.add(new Paragraph("金融机构：" + financial_institution, level3));
			document.add(new Paragraph("日期：" + jjsj, level3));
			document.add(new Paragraph(" "));
			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);

			// 创建基础信息表格
			float[] widths = { 0.1f, 0.2f, 0.2f, 0.3f, 0.1f, 0.1f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("二网地址", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("状态", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("备注", basicFont));
			basicInfoTable.addCell(basicCell);

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SuperviseImportVO svo = list.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getTwo_name(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			document.add(basicInfoTable);
			document.add(new Paragraph(
					"监管员确认：                                                                                         经销商授权人确认：",
					basicFont));
			// 关闭PDF文件流
			document.close();
			return "/pdf/" + randomFilename + ".pdf";

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	public static String supervisorInfo(int id, HttpServletRequest request) {

		try {
			SupervisoryService service = new SupervisoryService();
			SupervisorEntity supervisor = service.loadSupervisorInfo(id);
			if (supervisor != null) {
				SupervisorBaseInfoVO superVisorBaseInfo = supervisor
						.getSuperVisorBaseInfo();
				if (superVisorBaseInfo == null) {
					superVisorBaseInfo = new SupervisorBaseInfoVO();
				}
				List<SupervisorEducationVO> supervisoryEducation = supervisor
						.getSupervisoryEducation();

				List<SupervisorFamilyVO> supervisoryFamily = supervisor
						.getSupervisoryFamily();
				SupervisorRecommendVO supervisoryRecommend = supervisor
						.getSupervisoryRecommend();
				if (supervisoryRecommend == null) {
					supervisoryRecommend = new SupervisorRecommendVO();
				}
				List<SupervisorWorkExperienceVO> supervisoryWorkExperience = supervisor
						.getSupervisoryWorkExperience();

				// 生成随机数
				Random rand = new Random();
				int random = rand.nextInt();

				Calendar calCurrent = Calendar.getInstance();
				int intDay = calCurrent.get(Calendar.DATE);
				int intMonth = calCurrent.get(Calendar.MONTH) + 1;
				int intYear = calCurrent.get(Calendar.YEAR);
				int intTime = calCurrent.getTime().getHours()
						+ calCurrent.getTime().getMinutes()
						+ calCurrent.getTime().getSeconds();
				String now = String.valueOf(intYear) + "_"
						+ String.valueOf(intMonth) + "_"
						+ String.valueOf(intDay) + "_" + intTime;

				// 根据日期和随机数生成文件名
				String randomFilename = now
						+ String.valueOf(random > 0 ? random : (-1) * random);

				String url = request.getSession().getServletContext()
						.getRealPath("/")
						+ "pdf/" + randomFilename + ".pdf";

				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(url));
				document.open();

				BaseFont bf = BaseFont.createFont("STSong-Light",
						"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				// 一级标题字体
				Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255,
						255, 255, 255));
				// 二级标题字体
				Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255,
						255, 255, 255));
				// 二级标题字体
				Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255,
						255, 255, 255));

				// 标题
				PdfPTable titleTable = new PdfPTable(1);
				titleTable.setTotalWidth(500);
				titleTable.setLockedWidth(true);

				PdfPCell titleCell = new PdfPCell(new Paragraph("监管员基本信息登记表",
						level1));
				titleCell.setBorder(0);
				titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
				titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				titleTable.addCell(titleCell);

				document.add(titleTable);
				document.add(new Paragraph("   ", level1));

				Font headFont = new Font(bf, 8, Font.NORMAL);
				Font basicFont = new Font(bf, 9, Font.NORMAL);

				float[] widths = { 0.142f, 0.142f, 0.142f, 0.142f, 0.142f,
						0.142f, 0.148f };
				PdfPTable basicInfoTable = new PdfPTable(widths);
				basicInfoTable.setTotalWidth(500);
				basicInfoTable.setLockedWidth(true);

				PdfPCell basicCell = new PdfPCell();
				// 第一行
				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell
						.setPhrase(new Paragraph(
								"请提供您的真实信息，我们将对您的信息进行全面核实，由于信息不实所带来的一切后果将由应试人员承担。（注：*为必填项）",
								basicFont));
				basicInfoTable.addCell(basicCell);

				// 第二行
				basicCell.setColspan(2);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("应聘岗位：", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo.getJob(),
						basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(" ", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("入职时间:", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(2);
				basicCell.setRowspan(1);
				if (superVisorBaseInfo.getEntryTime() != null) {
					basicCell.setPhrase(new Paragraph(DateUtil
							.getStringFormatByDate(
									superVisorBaseInfo.getEntryTime(),
									"yyyy-MM-dd"), basicFont));
				} else {
					basicCell.setPhrase(new Paragraph("", basicFont));
				}
				basicInfoTable.addCell(basicCell);

				// 第三行
				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph("一、基本资料", basicFont));
				basicInfoTable.addCell(basicCell);

				// 第四行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("*姓名", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo.getName(),
						basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("*性别", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getGender(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("*出生日期", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell
						.setPhrase(new Paragraph(DateUtil
								.getStringFormatByDate(
										superVisorBaseInfo.getBirthday(),
										"yyyy-MM-dd"), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(5);
				/* basicCell.setHorizontalAlignment(Element.ALIGN_CENTER); */
				basicCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				basicCell.setPhrase(new Paragraph("*照片（一寸免冠）", basicFont));
				basicInfoTable.addCell(basicCell);

				// 第五行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*身份证号", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getIdNumber(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*民族", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getNation(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*学历", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getEducationBackground(), basicFont));
				basicInfoTable.addCell(basicCell);

				// 第三行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*籍贯", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getNativePlace(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*政治面貌", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getPoliticsStatus(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*户口性质", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(
						RegisteredStatusContants
								.getNameByCode(superVisorBaseInfo
										.getRegisteredStatus()), basicFont));
				basicInfoTable.addCell(basicCell);

				// 第四行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*本人联系电话", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getSelfTelephone(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("家庭电话", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getHomeTelephone(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*婚育状况", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getFertilityState(), basicFont));
				basicInfoTable.addCell(basicCell);

				// 第四行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*户口所在地", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(5);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getRegisteredAddress(), basicFont));
				basicInfoTable.addCell(basicCell);

				// 第五行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*现居住详细地址", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(6);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getLiveAddress(), basicFont));
				basicInfoTable.addCell(basicCell);

				// 第六行
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*紧急状况联系人", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getEmergencyContactor(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*联系电话", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getEmergencyContactNumber(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("*与紧急状况联系人关系", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(2);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo
						.getEmergencyContactRelationship(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph("二、*教育状况", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("起", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("止", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("学校名称", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("主修专业", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("获得证书", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("学历", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(" ", basicFont));
				basicInfoTable.addCell(basicCell);

				if (supervisoryEducation != null
						&& supervisoryEducation.size() > 0) {
					for (SupervisorEducationVO education : supervisoryEducation) {

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph((DateUtil
								.getStringFormatByDate(
										education.getEducationStartTime(),
										"yyyy-MM-dd")), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph((DateUtil
								.getStringFormatByDate(
										education.getEducationEndTime(),
										"yyyy-MM-dd")), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(education
								.getSchoolName(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(education.getMajor(),
								basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(education
								.getCertificate(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(
								education.getDegree(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(" ", basicFont));
						basicInfoTable.addCell(basicCell);
					}
				}

				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph("三、*工作经历", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("起", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("止", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("服务机构", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("职位", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("薪资", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("离职原因", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("证明人及联系方式 ", basicFont));
				basicInfoTable.addCell(basicCell);

				if (supervisoryWorkExperience != null
						&& supervisoryWorkExperience.size() > 0) {
					for (SupervisorWorkExperienceVO workExperience : supervisoryWorkExperience) {

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph((DateUtil
								.getStringFormatByDate(
										workExperience.getWorkStartTime(),
										"yyyy-MM-dd")), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph((DateUtil
								.getStringFormatByDate(
										workExperience.getWorkEndTime(),
										"yyyy-MM-dd")), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(workExperience
								.getServiceOrganization(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(workExperience
								.getPosition(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(workExperience
								.getCompensation(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(workExperience
								.getLeaveReason(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell
								.setPhrase(new Paragraph(workExperience
										.getCertifier()
										+ "  "
										+ workExperience.getContactNumber(),
										basicFont));
						basicInfoTable.addCell(basicCell);
					}
				}

				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph("四、*家庭状况", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("与家庭成员关系", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("姓名", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("职业", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("单位", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("电话", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(" ", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(" ", basicFont));
				basicInfoTable.addCell(basicCell);

				if (supervisoryFamily != null && supervisoryFamily.size() > 0) {
					for (SupervisorFamilyVO family : supervisoryFamily) {

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(family
								.getRelationship(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(family.getName(),
								basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(family
								.getProfession(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(family
								.getOrganization(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(
								family.getTelephone(), basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph(" ", basicFont));
						basicInfoTable.addCell(basicCell);

						basicCell.setColspan(1);
						basicCell.setRowspan(1);
						basicCell.setPhrase(new Paragraph("  ", basicFont));
						basicInfoTable.addCell(basicCell);
					}
				}

				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph("五、*招聘渠道", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("招聘渠道", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(6);
				basicCell.setRowspan(1);
				int otherChannel = supervisoryRecommend.getOtherChannel();
				if (otherChannel == 1) {
					basicCell.setPhrase(new Paragraph(
							" ■ 校园招聘    □ 监管员推荐   □ 社会招聘   □ 内部人员推荐  ",
							basicFont));
				} else if (otherChannel == 2) {
					basicCell.setPhrase(new Paragraph(
							" □ 校园招聘    ■ 监管员推荐   □ 社会招聘   □ 内部人员推荐  ",
							basicFont));
				} else if (otherChannel == 3) {
					basicCell.setPhrase(new Paragraph(
							" □ 校园招聘    □ 监管员推荐   ■ 社会招聘   □ 内部人员推荐  ",
							basicFont));
				} else if (otherChannel == 4) {
					basicCell.setPhrase(new Paragraph(
							" □ 校园招聘    □ 监管员推荐   □ 社会招聘   ■ 内部人员推荐  ",
							basicFont));
				} else {
					basicCell.setPhrase(new Paragraph(
							" □ 校园招聘    □ 监管员推荐   □ 社会招聘   □ 内部人员推荐  ",
							basicFont));
				}
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("推荐人姓名", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(supervisoryRecommend
						.getRecommenderName(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("推荐人职位", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(supervisoryRecommend
						.getRecommenderPosition(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(2);
				basicCell.setPhrase(new Paragraph("推荐人所在部门或4S店名称", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(2);
				basicCell.setRowspan(2);
				basicCell.setPhrase(new Paragraph(supervisoryRecommend
						.getRecommenderDepartment(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("推荐人联系方式", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(supervisoryRecommend
						.getRecommenderPhone(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("与推荐人间关系", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(supervisoryRecommend
						.getRecommenderRelationship(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(7);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell
						.setPhrase(new Paragraph(
								"本人声明：以上情况均为如实、正确填写，如有任何虚构，愿接受无偿之解雇；本人确认已阅读过公司发布的制度，了解了所有内容，并且公司为我解答了相关疑问。我将遵守公司的管理制度及规定。",
								basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(3);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				basicCell.setPhrase(new Paragraph("本人对以上内容表示同意并签字确认：",
						basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph(superVisorBaseInfo.getSign(),
						basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph("日期：", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(2);
				basicCell.setRowspan(1);
				basicCell.setPhrase(new Paragraph(DateUtil
						.getStringFormatByDate(
								superVisorBaseInfo.getCurrentTime(),
								"yyyy-MM-dd"), basicFont));
				basicInfoTable.addCell(basicCell);

				document.add(basicInfoTable);
				// 关闭PDF文件流
				document.close();
				return "/pdf/" + randomFilename + ".pdf";
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * 手工台账
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static CreatePdfVO sgtz(int id, HttpServletRequest request) {

		try {

			DraftService draftservice = new DraftService();

			DealerService ds = new DealerService();

			// 经销商
			String distrib = "";
			// 金融机构
			String financial_institution = "";

			String draftnum = "";

			CarOperationService coservice = new CarOperationService();
			SuperviseImportService sservice = new SuperviseImportService();

			UserVO user = UserSessionUtil.getUserSession(request).getUser();

			CarOperationVO covo = coservice.loadCarOperationById(id);

			List<SuperviseImportVO> sList = new ArrayList<SuperviseImportVO>();

			String cars = covo.getCars();
			if (!StringUtil.isEmpty(cars)) {
				String[] idArray = cars.split(",");
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO svo = sservice
							.loadSuperviseImportById(Integer
									.parseInt(idArray[i]));
					sList.add(svo);
				}
			}

			if (sList != null && sList.size() > 0) {
				SuperviseImportVO sivo = sList.get(0);
				draftnum = sivo.getDraft_num();
				DraftQueryVO draftquery = new DraftQueryVO();
				draftquery.setDraft_num(draftnum);
				List<DraftVO> draftList = draftservice
						.searchDraftList(draftquery);
				if (draftList != null && draftList.size() > 0) {
					DraftVO draftvo = draftList.get(0);
					DealerVO dealer = ds.get(draftvo.getDistribid());
					if (dealer != null) {
						distrib = dealer.getDealerName();
						financial_institution = ds
								.getBankNameByDealerId(draftvo.getDistribid());
					}
				}
			}

			// 检查时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("质押监管手工台账", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			List<CarOperationVO> cList = coservice.todaycount(2, user.getId());
			int count = 0;
			if (cList != null && cList.size() > 0) {
				count = cList.size();
			}

			document.add(titleTable);
			document.add(new Paragraph("经销商名称：" + distrib, level3));
			document.add(new Paragraph("合作行：" + financial_institution, level3));
			document.add(new Paragraph("票号：" + draftnum, level3));
			document.add(new Paragraph(" "));
			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);

			float[] widths1 = { 1f };
			PdfPTable basicInfoTable1 = new PdfPTable(widths1);
			basicInfoTable1.setTotalWidth(500);
			basicInfoTable1.setLockedWidth(true);

			PdfPCell basicCell1 = new PdfPCell();
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("编号：" + distrib
					+ formatter.format(new Date()) + count, basicFont));
			basicInfoTable1.addCell(basicCell1);
			document.add(basicInfoTable1);

			// 创建基础信息表格
			float[] widths = { 0.07f, 0.07f, 0.07f, 0.07f, 0.07f, 0.07f, 0.07f,
					0.07f, 0.07f, 0.07f, 0.07f, 0.07f, 0.07f, 0.07f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("合格证发证日期", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("合格证号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车辆型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车身结构", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("排量", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("颜色", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("发动机号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("钥匙号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("入库时间", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("出库时间", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("交付人", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("接收人", basicFont));
			basicInfoTable.addCell(basicCell);

			if (sList != null && sList.size() > 0) {
				for (int i = 0; i < sList.size(); i++) {
					SuperviseImportVO svo = sList.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					String certificate_date = DateUtil.getStringFormatByDate(
							svo.getCertificate_date(), "yyyy-MM-dd");
					basicCell.setPhrase(new Paragraph(certificate_date,
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_structure(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getDisplacement(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell
							.setPhrase(new Paragraph(svo.getColor(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getKey_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					String storagetime = "";
					if (svo.getStoragetime() != null) {
						storagetime = DateUtil.getStringFormatByDate(
								svo.getStoragetime(), "yyyy-MM-dd");
					}
					basicCell.setPhrase(new Paragraph(storagetime, basicFont));
					basicInfoTable.addCell(basicCell);
					String outtime = "";
					if (svo.getOuttime() != null) {
						outtime = DateUtil.getStringFormatByDate(
								svo.getOuttime(), "yyyy-MM-dd");
					}
					basicCell.setPhrase(new Paragraph(outtime, basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setColspan(1);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setColspan(1);
					basicCell.setPhrase(new Paragraph(" ", basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			document.add(basicInfoTable);
			// 关闭PDF文件流
			document.close();

			CreatePdfVO cvo = new CreatePdfVO();
			cvo.setUrl("/pdf/" + randomFilename + ".pdf");
			cvo.setName(distrib + "质押监管手工台账" + formatter.format(new Date()));

			return cvo;

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	/**
	 * 监管物释放申请书
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static CreatePdfVO jgwsf(int id, HttpServletRequest request) {

		try {

			CarOperationService coservice = new CarOperationService();
			SuperviseImportService sservice = new SuperviseImportService();

			UserVO user = UserSessionUtil.getUserSession(request).getUser();

			CarOperationVO covo = coservice.loadCarOperationById(id);

			List<SuperviseImportVO> sList = new ArrayList<SuperviseImportVO>();

			DraftService draftservice = new DraftService();
			DealerService ds = new DealerService();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// 经销商
			String distrib = "";

			String draftnum = "";
			String cars = covo.getCars();
			if (!StringUtil.isEmpty(cars)) {
				String[] idArray = cars.split(",");
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO svo = sservice
							.loadSuperviseImportById(Integer
									.parseInt(idArray[i]));
					sList.add(svo);
				}
			}
			if (sList != null && sList.size() > 0) {
				SuperviseImportVO sivo = sList.get(0);
				draftnum = sivo.getDraft_num();
				DraftQueryVO draftquery = new DraftQueryVO();
				draftquery.setDraft_num(draftnum);
				List<DraftVO> draftList = draftservice
						.searchDraftList(draftquery);
				if (draftList != null && draftList.size() > 0) {
					DraftVO draftvo = draftList.get(0);
					DealerVO dealer = ds.get(draftvo.getDistribid());
					if (dealer != null) {
						distrib = dealer.getDealerName();
					}

				}
			}

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("监管物释放申请书", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("   ", level1));

			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);
			// 头部
			List<CarOperationVO> cList = coservice.todaycount(2, user.getId());
			int count = 0;
			if (cList != null && cList.size() > 0) {
				count = cList.size();
			}

			document.add(new Paragraph("	", basicFont));

			float[] widths1 = { 1f };
			PdfPTable basicInfoTable1 = new PdfPTable(widths1);
			basicInfoTable1.setTotalWidth(500);
			basicInfoTable1.setLockedWidth(true);

			PdfPCell basicCell1 = new PdfPCell();
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("编号：" + distrib
					+ formatter.format(new Date()) + count + 1, basicFont));
			basicInfoTable1.addCell(basicCell1);
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("票号：" + draftnum, basicFont));
			basicInfoTable1.addCell(basicCell1);
			document.add(basicInfoTable1);

			document.add(new Paragraph("	", headFont));

			document.add(new Paragraph("致：______________________________：",
					basicFont));
			document.add(new Paragraph(
					"  按照我公司、贵司以及中都物流有限公司三方签订的编号为__________________________的《动产监管合作协议》，现公司拟申请对质物解除监管，解除的监管物以本清单列明为准。作为监管物解除监管的条件，本公司已将共计______________元的款项划入本公司在贵司的账户____________________上。",
					basicFont));
			document.add(new Paragraph("  ", basicFont));

			// 创建基础信息表格
			float[] widths = { 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.125f,
					0.125f, 0.125f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车辆型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("颜色", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("发动机号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("合格证", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("钥匙(数)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("单价(元)", basicFont));
			basicInfoTable.addCell(basicCell);

			if (sList != null && sList.size() > 0) {
				for (int i = 0; i < sList.size(); i++) {
					SuperviseImportVO svo = sList.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell
							.setPhrase(new Paragraph(svo.getColor(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell
							.setPhrase(new Paragraph(svo.getMoney(), basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			basicCell.setColspan(8);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("合计：", basicFont));
			basicInfoTable.addCell(basicCell);

			document.add(basicInfoTable);
			document.add(new Paragraph(
					"                                                                                                                                               公司：__(丙方预留印鉴)",
					basicFont));
			document.add(new Paragraph(
					"                                                                                                                                               日期：        年      月      日",
					basicFont));
			for (int i = 0; i < 8; i++) {
				document.add(new Paragraph("  ", basicFont));
			}

			document.add(new Paragraph("甲方：____________________申请释放回执",
					basicFont));
			document.add(new Paragraph(
					"    我司现通知中都物流有限公司准许                （丙方公司名称）提取上述表述监管的监管物共计    台。特此通知！",
					basicFont));

			document.add(new Paragraph("1.本表单一式两份、甲方和乙方各一份", basicFont));
			document.add(new Paragraph("2.共    页，第    页", basicFont));
			// 关闭PDF文件流
			document.close();

			CreatePdfVO cvo = new CreatePdfVO();
			cvo.setUrl("/pdf/" + randomFilename + ".pdf");
			cvo.setName(distrib + "监管物释放申请书" + formatter.format(new Date()));
			return cvo;

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	/**
	 * 监管物移动申请书
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public static CreatePdfVO jgwyd(int id, HttpServletRequest request) {

		try {

			CarOperationService coservice = new CarOperationService();
			SuperviseImportService sservice = new SuperviseImportService();

			UserVO user = UserSessionUtil.getUserSession(request).getUser();

			CarOperationVO covo = coservice.loadCarOperationById(id);

			List<SuperviseImportVO> sList = new ArrayList<SuperviseImportVO>();

			DraftService draftservice = new DraftService();
			DealerService ds = new DealerService();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// 经销商
			String distrib = "";

			String draftnum = "";
			String cars = covo.getCars();
			if (!StringUtil.isEmpty(cars)) {
				String[] idArray = cars.split(",");
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO svo = sservice
							.loadSuperviseImportById(Integer
									.parseInt(idArray[i]));
					sList.add(svo);
				}
			}
			if (sList != null && sList.size() > 0) {
				SuperviseImportVO sivo = sList.get(0);
				draftnum = sivo.getDraft_num();
				DraftQueryVO draftquery = new DraftQueryVO();
				draftquery.setDraft_num(draftnum);
				List<DraftVO> draftList = draftservice
						.searchDraftList(draftquery);
				if (draftList != null && draftList.size() > 0) {
					DraftVO draftvo = draftList.get(0);
					DealerVO dealer = ds.get(draftvo.getDistribid());
					if (dealer != null) {
						distrib = dealer.getDealerName();
					}

				}
			}

			Random rand = new Random();// 生成随机数
			int random = rand.nextInt();

			Calendar calCurrent = Calendar.getInstance();
			int intDay = calCurrent.get(Calendar.DATE);
			int intMonth = calCurrent.get(Calendar.MONTH) + 1;
			int intYear = calCurrent.get(Calendar.YEAR);
			int intTime = calCurrent.getTime().getHours()
					+ calCurrent.getTime().getMinutes()
					+ calCurrent.getTime().getSeconds();
			String now = String.valueOf(intYear) + "_"
					+ String.valueOf(intMonth) + "_" + String.valueOf(intDay)
					+ "_" + intTime;

			// 根据日期和随机数生成文件名
			String randomFilename = now
					+ String.valueOf(random > 0 ? random : (-1) * random);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "pdf/" + randomFilename + ".pdf";

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(url));
			document.open();

			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 一级标题字体
			Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level2 = new Font(bf, 15, Font.NORMAL, new CMYKColor(255, 255,
					255, 255));
			// 二级标题字体
			Font level3 = new Font(bf, 9, Font.BOLD, new CMYKColor(255, 255,
					255, 255));

			// 标题
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setTotalWidth(500);
			titleTable.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Paragraph("监管物移动申请书", level1));
			titleCell.setBorder(0);
			titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);

			document.add(titleTable);
			document.add(new Paragraph("   ", level1));

			Font headFont = new Font(bf, 8, Font.NORMAL);
			Font basicFont = new Font(bf, 9, Font.NORMAL);
			// 头部
			List<CarOperationVO> cList = coservice.todaycount(2, user.getId());
			int count = 0;
			if (cList != null && cList.size() > 0) {
				count = cList.size();
			}

			document.add(new Paragraph("	", basicFont));

			float[] widths1 = { 1f };
			PdfPTable basicInfoTable1 = new PdfPTable(widths1);
			basicInfoTable1.setTotalWidth(500);
			basicInfoTable1.setLockedWidth(true);

			PdfPCell basicCell1 = new PdfPCell();
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("编号：" + distrib
					+ formatter.format(new Date()) + count + 1, basicFont));
			basicInfoTable1.addCell(basicCell1);
			basicCell1.setColspan(1);
			basicCell1.setRowspan(1);
			basicCell1.setBorder(0);
			basicCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			basicCell1.setPhrase(new Paragraph("票号：" + draftnum, basicFont));
			basicInfoTable1.addCell(basicCell1);
			document.add(basicInfoTable1);

			document.add(new Paragraph("	", headFont));

			document.add(new Paragraph("致：______________________________：",
					basicFont));
			document.add(new Paragraph(
					"  按照我公司、贵司以及中都物流有限公司三方签订的编号为_________________的《动产监管合作协议》，现公司拟申请对监管物移动监管，移动的监管物以本清单列明为准。作为移动监管物的条件，本公司已将共计___________________元的款项划入本公司在贵司的账户_____________________上并且保证按照甲乙两方的要求提供远程监管证明文件。",
					basicFont));
			document.add(new Paragraph("  ", basicFont));

			// 创建基础信息表格
			float[] widths = { 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.125f,
					0.125f, 0.125f };
			PdfPTable basicInfoTable = new PdfPTable(widths);
			basicInfoTable.setTotalWidth(500);
			basicInfoTable.setLockedWidth(true);

			PdfPCell basicCell = new PdfPCell();

			basicCell.setColspan(1);
			basicCell.setRowspan(1);
			basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			basicCell.setPhrase(new Paragraph("序号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车辆型号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("车架号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("发动机号", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("合格证", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("钥匙(数)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("移动地址及距离(公里)", basicFont));
			basicInfoTable.addCell(basicCell);
			basicCell.setPhrase(new Paragraph("单价(元)", basicFont));
			basicInfoTable.addCell(basicCell);

			if (sList != null && sList.size() > 0) {
				for (int i = 0; i < sList.size(); i++) {
					SuperviseImportVO svo = sList.get(i);
					basicCell.setPhrase(new Paragraph((i + 1) + "", basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCar_model(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getVin(), basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getEngine_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getCertificate_num(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getKey_amount(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell.setPhrase(new Paragraph(svo.getTwo_name(),
							basicFont));
					basicInfoTable.addCell(basicCell);
					basicCell
							.setPhrase(new Paragraph(svo.getMoney(), basicFont));
					basicInfoTable.addCell(basicCell);
				}
			}

			basicCell.setColspan(8);
			basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			basicCell.setPhrase(new Paragraph("合计：", basicFont));
			basicInfoTable.addCell(basicCell);

			document.add(basicInfoTable);
			document.add(new Paragraph(
					"                                                                                                                                               公司：__(丙方预留印鉴)",
					basicFont));
			document.add(new Paragraph(
					"                                                                                                                                               日期：        年      月      日",
					basicFont));
			for (int i = 0; i < 8; i++) {
				document.add(new Paragraph("  ", basicFont));
			}

			document.add(new Paragraph("甲方：____________________申请释放回执",
					basicFont));
			document.add(new Paragraph(
					"    我司现通知中都物流有限公司准许____________________（丙方公司名称）移动上述表述监管的车辆共计_______台，钥匙_____把。特此通知！",
					basicFont));

			document.add(new Paragraph("1.本表单一式两份、甲方和乙方各一份", basicFont));
			document.add(new Paragraph("2.共    页，第    页", basicFont));
			// 关闭PDF文件流
			document.close();

			CreatePdfVO cvo = new CreatePdfVO();
			cvo.setUrl("/pdf/" + randomFilename + ".pdf");
			cvo.setName(distrib + "监管物移动申请书" + formatter.format(new Date()));
			return cvo;

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}

	public static double sum(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}

	public static String workInspection(int id, HttpServletRequest request)
			throws Exception {
		InspectionService inspecService = new InspectionService();
		
		InspectionReportQueryBean inspectionReport=inspecService.getInspectionReport(id) ;
		InspectionInfoVO info = inspectionReport.getInfo() ;
		InspectionItemVO item = inspectionReport.getItem();
		Map<String, List<InspectionRecordVO>> record =inspectionReport.getRecords() ;
		InspectionCommunionVO comm = inspectionReport.getCommunion();
		InspectionSupervisorVO supervisor =inspectionReport.getSupervisor() ; 
		InspectionResultVO result = inspectionReport.getInspecResult();
		List<UploadfileVO> pictures = inspecService.findPictureById(id);

		// 根据日期和随机数生成文件名
		Random rand = new Random();// 生成随机数
		int random = rand.nextInt(1000);

		Calendar calCurrent = Calendar.getInstance();
		int intDay = calCurrent.get(Calendar.DATE);
		int intMonth = calCurrent.get(Calendar.MONTH) + 1;
		int intYear = calCurrent.get(Calendar.YEAR);

		String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth)
				+ "_" + String.valueOf(intDay);
		// 根据日期和随机数生成文件名
		String randomFilename = now
				+ String.valueOf(random > 0 ? random : (-1) * random);

		String url = request.getSession().getServletContext().getRealPath("/")
				+ "pdf/" + randomFilename + ".pdf";

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(url));
		document.open();

		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.NOT_EMBEDDED);
		// 一级标题字体
		Font level1 = new Font(bf, 20, Font.NORMAL, new CMYKColor(255, 255,255, 255));

		// 标题
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setTotalWidth(500);
		titleTable.setLockedWidth(true);

		PdfPCell titleCell = new PdfPCell(new Paragraph("巡店报告", level1));
		titleCell.setBorder(0);
		titleCell.setBorderColor(new CMYKColor(0, 0, 0, 0));
		titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		titleTable.addCell(titleCell);

		document.add(titleTable);
		document.add(new Paragraph("   ", level1));

		Font basicFont = new Font(bf, 9, Font.NORMAL);
		float[] widths = { 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.35f };
		PdfPTable basicInfoTable = new PdfPTable(widths);
		basicInfoTable.setTotalWidth(500);
		basicInfoTable.setLockedWidth(true);

		PdfPCell basicCell = new PdfPCell();

		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("一:基础信息", basicFont));
		basicInfoTable.addCell(basicCell);
		// 第二行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("经销商名称", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getDealer_name(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("合作金融机构", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getBank(), basicFont));
		basicInfoTable.addCell(basicCell);

		// 第三行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("客户经理", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(info.getCustomer_manager(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("品牌", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getBrand(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("企业性质", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		if (info.getCompany_type() == 1) {
			basicCell.setPhrase(new Paragraph("国企 ", basicFont));
		} else if (info.getCompany_type() == 2) {
			basicCell.setPhrase(new Paragraph("民企 ", basicFont));
		} else if (info.getCompany_type() == 3) {
			basicCell.setPhrase(new Paragraph("合资 ", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		// 第四行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("地址", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getAddress(), basicFont));
		basicInfoTable.addCell(basicCell);

		// 第五行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("合作开始时间", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(DateUtil.getStringFormatByDate(
				info.getCooperate_time(), "yyyy-MM-dd"), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("操作模式", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);

		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (info.getOper_mode() == 1) {
			basicCell.setPhrase(new Paragraph("车证 ", basicFont));
		} else if (info.getOper_mode() == 2) {
			basicCell.setPhrase(new Paragraph("合格证  ", basicFont));
		} else if (info.getOper_mode() == 3) {
			basicCell.setPhrase(new Paragraph("其他  ", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("检查类型", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);

		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (info.getCheck_type() == 1) {
			basicCell.setPhrase(new Paragraph("常规检查 ", basicFont));
		} else if (info.getCheck_type() == 2) {
			basicCell.setPhrase(new Paragraph("异常检查  ", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		// 第六行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("业务专员", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getBusiness_name(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否存在二库", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);

		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (info.getExist_storage() == 0) {
			basicCell.setPhrase(new Paragraph("不存在", basicFont));
		} else if (info.getExist_storage() == 1) {
			basicCell.setPhrase(new Paragraph("存在  ", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否存在二网", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);

		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (info.getExist_network() == 0) {
			basicCell.setPhrase(new Paragraph("不存在  ", basicFont));
		} else if (info.getExist_network() == 1) {
			basicCell.setPhrase(new Paragraph("存在  ", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		// 第七行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("检查人员", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getCheck_name(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("检查日期", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(DateUtil.getStringFormatByDate(
				info.getCheck_time(), "yyyy-MM-dd"), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("检查用时", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getCheck_period(), basicFont));
		basicInfoTable.addCell(basicCell);

		// 第八行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("陪同人员", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(info.getEntourage(), basicFont));
		basicInfoTable.addCell(basicCell);

		// 第九行
		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph("1.基础信息介绍", basicFont));
		basicInfoTable.addCell(basicCell);

		// 第十行
		basicCell.setColspan(6);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		/*
		 * String description = ""; if (info.getDescription() != null &&
		 * info.getDescription().length() > 40) { int rows =
		 * info.getDescription().length() / 40; for (int i = 1; i <= rows; i++)
		 * { int j = (i - 1) * 40, k = i * 40; description +=
		 * info.getDescription().substring(j, k) + "\n"; } description +=
		 * info.getDescription().substring(rows * 40); } else { description =
		 * info.getDescription(); }
		 */

		basicCell.setPhrase(new Paragraph(info.getDescription(), basicFont));
		basicInfoTable.addCell(basicCell);
		// 第十一行
		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph("2.二网、二库地址及介绍", basicFont));
		basicInfoTable.addCell(basicCell);
		// 第十二行
		basicCell.setColspan(6);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        basicCell.setPhrase(new Paragraph(info.getOther_description(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("二:检查内容", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("序号", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(3);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("项目", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("填写项（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("备注", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(11);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("1", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(11);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("质押物情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(7);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("质押车总数量（" + item.getCar_num() + "）台",
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("展厅及周边（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getHall_num() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getHall_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("二库（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getStore_num() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getStore_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("二网（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(item.getNetwork_num() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getNetwork_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("销售未还款", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getArrears(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getArrears_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("当日销售（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getSale_num() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getSale_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("私自售卖车辆（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getPriv_sale() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(item.getPrivSale_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("私自移动车辆（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getMove_car() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getMoveCar_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("合格证/关单 （"
				+ item.getCertificate_num() + "）份", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("正常合格证数量", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getNormal_certificate() + "",
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getNormal_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("异常合格证数量", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getAbnormal_certificate() + "",
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(item.getAbnormal_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("钥匙 （" + item.getKey_num() + "）把",
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("正常钥匙数量", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(item.getNormal_key() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getKey_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("异常钥匙数量", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getAbnormal_key() + "",
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getAbnormalKey_remark(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(6);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("2", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(6);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("纸质文件情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("手工台账", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否与总账一致", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getManual_accounting() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getManual_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("授权人签字是否规范", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getManual_standard() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(item.getStandard_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("日查库检查表", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否与总账一致", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getCheck_list() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getCheck_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("日期是否连续", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getCheck_continuity() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getContinuity_remark(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("授权书、委任书", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否符合规范", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getAuthorization() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getAuth_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("钥匙交接及钥匙借出登记表", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("授权人签字是否规范", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getKey_handover() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(item.getHandover_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("3", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("汇票及押车情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("汇票异常情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("开票1个月未押满", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getBilling(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getBilling_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("开票15日未押车", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getGuard(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getGuard_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("超期未清票", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getClear_ticket(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getClearTicket_remark(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("其他情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getOther(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getOther_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("4", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("其他情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("系统情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否可以正常使用", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getSystem_state() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getState_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("标识", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("质押车是否摆放标识", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getIdentification() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getIdentification_remark(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("办公设备", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否符合检查要求", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getOffice_machine() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getMachine_remark(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("办公条件", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("是否为独立办公室", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (item.getOffice_conditions() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(item.getConditions_remark(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("三:检查过程中发现的问题及监管员优/缺点", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("序号", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("发现问题", basicFont));
		basicInfoTable.addCell(basicCell);
		if (record != null && record.get("problems") != null) {
			List<InspectionRecordVO> problemList = record.get("problems");
			for (int i = 0; i < problemList.size(); i++) {
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph(i + 1 + "", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(5);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph(problemList.get(i)
						.getContent(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("处理结果", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(5);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph(problemList.get(i)
						.getResult(), basicFont));
				basicInfoTable.addCell(basicCell);
			}

		}
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("序号", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("监管员优点/缺点", basicFont));
		basicInfoTable.addCell(basicCell);

		if (record != null && record.get("supervisors") != null) {
			List<InspectionRecordVO> supervisorList = record.get("problems");
			for (int i = 0; i < supervisorList.size(); i++) {
				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph(i + 1 + "", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(5);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph(supervisorList.get(i)
						.getContent(), basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(1);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicCell.setPhrase(new Paragraph("处理结果", basicFont));
				basicInfoTable.addCell(basicCell);

				basicCell.setColspan(5);
				basicCell.setRowspan(1);
				basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicCell.setPhrase(new Paragraph(supervisorList.get(i)
						.getResult(), basicFont));
				basicInfoTable.addCell(basicCell);
			}
		}

		// 与店方沟通情况
		// 第一行
		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("四:与店方沟通情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("店方人员姓名", basicFont));
		basicInfoTable.addCell(basicCell);
		// 第二行
		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(comm.getName(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("职位", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(comm.getPosition(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("月销量（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		// 第三行
		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(comm.getMonth_sales() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("自有车辆（台）", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(comm.getCar_count() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("座谈内容", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(comm.getContent(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("店方情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(comm.getInfo(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("我司情况", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(4);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(comm.getCompany_info(), basicFont));
		basicInfoTable.addCell(basicCell);

		// 第五部分：监管员基本情况介绍
		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("五:监管员基本情况介绍", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("监管员姓名", basicFont));
		basicInfoTable.addCell(basicCell);
		// 第二行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getName(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("联系方式", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setPhrase(new Paragraph(supervisor.getContact_phone(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("紧急联系方式", basicFont));
		basicInfoTable.addCell(basicCell);

		// 第三行
		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getEmergency_telephone()
				+ "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("性别", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (supervisor.getSex() == 0) {
			basicCell.setPhrase(new Paragraph("未知", basicFont));
		} else if (supervisor.getAge() == 1) {
			basicCell.setPhrase(new Paragraph("男", basicFont));
		} else if (supervisor.getAge() == 2) {
			basicCell.setPhrase(new Paragraph("女", basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("学历", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(supervisor.getEducation(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("年龄", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getAge() + "", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("公司认可属性", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getCompany_attr(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("户口所在地", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getRegistered_address(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("本人实际属性", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getPeople_attr(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("住宿", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getAddress(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("用餐", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getMeal(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("电脑技能", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getComputer_skills(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("招聘来源", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getJob_channel(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("培训方式", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(supervisor.getTrain_mode(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("培训人员", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell
				.setPhrase(new Paragraph(supervisor.getTrain_man(), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("周末休假", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(2);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (supervisor.getWeekend() == 1) {
			basicCell.setPhrase(new Paragraph("是", basicFont));
		} else {
			basicCell.setPhrase(new Paragraph("否", basicFont));
		}

		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(2);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("上岗时间", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph("初次上岗时间", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(DateUtil.getStringFormatByDate(
				supervisor.getPosts_time(), "yyyy-MM-dd"), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph("接管此店时间", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(DateUtil.getStringFormatByDate(
				supervisor.getTakeOver_time(), "yyyy-MM-dd"), basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("工作经历(监管员对工作、总部建议)", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getWork_experience(),
				basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("巡检人员对监管员评价", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(supervisor.getEvaluate(), basicFont));
		basicInfoTable.addCell(basicCell);

		// 第六部分：巡店总结
		basicCell.setColspan(6);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("六:巡店总结", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("风险等级", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if (result.getDanger_level() == 4) {
			basicCell.setPhrase(new Paragraph("● 无  ○ A级  ○ B级  ○ C级  ",
					basicFont));
		} else if (result.getDanger_level() == 1) {
			basicCell.setPhrase(new Paragraph("○ 无  ● A级  ○ B级  ○ C级 ",
					basicFont));
		} else if (result.getDanger_level() == 2) {
			basicCell.setPhrase(new Paragraph("○ 无  ○ A级  ● B级  ○ C级  ",
					basicFont));
		} else if (result.getDanger_level() == 3) {
			basicCell.setPhrase(new Paragraph("○ 无  ○ A级  ○ B级  ○ C级  ",
					basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("经销店等级", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if (result.getDealer_level() == 4) {
			basicCell.setPhrase(new Paragraph("● 无  ○ A级  ○ B级  ○ C级 ",
					basicFont));
		} else if (result.getDealer_level() == 1) {
			basicCell.setPhrase(new Paragraph("○ 无  ● A级  ○ B级  ○ C级",
					basicFont));
		} else if (result.getDealer_level() == 2) {
			basicCell.setPhrase(new Paragraph("○ 无  ○ A级  ● B级  ○ C级 ",
					basicFont));
		} else if (result.getDealer_level() == 3) {
			basicCell.setPhrase(new Paragraph("○ 无  ○ A级  ○ B级  ● C级 ",
					basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("监管员等级", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(1);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if (result.getSupervisor_level() == 4) {
			basicCell.setPhrase(new Paragraph("● 无   ○ A级    ○ B级   ○ C级 ",
					basicFont));
		} else if (result.getSupervisor_level() == 1) {
			basicCell.setPhrase(new Paragraph("○ 无   ● A级   ○ B级    ○ C级 ",
					basicFont));
		} else if (result.getSupervisor_level() == 2) {
			basicCell.setPhrase(new Paragraph("○ 无   ○ A级   ● B级    ○ C级",
					basicFont));
		} else if (result.getSupervisor_level() == 3) {
			basicCell.setPhrase(new Paragraph("○ 无   ○ A级   ○ B级    ● C级 ",
					basicFont));
		}
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(1);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		basicCell.setPhrase(new Paragraph("巡店总结", basicFont));
		basicInfoTable.addCell(basicCell);

		basicCell.setColspan(5);
		basicCell.setRowspan(8);
		basicCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		basicCell.setPhrase(new Paragraph(result.getRemak(), basicFont));
		basicInfoTable.addCell(basicCell);
		document.add(basicInfoTable);

		if (pictures != null && pictures.size() > 0) {
			//document.newPage();
			Paragraph ssp = new Paragraph("\n\n", basicFont);
			ssp.setAlignment(Element.ALIGN_CENTER);
			document.add(ssp);
			
		    Paragraph titleP = new Paragraph("七:店面拍照", basicFont);
			titleP.setAlignment(Element.ALIGN_CENTER);
			document.add(titleP);
			String dir = FileUtil.getFileFolder();
			
			int times = 0;
			int thistime ;
			int lefttime = pictures.size();
			if(pictures.size() > 3){
				thistime = 3;
			}else{
				thistime = pictures.size();
			}
			
			while(lefttime > 0){
			PdfPTable table = new PdfPTable(thistime);  
            table.setWidthPercentage(100);
            
            PdfPTable table1 = new PdfPTable(2);  
            table1.setWidthPercentage(67);
            PdfPTable table2 = new PdfPTable(1);  
            table2.setWidthPercentage(34);
            /*if(thistime == 3){
            	float[] columnWidths = {3.3f,3.3f,3.3f}; 
            	table.setWidths(columnWidths);
            }else if(thistime == 2){
            	//float[] columnWidths = {5f,5f}; 
            	float[] columnWidths = {3.3f,3.3f}; 
            	table.setWidths(columnWidths);
            }else{
            	//float[] columnWidths = {10f};
            	float[] columnWidths = {3.3f}; 
            	table.setWidths(columnWidths);
            }*/
            table.setHorizontalAlignment(Element.ALIGN_CENTER); //垂直居中
            table.getDefaultCell().setBorderWidth(0); //不显示边框
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.getDefaultCell().setBorderWidth(0);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.getDefaultCell().setBorderWidth(0);

			for (int i = 0; i < thistime; i++) {
				String imgurl = dir + pictures.get(times*3+i).getFile_path();
				Image img = null;
				try{
					img	= Image.getInstance(imgurl);
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
				// 图片缩小到25%
				//img.scalePercent(15);
				//img.setAlignment(Image.MIDDLE);
				//img.setBorder(Image.BOX);
				//img.setBorderWidth(10);
				//img.setBorderColor(BaseColor.WHITE);
			    //img.scaleAbsolute(100,100);//控制图片大小
			    //img.setAbsolutePosition(0,200);//控制图片位置
			    //img.scalePercent(20,20);//依照比例缩放  
				//document.add(img);
				/*Paragraph picName = new Paragraph(pictures.get(times*3+i)
						.getFile_name(), basicFont);*/
				//picName.setAlignment(Element.ALIGN_CENTER);
				img.setAbsolutePosition(0, 0);
	            img.scaleAbsolute(120, 200);// 直接设定显示尺寸
	            img.scalePercent(50);// 表示显示的大小为原尺寸的50%
	            img.scalePercent(25, 50);// 图像高宽的显示比例
			    if (thistime == 3) {
			    	table.addCell(img);
				}else if (thistime == 2) {
					table1.addCell(img);
				}else if (thistime == 1) {
					table2.addCell(img);
				}
			    
				
				//document.add(picName);
			}
			document.add(table);document.add(table1);document.add(table2);
			if(lefttime >= 3){
				lefttime = lefttime - 3;
				if(lefttime > 3){
					thistime = 3;
				}else{
					thistime = lefttime;
				}
				times++;
			}else{
				lefttime = 0;
				times++;
			}
			}
		}
		

		document.newPage();
		float[] footwidths = { 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.25f };
		PdfPTable footTable = new PdfPTable(footwidths);
		footTable.setTotalWidth(500);
		footTable.setLockedWidth(true);

		PdfPCell footCell = new PdfPCell();
		footCell.setBorder(0);
		footCell.setBorderColor(new CMYKColor(0, 0, 0, 0));

		footCell.setColspan(6);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		footCell.setPhrase(new Paragraph("\n\n\n", basicFont));
		footTable.addCell(footCell);

		footCell.setColspan(5);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		footCell.setPhrase(new Paragraph("检  查 人: ", basicFont));
		footTable.addCell(footCell);

		footCell.setColspan(1);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		footCell.setPhrase(new Paragraph(info.getCheck_name(), basicFont));
		footTable.addCell(footCell);

		footCell.setColspan(5);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		footCell.setPhrase(new Paragraph("陪  同 人: ", basicFont));
		footTable.addCell(footCell);

		footCell.setColspan(1);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		footCell.setPhrase(new Paragraph(info.getEntourage(), basicFont));
		footTable.addCell(footCell);

		footCell.setColspan(5);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		footCell.setPhrase(new Paragraph("完成日期: ", basicFont));
		footTable.addCell(footCell);

		footCell.setColspan(1);
		footCell.setRowspan(1);
		footCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		if (info.getModify_time() == null) {
			info.setModify_time(new Date());
		}
		footCell.setPhrase(new Paragraph(df.format(info.getModify_time()),
				basicFont));
		footTable.addCell(footCell);

		document.add(footTable);
		document.close();
		return "/pdf/" + randomFilename + ".pdf";
	}

}

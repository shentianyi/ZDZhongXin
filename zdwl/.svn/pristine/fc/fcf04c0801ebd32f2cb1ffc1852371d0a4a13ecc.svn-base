package com.zd.csms.supervisory.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.dao.ICheckStockManageDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.supervisory.model.CheckStockCarVO;
import com.zd.csms.supervisory.model.CheckStockManageBean;
import com.zd.csms.supervisory.model.CheckStockManageQueryVO;
import com.zd.csms.supervisory.model.CheckStockManageVO;
import com.zd.csms.supervisory.model.CheckStockPicVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CheckStockManageQueryBean;
import com.zd.csms.util.Util;
import com.zd.csms.windcontrol.model.Image;
import com.zd.tools.file.FileUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CheckStockManageService extends ServiceSupport{
	
	private ICheckStockManageDAO dao = SupervisorDAOFactory.getCheckStockManageDAO();
	
	public CheckStockManageVO get(int id){
		return dao.get(CheckStockManageVO.class, id, new BeanPropertyRowMapper(CheckStockManageVO.class));
	}
	public CheckStockManageBean getCheckStockManageBranById(int id){
		return dao.getCheckStockManageBranById(id);
	}
	public int add(CheckStockManageVO bean) throws Exception{
		bean.setId(Util.getID(CheckStockManageVO.class));
		boolean flag=dao.add(bean);
		if(flag){
			this.setExecuteMessage("新增成功！");
			return bean.getId();
		}else{
			this.setExecuteMessage("新增失败！");
			return -1;
		}
	}
	public boolean addCheckStockCar(CheckStockCarVO bean) throws Exception{
		bean.setId(Util.getID(CheckStockCarVO.class));
		boolean flag=dao.add(bean);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean update(CheckStockManageVO bean) throws Exception{
		CheckStockManageVO checkStock = get(bean.getId());
		return dao.update(checkStock);
	}
	public boolean updateCheckStockManage(CheckStockManageBean bean) throws Exception{
		boolean flag= dao.updateCheckStockManage(bean);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		 return flag;
	}
	public boolean updateCheckStockCarActualstate(int id,int actualstate, String remark) throws Exception{
		boolean faTimeFlag = false;
		/*
		 * 如果是异常状态  且是首次更新异常时间
		 */
		if(actualstate > 4 && actualstate < 10){
			int count = dao.findCheckStockCarById(id);
			if(count>0){
				faTimeFlag = true;
			}
		}
		
		boolean flag= dao.updateCheckStockCarActualstate(id,actualstate,remark,faTimeFlag);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		 return flag;
	}
	public boolean delete(CheckStockManageVO bean) throws Exception{
		return dao.delete(CheckStockManageVO.class, bean.getId());
	}
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockManageQueryBean> findList(CheckStockManageQueryVO query, IThumbPageTools tools){
		return dao.findList(query, tools);
	}

	public List<CheckStockManageBean> findCheckStockManage(CheckStockManageQueryVO query, IThumbPageTools tools, HttpServletRequest request) {
		return dao.findCheckStockManage(query, tools,request);
	}
	public List<CheckStockManageBean> findCheckStockManageList(CheckStockManageQueryVO query, IThumbPageTools tools,HttpServletRequest request) {
		return dao.findCheckStockManageList(query, tools,request);
	}

	public List<CheckStockCarBean> findCheckStockCarList(int checkstockId, IThumbPageTools tools) {
		return dao.findCheckStockCarList(checkstockId, tools);
	}
	public List<CheckStockCarBean> findCheckStockCarList(int checkstockId) {
		return dao.findCheckStockCarList(checkstockId);
	}
	public List<SuperviseImportVO> findSuperviseImportList(int dealerid, int[] carStatus, IThumbPageTools tools) {
		return dao.findSuperviseImportList(dealerid,carStatus, tools);
	}
	public List<SuperviseImportVO> findSuperviseImportList(int dealerid, int carStatus) {
		return dao.findSuperviseImportList(dealerid,carStatus);
	}
	public boolean submit(int checkstockId) {
		boolean flag=dao.submit(checkstockId);
		if(flag){
			this.setExecuteMessage("提交成功！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}
	public boolean addPic(FormFile file, HttpServletRequest request,int id) {
		UserSession userSession = UserSessionUtil.getUserSession(request);
		int uplId = UploadFileUtil.savefile(file, userSession, request);
		// 保存文件到数据库中并获取对应的id
	/*	int index = dao.findPicIndex(id);
		if(index > 20){
			return;
		}
		index++;*/
		boolean flag = false;
		try {
			 flag = dao.addPic(id,uplId);
		} catch (Exception e) {
			this.setExecuteMessage("上传失败,已达到单个最大图片数量");
			e.printStackTrace();
			return false;
		}
		
		if(flag){
			this.setExecuteMessage("上传成功");
		}else{
			this.setExecuteMessage("上传失败");
		}
		
		return flag;
	}
	public List<Image> showPic(int checkstockId) {
		 UploadfileService ufService = new UploadfileService();
		 List<Image> imgList = new ArrayList<Image>();
		try {
			List<CheckStockPicVO> list = dao.findCheckStockPicById(checkstockId);
		if(list ==null){
			return imgList;
		}
		for (CheckStockPicVO vo : list) {
			Image image = new Image();
			UploadfileVO uplFile = ufService.loadUploadfileById(vo.getPicid());
			image.setUrl("/showImg.do?method=downLoadFile&filePath="
					+ uplFile.getFile_path() + "&fileName="
					+ uplFile.getFile_name());// 下载地址
			image.setCancelUrl("/showImg.do?method=showImg&filePath="+uplFile.getFile_path());//展示图片
			image.setThumbnailUrl("/showImg.do?method=showThumbnailImg&filePath="
					+ uplFile.getFile_path());
			// 上传完成后的缩略图地址
			image.setDeleteUrl("/windcontrol/inspection.do?method=deletePicture&fileId="
					+ uplFile.getId());

			image.setName(uplFile.getFile_name());

			// 删除图片的地址
			image.setDeleteType("POST");// 提交方式
			imgList.add(image);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgList;
	}
	public boolean downloadFiles(int id,String filename, HttpServletResponse response) {
		 UploadfileService ufService = new UploadfileService();
		List<CheckStockPicVO> picId = dao.findCheckStockPicById(id);
		if(picId.size() == 0){
			return false;
		}
		Map<String,String> map = new HashMap<String,String>();
		for (CheckStockPicVO vo : picId) {
			try {
				UploadfileVO uFile = ufService.loadUploadfileById(vo.getPicid());
				map.put(uFile.getFile_name(), (FileUtil.getFileFolder()+uFile.getFile_path()));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
			FileUtil.downloadZIP(map,filename, response);
		return true;
	}
}

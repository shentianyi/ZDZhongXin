package com.zd.csms.messagequartz.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.service.MessageQuartzService;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.StringUtil;

public class MsgUtil {
	
	private final static MsgUtil instance = new MsgUtil();
	public static MsgUtil getInstance() {
		return instance;
	}
	
	public static MessageQuartzService getService(){
		return new MessageQuartzService();
	}
	
	//填充对象的各级银行名称
	public static <T> void fillValues(List<T> list) throws Exception{
		if (null != list && list.size() > 0) {
			for (T bean : list) {
				Class<? extends Object> clazz = bean.getClass();
				Field fieldZer = clazz.getDeclaredField("bankFullName");
				Field fieldFir = clazz.getDeclaredField("oneBankName");
				Field fieldSec = clazz.getDeclaredField("twoBankName");
				Field fieldThr = clazz.getDeclaredField("threeBankName");
				
				setAccessible(fieldZer,fieldFir,fieldSec,fieldThr);
				String bankFullName = (String)fieldZer.get(bean);
				
				if (StringUtil.isNotEmpty(bankFullName)) {
					String[] bankNames = bankFullName.split("/");
					if (null != bankNames && bankNames.length > 0 && bankNames.length == 2) {
						fieldFir.set(bean, bankNames[0]);
						fieldSec.set(bean, bankNames[1]);
						fieldThr.set(bean, "");
					}else if (null != bankNames && bankNames.length > 0 && bankNames.length == 3) {
						fieldFir.set(bean, bankNames[0]);
						fieldSec.set(bean, bankNames[1]);
						fieldThr.set(bean, bankNames[2]);
					}
				}
			}
		}
	}
	
	/**
	 * 发送消息
	 * @param ids
	 * @param num
	 * @param type
	 * @param url
	 * @throws Exception
	 */
	public static void send(String ids,int num,int type,String url,String deptName,int msgType) throws Exception{
		MessageService mService = new MessageService();
		MessageVO vo = null;
		String[] userids = ids.split(",");
		List<MessageVO> msgList = new ArrayList<MessageVO>();
		for (String uid : userids) {
			vo = mService.loadMsgByUserAndType(Integer.parseInt(uid),type);
			if (null != vo) {//消息列表中存在对应消息，只对数量进行改变
				if (StringUtil.isNotEmpty(vo.getName())) {
					int count = Integer.parseInt(vo.getName());
					vo.setName(count + num + "");//更新消息数量
					vo.setIsread(MsgIsContants.NOREAD.getCode());//同时设置为未读
					mService.updMessage(vo);
				}
			}else{//消息列表中不存在对应消息，新增消息记录
				//用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
				msgList.add(new MessageVO(Integer.parseInt(uid),num + "",url,
						MsgIsContants.NOREAD.getCode(),msgType,type,
						deptName));
			}
		}
		MessageUtil.addMsg(msgList);
	}
	
	/**
	 * 根据汇票号查询对应业务专员id
	 * @param fields
	 */
	public static String findUserIdByDraftNum(String draftNum){
		return getService().findUserIdByDraftNum(draftNum);
	}
	public static String findUserIdByDealerId(int dealerId){
		return getService().findUserIdByDealerId(dealerId);
	}
	/**
	 * 根据汇票号查询对应监管员id
	 * @param fields
	 */
	public static String findSuperviseIdByDraftNum(String draftNum){
		return getService().findSuperviseIdByDraftNum(draftNum);
	}
	public static String findSuperviseIdByDealerId(int dealerId){
		return getService().findSuperviseIdByDealerId(dealerId);
	}
	public static String findMarketIdByDealerId(int dealerId){
		return getService().findMarketIdByDealerId(dealerId);
	}
	public static String findMarketIdByDraftNum(String draft_num){
		return getService().findMarketIdByDraftNum(draft_num);
	}
	
	/**
	 * 根据角色id获取用户id
	 * @param roleIds
	 * @return
	 */
	public static String findUserIdByRole(int...roleIds){
		String[] roles = null;
		if (roleIds.length > 0) {
			roles = new String[roleIds.length];
			for (int i = 0; i < roles.length; i++) {
				roles[i] = roleIds[i]+"";
			}
		}
		return getService().findUserIdByRole(roles);
	}
	
	public static void setAccessible(Field ...fields){
		if (null != fields && fields.length > 0) {
			for (Field field : fields) {
				field.setAccessible(true);
			}
		}
	}
	
	
	public static void updInfo(String ids,UserVO user,int flag) throws Exception{
		
		switch (flag) {
		case 1:
			updInfo(ids, user, flag);
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		default:
			break;
		}
		
	}
}

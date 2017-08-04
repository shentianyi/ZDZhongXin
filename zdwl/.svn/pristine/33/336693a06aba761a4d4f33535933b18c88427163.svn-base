package com.zd.csms.messageAndWaring.message.dao;
import java.util.List;
import com.zd.core.IDAO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.messageAndWaring.message.model.MessageInfoUserVO;
import com.zd.csms.messageAndWaring.message.model.WaringInfoUserVO;
public interface IMessageManagerDAO extends IDAO {
	// 获取用户的某个消息群组
	public MessageVO getMessageGroupByUserId(int userId, int msgType);

	//某个消息类别下的消息是否全读
	public boolean isAllReadMessage(int userId, int msgType);
	
	//某个预警类别下的消息是否全读
	public boolean isAllReadWaring(int userId, int msgType);
	
	//已读某条消息
    public boolean readMessage(int useId, String msgInfoIds);
	//已读预警
    public boolean readWaring(int useId, String msgInfoIds);
	
	//屏蔽预警
	public boolean shieldWaring(int useId, String msgInfoIds);
	
    //批量绑定用户与提醒
	public boolean addMessageUserBatch(List<MessageInfoUserVO> msgUsers);
	
	//批量绑定用户与预警
	public boolean addWaringUserBatch(List<WaringInfoUserVO> waringUsers);

	// 批量新增消息和预警
	boolean addMessageBath(List<?> msgInfos);
}

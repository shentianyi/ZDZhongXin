package com.zd.csms.messageAndWaring.message.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.zd.core.Constants;
import com.zd.csms.message.contant.MessageTypeContant;

public class MessageTemplateUtil {
	public static final Map<String,String> queryMap=new HashMap<String, String>() ;
	public static final Map<String,String> columnMap=new HashMap<String, String>() ;
	public static final Map<Integer,String> keyMap=new HashMap<Integer, String>() ;
	
	static{
		addKey();
		addQuery();
		addColumn();
	}
	
	private MessageTemplateUtil() {
		
	}

	public static String  getQueryTemplate(int msgType) {
		String key=keyMap.get(msgType) ;
		return queryMap.get(key);
	}
	
	public static String getColumnTemplate(int msgType) {
		String key=keyMap.get(msgType) ; 
		return columnMap.get(key);
	}
	
    private static void addQuery(){
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(
					System.getProperty(Constants.ROOT_WEBAPP.getCode())
							+ Constants.ROOT_CONFIG.getCode()
							+ "messageAndWaring/queryTemplate.properties"));
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		queryMap.putAll((Map) prop);

	}
    
    private static void addColumn() {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in =new FileInputStream(new File(
					System.getProperty(Constants.ROOT_WEBAPP.getCode())
							+ Constants.ROOT_CONFIG.getCode()
							+ "messageAndWaring/columnTemplate.properties"));
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		columnMap.putAll((Map)prop);

	}
    
    private static void addKey() {
    	//市场部
    	keyMap.put(MessageTypeContant.SEVENDAYSNONREGULATORYFEE.getCode(),  "SEVENDAYSNONREGULATORYFEE");
    	keyMap.put(MessageTypeContant.SUPERVISIONFEE30DAYSAGO.getCode(),  "SUPERVISIONFEE30DAYSAGO");
    	keyMap.put(MessageTypeContant.THREEDAYSAFTERNOBILLING.getCode(),  "THREEDAYSAFTERNOBILLING");
    	keyMap.put(MessageTypeContant.PROJECTINAGREEMENTNOTRECOVERED.getCode(), "PROJECTINAGREEMENTNOTRECOVERED");
    	keyMap.put(MessageTypeContant.SUPERVISIONAGREEMENT30DAYSAGO.getCode(),  "SUPERVISIONAGREEMENT30DAYSAGO");
    	keyMap.put(MessageTypeContant.AGREEMENTSIGNING10DAYNOTRECOVERED.getCode(), "AGREEMENTSIGNING10DAYNOTRECOVERED");
    	keyMap.put(MessageTypeContant.AGREEMENTSIGNING15DAYNOTRANSFER.getCode(), "AGREEMENTSIGNING15DAYNOTRANSFER");
    	keyMap.put(MessageTypeContant.BUSINESSIN3DAYSAGONOTPERSONNEL.getCode(), "BUSINESSIN3DAYSAGONOTPERSONNEL");
    	keyMap.put(MessageTypeContant.NO5CITICBANKSUPERVISIONFEE.getCode(),  "NO5CITICBANKSUPERVISIONFEE");
    	keyMap.put(MessageTypeContant.PROJECTIN.getCode(), "PROJECTIN");
    	keyMap.put(MessageTypeContant.BUSINESSIN.getCode(), "BUSINESSIN");
    	keyMap.put(MessageTypeContant.PROJECTOUT.getCode(), "PROJECTOUT");
    	keyMap.put(MessageTypeContant.PROJECTBINDING.getCode(), "PROJECTBINDING");
    	keyMap.put(MessageTypeContant.PROJECTUNBUNDLING.getCode(), "PROJECTUNBINDING");
    	
    	keyMap.put(MessageTypeContant.FIFTEENDAYSNONREGULATORYFEE.getCode(), "FIFTEENDAYSNONREGULATORYFEE");
    	keyMap.put(MessageTypeContant.SUPERVISIONFEE10DAYSAGO.getCode(),  "SUPERVISIONFEE10DAYSAGO");
    	keyMap.put(MessageTypeContant.TENDAYSAFTERNOBILLING.getCode(),  "TENDAYSAFTERNOBILLING");
    	keyMap.put(MessageTypeContant.EXPIRENOFEE.getCode(),  "EXPIRENOFEE");
    	keyMap.put(MessageTypeContant.FIFTEENDAYSAGREEMENTNOTRECOVERED.getCode(),  "FIFTEENDAYSAGREEMENTNOTRECOVERED");
    	keyMap.put(MessageTypeContant.AGREEMENT15DAYSAGO.getCode(),  "AGREEMENT15DAYSAGO");
    	keyMap.put(MessageTypeContant.AGREEMENTNOSIGNING.getCode(),  "AGREEMENTNOSIGNING");
    	keyMap.put(MessageTypeContant.BUSINESSINONEDAYSAGONOTPERSONNEL.getCode(),  "BUSINESSINONEDAYSAGONOTPERSONNEL");
    	
    	//业务部
    	keyMap.put(MessageTypeContant.BILLNOCARFIFTEENWARING.getCode(), "BILLNOCARFIFTEENWARING");
    	keyMap.put(MessageTypeContant.BILLFORTYFIVENOFULLWARING.getCode(),  "BILLFORTYFIVENOFULLWARING");
    	keyMap.put(MessageTypeContant.BILLNODRAFTNOWDATEWARING.getCode(),  "BILLNODRAFTNOWDATEWARING");
    	keyMap.put(MessageTypeContant.ZEROSKUZERODRAFTWARING.getCode(),  "ZEROSKUZERODRAFTWARING");
    	keyMap.put(MessageTypeContant.FIVEDAYNOBUSINESSWARING.getCode(),  "FIVEDAYNOBUSINESSWARING");
    	keyMap.put(MessageTypeContant.MOVEEXCEEDTHIRTYNOPROCESSWARING.getCode(),  "MOVEEXCEEDTHIRTYNOPROCESSWARING");
    	keyMap.put(MessageTypeContant.EXCEPTIONCAREXCEEDSKUTWENTYWARING.getCode(),  "EXCEPTIONCAREXCEEDSKUTWENTYWARING");
    	
    	
    	
    	keyMap.put(MessageTypeContant.MSGBILLNOCARREMIND.getCode(), "MSGBILLNOCARREMIND");
    	keyMap.put(MessageTypeContant.MSGBILLNOFULLREMIND.getCode(),  "MSGBILLNOFULLREMIND");
    	keyMap.put(MessageTypeContant.MSGBILLNODRAFTREMIND.getCode(),  "MSGBILLNODRAFTREMIND");
    	keyMap.put(MessageTypeContant.MSGZEROSKUZERODRAFTREMIND.getCode(),  "MSGZEROSKUZERODRAFTREMIND");
    	keyMap.put(MessageTypeContant.MSGTHREEDAYNOBUSINESSREMIND.getCode(),  "MSGTHREEDAYNOBUSINESSREMIND");
    	keyMap.put(MessageTypeContant.MSGMOVEEXCEEDTWENTYFIVENOPROCESSREMIND.getCode(),  "MSGMOVEEXCEEDTWENTYFIVENOPROCESSREMIND");
    	keyMap.put(MessageTypeContant.MSGMOVECARTEXCEEDKURTWENTYEMIND.getCode(),  "MSGMOVECARTEXCEEDKURTWENTYEMIND");
    	keyMap.put(MessageTypeContant.MSGEXCEPTIONCAREXCEEDSKUFIFTEENREMIND.getCode(),  "MSGEXCEPTIONCAREXCEEDSKUFIFTEENREMIND");
    
    	keyMap.put(MessageTypeContant.BANKMOVESTORAGEREMIND.getCode(),  "BANKMOVESTORAGEREMIND");
    	keyMap.put(MessageTypeContant.BANKOUTSTORAGEREMIND.getCode(),  "BANKOUTSTORAGEREMIND");
    	keyMap.put(MessageTypeContant.MSGLASTBUSINESSREMIND.getCode(),  "MSGLASTBUSINESSREMIND");
    }
    
    
}

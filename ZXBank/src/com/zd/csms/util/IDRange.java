package com.zd.csms.util;

public class IDRange {
	public static final int IDTYPE_PK = 0;
	public static final int IDTYPE_COLUMNID = 1;

	private int beginId;
	private int endId;
	//private int idType;
	private String tableName;
	private String dbName;

	public int getBeginId() {
		return beginId;
	}

	public void setBeginId(int beginId) {
		this.beginId = beginId;
	}

	public int getEndId() {
		return endId;
	}

	public void setEndId(int endId) {
		this.endId = endId;
	}

	/*public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}*/

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}

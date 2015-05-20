package com.netdimen.dao;

public class DBUser extends DBObject{

	private String appraiserId;
	private String LEVEL1ID;
	private String LEVEL2ID;
	private String LEVEL3ID;
	private String LEVEL4ID;
	private String LEVEL5ID;
	private String COSTCENTER;
	private String USERROLE;
	private String Current_ORGID;
	private String Parent_ORGID;
	private String appraiserId_EKPID;
	private String DOMAIN_ID;
	private String OrgCode;
	private String OrgDescription;
	private String Time_zone;
	private String Domain_name;
	private String User_ID;
	

	public String getOrgCode() {
		return OrgCode;
	}


	public String getTime_zone() {
		return Time_zone;
	}


	public void setTime_zone(String time_zone) {
		Time_zone = time_zone;
	}


	public String getDomain_name() {
		return Domain_name;
	}


	public void setDomain_name(String domain_name) {
		Domain_name = domain_name;
	}


	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}


	public String getOrgDescription() {
		return OrgDescription;
	}


	public void setOrgDescription(String orgDescription) {
		OrgDescription = orgDescription;
	}


	public DBUser() {
		// TODO Auto-generated constructor stub
	}


	public String getAppraiserId() {
		return appraiserId;
	}


	public void setAppraiserId(String appraiserId) {
		this.appraiserId = MapDBValue(appraiserId);
	}


	public String getLEVEL1ID() {
		return LEVEL1ID;
	}


	public void setLEVEL1ID(String lEVEL1ID) {
		LEVEL1ID = MapDBValue(lEVEL1ID);
	}


	public String getLEVEL2ID() {
		return LEVEL2ID;
	}


	public void setLEVEL2ID(String lEVEL2ID) {
		LEVEL2ID =  MapDBValue(lEVEL2ID);
	}


	public String getLEVEL3ID() {
		return LEVEL3ID;
	}


	public void setLEVEL3ID(String lEVEL3ID) {
		LEVEL3ID = MapDBValue(lEVEL3ID);
	}


	public String getLEVEL4ID() {
		return LEVEL4ID;
	}


	public void setLEVEL4ID(String lEVEL4ID) {
		LEVEL4ID = MapDBValue(lEVEL4ID);
	}


	public String getLEVEL5ID() {
		return LEVEL5ID;
	}


	public void setLEVEL5ID(String lEVEL5ID) {
		LEVEL5ID =  MapDBValue(lEVEL5ID);
	}


	public String getCOSTCENTER() {
		return COSTCENTER;
	}


	public void setCOSTCENTER(String cOSTCENTER) {
		COSTCENTER =MapDBValue(cOSTCENTER);
	}


	public String getUSERROLE() {
		return USERROLE;
	}


	public void setUSERROLE(String uSERROLE) {
		USERROLE = uSERROLE;
	}


	public String getCurrent_ORGID() {
		return Current_ORGID;
	}


	public void setCurrent_ORGID(String current_ORGID) {
		Current_ORGID = current_ORGID;
	}


	public String getParent_ORGID() {
		return Parent_ORGID;
	}


	public void setParent_ORGID(String parent_ORGID) {
		Parent_ORGID = parent_ORGID;
	}


	public String getAppraiserId_EKPID() {
		return appraiserId_EKPID;
	}


	public void setAppraiserId_EKPID(String appraiserId_EKPID) {
		this.appraiserId_EKPID = MapDBValue(appraiserId_EKPID);
	}


	public String getDOMAIN_ID() {
		return DOMAIN_ID;
	}


	public void setDOMAIN_ID(String dOMAIN_ID) {
		DOMAIN_ID = dOMAIN_ID;
	}


	public String getUser_ID() {
		return User_ID;
	}


	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}


}

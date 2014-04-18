package com.syniverse.xroads.notification.model.remotedata;

import java.io.Serializable;

public class OrgInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orgId;
	private String orgName;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}

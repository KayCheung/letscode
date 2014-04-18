package com.syniverse.xroads.notification.model.remotedata;

import java.io.Serializable;

public class PermInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String permId;
	private String permName;

	public String getPermId() {
		return permId;
	}

	public void setPermId(String permId) {
		this.permId = permId;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

}

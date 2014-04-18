package com.syniverse.xroads.notification.model.remotedata;

import java.io.Serializable;

public class RoleInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}

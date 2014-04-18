package com.syniverse.xroads.notification.model.remotedata;

import java.util.List;

public interface RemoteDateService {
	/**
	 * Get organizations which are using <code>prod</code>
	 * 
	 * @param prod
	 * @param curXroadsUserId
	 * @return
	 */
	List<OrgInfo> getOrgByProduct(ProductInfo prod, String curXroadsUserId);

	/**
	 * Get MCT users who belong to <code>org</code>
	 * 
	 * @param org
	 * @param curXroadsUserId
	 * @return
	 */
	List<MctUserInfo> getMctUserByOrg(OrgInfo org, String curXroadsUserId);

	/**
	 * Get Products which are being used by <code>org</code>
	 * 
	 * @param org
	 * @param curXroadsUserId
	 * @return
	 */
	List<ProductInfo> getProductByOrg(OrgInfo org, String curXroadsUserId);

	/**
	 * Get roles which have been assigned to <code>org</code>
	 * 
	 * @param org
	 * @param curXroadsUserId
	 * @return
	 */
	List<RoleInfo> getRoleByOrg(OrgInfo org, String curXroadsUserId);

	/**
	 * Get permissions which have been assigned to <code>org</code>
	 * 
	 * @param org
	 * @param curXroadsUserId
	 * @return
	 */
	List<PermInfo> getPermByOrg(OrgInfo org, String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>org</code>
	 * 
	 * @param org
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrg(OrgInfo org, String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>org</code> and are also assigned
	 * <code>role</code>
	 * 
	 * @param org
	 * @param role
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrgAndRole(OrgInfo org, RoleInfo role,
			String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>org</code> and are also assigned
	 * <code>perm</code>
	 * 
	 * @param org
	 * @param perm
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrgAndPerm(OrgInfo org, PermInfo perm,
			String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>org</code> and also are using
	 * <code>prod</code>
	 * 
	 * 
	 * @param org
	 * @param prod
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrgAndProduct(OrgInfo org,
			ProductInfo prod, String curXroadsUserId);
}

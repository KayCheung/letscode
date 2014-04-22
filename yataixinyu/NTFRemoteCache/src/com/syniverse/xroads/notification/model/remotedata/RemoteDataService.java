package com.syniverse.xroads.notification.model.remotedata;

import java.util.List;

public interface RemoteDataService {
	/**
	 * Get organizations which are using <code>prodId</code>
	 * 
	 * @param prodId
	 * @param curXroadsUserId
	 * @return
	 */
	List<OrgInfo> getOrgByProduct(String prodId, String curXroadsUserId);

	/**
	 * Get MCT users who belong to <code>orgId</code>
	 * 
	 * @param orgId
	 * @param curXroadsUserId
	 * @return
	 */
	List<MctUserInfo> getMctUserByOrg(String orgId, String curXroadsUserId);

	/**
	 * Get Products which are being used by <code>orgId</code>
	 * 
	 * @param orgId
	 * @param curXroadsUserId
	 * @return
	 */
	List<ProductInfo> getProductByOrg(String orgId, String curXroadsUserId);

	/**
	 * Get roles which have been assigned to <code>orgId</code>
	 * 
	 * @param orgId
	 * @param curXroadsUserId
	 * @return
	 */
	List<RoleInfo> getRoleByOrg(String orgId, String curXroadsUserId);

	/**
	 * Get permissions which have been assigned to <code>orgId</code>
	 * 
	 * @param orgId
	 * @param curXroadsUserId
	 * @return
	 */
	List<PermInfo> getPermByOrg(String orgId, String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>orgId</code>
	 * 
	 * @param orgId
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrg(String orgId, String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>orgId</code> and are also assigned
	 * <code>roleId</code>
	 * 
	 * @param orgId
	 * @param roleId
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrgAndRole(String orgId, String roleId,
			String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>orgId</code> and are also assigned
	 * <code>permId</code>
	 * 
	 * @param orgId
	 * @param permId
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrgAndPerm(String orgId, String permId,
			String curXroadsUserId);

	/**
	 * Get Crossroads users who belong to <code>orgId</code> and also are using
	 * <code>prodId</code>
	 * 
	 * 
	 * @param orgId
	 * @param prodId
	 * @param curXroadsUserId
	 * @return
	 */
	List<XroadsUserInfo> getXroadsUserByOrgAndProduct(String orgId,
			String prodId, String curXroadsUserId);
}

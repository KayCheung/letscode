package com.syniverse.xroads.notification.model.remotedata;

import java.util.Arrays;
import java.util.List;

public class RemoteDataServiceImpl implements RemoteDataService {
	private static RemoteDataService INSTANCE = new RemoteDataServiceImpl();

	static RemoteDataService getService() {
		return INSTANCE;
	}

	@Override
	public List<OrgInfo> getOrgByProduct(String prodId,
			String curXroadsUserId) {

		return Arrays.asList(new OrgInfo[] { DummyData.org1, DummyData.org2,
				DummyData.org3 });
	}

	@Override
	public List<MctUserInfo> getMctUserByOrg(String orgId, String curXroadsUserId) {
		return Arrays.asList(new MctUserInfo[] { DummyData.mctUser1,
				DummyData.mctUser2, DummyData.mctUser3 });

	}

	@Override
	public List<ProductInfo> getProductByOrg(String orgId, String curXroadsUserId) {
		return Arrays.asList(new ProductInfo[] { DummyData.prod1,
				DummyData.prod2, DummyData.prod3 });

	}

	@Override
	public List<RoleInfo> getRoleByOrg(String orgId, String curXroadsUserId) {
		return Arrays.asList(new RoleInfo[] { DummyData.role1, DummyData.role2,
				DummyData.role3 });

	}

	@Override
	public List<PermInfo> getPermByOrg(String orgId, String curXroadsUserId) {
		return Arrays.asList(new PermInfo[] { DummyData.perm1, DummyData.perm2,
				DummyData.perm3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrg(String orgId,
			String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrgAndRole(String orgId,
			String roleId, String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrgAndPerm(String orgId,
			String permId, String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrgAndProduct(String orgId,
			String prodId, String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

}

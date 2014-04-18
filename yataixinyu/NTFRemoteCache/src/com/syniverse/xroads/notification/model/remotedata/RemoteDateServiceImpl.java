package com.syniverse.xroads.notification.model.remotedata;

import java.util.Arrays;
import java.util.List;

public class RemoteDateServiceImpl implements RemoteDateService {
	private static RemoteDateService INSTANCE = new RemoteDateServiceImpl();

	static RemoteDateService getService() {
		return INSTANCE;
	}

	@Override
	public List<OrgInfo> getOrgByProduct(ProductInfo prod,
			String curXroadsUserId) {

		return Arrays.asList(new OrgInfo[] { DummyData.org1, DummyData.org2,
				DummyData.org3 });
	}

	@Override
	public List<MctUserInfo> getMctUserByOrg(OrgInfo org, String curXroadsUserId) {
		return Arrays.asList(new MctUserInfo[] { DummyData.mctUser1,
				DummyData.mctUser2, DummyData.mctUser3 });

	}

	@Override
	public List<ProductInfo> getProductByOrg(OrgInfo org, String curXroadsUserId) {
		return Arrays.asList(new ProductInfo[] { DummyData.prod1,
				DummyData.prod2, DummyData.prod3 });

	}

	@Override
	public List<RoleInfo> getRoleByOrg(OrgInfo org, String curXroadsUserId) {
		return Arrays.asList(new RoleInfo[] { DummyData.role1, DummyData.role2,
				DummyData.role3 });

	}

	@Override
	public List<PermInfo> getPermByOrg(OrgInfo org, String curXroadsUserId) {
		return Arrays.asList(new PermInfo[] { DummyData.perm1, DummyData.perm2,
				DummyData.perm3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrg(OrgInfo org,
			String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrgAndRole(OrgInfo org,
			RoleInfo role, String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrgAndPerm(OrgInfo org,
			PermInfo perm, String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

	@Override
	public List<XroadsUserInfo> getXroadsUserByOrgAndProduct(OrgInfo org,
			ProductInfo prod, String curXroadsUserId) {
		return Arrays.asList(new XroadsUserInfo[] { DummyData.xroadsUser1,
				DummyData.xroadsUser2, DummyData.xroadsUser3 });

	}

}

package com.syniverse.xroads.notification.model.remotedata;

public class DummyData {
	public static OrgInfo org1;
	public static OrgInfo org2;
	public static OrgInfo org3;

	public static ProductInfo prod1;
	public static ProductInfo prod2;
	public static ProductInfo prod3;

	public static PermInfo perm1;
	public static PermInfo perm2;
	public static PermInfo perm3;

	public static RoleInfo role1;
	public static RoleInfo role2;
	public static RoleInfo role3;

	public static MctUserInfo mctUser1;
	public static MctUserInfo mctUser2;
	public static MctUserInfo mctUser3;

	public static XroadsUserInfo xroadsUser1;
	public static XroadsUserInfo xroadsUser2;
	public static XroadsUserInfo xroadsUser3;

	static {
		org1 = new OrgInfo();
		org1.setOrgId("Org01");
		org1.setOrgName("Verizon");

		org2 = new OrgInfo();
		org1.setOrgId("Org02");
		org1.setOrgName("China Unicom");

		prod1 = new ProductInfo();
		prod1.setProdId("RTI");
		prod1.setProdName("Real Time Intelligence");

		prod2 = new ProductInfo();
		prod2.setProdId("CSP");
		prod3.setProdName("Common Subscriber Profile");

		prod3 = new ProductInfo();
		prod3.setProdId("PCS");
		prod3.setProdName("Premium Customer Service");

		perm1 = new PermInfo();
		perm1.setPermId("P000001");
		perm1.setPermName("Configure RTI");

		perm2 = new PermInfo();
		perm2.setPermId("P000002");
		perm2.setPermName("Configure CSP");

		perm3 = new PermInfo();
		perm3.setPermId("P000003");
		perm3.setPermName("Configure PCS");

		role1 = new RoleInfo();
		role1.setRoleId("R999999");
		role1.setRoleName("Administrator of RTI");

		role2 = new RoleInfo();
		role2.setRoleId("R888888");
		role2.setRoleName("Administrator of CSP");

		role3 = new RoleInfo();
		role3.setRoleId("R777777");
		role3.setRoleName("Administrator of PCS");

		mctUser1 = new MctUserInfo();
		mctUser1.setUserId("MCT01");
		mctUser1.setUserName("Tom");
		mctUser1.setMobile("021-659-850");
		mctUser1.setEmail("Tom@syniverse.com");

		mctUser2 = new MctUserInfo();
		mctUser2.setUserId("MCT02");
		mctUser2.setUserName("Jerry");
		mctUser2.setMobile("021-659-851");
		mctUser2.setEmail("Jerry@syniverse.com");

		mctUser3 = new MctUserInfo();
		mctUser3.setUserId("MCT03");
		mctUser3.setUserName("Kitty");
		mctUser3.setMobile("021-659-852");
		mctUser3.setEmail("Kitty@syniverse.com");

		xroadsUser1 = new XroadsUserInfo();
		xroadsUser1.setUserId("XRD01");
		xroadsUser1.setUserName("IronMan");
		xroadsUser1.setMobile("025-659-000");
		xroadsUser1.setEmail("IronMan@syniverse.com");

		xroadsUser2 = new XroadsUserInfo();
		xroadsUser2.setUserId("XRD02");
		xroadsUser2.setUserName("SpiderMan");
		xroadsUser2.setMobile("025-659-001");
		xroadsUser2.setEmail("SpiderMan@syniverse.com");

		xroadsUser3 = new XroadsUserInfo();
		xroadsUser3.setUserId("XRD03");
		xroadsUser3.setUserName("CarMan");
		xroadsUser3.setMobile("025-659-002");
		xroadsUser3.setEmail("CarMan@syniverse.com");

	}

}

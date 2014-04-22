/**
 * @author marvin
 * 
 */
public class MaxTenuringThreshold {
	private static final int _1M = 1024 * 1024;

	/**
	 * 
	 * <pre>
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 * </pre>
	 * 
	 * 1. Marvin，你现在好像很困哎。是啊，妈的，昨天睡的晚，不爽的是，待会到了11点，有可能又不困了，搽的
	 * 
	 * 2. 发现了一个规律："-XX:"的JVM参数，（嘻嘻，+和= 不同时 出现）
	 * 
	 * MaxTenuringThreshold 控制着 “熬过几次 Minor GC”，对象才能从 new gen promote到 tenured
	 * gen
	 * 
	 */
	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		
		allocation1 = new byte[_1M / 4];

		allocation2 = new byte[4 * _1M];
		
		System.out.println("First Minor GC");//GC完成后，两个 4M 到了 tenured gen（因为 Survivor放不开）。256k留在 Survivor
		allocation3 = new byte[4 * _1M];
		System.out.println("End First Minor GC");
		
		allocation3 = null;
		System.out.println("Last 4M begin");
		allocation3 = new byte[4 * _1M];
		System.out.println("Last 4M end");

	}

	public static void main(String[] args) {
		testTenuringThreshold();
	}
}

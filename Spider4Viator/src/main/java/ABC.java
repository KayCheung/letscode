
public class ABC {
	@SuppressWarnings("unchecked")
	public <T> T getRspMsg(Object msgResponseVO) {
		return (T)new Object();
	}
	
	@SuppressWarnings("unchecked")
	public <X> X getRspMsag(Object msgResponseVO) {
		return (X)new Object();
	}
	public static void main(String[] args) {
		ABC d = new ABC();
		Integer t = d.getRspMsag(d);
		String x = d.getRspMsag(d);
		System.out.println(t);
		System.out.println(x);
	}
}

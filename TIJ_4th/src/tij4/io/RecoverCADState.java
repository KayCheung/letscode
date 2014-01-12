package tij4.io;

//: io/RecoverCADState.java
// Restoring the state of the pretend CAD system.
// {RunFirst: StoreCADState}
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"CADState.out"));
		// Read in the same order they were written:
		// 1. Marvin: 读出 shapeTypes
		List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in
				.readObject();
		// 2. Marvin: 读出 一个 int
		Line.deserializeStaticState(in);
		// 3. Marvin: 读出 shapes
		// 4. Marvin: 反序列化时，这里read的顺序，和 StoreCADState.java 中 write 的顺序保持严格一致
		List<Shape> shapes = (List<Shape>) in.readObject();
		System.out.println(shapes);
	}
}
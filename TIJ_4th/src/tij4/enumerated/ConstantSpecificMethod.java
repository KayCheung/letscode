package tij4.enumerated;

//: enumerated/ConstantSpecificMethod.java
import java.text.DateFormat;
import java.util.Date;

public enum ConstantSpecificMethod {
	DATE_TIME {
		String getInfo() {
			return DateFormat.getDateInstance().format(new Date());
		}
	},
	CLASSPATH {
		String getInfo() {
			return System.getenv("CLASSPATH");
		}
	},
	VERSION {
		String getInfo() {
			return System.getProperty("java.version");
		}
	};
	abstract String getInfo();

	public static void main(String[] args) {
		for (ConstantSpecificMethod csm : values()) {
			// 当然，这里的 cms 就是 enum实例
			System.out.println(csm.getInfo());
		}
	}
} /* (Execute to see output) */// :~

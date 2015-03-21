package json2class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.studytrails.json.jackson.FetchDestination;

public class Json2Class {
	public static final String ENTER = "\r\n";
	public static final String FOUR_SPACE = "    ";
	public static final String fullPath = "D:/Eden/gitworkspace/letscode/Spider4Viator/src/main/java/json2class/test_json2class.json";

	private static NumberStrategy nmbStrategy = NumberStrategy.IntegerBigDecimal;

	public static void main(String[] args) throws Exception {
		file2Class(fullPath);
	}

	public static String file2Class(String jsonFullPath) throws Exception {
		File f = new File(jsonFullPath);
		String jsonFilename = f.getName();
		String clsName = jsonFilename;
		int dot = jsonFilename.lastIndexOf(".");
		if (dot != -1) {
			clsName = jsonFilename.substring(0, dot);
		}

		String javaFullPath = f.getParentFile().getAbsolutePath() + "/"
				+ jsonFilename + ".java";
		json2Class(readContent(jsonFullPath), clsName, javaFullPath);
		return javaFullPath;
	}

	public static void json2Class(String jsonText, String clsName,
			String javaFullPath) throws Exception {
		List<StringBuilder> listSB = new ArrayList<StringBuilder>();
		processObject(0, new ObjectMapper().readTree(jsonText), clsName, listSB);
		String javaText = mergeSB(listSB);
		writeContent(javaText, javaFullPath);
	}

	private static String mergeSB(List<StringBuilder> listSB) {
		StringBuilder sb0 = listSB.get(0);
		int lastBracket = sb0.lastIndexOf("}");
		sb0.replace(lastBracket, sb0.length(), "");
		for (int i = 1; i < listSB.size(); i++) {
			sb0.append(ENTER);
			sb0.append(listSB.get(i).toString());
		}
		sb0.append("}");
		return sb0.toString();
	}

	private static String clsIndent(int depth) {
		if (depth == 0) {
			return space(0);
		} else {
			return space(1);
		}
	}

	private static String varIndent(int depth) {
		if (depth == 0) {
			return space(1);
		} else {
			return space(3);
		}
	}

	private static String space(int depth) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < depth; i++) {
			sb.append(FOUR_SPACE);
		}
		return sb.toString();
	}

	private static void process_NULL_String_Boolean_Number(int depth,
			JsonNode jnode, String strNodeName, StringBuilder objSB) {
		objSB.append(varIndent(depth) + "private " + javaTypeByNodeType(jnode)
				+ " " + strNodeName + ";" + ENTER);
	}

	private static String javaTypeByNodeType(JsonNode jnode) {
		JsonNodeType jnt = jnode.getNodeType();
		switch (jnt) {
		case NULL:
		case STRING:
			return "String";
		case BOOLEAN:
			return "boolean";
		case NUMBER:
			return deriveTypeByNumber(jnode.asText(), nmbStrategy);
		default:
			break;
		}
		return "";
	}

	private static void processObject(int depth, JsonNode jnode,
			String strNodeName, List<StringBuilder> listSB) {
		StringBuilder objSB = new StringBuilder();
		listSB.add(objSB);
		String staticOrNot = depth == 0 ? "" : " static";
		objSB.append(clsIndent(depth) + "public" + staticOrNot + " class "
				+ upperFirstChar(strNodeName) + "{" + ENTER);

		// object node
		Iterator<Entry<String, JsonNode>> it = jnode.fields();
		while (it.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) it
					.next();
			process((depth + 1), entry.getValue(), entry.getKey(), objSB,
					listSB);
		}
		objSB.append(clsIndent(depth) + "}" + ENTER);
	}

	private static void processArray(int depth, JsonNode jnode,
			String strNodeName, StringBuilder objSB, List<StringBuilder> listSB) {
		ArrayNode an = (ArrayNode) jnode;
		if (an.size() == 0) {
			objSB.append(varIndent(depth) + "private List<String> "
					+ strNodeName + ";" + ENTER);
		} else {
			JsonNode ele = an.get(0);
			JsonNodeType jnt = ele.getNodeType();
			objSB.append(varIndent(depth) + "private List<"
					+ upperFirstChar(deriveArrayEleType(ele, strNodeName))
					+ "> " + strNodeName + ";" + ENTER);
			if (jnt == JsonNodeType.ARRAY || jnt == JsonNodeType.OBJECT) {
				process(depth + 1, ele, strNodeName, objSB, listSB);
			} else {
				// not array or object, do nothing
			}
		}
	}

	private static String deriveArrayEleType(JsonNode eleOfArray,
			String strNodeName) {
		JsonNodeType jnt = eleOfArray.getNodeType();
		if (jnt == JsonNodeType.OBJECT) {
			return strNodeName;
		} else if (jnt == JsonNodeType.ARRAY) {
			// not sure on array's element is still an array
			// like this: "tourGrades":[[k1:v1,k2:v2...],[k3:v3,k3:v3...]]
			// just return String. may fix it when I'm clear on this
			return "String";
		} else {
			String strEleOfArray = eleOfArray.asText();
			// number
			if (digitOrDot(strEleOfArray) == true) {
				return deriveTypeByNumber(strEleOfArray, nmbStrategy);
			}
			// String
			else {
				return "String";
			}
		}
	}

	private static boolean digitOrDot(String str) {
		if (empty(str)) {
			return false;
		}
		str = trimToEmpty(str);
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			// not digit, not dot
			if (!Character.isDigit(ch) && ch != '.') {
				return false;
			}
		}
		return true;
	}

	private static String deriveTypeByNumber(String number,
			NumberStrategy nmbStrategy) {
		if (empty(number)) {
			return "Integer";
		}
		// no dot
		if (number.indexOf(".") == -1) {
			return nmbStrategy.noDot();
		} else {
			return nmbStrategy.yesDot();
		}
	}

	private static void process(int depth, JsonNode jnode, String strNodeName,
			StringBuilder objSB, List<StringBuilder> listSB) {
		switch (jnode.getNodeType()) {
		case NULL:
		case STRING:
		case BOOLEAN:
		case NUMBER:
			process_NULL_String_Boolean_Number(depth, jnode, strNodeName, objSB);
			break;
		case OBJECT:
			processObject(depth, jnode, strNodeName, listSB);
			break;
		case ARRAY:
			processArray(depth, jnode, strNodeName, objSB, listSB);
			break;
		default:
			break;
		}
	}

	private static boolean empty(String str) {
		return str == null ? true : str.trim().equals("");
	}

	public static String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	private static String upperFirstChar(String str) {
		if (empty(str)) {
			return "";
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String readContent(String fullPath) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(fullPath));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(br);
		}
		return sb.toString();
	}

	public static void writeContent(String content, String fullPath) {
		BufferedWriter bw = createFileWriter(fullPath, false);
		try {
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(bw);
		}
	}

	public static BufferedWriter createFileWriter(String fullPath,
			boolean append) {
		try {
			new File(fullPath).getParentFile().mkdirs();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fullPath, append)));
			return bw;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(Closeable c) {
		try {
			c.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	enum NumberStrategy {
		IntegerBigDecimal {
			@Override
			public String noDot() {
				return "Integer";
			}

			@Override
			public String yesDot() {
				return "BigDecimal";
			}
		},
		BigIntegerBigDecimal {
			@Override
			public String noDot() {
				return "BigInteger";
			}

			@Override
			public String yesDot() {
				return "BigDecimal";
			}
		},
		BigIntegerDouble {
			@Override
			public String noDot() {
				return "BigInteger";
			}

			@Override
			public String yesDot() {
				return "Double";
			}
		},
		IntegerDouble {
			@Override
			public String noDot() {
				return "Integer";
			}

			@Override
			public String yesDot() {
				return "Double";
			}
		};
		public abstract String noDot();

		public abstract String yesDot();
	}
}

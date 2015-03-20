package com.studytrails.import2database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.studytrails.json.jackson.IOUtil;

public class JsonText2Class {
	public static final String ENTER = "\r\n";
	public static final String FOUR_SPACE = "    ";
	public static final String fullPath = "/home/marvin/Desktop/test_json2class.json";

	private static NumberStrategy nmbStrategy = NumberStrategy.IntegerBigDecimal;

	public static void main(String[] args) throws Exception {
		String jsonText = IOUtil.readContent(fullPath);
		json2Class(jsonText, "ViatorProductDetailInfo", "/home/marvin/Desktop");
	}

	public static void json2Class(String jsonText, String clsName, String folder)
			throws Exception {

		List<StringBuilder> listSB = new ArrayList<StringBuilder>();
		processObject(0, new ObjectMapper().readTree(jsonText), "myname",
				listSB);
		String javaText = mergeSB(listSB);

		String clsFullPath = folder + "/" + clsName + ".java";
		IOUtil.writeContent(javaText, clsFullPath);
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
			Map.Entry<java.lang.String, com.fasterxml.jackson.databind.JsonNode> entry = (Map.Entry<java.lang.String, com.fasterxml.jackson.databind.JsonNode>) it
					.next();
			process((depth + 1), entry.getValue(), entry.getKey(), objSB,
					listSB);
		}
		objSB.append(clsIndent(depth) + "}" + ENTER);
	}

	private static void processArray(int depth, JsonNode jnode,
			String strNodeName, StringBuilder objSB, List<StringBuilder> listSB) {
		ArrayNode an = (ArrayNode) jnode;
		if (an == null || an.size() == 0) {
			objSB.append(varIndent(depth) + "private List<String> "
					+ strNodeName + ";" + ENTER);
		} else {
			objSB.append(varIndent(depth) + "private List<"
					+ upperFirstChar(strNodeName) + "> " + strNodeName + ";"
					+ ENTER);
			JsonNode eleOfArray = an.get(0);
			if (eleOfArray.getNodeType() == JsonNodeType.ARRAY
					|| eleOfArray.getNodeType() == JsonNodeType.OBJECT) {
				process(depth + 1, an.get(0), strNodeName, objSB, listSB);
			} else {
				// not array or object, do nothing
			}
		}
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

	private static String upperFirstChar(String str) {
		if (empty(str)) {
			return "";
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
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

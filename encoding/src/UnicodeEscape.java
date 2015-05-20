public class UnicodeEscape {
	public static void main(String[] args) {
		// a
		String a_1byte = "\u0061";
		// ä
		String a_2byte = "\u00E4";
		// 退
		String tui_2bytes = "\u9000";
		// 𡯁
		String burenshi_4bytes = "\u21BC1";// 错误。只能跟4个hex

		String[] arr = new String[] { a_1byte, a_2byte, tui_2bytes,
				burenshi_4bytes };
		for (String str : arr) {
			System.out.println(str + "-->length=" + str.length());
		}

		// 数学符号
		String yeburenshi_4bytes = "\u1D56B";
		System.out.println(Integer.toHexString(yeburenshi_4bytes.charAt(0)));
		System.out.println(Integer.toHexString(yeburenshi_4bytes.charAt(1)));

		String str1 = new String(Character.toChars(0x1D56B));
		System.out.println(Integer.toHexString(str1.charAt(0)).toUpperCase());
		System.out.println(Integer.toHexString(str1.charAt(1)).toUpperCase());
		System.out.println(str1.codePointCount(0, str1.length()));
	}
}

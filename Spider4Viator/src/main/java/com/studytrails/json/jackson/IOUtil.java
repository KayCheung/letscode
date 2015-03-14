package com.studytrails.json.jackson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class IOUtil {

	public static <E> void concat(StringBuilder sb, List<E> list,
			String normal, String lastSpecial) {

		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).toString());
			if (i == list.size() - 1) {
				sb.append(lastSpecial);
			} else {
				sb.append(normal);
			}
		}

	}

	public static String readContent(String fullPath) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(fullPath));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(br);
		}
		return sb.toString();
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

	public static BufferedReader createFileReader(String fullPath) {
		try {
			new File(fullPath).getParentFile().mkdirs();
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			return br;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeList2File(BufferedWriter bw,
			List<DestinationInfo> list) throws Exception {
		if (list.size() <= 0) {
			return;
		}
		for (DestinationInfo destInfo : list) {
			bw.write(destInfo.toString());
			bw.newLine();
		}
		bw.flush();
	}

	public static void close(Closeable c) {
		try {
			c.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

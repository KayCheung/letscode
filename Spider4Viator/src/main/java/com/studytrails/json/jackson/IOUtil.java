package com.studytrails.json.jackson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.impl.DefaultBHttpClientConnection;

public class IOUtil {
	public static final int SO_TIMEOUT = 1000 * 300;

	public static String human(long value) {
		String str = value + "";
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			cnt++;
			if ((cnt == 3) && (i != 0)) {
				cnt = 0;
				sb.insert(0, "," + str.charAt(i));
			} else {
				sb.insert(0, str.charAt(i));
			}
		}
		return sb.toString();
	}

	public static String int2str(BigInteger bi, int finalLength) {
		StringBuilder sb = new StringBuilder(bi.toString());

		while (sb.length() < finalLength) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}

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
				sb.append(line + FetchDestination.ENTER);
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

	public static void bindSocketSolidly(DefaultBHttpClientConnection conn,
			HttpHost host) {
		if (!conn.isOpen()) {
			int tryCount = 0;
			while (tryCount <= 19) {
				try {
					Socket socket = new Socket(host.getHostName(),
							host.getPort());
					socket.setSoTimeout(SO_TIMEOUT);
					conn.bind(socket);
					// Good, success
					break;
				} catch (Exception e) {
					System.err.println(tryCount
							+ " time to connect to host failed");
					e.printStackTrace();
					tryCount++;
					System.err.println("Sleep and try again");

					try {
						Thread.sleep(1000 * 15);
						System.err.println("Just sleep for: " + (1000 * 15)
								+ " ms");
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		}
		// Good. Connectioni is open
		else {

		}
	}

	public static String getJarStayFolder_nologinfo(Class<?> cls) {
		String jarSelf = cls.getProtectionDomain().getCodeSource()
				.getLocation().getFile();
		try {
			jarSelf = java.net.URLDecoder.decode(jarSelf, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String jarStayFolder = new File(jarSelf).getParentFile()
				.getAbsolutePath();
		return jarStayFolder;
	}

	public static void main(String[] args) {
		System.out.println(getJarStayFolder_nologinfo(IOUtil.class));
	}
}

package com.studytrails.json.jackson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FetchAvailabilityTourgradesPricingmatrix {
	public static final String D_Q = "\"";
	public static final String ATP_URI = "/service/booking/availability/tourgrades/pricingmatrix?apiKey=7996631481948906";
	public static final String[] MONTHS = { "03", "04", "05" };

	public static int alreadyWriteDoneCount = 0;
	public static int folderSequence = 1;

	public static void main(String[] args) throws Exception {
		String infolog = FetchDestination.BASE_DIR + "atp_info.log";
		PrintStream psInfo = new PrintStream(new File(infolog));
		System.setOut(psInfo);

		String errorlog = FetchDestination.BASE_DIR + "atp_error.log";
		PrintStream psError = new PrintStream(new File(errorlog));
		System.setErr(psError);
		new FetchAvailabilityTourgradesPricingmatrix().fetchAll_ATP();
		psInfo.close();
		psError.close();
	}

	public void fetchAll_ATP() throws Exception {
		long begin = System.currentTimeMillis();
		HttpProcessor httpproc = HttpProcessorBuilder.create()
				.add(new RequestContent()).add(new RequestTargetHost())
				.add(new RequestConnControl())
				.add(new RequestUserAgent(FetchProductCode.FIRE_USER_AGENT))
				.add(new RequestExpectContinue(true)).build();

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpCoreContext coreContext = HttpCoreContext.create();
		HttpHost host = new HttpHost(FetchProductCode.VIATOR_HOST,
				FetchProductCode.VIATOR_PORT);
		coreContext.setTargetHost(host);

		DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(
				8 * 1024);
		ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

		List<String> listProductCode = FetchProductDetail.provideProductCode();
		doFetchAll(listProductCode, httpproc, httpexecutor, coreContext, host,
				conn, connStrategy);

		long cost = System.currentTimeMillis() - begin;
		String log = "FetchAvailabilityTourgradesPricingmatrix cost: "
				+ IOUtil.human(cost);
		System.out.println(log);
	}

	private void doFetchAll(List<String> listProductCode,
			HttpProcessor httpproc, HttpRequestExecutor httpexecutor,
			HttpCoreContext coreContext, HttpHost host,
			DefaultBHttpClientConnection conn,
			ConnectionReuseStrategy connStrategy) throws UnknownHostException,
			IOException, HttpException {
		try {
			for (int i = 0, totalProdCounts = listProductCode.size(); i < totalProdCounts; i++) {
				for (int m = 0; m < MONTHS.length; m++) {
					String month = MONTHS[m];
					IOUtil.bindSocketSolidly(conn, host);
					String prodCode = listProductCode.get(i);
					int tryCount = 0;
					HttpResponse response = null;
					while (tryCount <= 2) {
						System.out
								.println(tryCount
										+ " time, Availability_Tourgrades_Pricingmatrix: "
										+ prodCode);
						response = fetchWithoutRetry(i, totalProdCounts,
								prodCode, month, httpproc, httpexecutor,
								coreContext, conn);
						if (response == null) {
							tryCount++;
							conn.close();
							IOUtil.bindSocketSolidly(conn, host);
						} else {
							// good
							break;
						}
					}

					if (response == null) {
						conn.close();
					} else {
						if (!connStrategy.keepAlive(response, coreContext)) {
							conn.close();
						} else {
							// System.out.println("Connection kept alive...");
						}
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			conn.close();
		}
	}

	private HttpResponse fetchWithoutRetry(int prodCodeIndex,
			int totalProdCounts, String prodCode, String month,
			HttpProcessor httpproc, HttpRequestExecutor httpexecutor,
			HttpCoreContext coreContext, DefaultBHttpClientConnection conn)
			throws HttpException, IOException, JsonProcessingException {
		HttpResponse response;
		try {
			response = doFetchByProdCode(prodCodeIndex, totalProdCounts,
					prodCode, month, httpproc, httpexecutor, coreContext, conn);
		} catch (Exception e) {
			System.err
					.println("Error when Availability_Tourgrades_Pricingmatrix by product code: "
							+ prodCode);
			e.printStackTrace();
			response = null;
		}
		return response;
	}

	private HttpResponse doFetchByProdCode(int prodCodeIndex,
			int totalProdCounts, String prodCode, String month,
			HttpProcessor httpproc, HttpRequestExecutor httpexecutor,
			HttpCoreContext coreContext, DefaultBHttpClientConnection conn)
			throws HttpException, IOException, JsonProcessingException {
		long begin = System.currentTimeMillis();
		BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(
				"POST", ATP_URI);
		request.addHeader("User-Agent", FetchProductCode.FIRE_USER_AGENT);
		request.setEntity(createHttpEntity(prodCode, month));

		httpexecutor.preProcess(request, httpproc, coreContext);
		HttpResponse response = httpexecutor
				.execute(request, conn, coreContext);
		httpexecutor.postProcess(response, httpproc, coreContext);

		String strATP = EntityUtils.toString(response.getEntity());
		long cost = System.currentTimeMillis() - begin;
		String log = (prodCodeIndex + 1) + " / " + totalProdCounts
				+ ", Availability_Tourgrades_Pricingmatrix, cost: "
				+ IOUtil.human(cost) + ". By product code: " + prodCode
				+ ", month: " + month;
		System.out.println(log);
		write_ATP_toFile(prodCodeIndex, prodCode, month, strATP);
		return response;
	}

	private void write_ATP_toFile(int prodCodeIndex, String productCode,
			String month, String strATP) {
		try {
			String filename = IOUtil.int2str(new BigInteger(""
					+ (prodCodeIndex + 1)), 5)
					+ "_" + month + "_" + productCode + ".json";
			String fullPath = FetchDestination.BASE_DIR + "ATP/"
					+ folderSequence + "/" + filename;
			BufferedWriter bw = IOUtil.createFileWriter(fullPath, false);
			bw.write(strATP);
			IOUtil.close(bw);
			alreadyWriteDoneCount++;
			folderSequence = (alreadyWriteDoneCount / 3000) + 1;
			System.out.println(filename + " is written done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HttpEntity createHttpEntity(String prodCode, String month) {
		String entity = "{" + //
				D_Q + "productCode" + D_Q + ":" + D_Q + prodCode + D_Q + "," + //
				D_Q + "month" + D_Q + ":" + D_Q + month + D_Q + "," + //
				D_Q + "year" + D_Q + ":" + D_Q + "2015" + D_Q + "," + //
				D_Q + "currencyCode" + D_Q + ":" + D_Q + "USD" + D_Q + //
				"}";
		System.out.println(entity);
		StringEntity se = new StringEntity(entity, ContentType.APPLICATION_JSON);
		return se;
	}
}

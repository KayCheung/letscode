package com.studytrails.json.jackson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.message.BasicHttpRequest;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FetchProductDetail {
	public static final String PRODUCT_DETAIL_URI = "/service/product";

	public static int alreadyWriteDoneCount = 0;
	public static int folderSequence = 1;

	public static void main(String[] args) throws Exception {
		String infolog = FetchDestination.BASE_DIR + "info.log";
		PrintStream psInfo = new PrintStream(new File(infolog));
		System.setOut(psInfo);

		String errorlog = FetchDestination.BASE_DIR + "error.log";
		PrintStream psError = new PrintStream(new File(errorlog));
		System.setErr(psError);

		long begin = System.currentTimeMillis();
		// testParser();
		boolean bFromNetwork = true;
		reallyGetProductDetail(bFromNetwork);

		System.out.println("FetchProductDetail main() totally cost: "
				+ IOUtil.human(System.currentTimeMillis() - begin));
		psInfo.close();
		psError.close();
	}

	private static void testParser() throws Exception {
		List<ProductInfo> listProdInfo = new ArrayList<ProductInfo>();
		ObjectMapper mapper = new ObjectMapper();
		String[] filenames = { "productDetail_2280LI_5H.json",
				"productDetail_5034ROM17.json" };
		FetchProductDetail fpd = new FetchProductDetail();
		for (String fname : filenames) {
			listProdInfo.add(fpd.parseDetailToGetProductInfo(mapper,
					IOUtil.readContent(FetchDestination.BASE_DIR + fname)));
		}
		new Estimate().estimate(listProdInfo);
	}

	private static void reallyGetProductDetail(boolean bFromNetwork)
			throws Exception {
		new FetchProductDetail().letGetProductDetails(bFromNetwork);
	}

	public void letGetProductDetails(boolean bFromNetwork) throws Exception {
		List<String> listProductCode = null;
		if (bFromNetwork) {
			FetchDestination fd = new FetchDestination();
			FetchProductCode fpc = new FetchProductCode();
			List<DestinationInfo> listDI = fd.getAllDestination();
			listProductCode = fpc.fetchUniqueProductCode(listDI);
		} else {
			listProductCode = provideProductCode();
		}

		List<ProductInfo> listProdInfo = fetchAllProductDetail(listProductCode);
		write_ListProdInfo(listProdInfo);
		new Estimate().estimate(listProdInfo);
	}

	public static List<String> provideProductCode() {
		BufferedReader br = IOUtil.createFileReader(FetchDestination.BASE_DIR
				+ "uniqueProductCode.txt");
		List<String> listProductCode = new ArrayList<String>();
		String aProdCode = null;
		try {
			while ((aProdCode = br.readLine()) != null) {
				listProductCode.add(aProdCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(br);
		}
		System.out.println("provideProductCode gets product code count: "
				+ listProductCode.size());
		return listProductCode;
	}

	private void write_ListProdInfo(List<ProductInfo> listProdInfo) {
		BufferedWriter bw = IOUtil.createFileWriter(FetchDestination.BASE_DIR
				+ "allProductInfo.txt", false);
		for (ProductInfo prodInfo : listProdInfo) {
			try {
				bw.write(prodInfo.toString());
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IOUtil.close(bw);
	}

	public List<ProductInfo> fetchAllProductDetail(List<String> listProductCode)
			throws Exception {
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
				8 * 1024 * 1024);
		ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

		List<ProductInfo> listProdInfo = new ArrayList<ProductInfo>();

		doFetchAll(listProdInfo, listProductCode, httpproc, httpexecutor,
				coreContext, host, conn, connStrategy);

		long cost = System.currentTimeMillis() - begin;
		String log = "Product Details Code Counts: " + listProductCode.size()
				+ ", Fetch All Product Details cost: " + IOUtil.human(cost);
		System.out.println(log);
		return listProdInfo;
	}

	private void doFetchAll(List<ProductInfo> listProdInfo,
			List<String> listProductCode, HttpProcessor httpproc,
			HttpRequestExecutor httpexecutor, HttpCoreContext coreContext,
			HttpHost host, DefaultBHttpClientConnection conn,
			ConnectionReuseStrategy connStrategy) throws UnknownHostException,
			IOException, HttpException {
		try {
			// create an ObjectMapper instance.
			ObjectMapper mapper = new ObjectMapper();
			for (int i = 0, totalSize = listProductCode.size(); i < totalSize; i++) {
				IOUtil.bindSocketSolidly(conn, host);

				String productCode = listProductCode.get(i);
				HttpResponse response = null;
				int tryCount = 0;
				while (tryCount <= 2) {
					response = fetchWithoutRetry(listProdInfo, httpproc,
							httpexecutor, coreContext, conn, mapper, i,
							totalSize, productCode);

					if (response == null) {
						tryCount++;
						conn.close();
						IOUtil.bindSocketSolidly(conn, host);
					} else {
						// Good
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
			}
		} finally {
			conn.close();
		}
	}

	private HttpResponse fetchWithoutRetry(List<ProductInfo> listProdInfo,
			HttpProcessor httpproc, HttpRequestExecutor httpexecutor,
			HttpCoreContext coreContext, DefaultBHttpClientConnection conn,
			ObjectMapper mapper, int i, int totalSize, String productCode)
			throws HttpException, IOException, JsonProcessingException {
		HttpResponse response;
		try {
			response = doFetchByCode(i, totalSize, mapper, productCode,
					listProdInfo, httpproc, httpexecutor, coreContext, conn);
		} catch (Exception e) {
			System.err
					.println("Error when Get Product Detail By Product code: "
							+ productCode);
			e.printStackTrace();
			response = null;
		}
		return response;
	}

	private HttpResponse doFetchByCode(int prodCodeIndex, int totalProdCounts,
			ObjectMapper mapper, String productCode,
			List<ProductInfo> listProdInfo, HttpProcessor httpproc,
			HttpRequestExecutor httpexecutor, HttpCoreContext coreContext,
			DefaultBHttpClientConnection conn) throws HttpException,
			IOException, JsonProcessingException {
		long begin = System.currentTimeMillis();

		BasicHttpRequest request = new BasicHttpRequest("GET",
				createURI(productCode));

		httpexecutor.preProcess(request, httpproc, coreContext);
		HttpResponse response = httpexecutor
				.execute(request, conn, coreContext);
		httpexecutor.postProcess(response, httpproc, coreContext);

		String productDetail = EntityUtils.toString(response.getEntity());
		write_ProductDetail_toFile(prodCodeIndex, productCode, productDetail);
		ProductInfo prodInfo = parseDetailToGetProductInfo(mapper,
				productDetail);
		listProdInfo.add(prodInfo);

		long cost = System.currentTimeMillis() - begin;
		String log = (prodCodeIndex + 1) + " / " + totalProdCounts
				+ ", Get Product Detail By Code cost: " + IOUtil.human(cost)
				+ ". Product: " + prodInfo;
		System.out.println(log);

		return response;
	}

	private void write_ProductDetail_toFile(int prodCodeIndex,
			String productCode, String productDetail) {
		try {
			String filename = IOUtil.int2str(new BigInteger(""
					+ (prodCodeIndex + 1)), 5)
					+ "_" + productCode + ".json";
			String fullPath = FetchDestination.BASE_DIR + "data/"
					+ folderSequence + "/" + filename;
			BufferedWriter bw = IOUtil.createFileWriter(fullPath, false);
			bw.write(productDetail);
			IOUtil.close(bw);
			alreadyWriteDoneCount++;
			folderSequence = (alreadyWriteDoneCount / 1000) + 1;
			System.out.println(filename + " is written done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String createURI(String productCode) {
		String uri = "/service/product?currencyCode=USD&apiKey=7996631481948906&code="
				+ productCode;
		return uri;
	}

	private ProductInfo parseDetailToGetProductInfo(ObjectMapper mapper,
			String productDetail) throws IOException, JsonProcessingException {
		long begin = System.currentTimeMillis();
		// use the ObjectMapper to read the json string and create a tree
		JsonNode rootNode = mapper.readTree(productDetail);
		// Lets look at the dataset now.
		JsonNode dataset = rootNode.get("data");

		Iterator<String> dataFieldsName = dataset.fieldNames();
		ProductInfo prodInfo = new ProductInfo();
		while (dataFieldsName.hasNext()) {
			// direct child of "data"
			String aDataFieldName = dataFieldsName.next();
			JsonNode aDataFieldNode = dataset.get(aDataFieldName);
			String value = aDataFieldNode.asText();

			if ("code".equals(aDataFieldName)) {
				prodInfo.code = value;
				continue;
			}
			if ("title".equals(aDataFieldName)) {
				prodInfo.title = value;
				continue;
			}
			if ("shortTitle".equals(aDataFieldName)) {
				prodInfo.shortTitle = value;
				continue;
			}
			if ("shortDescription".equals(aDataFieldName)) {
				prodInfo.shortDescription = value;
				continue;
			}
			if ("location".equals(aDataFieldName)) {
				prodInfo.location = value;
				continue;
			}
			if ("country".equals(aDataFieldName)) {
				prodInfo.country = value;
				continue;
			}
			if ("region".equals(aDataFieldName)) {
				prodInfo.region = value;
				continue;
			}
			if ("hotelPickup".equals(aDataFieldName)) {
				if (dataset.get(aDataFieldName).isBoolean()) {
					prodInfo.hotelPickup = dataset.get(aDataFieldName)
							.booleanValue();
				} else {
					prodInfo.debug
							.append("God, hotelPickup IS NOT boolean type node. ");
				}
				continue;
			}
			if ("bookingQuestions".equals(aDataFieldName)) {
				analyseBQ(prodInfo, dataset);
				continue;
			}
			if ("tourGrades".equals(aDataFieldName)) {
				analyseTourGrade(prodInfo, dataset);
				continue;
			}

		}
		long cost = System.currentTimeMillis() - begin;
		String log = "--------Parsing json product detail cost: "
				+ IOUtil.human(cost) + ", tourGrades count: "
				+ prodInfo.tourGradesInfo.getTourGradeCount();
		System.out.println(log);
		// System.out.println(prodInfo);
		return prodInfo;
	}

	private void analyseBQ(ProductInfo prodInfo, JsonNode datasetElement) {
		JsonNode bqNode = datasetElement.get("bookingQuestions");
		// direct children of bookingQuestions
		Iterator<JsonNode> it = bqNode.iterator();
		while (it.hasNext()) {
			JsonNode aQuestion = (JsonNode) it.next();
			StringBuilder sb = new StringBuilder();
			// all the field names of -- direct child of "data"
			Iterator<String> questionFields = aQuestion.fieldNames();
			while (questionFields.hasNext()) {
				String field = questionFields.next();
				String value = aQuestion.get(field).asText();
				if ("title".equals(field)) {
					sb.append("title=" + value);
					continue;
				}
				if ("subTitle".equals(field)) {
					sb.append("--subTitle=" + value);
					continue;
				}
				if ("message".equals(field)) {
					sb.append("--message=" + value);
					continue;
				}
				if ("required".equals(field)) {
					prodInfo.bqInfo.listRequired.add(aQuestion.get("required")
							.booleanValue());
					continue;
				}
			}
			prodInfo.bqInfo.listQ.add(sb.toString());
		}
	}

	private void analyseTourGrade(ProductInfo prodInfo, JsonNode datasetElement) {
		JsonNode tgNode = datasetElement.get("tourGrades");
		// direct children of tourGrades
		Iterator<JsonNode> it = tgNode.iterator();
		while (it.hasNext()) {
			JsonNode aTour = (JsonNode) it.next();
			TourGradeInfo.TourInfo ti = new TourGradeInfo.TourInfo();
			prodInfo.tourGradesInfo.tourGrades.add(ti);
			// all the field names of -- direct child of "data"
			Iterator<String> tourFields = aTour.fieldNames();
			while (tourFields.hasNext()) {
				String field = tourFields.next();
				String value = aTour.get(field).asText();

				if ("langServices".equals(field)) {
					analyseLangServices(aTour, ti);
					continue;
				}
				if ("gradeCode".equals(field)) {
					ti.gradeCode = value;
					continue;
				}
				if ("gradeTitle".equals(field)) {
					ti.gradeTitle = value;
					continue;
				}
				if ("gradeDescription".equals(field)) {
					ti.gradeDescription = value;
					continue;
				}
			}
		}
	}

	private void analyseLangServices(JsonNode aTour, TourGradeInfo.TourInfo ti) {
		JsonNode lsNode = aTour.get("langServices");
		Iterator<String> it = lsNode.fieldNames();
		while (it.hasNext()) {
			String field = it.next();
			String value = lsNode.get(field).asText();
			ti.langServices += field + ":" + value + "--";
		}
	}
}

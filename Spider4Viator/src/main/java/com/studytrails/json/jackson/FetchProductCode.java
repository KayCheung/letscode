package com.studytrails.json.jackson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FetchProductCode {
	public static final String FIRE_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";
	public static final String VIATOR_HOST = "prelive.viatorapi.viator.com";
	public static final String SEARCH_PRODUCTS_URI = "/service/search/products?apiKey=7996631481948906";
	public static final int VIATOR_PORT = 80;
	public static final String FOUR_DASH = "----";

	public static void main(String[] args) throws Exception {

		FetchDestination fd = new FetchDestination();
		FetchProductCode fpc = new FetchProductCode();

		List<DestinationInfo> listDI = fd.getAllDestination();

		List<String> listProductCode = fpc.fetchUniqueProductCode(listDI);
	}

	public List<String> fetchUniqueProductCode(List<DestinationInfo> listDI) {
		try {
			LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct = fetchAllProductCode(listDI);
			Map<String, List<DestinationInfo>> mapProduct2ListDest = calculateProductBelongedDestination(mapDest2ListProduct);
			write_ProductAssicatedWithItsDestinations(mapProduct2ListDest);

			List<String> listProductCode = generateUniqueProductCode(mapProduct2ListDest);
			write_UniqueProductCode(listProductCode);

			return listProductCode;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Map<String, List<DestinationInfo>> calculateProductBelongedDestination(
			LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct) {
		Iterator<Entry<DestinationInfo, List<String>>> it = mapDest2ListProduct
				.entrySet().iterator();
		Map<String, List<DestinationInfo>> mapProduct2ListDest = new HashMap<String, List<DestinationInfo>>();
		while (it.hasNext()) {
			Map.Entry<DestinationInfo, List<String>> oneDest2ListProduct = (Map.Entry<DestinationInfo, List<String>>) it
					.next();
			DestinationInfo di = oneDest2ListProduct.getKey();
			List<String> listProduct = oneDest2ListProduct.getValue();
			for (String oneProd : listProduct) {
				if (!mapProduct2ListDest.containsKey(oneProd)) {
					mapProduct2ListDest.put(oneProd,
							new ArrayList<DestinationInfo>(8));
				}
				mapProduct2ListDest.get(oneProd).add(di);
			}
		}
		return mapProduct2ListDest;
	}

	public void write_ProductAssicatedWithItsDestinations(
			Map<String, List<DestinationInfo>> mapProduct2ListDest) {
		BufferedWriter bw = IOUtil.createFileWriter(FetchDestination.BASE_DIR
				+ "productBelongsToDestination.txt", false);
		Iterator<Entry<String, List<DestinationInfo>>> it = mapProduct2ListDest
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<DestinationInfo>> oneDest2ListProduct = (Map.Entry<String, List<DestinationInfo>>) it
					.next();
			String prod = oneDest2ListProduct.getKey();
			List<DestinationInfo> listDest = oneDest2ListProduct.getValue();
			StringBuilder sb = new StringBuilder();
			sb.append(prod + "," + listDest.size() + ",");
			IOUtil.concat(sb, listDest, FOUR_DASH, "");

			System.out.println(sb.toString());
			try {
				bw.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IOUtil.close(bw);
	}

	private List<String> generateUniqueProductCode(
			Map<String, List<DestinationInfo>> mapProduct2ListDest) {
		List<String> listProductCode = new ArrayList<String>(
				mapProduct2ListDest.size());

		Iterator<Entry<String, List<DestinationInfo>>> it = mapProduct2ListDest
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<DestinationInfo>> oneDest2ListProduct = (Map.Entry<String, List<DestinationInfo>>) it
					.next();
			listProductCode.add(oneDest2ListProduct.getKey());
		}
		return listProductCode;
	}

	public void write_UniqueProductCode(List<String> listProductCode) {
		BufferedWriter bw = IOUtil.createFileWriter(FetchDestination.BASE_DIR
				+ "uniqueProductCode.txt", false);

		StringBuilder sb = new StringBuilder();
		IOUtil.concat(sb, listProductCode, "\r\n", "\r\n");
		try {
			bw.write(sb.toString());
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		IOUtil.close(bw);
	}

	public LinkedHashMap<DestinationInfo, List<String>> fetchAllProductCode(
			List<DestinationInfo> listDI) throws Exception {
		long begin = System.currentTimeMillis();
		HttpProcessor httpproc = HttpProcessorBuilder.create()
				.add(new RequestContent()).add(new RequestTargetHost())
				.add(new RequestConnControl())
				.add(new RequestUserAgent(FIRE_USER_AGENT))
				.add(new RequestExpectContinue(true)).build();

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpCoreContext coreContext = HttpCoreContext.create();
		HttpHost host = new HttpHost(VIATOR_HOST, VIATOR_PORT);
		coreContext.setTargetHost(host);

		DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(
				8 * 1024);
		ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

		LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct = new LinkedHashMap<DestinationInfo, List<String>>();

		doFetchAll(mapDest2ListProduct, listDI, httpproc, httpexecutor,
				coreContext, host, conn, connStrategy);

		long cost = System.currentTimeMillis() - begin;
		String log = "fetchAllProductCode cost: " + cost;
		System.out.println(log);
		return mapDest2ListProduct;
	}

	private void doFetchAll(
			LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct,
			List<DestinationInfo> listDI, HttpProcessor httpproc,
			HttpRequestExecutor httpexecutor, HttpCoreContext coreContext,
			HttpHost host, DefaultBHttpClientConnection conn,
			ConnectionReuseStrategy connStrategy) throws UnknownHostException,
			IOException, HttpException {
		try {
			// create an ObjectMapper instance.
			ObjectMapper mapper = new ObjectMapper();
			for (int i = 0, totalDestCount = listDI.size(); i < totalDestCount; i++) {
				if (!conn.isOpen()) {
					Socket socket = new Socket(host.getHostName(),
							host.getPort());
					conn.bind(socket);
				}

				DestinationInfo di = listDI.get(i);
				int tryCount = 0;
				HttpResponse response = null;
				while (tryCount <= 2) {
					System.out.println(tryCount
							+ " time, Search Product Code By Destination: "
							+ di);
					response = fetchWithoutRetry(mapDest2ListProduct, httpproc,
							httpexecutor, coreContext, conn, mapper, i,
							totalDestCount, di);
					if (response == null) {
						tryCount++;
						conn.close();
						Socket socket = new Socket(host.getHostName(),
								host.getPort());
						conn.bind(socket);
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
			}
		} finally {
			conn.close();
		}
	}

	private HttpResponse fetchWithoutRetry(
			LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct,
			HttpProcessor httpproc, HttpRequestExecutor httpexecutor,
			HttpCoreContext coreContext, DefaultBHttpClientConnection conn,
			ObjectMapper mapper, int i, int totalDestCount, DestinationInfo di)
			throws HttpException, IOException, JsonProcessingException {
		HttpResponse response;
		try {
			response = doFetchByDestination(i, totalDestCount, mapper,
					mapDest2ListProduct, di, httpproc, httpexecutor,
					coreContext, conn);
		} catch (Exception e) {
			System.err
					.println("Error when Search Product Code By Destination: "
							+ di);
			e.printStackTrace();
			response = null;
		}
		return response;
	}

	private HttpResponse doFetchByDestination(int destIndex,
			int totalDestCount, ObjectMapper mapper,
			LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct,
			DestinationInfo di, HttpProcessor httpproc,
			HttpRequestExecutor httpexecutor, HttpCoreContext coreContext,
			DefaultBHttpClientConnection conn) throws HttpException,
			IOException, JsonProcessingException {
		long begin = System.currentTimeMillis();
		BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(
				"POST", SEARCH_PRODUCTS_URI);
		request.addHeader("User-Agent", FIRE_USER_AGENT);
		request.setEntity(createHttpEntity(di));

		httpexecutor.preProcess(request, httpproc, coreContext);
		HttpResponse response = httpexecutor
				.execute(request, conn, coreContext);
		httpexecutor.postProcess(response, httpproc, coreContext);

		String searchProductResult = EntityUtils.toString(response.getEntity());
		int foundCodeCount = parseSearchProductToGetProductCode(mapper,
				mapDest2ListProduct, searchProductResult, di);

		long cost = System.currentTimeMillis() - begin;
		String log = (destIndex + 1) + " / " + totalDestCount
				+ ", Search Product Code By Destination, get: "
				+ foundCodeCount + " Product Code, cost: " + cost
				+ ". By destination: " + di;
		System.out.println(log);
		return response;
	}

	private static HttpEntity createHttpEntity(DestinationInfo di) {
		String entity = "{\"currencyCode\":\"USD\", \"destId\":" + di.destId
				+ "}";
		StringEntity se = new StringEntity(entity, ContentType.APPLICATION_JSON);
		return se;
	}

	private int parseSearchProductToGetProductCode(ObjectMapper mapper,
			LinkedHashMap<DestinationInfo, List<String>> mapDest2ListProduct,
			String searchProductResult, DestinationInfo di) throws IOException,
			JsonProcessingException {
		if (!mapDest2ListProduct.containsKey(di)) {
			mapDest2ListProduct.put(di, new ArrayList<String>());
		}
		List<String> listProduct = mapDest2ListProduct.get(di);
		long begin = System.currentTimeMillis();
		int codeCount = 0;
		// use the ObjectMapper to read the json string and create a tree
		JsonNode rootNode = mapper.readTree(searchProductResult);
		// Lets look at the dataset now.
		JsonNode dataset = rootNode.get("data");
		// The data is an array. Lets iterate through the array and see
		// what each of the elements are
		// each element is also a JsonNode
		Iterator<JsonNode> datasetElements = dataset.iterator();
		while (datasetElements.hasNext()) {
			// direct child of "data"
			JsonNode datasetElement = datasetElements.next();
			// all the field names of -- direct child of "data"
			Iterator<String> datasetElementFields = datasetElement.fieldNames();
			while (datasetElementFields.hasNext()) {
				String field = datasetElementFields.next();
				if ("code".equals(field)) {
					codeCount++;
					listProduct.add(datasetElement.get(field).asText());
					break;// already found product code of -- direct child of
							// "data"
				}
			}
		}
		long cost = System.currentTimeMillis() - begin;
		String log = "--------Parsing json node, get: " + codeCount
				+ " product code, cost: " + cost;
		System.out.println(log);
		return codeCount;
	}
}

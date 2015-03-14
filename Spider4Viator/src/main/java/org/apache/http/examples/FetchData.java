package org.apache.http.examples;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studytrails.json.jackson.FetchDestination;

public class FetchData {
	public static void main(String[] args) throws Exception {
		writeSKU_destByDest(organizeDest());
	}

	public static void writeSKU_destByDest(List<DestInfo> listDI)
			throws Exception {
		HttpProcessor httpproc = HttpProcessorBuilder
				.create()
				.add(new RequestContent())
				.add(new RequestTargetHost())
				.add(new RequestConnControl())
				.add(new RequestUserAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0"))
				.add(new RequestExpectContinue(true)).build();

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpCoreContext coreContext = HttpCoreContext.create();
		HttpHost host = new HttpHost("prelive.viatorapi.viator.com", 80);
		coreContext.setTargetHost(host);

		DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(
				8 * 1024);
		ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;
		System.out.println("Total destination count:" + listDI.size());
		try {

			HttpEntity[] requestBodies = new HttpEntity[listDI.size()];
			for (int i = 0; i < listDI.size(); i++) {
				String entity = "{\"currencyCode\":\"USD\", \"catId\":8, \"destId\":"
						+ listDI.get(i).id + "}";
				requestBodies[i] = new StringEntity(entity,
						ContentType.APPLICATION_JSON);
			}
			BufferedWriter bw = createFileWriter();
			// create an ObjectMapper instance.
			ObjectMapper mapper = new ObjectMapper();
			for (int i = 0; i < requestBodies.length; i++) {
				if (!conn.isOpen()) {
					Socket socket = new Socket(host.getHostName(),
							host.getPort());
					conn.bind(socket);
				}
				BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(
						"POST",
						"/service/search/products?apiKey=7996631481948906");
				request.addHeader("User-Agent",
						"	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
				request.setEntity(requestBodies[i]);

				httpexecutor.preProcess(request, httpproc, coreContext);
				HttpResponse response = httpexecutor.execute(request, conn,
						coreContext);
				httpexecutor.postProcess(response, httpproc, coreContext);

				System.out.println("<< Response: " + response.getStatusLine());
				String skuJson = EntityUtils.toString(response.getEntity());
				List<SKUInfo> listSKU = sku4EachDestId(mapper, skuJson,
						listDI.get(i));
				System.out.println("Processing " + i + " destination, found "
						+ listSKU.size() + " SKUs");
				writeList2File(bw, listSKU);
				System.out.println("==============");
				if (!connStrategy.keepAlive(response, coreContext)) {
					conn.close();
				} else {
					// System.out.println("Connection kept alive...");
				}
			}
		} finally {
			conn.close();
		}
	}

	private static BufferedWriter createFileWriter() throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("c:/skulist.txt", true)));
		return bw;
	}

	private static void writeList2File(BufferedWriter bw, List<SKUInfo> listSKU)
			throws Exception {
		if (listSKU.size() <= 0) {
			return;
		}
		for (SKUInfo aSKU : listSKU) {
			bw.write(aSKU.toString());
		}
		bw.flush();
	}

	private static List<SKUInfo> sku4EachDestId(ObjectMapper mapper,
			String jsonString, DestInfo correspondingDI) throws Exception {
		List<SKUInfo> listSKU = new ArrayList<SKUInfo>();
		// use the ObjectMapper to read the json string and create a tree
		JsonNode rootNode = mapper.readTree(jsonString);
		// Lets look at the dataset now.
		JsonNode dataset = rootNode.get("data");
		// what is its type?
		// System.out.println(dataset.getNodeType()); // Prints ARRAY

		// so the data is an array. Lets iterate through the array and see
		// what each of the elements are
		// each element is also a JsonNode
		Iterator<JsonNode> datasetElements = dataset.iterator();
		while (datasetElements.hasNext()) {
			JsonNode datasetElement = datasetElements.next();
			// what is its type
			// System.out.println(datasetElement.getNodeType());// Prints Object
			SKUInfo si = new SKUInfo();
			si.destId = correspondingDI.id;
			si.destName = correspondingDI.name;
			si.destType = correspondingDI.type;
			listSKU.add(si);
			Iterator<String> datasetElementFields = datasetElement.fieldNames();
			while (datasetElementFields.hasNext()) {
				String datasetElementField = datasetElementFields.next();
				String datasetElementValue = datasetElement.get(
						datasetElementField).asText();
				if ("currencyCode".equals(datasetElementField)) {
					si.currencyCode = datasetElementValue;
				} else if ("merchantNetPriceFrom".equals(datasetElementField)) {
					si.merchantNetPriceFrom = datasetElementValue;
					si._merchantNetPriceFrom105 = si.merchantNetPriceFrom105();
				} else if ("price".equals(datasetElementField)) {
					si.price = datasetElementValue;
				} else if ("code".equals(datasetElementField)) {
					si.code = datasetElementValue;
				} else if ("title".equals(datasetElementField)) {
					si.title = datasetElementValue;
				}
			}
		}
		return listSKU;
	}

	static class DestInfo {
		public String id, type, name;
	}

	static class SKUInfo {
		public String currencyCode, merchantNetPriceFrom,
				_merchantNetPriceFrom105, price, code, title, destId, destType,
				destName;

		public String merchantNetPriceFrom105() {
			if (merchantNetPriceFrom == null
					|| merchantNetPriceFrom.trim().equals("")) {
				return "";
			}
			BigDecimal mnpf105 = new BigDecimal(merchantNetPriceFrom)
					.multiply(new BigDecimal("1.05"));
			return mnpf105.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		}

		public static void main(String[] args) {
			SKUInfo si = new SKUInfo();
			si.merchantNetPriceFrom = "1.10";
			System.out.println(si.merchantNetPriceFrom105());
		}

		@Override
		public String toString() {
			return merchantNetPriceFrom + _merchantNetPriceFrom105 + price
					+ code + title + destId + destType + destName + FetchDestination.ENTER;
		}
	}

	private static List<DestInfo> organizeDest() throws Exception {
		JsonNode rootNode = createJsonRootNode();
		List<DestInfo> list = new ArrayList<DestInfo>();
		// lets see what type the node is
		System.out.println(rootNode.getNodeType()); // prints OBJECT

		// Lets look at the dataset now.
		JsonNode dataset = rootNode.get("data");
		// what is its type?
		System.out.println(dataset.getNodeType()); // Prints ARRAY

		// so the data is an array. Lets iterate through the array and see
		// what each of the elements are
		// each element is also a JsonNode
		Iterator<JsonNode> datasetElements = dataset.iterator();
		while (datasetElements.hasNext()) {
			JsonNode datasetElement = datasetElements.next();
			// what is its type
			// System.out.println(datasetElement.getNodeType());// Prints Object
			DestInfo di = new DestInfo();
			list.add(di);
			Iterator<String> datasetElementFields = datasetElement.fieldNames();
			while (datasetElementFields.hasNext()) {
				String datasetElementField = datasetElementFields.next();
				String datasetElementValue = datasetElement.get(
						datasetElementField).asText();
				if ("destinationId".equals(datasetElementField)) {
					di.id = datasetElementValue;
				} else if ("destinationType".equals(datasetElementField)) {
					di.type = datasetElementValue;
				} else if ("destinationName".equals(datasetElementField)) {
					di.name = datasetElementValue;
				}
			}
		}
		return list;
	}

	private static JsonNode createJsonRootNode() throws Exception {
		// Get a list of albums from free music archive. limit the results to 5
		String url = "http://prelive.viatorapi.viator.com/service/taxonomy/locations?apiKey=7996631481948906";
		// Get the contents of json as a string using commons IO IOUTils class.
		String genreJson = IOUtils.toString(new URL(url));
		// System.out.println(genreJson);
		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();
		// use the ObjectMapper to read the json string and create a tree
		JsonNode node = mapper.readTree(genreJson);
		return node;
	}
}
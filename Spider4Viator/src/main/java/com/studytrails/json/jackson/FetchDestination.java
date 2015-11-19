package com.studytrails.json.jackson;

import java.io.BufferedWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FetchDestination {
	// public static final String BASE_DIR = "D:/Fast/Viator/statistic/";
	public static String BASE_DIR = "/home/marvin/Tuniu/Fast/Viator/statistic/";
	public static final String ENTER = "\r\n";
	public static final String DESTINATION_URL = "http://prelive.viatorapi.viator.com/service/taxonomy/locations?apiKey=7996631481948906";

	public List<DestinationInfo> getAllDestination() throws Exception {
		long begin = System.currentTimeMillis();
		JsonNode rootNode = createJsonRootNode();
		List<DestinationInfo> list = new ArrayList<DestinationInfo>();
		// Lets look at the dataset now.
		JsonNode dataset = rootNode.get("data");
		// The data is an array. Lets iterate through the array and see
		// what each of the elements are
		// each element is also a JsonNode
		Iterator<JsonNode> datasetElements = dataset.iterator();
		while (datasetElements.hasNext()) {
			JsonNode datasetElement = datasetElements.next();
			// what is its type
			// System.out.println(datasetElement.getNodeType());// Prints Object
			DestinationInfo di = new DestinationInfo();
			list.add(di);
			Iterator<String> datasetElementFields = datasetElement.fieldNames();
			while (datasetElementFields.hasNext()) {
				String field = datasetElementFields.next();
				String value = datasetElement.get(field).asText();
				if ("destinationId".equals(field)) {
					di.destId = value;
				} else if ("destinationType".equals(field)) {
					di.destType = value;
				} else if ("destinationName".equals(field)) {
					di.destName = value;
				}
			}
		}
		writeDestination2File(list);
		System.out.println("Getting all destinations cost: "
				+ IOUtil.human((System.currentTimeMillis() - begin))
				+ ", destination count: " + list.size());
		return list;
	}

	private void writeDestination2File(List<DestinationInfo> list)
			throws Exception {
		BufferedWriter bw = IOUtil.createFileWriter(BASE_DIR
				+ "destinations.txt", false);
		IOUtil.writeList2File(bw, list);
		IOUtil.close(bw);
	}

	private JsonNode createJsonRootNode() throws Exception {

		// Get the contents of json as a string using commons IO IOUTils class.
		String genreJson = IOUtils.toString(new URL(DESTINATION_URL));
		ObjectMapper mapper = new ObjectMapper();
		// use the ObjectMapper to read the json string and create a tree
		JsonNode node = mapper.readTree(genreJson);
		return node;
	}

	public static void main(String[] args) throws Exception {
		new FetchDestination().getAllDestination();
	}
}
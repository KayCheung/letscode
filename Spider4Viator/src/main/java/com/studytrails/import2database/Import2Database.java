package com.studytrails.import2database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studytrails.json.jackson.IOUtil;

public class Import2Database {
	public static final String BASE_DIR = "D:/Fast/Viator/statistic/virgin_data/";
	public static final String DB_URL = "jdbc:mysql://10.10.30.34:3306/cowboy?useUnicode=true&zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8&allowMultiQueries=true";
	public static final String DB_USER = "cowboy_dev";
	public static final String DB_PWD = "tuniu520";

	static class SortFile implements Comparable<SortFile> {
		public SortFile(String filename, File f) {
			this.filename = filename;
			this.f = f;
		}

		public String filename;
		public File f;

		public int compareTo(SortFile o) {
			return this.filename.compareTo(o.filename);
		}

	}

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PWD);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = getConn();
		List<CategoryInfo> listCategory = analyseCategory(IOUtil
				.readContent(BASE_DIR + "Viator_All_Categories.json"));
		insertCategory(conn, listCategory);

		insertVirginProduct(conn);

		conn.close();

	}

	private static void insertCategory(Connection conn,
			List<CategoryInfo> listCategory) throws Exception {
		long longcurrent = System.currentTimeMillis();
		String sql = "insert into viator_category(category_Id, category_name, subcategory_Id, subcategory_name, create_time) values(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (CategoryInfo ci : listCategory) {
			for (SubcategoryInfo si : ci.listSub) {
				pstmt.setInt(1, ci.id);
				pstmt.setString(2, ci.groupName);
				pstmt.setInt(3, si.subcategoryId);
				pstmt.setString(4, si.subcategoryName);
				pstmt.setTimestamp(5, new Timestamp(longcurrent));
				pstmt.addBatch();
			}
		}
		pstmt.executeBatch();
		pstmt.close();
	}

	private static void insertVirginProduct(Connection conn) throws Exception {
		long longcurrent = System.currentTimeMillis();
		String sql = "insert into viator_virgin_product(prd_code, prd_detail,create_time,update_time) values(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		List<SortFile> listFiles = traverseTopFolder(BASE_DIR + "data");

		int currentFileIndex = 1;// start from 1
		for (int i = 0; i < listFiles.size(); i++) {
			File aFile = listFiles.get(i).f;
			String filename = aFile.getName();
			int dash = filename.indexOf("_");
			int dot = filename.lastIndexOf(".");
			String code = filename.substring(dash + 1, dot);
			String detail = IOUtil.readContent(aFile.getAbsolutePath());

			pstmt.setString(1, code);
			pstmt.setString(2, detail);
			pstmt.setTimestamp(3, new Timestamp(longcurrent));
			pstmt.setTimestamp(4, new Timestamp(longcurrent));
			pstmt.addBatch();
			System.out.println("Adding: " + filename);
			if (currentFileIndex % 1000 == 0) {
				pstmt.executeBatch();
			}
			currentFileIndex++;
		}

		if (currentFileIndex % 1000 != 0) {
			pstmt.executeBatch();
		}
		pstmt.close();
	}

	private static List<SortFile> traverseTopFolder(String topFolderPath) {
		List<SortFile> listFiles = new ArrayList<SortFile>(13000);
		// D:\Fast\Viator\statistic\virgin_data\data

		File fTopFolder = new File(topFolderPath);
		File[] subFolders = fTopFolder.listFiles();
		for (File aFolder : subFolders) {
			File[] files = aFolder.listFiles();
			for (File aFile : files) {
				listFiles.add(new SortFile(aFile.getName(), aFile));
			}
		}
		Collections.sort(listFiles);
		return listFiles;
	}

	private static List<CategoryInfo> analyseCategory(String categoryJson)
			throws Exception {
		List<CategoryInfo> listCategory = new ArrayList<CategoryInfo>();
		long begin = System.currentTimeMillis();
		ObjectMapper mapper = new ObjectMapper();
		// use the ObjectMapper to read the json string and create a tree
		JsonNode rootNode = mapper.readTree(categoryJson);
		// Lets look at the dataset now.
		JsonNode dataset = rootNode.get("data");
		// The data is an array. Lets iterate through the array and see
		// what each of the elements are
		// each element is also a JsonNode
		Iterator<JsonNode> datasetElements = dataset.iterator();
		while (datasetElements.hasNext()) {
			// direct child of "data"
			JsonNode datasetElement = datasetElements.next();
			CategoryInfo ci = new CategoryInfo();
			listCategory.add(ci);
			// all the field names of -- direct child of "data"
			Iterator<String> datasetElementFields = datasetElement.fieldNames();
			while (datasetElementFields.hasNext()) {
				String field = datasetElementFields.next();
				if ("id".equals(field)) {
					ci.id = datasetElement.get(field).asInt();
					continue;
				}
				if ("groupName".equals(field)) {
					ci.groupName = datasetElement.get(field).asText();
					continue;
				}
				if ("subcategories".equals(field)) {
					JsonNode subcategoriesNode = datasetElement.get(field);
					analyseSubcategory(ci, subcategoriesNode);
					continue;
				}
			}
		}
		Collections.sort(listCategory);
		return listCategory;
	}

	private static void analyseSubcategory(CategoryInfo ci,
			JsonNode subcategoriesNode) {
		Iterator<JsonNode> subElements = subcategoriesNode.iterator();
		while (subElements.hasNext()) {
			// a subcategory
			JsonNode aSubcategory = subElements.next();
			SubcategoryInfo si = new SubcategoryInfo();
			ci.listSub.add(si);
			Iterator<String> subFields = aSubcategory.fieldNames();
			while (subFields.hasNext()) {
				String field = subFields.next();
				if ("subcategoryId".equals(field)) {
					si.subcategoryId = aSubcategory.get(field).asInt();
					continue;
				}
				if ("subcategoryName".equals(field)) {
					si.subcategoryName = aSubcategory.get(field).asText();
					continue;
				}
			}
		}
		Collections.sort(ci.listSub);
	}

}

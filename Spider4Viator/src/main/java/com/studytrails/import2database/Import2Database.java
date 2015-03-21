package com.studytrails.import2database;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studytrails.analyse.ViatorProductDetailInfo;
import com.studytrails.analyse.ViatorProductDetailInfo.ProductDetailData;
import com.studytrails.analyse.ViatorProductDetailInfo.ProductDetailData.TourGrades;
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
		long begin = System.currentTimeMillis();

		// String infolog = BASE_DIR + "dev_info.log";
		// PrintStream psInfo = new PrintStream(new File(infolog));
		// System.setOut(psInfo);
		//
		// String errorlog = BASE_DIR + "dev_error.log";
		// PrintStream psError = new PrintStream(new File(errorlog));
		// System.setErr(psError);
		Connection conn = getConn();
		// List<CategoryInfo> listCategory = parseCategory(IOUtil
		// .readContent(BASE_DIR + "Viator_All_Categories.json"));
		// insertCategory(conn, listCategory);
		//
		// insertVirginProduct(conn);
		//
		// insertATP(conn);
		//
		analyseProductDetail_2_StructureTable(conn);

		conn.close();
		long cost = System.currentTimeMillis() - begin;
		System.out.println("analyseProductDetail_2_StructureTable totally cost: " + IOUtil.human(cost));

		// psInfo.close();
		// psError.close();
	}

	private static void analyseProductDetail_2_StructureTable(Connection conn)
			throws Exception {
		String sql = "INSERT INTO dev_only_viator_product_detail(product_code,product_duration,tourGradesAvailable,gradeCode,gradeTitle,langServices,gradeDepartureTime,tourgrade_priceFrom,tourgrade_merchantNetPriceFrom,product_price,product_merchantNetPriceFrom,merchantCancellable,hotelPickup,bookingQuestions_count,bookingEngineId,onRequestPeriod,city,location,country,region,primaryGroupId,destinationId,primaryDestinationId,primaryDestinationName,product_title,product_shortTitle,translationLevel,product_departureTime,product_departurePoint,operates) VALUES ("
				+ "?,"// product_code
				+ "?,"// product_duration
				+ "?,"// tourGradesAvailable,
				+ "?,"// gradeCode,
				+ "?,"// gradeTitle,
				+ "?,"// langServices,
				+ "?,"// gradeDepartureTime,
				+ "?,"// tourgrade_priceFrom,
				+ "?,"// tourgrade_merchantNetPriceFrom,
				+ "?,"// product_price,
				+ "?,"// product_merchantNetPriceFrom,
				+ "?,"// merchantCancellable,
				+ "?,"// hotelPickup,
				+ "?,"// bookingQuestions_count,
				+ "?,"// bookingEngineId,
				+ "?,"// onRequestPeriod,
				+ "?,"// city,
				+ "?,"// location,
				+ "?,"// country,
				+ "?,"// region,
				+ "?,"// primaryGroupId,
				+ "?,"// destinationId,
				+ "?,"// primaryDestinationId,
				+ "?,"// primaryDestinationName,
				+ "?,"// product_title,
				+ "?,"// product_shortTitle,
				+ "?,"// translationLevel,
				+ "?,"// product_departureTime,
				+ "?,"// product_departurePoint,
				+ "?)";// operates

		PreparedStatement pstmt = conn.prepareStatement(sql);
		List<SortFile> listFiles = traverseTopFolder(BASE_DIR + "data", false);

		int alreadInsertedRowCount = 0;// start from 0
		for (int i = 0; i < listFiles.size(); i++) {
			File aFile = listFiles.get(i).f;
			String filename = aFile.getName();
			int dash = filename.indexOf("_");
			int dot = filename.lastIndexOf(".");
			String code = filename.substring(dash + 1, dot);
			String detail = IOUtil.readContent(aFile.getAbsolutePath());

			ViatorProductDetailInfo vpdi = JSON.parseObject(detail,
					ViatorProductDetailInfo.class);
			ProductDetailData pdd = vpdi.getData();
			if (pdd == null) {
				continue;
			}
			if (pdd.getTourGrades() == null) {
				continue;
			}
			List<TourGrades> listTourGrades = vpdi.getData().getTourGrades();
			for (TourGrades aTourgrade : listTourGrades) {
				pstmt.setString(1, truncateIfLongerThan(code, 32)); // product_code
				pstmt.setString(2, truncateIfLongerThan(pdd.getDuration(), 32)); // product_duration
				pstmt.setString(
						3,
						truncateIfLongerThan(pdd.isTourGradesAvailable() + "",
								5)); // tourGradesAvailable
				pstmt.setString(4,
						truncateIfLongerThan(aTourgrade.getGradeCode(), 32)); // gradeCode
				pstmt.setString(5,
						truncateIfLongerThan(aTourgrade.getGradeTitle(), 128)); // gradeTitle
				pstmt.setString(6,
						truncateIfLongerThan(strLangService(aTourgrade), 256)); // langServices
				pstmt.setString(
						7,
						truncateIfLongerThan(
								aTourgrade.getGradeDepartureTime(), 32)); // gradeDepartureTime

				pstmt.setBigDecimal(8, aTourgrade.getPriceFrom()); // tourgrade_priceFrom
				pstmt.setBigDecimal(9, aTourgrade.getMerchantNetPriceFrom()); // tourgrade_merchantNetPriceFrom
				pstmt.setBigDecimal(10, pdd.getPrice()); // product_price
				pstmt.setBigDecimal(11, pdd.getMerchantNetPriceFrom()); // product_merchantNetPriceFrom
				pstmt.setString(12, pdd.isMerchantCancellable() + ""); // merchantCancellable
				pstmt.setString(13, pdd.isHotelPickup() + ""); // hotelPickup
				pstmt.setShort(14,
						(short) (pdd.getBookingQuestions() == null ? 0 : pdd
								.getBookingQuestions().size())); // bookingQuestions_count

				pstmt.setString(15,
						truncateIfLongerThan(pdd.getBookingEngineId(), 32)); // bookingEngineId
				pstmt.setString(16,
						truncateIfLongerThan(pdd.getOnRequestPeriod(), 32)); // onRequestPeriod
				pstmt.setString(17, truncateIfLongerThan(pdd.getCity(), 32)); // city
				pstmt.setString(18, truncateIfLongerThan(pdd.getLocation(), 32)); // location
				pstmt.setString(19, truncateIfLongerThan(pdd.getCountry(), 32)); // country
				pstmt.setString(20, truncateIfLongerThan(pdd.getRegion(), 32)); // region

				pstmt.setString(21,
						truncateIfLongerThan(pdd.getPrimaryGroupId(), 6)); // primaryGroupId
				pstmt.setString(
						22,
						truncateIfLongerThan(pdd.getDestinationId().toString(),
								6)); // destinationId
				pstmt.setString(
						23,
						truncateIfLongerThan(pdd.getPrimaryDestinationId()
								.toString(), 6)); // primaryDestinationId
				pstmt.setString(
						24,
						truncateIfLongerThan(pdd.getPrimaryDestinationName(),
								128)); // primaryDestinationName

				pstmt.setString(25, truncateIfLongerThan(pdd.getTitle(), 128)); // product_title
				pstmt.setString(26,
						truncateIfLongerThan(pdd.getShortTitle(), 128)); // product_shortTitle
				pstmt.setShort(27, pdd.getTranslationLevel().shortValue()); // translationLevel
				pstmt.setString(28,
						truncateIfLongerThan(pdd.getDepartureTime(), 256)); // product_departureTime
				pstmt.setString(29,
						truncateIfLongerThan(pdd.getDeparturePoint(), 256)); // product_departurePoint
				pstmt.setString(30, truncateIfLongerThan(pdd.getOperates(), 32)); // operates
				pstmt.addBatch();
				System.out.println("Adding: " + code + "----"
						+ aTourgrade.getGradeCode());
				alreadInsertedRowCount++;
				if (alreadInsertedRowCount % 1000 == 0) {
					pstmt.executeBatch();
				}
			}
		}

		if (alreadInsertedRowCount % 1000 != 0) {
			pstmt.executeBatch();
		}
		pstmt.close();
	}

	private static String strLangService(TourGrades aTourgrade) {
		Map<String, String> map = aTourgrade.getLangServices();
		if (map == null || map.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) it
					.next();
			sb.append(entry.getKey() + ":" + entry.getValue() + "----");
		}
		return sb.toString();
	}

	private static String truncateIfLongerThan(String str, int maxLength) {
		if (str == null) {
			return "";
		}
		if (str.length() > maxLength) {
			return str.substring(0, maxLength);
		}
		return str;
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
		List<SortFile> listFiles = traverseTopFolder(BASE_DIR + "data", false);

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

	private static void insertATP(Connection conn) throws Exception {
		long longcurrent = System.currentTimeMillis();
		String sql = "insert into viator_avail_tourgrades_pricingmatrix(prd_code, prd_atp,year,month,create_time,update_time) values(?,?,'2015',?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		List<SortFile> listFiles = traverseTopFolder(BASE_DIR + "ATP", true);

		int currentFileIndex = 1;// start from 1
		for (int i = 0, size = listFiles.size(); i < size; i++) {
			File aFile = listFiles.get(i).f;
			String filename = aFile.getName();
			// 01977_04_2140AGPHTLPRT.json
			int dash_1 = filename.indexOf("_");
			int dash_2 = filename.indexOf("_", dash_1 + 1);
			int dot = filename.lastIndexOf(".");

			String prodCode = filename.substring(dash_2 + 1, dot);
			String atp = IOUtil.readContent(aFile.getAbsolutePath());
			String month = filename.substring(dash_1 + 1, dash_2);

			pstmt.setString(1, prodCode);
			pstmt.setString(2, atp);
			pstmt.setString(3, month);
			pstmt.setTimestamp(4, new Timestamp(longcurrent));
			pstmt.setTimestamp(5, new Timestamp(longcurrent));
			pstmt.addBatch();
			System.out.println("Adding: " + filename);
			if (currentFileIndex % 1000 == 0) {
				long begin = System.currentTimeMillis();
				pstmt.executeBatch();
				long cost = System.currentTimeMillis() - begin;
				System.out.println(currentFileIndex + " / " + size
						+ ", executeBatch cost: " + IOUtil.human(cost));
			}
			currentFileIndex++;
		}

		if (currentFileIndex % 1000 != 0) {
			pstmt.executeBatch();
		}
		pstmt.close();
	}

	private static List<SortFile> traverseTopFolder(String topFolderPath,
			boolean doFilter) {
		long begin = System.currentTimeMillis();
		Set<String> filterSet = null;
		if (doFilter == true) {
			filterSet = new HashSet<String>(13000);
		}
		List<SortFile> listFiles = new ArrayList<SortFile>(13000);
		// D:\Fast\Viator\statistic\virgin_data\data
		File fTopFolder = new File(topFolderPath);
		File[] subFolders = fTopFolder.listFiles();
		for (File aFolder : subFolders) {
			File[] files = aFolder.listFiles();
			for (File aFile : files) {
				String filename = aFile.getName();
				if (doFilter == true) {
					if (!filterSet.contains(filename)) {
						listFiles.add(new SortFile(filename, aFile));
						filterSet.add(filename);
					} else {
						// already contained, do nothing
					}
				} else {
					listFiles.add(new SortFile(filename, aFile));
				}
			}
		}
		long cost = System.currentTimeMillis() - begin;
		System.out.println("traverseTopFolder cost: " + IOUtil.human(cost)
				+ ", topFolderPath: " + topFolderPath);
		Collections.sort(listFiles);
		return listFiles;
	}

	private static List<CategoryInfo> parseCategory(String categoryJson)
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
					parseSubcategory(ci, subcategoriesNode);
					continue;
				}
			}
		}
		Collections.sort(listCategory);
		return listCategory;
	}

	private static void parseSubcategory(CategoryInfo ci,
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

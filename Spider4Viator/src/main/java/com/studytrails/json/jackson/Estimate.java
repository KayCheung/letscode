package com.studytrails.json.jackson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Estimate {
	public void estimate(List<ProductInfo> listProdInfo) {
		int totalProductCount = listProdInfo.size();
		int totalTourCount = 0;

		int onlyBQ_Product_Count = 0;
		int onlyBQ_Tour_Count = 0;

		int onlyHotelpickupTrue_Product_Count = 0;
		int onlyHotelpickupTrue_Tour_Count = 0;

		int bothBQAndHotelpickup_Product_Count = 0;
		int bothBQAndHotelpickup_Tour_Count = 0;

		int containsNone_Product_Count = 0;
		int containsNone_Tour_Count = 0;

		for (ProductInfo prodInfo : listProdInfo) {
			int tourInProduct = prodInfo.tourGradesInfo.getTourGradeCount();
			totalTourCount += tourInProduct;

			boolean containsBQ = false;
			boolean containsHotelpickup = false;
			if (prodInfo.bqInfo.getBookQCount() > 0) {
				containsBQ = true;
			}
			if (prodInfo.hotelPickup == true) {
				containsHotelpickup = true;
			}

			if (containsBQ) {
				if (containsHotelpickup) {
					// Booking Questions, and hotelPickup
					bothBQAndHotelpickup_Product_Count++;
					bothBQAndHotelpickup_Tour_Count += tourInProduct;
				} else {
					// Booking Questions, No hotelPickup
					onlyBQ_Product_Count++;
					onlyBQ_Tour_Count += tourInProduct;
				}
			} else {
				if (containsHotelpickup) {
					// No Booking Questions, but hotelPickup
					onlyHotelpickupTrue_Product_Count++;
					onlyHotelpickupTrue_Tour_Count += tourInProduct;
				} else {
					// No Booking Questions, No hotelPickup
					containsNone_Product_Count++;
					containsNone_Tour_Count += tourInProduct;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Total Product Count: ");
		sb.append(totalProductCount);

		sb.append("\nTotal Tour Grades count: ");
		sb.append(totalTourCount);

		sb.append("\nProduct Count (No Booking Question, No Hotel Pickup). Good for us, as we can integrate them to Tuniu: ");
		sb.append(containsNone_Product_Count);
		sb.append("\nTour Grades Count (No Booking Question, No Hotel Pickup). Good for us, as we can integrate them to Tuniu: ");
		sb.append(containsNone_Tour_Count);

		sb.append("\nProduct Count (Booking Question and Hotel Pickup). Bad for us, as we CANNOT integrate them to Tuniu: ");
		sb.append(bothBQAndHotelpickup_Product_Count);
		sb.append("\nTour Grades Count (Booking Question and Hotel Pickup). Bad for us, as we CANNOT integrate them to Tuniu: ");
		sb.append(bothBQAndHotelpickup_Tour_Count);

		sb.append("\nProduct Count (Only No Booking Question). Bad for us, as we CANNOT integrate them to Tuniu: ");
		sb.append(onlyBQ_Product_Count);
		sb.append("\nTour Grades Count (Only No Booking Question). Bad for us, as we CANNOT integrate them to Tuniu: ");
		sb.append(onlyBQ_Tour_Count);

		sb.append("\nProduct Count (Only Hotel Pickup). Bad for us, as we CANNOT integrate them to Tuniu: ");
		sb.append(onlyHotelpickupTrue_Product_Count);
		sb.append("\nTour Grades Count (Only Hotel Pickup). Bad for us, as we CANNOT integrate them to Tuniu: ");
		sb.append(onlyHotelpickupTrue_Tour_Count);

		BufferedWriter bw = IOUtil.createFileWriter(FetchDestination.BASE_DIR
				+ "FinalResult.txt", false);
		try {
			bw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		IOUtil.close(bw);
	}

}

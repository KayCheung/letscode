package com.studytrails.json.jackson;

public class ProductInfo {
	public String code;
	public String title;
	public String shortTitle;
	public String shortDescription;
	public String country;
	public String region;
	public String location;

	public boolean hotelPickup;
	public BookQInfo bqInfo = new BookQInfo();
	public TourGradeInfo tourGradesInfo = new TourGradeInfo();

	public StringBuilder debug = new StringBuilder("debug");

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(code + ",");
		sb.append(hotelPickup + ",");
		sb.append(bqInfo + ",");
		sb.append(tourGradesInfo + ",");
		sb.append(title + ",");
		sb.append(shortTitle + ",");
		sb.append(shortDescription + ",");
		sb.append(country + ",");
		sb.append(region + ",");
		sb.append(location + ",");
		sb.append(debug.toString());
		return sb.toString();
	}

}

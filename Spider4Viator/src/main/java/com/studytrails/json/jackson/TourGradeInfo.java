package com.studytrails.json.jackson;

import java.util.ArrayList;
import java.util.List;

public class TourGradeInfo {
	static class TourInfo {
		public String langServices = "";
		public String gradeCode;
		public String gradeTitle;
		public String gradeDescription;

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(" [ ");
			sb.append("gradeCode=" + gradeCode);
			sb.append(FetchProductCode.FOUR_DASH);
			sb.append("langServices=" + langServices);
			sb.append(FetchProductCode.FOUR_DASH);
			sb.append("gradeTitle=" + gradeTitle);
			sb.append(FetchProductCode.FOUR_DASH);
			sb.append("gradeDescription=" + gradeDescription);
			sb.append(" ] ");
			return sb.toString();
		}

	}

	public List<TourInfo> tourGrades = new ArrayList<TourInfo>();

	public int getTourGradeCount() {
		return tourGrades.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTourGradeCount());
		sb.append(FetchProductCode.FOUR_DASH);

		IOUtil.concat(sb, tourGrades, FetchProductCode.FOUR_DASH, "");

		return sb.toString();
	}
}

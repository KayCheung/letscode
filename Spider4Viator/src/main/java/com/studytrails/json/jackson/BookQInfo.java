package com.studytrails.json.jackson;

import java.util.ArrayList;
import java.util.List;

public class BookQInfo {
	public List<String> listQ = new ArrayList<String>(1);
	public List<Boolean> listRequired = new ArrayList<Boolean>(1);

	public int getBookQCount() {
		return listQ.size();
	}

	/**
	 * Precondition: at least one booking question exists
	 * 
	 * true: if all are true
	 * 
	 * false: if any of them is false
	 * 
	 * @return
	 */
	public boolean yesAllAreRequired() {
		for (Boolean required : listRequired) {
			if (required.booleanValue() == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getBookQCount());
		sb.append(FetchProductCode.FOUR_DASH);
		sb.append(" [ ");
		IOUtil.concat(sb, listRequired, FetchProductCode.FOUR_DASH, "");
		sb.append(" ] ");

		sb.append(FetchProductCode.FOUR_DASH);
		sb.append(" [ ");
		IOUtil.concat(sb, listQ, FetchProductCode.FOUR_DASH, "");
		sb.append(" ] ");

		return sb.toString();
	}

}

package com.studytrails.import2database;

import java.util.ArrayList;
import java.util.List;

public class CategoryInfo implements Comparable<CategoryInfo> {
	public int id;
	public String groupName;
	public List<SubcategoryInfo> listSub = new ArrayList<SubcategoryInfo>();

	public int compareTo(CategoryInfo o) {
		return this.id - o.id;
	}
}

class SubcategoryInfo implements Comparable<SubcategoryInfo> {
	public int subcategoryId;
	public String subcategoryName;

	public int compareTo(SubcategoryInfo o) {
		return this.subcategoryId - o.subcategoryId;
	}
}

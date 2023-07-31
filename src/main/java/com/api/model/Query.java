package com.api.model;

import java.util.ArrayList;
import java.util.List;

import com.api.model.Query;
import com.api.model.Search;
import com.api.model.SearchInfo;

public class Query {

	private SearchInfo searchinfo;

	public SearchInfo getSearchinfo() {
		return searchinfo;
	}

	public void setSearchinfo(SearchInfo searchInfo) {
		this.searchinfo = searchInfo;
	}

	private List<Search> search;

	public List<Search> getSearch() {
		if (this.search == null) {
			this.search = new ArrayList<Search>();
		}
		return this.search;
	}

	public void setSearch(List<Search> searchs) {
		this.search = searchs;
	}

	public String toString() {
		return searchinfo != null ? searchinfo.toString() : "0";
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null && this.getClass() == obj.getClass() && this.searchinfo.equals(((Query) obj).searchinfo)
				&& this.search.size() == ((Query) obj).search.size()) {
			return true;
		}

		return false;

	}

}

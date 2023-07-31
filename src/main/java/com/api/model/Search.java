package com.api.model;

public class Search {

	private String title;
	private String snippet;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private Integer pageid;

	public Integer getPageid() {
		return this.pageid;
	}

	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}

	public String getSnippet() {
		return this.snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null && this.getClass() == obj.getClass() && this.title.trim() == ((Search) obj).title.trim()) {
			return true;
		}

		return false;
	}
}
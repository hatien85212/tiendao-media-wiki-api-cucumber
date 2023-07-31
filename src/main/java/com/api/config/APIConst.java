package com.api.config;

public class APIConst {
	public static final String BASE_URL = "https://en.wikipedia.org/w/api.php";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	public static final String SEARCH_QUERY = "?action=query&format=json&list=search&continue=-||&formatversion=2&srsearch=${searchKey}&sroffset=10";
}

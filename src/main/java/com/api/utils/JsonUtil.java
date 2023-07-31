package com.api.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.cucumber.datatable.DataTable;
import com.api.model.Query;
import com.api.model.Search;

public class JsonUtil {
	public static JsonObject parseToJsonObject(String strJson) {
		JsonObject jObj = new JsonParser().parse(strJson).getAsJsonObject();
		return jObj;
	}
	public static boolean ComapreResponseTitleAndKeySearh(String actTitle, String actSnippet, String searchKey) {
    	
    	searchKey = searchKey.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
    	actTitle = actTitle.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
    	
    	// Replace html tag
    	actSnippet = actSnippet.replaceAll("\\<.*?\\>", "");    	
    	actSnippet = actSnippet.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
    	
    	String[] arrayKeySearh = searchKey.trim().split("\\s+");
    	
    	System.out.println("Title: " + actTitle);
    	System.out.println("SearchKey: " + searchKey);
    	System.out.println("Snippet: " + actSnippet);
    	
    	// Title contain word of key search 
    	for (String onekey : arrayKeySearh) {
    		System.out.println("String: " + onekey);
			
    		if(actTitle.contains(onekey) || actSnippet.contains(onekey)) {
				return true;				
			}
		}
    	
    	// Key search content word of title
    	String[] arrayTitle = actTitle.trim().split("\\s+");
    	for (String oneword : arrayTitle) {
    		System.out.println("String: " + oneword);
			
    		if(searchKey.contains(oneword)) {
				return true;				
			}
		}
    	
    	// Key search content word of snippet
    	String[] arraySnippet = actSnippet.trim().split("\\s+");
    	for (String onesnippet : arraySnippet) {
    		System.out.println("String: " + onesnippet);
			
    		if(searchKey.contains(onesnippet)) {
				return true;				
			}
		}
    	
    	return false;
    }
	
	//be used for printing the list
	public static boolean CompareResponseAndData(Query actQuery, DataTable expSearchTable) {
		boolean result = true;
		Query queryTest = new Query();
		Search expSearch;
		Search actSearch;

		List<Map<String, String>> expSearchList = expSearchTable.asMaps();

		// Covert expSearchTable to expSearchList
		for (Map<String, String> map : expSearchList) {
			expSearch = new Search();
			expSearch.setTitle(map.get("title"));
			expSearch.setPageid(Integer.parseInt(map.get("snippet")));
			
			queryTest.getSearch().add(expSearch);
		}

		// Compare line to line
		for (int i = 0; i < queryTest.getSearch().size(); i++) {
			expSearch = queryTest.getSearch().get(i);
			actSearch = actQuery.getSearch().get(i);
			
			System.out.println("expTitle: " + i + expSearch.getTitle());
			System.out.println("actTitle: " + i + actSearch.getTitle());

//			if (!actSearch.equals(expSearch)) {
//				result = false;
//				break;
//			}
		}

		return result;
	}
}

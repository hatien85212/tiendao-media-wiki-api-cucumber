package com.api.stepdefinition;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.api.config.APIConst;
//
import com.api.model.Query;
import com.api.model.Search;
import com.api.utils.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
//
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//import qa.api.httpURLConnection.RestAPIRequest;

public class SearchWikiMediaSteps {
	private static final Logger LOG = LogManager.getLogger(SearchWikiMediaSteps.class);
	public static Response response = null;
	public static Query query = null;
	public static int actReturnListItem = 0;

	@When("I search by keyword {string}")
	public void i_search_by_keyword(String strSearchKeyword) throws Exception {
		System.out.println("=========Start i_search_by_keyword - strSearchKeyword: " + strSearchKeyword);
		String url = APIConst.BASE_URL + APIConst.SEARCH_QUERY;
		url = url.replace("${searchKey}", strSearchKeyword);
		LOG.info("URL: " + url);
		System.out.println("URL: " + url);
//		apiRequest = new RestAPIRequest(url, "GET");
//		apiRequest.sendRequest();
		response = RestAssured.get(url);
	}

	@Then("I see the response code {int}")
	public void i_see_the_response_code(int expResponseCode) throws Exception {
		System.out.println("=========Start i_see_the_response_code - expResponseCode: " + expResponseCode);
		int actResponseCode = response.getStatusCode();
		Assert.assertEquals(actResponseCode, expResponseCode);
	}

	@Then("Data from jsonPath {string} is {int}")
	public void data_from_json_path_is(String strJsonPath, int expTotalResult) {
		System.out.println("=========Start the_number_of_is..... ");
		System.out.println("JsonPath: " + strJsonPath);
		System.out.println("Expected total result: " + expTotalResult);

//		ResponseBody body = response.getBody();
		JsonPath jsonPath = JsonPath.from(response.getBody().toString());
		int actRotalResult = jsonPath.get(strJsonPath);
		System.out.println("Total Result: " + actRotalResult);
	}

	@Then("result list returns {int} items")
	public void result_list_returns_items(int expReturnListItem) {
		JsonObject jResponseBody = JsonUtil.parseToJsonObject(response.getBody().asString());
		JsonObject jResponseQuery = (JsonObject) jResponseBody.get("query");
		System.out.println("jResponseBody: + " + jResponseBody.toString());
		System.out.println("jResponseQuery: + " + jResponseQuery.toString());

		query = new Gson().fromJson(jResponseQuery.toString(), Query.class);
		actReturnListItem = query.getSearch().size();
		Assert.assertEquals(actReturnListItem, expReturnListItem);
	}

	// The actual search list displays items randomly. So unable to search page
	// content.
	// Please ignore the detail result of this step. Just use to print content
	// between actual and expect list
	@Then("search info should returns {string} as the following")
	public void search_info_should_returns_as_the_following(String string, DataTable dataTable) {
		System.out.println("=========Start search_info_should_returns_as_the_following..... ");
		if (actReturnListItem != 0) // current compare is used to print the list if search item <> 0
			Assert.assertTrue(JsonUtil.CompareResponseAndData(query, dataTable));
	}

	@Then("all result items with title should be match fuzzy search from search keyword {string}")
	public void all_result_items_with_title_should_be_match_fuzzy_search_from_search_keyword(String searchKey) {
		System.out
				.println("=========Start all_result_items_with_title_should_be_match_fuzzy_search_from_search_keyword: "
						+ searchKey);
//boolean result = true;
    	
    	for (Search searchResponse: query.getSearch()) {
    		Assert.assertTrue(JsonUtil.ComapreResponseTitleAndKeySearh(searchResponse.getTitle(),searchResponse.getSnippet(), searchKey));    		
		}
    	
	}
}

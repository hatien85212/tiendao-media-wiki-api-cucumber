package com.api.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty:target/cucumber/cucumber.txt",
//				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"html:target/cucumber/report.html",
				"json:target/cucumber/cucumber.json",
				"com.api.utils.MyTestListener"
		}
		,features= {"src/test/resources/features"}
		,glue = {"com.api.stepdefinition"}
		//,dryRun = true
		,monochrome = true
		,snippets = SnippetType.CAMELCASE
		,tags = "@mediaWikiAPI"
		//,publish = true
		
//		//path of feature file
//		   features = "src/test/resources/features",
//		   //path of step definition file
//		   glue = "qa.api.stepDefinition"
		)
public class TestRunner {
}
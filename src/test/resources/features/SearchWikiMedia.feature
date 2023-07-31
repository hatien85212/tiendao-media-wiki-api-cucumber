#Author: hatien85212@gmail.com
#Date: 30-Jul-2023
#Description: Verify search result (response code, items on page, fuzzy search rule)
@mediaWikiAPI @search
Feature: To search data from Media Wiki

  @MWA_S001 @smokeTest @regression @funtional
  Scenario Outline: Search by keyword and have result
    When I search by keyword "<searchKey>"
    Then I see the response code 200
    And result list returns <itemReturnOnPage> items
    And all result items with title should be match fuzzy search from search keyword "<searchKey>"

    Examples: 
      | searchKey           | itemReturnOnPage |
      | Software Testing    |               10 |
      | Search Testing      |               10 |
      | Exploratory Testing |               10 |

  @MWA_S002 @smokeTest @funtional
  Scenario Outline: Search by keyword and have no result
    When I search by keyword "<searchKey>"
    Then I see the response code 200
    And result list returns <itemReturnOnPage> items

    Examples: 
      | searchKey           | itemReturnOnPage |
      | tientestabc Testing |                0 |
      #| tientestabc         |                0 |

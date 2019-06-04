Feature: Search Scenario
  - User is able to search items from the main page
  - User receives proper message if search returns no results

  Background:
    Given user is on Home Page

  Scenario Outline: Search from the main page
    When user enters <search_string> as search string in the search field and clicks enter
    Then user is redirected to the search result page
    And  <search_string> is displayed at the top
    And <search_results> search results are loaded
    And <test_search_item> as search item is displayed in the results

    Examples:
      |search_string |search_results                |test_search_item|
      |blouse        |1 result has been found.      |Blouse|
      |chiffon dress |2 results have been found.    |Printed Summer Dress|
      |demo_1        |7 results have been found.    |Faded Short Sleeve T-shirts|

  Scenario Outline: Search from the main page
    When user enters <search_string> as search string in the search field and clicks enter
    Then user is redirected to the search result page
    And <zero_search_results> search results are loaded
    And <zero_results_error_message> as search page error message is displayed

    Examples:
      |search_string |zero_search_results       |zero_results_error_message|
      |qwer1234      |0 results have been found.|No results were found for your search "qwer1234"|
      |.             |0 results have been found.|No results were found for your search "."|

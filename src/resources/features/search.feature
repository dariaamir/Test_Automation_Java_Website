Feature: Search Scenario
  - User is able to search items from the main page
  - User receives proper message if search returns no results

  Background:
    Given user is on Home Page

  Scenario Outline: Search from the main page
    When user enters search string in the search field
      |search string  |<search_string>|
    And user clicks Enter
    Then user is redirected to the search result page
    And search results are displayed at the search page
      |search string   |<search_string>|
      |search results  |<search_results>|
      |test search item|<test_search_item>|

    Examples:
      |search_string |search_results                |test_search_item|
      |blouse        |1 result has been found.      |Blouse|
      |chiffon dress |2 results have been found.    |Printed Summer Dress|
      |demo_1        |7 results have been found.    |Faded Short Sleeve T-shirts|

  Scenario Outline: Search from the main page with 0 results
    When user enters search string in the search field
      |search string|<search_string>|
    And user clicks Enter
    Then user is redirected to the search result page
    And zero search results are displayed at the search page
      |zero search result        |<zero_search_result>        |
      |zero results error message|<zero_results_error_message>|


    Examples:
      |search_string |zero_search_result        |zero_results_error_message                      |
      |qwer1234      |0 results have been found.|No results were found for your search "qwer1234"|
      |.             |0 results have been found.|No results were found for your search "."       |

Feature: Search Scenario
  Verify if user is able to search items from the main page

  Scenario Outline: Search from the main page
    Given user is on homepage
    When user enters <search_string> as search_string in the search field
    And user clicks enter
    Then user is redirected to the search result page
    And  <search_string> is displayed at the top
    And <search_results> search results are loaded
    And <test_search_item> as search item is displayed

  Examples:
    |search_string |search_results|test_search_item|
    |blouse        |1 result has been found.|                |
    |chiffon dress |2 results have been found.    |                |
    |demo_1        |7 results have been found.    |                |

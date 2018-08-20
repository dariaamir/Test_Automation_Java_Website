Feature: Search Scenario
  Verify if user is able to search items from the main page

  Scenario Outline: Search from the main page
    Given user is on homepage
    When user enters <seach_string> in the search field
    And user clicks enter
    Then user is redirected to the search result page
    And <seach_string> is displayed at the top
    And search results are loaded
    And <test_search_item> is displayed

  Examples:
    |seach_string |test_search_item|
    |blouse       |                |
    |chiffon dress|                |
    |demo_1       |                |
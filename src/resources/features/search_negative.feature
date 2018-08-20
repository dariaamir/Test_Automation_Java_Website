Feature: Search Scenario Neagative Cases
  Verify if user receives proper message if search returns no results

  Scenario Outline: Search from the main page
    Given user is on homepage
    When user enters <seach_string> in the search field
    And user clicks enter
    Then user is redirected to the search result page
    And zero results found
    And <zero_results_error_message> is displayed

  Examples:
    |seach_string |zero_results_error_message|
    |qwer1234     |                          |
    |.            |                          |

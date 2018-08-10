Feature: Check addition in Google calculatorcontent
  In order to verify that Google calculator work correctly
  As a user of Google
  I should be able to get correct addition result

  Scenario: Addition
    Given I open Google
    When I enter "2+2" in search textbox
    Then I should get result as "4"
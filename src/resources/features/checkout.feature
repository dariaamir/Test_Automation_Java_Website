Feature: Checkout
  User is able to purchase item
  User is redirected between checkout pages correctly

  Scenario: 1st checkout page
    Given user adds an item in the cart
    When user clicks proceed to checkout at the pop-up
    Then user is redirected to the 1st checkout page

  Scenario: 2nd checkout page
    Given user is at the 1st checkout page
    When user clicks proceed to checkout
    Then user is redirected to the 2nd checkout page

  Scenario: 3rd checkout page
    Given user is at the 2nd checkout page
    When user logs in from the checkout page
    Then user is redirected to the 3rd checkout page

  Scenario: 4th checkout page
    Given user is at the 3rd checkout page
    When user clicks proceed to checkout
    Then user is redirected to the 4th checkout page

  Scenario: 5th checkout page
    Given user is at the 4th checkout page
    When user agrees the terms of service
    And user clicks proceed to checkout
    Then user is redirected to the 5th checkout page

  Scenario: End-to-end checkout test
    Given user adds an item in the cart
    When user clicks through all checkout pages
    Then purchase is completed
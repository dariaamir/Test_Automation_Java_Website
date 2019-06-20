Feature: Checkout
  User is able to purchase item
  All checkout pages are displayed correctly


  Scenario: Add item to the cart
    And user clicks proceed_to_checkout at the pop-up
    Then user is redirected to the 1st checkout page
    Then item is displayed at the checkout page
    Then user clicks proceed_to_checkout
    Then user is redirected to the 2nd checkout page
    Then user loggs in from the checkout page
    Then user is redirected to the 3rd checkout page
    Then user clicks proceed_to_checkout
    Then user is redirected to the 4th checkout page
    Then user agrees the terms of service
    Then user clicks proceed_to_checkout
    Then user is redirected to the 5th checkout page
    Then user selects payment by wire
    Then user clicks confirmation button
    Then confirmation message is displayed

  Scenario: 1st checkout page
    Given user adds an item in the cart
    When user clicks proceed_to_checkout at the pop-up
    Then user is redirected to the 1st checkout page
    And 1st checkout page is displayed correctly

  Scenario: 2nd checkout page
    Given is at the 1st checkout page
    When user clicks proceed to checkout
    Then user is redirected to the 2nd checkout page
    And 2nd checkout page is displayed correctly

  Scenario: 3rd checkout page
    Given is at the 2nd checkout page
    When user loggs in from the checkout page
    And user clicks proceed to checkout
    Then user is redirected to the 3rd checkout page
    And 3rd checkout page is displayed correctly

  Scenario: 4th checkout page
    Given is at the 3rd checkout page
    When user clicks proceed to checkout
    Then user is redirected to the 4th checkout page
    And 4th checkout page is displayed correctly

  Scenario: 5th checkout page
    Given is at the 4th checkout page
    When user agrees the terms of service
    And user clicks proceed to checkout
    Then user is redirected to the 5th checkout page
    And 5th checkout page is displayed correctly

  Scenario: End-to-end checkout test
    Given user adds an item in the cart
    When user clicks through checkout pages
    Then purchase is completed
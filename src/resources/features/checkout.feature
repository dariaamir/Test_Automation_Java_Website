Feature: Checkout
  User is able to purchase item

  Scenario: Add item to the cart
    Given user is on item page
    When user clicks add_to_cart button at the item page
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
Feature: Checkout
  User is able to purchase item

  Scenario: Add item to the cart
    Given user is on item page
    When user clicks add_to_cart button at the item page
    And user clicks proceed_to_checkout
    Then user is redirected to the 1st checkout page
    And item is displayed at the checkout page
    And user clicks proceed_to_checkout
    And user is redirected to the 2nd checkout page
    And user loggs in from the checkout page
    And user is redirected to the 3rd checkout page
    And user clicks proceed_to_checkout
    And user is redirected to the 4th checkout page
    And user agrees the terms of service
    And user clicks proceed_to_checkout
    And user is redirected to the 5th checkout page
    And user selects payment by whire
    And user clicks confirmation button
    And confirmation message is displayed
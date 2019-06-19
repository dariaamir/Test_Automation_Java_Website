Feature: Catalogue
  User is able to browse the catalogue

  Scenario Outline: Browse category/subcategory
    Given user is on Home Page
    When user opens category page
      |category  |
      |<category>|
    And user opens subcategory page
      |subcategory  |
      |<subcategory>|
    Then user is able to see test item in the catalogue
      |test_catalogue_item  |
      |<test_catalogue_item>|

    Examples:
      |category |subcategory    |test_catalogue_item        |
      |Women    |Tops           |Faded Short Sleeve T-shirts|
      |Dresses  |Casual Dresses |Printed Dress              |

  Scenario Outline: Hover on category
    Given user is on Home Page
    When user hovers over the category menu item
      |category  |
      |<category>|
    Then user is able to see subcategory
      |subcategory  |
      |<subcategory>|

    Examples:
      |category |subcategory        |
      |Woman    |Blouses            |
      |Woman    |Evening Dresses    |


  Scenario Outline: Select size
    Given user is at the Women category page
    And user selects size
      |size  |
      |<size>|
    And user opens the first item at the category page
    Then size is available for purchase
      |size  |
      |<size>|

    Examples:
      |size|
      |S   |
      |L   |
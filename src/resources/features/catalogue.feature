Feature: Catalogue
  User is able to browse the catalogue

  Scenario Outline: Browse category and subcategory
    Given user is on Home Page
    When user opens <category> as category page
    And user opens <subcategory> as subcategory page
    Then user is able to see <test_catalogue_item> as item in the catalogue

    Examples:
      |category |subcategory    |test_catalogue_item        |
      |Women    |Tops           |Faded Short Sleeve T-shirts|
      |Dresses  |Casual Dresses |Printed Dress              |

  Scenario Outline: Hover on category
    Given user is on Home Page
    When user hovers over the <category> menu item
    Then user is able to see <subcategory> as subcategory

    Examples:
      |category |subcategory        |
      |Woman    |Blouses            |
      |Woman    |Evening Dresses    |


  Scenario Outline: Select size
    Given user is at the Women category page
    And user selects <size> as size
    And user opens the first item at the category page
    Then <size> size is available for purchase

    Examples:
      |size|
      |S   |
      |L   |
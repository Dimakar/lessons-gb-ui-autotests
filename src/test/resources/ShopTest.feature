Feature: Add dresses to the cart

  Background:
    Given Login page is opened

  Scenario Outline: Add dresses to the cart
    Given User "<login>" "<password>" is authorized
    When User go ro page "<firstLevelPage>" -> "<secondLevelPage>"
    And User select "<productNumber>"
    Then Product is added to the cart

    Examples:
      | login                                   | password | firstLevelPage | secondLevelPage | productNumber |
      | autosupertravel+1117140805004@yandex.ru | 12345    | Women          | Summer Dresses  | 1             |
      | autosupertravel+1117140805004@yandex.ru | 12345    | Women          | T-shirts        | 0             |
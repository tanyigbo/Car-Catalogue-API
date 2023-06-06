Feature: TBD

  Scenario: User views all manufacturers
    When User requests a list of all manufacturers
    Then A list of manufacturers is returned

  Scenario: User views manufacturer by id
    When User requests a manufacturer by id
    Then Requested manufacturer is returned
    When User requests a manufacturer by id that does not exist
    Then Error message returned stating manufacture not found

  Scenario: User views all cars by manufacturer
    When User requests a list of all cars by manufacturer
    Then A list of all cars by requested manufacturer is returned

  Scenario: User views all cars
    When User requests a list of all cars
    Then A list of all cars is returned

  Scenario: User views car by id
    When User requests a car by id
    Then Requested car is returned
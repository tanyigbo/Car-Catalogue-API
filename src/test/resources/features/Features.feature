Feature: TBD

  Scenario: User views cars by manufacture
    When User requests a list of all manufacturers
    Then A list of manufacturers is returned

  Scenario: User views all cars
    When User requests a list of all cars
    Then A list of all cars is returned

  Scenario: User views car by id
    When User requests a car by id
    Then Requested car is returned
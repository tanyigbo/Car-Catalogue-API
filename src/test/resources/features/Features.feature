Feature: TBD

  Scenario: User views cars by manufacture
    When User requests a list of all manufacturers
    Then A list of manufacture is returned

  Scenario: User views all cars
    When User requests a list of all cars
    Then A list of all cars is returned
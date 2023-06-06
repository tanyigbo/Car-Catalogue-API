Feature: Car Features

  Scenario: User views all cars
    When User requests a list of all cars
    Then A list of all cars is returned

  Scenario: User views car by id
    When User requests a car by id
    Then Requested car is returned
    When User requests a car by id that does not exist
    Then Error message returned stating car not found
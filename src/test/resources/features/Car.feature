Feature: Car Features

  Scenario: User views all cars
    When User requests a list of all cars
    Then A list of all cars is returned

  Scenario: User views car by id
    When User requests a car by id
    Then Requested car is returned
    When User requests a car by id that does not exist
    Then Error message returned stating car not found

  Scenario: User views all images of car
    When User requests a list of images of a specific car
    Then A list of all images of requested car is returned

  Scenario: User views all reviews of car
    When User requests all reviews of specific car
    Then List of all reviews of requested car is returned
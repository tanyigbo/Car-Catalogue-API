Feature: Review Features

  Scenario: User images of review
    When User requests a list of images of specific review
    Then List of all images of requested review is returned

  Scenario: User creates review
    When User provides new review info
    Then Created review returned

  Scenario: User adds images to a review
    When User provides new image for review
    Then Image is added to the review
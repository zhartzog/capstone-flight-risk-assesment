Feature: Testing the professor table service

  Scenario: The Professor Service can retrieve all objects from the database
    Given a call is made to the Professor Service to get all professors
    When the call to get all professors is made
    Then all of the professors are returned
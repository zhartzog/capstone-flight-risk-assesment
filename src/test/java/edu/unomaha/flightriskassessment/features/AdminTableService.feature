Feature: Testing the admin table service

  Scenario: The Admin Table Service can get thresholds from the database
    Given a call is made to the service class to get the admin threshold with an ID of 3
    When the request is made
    Then an admin threshold is returned

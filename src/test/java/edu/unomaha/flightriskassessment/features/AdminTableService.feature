Feature: Testing the admin table service

  Scenario: The Admin Table Service can get all of the thresholds from the database
    Given a call is made to the service class to get all thresholds
    When the request to get all thresholds is made
    Then all of the admin thresholds are returned
    And the first element is correct
    And the middle element is correct
    And the last element is correct
	Scenario: The Admin Table Service can retrieve thresholds by ID
		Given a call is made to the service class to get a threshold by id
		When the request is made to get a threshold by its ID
		Then The threshold returned is the correct one
	Scenario: The Admin Table Service can create and delete thresholds
		Given a call is made to the service class to create a threshold
		When the request is made to create a threshold
		Then the threshold is created, validated, updated, and deleted
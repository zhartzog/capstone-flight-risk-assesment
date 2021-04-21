Feature: Testing the professor table service

  Scenario: The Professor Service can retrieve all objects from the database
    Given a call is made to the Professor Service to get all professors
    When the call to get all professors is made
    Then all of the professors are returned
  Scenario: The Professor Service can retrieve a professor by their first and last name
  	Given a call is made to the Professor Service to get a professor by their first and last name
  	When the call to get the specific profess is made for the names 'uniqueFirstName' and 'uniqueLastName'
  	Then the specific professor is returned
  Scenario: The Professor Service can create, update, and delete a professor object
  	Given a call is made to the Professor Service to create a professor
  	When the call is made to create a profesor
  	Then the data is validated
  	And the data is updated and the updated is validated
  	And the data is deleted and validated to be deleted
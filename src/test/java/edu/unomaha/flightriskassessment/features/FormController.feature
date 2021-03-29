Feature: Testing the Form Controller

  Scenario: The Form Controller populates a Form Input Object
    Given a request is made to fetch form data
    When that BFI request is made
    Then the basic form object is populated

Feature: Testing the AWC Controller

  Scenario: The AWC Controller populates a METAR object
    Given a request is made to fetch metar data for "KOMA"
    When that AWC request is made
    Then the METAR object is populated

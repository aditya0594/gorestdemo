Feature: To get the all user list

  Scenario: To list of all user
    Given Payload for the users
    When User call "Getuser" with "GET" http request
    And I should receive a list of users
    And Each user should have id, name, email, gender, and status fields

  Scenario: Verify the user details by ID
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user is created and Get ID from the response
    And Verify user details by ID
    Then I should receive the user details

  Scenario: Verify user details with invalid ID format (string)
    When User calls "GetuserDetails" with "GET" http request using invalid ID "9999999"
    Then Status code should be 404
    And The error message should be "Resource not found"

  Scenario: Verify user details with invalid ID format (string)
    When User calls "GetuserDetails" with "GET" http request using invalid ID "abc"
    Then Status code should be 404
    And The error message should be "Resource not found"

  Scenario: Verify user details with special character ID
    When User calls "GetuserDetails" with "GET" http request using invalid ID "!@#"
    Then Status code should be 404
    And The error message should be "Resource not found"


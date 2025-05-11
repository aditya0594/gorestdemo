Feature: To get the all user list

  @getUser
  Scenario: To list of all user
    Given Payload for the users
    When User call "Getuser" with "GET" http request
    And I should receive a list of users
    And Each user should have id, name, email, gender, and status fields

  @getUser @regression
  Scenario: Verify the user details by ID
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user is created and Get ID from the response
    And Verify user details by ID
    Then I should receive the user details

  @getUser @regression
  Scenario Outline: Verify user details with invalid ID "<InvalidID>"
    When User calls "GetuserDetails" with "GET" http request using invalid ID "<InvalidID>"
    Then Status code should be 404
    And The error message should be "Resource not found"

    Examples:
      | InvalidID |
      | 9999999   |
      | abc       |
      | !@#       |
Feature: To verify the update user API

  Background:
    Given Update the user create data
    When User call "AddUser" with "POST" http request
    Then Then The response status code should be 201 Get the created user id
    When User call "Putuserdata" with "PUT" http request for record update
    And Verify that the updated name, email, gender, status

  @UpdateUser
  Scenario: Update user with blank email
    When User calls "Putuserdata" with "PUT" http request using missing "email"
    Then The error message "email" should contain "can't be blank"

  @UpdateUser
  Scenario: Update user with missing name
    When User calls "Putuserdata" with "PUT" http request using missing "name"
    Then The error message "name" should contain "can't be blank"

  @UpdateUser @regression
  Scenario: Update user with missing gender
    When User calls "Putuserdata" with "PUT" http request using missing "gender"
    Then The error message "gender" should contain "can't be blank, can be male of female"

  @UpdateUser @regression
  Scenario Outline: Update user with empty <Field>
    When User calls "Putuserdata" with "PUT" http request using missing "<Field>"
    Then The error message "<Field>" should contain "<ErrorMessage>"

    Examples:
      | Field  | ErrorMessage                          |
      | status | can't be blank                        |
      | email  | can't be blank                        |
      | name   | can't be blank                        |
      | gender | can't be blank, can be male of female |
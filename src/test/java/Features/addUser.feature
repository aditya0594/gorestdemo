Feature: Validating place Api
  @addUser
  Scenario: Verify the add user API
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user is created
  @addUser
  Scenario: Verify that created user id is created
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user ID is present or not

  @addUser
  Scenario Outline: Verify that create user with missing "<Field>" field
    Given Add user payload with the missing "<Field>"
    When User call "AddUser" with "POST" http request
    Then It should show the expected "<Field>" and "<ErrorMessage>" message

    Examples:
      | Field  | ErrorMessage                          |
      | name   | can't be blank                        |
      | email  | can't be blank                        |
      | gender | can't be blank, can be male of female |
      | status | can't be blank                        |

  @addUser
  Scenario: Create user with the same email id
    Given Add user payload with same email
    When User call "AddUser" with "POST" http request
    And It should show the email already taken
Feature: Validating place Api

  Scenario: Verify the add user API
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user is created

  Scenario: Verify that created user id is created
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user ID is present or not

  Scenario: Verify that create user with missing name field
    Given Add user payload with missing name
    When User call "AddUser" with "POST" http request
    And Verify Name is missing or empty!

  Scenario: Verify that create user with missing email field
    Given Add user payload with the missing email
    When User call "AddUser" with "POST" http request
    And It should show the Email is missing or empty! message

  Scenario: Verify that create user with missing Gender field
    Given Add user payload with the missing Gender
    When User call "AddUser" with "POST" http request
    And It should show the Gender is missing or empty! message

  Scenario: Verify that create user with missing Status field
    Given Add user payload with the missing Status
    When User call "AddUser" with "POST" http request
    And It should show the Status is missing or empty! message

  Scenario: Create user with the same email id
    Given Add user payload with same email
    When User call "AddUser" with "POST" http request
    And It should show the email already taken
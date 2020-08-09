Feature: API TESTING

  @GET
  Scenario: REST API GET REQUEST
    Given Validate the number of users in the user list GET service url
    When I send the GET request
    And I validate reposnse status 200
    And I validate the count of users 100

  @PUT
  Scenario: REST API PUT REQUEST
    Given I provide the PUT request url
    Then I user update the title value in json and send PUT request
      | title |
      | PK    |
      | EB    |
    And I validate reposnse status 200

  @POST
  Scenario: REST API POST REQUEST
    Given I provide the POST request url
    Then I send the POST request with the payload
    And I validate reposnse status 200
    And I validate title from reposnse body

  @DELETE
  Scenario: REST API DELETE REQUEST
    Given I provide the Delete request url
    Then I send the DELETE request
    And I validate reposnse status 200
    And I validate response body is empty

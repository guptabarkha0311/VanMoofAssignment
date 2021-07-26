Feature: Post feature of the API

  Scenario: Get the Post API Validation
    Given : I am at the API home page
    Then : I get all the Posts
    And : I validate posts response code and valid json content
    Then : I validate posts reference by the comments exist

Feature: VanMoof S3 Page testing

Scenario: Test URLs and redirection on VanMoof S3 page.
    Given : I am at the S3 page
    Then  : I verify the current url
    And   : I get all the links on the page 
    Then  : I verify response code of all links


@Errorvalidations
Feature: ErrorValidations
  

  @tag2
  Scenario Outline: Negative test of logging in to landing page
   Given I landed on Ecommerce page
   Given Logged in with username <name> and password <password>
   Then "Incorrect email or password." message is displayed on the landing page

    Examples: 
      | name               | password    | 
      | shishi@example.com | Shishira1   | 
     
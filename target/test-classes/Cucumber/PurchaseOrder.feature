
@tag
Feature: Purchase order from e-commerce website
  
 Background:
 Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to the cart
    And Checkout the <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name               | password     | productName  |
      | shishi@example.com | Shishira1!   | ZARA COAT 3  |
     

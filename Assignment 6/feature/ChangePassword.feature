Feature: JPetStore Change Password

  Scenario Outline: Test JPetStore Change Password
    Given I open FireFox browser
    And I go to JPetStore Sign on page
    And I enter user info "<username>" and password "<password>"
    And I select My Account
    When I enter a new password "<newpassword>" and repeated password "<repeatpassword>"
    And click on save account information
    Then should be able to login with the "<newpassword>" and current "<username>"

    Examples: 
      | username | password | newpassword | repeatpassword |
      | quality3 | 12345678 |        1234 |           1234 |

  Scenario Outline: Test JPetStore Change Password with mismatch
    Given I open FireFox browser
    And I go to JPetStore Sign on page
    And I enter valid "<username>" and "<password>" combination
    And I select My Account
    When I enter a new password "<newpassword>" and repeated password "<repeatpassword>"
    And click on save account information
    Then should get an error message

    Examples: 
      | username | password | newpassword | repeatpassword |
      | quality3 | 1234 |        4321 |           1234 |

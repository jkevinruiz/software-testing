Feature: JPetStore Create Account

  Scenario Outline: Test JPetStore Account creation valid information
    Given I open firefox browser
    And I go to JPetStore registration page
    When I enter the user information "<username>", "<newpassword>", "<repeatedpassword>"
    And account information "<firstname>", "<lastname>", "<email>", "<phone>", "<address1>", "<city>", "<state>", "<zip>", "<country>"
    And I click on save account information
    Then I should be able to login with the new account using the new "<username>" and "<newpassword>"

    Examples: 
      | username | newpassword | repeatedpassword | firstname | lastname | email              | phone      | address1    | city    |	state	| zip     | country |
      | test1    |        1234 |1234							| test      | one      | testone@domain.com | 4039996666 | mount royal | calgary | alberta	|	1x1 2x2 | canada  |
			| test2    |        1234 |1234 							| test      | two      | testtwo@domain.com | 4039996666 | mount royal | calgary | alberta	|	1x1 2x2 | canada  |
	
	Scenario Outline: Test JPetStore Account empty required field or password mismatch
    Given I open firefox browser
    And I go to JPetStore registration page
    When I enter the user information "<username>", "<newpassword>", "<repeatedpassword>"
    And account information "<firstname>", "<lastname>", "<email>", "<phone>", "<address1>", "<city>", "<state>", "<zip>", "<country>"
    And I click on save account information
    Then I should get an error message

    Examples: 
      | username | newpassword | repeatedpassword | firstname | lastname | email              | phone      | address1    | city    |	state	| zip     | country |
      | test9  | 1234 | x | test | one      | testone@domain.com | 4039996666 | mount royal | calgary | alberta	|	1x1 2x2 | canada  |
			| test5  | 1234 | 1234 |test  |      | testtwo@domain.com | 4039996666 | mount royal | calgary | alberta	|	1x1 2x2 | canada  |
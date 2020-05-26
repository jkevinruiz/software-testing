Feature: JPetStore Login
	Scenario Outline: Test JPetStore Login with valid username and password
		Given I open Firefox browser
		And I go to JPetStore page
		And I select Enter the Store
		And I select Sign in
		When I enter user info "<username>" and "<password>" combination 
		Then I should be able to login successfully
		
	Examples:
	 | username | password |
	 | j2ee| j2ee |
	 | quality1| 12345678 |
	 | quality2| 12345678 |
	 
	Scenario Outline: Test JPetStore Login with invalid username and password
		Given I open Firefox browser
		And I go to JPetStore page
		And I select Enter the Store
		And I select Sign in
		When I enter user info "<username>" and "<password>" combination 
		Then I should get an error message for invalid username or password
		
	Examples:	
	 | username | password |
	 | j2ee| j2e1 |
	 | quality2| 1235678 |

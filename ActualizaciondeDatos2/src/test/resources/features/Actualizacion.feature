#Author: William Steearing Cordoba Mosquera
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@End2End
Feature: Update data
  Verify if user is able to Login in to the site
    
 @End2End
  Scenario: Update data
    Given Main configuration
    When user navigates to Login Page
    When user update data
    When user logout
    And user close window
    Then user validate on API
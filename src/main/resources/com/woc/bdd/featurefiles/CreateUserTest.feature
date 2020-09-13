#Write the test case in BDD(Behaviour Driven Development) style
Feature: Need to test the functionality of user creation using rest api testing

  Scenario Outline: create user with name and job
    Given Set the base url
    When Enter the Name <name> and Job <job> hit the post request
    Then verify the response

    #user's credentials input
    Examples:
    |name|job|
    |"piyus bdd test"   |"student"|
    |"sonu kumar bdd test"   |"employee"|
    |"sonu kumar bdd sdet"   |"celebrity"|
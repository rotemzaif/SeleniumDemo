### Project Description

This project includes automated tests for the following page: http://the-internet.herokuapp.com/ which contains links for different pages.
The tests cover the following pages:
1. Checkboxes - toggling checkboxes state and verifying updated state
2. Frames & iFrames - moving to a different frame and entering test in editor
3. Dynamic Loading - moving to dynamic loading page and from there to another link.
   In the new page, waiting till Loading is completed and verifying text displayed. 
4. JQueryUI-Menu - selecting a couple of menus and eventually downloading an excel file and verifying it was downloaded

### Technical Details
OS:     Window 10 64 bit

IDE:    intelliJ

Project Type: Maven

Frameworks:

    * Selenium
    * TestNG

Programming Language: JAVA (SDK 1.8)

Browser: Chrome

Setup & prerequisites

    * no specific setup or prerequisites are required
 
Design Patterns
 
    * POM (Page Object Module)
    * PageFactory
    
Project Structure

    * src/main/java - 2 packages
        -   pageObjects - contains all pages objects classes
        -   utils - include a Utils class which consists of assistance methods
    * src/main/resources - 1 package
        -   data - includes the configuration.properties file 
    * src/test/java - 1 package
        - tests - consists of all tests classes
        
Tests Execution

2 options:

1. run WelcomeToTheInternet TestNG template (in the run configuration)
2. run testing.xml under the project path

Issues Experienced

1. Checkboxes test
    * couldn't extract checkboxes labels in order to access each checkboxes by name
    * solved by accessing each checkbox via list index
    
2.  Basic Auth page test
    * there was an issue in which entering admin/admin and from this point on the popup/alert was not displayed and always showed the Basic Auth page.
      Solved by clearing browser cash.
    * this test is not included in the test suite since I was unable to access the alert and enter credential
    * I managed to solve this issue and added the test:
      I used the following source to add authentication support:
      https://www.lambdatest.com/blog/handling-login-popup-in-selenium-webdriver-using-java/
      I also created a 'clear cache' method which accessed the chrome settings and clears the passwords cache in the last hour.  
      For this purpose I added o.github.sukgu dependency in the pom file.
    
3.  Application Error
    * the application crashed and was down for a while which created a small delay

 
# VanMoofAssignment

## BDD framework for Automation Testing Using Selenium , TestNG, Java and Rest-Assured

This is the test automation framework developed for web application Vanmoof and for testing API of the site https://jsonplaceholder.typicode.com. This automation framework is developed using Selenium, Cucumber, Java, Maven and Rest-Assured for web and API testing.

## Framework Description

It is a Behavior Driven Development (BDD) approach to write automation test script to test Web applications and for API automation. It enables you to write and execute automated  test scripts using the Gherkin language and for testing the API's using the Rest Assured libraries. 
The framework has a Maven based structure where dependencies are specified in the pom.xml file. 
API calls & validations are made using RestAssured.

**The framework has following features**

- Maven based framework
- Log4j enabled for logging
- Report Generation (cucumber-reporting)
- Page Object Model design framework
- Different browser support (using tag @chrome,@firefox)
- Rest-Assured for making API Calls
- Feature file in Gherkin LAnguage

## Installation
**
Prerequisite: You need to have following softwares installed on your computer**
- Install JDK and set path
- Install Maven and set path
- Eclipse installed
- Eclipse Plugins: Maven and Cucumber
- Installation: In order to start using the project you need to create your own Fork on Github and then clone the project.

## Executing test cases in the framework

**Navigate to your project directory from terminal and run following commands**

- `mvn clean generate-sources test` (defualt will run on local chrome browser)
- `mvn test "-Dbrowser=chrome` (to use any other browser)

## Cucumber Report

![image](https://user-images.githubusercontent.com/87953458/126920468-5474872f-74ff-456c-82b3-2ab51e9f855d.png)

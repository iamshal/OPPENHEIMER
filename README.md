# OPPENHEIMER
To test for functional requirements tools used--- Selenium, TestNG Framework
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
To test for non functional requirements--- RestAssured for API testing
------------------------------------------------------------------------------------------------------------------------------------------------------------------
Test cases covered : 
 (1) As the Clerk, I should be able to insert a single record of
working class hero into database via an API
AC1: Single record of a working class hero should consist of Natural Id
(natid), Name, Gender, Birthday, Salary and Tax paid
(2) As the Clerk, I should be able to insert more than one working
class hero into database via an API
AC1: Enhancement of (1) , with the ability to insert a list
(3) As the Clerk, I should be able to upload a csv file to a portal so
that I can populate the database from a UI
AC1: First row of the csv file must be natid, name, gender, salary,
birthday, tax
AC2: Subsequent rows of csv are the relevant details of each working
class hero
AC3: A simple button that allows me to upload a file on my pc to the
portal
(4) As the Bookkeeper, I should be able to query the amount of tax
relief for each person in the database so that I can report the
figures to my Bookkeeping Manager
AC1: a GET endpoint which returns a list consist of natid, tax relief
amount and name
AC2: natid field must be masked from the 5th character onwards with
dollar sign ‘$’
AC3: computation of the tax relief is using the formula as described:
2
*AC - Acceptance Criteria
AC4: After calculating the tax relief amount, it should be subjected to
normal rounding rule to remove any decimal places
AC5: If the calculated tax relief amount after subjecting to normal
rounding rule is more than 0.00 but less than 50.00, the final tax
relief amount should be 50.00
AC6: If the calculated tax relief amount before applying the normal
rounding rule gives a value with more than 2 decimal places, it should
be truncated at the second decimal place and then subjected to normal
rounding rule
(5) As the Governor, I should be able to see a button on the screen so
that I can dispense tax relief for my working class heroes
AC1: The button on the screen must be red-colored
AC2: The text on the button must be exactly “Dispense Now”
AC3: After clicking on the button, it should direct me to a page with a
text that says “Cash dispensed”
----------------------------------------------------------------------------------------------------------------------------------------

To locate the app goto url  
https://github.com/auronsiow/oppenheimer-project-dev
and follow instructions as per README.


To execute the Project, install Maven on your machine
To Execute Testcases use testng.xml or pom.xml

Testevidence calculation sheet for story 4 -----Testdata-Evidence, located at the root of src in framework.


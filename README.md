# mizu_signin_automation

This project automates the functional testing of the member login page of the Mizu website. In this project, Selenium Webdriver was used as a web automation tool, JUnit for unit testing, Java as a software language, Visual Studio Code as a code editor and chrome driver as a driver.

Selenium WebDriver is a browser automation framework that accepts commands and sends them to a browser. It is implemented via a browser-specific driver. It communicates directly with the scanner and controls it. In order to perform each step of the test case, whose scenario was written with Selenium Webdriver, the relevant areas of the web page were accessed. Each test case is written as a function.

The input and output control of every test-case function written in Junit is provided using JUnit's notations.

Java is a program that can run independently of any computer architecture and platforms. Java, which has many different features; It is an open source, object-oriented, multi-functional, high-level language, highly efficient, platform-independent, step-by-step application.

This project is built with Java-Maven. In order for the project to be compiled correctly, the Chrome Driver added to the project must be 100 or higher.

#How to run JUnit test cases from the command line?

Maven way

If you use Maven, you can run the following command to run all your test cases:

mvn clean test
Or you can run a particular test as below

mvn clean test -Dtest=your.package.TestClassName
mvn clean test -Dtest=your.package.TestClassName#particularMethod
If you would like to see the stack trace (if any) in the console instead of report files in the target\surefire-reports folder, set the user property surefire.useFile to false. For example:

mvn clean test -Dtest=your.package.TestClassName -Dsurefire.useFile=false


Normal way

If you do not use Maven, or Gradle or Ant, you can follow the following way:

First of all, you need to compile your test cases. For example (in Linux):

javac -d /absolute/path/for/compiled/classes -cp /absolute/path/to/junit-4.12.jar /absolute/path/to/TestClassName.java

Then run your test cases. For example:

java -cp /absolute/path/for/compiled/classes:/absolute/path/to/junit-4.12.jar:/absolute/path/to/hamcrest-core-1.3.jar org.junit.runner.JUnitCore your.package.TestClassName




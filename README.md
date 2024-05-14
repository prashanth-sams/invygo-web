# Invygo Web Test Automation Challenge


### Overview

#### Pre-requisites

* JDK 21
* Google Chrome Latest; eg., 124.0.6367.158
* ChromeDriver Latest

#### Automation Framework

* [Selenium WebDriver](https://www.selenium.dev/)

#### Testing Framework

* [JUnit 5](https://junit.org/junit5/)

#### Build Tool

* [Gradle](https://gradle.org/)

### How to Run

#### Run Tests

##### macOS/Linux

```
xattr -d com.apple.quarantine /usr/local/bin/chromedriver

./gradlew clean test
```

##### Windows

```
gradlew.bat clean test
```

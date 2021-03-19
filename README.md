### Separating Unit from Integration tests in Java using Gradle

##### Question: 
Should I separate unit tests and integration tests?

In general: <b>yes</b>, 

we should put unit tests and integration tests into different folders. Often, there isn't a clear line between these two kinds of tests and just write whatever kind of test is useful. But integration tests tend to be slower, because they often involve:

* Database queries
* Network requests
* Time-dependent behaviour
* Large amounts of data
* Boot partially or full Application (Servlet Container)

In contrast, an unit test would mock any expensive operations, so unit tests tend to run quickly.

Whatever allows a programmer to get feedback quickly is good.

In his ![blog](https://martinfowler.com/articles/practical-test-pyramid.html), Martin Fowler described the following pyramid
![Test Pyramid](https://martinfowler.com/bliki/images/testPyramid/test-pyramid.png)

we should have: 
* 60% Unit Tests
* 30% Component-, Integration- and API-Tests
* 10% GUI Tests

Building, testing and deploying an ever-increasing amount of software manually soon becomes impossible 
— unless you want to spend all your time with manual, repetitive work instead of delivering working software. 
Automating everything — from build to tests, deployment and infrastructure — is your only way forward.

![Build Pipeline](https://martinfowler.com/articles/practical-test-pyramid/buildPipeline.png)

There are many ways how this can be done.

### Separating Unit from Integration tests in Java using Gradle
 
> <b>Step 1</b>.
  Include jcenter as a source for your build script dependencies and pull in the [gradle-testsets-plugin](https://github.com/unbroken-dome/gradle-testsets-plugin) dependency
  ```
  buildscript {  
    repositories {
      jcenter()
    }
    dependencies {
      classpath 'org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:1.0.2'
    }
  }
  ```
 
***
  
> <b>Step 2</b>.
  Apply the plugin to the build. Be sure to activate this after the java plugin and before any plugins which may build off the gradle tasks automatically created by the plugin.
  ```
  apply plugin: 'org.unbroken-dome.test-sets'
  ```

***
  
> <b>Step 3</b>.
  Create the new test set definition and configuration. Here we want to add an integration test suite but this could be any category of tests you wish to scope together.
  
  ```
  testSets {  
    integrationTest
  }
  ```
  
>  Ensure that the check step executes the new test definition and that the new integrationTest step runs after the normal test (unit) step.

  ```
  check.dependsOn integrationTest  
  integrationTest.mustRunAfter test 
  ```
  
>  Ensure that integration tests are always run regardless if they passed on previous runs
  
  ```
  project.integrationTest {  
    outputs.upToDateWhen { false }
  }
  ```
  
>  Finally ensure that the output for tasks of type Test are namespaced appropriately so reports are separated for the test (unit) and integrationTest tasks

  ```
  tasks.withType(Test) {  
    reports.html.destination = file("${reporting.baseDir}/${name}")
  }
  ```
 
***
  
> <b>Step 4</b>.
  Test compile dependencies should be reviewed and the new integrationTestCompile dependencies declared appropriately 
  e.g.
  
  ```
  testCompile("junit:junit")  
  integrationTestCompile("org.springframework.boot:spring-boot-starter-test")
  ```

***
  
> <b>Step 5</b>.
  Restructure your test file layout. Your directory structure should look something like the following.
  
  ```
  src/  
    main/
      java/...
      resources/...
    integrationTest/
      java/...
      resources/...
    test/
      java/...
      resources/...
  ```
  
>  At this point you should be able to run *gradle clean build* and see your separate test and integrationTest related tasks execute.

***

> <b>Real time test reporting</b>.
  To see a visual report of test execution and outcome as it happens in the console, add the following
  
 ```
 test {
     afterTest { desc, result ->
         println "Executing test [${desc.className}].${desc.name} with result: ${result.resultType}"
     }
 }
 integrationTest {
     afterTest { desc, result ->
         println "Executing test [${desc.className}].${desc.name} with result: ${result.resultType}"
     }
 }
 ```
 
 ***
 
 > <b>apply plugin: jacoco</b>.
 Using Jacoco for test coverage with the help of the Jacoco gradle plugin. While it would be ideal to have separate test coverage for integration and unit test suites in separate reports I was unable to find a simple method to generate them independently. However you can combine the coverage from both suites with the following;
 
```
apply plugin: 'jacoco'  
.....

jacoco {
    toolVersion = "0.7.5.201505241946"
}

jacocoTestReport {
    reports {
        xml.enabled false
        csv.enabled false
        html{
            enabled true
        }
    }
    executionData(test, integrationTest)
}

tasks.build.dependsOn(jacocoTestReport)
```

> The separation of test types has many benefits including:

* <b>Fail fast</b> unit tests from potentially costly integration tests allowing finer control over CI builds and development process.
* Done right, unit tests <b>speed up</b> the development cycles.
* Forcing developers to think about test types and purpose enforcing unit test conventions. 

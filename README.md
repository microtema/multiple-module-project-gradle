### Why should I separate unit tests and integration tests?

In general: <br>yes</b>, you should put integration tests and unit tests into different folders. Often, there isn't a clear line between these two kinds of tests and just write whatever kind of test is useful. But integration tests tend to be slower, because they often involve:

* Database queries
* Network requests
* Time-dependent behaviour
* Large amounts of data
* Boot partially or full Application (Servlet Container)

In contrast, an unit test would mock any expensive operations, so unit tests tend to run quickly (in fact, the slowest part of running the test is often the test framework itself).

Whatever allows a programmer to get feedback quickly is good.

Considering the Test Pyramid:
![Test Pyramid](https://www.360logica.com/blog/wp-content/uploads/2014/07/A-sneak-peek-into-test-framework-test-pyramid-testing-pyramid.png)

we should have: 
* 60% Unit Tests
* 30% Component-, Integration- and API-Tests
* 10% GUI Tests

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

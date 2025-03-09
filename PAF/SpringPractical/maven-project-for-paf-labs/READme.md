# Read Me First

The following was discovered as part of building this project:

- The original package name 'com.example.paf.maven-project-for-paf-labs' is invalid and this project uses 'com.example.paf.maven_project_for_paf_labs' instead.

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
- [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## How to run this project

- to run the application you need some of requirements

  1. Need to install java on your computer if you trying to run this on your local machine

  - install java 18 or above

  2. And need to install the Maven to your local machine.

  - here is the web site that help you to install the Maven to your local machine [Click here](https://phoenixnap.com/kb/install-maven-windows)
  - here is the location where that you can download the maven binary zip archive [Click here](https://maven.apache.org/download.cgi)

- then run the following command to build your project

  - `mvn clean install` to install the dependencies and build the jar file in the target directory
  - `java -jar target\maven-project-for-paf-labs-0.0.1-SNAPSHOT.jar` to run the snapshot that we build.

- here is the sample dependencies that i used in this project

  ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
  ```

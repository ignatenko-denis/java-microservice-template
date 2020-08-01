# java-microservice-template

1. Build - mvn clean install
1. Run - ./run.sh or mvn spring-boot:run
1. Check
    1. http://localhost:8081/
    1. http://localhost:8081/echo
    1. http://localhost:8081/actuator/health
    1. http://localhost:8081/actuator/info

This tiny project is a sample of usage:
1. Java 13
1. [Spring Boot](https://spring.io/projects/spring-boot)
1. [Spring Data](https://spring.io/projects/spring-data#overview)
1. REST JSON Request/Response
1. [Protocol Buffers version 3](https://developers.google.com/protocol-buffers/docs/proto3)
1. [Lombok](https://projectlombok.org/)
1. [Liquibase](https://www.liquibase.org/)
1. [Logback](http://logback.qos.ch/)
1. [DBUnit](http://dbunit.sourceforge.net/)
1. [Checkstyle](https://checkstyle.org/checks.html)
1. [PMD](https://pmd.github.io/pmd-6.0.0/pmd_rules_java.html)
1. [PostgreSQL](https://www.postgresql.org/)
1. [HSQLDB](http://hsqldb.org/)
1. [Maven](https://maven.apache.org/)

***

[Liquibase and Maven](https://docs.liquibase.com/tools-integrations/maven/commands/home.html)

Prints which changesets need to be applied to the database.
```ssh
mvn liquibase:status
```

Before update run this and check SQL-script in /target/liquibase/migrate.sql
```ssh
mvn liquibase:updateSQL
```

```ssh
mvn liquibase:update -e -Dorg.slf4j.simpleLogger.defaultLogLevel=DEBUG
```

Before rollback run this and check SQL-script in /target/liquibase/migrate.sql 
```ssh
mvn liquibase:rollbackSQL -Dliquibase.rollbackCount=1
```

```ssh
mvn liquibase:rollback -Dliquibase.rollbackTag=01.00.00 -e -Dorg.slf4j.simpleLogger.defaultLogLevel=DEBUG
```

Rollback the one step back
```ssh
mvn liquibase:rollback -Dliquibase.rollbackCount=1
```
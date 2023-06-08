## Example gradle multi-module hexagonal application

running tests: \
```./gradlew clean check ``` \
```./gradlew clean test testIntegration ```

&nbsp;

### Spring
run: \
```./gradlew clean spring-runnable:bootRun```
bootJar: \
```./gradlew clean spring-runnable:bootJar``` \
run bootJar: \
```java -jar runnable/spring-runnable/build/libs/spring-runnable-*.jar```

&nbsp;

### Micronaut
run: \
```./gradlew clean micronaut-runnable:run```
bootJar: \
```./gradlew clean micronaut-runnable:build``` \
run bootJar: \
```java -jar runnable/micronaut-runnable/build/libs/micronaut-runnable-*.jar```

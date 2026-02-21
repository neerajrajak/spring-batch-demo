# Spring Batch Demo

A small Spring Boot project demonstrating Spring Batch concepts (jobs, steps, readers, processors, writers). This repository contains example batch jobs and typical configuration used to run, test and extend batch processing.

## Features
- Spring Boot application starter for Spring Batch
- Example Job(s) demonstrating item reading, processing and writing
- Configuration via `application.yml` / `application.properties`
- Maven build and runnable fat JAR

## Prerequisites
- Java 17+ (or the Java version declared in the project)
- Spring BOOT 4.0
- Maven 3.6+
- (Optional) Docker/Database if the project uses an external job repository / datasource

## Build
From repository root:
- mvn clean package

Or run directly in development:
- mvn spring-boot:run

## Run
Run the packaged JAR:
- java -jar target/spring-batch-demo-0.0.1-SNAPSHOT.jar

To execute specific Spring Batch job(s) at startup:
- java -jar target/spring-batch-demo-0.0.1-SNAPSHOT.jar --spring.batch.job.names=yourJobName

You can also pass job parameters:
- java -jar target/spring-batch-demo-0.0.1-SNAPSHOT.jar --spring.batch.job.names=yourJobName job.date=2023-01-01

If the project exposes an HTTP endpoint to launch jobs, check the controller/docs in `src/main/java` for details.

## Configuration
Primary configuration files:
- `src/main/resources/application.yml` (or `application.properties`) — datasource, job repository, job parameters, logging, etc.

Common settings:
- `spring.datasource.*` for the job repository (if using a persistent DB)
- `spring.batch.initialize-schema=always|never|embedded` depending on DB setup
- `spring.batch.job.names` to select jobs on startup

## Project layout (typical)
- src/main/java/... — application, job configurations, readers/processors/writers, controllers
- src/main/resources — application.yml, SQL schema (if present), test data
- src/test — unit and integration tests

## Testing
- mvn test
- For integration tests that require DB or Spring context, configure test profiles or use embedded DB.

## Extending the demo
- Add new Job beans in a `@Configuration` class
- Implement `ItemReader`, `ItemProcessor`, and `ItemWriter` beans
- Use `JobLauncher` or an HTTP endpoint to trigger jobs programmatically

## Troubleshooting
- If jobs don't start, check `spring.batch.job.names` and application logs for missing beans or datasource errors.
- For corrupted batch metadata, clear Spring Batch metadata tables (only in development).

## Contributing
- Fork the repo, create a feature branch, open a pull request.
- Follow existing coding style and add tests for new behavior.

## License
- Add or update the license file (e.g., MIT, Apache-2.0) as appropriate for this repository.



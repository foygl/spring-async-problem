# Proof of concept for problem with Spring @Async blocking

There is an issue introduced in Spring 4.2.6.RELEASE (used by Spring Boot 1.3.4.RELEASE) which means that it is possible for `@Async` annotated method calls to become blocked. This happens when you have nested `@Async` annotated calls which use the default task executor, and you have a task executor defined in your application context (but not explicitly used).

## Running proof of concept

`./gradlew clean bootRun`

Expected: Should log that each of the two async methods has been run 5 times then exit.

Actual: Logs first async method being called then hangs indefinitely.

## Analysis

Removing the `unusedTaskExecutor` from `AppConfig` or increasing its concurrency limit to 10+ (which is the number of async calls we make in this example) makes the blocking problem go away. This implies that this bean is overriding the default task executor created by Spring.

Removing the nested async call causes the problem to go away which implies this is only an issue for nested async calls (or there is something else subtle going on).

Downgrading the Spring Boot version to 1.3.3.RELEASE (i.e. Spring 4.2.5.RELEASE) makes the problem go away, so this was introduced in Spring 4.2.6.RELEASE. Upgrading to Spring Boot version 1.4.0.RC1 (i.e. Spring 4.3.1.RELEASE) shows this problem still exists in the latest version.

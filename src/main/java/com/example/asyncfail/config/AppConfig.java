package com.example.asyncfail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * Spring configuration for the application.
 */
@Configuration
@EnableAsync
public class AppConfig {

    /*
     * Note: Removing this bean or increasing the concurrency limit to 10+ makes the blocking problem go away which
     * implies that this bean is overriding the default task executor created by Spring.
     */
    @Bean(name = "unusedTaskExecutor")
    public Executor unusedTaskExecutor() {
        final SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(1);
        return executor;
    }
}


package com.example.asyncfail;

import com.example.asyncfail.async.AsyncBean1;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class);

    @Autowired
    private AsyncBean1 asyncBean1;

    @Override
    public void run(final String... args) throws Exception {
        LOGGER.info("Running application");

        List<Future<Boolean>> eventConsistencyResults = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            eventConsistencyResults.add(asyncBean1.callAsync1(i));
        }

        for (Future<Boolean> eventConsistencyResult : eventConsistencyResults) {
            eventConsistencyResult.get();
        }
    }
}

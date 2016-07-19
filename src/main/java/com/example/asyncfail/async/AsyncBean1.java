package com.example.asyncfail.async;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class AsyncBean1 {

    private static final Logger LOGGER = Logger.getLogger(AsyncBean1.class);

    @Autowired
    private AsyncBean2 asyncBean2;

    @Async
    public Future<Boolean> callAsync1(final int count) throws ExecutionException, InterruptedException {
        LOGGER.info(count + ": Async call to async bean 1.");

        final Future<Boolean> eventIdsFromMongoTask = asyncBean2.callAsync2(count);

        eventIdsFromMongoTask.get();

        return new AsyncResult<>(true);
    }
}

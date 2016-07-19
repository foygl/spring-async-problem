package com.example.asyncfail.async;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncBean2 {

    private static final Logger LOGGER = Logger.getLogger(AsyncBean2.class);

    @Async
    public Future<Boolean> callAsync2(final int count) {
        LOGGER.info(count + ": Async call to async bean 2.");

        return new AsyncResult<>(true);
    }
}

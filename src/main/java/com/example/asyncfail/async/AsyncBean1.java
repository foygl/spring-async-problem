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

        /*
         * Note: Removing this nested async call causes the blocking problem to go away which implies this is only an
         * issue for nested async calls.
         */
        final Future<Boolean> asyncResult = asyncBean2.callAsync2(count);
        asyncResult.get();

        return new AsyncResult<>(true);
    }
}

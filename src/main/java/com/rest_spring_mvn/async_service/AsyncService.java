package com.rest_spring_mvn.async_service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {

    @Async
    public String slowOperation() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("1");
        Thread.sleep(1000);
        System.out.println("2");
        Thread.sleep(1000);
        System.out.println("3");

        return "Slow operation completed";
    }

}

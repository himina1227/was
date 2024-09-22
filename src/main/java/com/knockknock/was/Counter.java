package com.knockknock.was;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Counter implements Runnable {

    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
    @Override
    public void run() {
        this.increment();
        log.info("Value for Thread Name After Increment : {} is Value : {}", Thread.currentThread().getName(), getCount());

        this.decrement();
        log.info("Value for Thread Name After Decrement : {} is Value : {}", Thread.currentThread().getName(), getCount());
    }
}

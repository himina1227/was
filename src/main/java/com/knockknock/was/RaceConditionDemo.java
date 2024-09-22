package com.knockknock.was;

public class RaceConditionDemo {

    /**
     * 서블릿 객체는 싱글톤으로 관리(인스턴스를 하나만 생성해서 공유하는 방식)
     *
     * 상태를 유지하게 설계하면 안되며 Thread safety하지 않다.
     *
     * @param args
     */
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

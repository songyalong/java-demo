package com.example.java.javademo.zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class RecipesNoLock {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (Exception e) {
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                    String format = simpleDateFormat.format(new Date());
                    System.out.println(format);

                }
            }).start();
        }

        countDownLatch.countDown();

    }
}
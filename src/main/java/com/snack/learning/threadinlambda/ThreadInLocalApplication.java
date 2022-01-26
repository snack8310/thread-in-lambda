package com.snack.learning.threadinlambda;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ThreadInLocalApplication {

	private static final Logger logger = LoggerFactory.getLogger(ThreadInLocalApplication.class);

	public static void main(String[] args) {
		logger.info("availableProcessors: " + Runtime.getRuntime().availableProcessors());

        IntStream.range(1, 10).parallel().forEach(id->{
            logger.info("Thread: " + Thread.currentThread());
            logger.info("ForkJoinPool Size: " + ForkJoinPool.commonPool().getPoolSize());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
	}

}

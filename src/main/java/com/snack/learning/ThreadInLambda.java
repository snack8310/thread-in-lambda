package com.snack.learning;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadInLambda implements RequestHandler<APIGatewayProxyRequestEvent, Object> {

    private static final Logger logger = LoggerFactory.getLogger(ThreadInLambda.class);

    @Override
    public Object handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        
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
        return new APIGatewayProxyResponseEvent().withBody("Hi " + input.getBody()).withStatusCode(200);
    }

    
}

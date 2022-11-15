package com.consumer.feedme.Exception.Handler;

import com.consumer.feedme.Exception.model.FeedTypeNotFoundException;
import com.mongodb.MongoSocketOpenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    @ExceptionHandler(FeedTypeNotFoundException.class)
    public void handleFeedTypeNotFoundException(FeedTypeNotFoundException request) {
        LOGGER.error(request.getMessage(), request);
        System.out.println("SORRY!! NO SUCH FEED TYPE");
    }

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException request) {
        LOGGER.error(request.getMessage(), request);
        System.out.println("SORRY!! NULL PACKET OR VALUE RECEIVED");
    }

    @ExceptionHandler(MongoSocketOpenException.class)
    public void handleMongodbConnectionException(MongoSocketOpenException request) {
        LOGGER.error(request.getMessage(), request);
        System.out.println("MONGODB SOCKET CONNECTION ERROR");
    }

    @ExceptionHandler(IOException.class)
    public void handleIOException(IOException request) {
        LOGGER.error(request.getMessage(), request);
        System.out.println("IO EXCEPTION");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception request) {
        LOGGER.error(request.getMessage(), request);
        System.out.println("SORRY!!SOME EXCEPTION OCCURRED");
    }


}

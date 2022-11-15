package com.consumer.feedme.service;

import com.consumer.feedme.model.*;
import com.consumer.feedme.repository.EventRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;
    public String convertToEventJson(String[] feed) {


            Event event = Event.builder()
                    .eventHeader(FeedHeader.builder()
                            .msgId(Integer.valueOf(feed[0]))
                            .operation(FeedOperationEnum.valueOf(feed[1].toUpperCase()))
                            .type(FeedTypeEnum.valueOf(feed[2].toUpperCase()))
                            .timestamp(Long.valueOf(feed[3]))
                            .build())
                    .eventBody(
                            EventBody.builder()
                                    .eventId(feed[4])
                                    .category(feed[5])
                                    .subCategory(feed[6])
                                    .name(feed[7] + feed[8] + " " + feed[9])
                                    .startTime(Long.valueOf(feed[10]))
                                    .displayed(feed[11].equals("1"))
                                    .suspended(feed[12].equals("1"))
                                    .build())
                    .build();

            String eventJson=new Gson().newBuilder().setPrettyPrinting().create().toJson(event);
            eventRepository.save(event);
            return eventJson;
    }
}

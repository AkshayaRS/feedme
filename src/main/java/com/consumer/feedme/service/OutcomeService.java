package com.consumer.feedme.service;

import com.consumer.feedme.model.*;
import com.consumer.feedme.repository.OutcomeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    public String convertToOutcomeJson(String[] feed) {

        Outcome outcome = Outcome.builder()
                    .outcomeHeader(FeedHeader.builder()
                            .msgId(Integer.valueOf(feed[0]))
                            .operation(FeedOperationEnum.valueOf(feed[1].toUpperCase()))
                            .type(FeedTypeEnum.valueOf(feed[2].toUpperCase()))
                            .timestamp(Long.valueOf(feed[3]))
                            .build())
                    .outcomeBody(OutcomeBody.builder()
                            .marketId(feed[4])
                            .outcomeId(feed[5])
                            .name(feed[6])
                            .price(feed[7])
                            .displayed(feed[8].equals("1"))
                            .suspended(feed[9].equals("1"))
                            .build())
                    .build();

        String outcomeJson = new Gson().newBuilder().setPrettyPrinting().create().toJson(outcome);
        outcomeRepository.save(outcome);
        return outcomeJson;

    }
}

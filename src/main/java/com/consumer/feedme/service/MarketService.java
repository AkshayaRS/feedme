package com.consumer.feedme.service;

import com.consumer.feedme.model.*;
import com.consumer.feedme.repository.MarkerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    @Autowired
    MarkerRepository markerRepository;
    public String convertToMarketJson(String[] feed) {

            Market market = Market.builder()
                    .marketHeader(FeedHeader.builder()
                            .msgId(Integer.valueOf(feed[0]))
                            .operation(FeedOperationEnum.valueOf(feed[1].toUpperCase()))
                            .type(FeedTypeEnum.valueOf(feed[2].toUpperCase()))
                            .timestamp(Long.valueOf(feed[3]))
                            .build())
                    .marketBody(MarketBody.builder()
                            .eventId(feed[4])
                            .marketId(feed[5])
                            .name(feed[6])
                            .displayed(feed[7].equals("1"))
                            .suspended(feed[8].equals("1"))
                            .build())
                    .build();

            String marketJson = new Gson().newBuilder().setPrettyPrinting().create().toJson(market);
            markerRepository.save(market);
            return marketJson;

    }
}

package com.consumer.feedme.consumer;

import com.consumer.feedme.Exception.model.FeedTypeNotFoundException;
import com.consumer.feedme.service.EventService;
import com.consumer.feedme.service.MarketService;
import com.consumer.feedme.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.Arrays;

@MessageEndpoint
public class TcpConsumer {

    @Autowired
    EventService eventService;

    @Autowired
    MarketService marketService;

    @Autowired
    OutcomeService outcomeService;


    @ServiceActivator(inputChannel = "feedMe-Consumer")
    public void consume(byte[] bytes) {
        String packet=new String(bytes);
        System.out.println(packet);
        System.out.println(parseFeed(packet));
    }

    public String parseFeed(String packet){
        //remove space and backslash
        String cleanPacket = packet.replaceAll("[\\s]*\\\\", "");
        //remove 1st and last blank char
        String trimmedPacket = cleanPacket.substring(1, cleanPacket.length() - 1);
        //replace double pipe with single
        trimmedPacket = trimmedPacket.replaceAll("\\|\\|","\\|");
        //get the feed
        String[] feed = trimmedPacket.split("\\|");
        return convertFeedToJsonBasedOnFeedType(feed);
    }

    public String convertFeedToJsonBasedOnFeedType(String[] feed){
        switch (feed[2].toUpperCase()){
            case "EVENT": return eventService.convertToEventJson(feed);
            case "MARKET": return marketService.convertToMarketJson(feed);
            case "OUTCOME": return outcomeService.convertToOutcomeJson(feed);
            default :
                String message = "FEED TYPE NOT FOUND";
                throw new FeedTypeNotFoundException(message);
        }
    }

}
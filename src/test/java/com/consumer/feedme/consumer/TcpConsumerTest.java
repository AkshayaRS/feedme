package com.consumer.feedme.consumer;

import com.consumer.feedme.Exception.model.FeedTypeNotFoundException;
import com.consumer.feedme.service.EventService;
import com.consumer.feedme.service.MarketService;
import com.consumer.feedme.service.OutcomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TcpConsumerTest {

    @Mock
    EventService eventService;

    @Mock
    MarketService marketService;

    @Mock
    OutcomeService outcomeService;

    @InjectMocks
    TcpConsumer tcpConsumer;

    String eventReceived;
    String[] eventReceivedArr = new String[12];
    String expectedEventFeed;

    String marketFeedReceived;
    String[] marketFeedReceivedArr = new String[12];
    String expectedMarketFeed;

    String outcomeReceivedFeed;
    String[] outcomeFeedReceivedArr = new String[12];
    String expectedOutcomeFeed;

    @BeforeEach
    public void setUp() {

        initializeEventFeed();
        initializeMarketFeed();
        initializeOutcomeFeed();

    }

    @Test
    public void validParseFeedForEvent(){
        Mockito.when(eventService.convertToEventJson(Mockito.any())).thenReturn(expectedEventFeed);
        String actualEvent = tcpConsumer.parseFeed(eventReceived);
        Assertions.assertEquals(expectedEventFeed,actualEvent);
    }
   @Test
    public void validParseFeedForOutcome(){
        Mockito.when(outcomeService.convertToOutcomeJson(Mockito.any())).thenReturn(expectedOutcomeFeed);
        String actualOutcomeFeed = tcpConsumer.parseFeed(outcomeReceivedFeed);
        Assertions.assertEquals(expectedOutcomeFeed,actualOutcomeFeed);
    }
   @Test
    public void validParseFeedForMarket(){
        Mockito.when(marketService.convertToMarketJson(Mockito.any())).thenReturn(expectedMarketFeed);
        String actualMarketFeed = tcpConsumer.parseFeed(marketFeedReceived);
        Assertions.assertEquals(expectedMarketFeed,actualMarketFeed);
    }

    @Test
    public void invalidPacketParseFeed(){
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            String actualMarketFeed = tcpConsumer.parseFeed(null);
        });

        Assertions.assertEquals(NullPointerException.class, thrown.getClass());
    }

   @Test
    public void validConvertFeedToJsonBasedOnFeedTypeForEvent(){
        Mockito.when(eventService.convertToEventJson(Mockito.any())).thenReturn(expectedEventFeed);
        String actualEvent = tcpConsumer.convertFeedToJsonBasedOnFeedType(eventReceivedArr);
        Assertions.assertEquals(expectedEventFeed,actualEvent);
    }
   @Test
    public void validConvertFeedToJsonBasedOnFeedTypeForOutcome(){
        Mockito.when(outcomeService.convertToOutcomeJson(Mockito.any())).thenReturn(expectedOutcomeFeed);
        String actualOutcomeFeed = tcpConsumer.convertFeedToJsonBasedOnFeedType(outcomeFeedReceivedArr);
        Assertions.assertEquals(expectedOutcomeFeed,actualOutcomeFeed);
    }
   @Test
    public void validConvertFeedToJsonBasedOnFeedTypeForMarket(){
        Mockito.when(marketService.convertToMarketJson(Mockito.any())).thenReturn(expectedMarketFeed);
        String actualMarketFeed = tcpConsumer.convertFeedToJsonBasedOnFeedType(marketFeedReceivedArr);
        Assertions.assertEquals(expectedMarketFeed,actualMarketFeed);
    }

    @Test
    public void invalidPacketTestForConvertFeedToJsonBasedOnFeedType(){
        FeedTypeNotFoundException thrown = Assertions.assertThrows(FeedTypeNotFoundException.class, () -> {
            String actualMarketFeed = tcpConsumer.convertFeedToJsonBasedOnFeedType(new String[]{
                    "2", "create", "marke", "1668462996526", "ffe6a64c-4eed-4837-a4ec-ad3cef84b984", "31d5fb73-0faa-42ba-a27f-91eb48eaaeab", "Full Time Result",
                    "0", "1"});
        });

        Assertions.assertEquals(FeedTypeNotFoundException.class, thrown.getClass());
    }

    private void initializeEventFeed() {
        eventReceived="|1|create|event|1668462996525|ffe6a64c-4eed-4837-a4ec-ad3cef84b984|Football|Sky Bet Championship|\\|Fulham\\| vs \\|Middlesbrough\\||1668463040113|0|1|";

        eventReceivedArr = new String[]{
                "1", "create", "event", "1668462996525", "ffe6a64c-4eed-4837-a4ec-ad3cef84b984", "Football", "Sky Bet Championship",
                "Fulham"," vs","Middlesbrough", "1668463040113", "0", "1"};

        expectedEventFeed = "{\n" +
                "  \"eventHeader\": {\n" +
                "    \"msgId\": 1,\n" +
                "    \"operation\": \"CREATE\",\n" +
                "    \"type\": \"EVENT\",\n" +
                "    \"timestamp\": 1668462996525\n" +
                "  },\n" +
                "  \"eventBody\": {\n" +
                "    \"eventId\": \"ffe6a64c-4eed-4837-a4ec-ad3cef84b984\",\n" +
                "    \"category\": \"Football\",\n" +
                "    \"subCategory\": \"Sky Bet Championship\",\n" +
                "    \"name\": \"Fulham vs Middlesbrough\",\n" +
                "    \"startTime\": 1668463040113,\n" +
                "    \"displayed\": false,\n" +
                "    \"suspended\": true\n" +
                "  }\n" +
                "}" +
                "";
    }

    private void initializeMarketFeed() {

        marketFeedReceived="|2|create|market|1668462996526|ffe6a64c-4eed-4837-a4ec-ad3cef84b984|31d5fb73-0faa-42ba-a27f-91eb48eaaeab|Full Time Result|0|1|";
        marketFeedReceivedArr = new String[]{
                "2", "create", "market", "1668462996526", "ffe6a64c-4eed-4837-a4ec-ad3cef84b984", "31d5fb73-0faa-42ba-a27f-91eb48eaaeab", "Full Time Result",
                "0", "1"};

        expectedMarketFeed = "{\n" +
                "  \"marketHeader\": {\n" +
                "    \"msgId\": 2,\n" +
                "    \"operation\": \"CREATE\",\n" +
                "    \"type\": \"MARKET\",\n" +
                "    \"timestamp\": 1668462996526\n" +
                "  },\n" +
                "  \"marketBody\": {\n" +
                "    \"eventId\": \"ffe6a64c-4eed-4837-a4ec-ad3cef84b984\",\n" +
                "    \"marketId\": \"31d5fb73-0faa-42ba-a27f-91eb48eaaeab\",\n" +
                "    \"name\": \"Full Time Result\",\n" +
                "    \"displayed\": false,\n" +
                "    \"suspended\": true\n" +
                "  }\n" +
                "}";
    }

    private   void initializeOutcomeFeed(){
        outcomeReceivedFeed="|3|create|outcome|1668462996526|31d5fb73-0faa-42ba-a27f-91eb48eaaeab|39988979-c57a-44e9-bd50-e5ee8f4e54d9|\\|Fulham\\||33/1|0|1|";

        outcomeFeedReceivedArr = new String[]{
                "3", "create", "outcome", "1668462996526", "31d5fb73-0faa-42ba-a27f-91eb48eaaeab","39988979-c57a-44e9-bd50-e5ee8f4e54d9"
                ,"Fulham","33/1","0", "1"};

        expectedOutcomeFeed = "{\n" +
                "  \"outcomeHeader\": {\n" +
                "    \"msgId\": 3,\n" +
                "    \"operation\": \"CREATE\",\n" +
                "    \"type\": \"OUTCOME\",\n" +
                "    \"timestamp\": 1668462996526\n" +
                "  },\n" +
                "  \"outcomeBody\": {\n" +
                "    \"marketId\": \"31d5fb73-0faa-42ba-a27f-91eb48eaaeab\",\n" +
                "    \"outcomeId\": \"39988979-c57a-44e9-bd50-e5ee8f4e54d9\",\n" +
                "    \"name\": \"Fulham\",\n" +
                "    \"price\": \"33/1\",\n" +
                "    \"displayed\": false,\n" +
                "    \"suspended\": true\n" +
                "  }\n" +
                "}";

    }
}

package com.consumer.feedme.service;

import com.consumer.feedme.model.Market;
import com.consumer.feedme.repository.MarkerRepository;
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
public class MarketServiceTest {

    @Mock
    MarkerRepository marketRepository;
    @InjectMocks
    MarketService marketService;

    String[] marketFeedReceived = new String[12];
    String expected;

    @BeforeEach
    public void setUp() {
        marketFeedReceived = new String[]{
                "6751", "create", "market", "1668440517245", "de0a0e5e-e365-4ca3-9bf7-a97ebdd6e37f", "ff523bcb-005a-4bb5-9053-ede609166ba2", "Full Time Result",
                 "0", "1"};

        expected = "{\n" +
                "  \"marketHeader\": {\n" +
                "    \"msgId\": 6751,\n" +
                "    \"operation\": \"CREATE\",\n" +
                "    \"type\": \"MARKET\",\n" +
                "    \"timestamp\": 1668440517245\n" +
                "  },\n" +
                "  \"marketBody\": {\n" +
                "    \"eventId\": \"de0a0e5e-e365-4ca3-9bf7-a97ebdd6e37f\",\n" +
                "    \"marketId\": \"ff523bcb-005a-4bb5-9053-ede609166ba2\",\n" +
                "    \"name\": \"Full Time Result\",\n" +
                "    \"displayed\": false,\n" +
                "    \"suspended\": true\n" +
                "  }\n" +
                "}";

           }

    @Test
    public void validEventParse() {

        Mockito.when(marketRepository.save(Mockito.any())).thenReturn(Market.builder().build());
        String actual = marketService.convertToMarketJson(marketFeedReceived);
        Assertions.assertEquals(expected,actual);
        Mockito.verify(marketRepository,Mockito.times(1)).save(Mockito.any());
    }
}

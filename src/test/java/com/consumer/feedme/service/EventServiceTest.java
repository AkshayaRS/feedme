package com.consumer.feedme.service;


import com.consumer.feedme.model.Event;
import com.consumer.feedme.repository.EventRepository;
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
public class EventServiceTest {

    @Mock
    EventRepository eventRepository;
    @InjectMocks
    EventService eventService;

    String[] eventReceived = new String[12];
    String expected;

    @BeforeEach
    public void setUp() {
        eventReceived = new String[]{
                "6750", "create", "event", "1668440517244", "de0a0e5e-e365-4ca3-9bf7-a97ebdd6e37f", "Football", "Premier League",
                "Newcastle"," vs","Manchester City", "1668440557790", "0", "1"};

        expected = "{\n" +
                "  \"eventHeader\": {\n" +
                "    \"msgId\": 6750,\n" +
                "    \"operation\": \"CREATE\",\n" +
                "    \"type\": \"EVENT\",\n" +
                "    \"timestamp\": 1668440517244\n" +
                "  },\n" +
                "  \"eventBody\": {\n" +
                "    \"eventId\": \"de0a0e5e-e365-4ca3-9bf7-a97ebdd6e37f\",\n" +
                "    \"category\": \"Football\",\n" +
                "    \"subCategory\": \"Premier League\",\n" +
                "    \"name\": \"Newcastle vs Manchester City\",\n" +
                "    \"startTime\": 1668440557790,\n" +
                "    \"displayed\": false,\n" +
                "    \"suspended\": true\n" +
                "  }\n" +
                "}";
    }

    @Test
    public void validEventParse() {

        Mockito.when(eventRepository.save(Mockito.any())).thenReturn(Event.builder().build());
        String actual = eventService.convertToEventJson(eventReceived);
        Assertions.assertEquals(expected,actual);
        Mockito.verify(eventRepository,Mockito.times(1)).save(Mockito.any());
    }

}
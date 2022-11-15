package com.consumer.feedme.service;

import com.consumer.feedme.model.Market;
import com.consumer.feedme.model.Outcome;
import com.consumer.feedme.repository.MarkerRepository;
import com.consumer.feedme.repository.OutcomeRepository;
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
public class OutcomeServiceTest {
    @Mock
    OutcomeRepository outcomeRepository;
    @InjectMocks
    OutcomeService outcomeService;

    String[] outcomeFeedReceived = new String[12];
    String expected;

    @BeforeEach
    public void setUp() {
        outcomeFeedReceived = new String[]{
                "6752", "create", "outcome", "1668440517245", "ff523bcb-005a-4bb5-9053-ede609166ba2","dad54a8b-023c-4616-8381-8534f65bd92f"
                ,"Peterborough","4/1","0", "1"};

        expected = "{\n" +
                "  \"outcomeHeader\": {\n" +
                "    \"msgId\": 6752,\n" +
                "    \"operation\": \"CREATE\",\n" +
                "    \"type\": \"OUTCOME\",\n" +
                "    \"timestamp\": 1668440517245\n" +
                "  },\n" +
                "  \"outcomeBody\": {\n" +
                "    \"marketId\": \"ff523bcb-005a-4bb5-9053-ede609166ba2\",\n" +
                "    \"outcomeId\": \"dad54a8b-023c-4616-8381-8534f65bd92f\",\n" +
                "    \"name\": \"Peterborough\",\n" +
                "    \"price\": \"4/1\",\n" +
                "    \"displayed\": false,\n" +
                "    \"suspended\": true\n" +
                "  }\n" +
                "}";

    }

    @Test
    public void validEventParse() {

        Mockito.when(outcomeRepository.save(Mockito.any())).thenReturn(Outcome.builder().build());
        String actual = outcomeService.convertToOutcomeJson(outcomeFeedReceived);
        Assertions.assertEquals(expected,actual);
        Mockito.verify(outcomeRepository,Mockito.times(1)).save(Mockito.any());
    }
}

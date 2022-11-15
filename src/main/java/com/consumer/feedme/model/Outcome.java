package com.consumer.feedme.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Builder
@Document(collection = "BetFeedData")
public class Outcome{

    private FeedHeader outcomeHeader;
    private OutcomeBody outcomeBody;

}

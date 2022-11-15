package com.consumer.feedme.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Builder
@Document(collection = "BetFeedData")
public class Event{
    private FeedHeader eventHeader;
    private EventBody eventBody;
}

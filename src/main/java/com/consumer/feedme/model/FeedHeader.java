package com.consumer.feedme.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@Builder
public class FeedHeader {

    @Id
    private Integer msgId;
    private FeedOperationEnum operation;
    private FeedTypeEnum type;
    private Long timestamp;

}

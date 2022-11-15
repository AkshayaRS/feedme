package com.consumer.feedme.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class EventBody {

    private String eventId;
    private String category;
    private String subCategory;
    private String name;
    private Long startTime;
    private boolean displayed;
    private boolean suspended;
}

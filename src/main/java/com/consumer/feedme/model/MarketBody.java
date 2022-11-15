package com.consumer.feedme.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class MarketBody {
    private String eventId;
    private String marketId;
    private String name;
    private boolean displayed;
    private boolean suspended;
}
